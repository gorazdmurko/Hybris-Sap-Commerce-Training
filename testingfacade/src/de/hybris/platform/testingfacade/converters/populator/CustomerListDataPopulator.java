package de.hybris.platform.testingfacade.converters.populator;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.CustomerListData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.ArrayList;
import java.util.List;

public class CustomerListDataPopulator  implements Populator<List<CustomerModel>, CustomerListData> {

    private CustomerDataPopulator customerDataPopulator;

    @Override
    public void populate(List<CustomerModel> source, CustomerListData target) throws ConversionException {
        // super.populate(source, target);

        List<CustomerData> dataList = new ArrayList<>();

        for (CustomerModel model : source) {
            CustomerData data = new CustomerData();
            customerDataPopulator.populate(model, data);    // populates from model to data
            dataList.add(data);                             // adds data to list
        }

        target.setCustomerList(dataList);
    }

    public CustomerDataPopulator getCustomerDataPopulator() {
        return customerDataPopulator;
    }

    public void setCustomerDataPopulator(CustomerDataPopulator customerDataPopulator) {
        this.customerDataPopulator = customerDataPopulator;
    }
}
