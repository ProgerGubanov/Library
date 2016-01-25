package by.epam.library.domain;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

public enum BookStatus {
    INLIBRARY("В библиотеке"),
    INREADINGROOM("В читальном зале"),
    ONSUBSCRIPTION("На абонементе");

    private String name;

    BookStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public static BookStatus getByIdentity(Integer identity) {
        return BookStatus.values()[identity];
    }

}
