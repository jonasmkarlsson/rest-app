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
import se.jmkit.generatedata.GenerateData;
import se.jmkit.rest.common.constants.Constant;
import se.jmkit.rest.common.controller.IController;
import se.jmkit.rest.common.entity.JSONWrapperEntity;
import se.jmkit.rest.common.entity.Team;
import se.jmkit.rest.service.spring.datajpa.service.EntityNotFoundException;
import se.jmkit.rest.service.spring.datajpa.service.TeamService;

/**
 * @author Jonas M Karlsson
 * 
 *         Every method has default @RequestMapping(produces = { MediaType.APPLICATION_JSON })
 */

@Controller
@RequestMapping(Constant.TEAM)
public class TeamController extends AbstractController<Team> implements IController<Team> {

    @Resource
    private TeamService teamService;

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Team read(@PathVariable Long id) {
        return teamService.findById(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON })
    @ResponseBody
    public Team create(@RequestBody Team entity) {
        return teamService.create(entity);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON })
    @ResponseBody
    public Team update(@RequestBody Team entity) {
        Team team = null;
        try {
            team = teamService.update(entity);
        } catch (EntityNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return team;
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Team delete(@PathVariable Long id) {
        Team team = null;
        try {
            team = teamService.delete(id);
        } catch (EntityNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return team;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Team> list() {
        return teamService.findAll();
    }

    @RequestMapping(value = "generate/{count}", method = RequestMethod.GET)
    @ResponseBody
    public List<Team> init(@PathVariable int count) {
        List<Team> teams = new ArrayList<Team>();
        String[] names = GenerateData.list(Field.LASTNAME, count);

        for (int i = 0; i < count; i++) {
            teams.add(teamService.create(new Team("Team " + names[i])));
        }
        return teams;
    }

    @RequestMapping(value = "export", method = RequestMethod.GET)
    @ResponseBody
    public void export() {

    }

    /**
     * This setter method should only be used by unit tests
     * 
     * @param teamService
     */
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public JSONWrapperEntity<Team> list2() {
        JSONWrapperEntity<Team> jSONWrapperEntity = new JSONWrapperEntity<Team>();
        jSONWrapperEntity.setEntities(this.list());
        jSONWrapperEntity.setClazz(Team.class.getName());
        return jSONWrapperEntity;
    }

}
