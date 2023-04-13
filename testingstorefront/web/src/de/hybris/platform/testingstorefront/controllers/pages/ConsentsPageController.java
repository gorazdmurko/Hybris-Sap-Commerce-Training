package de.hybris.platform.testingstorefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.ConsentForm;
import de.hybris.platform.acceleratorstorefrontcommons.strategy.CustomerConsentDataStrategy;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.consent.ConsentFacade;
import de.hybris.platform.commercefacades.consent.data.ConsentTemplateData;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commerceservices.consent.CommerceConsentService;
import de.hybris.platform.commerceservices.consent.exceptions.CommerceConsentGivenException;
import de.hybris.platform.commerceservices.model.consent.ConsentModel;
import de.hybris.platform.commerceservices.model.consent.ConsentTemplateModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.testingcore.event.ConsentEmailEvent;
import de.hybris.platform.testingcore.event.TrainingEmailEvent;
import de.hybris.platform.testingstorefront.controllers.forms.ConsentSubscriptionForm;
import de.hybris.platform.testingstorefront.controllers.forms.TestingEmailForm;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping(value = "/consent")
public class ConsentsPageController extends AbstractPageController {

    private static final String CUSTOM_CMS_PAGE = "consentManagementCMSPage";  // !!! inserted w/impex in 3. step !!!
    private static final String REDIRECT_PREFIX = "redirect:";
    private static final String REDIRECT_TO_CONSENT = REDIRECT_PREFIX + "/consent";
    private static final String REDIRECT_TO_CONSENT_MANAGEMENT = REDIRECT_PREFIX + "/my-account/consents";

    private static final String CUSTOM_TEST_CONSENT_ID = "test.consent.id.";
    private static final String CUSTOM_PROFILE_CONSENT_ID = "profile-tracking.consent.id.";
    private static final String CUSTOM_COOKIES_CONSENT_ID = "cookies.consent.id.";
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(CustomPageController.class);

    @Resource(name = "sessionService")
    private SessionService sessionService;

    @Resource(name = "commerceConsentService")
    private CommerceConsentService consentService;

    @Resource(name = "consentFacade")
    private ConsentFacade consentFacade;

    @Resource(name = "configurationService")
    private ConfigurationService configurationService;

    @Resource(name = "baseSiteService")
    private BaseSiteService baseSiteService;

    @Resource(name = "customerConsentDataStrategy")
    protected CustomerConsentDataStrategy customerConsentDataStrategy;

    @Resource(name = "userFacade")
    private UserFacade userFacade;

    @Resource(name = "eventService")
    private EventService eventService;


    @RequestMapping(method = RequestMethod.GET)
    public String getConsentPage(final Model model) throws CMSItemNotFoundException {

        final ContentPageModel customCMSPage = getContentPageForLabelOrId(CUSTOM_CMS_PAGE);

        storeCmsPageInModel(model, customCMSPage);
        setUpMetaDataForContentPage(model, customCMSPage);

        model.addAttribute(new TestingEmailForm());

        // ADD CONSENT FORM TO MODEL
        model.addAttribute(new ConsentForm());
        model.addAttribute(new ConsentSubscriptionForm());

        // ADD CONSENT DATA
        setupConsentData(model);

        return getViewForPage(model);
    }

    private void setupConsentData(Model model) {

        addRegistrationConsentDataToModel(model);   // AbstractPageController method

        consentDataToModel(model, CUSTOM_TEST_CONSENT_ID, "customTestConsentTemplateData");
        consentDataToModel(model, CUSTOM_COOKIES_CONSENT_ID, "cookiesConsentTemplateData");
        consentDataToModel(model, CUSTOM_PROFILE_CONSENT_ID, "customProfileConsentTemplateData");
    }

    protected void consentDataToModel(final Model model, final String id, String modelDataString) {
        final String consentTemplateId = configurationService.getConfiguration()
                .getString(id + baseSiteService.getCurrentBaseSite().getUid());

        if (StringUtils.isNotBlank(consentTemplateId)) {
            final ConsentTemplateData consentData = getConsentFacade().getLatestConsentTemplate(consentTemplateId);
            model.addAttribute(modelDataString, consentData);
            // model.addAttribute("consentCode", ????);
        }
    }

    protected ConsentModel getConsentCode(ConsentForm form) {

        CustomerModel customer = sessionService.getAttribute("user");

        List<ConsentTemplateModel> consentTemplateModelList = consentService.getConsentTemplates(baseSiteService.getCurrentBaseSite());
        List<ConsentTemplateModel> modelList = consentTemplateModelList.stream().filter(model -> model.getId().equals(form.getConsentTemplateId())).collect(Collectors.toList());

        if (modelList.size() > 1) {
            throw new IllegalStateException();
        }

        ConsentTemplateModel templateModel = modelList.get(0);

        ConsentModel consentModel = consentService.getActiveConsent(customer, templateModel);

        return consentModel;
    }


    @RequestMapping(value = "/giveConsent", method = RequestMethod.POST)
    public String giveConsentTest(final ConsentForm form, final RedirectAttributes redirectModel) {

        // GIVE CONSENT w/facade -- returns error if consent is already given (else not)
        // consentFacade.giveConsent(form.getConsentTemplateId(), form.getConsentTemplateVersion());
        // GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, "text.account.consent.template.notFound", null);

        CustomerModel customerModel = sessionService.getAttribute("user");

        try {
            List<ConsentTemplateModel> consentTemplateModelList = consentService.getConsentTemplates(baseSiteService.getCurrentBaseSite());
            List<ConsentTemplateModel> modelList = consentTemplateModelList.stream().filter(model -> model.getId().equals(form.getConsentTemplateId())).collect(Collectors.toList());

            List<ConsentTemplateData> consentTemplateDataList = consentFacade.getConsentTemplatesWithConsents();
            List<ConsentTemplateData> dataList = consentTemplateDataList.stream().filter(template -> template.getId().equals(form.getConsentTemplateId())).collect(Collectors.toList());

            if (modelList.size() > 1) {
                throw new IllegalStateException();
            }

            if (dataList.size() > 1) {
                throw new IllegalStateException();
            }

            ConsentTemplateModel templateModel = modelList.get(0);

            ConsentTemplateData templateData = dataList.get(0);
            LOG.info("Template data: " + templateData);

            // GIVE CONSENT w/service
            consentService.giveConsent(customerModel, templateModel);

        } catch (Exception e) {
            LOG.error(e);
        }

        if (userFacade.isAnonymousUser()) {

            this.eventService.publishEvent(initializeConsentEvent(new ConsentEmailEvent("consents-confirm@hybris.com/site=?electronics"), "consents-confirm@hybris.com/site=?electronics"));

            LOG.warn("Consent event for anonymous user should be triggered here!");
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.INFO_MESSAGES_HOLDER,
                    "Thank you for subscribing. You should confirm email that was currently sent to your email box.", null);
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
                    "Thank you for subscribing. You should confirm email that was currently sent to your email box.", null);

            return REDIRECT_TO_CONSENT;
        }

        this.eventService.publishEvent(initializeConsentEvent(new ConsentEmailEvent("consents-confirm@hybris.com/site=?electronics"), "consents-confirm@hybris.com/site=?electronics"));

        LOG.warn("Consent event for logged user should be triggered here!");
        GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.INFO_MESSAGES_HOLDER,
                "Thank you for subscription. You can manage your consents in Consent Manager section of your account.", null);
        GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
                "Thank you for subscription. You can manage your consents in Consent Manager section of your account.", null);

        return REDIRECT_TO_CONSENT;
    }

    private AbstractEvent initializeConsentEvent(final ConsentEmailEvent event, String confirmationUrl) {
        event.setConsentConfirmationURL(confirmationUrl);
        return event;
    }


    @RequestMapping(value = "/account-management", method = RequestMethod.POST)
    public String redirectToAccountConsentManagement() {
        return REDIRECT_TO_CONSENT_MANAGEMENT;
    }


    // O-O-T-B
    @RequestMapping(value = "/giveOOTBConsent", method = RequestMethod.POST)
    public String giveConsent(final ConsentForm form, final RedirectAttributes redirectModel) {

        // consentTemplateId & version
        String consentTemplateId = form.getConsentTemplateId();
        Integer version = form.getConsentTemplateVersion();

        try
        {
            getConsentFacade().giveConsent(consentTemplateId, version);
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "text.account.consent.given");
        }
        catch (final ModelNotFoundException | AmbiguousIdentifierException e)
        {
            LOG.warn(String.format("ConsentTemplate with code [%s] and version [%s] was not found", consentTemplateId, version), e);
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
                    "text.account.consent.template.notFound", null);
        }
        catch (final CommerceConsentGivenException e)
        {
            LOG.warn(String.format("ConsentTemplate with code [%s] and version [%s] already has a given consent", consentTemplateId,
                    version), e);
            GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, "text.account.consent.already.given",
                    null);
        }

        customerConsentDataStrategy.populateCustomerConsentDataInSession();

        return REDIRECT_TO_CONSENT;
    }

    @RequestMapping(value = "/withdrawConsent", method = RequestMethod.POST)
    public String withdrawConsentTest(final ConsentForm form,  final RedirectAttributes redirectModel) {

        // WITHDRAW w/facade
        // String consentCode = form.getConsentCode();
        // consentFacade.withdrawConsent(consentCode);
        // GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, "text.account.consent.withdrawn");

        try {
            CustomerModel customerModel = sessionService.getAttribute("user");
            List<ConsentTemplateModel> consentTemplateModelList = consentService.getConsentTemplates(baseSiteService.getCurrentBaseSite());
            List<ConsentTemplateModel> modelList = consentTemplateModelList.stream().filter(model -> model.getId().equals(form.getConsentTemplateId())).collect(Collectors.toList());

            if (modelList.size() > 1) {
                throw new IllegalStateException();
            }

            ConsentTemplateModel templateModel = modelList.get(0);

            ConsentModel consentModel = consentService.getActiveConsent(customerModel, templateModel);

            // WITHDRAW w/service
            consentService.withdrawConsent(consentModel);

        } catch (Exception e) {
            LOG.error(e);
        }

        return REDIRECT_TO_CONSENT;
    }
}
