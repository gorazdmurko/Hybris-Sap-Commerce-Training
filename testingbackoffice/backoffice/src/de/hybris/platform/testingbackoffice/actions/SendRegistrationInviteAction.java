package de.hybris.platform.testingbackoffice.actions;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import de.hybris.platform.acceleratorservices.dataexport.googlelocal.model.Product;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.commerceservices.event.ChangeUIDEvent;
import de.hybris.platform.commerceservices.security.SecureToken;
import de.hybris.platform.commerceservices.security.SecureTokenService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;
import org.zkoss.zul.Messagebox;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

/*
    public interface CockpitAction<I, O> {
        ActionResult<O> perform(ActionContext<I> var1);
* */                                               //         I - CONTEXT, O - RESULT
public class SendRegistrationInviteAction implements CockpitAction<Object, Object> {

    // https://waqasaslam.me/2019/02/01/adding-custom-action-button-in-backoffice-of-sap-hybris/

    // One thing to note is that Actions do NOT have access to the Spring application context.
    // Therefore, if we need to inject Spring bean into our action, we can use the @Resource annotation

    @Resource
    SecureTokenService secureToken; // DefaultSecureTokenService

    @Resource(name = "modelService")
    private ModelService modelService;

    @Resource
    private EventService eventService;

    @Resource
    private BaseStoreService baseStoreService;

    @Resource
    private BaseSiteService baseSiteService;

    @Resource
    private CommonI18NService commonI18NService;


    /**
     * functions that change token of Customer who is not able to log in. Check flexible search for more details
     * => select * from {customer} where {uid} = 'wei.liu5@hybris.com' or select * from {customer} where {uid} = 'democustomer'
     * @param actionContext
     * @return
     */
    @Override
    public ActionResult<Object> perform(ActionContext<Object> actionContext) {

        // LOG
        System.out.println("\n\nperform() of SendRegistrationInviteAction class invoked");
        logContextData(actionContext);
        logContextDataDetails(actionContext);

        LinkedHashSet<Object> dataList = (LinkedHashSet<Object>) actionContext.getData();

        if (dataList.size() > 0) {
            if(dataList.iterator().next() instanceof CustomerModel) {
                for (Object o : dataList) {
                    CustomerModel b2BCustomerModel = (CustomerModel) o;

                    // IF LOGIN IS DISABLED, CHANGE TOKEN
                    if(b2BCustomerModel.isLoginDisabled()) {
                        final SecureToken token = new SecureToken(b2BCustomerModel.getUid(), System.currentTimeMillis());
                        final String encryptedToken = secureToken.encryptData(token);

                        System.out.println("Encrypted token: " + encryptedToken);

                        b2BCustomerModel.setToken(encryptedToken);
                        // b2BCustomerModel.setLoginDisabled(false);
                        modelService.save(b2BCustomerModel);

                        // TODO: see AbstractCommerceUserEvent class & classes that are extended from it
                        // eventService.publishEvent(initializeEvent(new ChangeUIDEvent(b2BCustomerModel.getUid(), "new_uid"), b2BCustomerModel));
                        // eventService.publishEvent(initializeEvent(new RegistrationInviteEvent(encryptedToken), b2BCustomerModel));
                    }
                }
                System.out.println("Inner MessageBox");
                // message, title, buttons, icon
                Messagebox.show(actionContext.getLabel("action.send.registration.invite.sent"),
                        actionContext.getLabel("action.send.registration.invite.sent.title"), Messagebox.OK, Messagebox.INFORMATION);

                return new ActionResult<Object>(ActionResult.SUCCESS);
            }
        }

        System.out.println("Outer MessageBox");
        Messagebox.show(dataList + " (" + ActionResult.ERROR + ")",
                actionContext.getLabel("action.send.registration.invite.sent.title"), Messagebox.OK, Messagebox.ERROR);


        System.out.println(new ActionResult<Object>(ActionResult.ERROR).getData());
        return new ActionResult<Object>(ActionResult.ERROR);
    }

    @Override
    public boolean canPerform(ActionContext<Object> ctx) {

        // WORKS ONLY IF LOGIN IS DISABLED

        boolean canPerform;
        boolean couldPerform = false;
        boolean instanceOfCustomerModel = false;

        // LOG
        System.out.println("\n\ncanPerform() of SendRegistrationInviteAction class invoked");
        logContextData(ctx);

        LinkedHashSet<Object> dataList;

        if (ctx.getData() instanceof LinkedHashSet) {
            dataList = (LinkedHashSet<Object>) ctx.getData();
            Iterator<Object> it = dataList.iterator();
            Object currentElement;  // Object currentElement = ctx.getData().iterator().next()

            while (it.hasNext()) {
                currentElement = it.next();
                if (currentElement instanceof CustomerModel) {
                    instanceOfCustomerModel = true;
                    System.out.println("Instance of CustomerModel");
                } else {
                    instanceOfCustomerModel = false;
                    System.out.println("Not instance of CustomerModel");
                }
            }

            for (Object o : dataList) {
                CustomerModel customer = (CustomerModel) o;
                System.out.println("Login disabled: " + customer.isLoginDisabled());
                if (customer.isLoginDisabled()) {
                    couldPerform = true;
                    break;
                }
            }
            canPerform = couldPerform && instanceOfCustomerModel;
            System.out.println("Boolean return value: " + canPerform);

            return canPerform;
        }
        return false;
    }

    @Override
    public boolean needsConfirmation(ActionContext<Object> ctx) {
        return true;
    }

    @Override
    public String getConfirmationMessage(ActionContext<Object> ctx) {
        String confirmationMessage;
        if (ctx.getLabel("action.send.registration.invite.confirm") == null) {
            confirmationMessage = "Send registration invite?";
        } else {
            confirmationMessage = ctx.getLabel("action.send.registration.invite.confirm");
        }
        return confirmationMessage;
    }

    protected AbstractCommerceUserEvent initializeEvent(final AbstractCommerceUserEvent event, final CustomerModel customerModel) {

        event.setBaseStore(baseStoreService.getCurrentBaseStore());
        // Get the (first) site this eay because we are publishing this event from the Backoffice and Backoffice does NOT have a 'Single Current' site it is associated with
        event.setSite(baseSiteService.getAllBaseSites().iterator().next());
        event.setCustomer(customerModel);
        event.setLanguage(commonI18NService.getCurrentLanguage());
        event.setCurrency(commonI18NService.getCurrentCurrency());

        return event;
    }

    private void logContextData(ActionContext<Object> ctx) {
        System.out.println("REGISTRATION INVITE CONTEXT CODE: " + ctx.getCode());
        System.out.println("REGISTRATION INVITE CONTEXT NAME: " + ctx.getName());
        System.out.println("REGISTRATION INVITE CONTEXT DATA: " + ctx.getData());  // [] LinkedHashSet
        System.out.println("REGISTRATION INVITE CONTEXT ICON: " + ctx.getIconUri());
        System.out.println("REGISTRATION INVITE CONTEXT ICON ENABLED: " + ctx.getIconHoverUri());
        System.out.println("REGISTRATION INVITE CONTEXT ICON DISABLED: " + ctx.getIconDisabledUri());
        System.out.println("REGISTRATION INVITE CONTEXT CLASS: " + ctx.getClass());
        System.out.println("REGISTRATION INVITE CONTEXT DEFINITION: " + ctx.getDefinition());
        // System.out.println("REGISTRATION INVITE CONTEXT DATA IS TYPE OF " + ctx.getData().getClass());  // ? error ?
        System.out.println(ctx.getData() instanceof LinkedHashSet);
    }

    private void logContextDataDetails(ActionContext<Object> ctx) {
        System.out.println("ActionContext object: " + ctx.getClass());
        System.out.println("ActionContext params: " + ctx.getParameters());
        System.out.println("Root: " + ctx.getParameter("componentRoot"));
        System.out.println("Resource path" + ctx.getParameter("componentResourcePath"));
        System.out.println("Location path" + ctx.getParameter("__externalLocationPath"));
        System.out.println("UID: " + ctx.getParameter("actionUID"));
        System.out.println("Widget model: " + ctx.getParameter("parentWidgetModel"));
    }
}
