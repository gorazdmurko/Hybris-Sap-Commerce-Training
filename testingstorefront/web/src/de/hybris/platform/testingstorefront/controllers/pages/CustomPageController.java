package de.hybris.platform.testingstorefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/custom")
public class CustomPageController extends AbstractPageController {

    private static final String CUSTOM_CMS_PAGE = "customCMSPage";      // inserted w/impex in 3. step !!

    @RequestMapping(method = RequestMethod.GET)
    public String getCustomPage(final Model model) throws CMSItemNotFoundException {

        final ContentPageModel customCMSPage = getContentPageForLabelOrId(CUSTOM_CMS_PAGE);

        storeCmsPageInModel(model, customCMSPage);
        setUpMetaDataForContentPage(model, customCMSPage);

        return getViewForPage(model);
    }
}
