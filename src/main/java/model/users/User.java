package model.users;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends AbstractUser {

    @Column(nullable = false)
    private String name;

    public User() {
    }

    public User(String login, String password, String name) {
        super(login, password);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
