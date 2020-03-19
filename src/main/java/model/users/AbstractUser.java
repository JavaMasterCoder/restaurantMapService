package model.users;

import model.users.interfaces.IUser;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractUser implements IUser {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String login;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private EUserStatus userStatus;

    public AbstractUser() {
    }

    public AbstractUser(String login, String password) {
        this.login = login;
        this.password = password;
        this.userStatus = EUserStatus.IS_WAITING_TO_BE_REGISTERED_BY_ADMIN;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
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

    public EUserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(EUserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
