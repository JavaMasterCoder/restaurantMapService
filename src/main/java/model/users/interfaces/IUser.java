package model.users.interfaces;

import model.users.EUserStatus;

public interface IUser {
    public String getLogin();
    public String getPassword();
    public EUserStatus getUserStatus();

    public void setLogin(String login);
    public void setPassword(String password);
    public void setUserStatus(EUserStatus userStatus);
}
