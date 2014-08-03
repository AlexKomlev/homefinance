package org.komlev.hf.dao;

import org.komlev.hf.domain.TransactionDirection;
import org.komlev.hf.domain.TransactionType;

import java.util.List;

/**
 * Description.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 03.08.2014
 */
public interface TransactionDao {

    List<TransactionType> getTransactionTypes(TransactionDirection direction);

    TransactionType getTransactionType(Long ttId);
}
