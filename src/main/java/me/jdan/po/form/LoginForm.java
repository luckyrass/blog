package me.jdan.po.form;

import me.jdan.po.User;

/**
 * Created by jdan on 2017/5/29.
 */
public class LoginForm {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private boolean isRemember = false;

    public boolean isRemember() {
        return isRemember;
    }

    public void setRemember(boolean remember) {
        isRemember = remember;
    }

    public boolean getIsRemember() {
        return isRemember;
    }
}
