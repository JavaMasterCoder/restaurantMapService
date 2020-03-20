package model.users.interfaces;

import model.restaurants.Coordinate;
import model.restaurants.Restaurant;
import model.users.AbstractUser;
import model.users.EUserStatus;

public interface IAdminUser {
    public void deleteUser(AbstractUser user);
    public void deleteRestaurant(Restaurant restaurant);
    public void deleteComment(Restaurant restaurant, String comment);

    public void changeUserStatus(AbstractUser user, EUserStatus userStatus);
    public void changeRestaurantCoordinate(Restaurant restaurant, Coordinate coordinate);
}
