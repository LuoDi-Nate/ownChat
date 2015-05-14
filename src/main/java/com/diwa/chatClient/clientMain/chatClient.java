package com.diwa.chatClient.clientMain;


import com.diwa.chatClient.Vairable.Utils;
import com.diwa.chatClient.view.ClientView;
import com.diwa.chatClient.view.LoginView;
import com.diwa.chatClient.view.RegisterView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * Created by di on 18/4/15.
 */
public class ChatClient {

    private final static Logger logger = LoggerFactory.getLogger(ChatClient.class);

    //view
    private LoginView login;
    private RegisterView register;
    private ClientView client;

    class statusKeeper extends Thread{


        @Override
        public void run() {
            while (true){
                //不断轮询 监听目前状态
                display(Utils.getStatus());

                //状态常量 0:刚刚打开程序需要登录 1:点击注册跳转 2:已经登陆聊天界面 3:退出
                if(Utils.getStatus() == 2 || Utils.getStatus() == 3){
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //时刻等待的线程 从server那边获取消息
    class phoneKeeper extends Thread{
        @Override
        public void run() {

        }
    }

    public ChatClient(){
        this.login = new LoginView();
        this.register = new RegisterView();
        this.client = new ClientView();
        statusKeeper keeper = new statusKeeper();
        keeper.start();

    }

    public void display(int status){
        switch (status){
            case 0:loginStatus();break;
            case 1:registerStatus();break;
            case 2:clientStatus();break;
        }
    }

    public void loginStatus(){
        this.login.setVisible(true);
        this.register.setVisible(false);
        this.client.setVisible(false);

    }

    public void registerStatus(){
        this.login.setVisible(false);
        this.register.setVisible(true);
        this.client.setVisible(false);
    }

    public void clientStatus(){
        this.client.updateSelf();
        this.login.setVisible(false);
        this.register.setVisible(false);
        this.client.setVisible(true);
    }

    public static void main(String[] args) {
        ChatClient cc = new ChatClient();
    }

    public void flushFriendList(List<String> list) {
        this.client.flushFriend(list);
    }

    public void getMsg(){

    }
}
