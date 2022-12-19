package de.hybris.platform.testingstorefront.controllers.cms;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.cms.AbstractCMSComponentController;
import de.hybris.platform.testingcore.model.components.CustomParagraphComponentModel;
import de.hybris.platform.testingstorefront.controllers.ControllerConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

//@Scope("tenant")
@Controller("CustomParagraphComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.CustomParagraphCMSComponent)
public class CustomParagraphCMSComponentController extends AbstractCMSComponentController<CustomParagraphComponentModel> {

    protected static final Logger LOG = Logger.getLogger(CustomParagraphCMSComponentController.class);

    @Override
    protected void fillModel(HttpServletRequest request, Model model, CustomParagraphComponentModel component) {

        model.addAttribute("headerSection", component.getHeaderSection());
        model.addAttribute("content", component.getContent());
        model.addAttribute("footerSection", component.getFooterSection());
    }

    @Override
    protected String getView(CustomParagraphComponentModel component) {

        LOG.info(ControllerConstants.Views.Cms.ComponentPrefix + StringUtils.lowerCase(getTypeCode(component)));

        return ControllerConstants.Views.Cms.ComponentPrefix + StringUtils.lowerCase(getTypeCode(component));   // return cms/customparagraphcomponent.jsp
    }
}
