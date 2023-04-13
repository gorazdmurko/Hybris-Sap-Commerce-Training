package de.hybris.platform.testingbackoffice.widgets.mySearch;

import java.util.List;

import de.hybris.platform.testingbackoffice.widgets.MySearchService;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.hybris.cockpitng.annotations.ViewEvent;

import com.hybris.cockpitng.util.DefaultWidgetController;

public class MySearchController extends DefaultWidgetController {

    private Textbox searchInput;

    @WireVariable
    private MySearchService mySearchService;


    // add message to list and show it as a popup
    @ViewEvent(componentID = "searchBtn", eventName = Events.ON_CLICK)
    public void doSearch() throws InterruptedException
    {
        List<String> result = mySearchService.search(searchInput.getText());
        System.out.println("Result: " + result.get(0));
        Messagebox.show(result.get(0));
    }

    // log to the console
    @ViewEvent(componentID = "searchInput", eventName = Events.ON_CHANGING)
    public void doLog() throws InterruptedException
    {
        String result = searchInput.getText();
        System.out.println("Result: " + result);
    }
}
