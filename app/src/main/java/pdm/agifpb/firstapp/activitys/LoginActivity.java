package pdm.agifpb.firstapp.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import pdm.agifpb.firstapp.DataUserLogin;
import pdm.agifpb.firstapp.R;
import pdm.agifpb.firstapp.adapters.ContactListItemAdapter;
import pdm.agifpb.firstapp.entities.Contact;
import pdm.agifpb.firstapp.entities.ContactListItem;
import pdm.agifpb.firstapp.entities.ContactListItemGroup;
import pdm.agifpb.firstapp.entities.User;
import pdm.agifpb.firstapp.services.ContactListItemGroupService;
import pdm.agifpb.firstapp.services.LoginService;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginButton = (Button) findViewById(R.id.login_command_button);
        loginButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                final EditText nameLogin = (EditText) findViewById(R.id.input_login);
                final EditText passwordLogin = (EditText) findViewById(R.id.input_password);

                Contact contact = new Contact();
                contact.setName(nameLogin.getText().toString());
                User user = new User();
                user.setContact(contact);
                user.setUserPassWord(passwordLogin.getText().toString());

                Log.d("[LOG]", user.toString());
                checkUser(user);
            }
        });
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void checkUser(final User user) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                User result = new LoginService().request(user);
                System.out.println(result.toString());
                if(result != null){
                    DataUserLogin.getInstance().setLoggedUser(result);
                    checkContactListItemGroup(result.getContact());
                    openMainActivity();
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textResultView = (TextView) findViewById(R.id.text_view_login_result);
                            textResultView.setText("Conta inexistente, ou login e/ou senha incorretos");
                        }
                    });
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void checkContactListItemGroup(final Contact publish) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final ContactListItemGroup result = ContactListItemGroupService.request(publish);

                if(result != null && !result.getContactListItems().isEmpty()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ListView contactViewList = (ListView) findViewById(R.id.contacts_list_view);
                            List<ContactListItem> contactListItems = result.getContactListItems();

                            ContactListItemAdapter contactListItemAdapter = new ContactListItemAdapter(contactListItems);
                            contactViewList.setAdapter(contactListItemAdapter);
                        }
                    });
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

}
