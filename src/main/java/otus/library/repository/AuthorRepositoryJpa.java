package otus.library.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import otus.library.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class AuthorRepositoryJpa implements AuthorRepository {
    @PersistenceContext
    private EntityManager em;

    public AuthorRepositoryJpa(){}

    public Long count(){
        return em.createQuery("select count(a) from Author a", Long.class).getSingleResult();
    }

    public void insert(Author author){
        em.persist(author);
    }

    public Author getById(Long id){
        return em.find(Author.class, id);
    }

    public List<Author> getAll(){
        return em.createQuery("select a from Author a", Author.class).getResultList();

    }
}
