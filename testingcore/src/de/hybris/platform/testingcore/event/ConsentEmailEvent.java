package de.hybris.platform.testingcore.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.commerceservices.model.consent.ConsentModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.store.BaseStoreModel;

public class ConsentEmailEvent extends AbstractCommerceUserEvent {

    private String token;
    private ConsentModel consent;
    private String consentConfirmationURL;

    public ConsentEmailEvent(final String url) {

        this.consentConfirmationURL = url;
//        setBaseStore(baseStore);
//        setSite(site);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ConsentModel getConsent() {
        return consent;
    }

    public void setConsent(ConsentModel consent) {
        this.consent = consent;
    }

    public String getConsentConfirmationURL() {
        return consentConfirmationURL;
    }

    public void setConsentConfirmationURL(String consentConfirmationURL) {
        this.consentConfirmationURL = consentConfirmationURL;
    }
}
