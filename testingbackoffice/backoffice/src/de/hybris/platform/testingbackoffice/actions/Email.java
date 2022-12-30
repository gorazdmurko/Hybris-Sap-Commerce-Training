package de.hybris.platform.testingbackoffice.actions;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.hybris.cockpitng.engine.impl.AbstractComponentWidgetAdapterAware;
import de.hybris.platform.servicelayer.type.TypeService;
import org.apache.commons.lang3.StringUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

public class Email extends AbstractComponentWidgetAdapterAware implements CockpitAction<String, String> {

    @Resource
    private TypeService typeService;

    // TODO - FULLY IMPLEMENT METHODS

    @Override
    public ActionResult<String> perform(ActionContext<String> actionContext) {
        System.out.println("\n\nperform() method of Email class invoked!");
        System.out.println("EMAIL CONTEXT DATA: " + actionContext.getData());       // null
        System.out.println("ActionContext object: " + actionContext.getClass());
        System.out.println("ActionContext params: " + actionContext.getParameters());

        ActionResult<String> result = new ActionResult("error");

        if (actionContext != null && StringUtils.isNotEmpty((CharSequence)actionContext.getData())) {
            this.sendOutput("typeCode", actionContext.getData());     // socket output Id
            result.setData(actionContext.getData());
            result.setResultCode("success");
        } else {
            result = new ActionResult<String>(ActionResult.ERROR);
        }

        // Messagebox examples
        // https://www.javatips.net/api/org.zkoss.zul.messagebox
        // https://www.zkoss.org/wiki/ZK_Component_Reference/Supporting_Classes/Messagebox
        int messageBox = Messagebox.show("Are you sure to test the Messagebox buttons?",
                "Question", Messagebox.OK | Messagebox.IGNORE | Messagebox.CANCEL,
                "Messagebox.QUESTION is an object",
            new EventListener() {
                public void onEvent(Event e) {
                    // TODO: connect events with some functionality!!
                    if (Messagebox.ON_OK.equals(e.getName())){
                        // OK is clicked
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
                // OK = 1, CANCEL = 2
            }
        );

        System.out.println("Message box id: " + messageBox);
        if (messageBox == 1) {
            System.out.println("Process continues!");
        } else {
            System.out.println("Process canceled!");
        }

        System.out.println("EMAIL RESULT CODE: " + result.getResultCode());
        System.out.println("EMAIL RESULT DATA: " + result.getData());
        System.out.println("EMAIL RESULT MESSAGE: " + result.getResultMessage());
        System.out.println("EMAIL RESULT OUTPUT: " + result.getOutputsToSend());
        System.out.println("EMAIL RESULT STATUS FLAG: " + result.getStatusFlags());
        System.out.println("EMAIL RESULT SOCKET: " + result.getSocketAfterOperation());
        System.out.println("EMAIL RESULT CLASS: " + result.getClass());

        return result;
    }

    @Override
    public boolean canPerform(ActionContext<String> ctx) {

        System.out.println("\n\ncanPerform() method of Email class invoked");

        boolean bool = StringUtils.isNotEmpty(ctx.getData()) && this.typeService.isAssignableFrom("Item", ctx.getData());

        System.out.println("Boolean return value of Email.class = " + bool);
        System.out.println("E-mail context data: " + ctx.getData());
        System.out.println("Not empty: " + StringUtils.isNotEmpty(ctx.getData()));

        System.out.println("Assignable from Item: " + this.typeService.isAssignableFrom("Item", ctx.getData()));
        System.out.println("Assignable from User: " + this.typeService.isAssignableFrom("User", ctx.getData()));

        return bool;

        // return CockpitAction.super.canPerform(ctx);  // default - return true
    }

    @Override
    public boolean needsConfirmation(ActionContext<String> ctx) {

        boolean trueValue = !(CockpitAction.super.needsConfirmation(ctx));
        System.out.println("Email.class needsConfirmation: " + trueValue);

        return trueValue;
    }

    @Override
    public String getConfirmationMessage(ActionContext<String> ctx) {

        System.out.println("Email getConfirmationMessage() invoked!");
        System.out.println("Context: " + ctx);
        System.out.println("Confirmation message: " + ctx.getLabel("confirmation.message"));

        String message = "Email message confirmed!";

        return message;

        // return CockpitAction.super.getConfirmationMessage(ctx);
    }
}
