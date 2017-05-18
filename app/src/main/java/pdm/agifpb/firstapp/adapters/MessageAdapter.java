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

import pdm.agifpb.firstapp.DataUserLogin;
import pdm.agifpb.firstapp.entities.Message;
import pdm.agifpb.firstapp.R;

/**
 * Created by yatts on 23/02/2017.
 */

public class MessageAdapter extends BaseAdapter {

    List<Message> messageList = new ArrayList<Message>();

    public MessageAdapter() {
    }

    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
    
    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return messageList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Message item = (Message) getItem(position);

        if(item.getPublisher().equals(DataUserLogin.getInstance().getLoggedUser().getContact())){
            convertView = inflater.inflate(R.layout.box_chat_mensage_right, null);
            TextView publishMsg = (TextView) convertView.findViewById(R.id.chat_logged_user_msg);

            publishMsg.setText(item.getMsg());
            return convertView;
        }else{
            convertView = inflater.inflate(R.layout.box_chat_mensage, null);
            TextView publishName = (TextView) convertView.findViewById(R.id.chat_other_user_name);
            TextView publishMsg = (TextView) convertView.findViewById(R.id.chat_other_user_msg);

            publishName.setText(item.getPublisher().getName());
            publishMsg.setText(item.getMsg());
            return convertView;
        }
    }
}
