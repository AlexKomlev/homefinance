package org.komlev.hf.web;

import org.komlev.hf.domain.Account;
import org.komlev.hf.domain.TableData;
import org.komlev.hf.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Description.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 01.06.2014
 */
@Controller
@RequestMapping("/account")
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
    public List<Account> getAllAccounts() { //HttpServletRequest request
//        String echo = request.getParameter("sEcho");
//        int iecho = Integer.parseInt(echo);
        List<Account> accounts = accountService.getAccounts();
        return accounts;
    }

}
