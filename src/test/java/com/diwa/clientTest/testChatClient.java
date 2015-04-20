package com.diwa.clientTest;

import com.diwa.chatClient.view.loginBox;
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
public class testChatClient extends TestCase
{
   public void testClientLoginBox(){
       loginBox lb = new loginBox();
       lb.setVisible(true);

   }
}
