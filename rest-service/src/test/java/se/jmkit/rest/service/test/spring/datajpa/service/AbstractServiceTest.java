package se.jmkit.rest.service.test.spring.datajpa.service;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.jmkit.rest.common.entity.Person;
import se.jmkit.rest.common.entity.Team;
import se.jmkit.rest.service.spring.datajpa.service.PersonService;
import se.jmkit.rest.service.spring.datajpa.service.TeamService;
import se.jmkit.rest.service.test.spring.datajpa.context.TestContext;

/**
 * An abstract base class for all service unit tests.
 * 
 * @author Jonas M Karlsson
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class })
public abstract class AbstractServiceTest {

    public static Person[] persons;
    public static Team[] teams;

    public final static int NO_OF_PERSONS = 6;
    public final static int NO_OF_TEAMS = 10;

    @Resource
    protected PersonService personService;

    @Resource
    protected TeamService teamService;

    protected abstract void beforeTest();

    protected abstract void afterTest();

    @BeforeClass
    public static void beforeClass() {

    }

    @AfterClass
    public static void afterClass() {

    }

    @Before
    public void before() {
        beforeTest();
    }

    @After
    public void after() {
        afterTest();
    }

    // private static void createPersonsInRestService() {
    // for (Person person : persons) {
    // personService.create(person);
    // }
    // }
    //
    // private static void createTeamsInRestService() {
    // for (Team team : teams) {
    // teamService.create(team);
    // }
    // }
    //
    // private static void deletePersonsInRestService() throws EntityNotFoundException {
    // for (Person person : persons) {
    // personService.delete(person.getId());
    // }
    // }
    //
    // private static void deleteTeamsInRestService() throws EntityNotFoundException {
    // for (Team team : teams) {
    // teamService.delete(team.getId());
    // }
    // }
    //
    // private static Person[] generatePersons(int count) {
    // Person[] persons = new Person[count];
    // String firstnames[] = generate.getRandom(FieldType.FIRSTNAME, count, "src/test/resources/firstnames.txt");
    // String lastnames[] = generate.getRandom(FieldType.LASTNAME, count, "src/test/resources/lastnames.txt");
    // for (int i = 0; i < count; i++) {
    // persons[i] = new Person(new Long(i + 1), firstnames[i], lastnames[i]);
    // }
    // return persons;
    // }
    //
    // private static Team[] generateTeams(int count) {
    // Team[] teams = new Team[count];
    // String firstnames[] = generate.getRandom(FieldType.FIRSTNAME, count, "src/test/resources/firstnames.txt");
    // for (int i = 0; i < count; i++) {
    // teams[i] = new Team(new Long(i + 1), "Team " + firstnames[i]);
    // }
    // return teams;
    // }

}
