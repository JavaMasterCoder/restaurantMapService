package database.daos;

import model.restaurants.Coordinate;
import model.restaurants.Restaurant;
import model.users.Owner;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Objects;

public class OwnerDAO {
    private EntityManager manager;

    public OwnerDAO(EntityManager manager) {
        Objects.requireNonNull(manager, "EntityManager shouldn't be null");
        this.manager = manager;
    }

    public Owner createOwner(String login, String password, String name) {
        Owner owner = new Owner(login, password, name);

        manager.getTransaction().begin();

        try {
            manager.persist(owner);
        } catch (Throwable cause) {
            manager.getTransaction().rollback();
            throw cause;
        }

        manager.getTransaction().commit();

        return owner;
    }

    public Owner findOwnerByLogin(String login, String password) {
        try {
            return (Owner) manager.createQuery("SELECT owner from AbstractUser owner WHERE owner.login = :loginToSearch AND owner.password = :password", Owner.class)
                    .setParameter("loginToSearch", login)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }
    }

    public Restaurant addRestaurant(String name, Owner owner, Coordinate coordinate) {
        Restaurant restaurant = new Restaurant(name, owner, coordinate);
        owner.getRestaurants().add(restaurant);

        manager.getTransaction().begin();
        try {
            manager.persist(owner);
            manager.persist(restaurant);
        } catch (Throwable cause) {
            manager.getTransaction().rollback();
            throw cause;
        }

        manager.getTransaction().commit();

        return restaurant;
    }

    public List<Restaurant> findRestaurantByName(String name) {
        try {
            return manager.createQuery("SELECT r from Restaurant  r WHERE r.name = :nameToSearch", Restaurant.class)
                    .setParameter("nameToSearch", name)
                    .getResultList();
        } catch (NoResultException cause) {
            return null;
        }
    }
}
