package by.epam.library.action;

import java.io.Serializable;

/**
 * Created by Gubanov Andrey on 12.01.2016.
 */

public class MenuItem implements Serializable {
    private String url;
    private String name;

    public MenuItem(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
