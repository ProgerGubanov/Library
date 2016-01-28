package by.epam.library.domain;

/**
 * Список возможных статусов книги
 *
 * @author Gubanov Andrey
 */
public enum BookStatus {
    INLIBRARY("В библиотеке"),
    INREADINGROOM("В читальном зале"),
    ONSUBSCRIPTION("На абонементе");

    private String name;

    BookStatus(String name) {
        this.name = name;
    }

    /**
     * Получение наименования статуса
     *
     * @return наименование статуса
     */
    public String getName() {
        return name;
    }

    /**
     * Получение кода статуса
     *
     * @return код статуса
     */
    public Integer getIdentity() {
        return ordinal();
    }

    /**
     * Получение наименования статуса по его коду
     *
     * @param identity код статуса
     * @return BookStatus статус книги
     */
    public static BookStatus getByIdentity(Integer identity) {
        return BookStatus.values()[identity];
    }

}
