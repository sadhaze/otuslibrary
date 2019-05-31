package otus.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.library.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
    void deleteAllByBookId(String id);
}
