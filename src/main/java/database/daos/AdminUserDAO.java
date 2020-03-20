package database.daos;

import model.restaurants.Coordinate;
import model.restaurants.Restaurant;
import model.users.AbstractUser;
import model.users.EUserStatus;
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
            manager.createQuery("delete from AbstractUser user where user.login = : login", AbstractUser.class)
                    .setParameter("login", user.getLogin());
    }

    @Override
    public void deleteRestaurant(Restaurant restaurant) {

    }

    @Override
    public void deleteComment(Restaurant restaurant, String comment) {

    }

    @Override
    public void changeUserStatus(AbstractUser user, EUserStatus userStatus) {

    }

    @Override
    public void changeRestaurantCoordinate(Restaurant restaurant, Coordinate coordinate) {

    }
}
