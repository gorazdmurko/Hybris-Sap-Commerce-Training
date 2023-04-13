package de.hybris.platform.testingbackoffice.imp.wizard;

import com.hybris.cockpitng.editor.defaultfileupload.FileUploadResult;
import de.hybris.platform.catalog.model.CatalogVersionModel;

import java.util.HashSet;
import java.util.Set;

public class FileImportWizardForm {
    private FileUploadResult importFile;
    private CatalogVersionModel catalogVersion;
    private Set<FileUploadResult> fileUploadResult = new HashSet();

    public FileImportWizardForm() {
        System.out.println("FileImportWizardForm constructor invoked");
    }

    public FileUploadResult getImportFile() {
        return importFile;
    }

    public void setImportFile(FileUploadResult importFile) {
        this.importFile = importFile;
    }

    public Set<FileUploadResult> getFileUploadResult() {
        return this.fileUploadResult;
    }

    public void setFileUploadResult(Set<FileUploadResult> fileUploadResult) {
        this.fileUploadResult = fileUploadResult;
    }

    public CatalogVersionModel getCatalogVersion() {
        return catalogVersion;
    }

    public void setCatalogVersion(CatalogVersionModel catalogVersion) {
        this.catalogVersion = catalogVersion;
    }
}
