package model.users;

import model.restaurants.Restaurant;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("OWNER")
public class Owner extends AbstractUser{

    @Column
    private String name;

    @OneToMany(mappedBy = "owner")
    private List<Restaurant> restaurants;

    public Owner() {
        super();
    }

    public Owner(String login, String password, String name) {
        super(login, password);
        this.name = name;
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
