package se.jmkit.rest.service.spring.datajpa.service;

import java.util.List;

import se.jmkit.rest.common.entity.Person;

/**
 * Declares methods used to obtain and modify person information.
 * 
 * @author Petri Kainulainen
 */
public interface PersonService {

    /**
     * Creates a new person.
     * 
     * @param person The information of the created person.
     * @return The created person.
     */
    Person create(Person person);

    /**
     * Deletes a person.
     * 
     * @param personId The id of the deleted person.
     * @return The deleted person.
     * @throws EntityNotFoundException if no person is found with the given id.
     */
    Person delete(Long personId) throws EntityNotFoundException;

    /**
     * Finds all persons.
     * 
     * @return A list of persons.
     */
    List<Person> findAll();

    /**
     * Finds person by id.
     * 
     * @param id The id of the wanted person.
     * @return The found person. If no person is found, this method returns null.
     */
    Person findById(Long id);

    /**
     * Updates the information of a person.
     * 
     * @param person The information of the updated person.
     * @return The updated person.
     * @throws EntityNotFoundException if no person is found with given id.
     */
    Person update(Person person) throws EntityNotFoundException;
}
