package de.hybris.platform.testingcore.event;

import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.testingcore.model.custom.HybrisTubeEmailProcessModel;

public class HybrisTubeEmailEventListener extends AbstractAcceleratorSiteEventListener<HybrisTubeEmailEvent> {

    private ModelService modelService;
    private BusinessProcessService businessProcessService;

    @Override
    protected void onSiteEvent(HybrisTubeEmailEvent event) {
        // HybrisTubeEmailProcessModel defined in process definition
        final HybrisTubeEmailProcessModel hybrisTubeProcessModel = getBusinessProcessService().createProcess(
                "hybrisTubeEmail-" + event.getCart().getCode() + "-" + System.currentTimeMillis(), "hybrisTubeEmailProcess");

        hybrisTubeProcessModel.setCart(event.getCart());
        hybrisTubeProcessModel.setCurrency(event.getCurrency());
        hybrisTubeProcessModel.setStore(event.getBaseStore());
        hybrisTubeProcessModel.setSite(event.getSite());
        hybrisTubeProcessModel.setLanguage(event.getLanguage());

        // START PROCESS
        getModelService().save(hybrisTubeProcessModel);
        getBusinessProcessService().startProcess(hybrisTubeProcessModel);   // it goes to the process start="generateHybrisTubeEmail"
                                                                            // which calls GenerateEmailAction
    }

    @Override
    protected SiteChannel getSiteChannelForEvent(HybrisTubeEmailEvent event) {
        final BaseSiteModel site = event.getSite();
        ServicesUtil.validateParameterNotNullStandardMessage("event.site", site);
        return site.getChannel();
    }

    // GETTERS & SETTERS
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
