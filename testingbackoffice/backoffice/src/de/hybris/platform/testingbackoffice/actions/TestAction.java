package de.hybris.platform.testingbackoffice.actions;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.hybris.cockpitng.engine.impl.AbstractComponentWidgetAdapterAware;
import org.zkoss.zul.Messagebox;

public class TestAction  extends AbstractComponentWidgetAdapterAware implements CockpitAction<String, String> {

    protected static final String SOCKET_OUT_SELECTED_OBJECTS = "currentObject";

    @Override
    public ActionResult<String> perform(ActionContext<String> actionContext) {

        System.out.println("\n\nperform() method of class TestAction invoked");
        System.out.println("TEST CONTEXT DATA: " + actionContext.getData());                   // Product or AbstractOrderModel
        System.out.println("TEST CONTEXT DATA CLASS: " + actionContext.getData().getClass());
        System.out.println("TEST ActionContext object: " + actionContext.getClass());
        System.out.println("TEST ActionContext params: " + actionContext.getParameters());

        if(actionContext.getData() != null) {
            this.sendOutput(SOCKET_OUT_SELECTED_OBJECTS, actionContext.getData());
            Messagebox.show("Test action successfully invoked", "Confirmation message", Messagebox.OK, Messagebox.EXCLAMATION);
            return new ActionResult("success");
        }

        Messagebox.show("Test action can't be performed!", "Invocation error", Messagebox.OK, Messagebox.ERROR);
        return new ActionResult("error");
    }

    @Override
    public boolean canPerform(ActionContext<String> ctx) {
//        return CockpitAction.super.canPerform(ctx);
        return true;
    }

    @Override
    public boolean needsConfirmation(ActionContext<String> ctx) {
//        return CockpitAction.super.needsConfirmation(ctx);
        return true;
    }

    @Override
    public String getConfirmationMessage(ActionContext<String> ctx) {
//        return CockpitAction.super.getConfirmationMessage(ctx);
        String message = ctx.getLabel("confirmation.message");

        System.out.println("TestAction getConfirmationMessage() invoked!");
        System.out.println("Context: " + ctx);
        System.out.println("Confirmation message: " + message);

        if (message == null) {
            message = "TestAction message confirmed!";
            return message;
        }

        return message;
    }
}
