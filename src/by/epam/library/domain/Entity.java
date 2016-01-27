package by.epam.library.domain;

import java.io.Serializable;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

/**
 * Абстрактный класс сущности
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
