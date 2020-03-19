package model.users;

import model.users.interfaces.IUser;

public abstract class AbstractUser implements IUser {
    private String login;
    private String password;
    private String name;
    private EUserStatus userStatus;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public EUserStatus getStatus() {
        return userStatus;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserStatus(EUserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
