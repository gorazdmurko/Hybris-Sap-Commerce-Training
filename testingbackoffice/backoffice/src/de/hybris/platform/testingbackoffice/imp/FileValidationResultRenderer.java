package de.hybris.platform.testingbackoffice.imp;

import com.hybris.cockpitng.config.jaxb.wizard.ViewType;
import com.hybris.cockpitng.dataaccess.facades.type.DataType;
import com.hybris.cockpitng.engine.WidgetInstanceManager;
// import com.hybris.cockpitng.util.notifications.NotificationService;
// import de.hybris.platform.notificationservices.service.NotificationService;
import com.hybris.backoffice.widgets.notificationarea.NotificationService;
import com.hybris.cockpitng.widgets.configurableflow.renderer.DefaultCustomViewRenderer;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.springframework.beans.factory.annotation.Required;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.*;

import java.util.Map;

public class FileValidationResultRenderer extends DefaultCustomViewRenderer {

    private NotificationService notificationService;
    private ConfigurationService configurationService;

    public FileValidationResultRenderer() {
    }

    public void render(Component parent, ViewType customView, Map<String, String> parameters, DataType dataType, WidgetInstanceManager wim) {
        // List<ExcelValidationResult> result = (List)wim.getModel().getValue("excelImportValidationResult", List.class);
        Div container = new Div();
        container.setSclass("yw-excel-validation-result-container");
        container.setParent(parent);
        Div subtitle = this.createSubtitle();
        subtitle.setParent(container);

      /*  Listbox listBox = this.createListbox(this.reduceResultsList(result));
        listBox.setParent(container);*/
        //  Button button = this.createButton(result);
        //  button.setParent(parent.getParent());
    }

    /*  protected List<ExcelValidationResult> reduceResultsList(List<ExcelValidationResult> result) {
          int maxErrorRenderAmount = this.configurationService.getConfiguration().getInt("backoffice.excel.import.validation.result.renderer.max.results.to.render", 20);
          if (result.size() > maxErrorRenderAmount) {
              this.notificationService.notifyUser("excelImportValidationResultRendererHandler", "General", NotificationEvent.Level.WARNING, new Object[0]);
              return this.limitValidationResultsToMaxThreshold(result, maxErrorRenderAmount);
          } else {
              return result;
          }
      }

      protected List<ExcelValidationResult> limitValidationResultsToMaxThreshold(List<ExcelValidationResult> result, int maxErrorRenderAmount) {
          return (List)result.stream().limit((long)maxErrorRenderAmount).collect(Collectors.toList());
      }
  */
    protected Div createSubtitle() {
        Div subtitleContainer = new Div();
        subtitleContainer.setSclass("yw-excel-validation-result-subtitle-container");
        Label subtitle = new Label(Labels.getLabel("excel.import.wizard.validation.result.subtitle"));
        subtitle.setSclass("yw-excel-validation-result-subtitle");
        subtitle.setParent(subtitleContainer);
        return subtitleContainer;
    }

   /* protected Listbox createListbox(List<ExcelValidationResult> result) {
        Listbox listBox = new Listbox();
        listBox.setDisabled(true);
        listBox.setNonselectableTags("*");
        listBox.setOddRowSclass("yw-excel-validation-result-container-odd-row");
        listBox.setModel(new ListModelArray(result));
        listBox.setItemRenderer(this.getListitemRenderer());
        return listBox;
    }

    protected Button createButton(List<ExcelValidationResult> result) {
        Button button = new Button(Labels.getLabel("excel.import.wizard.downloadvalidationerrros.button.label"));
        button.addEventListener("onClick", (event) -> {
            System.out.println("download validation report!");
          //  this.excelDownloadReportService.downloadReport(result);
        });
        button.setSclass("yw-excel-validation-result-report");
        return button;
    }
*/
  /*  public ListitemRenderer<ExcelValidationResult> getListitemRenderer() {
        return this.listitemRenderer;
    }

    @Required
    public void setListitemRenderer(ListitemRenderer<ExcelValidationResult> listitemRenderer) {
        this.listitemRenderer = listitemRenderer;
    }*/

   /* public ExcelDownloadReportService getExcelDownloadReportService() {
        return this.excelDownloadReportService;
    }

    @Required
    public void setExcelDownloadReportService(ExcelDownloadReportService excelDownloadReportService) {
        this.excelDownloadReportService = excelDownloadReportService;
    }*/

    public NotificationService getNotificationService() {
        return this.notificationService;
    }

    @Required
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public ConfigurationService getConfigurationService() {
        return this.configurationService;
    }

    @Required
    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
