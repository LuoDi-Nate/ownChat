package com.diwa.clientTest;

import com.diwa.chatClient.view.LoginView;
import junit.framework.TestCase;

/**
 * Testing {@link com.diwa.ExampleMacro}
 */
public class testChatClient extends TestCase
{
   public void testClientLoginBox(){
       LoginView lb = new LoginView();
       lb.setVisible(true);

   }
}
