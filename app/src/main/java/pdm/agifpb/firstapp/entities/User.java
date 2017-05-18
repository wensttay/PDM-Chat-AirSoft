package pdm.agifpb.firstapp.entities;

import java.io.Serializable;

/**
 * Created by yatts on 24/02/2017.
 */

public class User implements Serializable{

    private Contact contact;
    private String userPassWord;

    public User() {
    }

    public User(Contact contact) {
        this.contact = contact;
    }

    public User(Contact contact, String userPassWord) {
        this.contact = contact;
        this.userPassWord = userPassWord;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    @Override
    public String toString() {
        return "User{" +
                "contact=" + contact.toString() +
                ", userPassWord='" + userPassWord + '\'' +
                '}';
    }
}
