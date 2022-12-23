package web.dao;

import web.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

@Repository
public class UserDaoImp implements UserDao{

    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static Map<Integer, User> users = new HashMap<>();

    static {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        user1.setId(AUTO_ID.getAndIncrement());
        user1.setFirstName("Ivan");
        user1.setLastName("Ivanov");
        user1.setPosition("Sales manager");

        user2.setId(AUTO_ID.getAndIncrement());
        user2.setFirstName("Petr");
        user2.setLastName("Petrov");
        user2.setPosition("CEO");

        user3.setId(AUTO_ID.getAndIncrement());
        user3.setFirstName("Barbra");
        user3.setLastName("Streisand");
        user3.setPosition("Singer");

        users.put(user1.getId(), user1);
        users.put(user2.getId(), user2);
        users.put(user3.getId(), user3);
    }

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
//        TypedQuery<User> query = entityManager.createQuery("SELECT user FROM User user", User.class);
//        return query.getResultList();
        return new ArrayList<>(users.values()); //временно
    }
}
