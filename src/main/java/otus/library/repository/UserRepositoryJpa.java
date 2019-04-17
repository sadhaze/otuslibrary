package otus.library.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import otus.library.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryJpa implements UserRepository {
    @PersistenceContext
    private EntityManager em;

    public UserRepositoryJpa(){}

    public Long count(){
        return new Long(em.createQuery("select u from User u", User.class).getResultList().size());
    }

    public void insert(User user){
        em.persist(user);
    }

    public User getById(String id){
        return em.find(User.class, id);
    }

    public List<User> getAll(){
        return em.createQuery("select u from User u", User.class).getResultList();
    }
}
