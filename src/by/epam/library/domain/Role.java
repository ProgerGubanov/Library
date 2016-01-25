package by.epam.library.domain;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public enum Role {
    ADMINISTRATOR("Администратор"),
    LIBRARIAN("Библиотекарь"),
    READER("Читатель");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public static Role getByIdentity(Integer identity) {
        return Role.values()[identity];
    }
}