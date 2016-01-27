package by.epam.library.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.library.service.ServiceFactory;
import by.epam.library.domain.Role;
import by.epam.library.domain.User;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 10.12.2015.
 */


/**
 * Абстрактный класс действий
 */
abstract public class Action {

    private Set<Role> allowRoles = new HashSet<>();
    private User authorizedUser;
    private String name;

    protected ServiceFactory factory;

    /**
     * Получение списка ролей
     *
     * @return Set<Role> список ролей
     */
    public Set<Role> getAllowRoles() {
        return allowRoles;
    }

    /**
     * Получение авторизованного пользователя
     *
     * @return User информация о пользователе
     */
    public User getAuthorizedUser() {
        return authorizedUser;
    }

    /**
     * Установка авторизованного пользователя
     *
     * @param authorizedUser информация о пользователе
     */
    public void setAuthorizedUser(User authorizedUser) {
        this.authorizedUser = authorizedUser;
    }

    /**
     * Получение имени
     *
     * @return String имя
     */
    public String getName() {
        return name;
    }

    /**
     * Установка имени
     *
     * @param name имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Установка фабрики
     *
     * @param factory фабрика ServiceFactory
     */
    public void setFactory(ServiceFactory factory) {
        this.factory = factory;
    }

    /**
     * Выполнение перехода
     *
     * @param request  запрос
     * @param response ответ
     * @return Forward
     * @throws PersistentException
     */
    abstract public Action.Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException;

    /**
     * Класс перехода Forward
     */
    public static class Forward {
        private String forward;
        private boolean redirect;
        private Map<String, Object> attributes = new HashMap<>();

        /**
         * Переход Forward
         *
         * @param forward
         * @param redirect признак boolean пересылки redirect
         */
        public Forward(String forward, boolean redirect) {
            this.forward = forward;
            this.redirect = redirect;
        }

        /**
         * Конструктор
         *
         * @param forward
         */
        public Forward(String forward) {
            this(forward, true);
        }

        /**
         * Получение forward
         *
         * @return
         */
        public String getForward() {
            return forward;
        }

        /**
         * Установка forward
         *
         * @param forward
         */
        public void setForward(String forward) {
            this.forward = forward;
        }

        /**
         * Признак redirect
         *
         * @return
         */
        public boolean isRedirect() {
            return redirect;
        }

        /**
         * Установка redirect
         *
         * @param redirect
         */
        public void setRedirect(boolean redirect) {
            this.redirect = redirect;
        }

        /**
         * Получение аттрибутов
         *
         * @return Map<String, Object>
         */
        public Map<String, Object> getAttributes() {
            return attributes;
        }
    }
}
