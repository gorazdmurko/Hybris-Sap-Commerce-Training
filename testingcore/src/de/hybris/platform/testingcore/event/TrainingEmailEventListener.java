package de.hybris.platform.testingcore.event;

import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.testingcore.model.custom.TrainingEmailProcessModel;
import org.springframework.beans.factory.annotation.Required;

import javax.crypto.KeyGenerator;

public class TrainingEmailEventListener extends AbstractAcceleratorSiteEventListener<TrainingEmailEvent> {

    private ModelService modelService;
    private BusinessProcessService businessProcessService;
    // private KeyGenerator processCodeGenerator;

    @Override
    protected void onSiteEvent(final TrainingEmailEvent event) {

//        final TrainingEmailProcessModel emailProcessModel = getBusinessProcessService().createProcess(
//                "trainingEmail-" + event.getCart().getCode() + "-" +
//                    processCodeGenerator.generateKey().toString(), "trainingEmailProcess"); // process name last argument

        final TrainingEmailProcessModel emailProcessModel = getBusinessProcessService().createProcess(
                "trainingEmail-" + event.getCart().getCode() + "-" + System.currentTimeMillis(), "trainingEmailProcess");

        emailProcessModel.setCart(event.getCart());
        emailProcessModel.setCurrency(event.getCurrency());
        emailProcessModel.setStore(event.getBaseStore());
        emailProcessModel.setSite(event.getSite());
        emailProcessModel.setLanguage(event.getLanguage());

        // START PROCESS
        getModelService().save(emailProcessModel);
        getBusinessProcessService().startProcess(emailProcessModel);    // it goes to the process start="generateTrainingEmail"
                                                                        // which calls GenerateEmailAction
    }

    @Override
    protected SiteChannel getSiteChannelForEvent(TrainingEmailEvent event) {
        final BaseSiteModel site = event.getSite();
        ServicesUtil.validateParameterNotNullStandardMessage("event.site", site);
        return site.getChannel();
    }

    // GETTERS & SETTERS
    protected ModelService getModelService() {
        return modelService;
    }

    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    public BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

    public void setBusinessProcessService(final BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

    // UNUSED
//    public KeyGenerator getProcessCodeGenerator() {
//        return processCodeGenerator;
//    }
//
//    public void setProcessCodeGenerator(KeyGenerator processCodeGenerator) {
//        this.processCodeGenerator = processCodeGenerator;
//    }
}
