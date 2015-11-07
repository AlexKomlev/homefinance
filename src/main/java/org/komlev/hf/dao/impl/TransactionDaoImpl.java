package org.komlev.hf.dao.impl;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.komlev.hf.dao.TransactionDao;
import org.komlev.hf.domain.Transaction;
import org.komlev.hf.domain.TransactionType;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.util.List;
import java.util.Optional;

/**
 * Description.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 03.08.2014
 */
public class TransactionDaoImpl extends HibernateDaoSupport implements TransactionDao {

    @SuppressWarnings("unchecked")
    /**
     * {@inheritDoc}
     */
    public List<TransactionType> getTransactionTypes(Criterion filter) {
        Session session = getSessionFactory().getCurrentSession();
        org.hibernate.Transaction transaction = session.getTransaction();
        List<TransactionType> transactionTypes = null;
        try {
            transaction.begin();
            if(filter == null) {
                transactionTypes = session.createCriteria(TransactionType.class).list();
            } else {
                transactionTypes = session.createCriteria(TransactionType.class).add(filter).list();
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return transactionTypes;
    }

    /**
     * {@inheritDoc}
     */
    public TransactionType getTransactionType(Long ttId) {
        TransactionType result = null;
        Session session = getSessionFactory().getCurrentSession();
        org.hibernate.Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            result = (TransactionType) session.get(TransactionType.class, ttId);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<Transaction> getTransactions() {
        Session session = getSessionFactory().getCurrentSession();
        org.hibernate.Transaction transaction = session.getTransaction();
        List<Transaction> transactions = null;
        try {
            transaction.begin();
            transactions = session.createCriteria(Transaction.class).list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return transactions;
    }

    public Long createTransaction(Transaction transaction) {
        Session session = getSessionFactory().getCurrentSession();
        org.hibernate.Transaction hbt = session.getTransaction();
        try {
            hbt.begin();
            session.save(transaction);
            hbt.commit();
        } catch (Exception e) {
            //todo add error log
            hbt.rollback();
            return -1L;
        }
        return transaction.getId();
    }
}
