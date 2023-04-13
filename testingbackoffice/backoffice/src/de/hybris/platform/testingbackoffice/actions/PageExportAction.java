package de.hybris.platform.testingbackoffice.actions;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.hybris.cockpitng.core.model.WidgetModel;
import com.hybris.cockpitng.dataaccess.facades.permissions.PermissionFacade;
import com.hybris.cockpitng.engine.impl.AbstractComponentWidgetAdapterAware;
import com.hybris.cockpitng.search.data.pageable.PageableList;
import com.hybris.cockpitng.util.type.BackofficeTypeUtils;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.util.Config;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.zkoss.zul.Messagebox;
import org.apache.commons.collections.CollectionUtils;
import javax.annotation.Resource;
import com.hybris.cockpitng.search.data.pageable.Pageable;
import java.util.*;


// I - CONTEXT
// O - RESULT                                                                                          I      O
public class PageExportAction extends AbstractComponentWidgetAdapterAware implements CockpitAction<String, Pageable<? extends ItemModel>> {

    @Resource
    private TypeService typeService;

    @Resource
    private PermissionFacade permissionFacade;

    @Resource
    private BackofficeTypeUtils backofficeTypeUtils;

    private Pageable<? extends ItemModel> EXPORT_DATA;
    private final String SOCKET_OUT_TYPE_CODE = "itemsToExport";
    private static final Logger LOG = Logger.getLogger(PageExportAction.class);

    @Override
    public ActionResult<Pageable<? extends ItemModel>> perform(final ActionContext<String> ctx) {

        ActionResult<Pageable<? extends ItemModel>> result = new ActionResult<>(ActionResult.ERROR);

        this.getData(ctx).ifPresent((exportData) -> {

            this.EXPORT_DATA = exportData;

            int totalCount = EXPORT_DATA.getTotalCount();

            this.logCtxData(EXPORT_DATA);

            EXPORT_DATA.refresh();  // ?


            // send output with data through socket & set result code
            if (this.isMaxRowsExceeded(ctx, totalCount)) {
                this.showMaxRowsExceeded(ctx, totalCount);
            } else {
                this.sendOutput(SOCKET_OUT_TYPE_CODE, EXPORT_DATA);
                result.setData(EXPORT_DATA);
                result.setResultCode("success");
                result.setResultMessage("success");
            }

            this.sendOutput("itemsToExport", EXPORT_DATA);
            result.setData(EXPORT_DATA);
            result.setResultCode(ActionResult.SUCCESS);
            result.setResultMessage(ActionResult.SUCCESS);


            // TEST (it works) - delete after
            this.testLog(EXPORT_DATA);
        });


        this.finalLogWithMessageBox(ctx, result);

        return result;
    }

    @Override
    public boolean canPerform(final ActionContext<String> ctx) {

        String typeCode = StringUtils.isNotBlank(ctx.getData()) ? ctx.getData() : this.getSelectedItemsType(ctx).orElse("");
        boolean perform = StringUtils.isNotEmpty(typeCode) && this.typeService.isAssignableFrom("Item", typeCode) && this.permissionFacade.canReadType(typeCode);

        LOG.info("\nExport Action Can Perform: " + perform);
        LOG.info("\nExportAction canPerform() TYPECODE: " + typeCode);

        return true;    // return perform;
    }


    // helper functions
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



    // LOGs
    private void logCtxData(Pageable<? extends ItemModel> exportData) {
        LOG.info("\n");
        LOG.info("EXPORT DATA: " + exportData);
        LOG.info("ALL RESULTS: " + exportData.getAllResults());
        LOG.info("\nTOTAL COUNT: " + exportData.getTotalCount());
        LOG.info("ALL RESULTS LENGTH: " + exportData.getAllResults().size());
        LOG.info("\nCURRENT PAGE: " + exportData.getCurrentPage());
        LOG.info("SORT DATA: " + exportData.getSortData());
        LOG.info("PAGE NUMBER: " + exportData.getPageNumber());
        LOG.info("PAGE SIZE: " + exportData.getPageSize());
        LOG.info("TYPECODE: " + exportData.getTypeCode());
        LOG.info("QUERY ID: " + exportData.getQueryId());
        LOG.info("CLASS: " + exportData.getClass());

        LOG.info("\nHas next page: " + exportData.hasNextPage());
        LOG.info("Next page: " + exportData.nextPage());
        LOG.info("\nHas previous page: " + exportData.hasPreviousPage());
        LOG.info("Previous page: " + exportData.previousPage());
        LOG.info("\n\n");

        for (ItemModel data: EXPORT_DATA.getAllResults()) {
            this.logAllResultData(data);
        }
    }

    private void logAllResultData(ItemModel data) {
        LOG.info(data.getItemtype());
        LOG.info(data);
        LOG.info(data.getPk());
        LOG.info("\n");
    }

    private void testLog(Pageable<? extends ItemModel> exportData) {
        LOG.info("\n\nTest log function invoked!");
        LOG.info("\nLIST");
        List arrayList = exportData.getAllResults();
        LOG.info(arrayList.get(0));
        // UnmodifiableList list = (UnmodifiableList) exportData.getAllResults();   // java.util.Collections$UnmodifiableList
        LOG.info("\n\nLINKED HASH SET");
        LinkedHashSet<ItemModel> linkedHashSet = new LinkedHashSet<>(arrayList);
        for (ItemModel row : linkedHashSet) {
            LOG.info(row);
            break;  // to break after first iteration
        }
    }

    private void finalLogWithMessageBox(ActionContext<String> ctx, ActionResult<Pageable<? extends ItemModel>> result) {
        LOG.info("\n\nPage Export action invoked");
        LOG.info("Context: " + ctx);
        LOG.info("Context class: " + ctx.getClass());
        LOG.info("Context params: " + ctx.getParameters());
        LOG.info("EXPORT CONTEXT DATA: " + ctx.getData());
        // LOG.info("Context data class: " + ctx.getData().getClass());

        Messagebox.show(result.getData() + " (" + result.getResultCode() + ")");

        LOG.info("\nRESULT: " + result);
        LOG.info("RESULT CODE: " + result.getResultCode());
        LOG.info("RESULT DATA: " + result.getData());
        LOG.info("RESULT DATA RESULTS: " + result.getData().getAllResults());
        LOG.info("RESULT MESSAGE: " + result.getResultMessage());
        LOG.info("RESULT OUTPUT: " + result.getOutputsToSend());
        LOG.info("RESULT STATUS FLAG: " + result.getStatusFlags());
        LOG.info("RESULT SOCKET: " + result.getSocketAfterOperation());
        LOG.info("RESULT CLASS: " + result.getClass());
    }
}
