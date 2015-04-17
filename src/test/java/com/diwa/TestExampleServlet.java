package com.diwa;

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
public class TestExampleServlet extends TestCase
{
    public void testBasic()
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
}
