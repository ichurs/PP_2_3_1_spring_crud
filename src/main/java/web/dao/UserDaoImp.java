package web.dao;

import web.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private  EntityManager entityManager;
    Logger logger = Logger.getLogger(UserDaoImp.class.getName());

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
        logger.info("User with ID: " + user.getId() + " successfully added.");
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
        logger.info("User with ID: " + user.getId() + " successfully updated.");
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        User user = getUserById(id);
        if (user != null) {
            entityManager.remove(user);
            logger.info("User with ID: " + id + " successfully removed.");
        }
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user", User.class);
        return query.getResultList();
    }
}
