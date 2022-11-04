package de.hybris.platform.testingfacade.converters.populator;

import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.testingcore.model.ExternalTokenModel;
import de.hybris.platform.testingfacade.data.ExternalTokenData;

import java.util.ArrayList;
import java.util.List;

// extends CustomerPopulator ??

public class CustomerDataPopulator implements Populator<CustomerModel, CustomerData> {

    // set converter which converts all tokens from model to data - !! this converter uses externalTokenDataPopulator !!
    private Converter<ExternalTokenModel, ExternalTokenData> tokenConverter;

    @Override
    public void populate(CustomerModel source, CustomerData target) throws ConversionException {
        // super.populate(source, target);

        List<String> name = splitName(source.getName());

        // !! populate firstName, lastName, id & title
        target.setFirstName(name.get(0));
        target.setLastName(name.get(1));
        target.setCustomerId(source.getCustomerID());
        target.setTitle(String.valueOf(source.getTitle()));

        // !! POPULATE EXTERNAL TOKENS      ==>  populates list      ==>    .convertAll(sourceList, converter);
        target.setExternalTokens(new ArrayList<ExternalTokenData>(
                Converters.convertAll(
                        source.getExternalToken(), getTokenDataConverter())
                ));

    }

    public Converter<ExternalTokenModel, ExternalTokenData> getTokenDataConverter() {
        return tokenConverter;
    }

    public void setTokenConverter(final Converter<ExternalTokenModel, ExternalTokenData> tokenConverter) {
        this.tokenConverter = tokenConverter;
    }


    private List splitName(String fullName) {

        List<String> nameList = new ArrayList<>();

        String[] nameArray = fullName.split(" ", 2);

        nameList.add(nameArray[0]);
        nameList.add(nameArray.length > 1 ? nameArray[1] : "");

        return nameList;
    }
}
