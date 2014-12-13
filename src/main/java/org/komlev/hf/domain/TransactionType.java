package org.komlev.hf.domain;

import javax.persistence.*;

/**
 * Transaction Type entity.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 28.11.2014
 */
@Entity
@Table(name = "TRANSACTION_TYPES")
public class TransactionType {

    private Long id;

    private String name;

    private DirectionE direction;

    private String description;

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    @Id
    @GeneratedValue
    @Column(name="ID")
    public Long getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    @Column(name="NAME")
    public String getName() {
        return name;
    }

    /**
     * Setter for property 'name'.
     *
     * @param name Value to set for property 'name'.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for property 'direction'.
     *
     * @return Value for property 'direction'.
     */
    @Column(name = "DIRECTION")
    @Enumerated(EnumType.ORDINAL)
    public DirectionE getDirection() {
        return direction;
    }

    /**
     * Setter for property 'direction'.
     *
     * @param direction Value to set for property 'direction'.
     */
    public void setDirection(DirectionE direction) {
        this.direction = direction;
    }

    /**
     * Getter for property 'description'.
     *
     * @return Value for property 'description'.
     */
    @Column(name="DESCRIPTION")
    public String getDescription() {
        return description;
    }

    /**
     * Setter for property 'description'.
     *
     * @param description Value to set for property 'description'.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
