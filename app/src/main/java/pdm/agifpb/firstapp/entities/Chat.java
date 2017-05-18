package pdm.agifpb.firstapp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yatts on 24/02/2017.
 */
public class Chat implements Serializable {

    private List<Contact> interlocutores;
    private List<Message> messageList;

    public Chat(List<Contact> interlocutores) {
        this.interlocutores = interlocutores;
        this.messageList = new ArrayList<>();
    }

    public boolean addInterlocutor(Contact contact) {
        return interlocutores.add(contact);
    }

    public boolean addMessage(Message message) {
        if (interlocutores.contains(message.getPublisher())
                && interlocutores.contains(message.getSubscriber())) {
            return messageList.add(message);
        }
        return false;
    }

    public ContactListItem getContactListItem(Contact contact) {
        return new ContactListItem(getTheOtherClient(contact), getLastMessage());
    }

    public Contact getTheOtherClient(Contact contact) {
        if (!messageList.isEmpty()) {
            if (messageList.get(0).getPublisher().equals(contact)) {
                return messageList.get(0).getSubscriber();
            } else {
                return messageList.get(0).getPublisher();
            }
        }
        throw new RuntimeException("Não tem ninguem no chat");
    }

    public Message getLastMessage() {
        if (!messageList.isEmpty()) {
            Message message = messageList.get(0);

            for (int i = 1; i < messageList.size(); i++) {
                if (messageList.get(i).getDate().before(message.getDate())) {
                    message = messageList.get(i);
                }
            }
            return message;
        } else {
            return null;
        }
    }

    public List<Contact> getInterlocutores() {
        return interlocutores;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
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
        final Chat other = (Chat) obj;
        if (other.getInterlocutores()
                .containsAll(this.getInterlocutores())
                && this.getInterlocutores()
                .containsAll(other.getInterlocutores())) {
            return true;
        }
        return false;
    }

}
