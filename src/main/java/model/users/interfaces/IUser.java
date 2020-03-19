package model.users.interfaces;

import model.users.EUserStatus;

public interface IUser {
    public String getLogin();
    public String getPassword();
    public String getName();
    public EUserStatus getStatus();

    public void setLogin(String login);
    public void setPassword(String password);
    public void setName(String name);
    public void setUserStatus(EUserStatus userStatus);
}
