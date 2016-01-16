package org.komlev.hf.service;

import org.hibernate.criterion.Restrictions;
import org.komlev.hf.dao.TransactionDao;
import org.komlev.hf.domain.*;
import org.komlev.hf.validators.TransactionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Transaction Service.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 17.01.2016
 */
@Service
public class TransactionService {

    /**
     * Logger.
     */
    private static Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    /**
     * DAO.
     */
    @Resource
    private TransactionDao transactionDao;

    @Autowired
    private AccountService accountService;

    @Autowired
    private List<TransactionValidator> validators;


    /**
     * Returns Transaction types for specified direction.
     *
     * @param direction transactions' direction.
     * @return List of transaction types.
     */
    public List<TransactionType> getTransactionTypes(DirectionE direction) {
        HomeFilter filter = null;
        if (direction == null) {
            LOGGER.info("Retrieve all types");
        } else {
            filter = new HomeFilter();
            LOGGER.info("Retrieve for {}", direction);
            filter.setFilter(Restrictions.eq("direction", direction));
        }
        return transactionDao.getTransactionTypes(filter);
    }

    /**
     * Returns Transactions for specified direction.
     *
     * @param direction transactions' direction.
     * @return List of transactions.
     */
    public List<Transaction> getTransactions(DirectionE direction) {
        HomeFilter filter = null;
        if (direction == null) {
            LOGGER.info("Retrieve all types");
        } else {
            //TODO Add filters, sorts and pages
            LOGGER.info("Retrieve for {}", direction);
            filter = new HomeFilter();
            filter.setInitialAlias("trans");
            Map<String, String> aliases = new HashMap<>();
            aliases.put("trans.type", "type");
            filter.setAliases(aliases);
            filter.setFilter(Restrictions.eq("type.direction", direction));
        }
        return transactionDao.getTransactions(filter);
    }

    /**
     * Creates new Transaction.
     *
     * @param transactionDate Transaction Date.
     * @param transactionType Transaction Type.
     * @param sourceAccountId Source Account Id
     * @param destAccountId   Destination Account Id
     * @param amount          Amount
     * @param description     Description
     * @return TODO
     */
    public Long createTransaction(Date transactionDate, Long transactionType, Long sourceAccountId, Long destAccountId,
                                  Long amount, String description) {
        Assert.notNull(transactionDate, "Transaction date should be defined");
        Assert.notNull(transactionType, "Transaction type should be defined");
        Assert.isTrue(amount != null && amount > 0, "Amount should be greater than 0");
        Assert.isTrue(sourceAccountId != null || destAccountId != null, "Source or destination account should be set");

        List<Account> streetAccountList = accountService.findAccountByType(AccountTypeE.STREET);
        Assert.notEmpty(streetAccountList, "There are no street accounts.");
        Account streetAccount = streetAccountList.get(0);

        final Account sourceAccount = sourceAccountId != null ? accountService.getAccount(sourceAccountId) : null;
        Account destAccount = destAccountId != null ? accountService.getAccount(destAccountId) : null;

        validators.forEach((validator) -> validator.validate(sourceAccount));

        //todo add check for account current balance

        Transaction transaction = new Transaction();
        transaction.setTransactionDate(transactionDate);
        transaction.setType(transactionDao.getTransactionType(transactionType));
        transaction.setSourceAccount(sourceAccount == null ? streetAccount : sourceAccount);
        transaction.setDestinationAccount(destAccount == null ? streetAccount : destAccount);
        transaction.setTransactionValue(amount);
        transaction.setDescription(description);
        Long result = transactionDao.createTransaction(transaction);
        //todo return transaction status. Will be updated after validation implementation.
        return result;
    }

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("/spring/appContext.xml");
        TransactionService ts = (TransactionService) context.getBean("transactionService");
//        for(int i = 6; i < 17; i++)
        ts.createTransaction(new Date(), (long) 2, 1L, null, 10000L, null);
        System.exit(0);
    }
}
