package de.hybris.platform.testingfacade.converters.populator;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.CustomerListData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.testingcore.service.ITestingOccApiService;

import java.util.ArrayList;
import java.util.List;

public class CustomerListByNameDataPopulator implements Populator<List<CustomerModel>, CustomerData> {

    private ITestingOccApiService service;
    // set converter which converts ALL customers from model to data - !! this converter uses customerDataPopulator !!
    private Converter<CustomerModel, CustomerData> converter;

    @Override
    public void populate(List<CustomerModel> source, CustomerData target) throws ConversionException {

        Converters.convertAll(source, getConverter());
    }

    // CONVERTER
    public Converter<CustomerModel, CustomerData> getConverter() {
        return converter;
    }

    public void setConverter(Converter<CustomerModel, CustomerData> converter) {
        this.converter = converter;
    }

    // SERVICE
    public ITestingOccApiService getService() {
        return service;
    }

    public void setService(ITestingOccApiService service) {
        this.service = service;
    }
}
