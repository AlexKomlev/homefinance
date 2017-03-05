package org.komlev.hf.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.komlev.hf.dao.TransactionDao;
import org.komlev.hf.domain.HomeFilter;
import org.komlev.hf.domain.Transaction;
import org.komlev.hf.domain.TransactionType;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Implementation of {@link TransactionDao}.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 17.01.2016
 */
public class TransactionDaoImpl extends HibernateDaoSupport implements TransactionDao {

    @SuppressWarnings("unchecked")
    /**
     * {@inheritDoc}
     */
    public List<TransactionType> getTransactionTypes(HomeFilter filter) {
        Session session = getSessionFactory().getCurrentSession();
        org.hibernate.Transaction transaction = session.getTransaction();
        List<TransactionType> transactionTypes = null;
        try {
            transaction.begin();
            if (filter == null || filter.getFilter() == null) {
                transactionTypes = session.createCriteria(TransactionType.class).list();
            } else {
                transactionTypes = session.createCriteria(TransactionType.class).add(filter.getFilter()).list();
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
            result = session.get(TransactionType.class, ttId);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Transaction> getTransactions(HomeFilter filter) {
        Session session = getSessionFactory().getCurrentSession();
        org.hibernate.Transaction transaction = session.getTransaction();
        List<Transaction> transactions = null;
        try {
            transaction.begin();
            if (filter == null) {
                transactions = session.createCriteria(Transaction.class).list();
            } else {
                Criteria criteria;

                if (StringUtils.isEmpty(filter.getInitialAlias())) {
                    criteria = session.createCriteria(Transaction.class);
                } else {
                    criteria = session.createCriteria(Transaction.class, filter.getInitialAlias());
                }
                filter.getAliases().forEach(criteria::createAlias);
                criteria.add(filter.getFilter());

                if (filter.getPageInfo() != null) {
                    criteria.setFirstResult(filter.getPageInfo().get("FirstResult"));
                    criteria.setMaxResults(filter.getPageInfo().get("MaxResults"));
                }
                transactions = criteria.list();

            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return transactions;
    }
}
