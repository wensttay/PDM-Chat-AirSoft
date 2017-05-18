package pdm.agifpb.firstapp;

import pdm.agifpb.firstapp.entities.User;

/**
 * Created by yatts on 24/02/2017.
 */

public class DataUserLogin {

    private User loggedUser;

    public DataUserLogin() {
        loggedUser = new User();
    }

    public DataUserLogin(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    private static final DataUserLogin holder = new DataUserLogin();
    
    public static DataUserLogin getInstance(){
        return holder;
    }
}
