package de.hybris.platform.testingcore.dao.impl;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.testingcore.dao.IFindOldCartsDao;

import javax.annotation.Resource;

public class FindOldCartsDaoImpl implements IFindOldCartsDao {

    private static final String HOUR = "hour";
    private static final String FILTER_TO_CREATION_TIME = " {cart.creationTime} > SYSDATE - INTERVAL '";
    private static final String CART_QUERY = "SELECT {cart.pk} FROM {Cart as cart}";
    private static final String WHERE_CLAUSE = " WHERE ";

    @Resource(name = "flexibleSearchService")
    FlexibleSearchService flexibleSearchService;

    @Override
    public SearchResult<CartModel> findOldCarts(int hours) {
        final StringBuilder stringBuilder = new StringBuilder(CART_QUERY);
        stringBuilder.append(WHERE_CLAUSE);
        stringBuilder.append(FILTER_TO_CREATION_TIME);
        stringBuilder.append(hours);
        stringBuilder.append("' HOUR");

        final FlexibleSearchQuery query = new FlexibleSearchQuery(stringBuilder.toString());

        return flexibleSearchService.search(query);
    }
}
