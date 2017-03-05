package org.komlev.hf.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.komlev.hf.dao.AccountDao;
import org.komlev.hf.domain.Account;
import org.komlev.hf.domain.AccountTypeE;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * Implementation of {@link org.komlev.hf.dao.AccountDao}.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 24.05.2014
 */
public class AccountDaoImpl extends HibernateDaoSupport implements AccountDao {

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

    @SuppressWarnings("unchecked")
    public List<Account> getAccounts(AccountTypeE accountTypeE) {
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        List<Account> accounts = null;
        try {
            transaction.begin();
            accounts = session.createCriteria(Account.class).add(Restrictions.eq("accountType", accountTypeE)).list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        }
        return accounts;

    }

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

    /**
     * {@inheritDoc}
     */
    public Account getAccount(String name) {
        Account result = null;
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            result = (Account) session.createCriteria(Account.class).add(Restrictions.eq("name", name)).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return result;
    }
}
