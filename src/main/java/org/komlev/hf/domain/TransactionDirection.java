package org.komlev.hf.domain;

/**
 * Transaction direction types.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 03.08.2014
 */
public enum TransactionDirection {
    I("Incoming"),
    O("Outgoing"),
    T("Transfer");

    private final String description;

    TransactionDirection(String description) {
        this.description = description;
    }

    /**
     * Getter for property 'description'.
     *
     * @return Value for property 'description'.
     */
    public String getDescription() {
        return description;
    }
}
