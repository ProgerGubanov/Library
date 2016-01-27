package by.epam.library.local;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Управление локализованными сообщениями
 */
public class MessageManager {
    /**
     * Имя файла локализации
     */
    public static final String LIBRARY_BUNDLENAME = "by.epam.library.local.messages";
    /**
     * Аттрибут локали
     */
    public static final String LOCALE_ATTRIBUTE = "javax.servlet.jsp.jstl.fmt.locale.session";
    private static MessageManager instance;
    private ResourceBundle resourceBundle;
    private Locale locale = null;

    /**
     * Создание экземпляра
     *
     * @param request запрос
     * @return экземпляр MessageManager
     */
    public static MessageManager getInstance(HttpServletRequest request) {
        Locale local = (Locale) request.getSession().getAttribute(LOCALE_ATTRIBUTE);
        if (instance == null || instance.locale != local) {
            instance = new MessageManager();
            instance.locale = local;
            if (local != null) {
                instance.resourceBundle = ResourceBundle.getBundle(LIBRARY_BUNDLENAME, local);
            } else {
                instance.resourceBundle = ResourceBundle.getBundle(LIBRARY_BUNDLENAME);
            }
        }
        return instance;
    }

    /**
     * Получение строки по ключу
     *
     * @param key ключ (имя параметра в файле)
     * @return String значение
     */
    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
