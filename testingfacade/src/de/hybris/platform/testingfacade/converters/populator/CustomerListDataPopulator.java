package de.hybris.platform.testingfacade.converters.populator;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.CustomerListData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class CustomerListDataPopulator  implements Populator<List<CustomerModel>, CustomerListData> {

    private CustomerDataPopulator customerDataPopulator;
    private Converter<CustomerModel, CustomerData> converter;

    @Override
    public void populate(List<CustomerModel> source, CustomerListData target) throws ConversionException {
        // super.populate(source, target);

//        List<CustomerData> dataList = new ArrayList<>();
//
//        for (CustomerModel model : source) {
//            CustomerData data = new CustomerData();
//            customerDataPopulator.populate(model, data);    // populates from model to data
//            dataList.add(data);                             // adds data to list
//        }
//
//        target.setCustomerList(dataList);

        target.setCustomerList(new ArrayList<CustomerData>(Converters.convertAll(source, getConverter())));
    }

    public CustomerDataPopulator getCustomerDataPopulator() {
        return customerDataPopulator;
    }

    public void setCustomerDataPopulator(CustomerDataPopulator customerDataPopulator) {
        this.customerDataPopulator = customerDataPopulator;
    }

    public Converter<CustomerModel, CustomerData> getConverter() {
        return converter;
    }

    public void setConverter(Converter<CustomerModel, CustomerData> converter) {
        this.converter = converter;
    }
}
