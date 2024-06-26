package carmenromano.jpaApiD3.repositories;

import carmenromano.jpaApiD3.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByEmail(String email);

    boolean existsByEmail(String email);
}
