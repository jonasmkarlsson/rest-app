package se.jmkit.rest.it.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.jonasmkarlsson.generatedata.Generate;
import org.jonasmkarlsson.generatedata.column.AbstractColumn;
import org.jonasmkarlsson.generatedata.column.Firstname;
import org.jonasmkarlsson.generatedata.column.Lastname;
import se.jmkit.rest.client.http.RestURIBuilder;
import se.jmkit.rest.client.http.controller.PersonControllerClient;
import se.jmkit.rest.client.http.controller.TeamControllerClient;
import se.jmkit.rest.common.constants.Constant;
import se.jmkit.rest.common.entity.Person;
import se.jmkit.rest.common.entity.Team;

/**
 * An abstract base class for all IT controller tests.
 * 
 * @author Jonas M Karlsson
 */
public abstract class AbstractControllerItTest {

    private static String schema = "http";
    private static String host = "localhost:8080";
    // private static String baseUrl = "/jmkit-app-rest/rest";
    private static String baseUrl = "/rest";

    public static RestURIBuilder restURIBuilder = new RestURIBuilder(schema, host, baseUrl);

    public static PersonControllerClient personControllerClient = new PersonControllerClient(restURIBuilder, Constant.TABLE_PERSON);
    public static TeamControllerClient teamControllerClient = new TeamControllerClient(restURIBuilder, Constant.TABLE_TEAM);

    public static Person[] persons;
    public static Team[] teams;

    public final static int NO_OF_PERSONS = 20;
    public final static int NO_OF_TEAMS = 10;

    protected abstract void beforeTest();

    protected abstract void afterTest();

    @BeforeClass
    public static void beforeClass() {
        persons = generatePersons(NO_OF_PERSONS);
        teams = generateTeams(NO_OF_TEAMS);

        createPersonsInRestService();
        createTeamsInRestService();
    }

    @AfterClass
    public static void afterClass() {
        deletePersonsInRestService();
        deleteTeamsInRestService();
    }

    @Before
    public void before() {
        beforeTest();
    }

    @After
    public void after() {
        afterTest();
    }

    private static void createPersonsInRestService() {
        for (Person person : persons) {
            personControllerClient.create(person);
        }
    }

    private static void createTeamsInRestService() {
        for (Team team : teams) {
            teamControllerClient.create(team);
        }
    }

    private static void deletePersonsInRestService() {
        for (Person person : persons) {
            personControllerClient.delete(person.getId());
        }
    }

    private static void deleteTeamsInRestService() {
        for (Team team : teams) {
            teamControllerClient.delete(team.getId());
        }
    }

    private static Person[] generatePersons(int count) {
        AbstractColumn[] firstnameColumn = { new Firstname("") };
        AbstractColumn[] lastnameColumn = { new Lastname("") };
        Person[] persons = new Person[count];
        String firstnames[] = Generate.list(firstnameColumn, count, "");
        String middlenames[] = Generate.list(firstnameColumn, count, "");
        String lastnames[] = Generate.list(lastnameColumn, count, "");
        for (int i = 0; i < count; i++) {
            persons[i] = new Person(new Long(i + 1), firstnames[i], middlenames[i], lastnames[i]);
        }
        return persons;
    }

    private static Team[] generateTeams(int count) {
        Team[] teams = new Team[count];
        AbstractColumn[] firstnameColumn = { new Firstname("") };
        String teamnames[] = Generate.list(firstnameColumn, count, "");
        for (int i = 0; i < count; i++) {
            teams[i] = new Team(new Long(i + 1), "Team " + teamnames[i]);
        }
        return teams;
    }

}
