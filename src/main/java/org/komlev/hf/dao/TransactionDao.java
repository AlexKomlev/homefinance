package org.komlev.hf.dao;

import org.hibernate.criterion.Criterion;
import org.komlev.hf.domain.Transaction;
import org.komlev.hf.domain.TransactionType;

import java.util.Date;
import java.util.List;

/**
 * Description.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 03.08.2014
 */
public interface TransactionDao {

    List<TransactionType> getTransactionTypes(Criterion filter);

    /**
     * .
     * @param ttId
     * @return
     */
    TransactionType getTransactionType(Long ttId);

    List<Transaction> getTransactions();

    Long createTransaction(Transaction transaction);
}
