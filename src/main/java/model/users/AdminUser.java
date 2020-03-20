package model.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("ADMIN")
public class AdminUser extends AbstractUser {
    public AdminUser() {
    }

    public AdminUser(String login, String password) {
        super(login, password);
        setUserStatus(EUserStatus.REGISTERED);
    }
}
