package by.epam.library.filters;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import by.epam.library.action.*;
import by.epam.library.action.admin.*;
import by.epam.library.action.librarian.*;
import by.epam.library.action.reader.*;
import org.apache.log4j.Logger;

/**
 * Created by Gubanov Andrey on 12.01.2016.
 */

/**
 * Фильтр по Uri
 */
public class ActionFromUriFilter implements Filter {
    private static Logger logger = Logger.getLogger(ActionFromUriFilter.class);

    private static Map<String, Class<? extends Action>> actions = new ConcurrentHashMap<>();

    static {
        actions.put("/", MainAction.class);
        actions.put("/index", MainAction.class);
        actions.put("/login", LoginAction.class);
        actions.put("/logout", LogoutAction.class);
        actions.put("/chooseLanguage", LocaleAction.class);

        actions.put("/profile/edit", ProfileEditAction.class);
        actions.put("/profile/save", ProfileSaveAction.class);

        actions.put("/user/list", UserListAction.class);
        actions.put("/user/edit", UserEditAction.class);
        actions.put("/user/save", UserSaveAction.class);
        actions.put("/user/delete", UserDeleteAction.class);

        actions.put("/reader/requestlist", BookRequestListAction.class);
        actions.put("/reader/orderlist", BookOrderListAction.class);
        actions.put("/reader/requestdelete", BookRequestDeleteAction.class);
        actions.put("/reader/requestreadingroom", BookRequestAction.class);
        actions.put("/reader/requestsubscription", BookRequestAction.class);

        actions.put("/search/card/form", SearchCardFormAction.class);
        actions.put("/search/card/result", SearchCardResultAction.class);
        actions.put("/search/card/usages", CardUsageListAction.class);
        actions.put("/search/card/edit", LibrarianCardEditAction.class);
        actions.put("/search/card/save", LibrarianCardSaveAction.class);

        actions.put("/librarian/usages", LibrarianUserInfoAction.class);
        actions.put("/librarian/bookreturn", LibrarianBookReturnAction.class);
        actions.put("/librarian/bookissue", LibrarianBookIssueAction.class);
        actions.put("/librarian/booksave", LibrarianBookSaveAction.class);

        actions.put("/search/reader/form", SearchReaderFormAction.class);
        actions.put("/search/reader/result", SearchReaderResultAction.class);
    }

    /**
     * Bybwbfkbpfwbz
     *
     * @param filterConfig конфигурация
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Выполнение фильтрации
     *
     * @param request  запрос
     * @param response ответ
     * @param chain    цепочка
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String contextPath = httpRequest.getContextPath();
            String uri = httpRequest.getRequestURI();
            logger.debug(String.format("Starting of processing of request for URI \"%s\"", uri));
            int beginAction = contextPath.length();
            int endAction = uri.lastIndexOf('.');
            String actionName;
            if (endAction >= 0) {
                actionName = uri.substring(beginAction, endAction);
            } else {
                actionName = uri.substring(beginAction);
            }
            Class<? extends Action> actionClass = actions.get(actionName);
            try {
                Action action = actionClass.newInstance();
                action.setName(actionName);
                httpRequest.setAttribute("action", action);
                chain.doFilter(request, response);
            } catch (InstantiationException | IllegalAccessException | NullPointerException e) {
                logger.error("It is impossible to create action handler object", e);
                httpRequest.setAttribute("error", String.format("Запрошенный адрес %s не может быть обработан сервером", uri));
                httpRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
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
