package de.hybris.platform.testingbackoffice.actions;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.commerceservices.security.SecureToken;
import de.hybris.platform.commerceservices.security.SecureTokenService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;
import org.zkoss.zul.Messagebox;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.LinkedHashSet;

// CONTEXT, RESULT
public class ResendRegistrationEmail implements CockpitAction<Object, Object> {

    @Resource(name = "modelService")
    private ModelService modelService;

    @Resource
    SecureTokenService secureToken; // DefaultSecureTokenService

    @Resource
    private EventService eventService;

    @Resource
    private BaseStoreService baseStoreService;

    @Resource
    private BaseSiteService baseSiteService;

    @Resource
    private CommonI18NService commonI18NService;

    @Override                                       // CONTEXT
    public ActionResult<Object> perform(ActionContext<Object> actionContext) {

        System.out.println("\n\nperform() of SendRegistrationInviteAction class invoked");
        logContextData(actionContext);
        logContextDataDetails(actionContext);

        CustomerModel customerModel = (CustomerModel) actionContext.getData();

        // customerModel.getSessionLanguage();     // session currency
        // customerModel.getSessionCurrency();     // session language

        if (customerModel.isLoginDisabled()) {
            final SecureToken token = new SecureToken(customerModel.getUid(), System.currentTimeMillis());
            final String encryptedToken = secureToken.encryptData(token);

            System.out.println("Encrypted token: " + encryptedToken);

            customerModel.setToken(encryptedToken);
            System.out.println("Customer token: " + customerModel.getToken());
            // b2BCustomerModel.setLoginDisabled(false);

            modelService.save(customerModel);
            // eventService.publishEvent(initializeEvent(new SendRegistrationInvite(encryptedToken), customerModel));

            Messagebox.show(actionContext.getLabel("action.send.registration.invite.sent"),
                    actionContext.getLabel("action.send.registration.invite.sent.title"), Messagebox.OK, Messagebox.INFORMATION);

            System.out.println("SUCCESS: " + new ActionResult<Object>(ActionResult.SUCCESS).getData());     // true

            return new ActionResult<Object>(ActionResult.SUCCESS);
        }


        // ERROR
        Messagebox.show(customerModel + " (" + ActionResult.ERROR + ")",
                actionContext.getLabel("action.send.registration.invite.sent.title"), Messagebox.OK, Messagebox.ERROR);

        System.out.println("ERROR" + new ActionResult<Object>(ActionResult.ERROR).getData());       // false

        return new ActionResult<Object>(ActionResult.ERROR);
    }

    @Override
    public boolean canPerform(ActionContext<Object> ctx) {
        // return CockpitAction.super.canPerform(ctx);

        // LOG
        System.out.println("\n\ncanPerform() of SendRegistrationInviteAction class invoked");

        System.out.println("CONTEXT DATA");
        logContextData(ctx);

        System.out.println("CONTEXT DATA DETAILS");
        logContextDataDetails(ctx);

        // WORKS ONLY IF LOGIN IS DISABLED
        boolean canPerform = false;

        if(ctx.getData() instanceof CustomerModel){
            System.out.println("Is CustomerModel");

            CustomerModel customerModel = (CustomerModel) ctx.getData();

            System.out.println("Login disabled: " + customerModel.isLoginDisabled());

            if (customerModel.isLoginDisabled()) {
                canPerform = true;
            } else {
                canPerform = false;
            }

        }
        // TO SPODAJ (else) JE NAROBE !!!!
        else {
            HashSet<Object> dataList = (HashSet<Object>) ctx.getData();
            if (dataList != null) {
                for (Object o : dataList) {
                    CustomerModel b2bCustomer = (CustomerModel) o;
                    if (b2bCustomer.isLoginDisabled()) {
                        canPerform = true;

                        break;
                    }
                }
                return canPerform && dataList.iterator().next() instanceof CustomerModel;
            }
        }

        return canPerform;
    }

    @Override
    public boolean needsConfirmation(ActionContext<Object> ctx) {
        // return CockpitAction.super.needsConfirmation(ctx);

        return true;
    }

    @Override
    public String getConfirmationMessage(ActionContext<Object> ctx) {
        // return CockpitAction.super.getConfirmationMessage(ctx);

        String confirmationMessage = "Send registration invite?";

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
        // select * from {customer} where {name} = 'Wei Liu', select * from {customer} where {pk} = '8796097183748'
        System.out.println("REGISTRATION INVITE CONTEXT DATA: " + ctx.getData());  // CustomerModel
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

