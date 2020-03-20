import model.restaurants.Coordinate;
import model.restaurants.Restaurant;
import model.users.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserTest {
    private EntityManagerFactory factory;
    private EntityManager manager;

    @Before
    public void connect() {
        factory = Persistence.createEntityManagerFactory("TestPersistanceUnit");
        manager = factory.createEntityManager();
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
    public void createUserTest() {
        String login = "user1";
        String password = "password";
        String name = "name1";

        User user = new User(login, password, name);

        manager.getTransaction().begin();
        manager.persist(user);

        User foundUser = (User) manager.find(AbstractUser.class, user.getId());
        assertNotNull(foundUser);
        assertEquals(user.getLogin(), foundUser.getLogin());
        assertEquals(user.getName(), foundUser.getName());
        assertEquals(EUserStatus.IS_WAITING_TO_BE_REGISTERED_BY_ADMIN, foundUser.getUserStatus());
        assertEquals(user.getUserStatus(), foundUser.getUserStatus());

        manager.getTransaction().commit();

        manager.refresh(foundUser);
    }

    @Test
    public void createOwner() {
        String login = "owner1";
        String password = "pass2";
        String name = "name";

        Owner owner = new Owner(login, password, name);

        manager.getTransaction().begin();
        manager.persist(owner);

        Owner foundOwner = (Owner)manager.find(AbstractUser.class, owner.getId());
        assertNotNull(foundOwner);
        assertEquals(owner.getLogin(), foundOwner.getLogin());
        assertEquals(owner.getName(), foundOwner.getName());
        assertEquals(EUserStatus.IS_WAITING_TO_BE_REGISTERED_BY_ADMIN, foundOwner.getUserStatus());
        assertEquals(owner.getUserStatus(), foundOwner.getUserStatus());

        manager.getTransaction().commit();

        manager.refresh(foundOwner);
    }

    @Test
    public void createRestaurant() {
        String login = "owner1";
        String password = "pass2";
        String name = "name";

        Owner owner = new Owner(login, password, name);

        String restaurantName = "Ola-la";
        Coordinate coordinate = new Coordinate(new Double[] {70.0D, 50.0D});
        Restaurant restaurant = new Restaurant(restaurantName, owner, coordinate);

        manager.getTransaction().begin();
        manager.persist(owner);
        manager.persist(restaurant);

        Restaurant foundRestaurant = manager.find(Restaurant.class, restaurant.getId());
        assertNotNull(foundRestaurant);
        assertEquals(restaurant.getId(), foundRestaurant.getId());
        assertEquals(restaurant.getName(), foundRestaurant.getName());
        assertEquals(restaurant.getRating(), foundRestaurant.getRating());
        assertEquals(restaurant.getOwner().getLogin(), foundRestaurant.getOwner().getLogin());

        manager.getTransaction().commit();
        manager.refresh(foundRestaurant);
    }

    @Test
    public void createAdminUser() {
        String login = "admin1";
        String pass = "pass1";

        AdminUser adminUser = new AdminUser(login, pass);

        manager.getTransaction().begin();
        manager.persist(adminUser);

        AdminUser foundAdminUser = (AdminUser) manager.find(AbstractUser.class, adminUser.getId());
        assertNotNull(foundAdminUser);
        assertEquals(adminUser.getLogin(), foundAdminUser.getLogin());
        assertEquals(EUserStatus.REGISTERED, foundAdminUser.getUserStatus());
        assertEquals(adminUser.getUserStatus(), foundAdminUser.getUserStatus());

        manager.getTransaction().commit();
        manager.refresh(foundAdminUser);
    }

    @Test
    public void createUserAndOwnerInOneTableTest() {
        String userLogin = "userLogin";
        String userPass = "userPass";
        String userName = "userName";

        User user = new User(userLogin, userPass, userName);

        String ownerLogin = "ownerLogin";
        String ownerPass = "ownerPass";
        String ownerName = "ownerName";

        Owner owner = new Owner(ownerLogin, ownerPass, ownerName);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.persist(owner);

        User foundUser = (User)manager.find(AbstractUser.class, user.getId());
        assertNotNull(foundUser);
        assertEquals(user.getLogin(), foundUser.getLogin());
        assertEquals(user.getName(), foundUser.getName());
        assertEquals(EUserStatus.IS_WAITING_TO_BE_REGISTERED_BY_ADMIN, foundUser.getUserStatus());
        assertEquals(user.getUserStatus(), foundUser.getUserStatus());

        Owner foundOwner = (Owner)manager.find(AbstractUser.class, owner.getId());
        assertNotNull(foundOwner);
        assertEquals(owner.getLogin(), foundOwner.getLogin());
        assertEquals(owner.getName(), foundOwner.getName());
        assertEquals(EUserStatus.IS_WAITING_TO_BE_REGISTERED_BY_ADMIN, foundOwner.getUserStatus());
        assertEquals(owner.getUserStatus(), foundOwner.getUserStatus());

        manager.getTransaction().commit();

        manager.refresh(foundUser);
        manager.refresh(foundOwner);
    }

    @Test
    public void createUserAndAdminUser() {
        String userLogin = "userLogin";
        String userPass = "userPass";
        String userName = "userName";

        User user = new User(userLogin, userPass, userName);

        String login = "admin";
        String pass = "pass";

        AdminUser adminUser = new AdminUser(login, pass);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.persist(adminUser);

        User foundUser = (User)manager.find(AbstractUser.class, user.getId());
        assertNotNull(foundUser);
        assertEquals(user.getLogin(), foundUser.getLogin());
        assertEquals(user.getName(), foundUser.getName());
        assertEquals(EUserStatus.IS_WAITING_TO_BE_REGISTERED_BY_ADMIN, foundUser.getUserStatus());
        assertEquals(user.getUserStatus(), foundUser.getUserStatus());

        AdminUser foundAdminUser = (AdminUser) manager.find(AbstractUser.class, adminUser.getId());
        assertNotNull(foundAdminUser);
        assertEquals(adminUser.getLogin(), foundAdminUser.getLogin());
        assertEquals(EUserStatus.REGISTERED, foundAdminUser.getUserStatus());
        assertEquals(adminUser.getUserStatus(), foundAdminUser.getUserStatus());

        manager.getTransaction().commit();
        manager.refresh(user);
        manager.refresh(foundAdminUser);
    }

    @Test
    public void createOwnerAndAdminUser() {
        String ownerLogin = "ownerLogin";
        String ownerPass = "ownerPass";
        String ownerName = "ownerName";

        Owner owner = new Owner(ownerLogin, ownerPass, ownerName);

        String login = "admin";
        String pass = "pass";

        AdminUser adminUser = new AdminUser(login, pass);

        manager.getTransaction().begin();
        manager.persist(owner);
        manager.persist(adminUser);

        Owner foundOwner = (Owner)manager.find(AbstractUser.class, owner.getId());
        assertNotNull(foundOwner);
        assertEquals(owner.getLogin(), foundOwner.getLogin());
        assertEquals(owner.getName(), foundOwner.getName());
        assertEquals(EUserStatus.IS_WAITING_TO_BE_REGISTERED_BY_ADMIN, foundOwner.getUserStatus());
        assertEquals(owner.getUserStatus(), foundOwner.getUserStatus());

        AdminUser foundAdminUser = (AdminUser) manager.find(AbstractUser.class, adminUser.getId());
        assertNotNull(foundAdminUser);
        assertEquals(adminUser.getLogin(), foundAdminUser.getLogin());
        assertEquals(EUserStatus.REGISTERED, foundAdminUser.getUserStatus());
        assertEquals(adminUser.getUserStatus(), foundAdminUser.getUserStatus());

        manager.getTransaction().commit();
        manager.refresh(owner);
        manager.refresh(foundAdminUser);
    }

    @Test
    public void createAllUsers() {
        String userLogin = "userLogin";
        String userPass = "userPass";
        String userName = "userName";

        User user = new User(userLogin, userPass, userName);

        String ownerLogin = "ownerLogin";
        String ownerPass = "ownerPass";
        String ownerName = "ownerName";

        Owner owner = new Owner(ownerLogin, ownerPass, ownerName);

        String login = "admin";
        String pass = "pass";

        AdminUser adminUser = new AdminUser(login, pass);

        manager.getTransaction().begin();
        manager.persist(user);
        manager.persist(owner);
        manager.persist(adminUser);

        User foundUser = (User)manager.find(AbstractUser.class, user.getId());
        assertNotNull(foundUser);
        assertEquals(user.getLogin(), foundUser.getLogin());
        assertEquals(user.getName(), foundUser.getName());
        assertEquals(EUserStatus.IS_WAITING_TO_BE_REGISTERED_BY_ADMIN, foundUser.getUserStatus());
        assertEquals(user.getUserStatus(), foundUser.getUserStatus());

        Owner foundOwner = (Owner)manager.find(AbstractUser.class, owner.getId());
        assertNotNull(foundOwner);
        assertEquals(owner.getLogin(), foundOwner.getLogin());
        assertEquals(owner.getName(), foundOwner.getName());
        assertEquals(EUserStatus.IS_WAITING_TO_BE_REGISTERED_BY_ADMIN, foundOwner.getUserStatus());
        assertEquals(owner.getUserStatus(), foundOwner.getUserStatus());

        AdminUser foundAdminUser = (AdminUser) manager.find(AbstractUser.class, adminUser.getId());
        assertNotNull(foundAdminUser);
        assertEquals(adminUser.getLogin(), foundAdminUser.getLogin());
        assertEquals(EUserStatus.REGISTERED, foundAdminUser.getUserStatus());
        assertEquals(adminUser.getUserStatus(), foundAdminUser.getUserStatus());

        manager.getTransaction().commit();

        manager.refresh(foundUser);
        manager.refresh(foundOwner);
        manager.refresh(adminUser);
    }

}
