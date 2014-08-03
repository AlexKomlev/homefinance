package org.komlev.hf.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SizeExpression;
import org.komlev.hf.dao.TransactionDao;
import org.komlev.hf.domain.TransactionDirection;
import org.komlev.hf.domain.TransactionType;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.util.List;

/**
 * Description.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 03.08.2014
 */
public class TransactionDaoImpl extends HibernateDaoSupport implements TransactionDao {

    @Override
    @SuppressWarnings("unchecked")
    /**
     * {@inheritDoc}
     */
    public List<TransactionType> getTransactionTypes(TransactionDirection direction) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List<TransactionType> transactionTypes = null;
        try {
            transaction.begin();
            if (direction == null) {
                transactionTypes = session.createCriteria(TransactionType.class).list();
            } else {
                transactionTypes = session.createCriteria(TransactionType.class).add(Restrictions.eq("direction", direction)).list();
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return transactionTypes;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public TransactionType getTransactionType(Long ttId) {
        TransactionType result = null;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            result = (TransactionType) session.get(TransactionType.class, ttId);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return result;
    }
}
