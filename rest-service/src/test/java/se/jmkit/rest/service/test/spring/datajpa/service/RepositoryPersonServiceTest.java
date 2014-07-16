package se.jmkit.rest.service.test.spring.datajpa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import se.jmkit.rest.common.entity.Person;

public class RepositoryPersonServiceTest extends AbstractServiceTest {

    private String firstname = "Foo";
    private String middlename = "E";
    private String lastname = "Bar";

    private final int personIdGet = 1;
    private final int personIdUpdate = 2;
    private final int personIdDelete = 3;

    // @Resource
    // private PersonService personService;

    @Override
    protected void beforeTest() {
    }

    @Override
    protected void afterTest() {
    }

    @Test
    public void createTest() {
        Person person = personService.create(new Person(firstname, middlename, lastname));
        assertNotNull(person.getCreationTime());
        assertNotNull(person.getModificationTime());
        assertEquals(firstname, person.getFirstname());
        assertEquals(lastname, person.getLastname());
        assertEquals(0, person.getVersion());
    }

    // @Test
    // public void getTest() {
    // int arrayId = personIdGet - 1;
    // Person person = personService.findById(new Long(personIdGet));
    // assertEquals(persons[arrayId].getFirstname(), person.getFirstname());
    // assertEquals(persons[arrayId].getLastname(), person.getLastname());
    // }

    @Test
    public void listTest() {
        List<Person> persons = personService.findAll();
        assertEquals(NO_OF_PERSONS, persons.size());
    }

    @Test
    public void updateTest() throws se.jmkit.rest.service.spring.datajpa.service.EntityNotFoundException {
        // get person and save properties to use in test...
        Person person = personService.findById(new Long(personIdUpdate));
        Long personOldVersion = person.getVersion();

        // Update person using object method...
        person.update(new Person(firstname, middlename, lastname));

        // Update person in DB...
        Person updatedPerson = personService.update(person);

        // check that updatedPerson is correct...
        assertNotNull(updatedPerson);
        assertEquals(firstname, person.getFirstname());
        assertEquals(middlename, person.getMiddlename());
        assertEquals(lastname, person.getLastname());
        assertEquals(personOldVersion + 1, updatedPerson.getVersion());
        assertEquals(person.getCreationTime(), updatedPerson.getCreationTime());
        assertNotEquals(person.getModificationTime(), updatedPerson.getModificationTime());
        assertTrue(person.getModificationTime().getTime() < updatedPerson.getModificationTime().getTime());
    }

    // @Test
    // public void deleteTest() throws se.jmkit.rest.service.spring.datajpa.service.EntityNotFoundException {
    // int arrayId = personIdDelete - 1;
    //
    // Person personDeleted = personService.delete(new Long(personIdDelete));
    // assertEquals(persons[arrayId].getFirstname(), personDeleted.getFirstname());
    // assertEquals(persons[arrayId].getLastname(), personDeleted.getLastname());
    //
    // // The below should throw an exception because we have just deleted the person...
    // Person personNotFound = null;
    // try {
    // personService.delete(new Long(personIdDelete));
    // } catch (EntityNotFoundException e) {
    // assertTrue(true);
    // }
    // assertNull(personNotFound);
    //
    // // add person to DB
    // assertNotNull(personService.create(personDeleted));
    //
    // }

}
