package org.komlev.hf.validators;

import org.komlev.hf.domain.Account;
import org.komlev.hf.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 15.02.2015
 */
@Service
public class CashTransactionValidator implements TransactionValidator {

    @Autowired
    private AccountService accountService;

    public void validate(Account account){

    }
}
