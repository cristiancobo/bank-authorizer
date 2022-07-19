package com.bank.authorizer.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ACCOUNT")
public class Account {

    /**
     * constants
     */
    public static final String ACCOUNT_ALREADY_INITIALIZED = "account-already-initialized";
    public static final String ACCOUNT_NOT_INITIALIZED = "account-not-initialized";
    public static final String CARD_NOT_ACTIVE = "card-not-active";
    public static final String INSUFFICIENT_LIMIT = "insufficient-limit";
    /**
     * Attributes of class
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name = "ACTIVE_CARD")
    private boolean activeCard;
    @Column(name = "AVAILABLE_LIMIT")
    private int availableLimit;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    /**
     * Getter and settter
     */
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public boolean getActiveCard() {
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

    public List<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }
    /**
     * Methods
     */
    /**
     * Method that drecrease available limit of an account
     * @param amount
     */
    public void decreaseAmount(int amount){
        this.availableLimit = this.availableLimit - amount;
    }

    /**
     * Method that verify whether an account have similars transactions in a
     * 2 minutes interval
     * @param localDateTimeLimit1
     * @param localDateTimeLimit2
     * @param amount
     * @param merchant
     * @return
     */
    public boolean haveSimilarTransactions(LocalDateTime localDateTimeLimit1, LocalDateTime localDateTimeLimit2, int amount, String merchant){
        LocalDateTime localDateTimeLimit1Converted =localDateTimeLimit1.plusMinutes(-2);
        boolean haveSimilar = false;
        int i =transactions.size();
        for ( ; i>0 && !haveSimilar ; ){
            if(haveSimilar = transactions.get(i-1).getTime().isAfter(
                    localDateTimeLimit1Converted) &&
                    transactions.get(i-1).getTime().isBefore(localDateTimeLimit2)
                    && transactions.get(i-1).getAmount()==amount && transactions.get(i-1).getMerchant().equalsIgnoreCase(merchant)){
                haveSimilar = true;
            }
            i--;
        }
        return haveSimilar;
    }

}
