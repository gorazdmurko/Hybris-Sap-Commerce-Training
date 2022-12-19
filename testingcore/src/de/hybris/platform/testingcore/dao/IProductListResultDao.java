package de.hybris.platform.testingcore.dao;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.SearchResult;

public interface IProductListResultDao {
    public SearchResult<ProductModel> findProductsByFirstLetter();
}
