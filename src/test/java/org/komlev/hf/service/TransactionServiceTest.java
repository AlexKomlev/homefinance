package org.komlev.hf.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.komlev.hf.tools.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static junit.framework.TestCase.assertTrue;

/**
 * Description.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 15.02.2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/db-config-test.xml"})
public class TransactionServiceTest {

    @Autowired
    private DBHelper dbHelper;

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testAllAccounts() {
        dbHelper.initDB();
        transactionService.createTransaction(new Date(), 5L, 1L, 4L, 1000L, null);

    }
}
