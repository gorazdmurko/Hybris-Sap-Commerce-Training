package de.hybris.platform.testingbackoffice.services;

import com.hybris.backoffice.widgets.notificationarea.NotificationService;
import com.hybris.backoffice.widgets.notificationarea.event.NotificationEvent;
import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.engine.WidgetInstanceManager;

public class CustomNotificationServices implements NotificationService {

    @Override
    public void notifyUser(String s, String s1, NotificationEvent.Level level, Object... objects) {
        System.out.println("notifyUser() method invoked");
        System.out.println("\ns: " + s + "\ns1: " + s1 + "\nlevel: " + level + "\nobjects: " + objects);
    }


    @Override
    public void clearNotifications(String s) {
        System.out.println("clearNotifications() method invoked");
    }

    @Override
    public void clearNotifications(String s, NotificationEvent.Level level) {
        System.out.println("clearNotifications() method invoked");
    }

    @Override
    public String getWidgetNotificationSource(WidgetInstanceManager widgetInstanceManager) {
        return null;
    }

    @Override
    public void notifyUser(WidgetInstanceManager widgetInstanceManager, String eventType, NotificationEvent.Level level, Object... referenceObjects) {
        NotificationService.super.notifyUser(widgetInstanceManager, eventType, level, referenceObjects);
    }

    @Override
    public String getWidgetNotificationSource(ActionContext<?> actionContext) {
        return null;
    }

    @Override
    public void notifyUser(ActionContext<?> context, String eventType, NotificationEvent.Level level, Object... referenceObjects) {
        NotificationService.super.notifyUser(context, eventType, level, referenceObjects);
    }
}
