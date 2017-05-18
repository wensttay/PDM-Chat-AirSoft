package pdm.agifpb.firstapp.services;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import pdm.agifpb.firstapp.Configs;
import pdm.agifpb.firstapp.entities.Chat;
import pdm.agifpb.firstapp.entities.Contact;
import pdm.agifpb.firstapp.entities.ContactListItem;
import pdm.agifpb.firstapp.entities.ContactListItemGroup;

/**
 * Created by yatts on 24/02/2017.
 */

public class ContactListItemGroupService {
    public static final String CONTACSTS_LIST_ITEM_SERVICE_TOKEN = "---MESSEGE:11312321--";

    public static ContactListItemGroup request(Contact publish){
        try{
            Socket socket = new Socket(Configs.SERVER_IP, 10992);
            System.out.println("ENTREI NO SERVICE");
            Gson gson = new Gson();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(CONTACSTS_LIST_ITEM_SERVICE_TOKEN);
            stringBuilder.append(gson.toJson(publish));
            stringBuilder.append(CONTACSTS_LIST_ITEM_SERVICE_TOKEN);
            String s = stringBuilder.toString();
            System.out.println(s);
            socket.getOutputStream().write(s.getBytes());

            byte[] b = new byte[4000];
            socket.getInputStream().read(b);
            String json = new String(b).toString().trim();
            json = json.replaceAll(CONTACSTS_LIST_ITEM_SERVICE_TOKEN, "");
            ContactListItemGroup contactListItemGroup = new Gson().fromJson(json, ContactListItemGroup.class);

            socket.close();
            return contactListItemGroup;
        }catch (IOException ex){
            Log.d("AGDebug-HelloWorld", ex.getMessage());
            return null;
        }
    }
}
