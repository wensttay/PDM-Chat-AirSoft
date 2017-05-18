package pdm.agifpb.firstapp.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yatts on 23/02/2017.
 */
public class Message implements Serializable{

    private Long id;
    private Date date;
    private Contact subscriber;
    private Contact publisher;
    private String msg;

    public Message() {
    }

    public Message(Date date, Contact subscriber, Contact publisher, String msg) {
        this.date = date;
        this.subscriber = subscriber;
        this.publisher = publisher;
        this.msg = msg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Contact getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Contact subscriber) {
        this.subscriber = subscriber;
    }

    public Contact getPublisher() {
        return publisher;
    }

    public void setPublisher(Contact publisher) {
        this.publisher = publisher;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", date=" + date.toString() +
                ", subscriber=" + subscriber.toString() +
                ", publisher=" + publisher.toString() +
                ", msg='" + msg + '\'' +
                '}';
    }
}
