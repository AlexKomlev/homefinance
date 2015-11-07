package org.komlev.hf.web;

import org.komlev.hf.domain.Account;
import org.komlev.hf.domain.AccountTypeE;
import org.komlev.hf.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 01.06.2014
 */
@Controller
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    /**
     * Returns all accounts.
     *
     * @return List of accounts.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAllAccounts(HttpServletRequest request) { //HttpServletRequest request
        String echo = request.getParameter("sEcho");
        System.out.println(echo);
        return accountService.getAccounts();
    }

    @RequestMapping(value = "type", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAccountsByType(@RequestParam("type") String type) {
        AccountTypeE accountTypeE = AccountTypeE.valueOf(type);
        return accountService.findAccountByType(accountTypeE);
    }

    @RequestMapping(value = "name", method = RequestMethod.GET)
    @ResponseBody
    public Account getAccountByName(@RequestParam("name") String name) {
        return accountService.getAccount(name);
    }

    @RequestMapping(value = "id", method = RequestMethod.GET)
    @ResponseBody
    public Account getAccountByIdName(@RequestParam("id") Long id) {
        return accountService.getAccount(id);
    }

}
