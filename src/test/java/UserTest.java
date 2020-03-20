import model.restaurants.Coordinate;
import model.restaurants.Restaurant;
import model.users.AbstractUser;
import model.users.Owner;
import model.users.User;
import org.junit.After;
import org.junit.Assert;
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
}
