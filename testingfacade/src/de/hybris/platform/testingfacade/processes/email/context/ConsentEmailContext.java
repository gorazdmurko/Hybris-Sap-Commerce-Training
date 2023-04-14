package de.hybris.platform.testingfacade.processes.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.testingcore.model.custom.ConsentEmailProcessModel;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.log4j.Logger;


public class ConsentEmailContext extends AbstractEmailContext<ConsentEmailProcessModel> {

    private String token;
    private CustomerData customerData;
    private CustomerModel customerModel;
    private CustomerData subscriberData;
    private Converter<UserModel, CustomerData> customerConverter;
    // private Converter<GGSubscriberModel, CustomerData> subscriberConverter;

    private static final Logger LOG = org.apache.log4j.Logger.getLogger(ConsentEmailContext.class);

    @Override
    public void init(final ConsentEmailProcessModel consentEmailProcessModel, final EmailPageModel emailPageModel) {
        super.init(consentEmailProcessModel, emailPageModel);

        if (getCustomer(consentEmailProcessModel) != null) {
            customerData = getCustomerConverter().convert(getCustomer(consentEmailProcessModel));
        } else {
            // customerData = getSubscriberConverter().convert(getSubscriber(consentEmailProcessModel));
            // subscriberData = getSubscriberConverter().convert(getSubscriber(consentEmailProcessModel));
        }

        put(EMAIL, getCustomerEmailResolutionService().getEmailForCustomer(getCustomer(consentEmailProcessModel)));
        put(DISPLAY_NAME, getCustomer(consentEmailProcessModel).getDisplayName());
        put(TITLE, (customerData.getTitle() != null) ? customerData.getTitle() : "");
        // put(DISPLAY_NAME, customerData.getName());
        put(EMAIL, customerData.getUid());
        setToken(((ConsentEmailProcessModel) consentEmailProcessModel).getToken());

        String confirmationURL = consentEmailProcessModel.getConsentConfirmationURL();
        put("confirmationURL", confirmationURL);

        try {
            String secureResetPasswordUrl = getSecureResetPasswordUrl();
            put("secureResetPasswordUrlLink", secureResetPasswordUrl);
            String displaySecureResetPasswordUrl = getDisplaySecureResetPasswordUrl();
            put("displaySecureResetPasswordUrlLink", displaySecureResetPasswordUrl);
        } catch (UnsupportedEncodingException e) {
            LOG.error(e);
        }
    }

    final private String getPath() {
        return (subscriberData == null ? "/email/confirm/customer" : "/email/confirm/subscriber");
    }

    public String getURLEncodedToken() throws UnsupportedEncodingException {
        return URLEncoder.encode(token, "UTF-8");
    }

    public String getSecureResetPasswordUrl() throws UnsupportedEncodingException {
        return getSiteBaseUrlResolutionService().getWebsiteUrlForSite(getBaseSite(), getUrlEncodingAttributes(), true, getPath(),
                "token=" + getURLEncodedToken());
    }

    public String getDisplaySecureResetPasswordUrl() throws UnsupportedEncodingException {
        return getSiteBaseUrlResolutionService().getWebsiteUrlForSite(getBaseSite(), getUrlEncodingAttributes(), true, getPath());
    }

    @Override
    protected BaseSiteModel getSite(ConsentEmailProcessModel businessProcessModel) {
        return businessProcessModel.getSite();
    }

    @Override
    protected CustomerModel getCustomer(ConsentEmailProcessModel businessProcessModel) {
        return (CustomerModel) businessProcessModel.getCustomer();
    }

    @Override
    protected LanguageModel getEmailLanguage(ConsentEmailProcessModel businessProcessModel) {
        return businessProcessModel.getCustomer().getSessionLanguage();
    }


    public String getToken() {
        return token;
    }
    public void setToken(final String ptoken) { this.token = ptoken; }
    public CustomerData getCustomerData() {
        return customerData;
    }
    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }
    public CustomerModel getCustomerModel() {
        return customerModel;
    }
    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }
    public Converter<UserModel, CustomerData> getCustomerConverter() {
        return customerConverter;
    }
    public void setCustomerConverter(Converter<UserModel, CustomerData> customerConverter) {
        this.customerConverter = customerConverter;
    }
}
