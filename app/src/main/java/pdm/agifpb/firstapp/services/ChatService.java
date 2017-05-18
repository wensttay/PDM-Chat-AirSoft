package pdm.agifpb.firstapp.services;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

import pdm.agifpb.firstapp.Configs;
import pdm.agifpb.firstapp.entities.Chat;
import pdm.agifpb.firstapp.entities.Contact;
import pdm.agifpb.firstapp.entities.Message;
import pdm.agifpb.firstapp.entities.User;

/**
 * Created by yatts on 24/02/2017.
 */

public class ChatService {

    public static final String MESSEGE_TOKEN = "---MESSEGE:11312321--";

    public static Chat request(Contact publish, Contact subcriber){
        try{
            Socket socket = new Socket(Configs.SERVER_IP, 10991);

            Gson gson = new Gson();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(MESSEGE_TOKEN);
            stringBuilder.append(gson.toJson(publish) + "|" + gson.toJson(subcriber));
            stringBuilder.append(MESSEGE_TOKEN);
            socket.getOutputStream().write(stringBuilder.toString().getBytes());

            PrintWriter out
                    = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in
                    = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn
                    = new BufferedReader(
                    new InputStreamReader(System.in));

            StringBuilder textBuilder = new StringBuilder();
            String userInput;

            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                textBuilder.append(in.readLine());
            }

            String json = textBuilder.toString().trim();
            json = json.replaceAll(MESSEGE_TOKEN, "");

            Chat chat = new Gson().fromJson(json, Chat.class);
            return chat;
        }catch (IOException ex){
            Log.d("AGDebug-HelloWorld", ex.getMessage());
            return null;
        }
    }
}
