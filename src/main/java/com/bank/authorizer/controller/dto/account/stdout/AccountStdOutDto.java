package com.bank.authorizer.controller.dto.account.stdout;

import com.bank.authorizer.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AccountStdOutDto {

    private int id;

    private boolean activeCard;

    private int availableLimit;

    private List<String> accountViolations;

    private List<Transaction> transactionsAccount;

    public AccountStdOutDto() {
        this.accountViolations = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void addTransaction(Transaction transaction){
        if(transactionsAccount == null){
            transactionsAccount=new ArrayList<>();
        }
        transactionsAccount.add(transaction);
    }

    public List<Transaction> getTransactionsAccount() {
        return transactionsAccount;
    }

    public void addViolation(String violation){
        accountViolations.add(violation);
    }

    public void setTransactionsAccount(List<Transaction> transactions) {
        this.transactionsAccount = transactions;
    }}
