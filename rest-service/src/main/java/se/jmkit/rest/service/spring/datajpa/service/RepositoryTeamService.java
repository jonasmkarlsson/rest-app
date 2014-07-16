package se.jmkit.rest.service.spring.datajpa.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class RepositoryTeamService implements TeamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryTeamService.class);

    @Resource
    private TeamRepository entityRepository;

    @Transactional
    @Override
    public Team create(Team entity) {
        LOGGER.debug("Creating a new entity with information: " + entity);
        return entityRepository.save(entity);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class)
    @Override
    public Team delete(Long entityId) throws EntityNotFoundException {
        LOGGER.debug("Deleting entity with id: " + entityId);

        Team entityToDelete = entityRepository.findOne(entityId);

        if (entityToDelete == null) {
            LOGGER.debug("No entity found with id: " + entityId);
            throw new EntityNotFoundException();
        }

        entityRepository.delete(entityToDelete);
        return entityToDelete;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Team> findAll() {
        LOGGER.debug("Finding all entitys");
        return entityRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Team findById(Long id) {
        LOGGER.debug("Finding entity by id: " + id);
        return entityRepository.findOne(id);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class)
    @Override
    public Team update(Team entity) throws EntityNotFoundException {
        LOGGER.debug("Updating entity with information: " + entity);

        Team entityToUpdate = entityRepository.findOne(entity.getId());

        if (entityToUpdate == null) {
            LOGGER.debug("No entity found with id: " + entity.getId());
            throw new EntityNotFoundException();
        }

        entityToUpdate.update(entity);
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
