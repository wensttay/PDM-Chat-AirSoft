package br.edu.ifpb.ads.pdm.chatapp.server.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 30/03/2017, 13:13:31
 */
public class UserSection {

    private Contact loggedAccount;
    private List<Chat> chats;

    public UserSection(Contact contact) {
        this.loggedAccount = contact;
        this.chats = new ArrayList<>();
    }

    public UserSection(Contact loggedAccount, List<Chat> chats) {
        this.loggedAccount = loggedAccount;
        this.chats = chats;
    }

    public Contact getLoggedAccount() {
        return loggedAccount;
    }

    public void setLoggedAccount(Contact loggedAccount) {
        this.loggedAccount = loggedAccount;
    }

    public List<Chat> getChats() {
        return Collections.unmodifiableList(chats);
    }

    public boolean addChat(Chat chat) {
        if (chat.getInterlocutores().contains(loggedAccount)) {
            return chats.add(chat);
        }
        return false;
    }

    public boolean removeChat(Chat chat) {
        return chats.remove(chat);
    }

    public void mergeUserSection(UserSection userSection) throws RuntimeException {
        if (userSection == null) {
            throw new RuntimeException("User Section cannot be null.");
        }

        if (loggedAccount.getId() == null) {
            throw new RuntimeException("LoggedAccount ID cannot be null.");
        }

        if (userSection.getLoggedAccount().getId() == null) {
            throw new RuntimeException("User Section ID cannot be null.");
        }

        if (!Objects.equals(this.loggedAccount.getId(), userSection.getLoggedAccount().getId())) {
            throw new RuntimeException("User Section from diferent account cannot be merged.");
        }

        List<Chat> otherChatList = userSection.getChats();

        for (Chat thisChat : this.chats) {
            for (Chat otherChat : otherChatList) {
                if (thisChat.getId().equals(otherChat.getId())) {
                    thisChat.addAllInterlocutores(otherChat.getInterlocutores());
                    thisChat.addAllMessages(otherChat.getMessageList());
                    otherChatList.remove(otherChat);
                    break;
                }
            }
        }

        for (Chat chat : otherChatList) {
            addChat(chat);
        }

    }
}
