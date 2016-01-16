package org.komlev.hf.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.komlev.hf.domain.DirectionE;
import org.komlev.hf.domain.Transaction;
import org.komlev.hf.domain.TransactionType;
import org.komlev.hf.tools.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

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

    @Test
    public void testAllAccounts1() {
        dbHelper.initDB();
        transactionService.createTransaction(new Date(), 5L, 1L, 4L, 1000L, null);
        transactionService.createTransaction(new Date(), 5L, 1L, 4L, 2000L, null);
        List<Transaction> types = transactionService.getTransactions(DirectionE.OUTGOING);
        assertEquals(2, types.size());

    }
}
