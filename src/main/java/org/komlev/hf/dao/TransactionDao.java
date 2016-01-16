package org.komlev.hf.dao;

import org.komlev.hf.domain.HomeFilter;
import org.komlev.hf.domain.Transaction;
import org.komlev.hf.domain.TransactionType;

import java.util.List;

/**
 * Interface for Transaction DAO.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 17.01.2016
 */
public interface TransactionDao {

    /**
     * Returns Transaction types in accordance with filter.
     *
     * @param filter filter
     * @return List of transaction types.
     */
    List<TransactionType> getTransactionTypes(HomeFilter filter);

    /**
     * Returns Transaction type by Id.
     *
     * @param ttId Transaction type Id.
     * @return Transaction type
     */
    TransactionType getTransactionType(Long ttId);

    /**
     * Persist new transaction.
     *
     * @param transaction transaction.
     * @return TODO
     */
    Long createTransaction(Transaction transaction);

    /**
     * Returns list of transactions in accordance with filter.
     *
     * @param filter filter.
     * @return List of transactions.
     */
    List<Transaction> getTransactions(HomeFilter filter);

}
