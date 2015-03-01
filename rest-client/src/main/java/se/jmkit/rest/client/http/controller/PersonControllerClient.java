package se.jmkit.rest.client.http.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import se.jmkit.rest.client.http.RestURIBuilder;
import se.jmkit.rest.common.controller.IController;
import se.jmkit.rest.common.entity.JSONWrapperEntity;
import se.jmkit.rest.common.entity.Person;

public class PersonControllerClient extends AbstractControllerClient implements IController<Person> {

    public PersonControllerClient(RestURIBuilder restURIBuilder, String entityURI) {
        super(restURIBuilder);
        this.entityURI = entityURI;
    }

    @Override
    public Person create(Person entity) {
        HttpEntity<Person> httpEntity = new HttpEntity<Person>(entity);
        ResponseEntity<Person> responseEntity = restTemplate.exchange(this.getURL(), HttpMethod.PUT, httpEntity, Person.class);
        return responseEntity.getBody();
    }

    @Override
    public Person read(Long id) {
        return restTemplate.getForObject(this.getURL() + "/" + id, Person.class);
    }

    @Override
    public Person update(Person entity) {
        return restTemplate.postForObject(this.getURL(), entity, Person.class);
    }

    @Override
    public Person delete(Long id) {
        Person entity = this.read(id);
        HttpEntity<Person> httpEntity = new HttpEntity<Person>(entity);
        ResponseEntity<Person> responseEntity = restTemplate.exchange(this.getURL() + "/" + id, HttpMethod.DELETE, httpEntity, Person.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Person> list() {
        return Arrays.asList(restTemplate.getForObject(this.getURL(), Person[].class));
    }

    @Override
    public JSONWrapperEntity<Person> JSONWrapperList() {
        return null;
    }

}
