package udemy.exer.exem1.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import udemy.exer.exem1.model.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
