package de.hybris.platform.testingstorefront.controllers.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.ReviewForm;

public class TestingReviewForm extends ReviewForm {

    private boolean reviewAsGuest;
    private String guestEmail;
    private boolean gdprAgreement;
    private boolean awardGame;

    public boolean isReviewAsGuest() {
        return reviewAsGuest;
    }

    public void setReviewAsGuest(boolean reviewAsGuest) {
        this.reviewAsGuest = reviewAsGuest;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public boolean isGdprAgreement() {
        return gdprAgreement;
    }

    public void setGdprAgreement(boolean gdprAgreement) {
        this.gdprAgreement = gdprAgreement;
    }

    public boolean isAwardGame() {
        return awardGame;
    }

    public void setAwardGame(boolean awardGame) {
        this.awardGame = awardGame;
    }
}
