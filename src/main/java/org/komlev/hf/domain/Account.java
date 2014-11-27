package org.komlev.hf.domain;

import javax.persistence.*;

/**
 * Accounts' entity.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 27.11.2014
 */
@Entity
@Table(name = "ACCOUNTS")
public class Account {

    private Long id;

    private String name;

    private AccountTypeE accountType;

    private Long creditRate;

    private Long accountLimit;

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
    @Column(name = "NAME")
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
    @Column(name = "TYPE")
    @Enumerated(EnumType.ORDINAL)
    public AccountTypeE getAccountType() {
        return accountType;
    }

    /**
     * Setter for property 'accountType'.
     *
     * @param accountType Value to set for property 'accountType'.
     */
    public void setAccountType(AccountTypeE accountType) {
        this.accountType = accountType;
    }

    /**
     * Getter for property 'creditRate'.
     *
     * @return Value for property 'creditRate'.
     */
    @Column(name = "CREDIT_RATE")
    public Long getCreditRate() {
        return creditRate;
    }

    /**
     * Setter for property 'creditRate'.
     *
     * @param creditRate Value to set for property 'creditRate'.
     */
    public void setCreditRate(Long creditRate) {
        this.creditRate = creditRate;
    }

    /**
     * Getter for property 'accountLimit'.
     *
     * @return Value for property 'accountLimit'.
     */
    @Column(name = "LIMIT")
    public Long getAccountLimit() {
        return accountLimit;
    }

    /**
     * Setter for property 'accountLimit'.
     *
     * @param accountLimit Value to set for property 'accountLimit'.
     */
    public void setAccountLimit(Long accountLimit) {
        this.accountLimit = accountLimit;
    }

    /**
     * Getter for property 'description'.
     *
     * @return Value for property 'description'.
     */
    @Column(name = "DESCRIPTION")
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
