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
import org.apache.log4j.Logger;
import org.zkoss.zul.Messagebox;
import javax.annotation.Resource;
import java.util.*;


public class ExportAction extends AbstractComponentWidgetAdapterAware implements CockpitAction<String, Pageable<? extends ItemModel>> {

    @Resource
    private BackofficeTypeUtils backofficeTypeUtils;
    @Resource
    private TypeService typeService;
    @Resource
    private PermissionFacade permissionFacade;

    private Pageable<? extends ItemModel> EXPORT_DATA;
    private final String SOCKET_OUT_TYPE_CODE = "itemsToExport";
    private static final Logger LOG = Logger.getLogger(ExportAction.class);

    @Override
    public ActionResult<Pageable<? extends ItemModel>> perform(ActionContext<String> ctx) {

        ActionResult<Pageable<? extends ItemModel>> result = new ActionResult("error");

        this.getData(ctx).ifPresent((exportData) -> {

            this.EXPORT_DATA = exportData;

            int totalCount = EXPORT_DATA.getTotalCount();


            this.logCtxData(EXPORT_DATA);   // ??


            EXPORT_DATA.refresh();

            if (this.isMaxRowsExceeded(ctx, totalCount)) {
                this.showMaxRowsExceeded(ctx, totalCount);
            } else {
                this.sendOutput("itemsToExport", EXPORT_DATA);
                result.setResultCode("success");
                result.setData(EXPORT_DATA);
            }

            this.sendOutput("itemsToExport", EXPORT_DATA);
            result.setResultCode("success");
            result.setData(EXPORT_DATA);


            // TEST (it works) - delete after
            this.testLog(EXPORT_DATA);
        });

        this.finalLogWithMessageBox(ctx, result);

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



    private void logCtxData(Pageable<? extends ItemModel> exportData) {
        System.out.println("\nEXPORT DATA: " + exportData);
        System.out.println("ALL RESULTS: " + exportData.getAllResults());
        System.out.println("\nTOTAL COUNT: " + exportData.getTotalCount());
        System.out.println("ALL RESULTS LENGTH: " + exportData.getAllResults().size());
        System.out.println("\nCURRENT PAGE: " + exportData.getCurrentPage());
        System.out.println("SORT DATA: " + exportData.getSortData());
        System.out.println("PAGE NUMBER: " + exportData.getPageNumber());
        System.out.println("PAGE SIZE: " + exportData.getPageSize());
        System.out.println("TYPECODE: " + exportData.getTypeCode());
        System.out.println("QUERY ID: " + exportData.getQueryId());
        System.out.println("CLASS: " + exportData.getClass());

        System.out.println("\nHas next page: " + exportData.hasNextPage());
        System.out.println("Next page: " + exportData.nextPage());
        System.out.println("\nHas previous page: " + exportData.hasPreviousPage());
        System.out.println("Previous page: " + exportData.previousPage());

        System.out.println("\n\n");
        for (ItemModel data: exportData.getAllResults()) {
            System.out.println(data.getItemtype());
            System.out.println(data);
            System.out.println(data.getPk());
            System.out.println("\n");
        }
    }
    private void testLog(Pageable<? extends ItemModel> exportData) {
        System.out.println("\n\nLIST");
        List arrayList = new ArrayList();
        arrayList = exportData.getAllResults();
        System.out.println(arrayList.get(0));
        // UnmodifiableList list = (UnmodifiableList) exportData.getAllResults();   // java.util.Collections$UnmodifiableList
        System.out.println("\n\nLINKED HASH SET");
        LinkedHashSet<ItemModel> linkedHashSet = new LinkedHashSet<>(arrayList);
        for (ItemModel row : linkedHashSet) {
            System.out.println(row);
            break;  // to break after first iteration
        }
    }
    private void finalLogWithMessageBox(ActionContext<String> ctx, ActionResult<Pageable<? extends ItemModel>> result) {
        System.out.println("\n\nPage Export action invoked");
        System.out.println("Context: " + ctx);
        System.out.println("Context class: " + ctx.getClass());
        System.out.println("Context params: " + ctx.getParameters());
        System.out.println("EXPORT CONTEXT DATA: " + ctx.getData());
        // System.out.println("Context data class: " + ctx.getData().getClass());

        Messagebox.show(result.getData() + " (" + result.getResultCode() + ")");

        System.out.println("\nRESULT: " + result);
        System.out.println("RESULT CODE: " + result.getResultCode());
        System.out.println("RESULT DATA: " + result.getData());
        System.out.println("RESULT DATA RESULTS: " + result.getData().getAllResults());
        System.out.println("RESULT MESSAGE: " + result.getResultMessage());
        System.out.println("RESULT OUTPUT: " + result.getOutputsToSend());
        System.out.println("RESULT STATUS FLAG: " + result.getStatusFlags());
        System.out.println("RESULT SOCKET: " + result.getSocketAfterOperation());
        System.out.println("RESULT CLASS: " + result.getClass());
    }
}
