package de.hybris.platform.testingbackoffice.actions;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.hybris.cockpitng.core.model.WidgetModel;
import com.hybris.cockpitng.dataaccess.facades.permissions.PermissionFacade;
import com.hybris.cockpitng.engine.impl.AbstractComponentWidgetAdapterAware;
import com.hybris.cockpitng.search.data.pageable.Pageable;
import com.hybris.cockpitng.search.data.pageable.PageableList;
import com.hybris.cockpitng.util.type.BackofficeTypeUtils;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.util.Config;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.zkoss.zul.Messagebox;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class ExportAction extends AbstractComponentWidgetAdapterAware implements CockpitAction<String, Pageable<? extends ItemModel>> {

    @Resource
    private BackofficeTypeUtils backofficeTypeUtils;
    @Resource
    private TypeService typeService;
    @Resource
    private PermissionFacade permissionFacade;

    @Override
    public ActionResult<Pageable<? extends ItemModel>> perform(ActionContext<String> ctx) {

        ActionResult<Pageable<? extends ItemModel>> result = new ActionResult("error");
        this.getData(ctx).ifPresent((exportData) -> {
            int totalCount = exportData.getTotalCount();

            System.out.println("\nEXPORT DATA: " + exportData + ", total count: " + exportData.getTotalCount());
            System.out.println("ALL RESULTS: " + exportData.getAllResults());
            System.out.println("CURRENT PAGE: " + exportData.getCurrentPage());
            System.out.println("SORT DATA: " + exportData.getSortData());
            System.out.println("PAGE NUMBER: " + exportData.getPageNumber());
            System.out.println("PAGE SIZE: " + exportData.getPageSize());
            System.out.println("TYPECODE: " + exportData.getTypeCode());
            System.out.println("QUERY ID: " + exportData.getQueryId());
            System.out.println("CLASS: " + exportData.getClass());

            if (this.isMaxRowsExceeded(ctx, totalCount)) {
                this.showMaxRowsExceeded(ctx, totalCount);
            } else {
                this.sendOutput("itemsToExport", exportData);
                result.setResultCode("success");
                result.setData(exportData);
            }

            // all bellow 3 lines just for test (remove later!!)
            this.sendOutput("itemsToExport", exportData);
            result.setResultCode("success");
            result.setData(exportData);
        });

        return result;      // wizard message: missing excel export configuration
    }

    @Override
    public boolean canPerform(ActionContext<String> ctx) {
        String typeCode = StringUtils.isNotBlank(ctx.getData()) ? ctx.getData() : this.getSelectedItemsType(ctx).orElse("");
        boolean perform = StringUtils.isNotEmpty(typeCode) && this.typeService.isAssignableFrom("Item", typeCode) && this.permissionFacade.canReadType(typeCode);

        System.out.println("\nExport Action Can Perform: " + perform);
        System.out.println("\nExportAction canPerform() TYPECODE: " + typeCode);

        return true;    // return perform;
    }

    protected void showMaxRowsExceeded(ActionContext<String> ctx, int rows) {
        Messagebox.show(ctx.getLabel("export.max.rows.exceeded.msg", new Integer[]{rows, this.getExportMaxRows(ctx)}), ctx.getLabel("export.max.rows.exceeded.title"), 1, "z-messagebox-icon z-messagebox-exclamation");
    }

    protected boolean isMaxRowsExceeded(ActionContext<String> ctx, int rows) {
        return rows > this.getExportMaxRows(ctx);
    }

    protected int getExportMaxRows(ActionContext<String> ctx) {
        return Config.getInt("backoffice.excel.export.max.rows", 2147483647);
    }

    protected Optional<String> getSelectedItemsType(ActionContext<String> ctx) {
        Collection<Object> selection = this.getSelectedItemsFromModel(ctx);
        return CollectionUtils.isNotEmpty(selection) ? Optional.ofNullable(this.backofficeTypeUtils.findClosestSuperType(new ArrayList(selection))) : Optional.empty();
    }

    public Optional<Pageable<ItemModel>> getData(ActionContext<String> ctx) {
        return Optional.ofNullable(this.getSelectedItems(ctx).orElseGet(() -> {
            return this.getPageable(ctx);
        }));
    }

    protected Pageable<ItemModel> getPageable(ActionContext<String> ctx) {
        WidgetModel widgetModel = this.getWidgetModel(ctx);
        return widgetModel != null ? (Pageable)widgetModel.getValue(this.getPageableModelProperty(ctx), Pageable.class) : null;
    }

    protected String getPageableModelProperty(ActionContext<String> ctx) {
        String pageableModelProperty = (String)ctx.getParameter("pageable");
        return StringUtils.isNotEmpty(pageableModelProperty) ? pageableModelProperty : "pageable";
    }

    protected String getSelectedItemsModelProperty(ActionContext<String> ctx) {
        String selectedItemsModelProperty = (String)ctx.getParameter("selectedObjects");
        return StringUtils.isNotEmpty(selectedItemsModelProperty) ? selectedItemsModelProperty : "selectedObjects";
    }

    protected Optional<Pageable<ItemModel>> getSelectedItems(ActionContext<String> ctx) {
        Collection<Object> selectedObjects = this.getSelectedItemsFromModel(ctx);
        if (CollectionUtils.isNotEmpty(selectedObjects)) {
            String typeCode = this.getBackofficeTypeUtils().findClosestSuperType(new ArrayList(selectedObjects));
            return Optional.of(new PageableList(new ArrayList(selectedObjects), selectedObjects.size(), typeCode));
        } else {
            return Optional.empty();
        }
    }

    protected Collection<Object> getSelectedItemsFromModel(ActionContext<String> ctx) {
        WidgetModel widgetModel = this.getWidgetModel(ctx);
        return (Collection)(widgetModel != null ? (Collection)widgetModel.getValue(this.getSelectedItemsModelProperty(ctx), Collection.class) : Collections.emptyList());
    }

    protected WidgetModel getWidgetModel(ActionContext<String> ctx) {
        return (WidgetModel)ctx.getParameter("parentWidgetModel");
    }

    public BackofficeTypeUtils getBackofficeTypeUtils() {
        return this.backofficeTypeUtils;
    }


//    @Override
//    public boolean needsConfirmation(ActionContext<String> ctx) {
//        return CockpitAction.super.needsConfirmation(ctx);
//    }
//
//    @Override
//    public String getConfirmationMessage(ActionContext<String> ctx) {
//        return CockpitAction.super.getConfirmationMessage(ctx);
//    }
}
