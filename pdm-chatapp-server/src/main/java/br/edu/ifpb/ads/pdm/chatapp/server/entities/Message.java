package br.edu.ifpb.ads.pdm.chatapp.server.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by yatts on 23/02/2017.
 */
public class Message implements Serializable {

    private Long id;
    private Date date;
    private String msg;
    private Contact publisher;

    public Message() {
    }

    public Message(Contact publisher, Date date, String msg) {
        this.date = date;
        this.publisher = publisher;
        this.msg = msg;
    }

    public Long getId() {
        return id;
    }

    public Message setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Message setDate(Date date) {
        this.date = date;
        return this;
    }

    public Contact getPublisher() {
        return publisher;
    }

    public Message setPublisher(Contact publisher) {
        this.publisher = publisher;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Message setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return "Message{"
                + "id=" + id
                + ", date=" + date.toString()
                + ", publisher=" + publisher.toString()
                + ", msg='" + msg + '\''
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.date);
        hash = 41 * hash + Objects.hashCode(this.publisher);
        hash = 41 * hash + Objects.hashCode(this.msg);
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
        final Message other = (Message) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
