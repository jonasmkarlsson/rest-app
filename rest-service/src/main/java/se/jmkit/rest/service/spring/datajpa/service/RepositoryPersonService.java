package se.jmkit.rest.service.spring.datajpa.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.jmkit.rest.common.entity.Person;
import se.jmkit.rest.service.spring.datajpa.repository.PersonRepository;

/**
 * This implementation of the PersonService interface communicates with the database by using a Spring Data JPA repository.
 * 
 * @author Petri Kainulainen
 * @updated Jonas M Karlsson
 */
@Service
public class RepositoryPersonService extends BaseService<Person> implements PersonService {

    @Resource
    private PersonRepository personRepository;

    @Transactional
    @Override
    public Person create(Person person) {
        logIsDebugEnabled("Creating a new person with information: " + person);
        return personRepository.save(person);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class)
    @Override
    public Person delete(Long id) throws EntityNotFoundException {
        logIsDebugEnabled("Deleting person with id: " + id);

        Person personToDelete = personRepository.findOne(id);

        if (personToDelete == null) {
            logIsDebugEnabled("Person with id '" + id + "' could not be found.");
            throw new EntityNotFoundException();
        }
        
        logIsDebugEnabled("Deleting person with id: " + id);
        personRepository.delete(personToDelete);
        return personToDelete;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Person> findAll() {
        logIsDebugEnabled("Finding all persons");
        return personRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Person findById(Long id) {
        logIsDebugEnabled("Finding person with id: '" + id + "'");
        return personRepository.findOne(id);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class)
    @Override
    public Person update(Person person) throws EntityNotFoundException {
        logIsDebugEnabled("Updating person with information: " + person);
        Person personToUpdate = personRepository.findOne(person.getId());

        if (personToUpdate == null) {
            logIsDebugEnabled("Person with id '" + person.getId() + "' could not be found.");
            throw new EntityNotFoundException();
        }

        personToUpdate.update(person);
        return personRepository.save(personToUpdate);
    }

    /**
     * This setter method should be used only by unit tests.
     * 
     * @param personRepository
     */
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
