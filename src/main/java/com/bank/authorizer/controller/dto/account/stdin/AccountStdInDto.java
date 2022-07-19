package com.bank.authorizer.controller.dto.account.stdin;

import java.util.List;

/**
 * Class that represents an account standard entry  dto
 */
public class AccountStdInDto {
    private boolean activeCard;
    private int availableLimit;


    /**
     * Getter and setter
     */

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


}
