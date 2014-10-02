package se.jmkit.rest.service.spring.datajpa.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.jmkit.rest.common.entity.Team;
import se.jmkit.rest.service.spring.datajpa.repository.TeamRepository;

/**
 * This implementation of the TService interface communicates with the database by using a Spring Data JPA repository.
 * 
 * @author Jonas M Karlsson
 */
@Service
public class RepositoryTeamService extends BaseService<Team> implements TeamService {

    @Resource
    private TeamRepository entityRepository;

    @Transactional
    @Override
    public Team create(Team team) {
        logIsDebugEnabled("Creating a new team with information: " + team);
        return entityRepository.save(team);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class)
    @Override
    public Team delete(Long id) throws EntityNotFoundException {
        logIsDebugEnabled("Deleting team with id: " + id);
        Team entityToDelete = entityRepository.findOne(id);

        if (entityToDelete == null) {
            logIsDebugEnabled("Team with id '" + id + "' could not be found.");
            throw new EntityNotFoundException();
        }
        logIsDebugEnabled("Deleting team with id: " + id);
        entityRepository.delete(entityToDelete);
        return entityToDelete;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Team> findAll() {
        logIsDebugEnabled("Finding all teams");
        return entityRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Team findById(Long id) {
        logIsDebugEnabled("Finding team with id: '" + id + "'");
        return entityRepository.findOne(id);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class)
    @Override
    public Team update(Team team) throws EntityNotFoundException {
        logIsDebugEnabled("Updating team with information: " + team);

        Team entityToUpdate = entityRepository.findOne(team.getId());

        if (entityToUpdate == null) {
            logIsDebugEnabled("Team with id '" + team.getId() + "' could not be found.");
            throw new EntityNotFoundException();
        }

        entityToUpdate.update(team);
        return entityRepository.save(entityToUpdate);
    }

    /**
     * This setter method should be used only by unit tests.
     * 
     * @param entityRepository
     */
    public void setEntityRepository(TeamRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

}
