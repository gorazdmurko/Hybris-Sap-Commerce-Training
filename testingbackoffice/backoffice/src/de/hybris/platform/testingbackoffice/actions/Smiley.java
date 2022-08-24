package de.hybris.platform.testingbackoffice.actions;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationListener;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

import javax.annotation.Resource;
import java.util.Set;

public class Smiley implements CockpitAction<String, String> {

    @Resource
    private TypeService typeService;
    @Resource
    private UserService userService;
    @Resource
    private UserModel currentUser;

    @Override
    public ActionResult<String> perform(final ActionContext<String> ctx) {

        System.out.println("\n\nperform() method of Smiley class invoked");
        System.out.println("SMILEY CONTEXT DATA: " + ctx.getData());        // null
        System.out.println("ActionContext object: " + ctx.getClass());
        System.out.println("ActionContext params: " + ctx.getParameters());

        // https://www.zkoss.org/wiki/ZK_Component_Reference/Supporting_Classes/Messagebox
        int messageBox = Messagebox.show("Message Box Opened. Perform?",
            "Question", Messagebox.OK | Messagebox.CANCEL,
            Messagebox.QUESTION,
            new EventListener() {
                public void onEvent(Event e) {
                    if (Messagebox.ON_OK.equals(e.getName())){
                        // OK is clicked
                        System.out.println("OK clicked");
                        System.out.println("Event name: " + e.getName());
                    } else if(Messagebox.ON_CANCEL.equals(e.getName())){
                        // Cancel is clicked
                        System.out.println("CANCEL clicked");
                        System.out.println("Event name: " + e.getName());
                    }
                }
                // OK = 1, CANCEL = 2
            }
        );
        System.out.println("Message box response: " + messageBox);

        if (messageBox == 1) {
            System.out.println("Process continued!");
        } else if (messageBox == 2) {
            System.out.println("Process canceled!");
        }

        ActionResult<String> result = null;
        final String data = ctx.getData();

        if (data != null) {
            result = new ActionResult<String>(ActionResult.SUCCESS, ctx.getLabel("message", new Object[] { data }));
        }
        else {
            result = new ActionResult<String>(ActionResult.ERROR);
        }

        System.out.println("SMILEY RESULT CODE: " + result.getResultCode());
        System.out.println("SMILEY RESULT DATA: " + result.getData());
        System.out.println("SMILEY RESULT MESSAGE: " + result.getResultMessage());
        System.out.println("SMILEY RESULT OUTPUT: " + result.getOutputsToSend());
        System.out.println("SMILEY RESULT STATUS FLAG: " + result.getStatusFlags());
        System.out.println("SMILEY RESULT SOCKET: " + result.getSocketAfterOperation());
        System.out.println("SMILEY RESULT CLASS: " + result.getClass());

        return result;
    }

    /*
        *************************************************************************************************************
        *                                                                                                           *
        *   State of the action button is based on com.hybris.cockpitng.actions.CockpitAction#canPerform method     *
        *       - please debug your implementation of that method and make sure it returns true when it should      *
        *                                                                                                           *
        *************************************************************************************************************
    */

    @Override
    public boolean canPerform(final ActionContext<String> ctx) {

        /*
            boolean canPerform = ctx.getData == null ? true : false;
        * */

        System.out.println("\n\ncanPerform() method of Smiley class invoked");
        System.out.println("SMILEY DATA: " + ctx.getData());                    // null
        // System.out.println("SMILEY DATA CLASS: " + ctx.getData().getClass());   // null pointer exception?

        boolean bool = StringUtils.isNotEmpty(ctx.getData()) && this.typeService.isAssignableFrom("Item", ctx.getData()); // changed from not
        System.out.println("Boolean value of Smiley.class = " + bool);
        System.out.println("Not empty: " + StringUtils.isNotEmpty(ctx.getData()));
        System.out.println("Assignable from Item: " + this.typeService.isAssignableFrom("Item", ctx.getData()));
        System.out.println("Assignable from User: " + this.typeService.isAssignableFrom("User", ctx.getData()));

        // final boolean isUserMemberOfGroup = this.userService.isMemberOfGroup(currentUser,customerGroup);

        try {
            currentUser = userService.getCurrentUser();
            System.out.println("Current user: " + currentUser.getName() + ", " + currentUser.getUid());

            Set<PrincipalGroupModel> groups = currentUser.getGroups();
            Set<PrincipalGroupModel> allGroups = currentUser.getAllGroups();
            for (PrincipalGroupModel group : groups) {
                System.out.println("Group name: " + group.getName());
            }
            for (PrincipalGroupModel group : allGroups) {
                System.out.println("AllGroups group name: " + group.getName());
            }
            final boolean notUserAdmin = !this.userService.isAdmin(currentUser);    // ** out of try catch block
            System.out.println("Is " + currentUser.getName() + " admin? " + notUserAdmin);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final boolean notUserAdmin = true;      // **

        return notUserAdmin;
    }

    @Override
    public boolean needsConfirmation(final ActionContext<String> ctx)
    {
        System.out.println("Smiley.class needsConfirmation: " + true);
        return true;
        // return false;
    }

    @Override
    public String getConfirmationMessage(final ActionContext<String> ctx)
    {

        System.out.println("Smiley getConfirmationMessage() invoked!");
        System.out.println("Context: " + ctx);
        System.out.println("Confirmation message: " + ctx.getLabel("confirmation.message"));

        return ctx.getLabel("confirmation.message");
    }
}
