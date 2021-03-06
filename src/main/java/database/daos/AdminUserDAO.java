package database.daos;

import model.restaurants.Coordinate;
import model.restaurants.Restaurant;
import model.users.*;
import model.users.interfaces.IAdminUser;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Objects;

public class AdminUserDAO implements IAdminUser {
    private EntityManager manager;

    public AdminUserDAO(EntityManager manager) {
        Objects.requireNonNull(manager, "EntityManager shouldn't be null");
        this.manager = manager;
    }

    public AdminUser createAdminUser(String login, String password) {
        AdminUser adminUser = new AdminUser(login, password);

        manager.getTransaction().begin();

        try {
            manager.persist(adminUser);
        } catch (Throwable cause) {
            manager.getTransaction().rollback();
            throw cause;
        }

        manager.getTransaction().commit();

        return adminUser;
    }

    public AdminUser findAdminByLogin(String login) {
        try {
            return (AdminUser) manager.createQuery("SELECT user from AbstractUser user WHERE user.login = :loginToSearch", AdminUser.class)
                    .setParameter("loginToSearch", login)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }
    }

    @Override
    public void deleteUser(AbstractUser user) {
        manager.getTransaction().begin();
        System.out.println(manager.createQuery("DELETE FROM AbstractUser user WHERE user.login = : login")
                .setParameter("login", user.getLogin())
                .executeUpdate());

        if (user instanceof Owner) {
            ((Owner) user).getRestaurants().forEach(this::deleteRestaurant);
        }

        manager.getTransaction().commit();
    }

    @Override
    public void deleteRestaurant(Restaurant restaurant) {
        Owner owner = restaurant.getOwner();

        manager.getTransaction().begin();
        manager.createQuery("DELETE FROM Restaurant r WHERE r.coordinate = :coordinate")
                .setParameter("coordinate", restaurant.getCoordinate())
                .executeUpdate();
        manager.getTransaction().commit();

        owner.getRestaurants().remove(restaurant);
    }

    @Override
    public void deleteComment(Restaurant restaurant, String comment) {
        manager.getTransaction().begin();
        manager.createQuery("UPDATE Restaurant r SET r.comments = :comment WHERE r.id = :restaurantId", Restaurant.class)
                .setParameter("comment", restaurant.getComments())
                .setParameter("restaurantId", restaurant.getId())
                .executeUpdate();
        manager.getTransaction().commit();

        restaurant.getComments().removeComment(comment);
    }

    @Override
    public void changeUserStatus(AbstractUser user, EUserStatus newUserStatus) {
        manager.createQuery("UPDATE AbstractUser user SET user.userStatus = :newUserStatus WHERE user.id = :userId", AbstractUser.class)
                .setParameter("newUserStatus", newUserStatus)
                .setParameter("userId", user.getId())
                .executeUpdate();

        user.setUserStatus(newUserStatus);
    }

    @Override
    public void changeRestaurantCoordinate(Restaurant restaurant, Coordinate newCoordinate) {
        manager.createQuery("UPDATE Restaurant r SET r.coordinate = :newCoordinate WHERE r.id = :restaurantId", Restaurant.class)
                .setParameter("newCoordinate", newCoordinate)
                .setParameter("restaurantId", restaurant.getId())
                .executeUpdate();

        restaurant.setCoordinate(newCoordinate);
    }
}
