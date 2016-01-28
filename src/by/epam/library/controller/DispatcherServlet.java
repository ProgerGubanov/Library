package by.epam.library.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

import by.epam.library.service.impl.ServiceFactoryImpl;
import by.epam.library.action.Action;
import by.epam.library.action.ActionManager;
import by.epam.library.action.ActionManagerFactory;
import by.epam.library.action.ActionManagerFactoryImpl;
import by.epam.library.dao.mysql.DaoFactoryImpl;
import by.epam.library.dao.pool.ConnectionPool;
import by.epam.library.exception.PersistentException;

/**
 * Сервлет
 *
 * @author Gubanov Andrey
 */

public class DispatcherServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(DispatcherServlet.class);

    public static final String DB_CONFIG_FILE_NAME = "/db_config.properties";
    public static final String LOG_FILE_NAME = "log.txt";
    public static final Level LOG_LEVEL = Level.ALL;
    public static final String LOG_MESSAGE_FORMAT = "%n%d%n%p\t%C.%M:%L%n%m%n";

    /**
     * Инициализация сервлета
     */
    public void init() {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = getClass().getResourceAsStream(DB_CONFIG_FILE_NAME);
            properties.load(input);
            String dbDriverClass = properties.getProperty("dbDriverClass");
            String dbUrl = properties.getProperty("dbUrl");
            String dbUser = properties.getProperty("dbUser");
            String dbPassword = properties.getProperty("dbPassword");
            int dbPoolStartSize = Integer.parseInt(properties.getProperty("dbPoolStartSize"));
            int dbPoolMaxSize = Integer.parseInt(properties.getProperty("dbPoolMaxSize"));
            int dbPoolCheckConnectionTimeout = Integer.parseInt(properties.getProperty("dbPoolCheckConnectionTimeout"));
            Logger root = Logger.getRootLogger();
            Layout layout = new PatternLayout(LOG_MESSAGE_FORMAT);
            root.addAppender(new FileAppender(layout, LOG_FILE_NAME, true));
            root.addAppender(new ConsoleAppender(layout));
            root.setLevel(LOG_LEVEL);
            ConnectionPool.getInstance().init(dbDriverClass, dbUrl, dbUser, dbPassword, dbPoolStartSize, dbPoolMaxSize, dbPoolCheckConnectionTimeout);
        } catch (PersistentException | IOException e) {
            logger.error("It is impossible to initialize application", e);
            destroy();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error("It is impossible to initialize application", e);
                }
            }
        }
    }

    /**
     * Получение фабрики ActionManager
     *
     * @return ActionManagerFactory
     * @throws PersistentException
     */
    public ActionManagerFactory getFactory() throws PersistentException {
        return new ActionManagerFactoryImpl(new ServiceFactoryImpl(new DaoFactoryImpl()));
    }

    /**
     * Выполнение Get
     *
     * @param request  запрос
     * @param response ответ
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    /**
     * Выполнение Post
     *
     * @param request  запрос
     * @param response ответ
     * @throws IOException
     * @throws ServletException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    /**
     * Обработка запросов
     *
     * @param request  запрос
     * @param response ответ
     * @throws IOException
     * @throws ServletException
     */
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
