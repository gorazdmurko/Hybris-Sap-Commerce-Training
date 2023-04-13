package de.hybris.platform.testingbackoffice.widgets.myInput;

import com.hybris.cockpitng.annotations.ViewEvent;
import com.hybris.cockpitng.util.DefaultWidgetController;
import de.hybris.platform.testingbackoffice.widgets.MyInputService;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.api.Textbox;

import com.hybris.cockpitng.core.util.impl.TypedSettingsMap;
import com.hybris.cockpitng.search.data.pageable.Pageable;
import com.hybris.cockpitng.widgets.collectionbrowser.mold.impl.PagingDelegateController;
import com.hybris.cockpitng.widgets.collectionbrowser.mold.impl.TitleDelegateController;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zk.ui.Component;
import org.apache.commons.lang3.StringUtils;
import java.util.Collections;


public class MyInputController extends DefaultWidgetController {

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
    private MyInputService myInputService;

    @ViewEvent(componentID = "submitButton", eventName = Events.ON_CLICK)
    public void doOperation() throws InterruptedException {

        final boolean r = isNumberOrNot(textInput.getText());
        if (r == true) {
            Messagebox.show("Input value is an integer", "TRUE", Messagebox.OK, Messagebox.EXCLAMATION);
        } else {
            Messagebox.show("Input value is an integer", "FALSE", Messagebox.OK, Messagebox.EXCLAMATION);
        }


        final boolean result = myInputService.isNumberOrNot(textInput.getText());
        if (result == true) {
            Messagebox.show("Input value is an integer");
        } else {
            Messagebox.show("Input value is not an integer nor a number");
        }
    }

    @ViewEvent(componentID = "textInput", eventName = Events.ON_DOUBLE_CLICK)
    public void doOperationOne() throws InterruptedException {

        final boolean result = myInputService.isNumberOrNot(textInput.getText());
        if (result == true) {
            Messagebox.show("Input value is an integer");
        } else {
            Messagebox.show("Input value is not an integer nor a number");
        }
    }

    public boolean isNumberOrNot(final String input) {
        try {
            Integer.parseInt(input);
        } catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }



    // TEST
    public void initialize(Component component) {

        System.out.println("Initialize invoked");

        super.initialize(component);
        this.initializeTitle();
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
