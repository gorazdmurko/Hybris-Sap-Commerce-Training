package de.hybris.platform.testingfacade.converters.populator;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.testframework.Assert;
import de.hybris.platform.testingcore.model.ExternalTokenModel;
import junit.framework.TestCase;

import java.util.Collection;

public class CustomerDataPopulatorTest extends TestCase {

    CustomerData customerData;
    CustomerModel customerModel;
    ExternalTokenModel tokenModel;
    CustomerDataPopulator populator;
    Collection<ExternalTokenModel> tokens;

    public void testPopulate() {

        // set token model and add to tokens list
        tokenModel.setValue("e547f866-9e5e-43f8-8b6c-acf450752504");
        tokens.add(tokenModel);
        customerModel.setExternalToken(tokens);

        // populate customer
        populator.populate(customerModel, customerData);

        // log the result
        System.out.println("Customer's enum:\n" + customerData.getExternalTokens().get(0).getAbc());
        System.out.println("Customer's token value:\n" + customerData.getExternalTokens().get(0).getValue());

        // Assert.assertEquals("e547f866-9e5e-43f8-8b6c-acf450752504", customerData.getExternalTokens().get(0).getValue()));

    }

    public void testGetTokenDataConverter() {
    }

    public void testSetTokenConverter() {
    }
}