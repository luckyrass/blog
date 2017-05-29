package me.jdan.service;

import me.jdan.po.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by jdan on 2016/11/30.
 */
public class ShiroRealm extends AuthorizingRealm {
    private UserService userService;
    public static final String SESSION_USER_KEY = "gray";

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(ShiroRealm.SESSION_USER_KEY);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("user");
        return info;
    }

    /*
    * 认证
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        // 把token转换成User对象
        User userLogin = tokenToUser((UsernamePasswordToken) authcToken);
        // 验证用户是否可以登录
        User ui = userService.selectByUserName(userLogin.getUsername());
        if (ui == null || !ui.getPassword().equals(userLogin.getPassword())) {
            throw new AuthenticationException("usernameorpassword.not.match");
        }
        // 设置session
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(ShiroRealm.SESSION_USER_KEY, ui);
        return new SimpleAuthenticationInfo(userLogin.getUsername(), userLogin.getPassword(), userLogin.getUsername());
    }

    private User tokenToUser(UsernamePasswordToken authcToken) {
        User user = new User();
        user.setUsername(authcToken.getUsername());
        user.setPassword(String.valueOf(authcToken.getPassword()));
        return user;
    }
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
