package com.diwa.chatServer.service;

import com.diwa.chatServer.dao.UserDao;
import com.diwa.chatServer.model.User;
import com.diwa.common.exceptions.registerException;

/**
 * Created by di on 20/4/15.
 */
public class RegisterService {
    public static User registerUser(User newUser) throws registerException{
        //如果非空项为空,抛出异常
        if(newUser.getNickName() == null ||
                newUser.getPassword() == null ||
                newUser.getIp() == null ||
                newUser.getPort() == 0){
            throw new registerException("创建用户,参数不合法");
        }

        UserDao userDao = new UserDao();

        int id = userDao.createUser(newUser);
        if(id == 0){
            throw new registerException("创建用户,数据库读写异常");
        }
        newUser.setId(id);
        return newUser;
    }
}
