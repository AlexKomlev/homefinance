package org.komlev.hf.service;

import org.komlev.hf.dao.AccountDao;
import org.komlev.hf.domain.Account;
import org.komlev.hf.domain.AccountTypeE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service that provides functionality of Accounts.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 01.06.2014
 */
@Service
public class AccountService {

    /**
     * Logger.
     */
    private static Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    /**
     * DAO.
     */
    @Resource
    private AccountDao accountDao;


    /**
     * Returns all accounts.
     *
     * @return List of accounts.
     */
    public List<Account> getAccounts() {
        LOGGER.info("Retrieve all accounts.");
        return accountDao.getAccounts();
    }

    /**
     * Returns of specified account by Id.
     *
     * @param id Account's Id
     * @return Account instance.
     */
    public Account getAccount(Long id) {
        Assert.notNull(id, "Id is empty");
        LOGGER.info("Retrieve account with id {}.", id);
        return accountDao.getAccount(id);
    }
    /**
     * Returns of specified account by Id.
     *
     * @param name Account's name
     * @return Account instance.
     */
    public Account getAccount(String name) {
        Assert.notNull(name, "Name is empty");
        LOGGER.info("Retrieve account with name {}.", name);
        return accountDao.getAccount(name);
    }

    public List<Account> findAccountByType(AccountTypeE accountTypeE) {
        Assert.notNull(accountTypeE, "Type is empty");
        LOGGER.info("Retrieve account with type {}.", accountTypeE);
        return accountDao.getAccounts(accountTypeE);

    }

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("/spring/appContext.xml");
        AccountService as = (AccountService) context.getBean("accountService");
        Account acc = as.getAccount("citi_debit");
        System.out.println(acc);
        List<Account> accounts = as.getAccounts();
        System.out.println(accounts.size());
    }
}
