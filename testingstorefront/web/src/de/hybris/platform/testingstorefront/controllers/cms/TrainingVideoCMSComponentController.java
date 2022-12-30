package de.hybris.platform.testingstorefront.controllers.cms;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.cms.AbstractCMSComponentController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.testingcore.model.components.TrainingVideoComponentModel;
import de.hybris.platform.testingstorefront.controllers.ControllerConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Scope("tenant")
@Controller("TrainingVideoComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.TrainingVideoCmsComponent)
public class TrainingVideoCMSComponentController extends AbstractCMSComponentController<TrainingVideoComponentModel> {

    protected final String ACTIONS = "actions";
    protected final String COMPONENT = "component";
    protected final String COMPONENT_UID = "componentUid";
    protected final String PARENT_COMPONENT = "parentComponent";
    protected static final Logger LOG = Logger.getLogger(TrainingVideoCMSComponentController.class);

    @Override
    protected void fillModel(HttpServletRequest request, Model model, TrainingVideoComponentModel component) {

        model.addAttribute("autoPlay", component.getAutoPlay() ? 1 : 0);
        model.addAttribute("showControls", component.getShowControls() ? 1 : 0);
        model.addAttribute("height", component.getHeight());
        model.addAttribute("width", component.getWidth());
        model.addAttribute("videoId", component.getVideoId());

        String srcUrl = "https://www.youtube.com/embed/";
        StringBuilder urlBuilder = new StringBuilder(srcUrl);
        urlBuilder.append(component.getVideoId()).append("?autoplay=").append(component.getAutoPlay() ? 1 : 0);
        urlBuilder.append("&controls=").append(component.getShowControls() ? 1 : 0);

        model.addAttribute("videoUrl", String.valueOf(urlBuilder));

        // add attributes for custom action
        model.addAttribute(ACTIONS, component.getActions());
        model.addAttribute("action", component.getActions().get(0));
        model.addAttribute(COMPONENT, component);
        model.addAttribute(PARENT_COMPONENT, component);
    }

    @Override
    protected String getView(TrainingVideoComponentModel component) {

        LOG.info(ControllerConstants.Views.Cms.ComponentPrefix + StringUtils.lowerCase(getTypeCode(component)));

        return ControllerConstants.Views.Cms.ComponentPrefix + StringUtils.lowerCase(getTypeCode(component));   // return cms/trainingvideocomponent.jsp
    }
}


// fillModel() & getView() are called from handleComponent() method of AbstractCMSComponentController.java