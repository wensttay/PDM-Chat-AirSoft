package br.edu.ifpb.ads.pdm.chatapp.server.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by yatts on 24/02/2017.
 */

public class User implements Serializable{

    private Contact contact;
    private String userPassWord;

    public User() {
    }

    public User(Contact contact, String userPassWord) {
        this.contact = contact;
        this.userPassWord = userPassWord;
    }

    public Contact getContact() {
        return contact;
    }

    public User setContact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public User setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "contact=" + contact.toString() +
                ", userPassWord='" + userPassWord + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.contact);
        hash = 41 * hash + Objects.hashCode(this.userPassWord);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        return true;
    }
    
}
