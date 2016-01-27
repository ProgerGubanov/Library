package by.epam.library.domain;

/**
 * Created by Gubanov Andrey on 16.12.2015.
 */

/**
 * Список возможных ролей пользователя
 */
public enum Role {
    ADMINISTRATOR("Администратор"),
    LIBRARIAN("Библиотекарь"),
    READER("Читатель");

    private String name;

    Role(String name) {
        this.name = name;
    }

    /**
     * Получение наименования роли
     *
     * @return наименование роли
     */
    public String getName() {
        return name;
    }

    /**
     * Получение кода роли
     *
     * @return код роли
     */
    public Integer getIdentity() {
        return ordinal();
    }

    /**
     * Получение роли по ее коду
     *
     * @param identity код роли
     * @return Role роль
     */
    public static Role getByIdentity(Integer identity) {
        return Role.values()[identity];
    }
}