package model.users;

import model.restaurants.Restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner extends AbstractUser{

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "owner")
    private List<Restaurant> restaurants;

    public Owner() {
        super();
    }

    public Owner(String login, String password) {
        super(login, password);
        restaurants = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
