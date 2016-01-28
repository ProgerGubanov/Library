package by.epam.library.listener;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Слушатель сессии
 *
 * @author Gubanov Andrey
 */
public class SessionListenerImpl implements HttpSessionListener {
    private static Logger logger = Logger.getLogger(SessionListenerImpl.class);

    private static int totalActiveSessions;

    /**
     * Определение количества активных сессий
     *
     * @return int количество активных сессий
     */
    public static int getTotalActiveSession() {
        return totalActiveSessions;
    }

    /**
     * Событие при создании сессии
     *
     * @param event HttpSessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        totalActiveSessions++;
        logger.info("Session created. Total active session: " + getTotalActiveSession());
    }

    /**
     * Событие при закрытии сессии
     *
     * @param event HttpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        totalActiveSessions--;
        logger.info("Session destroyed. Total active session: " + getTotalActiveSession());
    }
}
