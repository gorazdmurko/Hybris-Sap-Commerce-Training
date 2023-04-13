package de.hybris.platform.testingfacade.converters.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.testingcore.model.ExternalTokenModel;
import de.hybris.platform.testingfacade.data.ExternalTokenData;

public class ExternalTokenDataPopulator implements Populator<ExternalTokenModel, ExternalTokenData> {
    @Override
    public void populate(ExternalTokenModel source, ExternalTokenData target) throws ConversionException {
        target.setAbc(String.valueOf(source.getAbc()));
        target.setValue(source.getValue());
    }
}
