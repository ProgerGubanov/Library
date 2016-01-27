package by.epam.library.action;

import java.io.Serializable;

/**
 * Created by Gubanov Andrey on 12.01.2016.
 */

/**
 * Получение пункта меню
 */
public class MenuItem implements Serializable {
    private String url;
    private String name;

    /**
     * Конструктор
     *
     * @param url  адрес
     * @param name наименование
     */
    public MenuItem(String url, String name) {
        this.url = url;
        this.name = name;
    }

    /**
     * Получить адрес
     *
     * @return String адрес (url)
     */
    public String getUrl() {
        return url;
    }

    /**
     * Получить имя
     *
     * @return String имя (название) пункта меню
     */
    public String getName() {
        return name;
    }
}
