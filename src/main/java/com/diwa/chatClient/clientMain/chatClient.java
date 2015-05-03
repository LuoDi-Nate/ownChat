package com.diwa.chatClient.clientMain;


import com.diwa.chatClient.Vairable.Status;
import com.diwa.chatClient.view.ClientView;
import com.diwa.chatClient.view.LoginView;
import com.diwa.chatClient.view.RegisterView;
import com.diwa.common.job.RegisterJob;

/**
 * Created by di on 18/4/15.
 */
public class ChatClient {
    //view
    private LoginView login;
    private RegisterView register;
    private ClientView client;
    class statusKeeper extends Thread{
        @Override
        public void run() {
            while (true){
                display(Status.status);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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
        this.login.setVisible(false);
        this.register.setVisible(false);
        this.client.setVisible(true);
    }

    public static void main(String[] args) {
        ChatClient cc = new ChatClient();
//        cc.login.setVisible(true);
    }
}
