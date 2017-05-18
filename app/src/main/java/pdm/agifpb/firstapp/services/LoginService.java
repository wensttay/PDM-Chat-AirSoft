package pdm.agifpb.firstapp.services;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.Socket;

import pdm.agifpb.firstapp.Configs;
import pdm.agifpb.firstapp.DataUserLogin;
import pdm.agifpb.firstapp.entities.User;

/**
 * Created by yatts on 24/02/2017.
 */

public class LoginService {

    public static final String LOGIN_TOKEN = "---LOGIN:11312321--";


    public static User request(User user){
        try{
            Socket socket = new Socket(Configs.SERVER_IP, 10990);

            Gson gson = new Gson();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(LOGIN_TOKEN);
            stringBuilder.append(gson.toJson(user));
            stringBuilder.append(LOGIN_TOKEN);
            socket.getOutputStream().write(stringBuilder.toString().getBytes());

            byte[] b = new byte[1024];
            socket.getInputStream().read(b);
            String response = new String(b).trim();
            response = response.replaceAll(LOGIN_TOKEN, "");

            if(response.equals("")) {
                return null;
            }
            User u = gson.fromJson(response, User.class);
            System.out.println(u.toString());
            DataUserLogin.getInstance().setLoggedUser(u);

            return u;
        }catch (IOException ex){
            Log.d("AGDebug-HelloWorld", ex.getMessage());
            return null;
        }
    }
}
