package br.edu.ifpb.ads.pdm.chatapp.server.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by yatts on 24/02/2017.
 */
public class Chat implements Serializable {

    private Long id;
    private final List<Contact> interlocutores;
    private final List<Message> messageList;
    private String tittle = "";
    private boolean personTittle = false;
    private Contact manager;

    public Chat(Contact manager, Contact contact) {
        this.messageList = new ArrayList<>();
        this.interlocutores = new ArrayList<>();
        this.interlocutores.add(manager);
        this.interlocutores.add(contact);
        this.manager = manager;
    }

    public Chat(Contact manager, Contact contact, String tittle) {
        this.messageList = new ArrayList<>();
        this.interlocutores = new ArrayList<>();
        this.interlocutores.add(manager);
        this.interlocutores.add(contact);
        this.manager = manager;
        this.tittle = tittle;
        this.personTittle = true;
    }

    public String generateTittle(Contact currentContact) {
        if (!personTittle) {

            String t = "";
            if (interlocutores.size() >= 2) {
                for (int i = 0; i < interlocutores.size(); i++) {
                    if (!interlocutores.get(i).equals(currentContact)) {
                        if (i == interlocutores.size() - 1) {
                            t += interlocutores.get(i).getName();
                            break;
                        }
                        t += interlocutores.get(i).getName() + ", ";
                    }
                }
            }

            return t;
        } else {
            return this.tittle;
        }
    }

    public Contact getManager() {
        return manager;
    }

    public Long getId() {
        return id;
    }

    public Chat setId(Long id) {
        this.id = id;
        return this;
    }

    public Chat setManager(Contact manager) {
        this.manager = manager;
        return this;
    }

    public Chat setTittle(String tittle) {
        if (tittle != null && !tittle.equals("")) {
            this.tittle = tittle;
            personTittle = true;
        }
        return this;
    }

    public boolean addInterlocutor(Contact contact) {
        if (contact != null
                && contact.getId() != null
                && !interlocutores.contains(contact)) {
            return interlocutores.add(contact);
        }
        return false;
    }

    public void addAllInterlocutores(List<Contact> contacts) {
        for (Contact contact : contacts) {
            addInterlocutor(contact);
        }
    }

    public boolean removeInterlocutor(Contact contact) {
        if (contact.getId() != null) {
            return interlocutores.remove(contact);
        }
        return false;
    }

    public boolean addMessage(Message message) {
        if (interlocutores.contains(message.getPublisher())) {
            System.out.println(message.toString());
            return messageList.add(message);
        }
        return false;
    }

    public void addAllMessages(List<Message> messages) {
        for (Message message : messages) {
            addMessage(message);
        }
    }

    public boolean removeMessage(Message message) {
        return messageList.remove(message);
    }

    public List<Contact> getInterlocutores() {
        return Collections.unmodifiableList(interlocutores);
    }

    public List<Message> getMessageList() {
        return Collections.unmodifiableList(messageList);
    }

    public Message getLastMessage() {
        if (!messageList.isEmpty()) {
            return messageList.get(messageList.size() - 1);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Chat{" + "interlocutores=" + interlocutores.toString() + ", messageList=" + messageList.toString() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.interlocutores);
        hash = 59 * hash + Objects.hashCode(this.messageList);
        hash = 59 * hash + Objects.hashCode(this.tittle);
        hash = 59 * hash + (this.personTittle ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.manager);
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
        final Chat other = (Chat) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
