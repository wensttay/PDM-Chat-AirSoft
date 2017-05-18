package pdm.agifpb.firstapp.entities;

import java.io.Serializable;

/**
 * Created by yatts on 24/02/2017.
 */

public class ContactListItem implements Serializable{
    private Contact contact;
    private Message lastMsg;

    public ContactListItem(Contact contact, Message lastMsg) {
        this.contact = contact;
        this.lastMsg = lastMsg;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Message getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(Message lastMsg) {
        this.lastMsg = lastMsg;
    }
}
