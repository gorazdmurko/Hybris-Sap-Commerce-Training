package de.hybris.platform.testingbackoffice.imp.wizard;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

public class MultipleProductsSelectWizardForm {

    public MultipleProductsSelectWizardForm() {}

    private List<ProductModel> productsModelList;

    public List<ProductModel> getProductsModelList() {
        return productsModelList;
    }

    public void setProductsModelList(List<ProductModel> productsModelList) {
        this.productsModelList = productsModelList;
    }
}
