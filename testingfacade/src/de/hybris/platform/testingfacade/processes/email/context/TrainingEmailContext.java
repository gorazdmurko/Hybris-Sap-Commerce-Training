package de.hybris.platform.testingfacade.processes.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.testingcore.model.custom.TrainingEmailProcessModel;

public class TrainingEmailContext extends AbstractEmailContext<TrainingEmailProcessModel> {

    @Override
    public void init(final TrainingEmailProcessModel trainingProcessModel, final EmailPageModel emailPageModel) {
        super.init(trainingProcessModel, emailPageModel);
        put(EMAIL, getCustomerEmailResolutionService().getEmailForCustomer(getCustomer(trainingProcessModel)));
        put(DISPLAY_NAME, getCustomer(trainingProcessModel).getDisplayName());
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
}
