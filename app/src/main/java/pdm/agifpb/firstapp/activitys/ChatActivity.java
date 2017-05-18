package pdm.agifpb.firstapp.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import pdm.agifpb.firstapp.R;
import pdm.agifpb.firstapp.adapters.MessageAdapter;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        final ImageView sendButton = (ImageView) findViewById(R.id.chatButton);
        sendButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
//                sendMsg();
            }
        });

        final Button backButton = (Button) findViewById(R.id.chat_backpage_button);
        backButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                backToMain();
            }
        });

        final ListView listview = (ListView) findViewById(R.id.chat_listview);
        MessageAdapter chatAdapter = new MessageAdapter();
        listview.setAdapter(chatAdapter);
}

    private void backToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
