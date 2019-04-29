package otus.library.repository;

import org.springframework.data.repository.CrudRepository;
import otus.library.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
