package otus.library.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import otus.library.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @EntityGraph("comment-entity-graph")
    List<Comment> findAll();
}
