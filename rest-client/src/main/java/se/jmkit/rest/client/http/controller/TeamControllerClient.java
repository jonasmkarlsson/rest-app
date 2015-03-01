package se.jmkit.rest.client.http.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import se.jmkit.rest.client.http.RestURIBuilder;
import se.jmkit.rest.common.controller.IController;
import se.jmkit.rest.common.entity.JSONWrapperEntity;
import se.jmkit.rest.common.entity.Team;

public class TeamControllerClient extends AbstractControllerClient implements IController<Team> {

    public TeamControllerClient(RestURIBuilder restURIBuilder, String entityURI) {
        super(restURIBuilder);
        this.entityURI = entityURI;
    }

    @Override
    public Team create(Team entity) {
        HttpEntity<Team> httpEntity = new HttpEntity<Team>(entity);
        ResponseEntity<Team> responseEntity = restTemplate.exchange(this.getURL(), HttpMethod.PUT, httpEntity, Team.class);
        return responseEntity.getBody();
    }

    @Override
    public Team read(Long id) {
        return restTemplate.getForObject(this.getURL() + "/" + id, Team.class);
    }

    @Override
    public Team update(Team entity) {
        return restTemplate.postForObject(this.getURL(), entity, Team.class);
    }

    @Override
    public Team delete(Long id) {
        Team entity = this.read(id);
        HttpEntity<Team> httpEntity = new HttpEntity<Team>(entity);
        ResponseEntity<Team> responseEntity = restTemplate.exchange(this.getURL() + "/" + id, HttpMethod.DELETE, httpEntity, Team.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Team> list() {
        return Arrays.asList(restTemplate.getForObject(this.getURL(), Team[].class));
    }

    @Override
    public JSONWrapperEntity<Team> jSONWrapperList() {
        return null;
    }

}
