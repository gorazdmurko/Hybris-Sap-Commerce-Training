package de.hybris.platform.testingcore.service;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

public interface ITestingOccApiService {

    CustomerModel findCustomerById(String customerId);
    CustomerModel findCustomerByName(String name);
    List<CustomerModel> findAllCustomers();
    List<CustomerModel> findAllCustomersByName(String name);
}
