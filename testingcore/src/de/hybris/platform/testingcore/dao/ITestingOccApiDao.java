package de.hybris.platform.testingcore.dao;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;

import java.util.List;

public interface ITestingOccApiDao extends Dao {

    CustomerModel getCustomerById(String customerId);
    CustomerModel getCustomerByName(String name);

    List<CustomerModel> getAllCustomers();
    List<CustomerModel> getAllCustomersWithName(String name);
}
