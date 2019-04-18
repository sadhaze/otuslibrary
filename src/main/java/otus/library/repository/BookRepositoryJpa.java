package otus.library.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import otus.library.domain.Book;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class BookRepositoryJpa implements BookRepository {
    @PersistenceContext
    private EntityManager em;

    public BookRepositoryJpa(){}

    public Long count(){
        return em.createQuery("select count(b) from Book b", Long.class).getSingleResult();
    }

    public void insert(Book book){
        em.persist(book);
    }

    public Book getById(Long id){
        return em.find(Book.class, id);
    }

    public List<Book> getAll(){
        return em.createQuery("select b from Book b join fetch b.author join fetch b.genre", Book.class).getResultList();
    }
}
