package de.hybris.platform.checkboxconfiguratortemplatefacades.strategies;

import de.hybris.platform.commercefacades.order.data.ConfigurationInfoData;
import de.hybris.platform.commercefacades.product.strategies.merge.ProductConfigurationMergeStrategy;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

/*
 ************************************************************************
 *              Add the strategy implementation                         *
 *                                                                      *
 *      to support order entry process update. You need to have custom  *
 *      implementation of ProductConfigurationMergeStrategy to merge    *
 *      two lists of ConfigurationInfoData                              *
 *                                                                      *
 ************************************************************************

 * */

public class CheckboxProductConfigurationMergeStrategy implements ProductConfigurationMergeStrategy {

    public static final String CHECKBOX_CHECKED_VALUE = "on";

    @Nonnull
    @Override
    public List<ConfigurationInfoData> merge(@Nonnull final List<ConfigurationInfoData> baseConfiguration,
                                             @Nonnull final List<ConfigurationInfoData> mergeConfiguration)
    {
        List<String> formLabels = baseConfiguration.stream()
                .peek(this::setConfigurationValueToBooleanString)
                .map(ConfigurationInfoData::getConfigurationLabel)
                .collect(Collectors.toList());

        List<ConfigurationInfoData> checkboxConfiguration = mergeConfiguration.stream()
                .filter(checkBoxConfiguration -> !formLabels.contains(checkBoxConfiguration.getConfigurationLabel()))
                .collect(Collectors.toList());

        checkboxConfiguration.stream()
                .forEach(configuration -> configuration.setConfigurationValue(Boolean.FALSE.toString()));

        return checkboxConfiguration;
    }

    protected void setConfigurationValueToBooleanString(ConfigurationInfoData configurationInfoData)
    {
        configurationInfoData.setConfigurationValue(
                Boolean.valueOf(CHECKBOX_CHECKED_VALUE.equals(configurationInfoData.getConfigurationValue())).toString());
    }
}
