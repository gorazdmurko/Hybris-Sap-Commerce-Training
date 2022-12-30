package de.hybris.platform.testingstorefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.testingstorefront.controllers.forms.TestingEmailForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/custom")
public class CustomPageController extends AbstractPageController {

    private static final String REDIRECT_PREFIX = "redirect:";
    private static final String CUSTOM_CMS_PAGE = "customCMSPage";      // inserted w/impex in 3. step !!

    @RequestMapping(method = RequestMethod.GET)
    public String getCustomPage(final Model model) throws CMSItemNotFoundException {

        final ContentPageModel customCMSPage = getContentPageForLabelOrId(CUSTOM_CMS_PAGE);

        storeCmsPageInModel(model, customCMSPage);
        setUpMetaDataForContentPage(model, customCMSPage);

        model.addAttribute(new TestingEmailForm());

        return getViewForPage(model);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        System.out.println("GET Test works!");

        return REDIRECT_PREFIX + "/custom";
    }

    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    public String postVideoReview(final TestingEmailForm form, final Model model, final BindingResult result,
                        final HttpServletRequest request, final RedirectAttributes redirectAttrs) throws CMSItemNotFoundException {

        System.out.println("POST Test works!");

        if (result.hasErrors()) {
            final ContentPageModel customCMSPage = getContentPageForLabelOrId(CUSTOM_CMS_PAGE);

            GlobalMessages.addErrorMessage(model, "submit.general.error.title");
            model.addAttribute("EmailForm", form);

            storeCmsPageInModel(model, customCMSPage);
            return  getViewForPage(model);
        }

        GlobalMessages.addFlashMessage(redirectAttrs, GlobalMessages.CONF_MESSAGES_HOLDER, "submit.confirmation.thank.you.title");

        // pages/custom/customLayoutPage
        return REDIRECT_PREFIX + "/custom";
    }

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public String test(final BindingResult result, final Model model, final HttpServletRequest request, final RedirectAttributes redirectAttrs) {
//        System.out.println("Test works!");
//
//        if (result.hasErrors()) {
//            return getViewForPage(model);
//        }
//
//        return REDIRECT_PREFIX + "pages/custom/customLayoutPage";
//    }
}
