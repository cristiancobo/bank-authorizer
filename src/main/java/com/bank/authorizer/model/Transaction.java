package com.bank.authorizer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    /**
     * Constants
     */
    public static final String HIGH_FREQUENCY_SMALL_INTERVAL = "high-frequency-small-interval";
    public static final String DOUBLED_TRANSACTION = "doubled_transaction";

    /**
     * Attributes
     */
    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "MERCHANT", nullable = false)
    private String merchant;
    @Column(name = "AMOUNT", nullable = false)
    private int amount;
    @Column(name = "TIME", nullable = false)
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "account_id",nullable = false)
    private Account account;

    /**
     *
     * Getter and setter
     */

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Method that verify whether a transaction should be rejected
     * @param transactionN3
     * @return
     */
    public boolean isRejected(LocalDateTime transactionN3) {
        LocalDateTime localDateTimeTransactionN3=  transactionN3.plusMinutes(2);
        return time.isBefore(localDateTimeTransactionN3) ;
    }
}
