package se.jmkit.rest.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import se.jmkit.rest.common.constants.Constant;

@Entity
@Table(name = Constant.PERSON)
@JsonPropertyOrder({ "id", "firstname", "middlename", "lastname", "name" })
public class Person extends AbstractEntity<Person> {

    private static final long serialVersionUID = -309290308183957151L;
    private String firstname;
    private String middlename;
    private String lastname;

    /**
     * Default constructor
     */
    public Person() {
        super();
    }

    /**
     * Constructor creating person from values.
     * 
     * @param firstname the first name of Person
     * @param middlename the middle name of Person
     * @param lastname the last name of Person
     */
    public Person(String firstname, String middlename, String lastname) {
        this();
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
    }

    /**
     * This should only be used by tests. Not for production.
     * 
     * @param id the id of entity
     * @see #Person(String, String, String, String, String)
     */
    public Person(final Long id, final String firstname, final String middlename, final String lastname) {
        this(firstname, middlename, lastname);
        this.setId(id);
    }

    @Override
    public void update(Person entity) {
        update(entity.getFirstname(), entity.getMiddlename(), entity.getLastname());
    }

    public void update(final String firstname, final String middlename, final String lastname) {
        this.setFirstname(firstname);
        this.setMiddlename(middlename);
        this.setLastname(lastname);
    }

    public String getName() {
        StringBuilder name = new StringBuilder();
        if (StringUtils.isNotEmpty(firstname)) {
            name.append(firstname);
        }
        
        if (StringUtils.isNotEmpty(middlename)) {
            name.append(" " + middlename);
        }
        
        if (StringUtils.isNotEmpty(lastname)) {
            name.append(" " + lastname);
        }
        return name.toString().trim();
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the middlename
     */
    public String getMiddlename() {
        return middlename;
    }

    /**
     * @param middlename the middlename to set
     */
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
