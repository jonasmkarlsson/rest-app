package se.jmkit.rest.service.spring.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.jmkit.rest.common.entity.Team;

/**
 * Specifies methods used to obtain and modify team related information which is stored in the database.
 * 
 * @author Jonas M Karlsson
 */
public interface TeamRepository extends JpaRepository<Team, Long> {
}
