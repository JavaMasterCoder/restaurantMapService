package daos;

import database.daos.AdminUserDAO;
import model.users.AbstractUser;
import model.users.AdminUser;
import model.users.EUserStatus;
import model.users.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

public class AdminUserDAOTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private AdminUserDAO admins;

    @Before
    public void connect() {
        factory = Persistence.createEntityManagerFactory("TestPersistanceUnit");
        manager = factory.createEntityManager();
        admins = new AdminUserDAO(manager);
    }

    @After
    public void close() {
        if (manager != null) {
            manager.close();
        }

        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void createAdminUserTest() {
        String login = "adminTest";
        String password = "pass";
        AdminUser adminUser = admins.createAdminUser(login, password);

        assertNotNull(adminUser);
        assertEquals(login, adminUser.getLogin());
        assertEquals(password, adminUser.getPassword());

        AdminUser foundAdmin = manager.find(AdminUser.class, adminUser.getId());
        assertNotNull(foundAdmin);
        assertEquals(login, foundAdmin.getLogin());
        assertEquals(password, foundAdmin.getPassword());

        manager.refresh(foundAdmin);
    }

    @Test
    public void changeUserStatusTest() {
        String login = "adminTest1";
        String password = "pass1";
        AdminUser adminUser = admins.createAdminUser(login, password);

        String userLogin = "user";
        String userPassword = "password";
        String name = "name1";

        User user = new User(userLogin, userPassword, name);
        manager.getTransaction().begin();
        manager.persist(user);

        AbstractUser foundUser = manager.find(AbstractUser.class, user.getId());
        admins.changeUserStatus(foundUser, EUserStatus.REGISTERED);

        assertEquals(EUserStatus.REGISTERED, foundUser.getUserStatus());

        manager.refresh(foundUser);
        assertEquals(EUserStatus.REGISTERED, foundUser.getUserStatus());
    }

    @Test
    public void deleteUserTest() {
        String userLogin = "user";
        String userPassword = "password";
        String name = "name1";

        User user = new User(userLogin, userPassword, name);
        manager.getTransaction().begin();
        manager.persist(user);

        AbstractUser foundUser = manager.find(AbstractUser.class, user.getId());

        manager.getTransaction().commit();
        admins.deleteUser(foundUser);

        // Видимо, что-то не понял с EntityManager'ами
        List<AbstractUser> foundUsers = manager.createQuery("SELECT u FROM AbstractUser u WHERE u.id = :id", AbstractUser.class)
                .setParameter("id", user.getId())
                .getResultList();
        assertTrue(foundUsers.isEmpty());
    }
}
