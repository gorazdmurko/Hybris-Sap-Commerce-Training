package de.hybris.platform.testingbackoffice.actions;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.hybris.cockpitng.engine.impl.AbstractComponentWidgetAdapterAware;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// I - CONTEXT
// O - RESULT                                                                                   I      O
public class PopupAction extends AbstractComponentWidgetAdapterAware implements CockpitAction<Object, List> {

    protected static final String SOCKET_OUT_SELECTED_OBJECTS = "currentObject";

    @Override
    public ActionResult<List> perform(ActionContext<Object> actionContext) {

        System.out.println("\n\nperform() method of class PopupAction invoked");
        System.out.println("POPUP CONTEXT DATA: " + actionContext.getData());                   // Product or AbstractOrderModel
        System.out.println("POPUP CONTEXT DATA CLASS: " + actionContext.getData().getClass());
        System.out.println("ActionContext object: " + actionContext.getClass());
        System.out.println("ActionContext params: " + actionContext.getParameters());

        if(actionContext.getData() != null) {

            this.sendOutput(SOCKET_OUT_SELECTED_OBJECTS, actionContext.getData());

            Messagebox.show("Action successfully completed", "Confirmation popup", Messagebox.OK, Messagebox.EXCLAMATION);

            return new ActionResult("success");
        }

        callMessageBox(actionContext);

        return new ActionResult("error");
    }

    @Override
    public boolean canPerform(ActionContext<Object> ctx) {

        boolean canPerform;

        System.out.println("\n\ncanPerform() method of PopupAction class invoked");
        System.out.println("POPUP DATA: " + ctx.getData());
        System.out.println("Class: " + ctx.getData().getClass());   // String

        if (ctx.getData() instanceof String) {
            System.out.println("Instance of String");
            canPerform = true;
        } else if (ctx.getData() instanceof AbstractOrderModel) {
            System.out.println("Instance of AbstractOrderModel");
            canPerform = true;
        } else {
            canPerform = false;
        }

        System.out.println("Boolean value of PopupAction.class = " + canPerform);

        return canPerform;
    }

    @Override
    public boolean needsConfirmation(ActionContext<Object> ctx) {

        System.out.println("PopupAction.class needsConfirmation: " + true);
        return true;
    }

    @Override
    public String getConfirmationMessage(ActionContext<Object> ctx) {

        String message = ctx.getLabel("confirmation.message");

        System.out.println("PopupAction getConfirmationMessage() invoked!");
        System.out.println("Context: " + ctx);
        System.out.println("Confirmation message: " + message);

        if (message == null) {
            message = "PopupAction message confirmed!";
            return message;
        }

        return message;
    }

    private void callMessageBox(ActionContext<Object> actionContext) {
        Map params = new HashMap();
        params.put("sclass", "myMessagebox");
        params.put("width", 500);
        Messagebox.show("It's a customized style message box.",
                null, null, null, Messagebox.INFORMATION, null,
                new EventListener() {
                    public void onEvent(Event e) {
                        if (Messagebox.ON_OK.equals(e.getName())){
                            // OK is clicked
                            sendOutput(SOCKET_OUT_SELECTED_OBJECTS, actionContext.getData());
                            System.out.println("OK button was clicked");
                            System.out.println("Event name: " + e.getName());   // onOK
                        } else if(Messagebox.ON_CANCEL.equals(e.getName())){
                            // Cancel is clicked
                            System.out.println("CANCEL button was clicked");
                            System.out.println("Event name: " + e.getName());   // onCancel
                        } else {
                            // Ignore is clicked
                            System.out.println("IGNORE button was clicked");    // onIgnore
                            System.out.println("Event name: " + e.getName());
                        }
                    }
                }, params
        );
    }
}
