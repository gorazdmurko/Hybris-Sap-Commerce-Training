package de.hybris.platform.testingbackoffice.actions;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.hybris.cockpitng.engine.impl.AbstractComponentWidgetAdapterAware;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.jalo.user.Customer;
import org.apache.commons.lang.StringEscapeUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import java.util.Collections;
import java.util.List;

/*
    public interface CockpitAction<I, O> {
        ActionResult<O> perform(ActionContext<I> var1);
* */                                                                          //         I - CONTEXT, O - RESULT
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

            // TODO - not working yet
//            String xml = " <?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                    "<note>\n" +
//                    "  <to>Tove</to>\n" +
//                    "  <from>Jani</from>\n" +
//                    "  <heading>Reminder</heading>\n" +
//                    "  <body>Don't forget me this weekend!</body>\n" +
//                    "</note> ";
//
//            // Escape the XML content in preparation for the ZUL file
//            xml = StringEscapeUtils.escapeHtml(xml);
//
//            String template = "/widgets/actions/popup/popup-preview.zul";
//            Window window = (Window) Executions.createComponents(template, null, Collections.singletonMap("xml", xml));
//            window.doModal();

            Messagebox.show("Action successfully completed", "Confirmation popup", Messagebox.OK, Messagebox.EXCLAMATION);

            return new ActionResult("success");
        }

        return new ActionResult("error");
    }

    @Override
    public boolean canPerform(ActionContext<Object> ctx) {

        boolean canPerform;

        System.out.println("\n\ncanPerform() method of PopupAction class invoked");
        System.out.println("POPUP DATA: " + ctx.getData());
        System.out.println("Class: " + ctx.getData().getClass());   // String

        Customer customer;

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

        // return true;
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
}
