package me.jdan.service.impl;

import me.jdan.dao.UserMapper;
import me.jdan.po.User;
import me.jdan.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by jdan on 2016/10/29.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userDao;

    public User selectByUserName(String userName) {
        return this.userDao.selectByPrimaryKey(userName);
    }

    public void insertUser(User user) {
        this.userDao.insert(user);
    }

    public void updateUser(User user) {
        this.userDao.updateByPrimaryKey(user);
    }

    @Override
    public void deleteUserByUserName(String userName) {
        this.userDao.deleteByPrimaryKey(userName);
    }

    public int selectUserCount() {
        return 0;
    }
}
