package de.hybris.platform.testingbackoffice.actions;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import de.hybris.platform.servicelayer.type.TypeService;
import org.apache.commons.lang3.StringUtils;
import org.zkoss.zul.Messagebox;

import javax.annotation.Resource;

public class Save implements CockpitAction<String, String> {

    @Resource
    private TypeService typeService;

    // TODO: no sendOutput() action ?!!

    @Override
    public ActionResult<String> perform(final ActionContext<String> ctx) {

        System.out.println("\n\nperform() method of Save class invoked");
        System.out.println("SAVE CONTEXT DATA: " + ctx.getData());          // null
        System.out.println("ActionContext object: " + ctx.getClass());
        System.out.println("ActionContext params: " + ctx.getParameters());

        ActionResult<String> result = null;
        final String data = ctx.getData();
        if (data != null)
        {
            result = new ActionResult<String>(ActionResult.SUCCESS, ctx.getLabel("message", new Object[] { data }));
        }
        else
        {
            result = new ActionResult<String>(ActionResult.ERROR);
        }

        Messagebox.show(result.getData() + " (" + result.getResultCode() + ")");

        System.out.println("SAVE RESULT CODE: " + result.getResultCode());
        System.out.println("SAVE RESULT DATA: " + result.getData());
        System.out.println("SAVE RESULT MESSAGE: " + result.getResultMessage());
        System.out.println("SAVE RESULT OUTPUT: " + result.getOutputsToSend());
        System.out.println("SAVE RESULT STATUS FLAG: " + result.getStatusFlags());
        System.out.println("SAVE RESULT SOCKET: " + result.getSocketAfterOperation());
        System.out.println("SAVE RESULT CLASS: " + result.getClass());

        return result;
    }

    @Override
    public boolean canPerform(final ActionContext<String> ctx) {

        /*
            boolean canPerform = ctx.getData == null ? true : false;
        * */

        System.out.println("\n\ncanPerform() method of Save class invoked");
        System.out.println("SAVE DATA: " + ctx.getData());                  // null
        // System.out.println("SAVE DATA CLASS: " + ctx.getData().getClass()); // null pointer exception?

        boolean canPerform = StringUtils.isNotEmpty(ctx.getData()) && this.typeService.isAssignableFrom("Item", ctx.getData()); // changed from not
        System.out.println("Boolean value of Save.class = " + canPerform);
        System.out.println("Not empty: " + StringUtils.isNotEmpty(ctx.getData()));
        System.out.println("Assignable from Item: " + this.typeService.isAssignableFrom("Item", ctx.getData()));
        System.out.println("Assignable from User: " + this.typeService.isAssignableFrom("User", ctx.getData()));

        // final Object data = ctx.getData();
        // return (data instanceof String) && (!((String) data).isEmpty());

        // return canPerform;
        return true;
    }

    @Override
    public boolean needsConfirmation(final ActionContext<String> ctx)
    {
        System.out.println("Save.class needsConfirmation: " + true);
        return true;
        // return false;
    }

    @Override
    public String getConfirmationMessage(final ActionContext<String> ctx)
    {

        System.out.println("Save getConfirmationMessage() invoked!");
        System.out.println("Context: " + ctx);
        System.out.println("Confirmation message: " + ctx.getLabel("confirmation.message"));

        String confirmationMessage;
        if (ctx.getLabel("confirmation.message") == null) {
            confirmationMessage = "Do you want to confirm?";
        } else {
            confirmationMessage = ctx.getLabel("confirmation.message");
        }

        return confirmationMessage;
    }
}
