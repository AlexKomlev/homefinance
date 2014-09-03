package org.komlev.hf.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Description.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 03.09.2014
 */
@Entity
@Table(name = "TRANSACTIONS")
public class FinTransaction {

    private Long id;

    private Account deliverAccount;

    private Account receiveAccount;

    private TransactionType transactionType;

    private Date transactionDate;

    private Long transactionValue;

    private String description;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "TRANSACTION_DELIVER_ACCOUNT")
    public Account getDeliverAccount() {
        return deliverAccount;
    }

    public void setDeliverAccount(Account deliverAccount) {
        this.deliverAccount = deliverAccount;
    }

    @ManyToOne
    @JoinColumn(name = "TRANSACTION_RECEIVE_ACCOUNT")
    public Account getReceiveAccount() {
        return receiveAccount;
    }

    public void setReceiveAccount(Account receiveAccount) {
        this.receiveAccount = receiveAccount;
    }

    @ManyToOne
    @JoinColumn(name = "TRANSACTION_TYPE")
    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Column(name = "TRANSACTION_DATE")
    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Column(name = "TRANSACTION_VALUE")
    public Long getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(Long transactionValue) {
        this.transactionValue = transactionValue;
    }

    @Column(name = "TRANSACTION_DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}