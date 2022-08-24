package de.hybris.platform.testingbackoffice.widgets;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.hybris.cockpitng.engine.impl.AbstractComponentWidgetAdapterAware;
import de.hybris.platform.servicelayer.type.TypeService;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

public class FileDataImport extends AbstractComponentWidgetAdapterAware implements CockpitAction<String, String> {

    protected static final String SOCKET_OUT_TYPE_CODE = "typeCode";
    @Resource
    private TypeService typeService;

    public FileDataImport() {
    }

    public ActionResult<String> perform(ActionContext<String> ctx) {
        ActionResult<String> result = new ActionResult("error");
        if (ctx != null && StringUtils.isNotEmpty((CharSequence)ctx.getData())) {
            this.sendOutput("typeCode", ctx.getData());
            result.setData((String)ctx.getData());
            result.setResultCode("success");
        }

        return result;
    }

    public boolean canPerform(ActionContext<String> ctx) {
        return StringUtils.isNotEmpty((CharSequence)ctx.getData()) && this.typeService.isAssignableFrom("Item", (String)ctx.getData());
    }

    public TypeService getTypeService() {
        return this.typeService;
    }
}
