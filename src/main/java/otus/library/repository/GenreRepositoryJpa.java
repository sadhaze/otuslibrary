package otus.library.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import otus.library.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class GenreRepositoryJpa implements GenreRepository {
    @PersistenceContext
    private EntityManager em;

    public GenreRepositoryJpa(){}

    public Long count(){
        return em.createQuery("select count(g) from Genre g", Long.class).getSingleResult();
    }

    public void insert(Genre genre){
        em.persist(genre);
    }

    public Genre getById(Long id){
        return em.find(Genre.class, id);
    }

    public List<Genre> getAll(){
        return em.createQuery("select g from Genre g", Genre.class).getResultList();
    }
}
