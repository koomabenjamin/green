package com.shapeworks.mivule.detail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.shapeworks.mivule.R;
import com.github.bassaer.chatmessageview.model.User;
import com.github.bassaer.chatmessageview.models.Message;
import com.github.bassaer.chatmessageview.util.ChatBot;
import com.github.bassaer.chatmessageview.views.ChatView;

import java.util.Random;

public class d_consult extends AppCompatActivity {

    private ChatView mConsultChatView;
    private ChatBot chatBot;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_consult);

        Bundle extras = getIntent().getExtras();

        Toolbar toolbar = (Toolbar) findViewById(R.id.consult_toolbar);
        toolbar.setTitle(extras.getString("name"));
        //toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);

        FirebaseAuth auth = FirebaseAuth.getInstance();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //User id
        int myId = 0;
        //User icon
        Bitmap myIcon = BitmapFactory.decodeResource(getResources(), R.drawable.face_2);
        //User name
        String myName = auth.getCurrentUser().getDisplayName();

        int yourId = 1;
        Bitmap yourIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);
        String yourName = extras.getString("name");

        final User me = new User(myId, myName, myIcon);
        final User you = new User(yourId, yourName, yourIcon);

        mConsultChatView = (ChatView)findViewById(R.id.chat_view);
        //Set UI parameters if you need
        mConsultChatView.setRightBubbleColor(ContextCompat.getColor(this, R.color.green500));
        mConsultChatView.setLeftBubbleColor(Color.WHITE);
        mConsultChatView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorChatLight));
        mConsultChatView.setSendButtonColor(ContextCompat.getColor(this, R.color.colorAccent));
        mConsultChatView.setSendIcon(R.drawable.ic_action_send);
        mConsultChatView.setRightMessageTextColor(Color.WHITE);
        mConsultChatView.setLeftMessageTextColor(Color.BLACK);
        mConsultChatView.setUsernameTextColor(Color.WHITE);
        mConsultChatView.setSendTimeTextColor(Color.WHITE);
        mConsultChatView.setDateSeparatorColor(Color.WHITE);
        mConsultChatView.setInputTextHint("new message...");
        mConsultChatView.setMessageMarginTop(5);
        mConsultChatView.setMessageMarginBottom(5);

        //Click Send Button
        mConsultChatView.setOnClickSendButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new message
                Message message = new Message.Builder()
                        .setUser(me)
                        .setRightMessage(true)
                        .setMessageText(mConsultChatView.getInputText())
                        .hideIcon(true)
                        .build();
                //Set to chat view
                mConsultChatView.send(message);
                //Reset edit text
                mConsultChatView.setInputText("");

                //Receive message

                final Message receivedMessage = new Message.Builder()
                        .setUser(you)
                        .setRightMessage(false)
                        //.setMessageText(ChatBot.talk(me.getName(), message.getMessageText()))
                        //.setMessageText(chatBot.talk(me.getName(), message.getMessageText()))
                        .setMessageText("Am current offline, Pleased post your inquiry")
                        .build();

                // This is a demo bot
                // Return within 3 seconds
                int sendDelay = (new Random().nextInt(4) + 1) * 1000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mConsultChatView.receive(receivedMessage);
                    }
                }, sendDelay);
            }

        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        //return super.onSupportNavigateUp();
        onBackPressed();
        return true;
    }
}
