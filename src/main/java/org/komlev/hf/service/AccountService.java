package org.komlev.hf.service;

import org.komlev.hf.dao.AccountDao;
import org.komlev.hf.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 01.06.2014
 */
@Service
public class AccountService {

    private static Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @Resource
    private AccountDao accountDao;


    public List<Account> getAccounts(){
        return accountDao.getAccounts();
    }

    public Account getAccount(Long id){
        Assert.notNull(id, "Id is empty");
        return accountDao.getAccount(id);
    }

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("/spring/appContext.xml");
        AccountService as = (AccountService) context.getBean("accountService");
        List<Account> acc = as.getAccounts();
        System.out.println(acc.size());
//        Account account = as.getAccount(0L);
//        System.out.println(account.getDescription());

    }
}
