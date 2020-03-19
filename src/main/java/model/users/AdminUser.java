package model.users;

import model.restaurants.Coordinate;
import model.restaurants.Restaurant;
import model.users.interfaces.IAdminUser;

public class AdminUser extends AbstractUser implements IAdminUser {
    @Override
    public void deleteUser(AbstractUser user) {
        
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
