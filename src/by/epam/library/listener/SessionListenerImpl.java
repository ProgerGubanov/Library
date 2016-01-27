package by.epam.library.listener;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Gubanov Andrey on 26.01.2016.
 */

public class SessionListenerImpl implements HttpSessionListener {
    private static Logger logger = Logger.getLogger(SessionListenerImpl.class);

    private static int totalActiveSessions;

    public static int getTotalActiveSession(){
        return totalActiveSessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        totalActiveSessions++;
        logger.info("Session created. Total active session: " + getTotalActiveSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        totalActiveSessions--;
        logger.info("Session destroyed. Total active session: " + getTotalActiveSession());
    }
}
