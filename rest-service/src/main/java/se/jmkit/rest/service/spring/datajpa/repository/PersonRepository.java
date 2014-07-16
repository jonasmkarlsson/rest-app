package se.jmkit.rest.service.spring.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.jmkit.rest.common.entity.Person;

/**
 * Specifies methods used to obtain and modify person related information which is stored in the database.
 * 
 * @author Petri Kainulainen
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
