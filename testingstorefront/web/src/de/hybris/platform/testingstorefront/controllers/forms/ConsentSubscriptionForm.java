package de.hybris.platform.testingstorefront.controllers.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.ConsentForm;

public class ConsentSubscriptionForm {

    private String anonymousUserEmail;
    private ConsentForm consentForm;

    public String getAnonymousUserEmail() {
        return anonymousUserEmail;
    }

    public void setAnonymousUserEmail(String anonymousUserEmail) {
        this.anonymousUserEmail = anonymousUserEmail;
    }

    public ConsentForm getConsentForm() {
        return consentForm;
    }

    public void setConsentForm(ConsentForm consentForm) {
        this.consentForm = consentForm;
    }
}
