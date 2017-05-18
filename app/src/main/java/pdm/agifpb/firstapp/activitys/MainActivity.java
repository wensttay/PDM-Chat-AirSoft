package pdm.agifpb.firstapp.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import pdm.agifpb.firstapp.DataUserLogin;
import pdm.agifpb.firstapp.R;
import pdm.agifpb.firstapp.adapters.ContactListItemAdapter;
import pdm.agifpb.firstapp.entities.Contact;
import pdm.agifpb.firstapp.entities.ContactListItemGroup;
import pdm.agifpb.firstapp.entities.User;
import pdm.agifpb.firstapp.services.ContactListItemGroupService;
import pdm.agifpb.firstapp.services.LoginService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView userLoggedViewName = (TextView) findViewById(R.id.logged_user_name);
        userLoggedViewName.setText(DataUserLogin.getInstance().getLoggedUser().getContact().getName());
        //
        final Button tabContacts = (Button) findViewById(R.id.tab_contacts);
        final Button tabMissions = (Button) findViewById(R.id.tab_missions);
        //
        final LinearLayout tabFrameChat = (LinearLayout) findViewById(R.id.tab_frame_chat);
        final LinearLayout tabFrameMissions = (LinearLayout) findViewById(R.id.tab_frame_missions);
        //
        ListView contactViewList = (ListView) findViewById(R.id.contacts_list_view);
        ContactListItemGroup contactListItemGroup = new ContactListItemGroup();
        ContactListItemAdapter contactListItemAdapter =
                new ContactListItemAdapter(contactListItemGroup.getContactListItems());
        contactViewList.setAdapter(contactListItemAdapter);

        tabContacts.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                tabContacts.setBackgroundColor(Color.parseColor("#55888888"));
                tabMissions.setBackgroundColor(Color.parseColor("#00000000"));
                tabFrameChat.setVisibility(View.VISIBLE);
                tabFrameMissions.setVisibility(View.GONE);
            }
        });
        //

        tabMissions.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                tabContacts.setBackgroundColor(Color.parseColor("#00000000"));
                tabMissions.setBackgroundColor(Color.parseColor("#55888888"));
                tabFrameChat.setVisibility(View.GONE);
                tabFrameMissions.setVisibility(View.VISIBLE);

            }
        });

        contactViewList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                openChatActivity();
            }
        });

    }

    private void openChatActivity() {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }

}
