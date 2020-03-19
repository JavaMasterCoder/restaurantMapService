package model.users;

import javax.persistence.Entity;

@Entity
public class AdminUser extends AbstractUser {
    public AdminUser() {
    }

    public AdminUser(String login, String password) {
        super(login, password);
        setUserStatus(EUserStatus.REGISTERED);
    }
}
