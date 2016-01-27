package by.epam.library.listener;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Created by Gubanov Andrey on 22.01.2016.
 */

/**
 * Слушатель аттрибутов сессии
 */
@WebListener
public class SessionAttributeListenerImpl implements HttpSessionAttributeListener {
    private static Logger logger = Logger.getLogger(SessionAttributeListenerImpl.class);

    /**
     * Логирование удаления аттрибута
     *
     * @param event HttpSessionBindingEvent
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        logger.info("Attribute removed : " + event.getClass().getSimpleName() + " : " + event.getName()
                + " : " + event.getValue());
    }

    /**
     * Логирование добавления аттрибута
     *
     * @param event HttpSessionBindingEvent
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        logger.info("Attribute added : " + event.getClass().getSimpleName() + " : " + event.getName()
                + " : " + event.getValue());
    }

    /**
     * Логирование изменения значения аттрибута
     *
     * @param event HttpSessionBindingEvent
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        logger.info("Attribute replaced : " + event.getClass().getSimpleName() + " : " + event.getName()
                + " : " + event.getValue());
    }
}