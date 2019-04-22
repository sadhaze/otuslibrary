package otus.library.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import otus.library.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CommentRepositoryJpa implements CommentRepository {
    @PersistenceContext
    private EntityManager em;

    public CommentRepositoryJpa(){}

    public Long count(){
        return em.createQuery("select count(c) from Comment c", Long.class).getSingleResult();
    }

    public void insert(Comment comment){
        em.persist(comment);
    }

    public Comment getById(Long id){
        return em.find(Comment.class, id);
    }

    public List<Comment> getAll(){
        return em.createQuery("select c from Comment c join fetch c.book join fetch c.user", Comment.class).getResultList();
    }
}
