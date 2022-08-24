package de.hybris.platform.testingbackoffice.imp;

import com.hybris.cockpitng.config.jaxb.wizard.CustomType;
import com.hybris.cockpitng.widgets.configurableflow.FlowActionHandler;
import com.hybris.cockpitng.widgets.configurableflow.FlowActionHandlerAdapter;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.testingbackoffice.imp.wizard.FileImportWizardForm;
import de.hybris.platform.testingbackoffice.imp.wizard.MultipleProductsSelectWizardForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class MultiselectHandler implements FlowActionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(FileImportHandler.class);

    @Override
    public void perform(CustomType customType, FlowActionHandlerAdapter adapter, Map<String, String> map) {

        LOG.debug("Perform multiselect!");
        System.out.println("\nperform() method of MultiselectHandler class invoked");

        // 1. parameter
        MultipleProductsSelectWizardForm data = adapter.getWidgetInstanceManager().getModel().getValue("products", MultipleProductsSelectWizardForm.class);
        List<ProductModel> products = data.getProductsModelList();
        System.out.println("Products list: " + products);

        // 2. parameter
        ProductModel productModel = adapter.getWidgetInstanceManager().getModel().getValue("newProduct", ProductModel.class);
        System.out.println("Product model: " + productModel);


        if (data != null) {
            for (ProductModel model : data.getProductsModelList()) {
                System.out.println(model);
            }
        }


        // close adapter when done
        adapter.done();
    }
}
