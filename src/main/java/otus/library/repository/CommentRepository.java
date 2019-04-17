package otus.library.repository;

import otus.library.domain.Comment;

import java.util.List;

public interface CommentRepository {
    Long count();
    void insert(Comment comment);
    Comment getById(Long id);
    List<Comment> getAll();
}
