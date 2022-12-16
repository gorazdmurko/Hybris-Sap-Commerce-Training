package de.hybris.platform.testingstorefront.controllers.cms;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.cms.AbstractCMSComponentController;
import de.hybris.platform.testingcore.model.components.TrainingVideoComponentModel;
import de.hybris.platform.testingstorefront.controllers.ControllerConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller("TrainingVideoCMSComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.TrainingVideoCmsComponent)
public class TrainingVideoCMSComponentController extends AbstractCMSComponentController<TrainingVideoComponentModel> {

    protected static final Logger LOG = Logger.getLogger(TrainingVideoCMSComponentController.class);

    @Override
    protected void fillModel(HttpServletRequest request, Model model, TrainingVideoComponentModel component) {
        LOG.info("fillModel() from TrainingVideoCMSComponentController was invoked.");

        model.addAttribute("autoPlay", component.getAutoPlay() ? 1 : 0);
        model.addAttribute("showControls", component.getShowControls() ? 1 : 0);
        model.addAttribute("height", component.getHeight());
        model.addAttribute("width", component.getWidth());
        model.addAttribute("videoId", component.getVideoId());

        System.out.println("fillModel() from TrainingVideoCMSComponentController was invoked.");
    }

    @Override
    protected String getView(TrainingVideoComponentModel component) {
        LOG.info("getView() from TrainingVideoCMSComponentController was invoked.");
        LOG.info(ControllerConstants.Views.Cms.ComponentPrefix + StringUtils.lowerCase(getTypeCode(component)));

        System.out.println("getView() from CustomParagraphCMSComponentController was invoked.");
        System.out.println(ControllerConstants.Views.Cms.ComponentPrefix + StringUtils.lowerCase(getTypeCode(component)));

        return null;
    }
}


// fillModel() & getView() are called from handleComponent() method of AbstractCMSComponentController.java