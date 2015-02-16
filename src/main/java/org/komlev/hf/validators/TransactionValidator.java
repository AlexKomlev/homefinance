package org.komlev.hf.validators;

import org.komlev.hf.domain.Account;

/**
 * Description.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 15.02.2015
 */
public interface TransactionValidator {

    public void validate(Account account);

}
