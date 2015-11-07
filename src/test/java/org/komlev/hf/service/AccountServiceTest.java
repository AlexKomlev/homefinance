package org.komlev.hf.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.komlev.hf.domain.Account;
import org.komlev.hf.tools.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Test for {@link org.komlev.hf.service.AccountService}.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 13.12.2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/db-config-test.xml"})
public class AccountServiceTest {

    @Autowired
    private DBHelper dbHelper;

    @Autowired
    private AccountService accountService;

    @Test
    public void testAllAccounts() {
        dbHelper.initDB();
        assertTrue(accountService.getAccounts().size() == 4);

    }

    @Test
    public void testNullAccount() {
        dbHelper.initDB();
        assertNull(accountService.getAccount(999L));
        assertNull(accountService.getAccount("test"));

    }

    @Test
    public void testAccountById() {
        dbHelper.initDB();
        Account account = accountService.getAccount(1L);
        assertEquals("cash", account.getName());
    }

    @Test
    public void testAccountByName() {
        dbHelper.initDB();
        Account account = accountService.getAccount("citi_debit");
        assertEquals(2, account.getId().intValue());
    }
}
