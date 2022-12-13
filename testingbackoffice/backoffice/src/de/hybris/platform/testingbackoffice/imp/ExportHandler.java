package de.hybris.platform.testingbackoffice.imp;

import com.hybris.cockpitng.config.jaxb.wizard.CustomType;
import com.hybris.cockpitng.engine.WidgetInstanceManager;
import com.hybris.cockpitng.widgets.configurableflow.FlowActionHandler;
import com.hybris.cockpitng.widgets.configurableflow.FlowActionHandlerAdapter;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.testingbackoffice.imp.wizard.ExportWizardForm;

import java.util.Map;

public class ExportHandler implements FlowActionHandler {

    private ModelService modelService;

    public ExportHandler() {
        System.out.println("File Export Wizard constructor invoked");
    }

    @Override
    public void perform(CustomType customType, FlowActionHandlerAdapter adapter, Map<String, String> params) {

        try {
            // 1. way
            WidgetInstanceManager wim = adapter.getWidgetInstanceManager();
            ExportWizardForm form = this.getExportForm(wim, params);

            System.out.println("EXPORT FORM: " + form);
            System.out.println("TYPE_CODE: " + form.getTypeCode());
            System.out.println("EXPORT_DATA - PAGEABLE: " + form.getPageable());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // 2. way
            ExportWizardForm data = adapter.getWidgetInstanceManager().getModel().getValue("exportForm", ExportWizardForm.class);

            System.out.println("EXPORT DATA: " + data);
            System.out.println("TYPE_CODE DATA: " + data.getTypeCode());
            System.out.println("EXPORT_DATA - PAGEABLE: " + data.getPageable());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected ExportWizardForm getExportForm(WidgetInstanceManager wim, Map<String, String> parameters) {
        String formModelKey = parameters.getOrDefault("exportFormModelKey", "exportForm");
        return wim.getModel().getValue(formModelKey, ExportWizardForm.class);
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}