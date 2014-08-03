package org.komlev.hf.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.komlev.hf.dao.AccountDao;
import org.komlev.hf.domain.Account;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.util.List;

/**
 * Implementation of {@link org.komlev.hf.dao.AccountDao}.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 24.05.2014
 */
public class AccountDaoImpl extends HibernateDaoSupport implements AccountDao {

    @Override
    @SuppressWarnings("unchecked")
    /**
     * {@inheritDoc}
     */
    public List<Account> getAccounts() {
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List<Account> accounts = null;
        try {
            transaction.begin();
            accounts = session.createCriteria(Account.class).list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
        return accounts;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Account getAccount(Long id) {
        Account result = null;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            result = (Account) session.get(Account.class, id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return result;
    }
}
