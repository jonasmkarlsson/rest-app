package se.jmkit.rest.common.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import se.jmkit.rest.common.constants.Constant;

@Entity
@Table(name = Constant.TABLE_TEAM)
@JsonPropertyOrder({ "id", "name" })
public class Team extends AbstractEntity<Team> {

    private static final long serialVersionUID = -309290308183957151L;
    private String name;

    /**
     * Default constructor
     */
    public Team() {
        super();
    }

    /**
     * Constructor creating person from values.
     * 
     * @param firstname the first name of Person
     * @param middlename the middle name of Person
     * @param lastname the last name of Person
     */
    public Team(String name) {
        this();
        this.name = name;
    }

    /**
     * This should only be used by tests. Not for production.
     * 
     * @param id the id of entity
     * @see #Team(String)
     */
    public Team(Long id, String name) {
        this(name);
        this.setId(id);
    }

    @Override
    public void update(Team entity) {
        update(entity.getName());
    }

    public void update(String name) {
        this.setName(name);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param firstname the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
