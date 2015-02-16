package org.komlev.hf.web;

import org.komlev.hf.domain.Account;
import org.komlev.hf.json.Transaction;
import org.komlev.hf.service.AccountService;
import org.komlev.hf.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Description.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 01.06.2014
 */
@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public Long createTransaction(Transaction transaction) {
        return transactionService.createTransaction(transaction.getDate(), transaction.getType(),
                transaction.getSource(), transaction.getDestination(), transaction.getAmount(),
                transaction.getDescription());
    }

}
