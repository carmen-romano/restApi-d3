package carmenromano.jpaApiD3.repositories;

import carmenromano.jpaApiD3.entities.Author;
import carmenromano.jpaApiD3.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogPostsRepository extends JpaRepository<Blog, Integer> {

}
