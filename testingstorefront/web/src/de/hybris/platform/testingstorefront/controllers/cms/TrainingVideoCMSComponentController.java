package de.hybris.platform.testingstorefront.controllers.cms;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.cms.AbstractCMSComponentController;
import de.hybris.platform.testingcore.model.components.TrainingVideoComponentModel;
import de.hybris.platform.testingstorefront.controllers.ControllerConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller("TrainingVideoCMSComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.TrainingVideoCmsComponent)
public class TrainingVideoCMSComponentController extends AbstractCMSComponentController<TrainingVideoComponentModel> {

    @Override
    protected void fillModel(HttpServletRequest request, Model model, TrainingVideoComponentModel component) {
        model.addAttribute("autoPlay", component.getAutoPlay() ? 1 : 0);
        model.addAttribute("showControls", component.getShowControls() ? 1 : 0);
        model.addAttribute("height", component.getHeight());
        model.addAttribute("width", component.getWidth());
        model.addAttribute("videoId", component.getVideoId());
    }

    @Override
    protected String getView(TrainingVideoComponentModel component) {
        return null;
    }
}
