package de.hybris.platform.testingbackoffice.widgets;

public class CustomInputService {

    public boolean isNumberOrNot(final String input) {
        try {
            Integer.parseInt(input);
        } catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }
}
