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

}
