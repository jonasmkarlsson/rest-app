package se.jmkit.rest.service.spring.datajpa.service;


/**
 * This exception is thrown if the wanted entity is not found.
 * 
 * @author Jonas M Karlsson
 */
public class EntityNotFoundException extends Exception {

    private static final long serialVersionUID = -3487208416654357425L;

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

//    public EntityNotFoundException(Logger logger, String name, Long id) {
//        this();
//        logEntityNotFoundException(logger, name, id);
//    }
//
//    public void logEntityNotFoundException(Logger logger, String name, Long id) {
//        if (logger.isDebugEnabled()) {
//            logger.debug(getMessage(name, id), this);
//        } else {
//            logger.info(getMessage(name, id));
//        }
//    }
//
//    public String getMessageIdCouldNotBeFound(String name, Long id) {
//        return name + " with id '" + id + "' could not be found.";
//    }

}
