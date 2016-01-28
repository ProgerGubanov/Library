package by.epam.library.domain;

import java.io.Serializable;

/**
 * Абстрактный класс сущности
 *
 * @author Gubanov Andrey
 */
abstract public class Entity implements Serializable {
    private Integer identity;

    /**
     * Получение уникального кода сущности
     *
     * @return код сущности
     */
    public Integer getIdentity() {
        return identity;
    }

    /**
     * Установка уникального кода сущности
     *
     * @param identity код сущности
     */
    public void setIdentity(Integer identity) {
        this.identity = identity;
    }
}
