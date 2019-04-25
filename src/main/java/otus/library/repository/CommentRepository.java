package otus.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.library.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
