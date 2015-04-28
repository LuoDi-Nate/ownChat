package com.diwa.chatServer.dao;

import com.diwa.chatServer.model.User;
import com.diwa.common.exceptions.DbException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
* Created by di on 18/4/15.
        */
public class UserDao{
    static {
        Reader resourceAsReader = null;
        try {
            resourceAsReader = Resources.getResourceAsReader("configuration.xml");
        } catch (IOException e) {
            e.printStackTrace();
   }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsReader);
        sqlSession=sqlSessionFactory.openSession();
        }
private static SqlSession sqlSession;

public int createUser(User user){
        int userId = sqlSession.insert("INSERT-USER-BY-USER", user);
        sqlSession.commit();

        return userId;
        }

public User getUserById(int userId){

        return null;
        }
        }
