package pdm.agifpb.firstapp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yatts on 24/02/2017.
 */

public class ContactListItemGroup implements Serializable{
    private List<ContactListItem> contactListItems;

    public ContactListItemGroup() {
        contactListItems = new ArrayList<>();
    }

    public ContactListItemGroup(List<ContactListItem> contactListItems) {
        this.contactListItems = contactListItems;
    }

    public List<ContactListItem> getContactListItems() {
        return contactListItems;
    }

    public void setContactListItems(List<ContactListItem> contactListItems) {
        this.contactListItems = contactListItems;
    }

    public boolean addContactListItem(ContactListItem cli){
        return contactListItems.add(cli);
    }

}