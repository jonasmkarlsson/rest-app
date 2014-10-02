package se.jmkit.rest.service.spring.datajpa.service;

import org.apache.log4j.Logger;

/**
 * An abstract service class which provides utility methods useful to actual service classes.
 * 
 * @author Jonas M Karlsson
 */
public abstract class BaseService<T> {

    public final Logger logger = Logger.getLogger(this.getClass());

    public void logIsDebugEnabled(final String message) {
        if (logger.isDebugEnabled()) {
            logger.debug(message);
        }
    }
}
