package de.hybris.platform.testingstorefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.util.XSSFilterUtil;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.ReviewData;
import de.hybris.platform.commercefacades.url.impl.DefaultProductDataUrlResolver;
import de.hybris.platform.commerceservices.url.impl.AbstractUrlResolver;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.testingstorefront.url.DefaultPreviewDataModelUrlResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestPageController extends AbstractPageController {

    private static final String PRODUCT_CODE_PATH_VARIABLE_PATTERN = "/{productCode:.*}";
    private static final String REDIRECT_PREFIX = "redirect:";
    private static final String TEST_CMS_PAGE = "testCMSPage";      // inserted w/impex in 3. step !!

    @RequestMapping(method = RequestMethod.GET)
    public String getTest(final Model model) throws CMSItemNotFoundException {

        final ContentPageModel testCMSPage = getContentPageForLabelOrId(TEST_CMS_PAGE);

        // example
        // model.addAttribute(new Object());
        // model.addAttribute("", new Object());

        // 1. model, 2. cms page
        storeCmsPageInModel(model, testCMSPage);
        setUpMetaDataForContentPage(model, testCMSPage);

        return getViewForPage(model);
    }




    // -- T - E - S - T --

    @RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/review", method =
            {RequestMethod.GET, RequestMethod.POST})
    public String postReview(@PathVariable final String productCode, final GGReviewForm form, final BindingResult result,
                             final Model model, final HttpServletRequest request, final RedirectAttributes redirectAttrs)
            throws CMSItemNotFoundException {

        if (result.hasErrors()) {
            model.addAttribute("showReviewForm", Boolean.TRUE);
            model.addAttribute("submitReviewAsGuest", form.isReviewAsGuest());
            model.addAttribute("GGReviewForm", form);

            // storeCmsPageInModel(model, getPageForProduct(productCode));
            return getViewForPage(model);
        }

        ProductModel product = new ProductModel();
        ProductData productData = new ProductData();

        DefaultProductDataUrlResolver previewDataUrlResolver = new DefaultProductDataUrlResolver();     // call AbstractUrlResolver
        String redirect = REDIRECT_PREFIX + previewDataUrlResolver.resolve(productData);

        return redirect;
    }

    private class GGReviewForm {
        private boolean reviewAsGuest;
        private String guestEmail;
        private boolean gdprAgreement;
        private boolean awardGame;

        public boolean isReviewAsGuest() {
            return reviewAsGuest;
        }

        public void setReviewAsGuest(boolean reviewAsGuest) {
            this.reviewAsGuest = reviewAsGuest;
        }

        public String getGuestEmail() {
            return guestEmail;
        }

        public void setGuestEmail(String guestEmail) {
            this.guestEmail = guestEmail;
        }

        public boolean isGdprAgreement() {
            return gdprAgreement;
        }

        public void setGdprAgreement(boolean gdprAgreement) {
            this.gdprAgreement = gdprAgreement;
        }

        public boolean isAwardGame() {
            return awardGame;
        }

        public void setAwardGame(boolean awardGame) {
            this.awardGame = awardGame;
        }
    }
}
