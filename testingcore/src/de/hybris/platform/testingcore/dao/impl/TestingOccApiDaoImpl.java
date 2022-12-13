package de.hybris.platform.testingcore.dao.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.testingcore.dao.ITestingOccApiDao;

import javax.annotation.Resource;
import java.util.*;
import java.util.logging.Logger;

public class TestingOccApiDaoImpl extends AbstractItemDao implements ITestingOccApiDao {

    @Resource(name = "flexibleSearchService")       // no need for getters & setters
    FlexibleSearchService flexibleSearchService;

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(TestingOccApiDaoImpl.class));

    private static final String QUERY_ID = "SELECT {" + CustomerModel.PK + "} FROM {" + CustomerModel._TYPECODE + "} WHERE {"
            + CustomerModel.CUSTOMERID + "} =?customerId";

    private static final String QUERY_NAME = "SELECT {" + CustomerModel.PK + "} FROM {" + CustomerModel._TYPECODE + "} WHERE {"
            + CustomerModel.NAME + "} =?name";


    // 1. returns customer by id
    @Override
    public CustomerModel getCustomerById(String customerId) {

        ServicesUtil.validateParameterNotNull(customerId, "ID must not be null");

        final Map<String, String> params = new HashMap<>();
        params.put("customerId", customerId);

        final SearchResult result = getFlexibleSearchService().search(QUERY_ID, params);

        return result.getCount() > 0 ? (CustomerModel) result.getResult().get(0) : null;
    }

    // 2. returns only first customer by name (because it does not return a List)
    @Override
    public CustomerModel getCustomerByName(String name) {

        ServicesUtil.validateParameterNotNull(name, "Name must not be null");

        final Map<String, String> params = new HashMap<>();
        name = validateName(name);
        System.out.println("Name: " + name);
        params.put("name", name);

        final SearchResult result = getFlexibleSearchService().search(QUERY_NAME, params);

        return result.getCount() > 0 ? (CustomerModel) result.getResult().get(0): null;
    }

    // 3. ** RETURNS A LIST OF ALL CUSTOMERS
    @Override
    public List<CustomerModel> getAllCustomers() {

        final StringBuilder builder = new StringBuilder();
        builder.append("SELECT {c:").append(CustomerModel.PK).append("}");
        builder.append("FROM {").append(CustomerModel._TYPECODE).append(" AS c}");

        final FlexibleSearchQuery query = new FlexibleSearchQuery(builder.toString());

        return flexibleSearchService.<CustomerModel>search(query).getResult();
    }

    // 4. ** RETURNS A LIST OF ALL CUSTOMERS WITH SAME NAME
    @Override
    public List<CustomerModel> getAllCustomersWithName(String name) {

        ServicesUtil.validateParameterNotNull(name, "Name must not be null");

        final Map<String, Object> params = new HashMap<>();
        name = validateName(name);
        System.out.println("Name: " + name);
        params.put("name", name);

        LOGGER.info(String.valueOf(getFlexibleSearchService().search(QUERY_NAME, params).getClass()));

        // getFlexibleSearch is out-of-the-box
        final SearchResult<CustomerModel> customers = getFlexibleSearchService().search(QUERY_NAME, params);

        return customers.getResult() == null ? Collections.emptyList() : customers.getResult();
    }

    private static String validateName(String fullName) {

        String[] nameArray = fullName.split(" ", 2);

        String validFirst = nameArray[0].substring(0, 1).toUpperCase() + nameArray[0].substring(1);
        String validLast = nameArray.length > 1 ? nameArray[1].substring(0, 1).toUpperCase() + nameArray[1].substring(1) : "";

        String validName = nameArray.length > 1 ? validFirst + " " + validLast : validFirst;

        return validName;
    }
}
