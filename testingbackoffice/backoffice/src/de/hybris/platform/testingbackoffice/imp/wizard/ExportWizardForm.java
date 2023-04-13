package de.hybris.platform.testingbackoffice.imp.wizard;

import com.hybris.backoffice.attributechooser.AttributeChooserForm;
import com.hybris.backoffice.excel.data.SelectedAttribute;
import com.hybris.cockpitng.search.data.pageable.Pageable;
import de.hybris.platform.core.model.ItemModel;

import java.util.List;
import java.util.Objects;

public class ExportWizardForm {

    private Pageable<ItemModel> pageable;
    private String typeCode;

    private List<SelectedAttribute> attributes;
    private AttributeChooserForm attributesForm;
    private AttributeChooserForm classificationAttributesForm;
    private boolean exportTemplate;

    public ExportWizardForm() {
        System.out.println("FileExportWizardForm constructor invoked");
    }

    public Pageable<ItemModel> getPageable() {
        return pageable;
    }

    public void setPageable(Pageable<ItemModel> pageable) {
        this.pageable = pageable;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public List<SelectedAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<SelectedAttribute> attributes) {
        this.attributes = attributes;
    }

    public AttributeChooserForm getAttributesForm() {
        return attributesForm;
    }

    public void setAttributesForm(AttributeChooserForm attributesForm) {
        this.attributesForm = attributesForm;
    }

    public AttributeChooserForm getClassificationAttributesForm() {
        return classificationAttributesForm;
    }

    public void setClassificationAttributesForm(AttributeChooserForm classificationAttributesForm) {
        this.classificationAttributesForm = classificationAttributesForm;
    }

    public boolean isExportTemplate() {
        return exportTemplate;
    }

    public void setExportTemplate(boolean exportTemplate) {
        this.exportTemplate = exportTemplate;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            ExportWizardForm that = (ExportWizardForm) o;
            return Objects.equals(this.pageable, that.pageable) && Objects.equals(this.typeCode, that.typeCode) && Objects.equals(this.attributesForm, that.attributesForm) && Objects.equals(this.classificationAttributesForm, that.classificationAttributesForm);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.pageable, this.typeCode, this.attributesForm, this.classificationAttributesForm});
    }
}