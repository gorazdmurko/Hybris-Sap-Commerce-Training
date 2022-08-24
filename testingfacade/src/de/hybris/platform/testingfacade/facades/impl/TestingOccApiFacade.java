package de.hybris.platform.testingfacade.facades.impl;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.CustomerListData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.testingcore.service.ITestingOccApiService;
import de.hybris.platform.testingcore.service.impl.TestingOccApiService;
import de.hybris.platform.testingfacade.converters.populator.CustomerDataPopulator;
import de.hybris.platform.testingfacade.converters.populator.CustomerListByNameDataPopulator;
import de.hybris.platform.testingfacade.converters.populator.CustomerListDataPopulator;
import de.hybris.platform.testingfacade.facades.ITestingOccApiFacade;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

public class TestingOccApiFacade implements ITestingOccApiFacade {

    private ITestingOccApiService service;
    private CustomerDataPopulator populator;
    private CustomerListDataPopulator listPopulator;
    private Converter<CustomerModel, CustomerData> converter;   // customerDataConverter BEAN


    // 1. RETURNS CUSTOMER WITH MATCHING ID
    @Override
    public CustomerData populateCustomerData(final String customerId) {
        CustomerData customerData = new CustomerData();
        CustomerModel model = getService().findCustomerById(customerId);
        populator.populate(model, customerData);

        return customerData;
    }

    // 2. RETURNS FIRST CUSTOMER WITH MATCHING NAME
    @Override
    public CustomerData populateCustomerDataByName(String name) {
        CustomerData data = new CustomerData();
        CustomerModel model = getService().findCustomerByName(name);
        populator.populate(model, data);

        return data;
    }

    // 3. RETURNS A POPULATED LIST OF ALL CUSTOMERS
    @Override
    public CustomerListData populateCustomerListData() {
        List<CustomerModel> modelList = getService().findAllCustomers();

        CustomerListData dataList = new CustomerListData();
        listPopulator.populate(modelList,  dataList);

        return dataList;
    }


    // 4. RETURNS A POPULATED LIST OF ALL CUSTOMERS WITH SAME NAME
    @Override
    public List<CustomerData> populateCustomerListByName(String name) {

        // **list of CustomerModel classes
        final List<CustomerModel> customerModels = getService().findAllCustomersByName(name);

        // **goes through a list and converts all customers from model to data
        return Converters.convertAll(customerModels, getConverter());   // see Converters class for more details

        // **could also write a custom populator like in CustomerListDataPopulator example !!
    }


    // SERVICE
    public ITestingOccApiService getService() {return service; }

    @Required
    public void setService(TestingOccApiService service) {
        this.service = service;
    }

    // POPULATOR
    public CustomerDataPopulator getPopulator() {
        return populator;
    }

    @Required
    public void setPopulator(CustomerDataPopulator populator) {
        this.populator = populator;
    }

    // LIST POPULATOR
    public CustomerListDataPopulator getListPopulator() {
        return listPopulator;
    }

    @Required
    public void setListPopulator(CustomerListDataPopulator listPopulator) {
        this.listPopulator = listPopulator;
    }

    // CONVERTER
    public Converter<CustomerModel, CustomerData> getConverter() {
        return converter;
    }

    public void setConverter(Converter<CustomerModel, CustomerData> converter) {
        this.converter = converter;
    }
}
