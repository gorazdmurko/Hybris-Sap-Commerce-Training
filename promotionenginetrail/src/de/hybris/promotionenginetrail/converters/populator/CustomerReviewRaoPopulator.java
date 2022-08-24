package de.hybris.promotionenginetrail.converters.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
import de.hybris.platform.promotionenginetrail.rao.CustomerReviewRAO;
import de.hybris.platform.ruleengineservices.rao.ProductRAO;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;


/*
    *************************************************************************
    *                                                                       *
    *       IMPLEMENT THE CUSTOMERREVIEWRAOPOPULATOR                        *
    *       This populates a CustomerReviewRAO from a CustomerReviewModel,  *
    *       which is the data model for products with customer reviews      *
    *                                                                       *
    *************************************************************************
* */

public class CustomerReviewRaoPopulator implements Populator<CustomerReviewModel, CustomerReviewRAO> {

    @Override
    public void populate(final CustomerReviewModel source, final CustomerReviewRAO target) throws ConversionException
    {
        target.setProductCode(source.getProduct().getCode());
    }
}
