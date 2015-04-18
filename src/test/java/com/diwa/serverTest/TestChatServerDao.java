package com.diwa.serverTest;

import com.diwa.chatServer.model.User;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Testing {@link com.diwa.ExampleMacro}
 */
public class TestChatServerDao extends TestCase
{
    public void testSelectUserById()
    {
        // add test here...
        try {
            Reader resourceAsReader = Resources.getResourceAsReader("configuration.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsReader);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            int userId = 1;
            User user = (User)sqlSession.selectOne("SELECT-USER-BY-ID", userId);
            String s = user.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testCreateUserByUser(){
        User user = new User();
        user.setNickName("fortest7");
        user.setIp("127.0.0.1");
        user.setPort(9902);

        try{
            Reader resourceAsReader = Resources.getResourceAsReader("configuration.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsReader);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            int id = sqlSession.insert("INSERT-USER-BY-USER", user);

            sqlSession.commit();
        }catch (Exception e){
            e.printStackTrace();
        }



    }
}
