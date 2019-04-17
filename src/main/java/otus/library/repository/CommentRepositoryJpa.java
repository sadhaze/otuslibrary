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
        return new Long(em.createQuery("select c from Comment c", Comment.class).getResultList().size());
    }

    public void insert(Comment comment){
        em.persist(comment);
    }

    public Comment getById(Long id){
        return em.find(Comment.class, id);
    }

    public List<Comment> getAll(){
        return em.createQuery("select c from Comment c", Comment.class).getResultList();
    }
}
