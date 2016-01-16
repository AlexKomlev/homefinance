package org.komlev.hf.web;

import org.komlev.hf.domain.Account;
import org.komlev.hf.domain.DirectionE;
import org.komlev.hf.domain.TableData;
import org.komlev.hf.domain.TransactionType;
import org.komlev.hf.json.Transaction;
import org.komlev.hf.service.AccountService;
import org.komlev.hf.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public TableData<org.komlev.hf.domain.Transaction> getTransaction(@RequestParam("direction") String direction,
                                                                      HttpServletRequest request) {
        String echo = request.getParameter("draw");
        int draw = Integer.valueOf(echo);

        DirectionE directionE = DirectionE.valueOf(direction);
        List<org.komlev.hf.domain.Transaction> transactions =  transactionService.getTransactions(directionE);
        return new TableData<>(draw, transactions.size(), transactions.size(), transactions);
    }

    @RequestMapping(value = "types", method = RequestMethod.GET)
    @ResponseBody
    public List<TransactionType> getTransactionTypes(@RequestParam("direction") String direction) {
        DirectionE dirE = DirectionE.valueOf(direction);
        return transactionService.getTransactionTypes(dirE);
    }

}
