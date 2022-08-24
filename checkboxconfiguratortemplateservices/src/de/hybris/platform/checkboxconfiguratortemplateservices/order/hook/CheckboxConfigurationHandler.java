package de.hybris.platform.checkboxconfiguratortemplateservices.order.hook;

import de.hybris.platform.catalog.enums.ConfiguratorType;
import de.hybris.platform.checkboxconfiguratortemplateservices.jalo.CheckboxConfiguratorOrderedProductInfo;
import de.hybris.platform.checkboxconfiguratortemplateservices.jalo.DropdownConfiguratorSettings;
import de.hybris.platform.checkboxconfiguratortemplateservices.model.CheckboxConfiguratorSettingsModel;
import de.hybris.platform.checkboxconfiguratortemplateservices.model.CheckboxConfiguredProductInfoModel;
import de.hybris.platform.checkboxconfiguratortemplateservices.model.DropdownConfiguratorSettingsModel;
import de.hybris.platform.commerceservices.order.ProductConfigurationHandler;
import de.hybris.platform.commerceservices.service.data.ProductConfigurationItem;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.order.jalo.GeneratedAbstractOrderEntryProductInfo;
import de.hybris.platform.order.model.AbstractOrderEntryProductInfoModel;
import de.hybris.platform.product.model.AbstractConfiguratorSettingModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
 *********************************************************************************************
 *              Add the handler implementation                                               *
 *                                                                                           *
 *       to create a new custom handler that for a given (custom) configuration type can     *
 *       put the product-related configuration items into order-related ones                 *
 *                                                                                           *
 *********************************************************************************************

 * */

public class CheckboxConfigurationHandler implements ProductConfigurationHandler {

    @Override
    // for an order entries create a list of product info = created from ConfiguratorSettings
    public List<AbstractOrderEntryProductInfoModel> createProductInfo(final AbstractConfiguratorSettingModel productSettings) {
        if (!(productSettings instanceof CheckboxConfiguratorSettingsModel)) {
            throw new IllegalArgumentException("Argument must be a type of CheckboxConfiguratorSettingsModel");
        }

        final CheckboxConfiguratorSettingsModel checkboxSettings = (CheckboxConfiguratorSettingsModel) productSettings;
        // final DropdownConfiguratorSettingsModel dropdownSettings = (DropdownConfiguratorSettingsModel) productSettings;

        final CheckboxConfiguredProductInfoModel result = new CheckboxConfiguredProductInfoModel();
        result.setConfiguratorType(ConfiguratorType.CHECKBOX);
        result.setConfigurationLabel(checkboxSettings.getConfigurationLabel());
        result.setChecked(checkboxSettings.isChecked());

//        // DROPDOWN
//        final DropdownConfiguratorSettingsModel model = new DropdownConfiguratorSettingsModel();
//        model.setConfiguratorType(ConfiguratorType.DROPDOWN);
//        model.setConfigurationLabel(dropdownSettings.getConfigurationLabel());
//        model.setChecked(dropdownSettings.isChecked());

        return Collections.singletonList(result);
    }

    @Override
    public List<AbstractOrderEntryProductInfoModel> convert(final Collection<ProductConfigurationItem> items,
                                                            final AbstractOrderEntryModel entry) {
        return items.stream().map(item -> {
            final CheckboxConfiguredProductInfoModel result = new CheckboxConfiguredProductInfoModel();
            result.setConfigurationLabel(item.getKey());
            result.setChecked(Boolean.valueOf(item.getValue().toString()));
            result.setConfiguratorType(ConfiguratorType.CHECKBOX);
            return result;
        }).collect(Collectors.toList());
    }
}
