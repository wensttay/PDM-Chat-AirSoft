/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.ads.pdm.chatapp.server;

import br.edu.ifpb.ads.pdm.chatapp.server.entities.Chat;
import br.edu.ifpb.ads.pdm.chatapp.server.entities.Contact;
import br.edu.ifpb.ads.pdm.chatapp.server.entities.UserSection;
import br.edu.ifpb.ads.pdm.chatapp.socket.SocketProtocol;
import br.edu.ifpb.ads.pdm.chatapp.socket.SocketUtils;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yatts
 */
public class ChatManager {
    
    private long idChatSerial = 0;
    private List<Chat> chats;

    public ChatManager() {
        chats = new ArrayList<>();
    }

    public boolean addChats(Chat chat) {
        if (chat != null && !chats.contains(chat)) {
            chat.setId(++idChatSerial);
            return chats.add(chat);
        } else {
            return false;
        }
    }

    public boolean removeChats(Chat chat) {
        return chats.remove(chat);
    }

    public List<Chat> getChats() {
        return Collections.unmodifiableList(chats);
    }

    public void exec() {
        Runnable r;
        r = new Runnable() {
            @Override

            public void run() {
                ServerSocket serverSocket;
                try {
                    serverSocket = new ServerSocket(10991);

                    while (true) {
                        System.out.println("Esperando tentativa de obter ContactListItem ... ");
                        Socket socket = serverSocket.accept();

                        System.out.println("Traduzindo requisição ...");
                        String text = SocketUtils.reciveMessage(socket);
                        text = SocketProtocol.decodeMessage(text);

                        Gson g = new Gson();
                        Contact userLogued = g.fromJson(text, Contact.class);
                        UserSection userSection = new UserSection(userLogued);

                        for (Chat c : chats) {
                            if (c.getInterlocutores().contains(userLogued)) {
                                userSection.addChat(c);
                            }
                        }
                        
                        String decodeMessage = SocketProtocol.decodeMessage(g.toJson(userSection));
                        System.out.println("Retornando UserSection ...");
                        SocketUtils.sendMessage(socket, decodeMessage);

                        socket.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        Thread tMessage = new Thread(r);
        tMessage.start();
    }
}
