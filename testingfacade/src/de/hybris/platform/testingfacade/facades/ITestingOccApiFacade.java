package de.hybris.platform.testingfacade.facades;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.CustomerListData;

import java.util.List;

public interface ITestingOccApiFacade {

    CustomerData populateCustomerData(final String customerId);
    CustomerData populateCustomerDataByName(final String name);
    CustomerListData populateCustomerListData();
    List<CustomerData> populateCustomerListByName(final String name);
}
