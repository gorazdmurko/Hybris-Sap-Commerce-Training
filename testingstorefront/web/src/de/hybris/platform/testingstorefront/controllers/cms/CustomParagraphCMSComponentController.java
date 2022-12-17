package de.hybris.platform.testingstorefront.controllers.cms;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.cms.AbstractCMSComponentController;
import de.hybris.platform.testingcore.model.components.CustomParagraphComponentModel;
import de.hybris.platform.testingstorefront.controllers.ControllerConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller("CustomParagraphCMSComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.CustomParagraphCMSComponent)
public class CustomParagraphCMSComponentController extends AbstractCMSComponentController<CustomParagraphComponentModel> {

    protected static final Logger LOG = Logger.getLogger(CustomParagraphCMSComponentController.class);

    @Override
    protected void fillModel(HttpServletRequest request, Model model, CustomParagraphComponentModel component) {
        LOG.info("fillModel() from CustomParagraphCMSComponentController was invoked.");

        model.addAttribute("headerSection", component.getHeaderSection());
        model.addAttribute("content", component.getContent());
        model.addAttribute("footerSection", component.getFooterSection());

        model.addAttribute("name1", "G.");
        model.addAttribute("name2", "M.");
        model.addAttribute("age", 39);

        System.out.println("fillModel() from CustomParagraphCMSComponentController was invoked.");
    }

    @Override
    protected String getView(CustomParagraphComponentModel component) {
        LOG.info("getView() from CustomParagraphCMSComponentController was invoked.");
        LOG.info(ControllerConstants.Views.Cms.ComponentPrefix + StringUtils.lowerCase(getTypeCode(component)));

        System.out.println("getView() from CustomParagraphCMSComponentController was invoked.");
        System.out.println(ControllerConstants.Views.Cms.ComponentPrefix + StringUtils.lowerCase(getTypeCode(component)));

        return null;
        // return ControllerConstants.Views.Cms.ComponentPrefix + StringUtils.lowerCase(getTypeCode(component));
    }
}
