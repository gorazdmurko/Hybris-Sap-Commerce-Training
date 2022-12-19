package de.hybris.platform.testingcore.dao;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.search.SearchResult;

public interface IFindOldCartsDao {
    public SearchResult<CartModel> findOldCarts(int hours);
}
