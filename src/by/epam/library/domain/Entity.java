package by.epam.library.domain;

import java.io.Serializable;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

abstract public class Entity implements Serializable {
    private Integer identity;

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }
}
