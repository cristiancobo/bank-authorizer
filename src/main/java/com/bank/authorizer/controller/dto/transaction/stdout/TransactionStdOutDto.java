package com.bank.authorizer.controller.dto.transaction.stdout;

import java.time.LocalDateTime;

public class TransactionStdOutDto {

    private int id;

    private int accountId;

    private String merchant;

    private int amount;

    private LocalDateTime time;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public int getAmount() {
        return amount;
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
