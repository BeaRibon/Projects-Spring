package udemy.exer.exem1.repositories;

import udemy.exer.exem1.model.v1.entities.PersonV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonV1, Long> {
}
