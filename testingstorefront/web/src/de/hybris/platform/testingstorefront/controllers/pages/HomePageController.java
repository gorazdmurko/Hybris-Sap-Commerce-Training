/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.testingstorefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.ConsentForm;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;

import de.hybris.platform.commercefacades.consent.ConsentFacade;
import de.hybris.platform.commercefacades.consent.data.ConsentTemplateData;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commerceservices.consent.CommerceConsentService;
import de.hybris.platform.commerceservices.consent.dao.ConsentDao;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.commerceservices.event.ConsentGivenEvent;
import de.hybris.platform.commerceservices.model.consent.ConsentModel;
import de.hybris.platform.commerceservices.model.consent.ConsentTemplateModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.testingcore.event.ConsentEmailEvent;
import de.hybris.platform.testingstorefront.controllers.forms.ConsentSubscriptionForm;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Controller for home page
 */
@Controller
@RequestMapping("/")
public class HomePageController extends AbstractPageController
{
	private static final String LOGOUT = "logout";
	private static final String ACCOUNT_CONFIRMATION_SIGNOUT_TITLE = "account.confirmation.signout.title";
	private static final String ACCOUNT_CONFIRMATION_CLOSE_TITLE = "account.confirmation.close.title";

	private static final String CUSTOM_ANONYMOUS_CONSENT_ID = "anonymous.consent.id.";
	private static final String CUSTOM_ANONYMOUS_CONSENT_ID_2 = "anonymous2.consent.id.";
	private static final String CUSTOM_COOKIES_CONSENT_ID = "cookies.consent.id.";
	private static final String REDIRECT_PREFIX = "redirect:";
	private static final String REDIRECT_TO_CONSENT = REDIRECT_PREFIX + "/";
	private static final org.apache.log4j.Logger LOG = Logger.getLogger(CustomPageController.class);

	@Resource(name = "configurationService")
	private ConfigurationService configurationService;

	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;

	@Resource(name = "commerceConsentService")
	private CommerceConsentService consentService;

	@Resource(name = "consentFacade")
	private ConsentFacade consentFacade;

	@Resource(name = "sessionService")
	private SessionService sessionService;

	@Resource(name = "userFacade")
	private UserFacade userFacade;

	@Resource(name = "eventService")
	private EventService eventService;

	private ConsentDao consentDao;


	@RequestMapping(method = RequestMethod.GET)
	public String home(@RequestParam(value = WebConstants.CLOSE_ACCOUNT, defaultValue = "false") final boolean closeAcc,
			@RequestParam(value = LOGOUT, defaultValue = "false") final boolean logout, final Model model,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		if (logout)
		{
			String message = ACCOUNT_CONFIRMATION_SIGNOUT_TITLE;
			if (closeAcc)
			{
				message = ACCOUNT_CONFIRMATION_CLOSE_TITLE;
			}
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.INFO_MESSAGES_HOLDER, message);
			return REDIRECT_PREFIX + ROOT;
		}

		model.addAttribute(new ConsentSubscriptionForm());
		setupConsentData(model);

		storeCmsPageInModel(model, getContentPageForLabelOrId(null));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(null));
		updatePageTitle(model, getContentPageForLabelOrId(null));

		return getViewForPage(model);
	}

	protected void updatePageTitle(final Model model, final AbstractPageModel cmsPage)
	{
		storeContentPageTitleInModel(model, getPageTitleResolver().resolveHomePageTitle(cmsPage.getTitle()));
	}

	private void setupConsentData(Model model) {
		consentDataToModel(model, CUSTOM_ANONYMOUS_CONSENT_ID, "anonymousConsentTemplateData");
		consentDataToModel(model, CUSTOM_ANONYMOUS_CONSENT_ID_2, "anonymousConsentTemplateData");
		consentDataToModel(model, CUSTOM_COOKIES_CONSENT_ID, "cookiesConsentTemplateData");
	}

	protected void consentDataToModel(final Model model, final String id, String modelDataString) {
		final String consentTemplateId = configurationService.getConfiguration()
				.getString(id + baseSiteService.getCurrentBaseSite().getUid());

		if (StringUtils.isNotBlank(consentTemplateId)) {
			final ConsentTemplateData consentData = getConsentFacade().getLatestConsentTemplate(consentTemplateId);
			model.addAttribute(modelDataString, consentData);	// --> consentData.getConsentData().getCode(); == to get consent code
		}
	}


	@RequestMapping(value = "/giveAnonymousConsent", method = RequestMethod.POST)
	public String giveAnonymousConsent(final ConsentSubscriptionForm form, final RedirectAttributes redirectModel) {

		// GIVE CONSENT w/facade -- returns error if consent is already given (else not)
		// consentFacade.giveConsent(form.getConsentTemplateId(), form.getConsentTemplateVersion());
		// GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER, "text.account.consent.template.notFound", null);

		CustomerModel customerModel = sessionService.getAttribute("user");
		ConsentTemplateModel templateModel = new ConsentTemplateModel();

		try {
			List<ConsentTemplateModel> consentTemplateModelList = consentService.getConsentTemplates(baseSiteService.getCurrentBaseSite());
			List<ConsentTemplateModel> modelList = consentTemplateModelList.stream().filter(model -> model.getId().equals(form.getConsentForm().getConsentTemplateId())).collect(Collectors.toList());

			List<ConsentTemplateData> consentTemplateDataList = consentFacade.getConsentTemplatesWithConsents();
			List<ConsentTemplateData> dataList = consentTemplateDataList.stream().filter(template -> template.getId().equals(form.getConsentForm().getConsentTemplateId())).collect(Collectors.toList());

			if (modelList.size() > 1) {
				throw new IllegalStateException();
			}

			if (dataList.size() > 1) {
				throw new IllegalStateException();
			}

			templateModel = modelList.get(0);

			ConsentTemplateData templateData = dataList.get(0);
			LOG.info("Template data: " + templateData);

			// GIVE CONSENT w/service
			consentService.giveConsent(customerModel, templateModel);

		} catch (Exception e) {
			LOG.error(e);
		}

		ConsentModel consent = consentDao.findConsentByCustomerAndConsentTemplate(customerModel, templateModel);

		if (userFacade.isAnonymousUser()) {
			this.eventService.publishEvent(initializeConsentEvent(new ConsentEmailEvent("consents-confirm@hybris.com/site=?electronics"), "consents-confirm@hybris.com/site=?electronics", consent));

			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
					"Thank you for subscribing. You should confirm email that was currently sent to your email box.", null);

			return REDIRECT_TO_CONSENT;
		}

		this.eventService.publishEvent(initializeConsentEvent(new ConsentEmailEvent("consents-confirm@hybris.com/site=?electronics"), "consents-confirm@hybris.com/site=?electronics", consent));

		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.INFO_MESSAGES_HOLDER,
				"Thank you for subscription. You can manage your consents in Consent Manager section of your account.", null);

		return REDIRECT_TO_CONSENT;
	}

	private AbstractCommerceUserEvent initializeConsentEvent(final ConsentEmailEvent event, String confirmationUrl, ConsentModel consent) {
		CustomerModel customerModel = consent.getCustomer();

		event.setConsent(consent);
		event.setToken(customerModel.getToken());
		event.setConsentConfirmationURL(confirmationUrl);


		// see GORENJE initializeConfirmationvent() DefaultGGSubscriberService.java
//		event.setBaseStore(getBaseStoreService().getCurrentBaseStore());
//		BaseSiteModel site = getBaseSiteService().getCurrentBaseSite();
//		if (siteId != null) {
//			site = baseSiteService.getBaseSiteForUID(siteId);
//		}
//		event.setSite(site);
//
//		event.setGgSubscriberModel(subscriber);
//
//		GGBaseSiteSubscriptionModel baseSiteSubscriptionModel = null;
//		if(subscriber.getBaseSiteSubscription()!=null && !subscriber.getBaseSiteSubscription().isEmpty()){
//			for(GGBaseSiteSubscriptionModel sub : subscriber.getBaseSiteSubscription()){
//				if(sub.getBaseSite()!=null && sub.getBaseSite().equals(site)){
//					baseSiteSubscriptionModel = sub;
//					break;
//				}
//			}
//		}
//		event.setToken(baseSiteSubscriptionModel!=null ? baseSiteSubscriptionModel.getValidationToken() : "");
//		event.setLanguage(getCommonI18NService().getCurrentLanguage());
//		event.setCurrency(getCommonI18NService().getCurrentCurrency());
//		return event;

		return event;
	}
}