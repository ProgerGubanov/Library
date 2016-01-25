package by.epam.library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import by.epam.library.service.ServiceFactoryImpl;
import by.epam.library.action.Action;
import by.epam.library.action.ActionManager;
import by.epam.library.action.ActionManagerFactory;
import by.epam.library.action.ActionManagerFactoryImpl;
import by.epam.library.dao.mysql.DaoFactoryImpl;
import by.epam.library.dao.pool.ConnectionPool;
import by.epam.library.exception.PersistentException;

/**
 * Created by Gubanov Andrey on 12.01.2016.
 */

public class DispatcherServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(DispatcherServlet.class);

    public static final String LOG_FILE_NAME = "log.txt";
    public static final Level LOG_LEVEL = Level.ALL;
    public static final String LOG_MESSAGE_FORMAT = "%n%d%n%p\t%C.%M:%L%n%m%n";

    public static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/library?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    public static final String DB_USER = "library_user";
    public static final String DB_PASSWORD = "library_password";
    public static final int DB_POOL_START_SIZE = 10;
    public static final int DB_POOL_MAX_SIZE = 1000;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

    public void init() {
        try {
            Logger root = Logger.getRootLogger();
            Layout layout = new PatternLayout(LOG_MESSAGE_FORMAT);
            root.addAppender(new FileAppender(layout, LOG_FILE_NAME, true));
            root.addAppender(new ConsoleAppender(layout));
            root.setLevel(LOG_LEVEL);
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
        } catch (PersistentException | IOException e) {
            logger.error("It is impossible to initialize application", e);
            destroy();
        }
    }

    public ActionManagerFactory getFactory() throws PersistentException {
        return new ActionManagerFactoryImpl(new ServiceFactoryImpl(new DaoFactoryImpl()));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Action action = (Action) request.getAttribute("action");
        try {
            ActionManager actionManager = getFactory().getManager();
            Action.Forward forward = actionManager.execute(action, request, response);
            actionManager.close();
            String requestedUri = request.getRequestURI();
            if (forward != null && forward.isRedirect()) {
                String redirectedUri = request.getContextPath() + forward.getForward();
                logger.debug(String.format("Request for URI \"%s\" id redirected to URI \"%s\"", requestedUri, redirectedUri));
                response.sendRedirect(redirectedUri);
            } else {
                String jspPage;
                if (forward != null) {
                    jspPage = forward.getForward();
                } else {
                    jspPage = action.getName() + ".jsp";
                }
                jspPage = "/WEB-INF/jsp" + jspPage;
                logger.debug(String.format("Request for URI \"%s\" is forwarded to JSP \"%s\"", requestedUri, jspPage));
                getServletContext().getRequestDispatcher(jspPage).forward(request, response);
            }
        } catch (PersistentException e) {
            logger.error("It is impossible to process request", e);
            request.setAttribute("error", "Ошибка обработки данных");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
        }
    }
}
