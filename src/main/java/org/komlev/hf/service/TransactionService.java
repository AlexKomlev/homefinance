package org.komlev.hf.service;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.komlev.hf.dao.AccountDao;
import org.komlev.hf.dao.TransactionDao;
import org.komlev.hf.domain.AccountTypeE;
import org.komlev.hf.domain.DirectionE;
import org.komlev.hf.domain.Transaction;
import org.komlev.hf.domain.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Description.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 03.08.2014
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

    public List<TransactionType> getTransactionTypes(DirectionE direction) {
        Criterion filter = null;
        if (direction == null) {
            LOGGER.info("Retrieve all types");
        } else {
            LOGGER.info("Retrieve for {}", direction);
            filter = Restrictions.eq("direction", direction);
        }
        return transactionDao.getTransactionTypes(filter);
    }


    public List<Transaction> getTransactions() {
//        LOGGER.info("Retrieve all types for direction {}", direction);
        return transactionDao.getTransactions();
    }

    public void createTransaction(Date transactionDate, Long transactionType, Long sourceAccount, Long destAccount,
                                  Long amount, String description) {
        Assert.notNull(transactionDate, "Transaction date should be defined");
        Assert.notNull(transactionType, "Transaction type should be defined");
        Assert.isTrue(amount != null && amount > 0, "Amount should be greater than 0");
        Assert.isTrue(sourceAccount != null || destAccount != null, "Source or destination account should be set");

        Long streetId = accountService.getAccount(AccountTypeE.STREET.name()).getId();

        Long destAccId = destAccount == null ? streetId : destAccount;
        Long sourceAccId = sourceAccount == null ? streetId : sourceAccount;;

        //todo add check for account current balance

        Transaction transaction = new Transaction();
        transaction.setTransactionDate(transactionDate);
        transaction.setType(transactionDao.getTransactionType(transactionType));
        transaction.setSourceAccount(accountService.getAccount(sourceAccId));
        transaction.setDestinationAccount(accountService.getAccount(destAccId));
        transaction.setTransactionValue(amount);
        transaction.setDescription(description);
        Long result = transactionDao.createTransaction(transaction);

    }

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("/spring/appContext.xml");
        TransactionService ts = (TransactionService) context.getBean("transactionService");
        List<TransactionType> tt = ts.getTransactionTypes(DirectionE.INCOMING); //
        System.out.println(tt.size());
    }
}
