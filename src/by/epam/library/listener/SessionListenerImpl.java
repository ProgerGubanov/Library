package by.epam.library.listener;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

@WebListener
public class SessionListenerImpl implements HttpSessionAttributeListener {
    private static Logger logger = Logger.getLogger(SessionListenerImpl.class);

    public void attributeRemoved(HttpSessionBindingEvent ev) {
        logger.info("Listener remove: " + ev.getClass().getSimpleName() + " : " + ev.getName()
                + " : " + ev.getValue());
    }

    public void attributeAdded(HttpSessionBindingEvent ev) {
        logger.info("Listener add: " + ev.getClass().getSimpleName() + " : " + ev.getName()
                + " : " + ev.getValue());
    }

    public void attributeReplaced(HttpSessionBindingEvent ev) {
        logger.info("Listener replace: " + ev.getClass().getSimpleName() + " : " + ev.getName()
                + " : " + ev.getValue());
    }
}