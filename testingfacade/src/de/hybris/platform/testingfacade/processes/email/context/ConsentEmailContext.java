package de.hybris.platform.testingfacade.processes.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.testingcore.model.custom.ConsentEmailProcessModel;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class ConsentEmailContext extends AbstractEmailContext<ConsentEmailProcessModel> {

    private String token;
    private CustomerData customerData;
    private CustomerModel customerModel;
    private Converter<UserModel, CustomerData> customerConverter;

    private Map<SiteChannel, String> contextRoots;
    private static final String CMS_SITE_MODEL_CANNOT_BE_NULL_MSG = "CMS site model cannot be null";


    @Override
    public void init(final ConsentEmailProcessModel consentEmailProcessModel, final EmailPageModel emailPageModel) {
        super.init(consentEmailProcessModel, emailPageModel);
        put(EMAIL, getCustomerEmailResolutionService().getEmailForCustomer(getCustomer(consentEmailProcessModel)));
        put(DISPLAY_NAME, getCustomer(consentEmailProcessModel).getDisplayName());

        String confirmationURL = consentEmailProcessModel.getConsentConfirmationURL();
        put("confirmationURL", confirmationURL);
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






    // ----------------------------------------------------------------------------------------------------------------------------------

//    final private String getPath() {
//        return (subscriberData == null ? "/email/confirm/customer" : "/email/confirm/subscriber");
//    }

    public String getWebsiteUrlForSite(final BaseSiteModel site, final String encodingAttributes, final boolean secure,
                                       final String path)
    {
        validateParameterNotNull(site, CMS_SITE_MODEL_CANNOT_BE_NULL_MSG);

        final String url = cleanupUrl(lookupConfig("website." + site.getUid() + (secure ? ".https" : ".http")));
        final String websiteUrl = getWebsiteUrl(url, encodingAttributes, path);
        return StringUtils.isNotBlank(websiteUrl) ? websiteUrl : getDefaultWebsiteUrlForSite(site, secure, path);
    }

    protected String cleanupUrl(final String url) {
        if (url != null && url.endsWith("/"))
        {
            return url.substring(0, url.length() - 1);
        }
        return url;
    }

    protected String lookupConfig(final String key) {
        return getConfigurationService().getConfiguration().getString(key, null);
    }

    protected String getWebsiteUrl(String baseUrl, final String encodingAttributes, final String path) {
        //		 if url contains ? remove everything after ? then add path then add back the query string
        //		 this is so website urls in config files can have query strings and urls in emails will be
        //		 formatted correctly
        if (StringUtils.isBlank(baseUrl)) {
            return null;
        }
        if (baseUrl.contains("?"))
        {
            final String queryString = baseUrl.substring(baseUrl.indexOf('?'));
            final String tmpUrl = baseUrl.substring(0, baseUrl.indexOf('?'));
            return cleanupUrl(tmpUrl) + (StringUtils.isNotBlank(encodingAttributes) ? encodingAttributes : "")
                    + (path == null ? "" : path) + "/" + queryString;
        }
        return baseUrl + (StringUtils.isNotBlank(encodingAttributes) ? encodingAttributes : "") + (path == null ? "" : path);
    }

    protected String getDefaultWebsiteUrlForSite(final BaseSiteModel site, final boolean secure, final String path)
    {
        final String contextRoot = getDefaultWebsiteContextRootForSite(site);
        if (contextRoot != null)
        {
            final String schemeHostAndPort;
            if (secure)
            {
                schemeHostAndPort = "https://localhost:" + lookupConfig("tomcat.ssl.port");
            }
            else
            {
                schemeHostAndPort = "http://localhost:" + lookupConfig("tomcat.http.port");
            }

            final String url = schemeHostAndPort + contextRoot + path;
            final String queryParams = "clear=true&site=" + site.getUid();
            return appendQueryParams(url, queryParams);
        }
        return null;
    }

    protected String appendQueryParams(final String url, final String params)
    {
        if (url.contains("?"))
        {
            return url + "&" + params;
        }
        else
        {
            return url + "?" + params;
        }
    }

    protected String getDefaultWebsiteContextRootForSite(final BaseSiteModel site)
    {
        final Map<SiteChannel, String> roots = getContextRoots();
        if (site.getChannel() != null && roots.containsKey(site.getChannel()))
        {
            return cleanupUrl(roots.get(site.getChannel()));
        }
        return null;
    }

    protected Map<SiteChannel, String> getContextRoots() {
        return contextRoots;
    }

    public void setContextRoots(final Map<SiteChannel, String> contextRoots)
    {
        this.contextRoots = contextRoots;
    }

}
