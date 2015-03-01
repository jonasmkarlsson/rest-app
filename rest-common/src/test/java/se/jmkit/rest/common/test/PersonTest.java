package se.jmkit.rest.common.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import se.jmkit.rest.common.entity.Person;

public class PersonTest {
    private final String LINE_BREAK = System.getProperty("line.separator");
    private final char TAB = '\t';

    private Long id = new Long(1);
    private String firstname = "firstname";
    private String middlename = "middlename";
    private String lastname = "lastname";

    private String uFirstname = "uFirstname";
    private String uMiddlename = "uMiddlename";
    private String uLastname = "uLastname";

    private static final String FIRST_NAME = "Foo";
    private static final String FIRST_NAME_UPDATED = "Foo1";

    private static final String MIDDLE_NAME = "E";
    private static final String MIDDLE_NAME_UPDATED = "E!";

    private static final String LAST_NAME = "Bar";
    private static final String LAST_NAME_UPDATED = "Bar1";

    private Person person2;
    private Person person;

    @Before
    public void before() {
        person = new Person(id, firstname, middlename, lastname);
        person2 = new Person(FIRST_NAME, MIDDLE_NAME, LAST_NAME);
    }

    @Test
    public void testUpdatePerson() {
        Person updatePerson = new Person(id, uFirstname, uMiddlename, uLastname);
        person.update(updatePerson);
        assertEquals(updatePerson, person);
    }

    @Test
    public void testGetProperties() {
        assertEquals(id, person.getId());
        assertEquals(firstname, person.getFirstname());
        assertEquals(middlename, person.getMiddlename());
        assertEquals(lastname, person.getLastname());
    }

    @Test
    public void build() {
        assertEquals(FIRST_NAME, person2.getFirstname());
        assertEquals(LAST_NAME, person2.getLastname());
        assertEquals(0, person2.getVersion());

        assertNull(person2.getCreationTime());
        assertNull(person2.getModificationTime());
        assertNull(person2.getId());
    }

    @Test
    public void getName() {
        String expectedName = constructName(FIRST_NAME, MIDDLE_NAME, LAST_NAME);
        assertEquals(expectedName, person2.getName());
    }

    @Test
    public void prePersist() {
        person2.prePersist();
        Date creationTime = person2.getCreationTime();
        Date modificationTime = person2.getModificationTime();
        assertNotNull(creationTime);
        assertNotNull(modificationTime);
        assertEquals(creationTime, modificationTime);
    }

    @Test
    public void preUpdate() {
        person2.prePersist();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Back to work
        }

        person2.preUpdate();

        Date creationTime = person2.getCreationTime();
        Date modificationTime = person2.getModificationTime();

        assertNotNull(creationTime);
        assertNotNull(modificationTime);
        assertTrue(modificationTime.after(creationTime));
    }

    @Test
    public void update() {
        person2.update(FIRST_NAME_UPDATED, MIDDLE_NAME_UPDATED, LAST_NAME_UPDATED);
        assertEquals(FIRST_NAME_UPDATED, person2.getFirstname());
        assertEquals(MIDDLE_NAME_UPDATED, person2.getMiddlename());
        assertEquals(LAST_NAME_UPDATED, person2.getLastname());
    }

    @Test
    public void testToString() {
        String expectedToString = "Person [firstname=" + person.getFirstname() + ",middlename=" + person.getMiddlename() + ",lastname=" + person.getLastname() + ",id="
                + person.getId() + "]";
        assertEquals(expectedToString, person.toString());
    }

    private String constructName(final String firstname, final String middlename, final String lastname) {
        StringBuilder name = new StringBuilder();
        name.append(firstname).append(" ").append(middlename).append(" ").append(lastname);
        return name.toString();
    }
}
