package org.komlev.hf.domain;

import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;

/**
 * Description.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 24.05.2014
 */
@NamedQuery(
        name = "getAccounts",
        query = "from Account"
)
@Entity
@Table(name = "ACCOUNTS")
public class Account {

    private Long id;

    private String name;

    private AccountType accountType;

    private Integer debitRate;

    private Integer debitLimit;

    private String description;

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    @Column(name = "ACCOUNT_NAME")
    public String getName() {
        return name;
    }

    /**
     * Setter for property 'name'.
     *
     * @param name Value to set for property 'name'.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for property 'accountType'.
     *
     * @return Value for property 'accountType'.
     */
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_TYPE")
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * Setter for property 'accountType'.
     *
     * @param accountType Value to set for property 'accountType'.
     */
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    /**
     * Getter for property 'debitRate'.
     *
     * @return Value for property 'debitRate'.
     */
    @Column(name = "ACCOUNT_DEBIT_RATE")
    public Integer getDebitRate() {
        return debitRate;
    }

    /**
     * Setter for property 'debitRate'.
     *
     * @param debitRate Value to set for property 'debitRate'.
     */
    public void setDebitRate(Integer debitRate) {
        this.debitRate = debitRate;
    }

    /**
     * Getter for property 'debitLimit'.
     *
     * @return Value for property 'debitLimit'.
     */
    @Column(name = "ACCOUNT_DEBIT_LIMIT")
    public Integer getDebitLimit() {
        return debitLimit;
    }

    /**
     * Setter for property 'debitLimit'.
     *
     * @param debitLimit Value to set for property 'debitLimit'.
     */
    public void setDebitLimit(Integer debitLimit) {
        this.debitLimit = debitLimit;
    }

    /**
     * Getter for property 'description'.
     *
     * @return Value for property 'description'.
     */
    @Column(name = "ACCOUNT_DESCRIPTION")
    public String getDescription() {
        return description;
    }

    /**
     * Setter for property 'description'.
     *
     * @param description Value to set for property 'description'.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
