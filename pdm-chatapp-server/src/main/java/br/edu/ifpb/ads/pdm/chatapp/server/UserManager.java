/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.ads.pdm.chatapp.server;

import br.edu.ifpb.ads.pdm.chatapp.socket.SocketUtils;
import br.edu.ifpb.ads.pdm.chatapp.socket.SocketProtocol;
import br.edu.ifpb.ads.pdm.chatapp.server.entities.User;
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
public class UserManager {

    private long idUserSerial = 0;
    private final List<User> users;

    public UserManager() {
        users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public boolean addUser(User user) {
        if (user != null && !checkIfExists(user)) {
            user.getContact().setId(++idUserSerial);
            return users.add(user);
        } else {
            return false;
        }
    }

    public boolean removeUser(User user) {
        return users.remove(user);
    }

    private User checkPermission(User userToCheck) {
        for (User user : users) {
            if (user.getUserPassWord().equals(userToCheck.getUserPassWord())
                    && user.getContact().getName().equals(userToCheck.getContact().getName())) {
                return user;
            }
        }
        return null;
    }

    private boolean checkIfExists(User userToCheck) {
        for (User user : users) {
            if (user.getContact().getId().equals(userToCheck.getContact().getId())
                    || user.getContact().getName().equals(userToCheck.getContact().getName())) {
                return true;
            }
        }
        return false;
    }

    public void exec() {
        Runnable r;
        r = new Runnable() {
            @Override

            public void run() {
                ServerSocket serverSocket;
                try {
                    serverSocket = new ServerSocket(10990);

                    while (true) {
                        System.out.println("Esperando tentativa de login ... ");
                        Socket socket = serverSocket.accept();

                        System.out.println("Solicitação aceita ...");
                        String reciveMessage = SocketUtils.reciveMessage(socket);
                        String json = SocketProtocol.decodeMessage(reciveMessage);

                        Gson g = new Gson();
                        User user = g.fromJson(json, User.class);
                        System.out.println("Verificando se dados batem ...");
                        user = checkPermission(user);

                        System.out.println(user);

                        String encodeMessage = SocketProtocol
                                .encodeMessage(user != null ? g.toJson(user) : null);

                        System.out.println("Retornando resposta ...");
                        SocketUtils.sendMessage(socket, encodeMessage);

                        socket.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        Thread tLogin = new Thread(r);
        tLogin.start();
    }
}
