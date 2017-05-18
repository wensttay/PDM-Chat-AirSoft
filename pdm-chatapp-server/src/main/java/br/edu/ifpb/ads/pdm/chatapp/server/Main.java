package br.edu.ifpb.ads.pdm.chatapp.server;

import br.edu.ifpb.ads.pdm.chatapp.server.entities.Chat;
import br.edu.ifpb.ads.pdm.chatapp.server.entities.Contact;
import br.edu.ifpb.ads.pdm.chatapp.server.entities.Message;
import br.edu.ifpb.ads.pdm.chatapp.server.entities.User;
import br.edu.ifpb.ads.pdm.chatapp.socket.SocketProtocol;
import br.edu.ifpb.ads.pdm.chatapp.socket.SocketUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 30/03/2017, 08:14:37
 */
public class Main {

    public static void main(String[] args) {
        
        User me = new User(new Contact("Wensttay"), "100101");
        User friend = new User(new Contact("Pedro"), "202200");
        User brother = new User(new Contact("Taywens"), "30400");
        
        UserManager loginManager = new UserManager();
        loginManager.addUser(me);
        loginManager.addUser(friend);
        loginManager.addUser(brother);

        Chat chat = new Chat(me.getContact(), friend.getContact());
        Chat chat2 = new Chat(brother.getContact(), friend.getContact());
        Chat chat3 = new Chat(me.getContact(), brother.getContact());

        chat.addMessage(new Message(me.getContact(), new Date(), "Olá de Wensttay no Chat 1"));
        chat.addMessage(new Message(friend.getContact(), new Date(), "Olá de Pedro no Chat 1"));
        
        chat2.addMessage(new Message(brother.getContact(), new Date(), "Olá de Taywens no Chat 2"));
        chat2.addMessage(new Message(friend.getContact(), new Date(), "Olá de Pedro no Chat 2"));
        
        chat3.addMessage(new Message(me.getContact(), new Date(), "Olá de Wensttay no Chat 3"));
        chat3.addMessage(new Message(brother.getContact(), new Date(), "Olá de Taywens no Chat 3"));
        
        ChatManager chatManager = new ChatManager();
        chatManager.addChats(chat);
        chatManager.addChats(chat2);
        chatManager.addChats(chat3);
        
        MessageManager messageManager = new MessageManager();
        
        AppManager appManager = new AppManager(chatManager, loginManager, messageManager);
        appManager.exec();;
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        Socket socket;
        try {
            socket = new Socket("localhost", 10991);
            Gson g = new Gson();
            
            String toJson = g.toJson(me.getContact());
            SocketUtils.sendMessage(socket, SocketProtocol.encodeMessage(toJson));

            String reciveMessage = SocketProtocol.decodeMessage(SocketUtils.reciveMessage(socket));
//            System.out.println(reciveMessage);

            Type listType = new TypeToken<List<Chat>>() {}.getType();

            List<Chat> chats = g.fromJson(reciveMessage, listType);

            for (Chat chate : chats) {
                Message lastMessage = chate.getLastMessage();
                if (lastMessage != null) {
                    System.out.println(lastMessage.getPublisher().getName() + ": " + lastMessage.getMsg());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
