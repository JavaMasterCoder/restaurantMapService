package database.daos;

import model.restaurants.Coordinate;
import model.restaurants.Restaurant;
import model.users.AbstractUser;
import model.users.EUserStatus;
import model.users.Owner;
import model.users.interfaces.IAdminUser;

import javax.persistence.EntityManager;
import java.util.Objects;

public class AdminUserDAO implements IAdminUser {
    private EntityManager manager;

    public AdminUserDAO(EntityManager manager) {
        Objects.requireNonNull(manager, "EntityManager shouldn't be null");
        this.manager = manager;
    }

    @Override
    public void deleteUser(AbstractUser user) {
            manager.createQuery("DELETE FROM AbstractUser user WHERE user.login = : login", AbstractUser.class)
                    .setParameter("login", user.getLogin());
    }

    @Override
    public void deleteRestaurant(Restaurant restaurant) {
        Owner owner = restaurant.getOwner();

        manager.createQuery("DELETE FROM Restaurant r WHERE r.coordinate = :coordinate", Restaurant.class)
                .setParameter("coordinate", restaurant.getCoordinate());

        owner.getRestaurants().remove(restaurant);
    }

    @Override
    public void deleteComment(Restaurant restaurant, String comment) {
        manager.createQuery("UPDATE Restaurant r SET r.comments = :comment WHERE r.id = :restaurantId", Restaurant.class)
                .setParameter("comment", restaurant.getComments())
                .setParameter("restaurantId", restaurant.getId());

        restaurant.getComments().removeComment(comment);
    }

    @Override
    public void changeUserStatus(AbstractUser user, EUserStatus newUserStatus) {
        manager.createQuery("UPDATE AbstractUser user SET user.userStatus = :newUserStatus WHERE user.id = :userId", AbstractUser.class)
                .setParameter("newUserStatus", newUserStatus)
                .setParameter("userId", user.getId());

        user.setUserStatus(newUserStatus);
    }

    @Override
    public void changeRestaurantCoordinate(Restaurant restaurant, Coordinate newCoordinate) {
        manager.createQuery("UPDATE Restaurant r SET r.coordinate = :newCoordinate WHERE r.id = :restaurantId", Restaurant.class)
                .setParameter("newCoordinate", newCoordinate)
                .setParameter("restaurantId", restaurant.getId());

        restaurant.setCoordinate(newCoordinate);
    }
}
