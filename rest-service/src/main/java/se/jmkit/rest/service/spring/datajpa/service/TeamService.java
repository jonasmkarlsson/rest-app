package se.jmkit.rest.service.spring.datajpa.service;

import java.util.List;

import se.jmkit.rest.common.entity.Team;

/**
 * Declares methods used to obtain and modify team information.
 * 
 * @author Petri Kainulainen
 */
public interface TeamService {

    /**
     * Creates a new team.
     * 
     * @param team The information of the created team.
     * @return The created team.
     */
    Team create(Team team);

    /**
     * Deletes a team.
     * 
     * @param teamId The id of the deleted team.
     * @return The deleted team.
     * @throws EntityNotFoundException if no team is found with the given id.
     */
    Team delete(Long teamId) throws EntityNotFoundException;

    /**
     * Finds all teams.
     * 
     * @return A list of teams.
     */
    List<Team> findAll();

    /**
     * Finds team by id.
     * 
     * @param id The id of the wanted team.
     * @return The found team. If no team is found, this method returns null.
     */
    Team findById(Long id);

    /**
     * Updates the information of a team.
     * 
     * @param team The information of the updated team.
     * @return The updated team.
     * @throws EntityNotFoundException if no team is found with given id.
     */
    Team update(Team team) throws EntityNotFoundException;
}
