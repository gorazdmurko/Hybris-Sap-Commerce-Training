package de.hybris.platform.testingbackoffice.widgets;

import java.util.ArrayList;
import java.util.List;

public class MySearchService {

    public List<String> search(final String text)
    {
        List<String> result = new ArrayList<String>();
        result.add(text);
        result.add("Hello");
        result.add("Cockpit NG");
        result.add("Developer");

        return result;
    }
}
