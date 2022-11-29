package de.hybris.platform.testingbackoffice.actions;

import de.hybris.platform.core.model.product.ProductModel;
import org.apache.commons.lang3.StringUtils;
import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.hybris.cockpitng.engine.impl.AbstractComponentWidgetAdapterAware;
//import com.hybris.cockpitng.Door;


public class LockAction extends AbstractComponentWidgetAdapterAware implements CockpitAction<Object, Object>
{

    public static final String LOCK_SOCKET_KEY = "lockSocket";

    @Override
    public ActionResult<Object> perform(final ActionContext<Object> ctx)
    {
//        final Door door = (Door) ctx.getData();

        final ProductModel product = (ProductModel) ctx.getData();

        //do some logic here

//        final ActionResult<Object> result = new ActionResult<>(ActionResult.SUCCESS, door);
        final ActionResult<Object> result = new ActionResult<>(ActionResult.SUCCESS, product);

        final Object outSocket = ctx.getParameter(LOCK_SOCKET_KEY);
        if (outSocket instanceof String && StringUtils.isNotBlank((CharSequence) outSocket))
        {
//            result.addOutputSocketToSend((String) outSocket, door);
            result.addOutputSocketToSend((String) outSocket, product);
        }

        return result;
    }

    @Override
    public boolean canPerform(final ActionContext<Object> ctx)
    {
        return true;
    }
}
