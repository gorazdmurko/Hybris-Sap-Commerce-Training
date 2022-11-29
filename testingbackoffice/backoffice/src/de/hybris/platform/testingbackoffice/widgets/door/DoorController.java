package de.hybris.platform.testingbackoffice.widgets.door;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;

import com.hybris.cockpitng.annotations.ViewEvent;
import com.hybris.cockpitng.util.DefaultWidgetController;


public class DoorController extends DefaultWidgetController
{
    private static final long serialVersionUID = 1L;


    @Override
    public void initialize(final Component comp)
    {
        super.initialize(comp);

    }


    @ViewEvent(componentID = "closeBtn", eventName = Events.ON_CLICK)
    public void close()
    {
        System.out.println("close");
    }

    @ViewEvent(componentID = "openBtn", eventName = Events.ON_CLICK)
    public void open()
    {
        System.out.println("open");
    }
}
