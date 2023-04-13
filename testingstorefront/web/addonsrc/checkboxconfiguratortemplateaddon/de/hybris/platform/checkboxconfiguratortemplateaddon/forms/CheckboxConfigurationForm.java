package de.hybris.platform.checkboxconfiguratortemplateaddon.forms;

import de.hybris.platform.catalog.enums.ConfiguratorType;

import java.util.Map;

/*
    *********************************************************
    *   Add the form implementation                         *
    *                                                       *
    *   to create a form to store configurations            *
    *   according to configurator type                      *
    *                                                       *
    *********************************************************

* */

public class CheckboxConfigurationForm {

    private Long quantity;
    private Map<ConfiguratorType, Map<String, String>> configurationsKeyValueMap;

    public Long getQuantity()
    {
        return quantity;
    }

    public void setQuantity(final Long quantity)
    {
        this.quantity = quantity;
    }

    public Map<ConfiguratorType, Map<String, String>> getConfigurationsKeyValueMap()
    {
        return configurationsKeyValueMap;
    }

    public void setConfigurationsKeyValueMap(final Map<ConfiguratorType, Map<String, String>> configurationsKeyValueMap)
    {
        this.configurationsKeyValueMap = configurationsKeyValueMap;
    }
}
