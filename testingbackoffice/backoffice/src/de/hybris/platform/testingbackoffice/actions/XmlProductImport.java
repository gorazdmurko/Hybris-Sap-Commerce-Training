package de.hybris.platform.testingbackoffice.actions;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.hybris.cockpitng.engine.impl.AbstractComponentWidgetAdapterAware;
import de.hybris.platform.servicelayer.type.TypeService;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

public class XmlProductImport extends AbstractComponentWidgetAdapterAware implements CockpitAction<String, String> {

    protected static final String SOCKET_OUT_TYPE_CODE = "typeCode";
    @Resource
    private TypeService typeService;

    public XmlProductImport() {
    }

    public ActionResult<String> perform(ActionContext<String> ctx) {

        System.out.println("\n\nperform() method of XmlProductImport class invoked");
        System.out.println("IMPORT CONTEXT DATA: " + ctx.getData());   // ?
        System.out.println(ctx.getCode());
        System.out.println(ctx.getParameters());
        System.out.println(ctx.getDefinition());
        System.out.println(ctx.getName());
        System.out.println(ctx.getTriggerOnKeys());
        System.out.println(ctx.getClass());

        ActionResult<String> result = new ActionResult("error");

        if (ctx != null && StringUtils.isNotEmpty(ctx.getData())) {
            this.sendOutput("typeCode", ctx.getData());
            result.setData((String)ctx.getData());
            result.setResultCode("success");
        }

        System.out.println("IMPORT RESULT CODE: " + result.getResultCode());
        System.out.println("IMPORT RESULT DATA: " + result.getData());                  // Product
        System.out.println("IMPORT RESULT MESSAGE: " + result.getResultMessage());
        System.out.println("IMPORT RESULT OUTPUT: " + result.getOutputsToSend());
        System.out.println("IMPORT RESULT STATUS FLAG: " + result.getStatusFlags());
        System.out.println("IMPORT RESULT SOCKET: " + result.getSocketAfterOperation());
        System.out.println("IMPORT RESULT CLASS: " + result.getClass());

        return result;
        // data = "Product", resultCode="success", resultMessage=null, statusFlags={RegularEnumSet@37201},
        // socketOutputs={LinkedHashMap@37202}, socketAfterOperation={ArrayList@37203}
    }


    public boolean canPerform(ActionContext<String> ctx) {

        System.out.println("\n\ncanPerform() method of XmlProductImport class invoked");
        System.out.println("IMPORT DATA: " + ctx.getData());
        System.out.println("Assignable from Item: " + this.typeService.isAssignableFrom("Item", ctx.getData()));
        System.out.println("Assignable from User: " + this.typeService.isAssignableFrom("Product", ctx.getData()));

        return StringUtils.isNotEmpty(ctx.getData()) && this.typeService.isAssignableFrom("Item", ctx.getData());
    }

    @Override
    public boolean needsConfirmation(ActionContext<String> ctx) {

        boolean trueValue = !(CockpitAction.super.needsConfirmation(ctx));
        System.out.println("XmlProductImport.class needsConfirmation: " + trueValue);

        return trueValue;
    }

    @Override
    public String getConfirmationMessage(ActionContext<String> ctx) {

        System.out.println("XmlProductImport getConfirmationMessage() invoked!");
        System.out.println("Context: " + ctx);
        System.out.println("Confirmation message: " + ctx.getLabel("confirmation.message"));

        String label = "Do you want to proceed?";

        return label;

        // return CockpitAction.super.getConfirmationMessage(ctx);
    }

    public TypeService getTypeService() {
        return this.typeService;
    }

}
