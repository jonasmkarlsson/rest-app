package se.jmkit.rest.service.spring.datajpa.controller;

import se.jmkit.rest.common.entity.AbstractEntity;

/**
 * An abstract controller class which provides utility methods useful to actual controller classes.
 * 
 * @author Jonas M Karlsson
 */
public abstract class AbstractController<T extends AbstractEntity<T>> {

    public String getMessageIdCouldNotBeFound(Long id) {
        return this.getClass().getName() + " with id '" + id + "' could not be found.";
    }

}
