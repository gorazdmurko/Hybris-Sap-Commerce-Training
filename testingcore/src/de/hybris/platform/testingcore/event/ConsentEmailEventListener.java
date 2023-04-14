package de.hybris.platform.testingcore.event;

import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.testingcore.model.custom.ConsentEmailProcessModel;

public class ConsentEmailEventListener extends AbstractAcceleratorSiteEventListener<ConsentEmailEvent> {

    private ModelService modelService;
    private BusinessProcessService businessProcessService;

    @Override
    protected void onSiteEvent(ConsentEmailEvent event) {

        // MODEL
        final ConsentEmailProcessModel processModel = getBusinessProcessService().createProcess("consentEmail-" + event.getConsentConfirmationURL() + "-" + System.currentTimeMillis(), "consentEmailProcess");

        processModel.setToken(event.getToken());
        // processModel.setConsent(event.getConsent());
        processModel.setConsentConfirmationURL(event.getConsentConfirmationURL());
        processModel.setStore(event.getBaseStore());
        processModel.setLanguage(event.getLanguage());

//        if (event.getCustomer() == null) {
//            emailConfirmationProcessModel.setSubscriber(event.getGgSubscriberModel());
//        } else {
//            emailConfirmationProcessModel.setCustomer(event.getCustomer());
//        }

        processModel.setCustomer(event.getCustomer());

        // START PROCESS
        getModelService().save(processModel);
        getBusinessProcessService().startProcess(processModel);     // it goes to the process start="generateConsentEmail"
                                                                                // which calls GenerateEmailAction
    }

    @Override
    protected SiteChannel getSiteChannelForEvent(ConsentEmailEvent event) {
        final BaseSiteModel site = event.getSite();
        ServicesUtil.validateParameterNotNullStandardMessage("event.site", site);
        return site.getChannel();
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

    public void setBusinessProcessService(BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }
}
