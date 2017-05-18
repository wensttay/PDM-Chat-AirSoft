package pdm.agifpb.firstapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pdm.agifpb.firstapp.R;
import pdm.agifpb.firstapp.entities.Contact;
import pdm.agifpb.firstapp.entities.ContactListItem;

/**
 * Created by yatts on 24/02/2017.
 */

public class ContactListItemAdapter extends BaseAdapter{

    List<ContactListItem> contactList;

    public ContactListItemAdapter(List<ContactListItem> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contactList.get(position).getContact().getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ContactListItem contactListItem = (ContactListItem) getItem(position);

        convertView = inflater.inflate(R.layout.box_contact, null);
        TextView nameView = (TextView) convertView.findViewById(R.id.contact_name);
        TextView lastMsgView = (TextView) convertView.findViewById(R.id.contact_last_msg);
        nameView.setText(contactListItem.getContact().getName());
        lastMsgView.setText(contactListItem.getLastMsg().getMsg());
        return convertView;
    }
}
