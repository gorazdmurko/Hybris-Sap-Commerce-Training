package de.hybris.platform.testingbackoffice.widgets.customInput;

import com.hybris.cockpitng.annotations.ViewEvent;
import com.hybris.cockpitng.core.util.impl.TypedSettingsMap;
import com.hybris.cockpitng.search.data.pageable.Pageable;
import com.hybris.cockpitng.util.DefaultWidgetController;
import com.hybris.cockpitng.widgets.collectionbrowser.mold.impl.PagingDelegateController;
import com.hybris.cockpitng.widgets.collectionbrowser.mold.impl.TitleDelegateController;
import de.hybris.platform.testingbackoffice.widgets.CustomInputService;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.api.Textbox;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.apache.commons.lang3.StringUtils;
import java.util.Collections;


public class CustomInputController extends DefaultWidgetController {

    @Wire
    private Div pagingContainerTop;
    @Wire
    private Div pagingContainerBottom;
    @WireVariable
    private transient PagingDelegateController pagingDelegateController;
    @WireVariable
    private transient TitleDelegateController titleDelegateController;


    private Textbox textInput;

    @WireVariable
    private CustomInputService customInputService;


    @ViewEvent(componentID = "submitButton", eventName = Events.ON_CLICK)
    public void doOperation() throws InterruptedException {

        final boolean result = customInputService.isNumberOrNot(textInput.getText());
        if (result == true) {
            Messagebox.show("Input value is an integer");
        } else {
            Messagebox.show("Input value is not an integer nor a number");
        }
    }

    @ViewEvent(componentID = "textInput", eventName = Events.ON_CTRL_KEY)
    public void doOperation1() throws InterruptedException {

        final boolean result = customInputService.isNumberOrNot(textInput.getText());
        if (result == true) {
            Messagebox.show("Input value is an integer");
        } else {
            Messagebox.show("Input value is not an integer nor a number");
        }
    }



    // TEST

    public void initialize(Component component) {

        System.out.println("Initialize invoked");

        super.initialize(component);
        this.initializePagingContainer(component);
    }

    protected void initializePagingContainer(Component parent) {
        System.out.println("InitializePagingContainer invoked");

        if (StringUtils.equals("bottom", this.getWidgetSettings().getString("pagingPosition"))) {
            Executions.createComponents("/cockpitng/widgets/collectionBrowser/configurablePaging.zul", this.getPagingContainerBottom(), Collections.emptyMap());
        } else {
            Executions.createComponents("/cockpitng/widgets/collectionBrowser/configurablePaging.zul", this.getPagingContainerTop(), Collections.emptyMap());
        }

        Selectors.wireComponents(parent, this, true);
    }

    protected void initializeTitle() {
        Pageable pageable = this.getPagingDelegateController().getCurrentPageable();
        if (pageable == null) {
            this.getTitleDelegateController().resetTitle();
        } else {
            this.getTitleDelegateController().updateTitle(pageable.getTypeCode(), pageable.getTotalCount());
        }

    }

    public TypedSettingsMap getWidgetSettings() {
        // from super class
        return this.getWidgetInstanceManager().getWidgetSettings();
    }

    public Div getPagingContainerTop() {
        return this.pagingContainerTop;
    }

    public Div getPagingContainerBottom() {
        return this.pagingContainerBottom;
    }

    public PagingDelegateController getPagingDelegateController() {
        return this.pagingDelegateController;
    }

    public TitleDelegateController getTitleDelegateController() {
        return this.titleDelegateController;
    }
}
