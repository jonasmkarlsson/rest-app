package se.jmkit.rest.service.spring.datajpa.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class RepositoryPersonService implements PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryPersonService.class);

    @Resource
    private PersonRepository personRepository;

    @Transactional
    @Override
    public Person create(Person person) {
        LOGGER.debug("Creating a new person with information: " + person);
        return personRepository.save(person);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class)
    @Override
    public Person delete(Long personId) throws EntityNotFoundException {
        LOGGER.debug("Deleting person with id: " + personId);

        Person personToDelete = personRepository.findOne(personId);

        if (personToDelete == null) {
            LOGGER.debug("No person found with id: " + personId);
            throw new EntityNotFoundException();
        }

        personRepository.delete(personToDelete);
        return personToDelete;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Person> findAll() {
        LOGGER.debug("Finding all persons");
        return personRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Person findById(Long id) {
        LOGGER.debug("Finding person by id: " + id);
        return personRepository.findOne(id);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class)
    @Override
    public Person update(Person person) throws EntityNotFoundException {
        LOGGER.debug("Updating person with information: " + person);

        Person personToUpdate = personRepository.findOne(person.getId());

        if (personToUpdate == null) {
            LOGGER.debug("No person found with id: " + person.getId());
            throw new EntityNotFoundException();
        }

        personToUpdate.update(person);
        return personRepository.save(personToUpdate);
        // return personToUpdate;
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
