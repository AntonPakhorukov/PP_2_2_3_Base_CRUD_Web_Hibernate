package web.dao;

import org.springframework.stereotype.Repository;

import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(User user) {
    em.merge(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }
}
