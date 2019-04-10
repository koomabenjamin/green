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

import com.google.firebase.auth.FirebaseAuth;
import com.shapeworks.mivule.R;
import com.github.bassaer.chatmessageview.model.User;
import com.github.bassaer.chatmessageview.models.Message;
import com.github.bassaer.chatmessageview.util.ChatBot;
import com.github.bassaer.chatmessageview.views.ChatView;

import java.util.Random;

public class d_forum extends AppCompatActivity {

    private ChatView mForumChatView;
    private ChatBot chatBot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_forum);



        Bundle extras = getIntent().getExtras();

        Toolbar toolbar = (Toolbar) findViewById(R.id.forum_toolbar);
        toolbar.setTitle(extras.getString("name"));
        FirebaseAuth auth = FirebaseAuth.getInstance();
        setSupportActionBar(toolbar);

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
        String yourName = extras.getString("title");

        final User me = new User(myId, myName, myIcon);
        final User you = new User(yourId, yourName, yourIcon);

        mForumChatView = (ChatView)findViewById(R.id.chat_view);
        //Set UI parameters if you need
        mForumChatView.setRightBubbleColor(ContextCompat.getColor(this, R.color.green500));
        mForumChatView.setLeftBubbleColor(Color.WHITE);
        mForumChatView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorChatLight));
        mForumChatView.setSendButtonColor(ContextCompat.getColor(this, R.color.colorAccent));
        mForumChatView.setSendIcon(R.drawable.ic_action_send);
        mForumChatView.setRightMessageTextColor(Color.WHITE);
        mForumChatView.setLeftMessageTextColor(Color.BLACK);
        mForumChatView.setUsernameTextColor(Color.WHITE);
        mForumChatView.setSendTimeTextColor(Color.WHITE);
        mForumChatView.setDateSeparatorColor(Color.WHITE);
        mForumChatView.setInputTextHint("new message...");
        mForumChatView.setMessageMarginTop(5);
        mForumChatView.setMessageMarginBottom(5);

        //Click Send Button
        mForumChatView.setOnClickSendButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new message
                Message message = new Message.Builder()
                        .setUser(me)
                        .setRightMessage(true)
                        .setMessageText(mForumChatView.getInputText())
                        .hideIcon(true)
                        .build();
                //Set to chat view
                mForumChatView.send(message);
                //Reset edit text
                mForumChatView.setInputText("");

                //Receive message

                final Message receivedMessage = new Message.Builder()
                        .setUser(you)
                        .setRightMessage(false)
                        //.setMessageText(ChatBot.talk(me.getName(), message.getMessageText()))
                        //.setMessageText(chatBot.talk(me.getName(), message.getMessageText()))
                        .setMessageText("recieved")
                        .build();

                // This is a demo bot
                // Return within 3 seconds
                int sendDelay = (new Random().nextInt(4) + 1) * 1000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mForumChatView.receive(receivedMessage);
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
