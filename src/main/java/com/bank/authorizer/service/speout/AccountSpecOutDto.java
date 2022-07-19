package com.bank.authorizer.service.speout;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents an account special entry  dto
 */
public class AccountSpecOutDto {
    private boolean activeCard;

    private int availableLimit;

    private List<String> accountViolations;

    public AccountSpecOutDto() {
        this.accountViolations = new ArrayList<>();
    }

    public boolean isActiveCard() {
        return activeCard;
    }

    public void setActiveCard(boolean activeCard) {
        this.activeCard = activeCard;
    }

    public int getAvailableLimit() {
        return availableLimit;
    }

    public void setAvailableLimit(int availableLimit) {
        this.availableLimit = availableLimit;
    }

    public List<String> getViolations() {
        return accountViolations;
    }

    public void setViolations(List<String> violations) {
        this.accountViolations = violations;
    }

    public void addViolation(String violation){

        accountViolations.add(violation);
    }
}
