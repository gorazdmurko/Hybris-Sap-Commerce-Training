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

        final ConsentEmailProcessModel consentEmailProcessModel = getBusinessProcessService().createProcess("consentEmail-" + event.getConsentConfirmationURL() + "-" + System.currentTimeMillis(), "consentEmailProcess");

        // START PROCESS
        getModelService().save(consentEmailProcessModel);
        getBusinessProcessService().startProcess(consentEmailProcessModel);     // it goes to the process start="generateConsentEmail"
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
