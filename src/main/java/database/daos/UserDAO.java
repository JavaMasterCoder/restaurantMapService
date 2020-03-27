package database.daos;

import model.users.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Objects;

public class UserDAO {
    private EntityManager manager;

    public UserDAO(EntityManager manager) {
        Objects.requireNonNull(manager, "EntityManager shouldn't be null");
        this.manager = manager;
    }

    public User createUser(String login, String password, String name) {
        User user = new User(login, password, name);

        manager.getTransaction().begin();

        try {
            manager.persist(user);
        } catch (Throwable cause) {
            manager.getTransaction().rollback();
            throw  cause;
        }

        manager.getTransaction().commit();

        return user;
    }

    public User findUserByLogin(String login) {
        try {
            return (User) manager.createQuery("SELECT user from AbstractUser user WHERE user.login = :loginToSearch", User.class)
                    .setParameter("loginToSearch", login)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }
    }
}
