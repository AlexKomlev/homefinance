package org.komlev.hf.service;

import org.komlev.hf.dao.TransactionDao;
import org.komlev.hf.domain.TransactionDirection;
import org.komlev.hf.domain.TransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public List<TransactionType> getTransactionTypes(TransactionDirection direction) {
        LOGGER.info("Retrieve all types for direction {}", direction);
        return transactionDao.getTransactionTypes(direction);
    }

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("/spring/appContext.xml");
        TransactionService ts = (TransactionService) context.getBean("transactionService");
        List<TransactionType> tts = ts.getTransactionTypes(TransactionDirection.R);
        System.out.println(tts.size());
    }
}
