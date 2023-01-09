package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void addUser(User user) {
        this.userDao.addUser(user);
    }

    @Transactional
    @Override
    public void updateUser(int id, User user) {
        this.userDao.updateUser(id, user);
    }

    @Transactional
    @Override
    public void removeUser(int id) {
        this.userDao.removeUser(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(int id) {
        return this.userDao.getUserById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return this.userDao.listUsers();
    }
}
