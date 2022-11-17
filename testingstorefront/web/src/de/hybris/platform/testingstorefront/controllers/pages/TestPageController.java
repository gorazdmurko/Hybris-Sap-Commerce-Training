package de.hybris.platform.testingstorefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class TestPageController extends AbstractPageController {

    private static final String TEST_CMS_PAGE = "testCMSPage";  // inserted w/impex in 3. step

    @RequestMapping(method = RequestMethod.GET)
    public String getTest(final Model model) throws CMSItemNotFoundException {

        final ContentPageModel testCMSPage = getContentPageForLabelOrId(TEST_CMS_PAGE);
        // 1. model, 2. cms page
        storeCmsPageInModel(model, testCMSPage);
        setUpMetaDataForContentPage(model, testCMSPage);

        return getViewForPage(model);
    }
}
