package se.jmkit.rest.service.spring.datajpa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.jmkit.generatedata.Field;
import se.jmkit.generatedata.FieldParameter;
import se.jmkit.generatedata.GenerateData;
import se.jmkit.rest.common.constants.Constant;
import se.jmkit.rest.common.controller.IController;
import se.jmkit.rest.common.entity.JSONWrapperEntity;
import se.jmkit.rest.common.entity.Person;
import se.jmkit.rest.service.spring.datajpa.service.EntityNotFoundException;
import se.jmkit.rest.service.spring.datajpa.service.PersonService;

/**
 * @author Jonas M Karlsson
 * 
 *         Every method has default @RequestMapping(produces = { MediaType.APPLICATION_JSON })
 */

@Controller
@RequestMapping(Constant.PERSON)
public class PersonController extends AbstractController<Person> implements IController<Person> {

    @Resource
    private PersonService personService;

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Person read(@PathVariable Long id) {
        return personService.findById(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON })
    @ResponseBody
    public Person create(@RequestBody Person entity) {
        return personService.create(entity);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON })
    @ResponseBody
    public Person update(@RequestBody Person entity) {
        Person person = null;
        try {
            person = personService.update(entity);
        } catch (EntityNotFoundException e) {
            logIsDebugEnabled(getMessageIdCouldNotBeFound(entity.getId()));
            //e.logEntityNotFoundException(logger, this.getClass().getName(), entity.getId());
        }
        return person;
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Person delete(@PathVariable Long id) {
        Person person = null;
        try {
            person = personService.delete(id);
        } catch (EntityNotFoundException e) {
            logIsDebugEnabled(getMessageIdCouldNotBeFound(id));
            // e.logEntityNotFoundException(logger, this.getClass().getName(), id);
        }
        return person;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Person> list() {
        return personService.findAll();
    }

    @RequestMapping(value = "generate/{count}", method = RequestMethod.GET)
    @ResponseBody
    public List<Person> init(@PathVariable int count) {
        List<Person> persons = new ArrayList<Person>();
        String[] firstnames = GenerateData.list(new FieldParameter(Field.FIRSTNAME), count);
        String[] middlenames = GenerateData.list(new FieldParameter(Field.FIRSTNAME), count);
        String[] lastnames = GenerateData.list(new FieldParameter(Field.LASTNAME), count);
        for (int i = 0; i < count; i++) {
            persons.add(personService.create(new Person(firstnames[i], middlenames[i], lastnames[i])));
        }
        return persons;
    }

    @RequestMapping(value = "export", method = RequestMethod.GET)
    @ResponseBody
    public List<String> export() {
        List<Person> persons = this.list();
        List<String> sqls = new ArrayList<String>();
        for (Person person : persons) {
            String sql = "insert into person(id, firstname, lastname, creation_time, modification_time, version) values (" + person.getId() + ", '" + person.getFirstname()
                    + "', '" + person.getLastname() + "', '" + person.getCreationTime() + "', '" + person.getModificationTime() + "', " + person.getVersion();
            sqls.add(sql);
        }
        return sqls;

    }

    /**
     * This setter method should only be used by unit tests
     * 
     * @param personService
     */
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public JSONWrapperEntity<Person> list2() {
        JSONWrapperEntity<Person> jSONWrapperEntity = new JSONWrapperEntity<Person>();
        jSONWrapperEntity.setEntities(this.list());
        jSONWrapperEntity.setClazz(Person.class.getName());
        return jSONWrapperEntity;
    }

}
