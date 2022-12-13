package de.hybris.platform.testingbackoffice.imp;

import com.hybris.backoffice.widgets.notificationarea.NotificationService;
import com.hybris.backoffice.widgets.notificationarea.event.NotificationEvent;
import com.hybris.cockpitng.config.jaxb.wizard.CustomType;
import com.hybris.cockpitng.core.util.CockpitProperties;
import com.hybris.cockpitng.editor.defaultfileupload.FileUploadResult;
import com.hybris.cockpitng.widgets.configurableflow.FlowActionHandler;
import com.hybris.cockpitng.widgets.configurableflow.FlowActionHandlerAdapter;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.media.services.MimeService;
//import de.hybris.platform.productcockpit.services.product.ProductService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.testingbackoffice.imp.wizard.FileImportWizardForm;
import de.hybris.platform.testingfacade.parser.IStaxParser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class FileImportHandler implements FlowActionHandler {

    public static final String XML_EXTENSION = "xml";
    private static final Logger LOG = LoggerFactory.getLogger(FileImportHandler.class);

    private MimeService mimeService;
    private CockpitProperties cockpitProperties;
    private NotificationService notificationService;

    private IStaxParser parser;
    private ModelService modelService;
    private ProductService productService;  // productCockpitProductService bean of productServiceImpl class, productService

    public FileImportHandler() {
        System.out.println("\nFileImportHandler constructor called. Implementation goes here.");
    }

    public void perform(CustomType customType, FlowActionHandlerAdapter adapter, Map<String, String> parameters) {

        LOG.debug("Perform File Import!");
        System.out.println("\nperform() method of FileImportHandler class invoked");

        FileImportWizardForm data = adapter.getWidgetInstanceManager().getModel().getValue("fileForm", FileImportWizardForm.class);
        ProductModel model = adapter.getWidgetInstanceManager().getModel().getValue("newProduct", ProductModel.class);

        if (data == null) {
            this.getNotificationService().notifyUser("fileImport", "fileMissingFormInModel", NotificationEvent.Level.FAILURE, new Object[0]);
        } else {
            FileContent dataFile = toFileContent(data.getImportFile());
            // CatalogVersionModel catalogVersion = data.getCatalogVersion();

            if(!StringUtils.equals(XML_EXTENSION,getMimeService().getBestExtensionFromMime(data.getImportFile().getContentType())))
            {
                this.getNotificationService().notifyUser("fileImport", "fileMissingFile", NotificationEvent.Level.FAILURE, new Object[0]);
            }
            else
            {
                // TODO get input stream out of byte
                byte[] stream = dataFile.getData();
                InputStream targetStream = new ByteArrayInputStream(stream);

                try {
                    ProductModel uploadedModel = parser.xmlEventReader(targetStream);
                    model.setCode(uploadedModel.getCode());

                    // fetch all products from db that have same catalogVersion as uploaded/selected product model
                    List<ProductModel> fetchedCatalogList = productService.getAllProductsForCatalogVersion(model.getCatalogVersion());
                    System.out.println("CATALOG LIST LENGTH: " + fetchedCatalogList.size());

                    boolean productExists = false;

                    for (ProductModel pm : fetchedCatalogList) {
                        if ((!Objects.equals(pm.getCode(), model.getCode())) & (pm.getCatalogVersion() != model.getCatalogVersion())) {
                            log(pm, model);
                            productExists = false;
                        } else {
                            log(pm, model);
                            productExists = true;
                        }
                    }

                    if (!productExists) {
                        modelService.save(model);
                        this.getNotificationService().notifyUser("fileImport", "fileMissingFile",
                                NotificationEvent.Level.SUCCESS, new Object[0]);
                    } else {
                        System.out.println("PRODUCT ALREADY EXISTS");

                        Messagebox.show("PRODUCT ALREADY EXISTS!",
                            Messagebox.EXCLAMATION, Messagebox.OK,
                            "Messagebox.QUESTION is an object",
                            clickEvent -> {
                                System.out.println("Product successfully imported");
                                if (Messagebox.ON_OK.equals(clickEvent.getName())){
                                    // OK is clicked
                                    System.out.println("Product already imported");
                                }
                            }
                        );
                    }


                } catch (IOException | XMLStreamException e) {
                    e.printStackTrace();
                }

                getNotificationService().notifyUser("fileImport","FileImportSuccess",NotificationEvent.Level.SUCCESS);
                adapter.done(); // finish process and close the wizard

            }
            if (dataFile == null) {
                this.getNotificationService().notifyUser("fileImport", "MissingExcelFile", NotificationEvent.Level.FAILURE, new Object[0]);
            }
        }
    }

    protected void log(ProductModel pm, ProductModel model) {
        System.out.println("UPLOADED CODE: " + model.getCode() +
                ", UPLOADED CAT_VER: " + model.getCatalogVersion() +
                ", UPLOADED CATALOG: " + model.getCatalogVersion().getCatalog() +
                ", UPLOADED VERSION: " + model.getCatalogVersion().getVersion());
        System.out.println(" FETCHED CODE: " + pm.getCode() +
                " , FETCHED CAT_VER: " + pm.getCatalogVersion() +
                " , FETCHED CATALOG: " + pm.getCatalogVersion().getCatalog() +
                " , FETCHED VERSION: " + pm.getCatalogVersion().getVersion());
    }

    protected FileContent toFileContent(FileUploadResult uploadResult) {
        return uploadResult != null ? new FileContent(uploadResult.getData(), uploadResult.getContentType(), uploadResult.getName()) : null;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public MimeService getMimeService() {
        return this.mimeService;
    }

    @Required
    public void setMimeService(MimeService mimeService) {
        this.mimeService = mimeService;
    }


    public CockpitProperties getCockpitProperties() {
        return this.cockpitProperties;
    }

    @Required
    public void setCockpitProperties(CockpitProperties cockpitProperties) {
        this.cockpitProperties = cockpitProperties;
    }

    public IStaxParser getParser() {
        return parser;
    }

    public void setParser(IStaxParser parser) {
        this.parser = parser;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
