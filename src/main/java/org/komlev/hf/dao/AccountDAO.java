package org.komlev.hf.dao;

import org.komlev.hf.domain.Account;

import java.util.List;

/**
 * Interface for Accounting service.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 24.05.2014
 */
public interface AccountDao {

    /**
     * Returns all accounts.
     *
     * @return List of accounts.
     */
    List<Account> getAccounts();

    /**
     * Returns account by specified Id.
     *
     * @param id Account's Id.
     * @return Account object.
     */
    Account getAccount(Long id);
}
