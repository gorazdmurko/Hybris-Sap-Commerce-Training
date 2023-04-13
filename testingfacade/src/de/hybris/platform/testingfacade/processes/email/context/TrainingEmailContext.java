package de.hybris.platform.testingfacade.processes.email.context;

import de.hybris.platform.acceleratorservices.dataexport.googlelocal.model.Product;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.testingcore.model.custom.HybrisTubeEmailProcessModel;
import de.hybris.platform.testingcore.model.custom.TrainingEmailProcessModel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

public class TrainingEmailContext extends AbstractEmailContext<TrainingEmailProcessModel> {

    private CustomerModel customerModel;

    @Override
    public void init(final TrainingEmailProcessModel trainingProcessModel, final EmailPageModel emailPageModel) {
        super.init(trainingProcessModel, emailPageModel);
        put(EMAIL, getCustomerEmailResolutionService().getEmailForCustomer(getCustomer(trainingProcessModel)));
        put(DISPLAY_NAME, getCustomer(trainingProcessModel).getDisplayName());

        customerModel = getCustomer(trainingProcessModel);
        put("customer", customerModel);

        ProductModel product = trainingProcessModel.getCart().getEntries().get(0).getProduct();
        put("product", product);

        // image to Base64
        StringBuilder baseUrl = new StringBuilder("https://electronics.local:9002/testingstorefront");
        String imageUrl = getUrl(trainingProcessModel);
        baseUrl.append(imageUrl);
        String url = baseUrl.toString();

        System.out.println("\nImage URL: " + url);

        url = Base64.getUrlEncoder().encodeToString(url.getBytes());
        put("encodedUrl", url);

        System.out.println("\nEncoded URL: " + url);
    }

    @Override
    protected BaseSiteModel getSite(TrainingEmailProcessModel businessProcessModel) {
        return businessProcessModel.getCart().getSite();
    }

    @Override
    protected CustomerModel getCustomer(TrainingEmailProcessModel businessProcessModel) {
        return (CustomerModel) businessProcessModel.getCart().getUser();
    }

    @Override
    protected LanguageModel getEmailLanguage(TrainingEmailProcessModel businessProcessModel) {
        return businessProcessModel.getCart().getUser().getSessionLanguage();
    }

    public String getUrl(TrainingEmailProcessModel businessProcessModel) {
        return businessProcessModel.getCart().getEntries().get(0).getProduct().getPicture().getURL();
    }

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }
}
