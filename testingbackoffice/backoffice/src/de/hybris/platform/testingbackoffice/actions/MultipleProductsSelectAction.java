package de.hybris.platform.testingbackoffice.actions;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.hybris.cockpitng.engine.impl.AbstractComponentWidgetAdapterAware;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.zkoss.lang.Classes;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MultipleProductsSelectAction extends AbstractComponentWidgetAdapterAware implements CockpitAction<String, String> {

    @Resource
    private TypeService typeService;
    // private UserService userService;

    @Override
    public ActionResult<String> perform(ActionContext<String> ctx) {

        System.out.println("\n\nperform() method of MultipleProductsSelectAction class invoked");
        System.out.println("MULTISELECT CONTEXT DATA: " + ctx.getData());   // Product
        System.out.println("ActionContext object: " + ctx.getClass());
        System.out.println("ActionContext params: " + ctx.getParameters());

        // ORIGINAL CODE
        ActionResult<String> result = new ActionResult("error");
        if (ctx != null && StringUtils.isNotEmpty((CharSequence)ctx.getData())) {
            this.sendOutput("typeCode", ctx.getData());     // socket output Id
            result.setData((String)ctx.getData());
            result.setResultCode("success");
        } else {
            result = new ActionResult<String>(ActionResult.ERROR);
        }

        System.out.println("perform() method of MultipleProductsSelectAction class ended");
        System.out.println("SELECT RESULT CODE: " + result.getResultCode());
        System.out.println("SELECT RESULT DATA: " + result.getData());
        System.out.println("SELECT RESULT MESSAGE: " + result.getResultMessage());
        System.out.println("SELECT RESULT OUTPUT: " + result.getOutputsToSend());
        System.out.println("SELECT RESULT STATUS FLAG: " + result.getStatusFlags());
        System.out.println("SELECT RESULT SOCKET: " + result.getSocketAfterOperation());
        System.out.println("SELECT RESULT CLASS: " + result.getClass());

        return result;
        // data = "Product", resultCode="success", resultMessage=null, statusFlags={RegularEnumSet} size=0,
        // socketOutputs={LinkedHashMap} size=0, socketAfterOperation={ArrayList} size=0
    }

    public boolean canPerform(ActionContext<String> ctx) {

        System.out.println("\n\ncanPerform() method of MultipleProductsSelectAction class invoked");
        System.out.println("MULTISELECT DATA: " + ctx.getData());
        System.out.println("Assignable from Item: " + this.typeService.isAssignableFrom("Item", ctx.getData()));
        System.out.println("Assignable from User: " + this.typeService.isAssignableFrom("Product", ctx.getData()));

        // final UserModel currentUser = userService.getCurrentUser();
        // final boolean isUserMemberOfGroup = this.userService.isMemberOfGroup(currentUser,"group_name");
        // return isUserMemberOfGroup;

        boolean bool = StringUtils.isNotEmpty(ctx.getData()) && this.typeService.isAssignableFrom("Item", ctx.getData());
        System.out.println("Boolean value of MultipleProductsSelectAction.class = " + bool);

        return bool;
    }

    public TypeService getTypeService() {
        return this.typeService;
    }

    protected void alert(String m) {

        Method _alert = null;
        // brought from GenericAutowireComposer
        try {
            if (_alert == null) {
                final Class<?> mboxcls = Classes.forNameByThread("org.zkoss.zul.Messagebox");
                _alert = mboxcls.getMethod("show", new Class[] { String.class });
            }
            _alert.invoke(null, m);
        } catch (InvocationTargetException e) {
            throw UiException.Aide.wrap(e);
        } catch (Exception e) {
        }
    }

    @Override
    public boolean needsConfirmation(ActionContext<String> ctx) {

        boolean bool = !(CockpitAction.super.needsConfirmation(ctx));
        System.out.println("MultipleProductsSelectAction.class needsConfirmation: " + bool);

        return bool;   // if true, returns a popup window ( !! action continues only if confirmed !! )
    }

    @Override
    public String getConfirmationMessage(ActionContext<String> ctx) {

        System.out.println("MultipleProductsSelectAction getConfirmationMessage() invoked!");
        System.out.println("Context: " + ctx);
        System.out.println("Confirmation message: " + ctx.getLabel("confirmation.message"));

        String message = "MultipleProductsSelectAction message confirmed!";

        return message;     // this message appears in popup window

        // return CockpitAction.super.getConfirmationMessage(ctx);
    }
}