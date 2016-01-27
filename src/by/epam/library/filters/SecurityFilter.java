package by.epam.library.filters;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.library.action.LocaleAction;
import by.epam.library.local.MessageManager;
import org.apache.log4j.Logger;

import by.epam.library.domain.Role;
import by.epam.library.domain.User;
import by.epam.library.action.Action;
import by.epam.library.action.MainAction;

/**
 * Created by Gubanov Andrey on 12.01.2016.
 */

/**
 * Фильтр безопасности
 */
public class SecurityFilter implements Filter {
    private static Logger logger = Logger.getLogger(SecurityFilter.class);

    /**
     * Инициализация
     *
     * @param filterConfig конфигурация
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Фильтрация
     *
     * @param request  запрос
     * @param response ответ
     * @param chain    цепочка
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            Action action = (Action) httpRequest.getAttribute("action");
            Set<Role> allowRoles = action.getAllowRoles();
            String userName = "unauthorized user";
            HttpSession session = httpRequest.getSession(false);
            User user = null;
            if (session != null) {
                user = (User) session.getAttribute("authorizedUser");
                action.setAuthorizedUser(user);
                String errorMessage = (String) session.getAttribute("SecurityFilterMessage");
                if (errorMessage != null) {
                    httpRequest.setAttribute("message", errorMessage);
                    session.removeAttribute("SecurityFilterMessage");
                }
            }
            boolean canExecute = allowRoles == null;
            if (user != null) {
                userName = "\"" + user.getLogin() + "\" user";
                canExecute = canExecute || allowRoles.contains(user.getRole());
            }
            if (action.getClass() == LocaleAction.class) {
                canExecute = true;
            }
            if (canExecute) {
                chain.doFilter(request, response);
            } else {
                logger.info(String.format("Trying of %s access to forbidden resource \"%s\"", userName, action.getName()));
                if (session != null && action.getClass() != MainAction.class) {
                    session.setAttribute("SecurityFilterMessage", MessageManager.getInstance(httpRequest).getProperty("message.accessDenied"));
                }
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
            }
        } else {
            logger.error("It is impossible to use HTTP filter");
            request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }

    /**
     * Уничтожение
     */
    @Override
    public void destroy() {
    }
}
