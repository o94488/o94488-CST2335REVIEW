package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {

    protected EditText chatEditText;
    private ArrayList<Message> chatMessage = new ArrayList<>();
    protected static final String ACTIVITY_NAME = "ChatRoomActivity";
    protected static final int SEND_MESSAGE = 1;
    protected static final int RECEIVE_MESSAGE = 2;

    ChatAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chatroom);

        ListView listView = findViewById(R.id.listView);
        chatEditText = findViewById(R.id.inputText);

        messageAdapter = new ChatAdapter();
        listView.setAdapter(messageAdapter);

        Button buttonSend = findViewById(R.id.sendButton);

        buttonSend.setOnClickListener((send) ->{

            chatMessage.add(new Message(chatEditText.getText().toString(),SEND_MESSAGE));
            messageAdapter.notifyDataSetChanged();

            ContentValues newValues = new ContentValues();

            chatEditText.setText(" ");
            });





        Button buttonReceive = findViewById(R.id.receiveButton);

        buttonReceive.setOnClickListener((receive) ->{
            chatMessage.add(new Message(chatEditText.getText().toString(),RECEIVE_MESSAGE));
            messageAdapter.notifyDataSetChanged();

            ContentValues newValues = new ContentValues();

            chatEditText.setText(" ");
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Context context = view.getContext();

                TextView textViewItem = ((TextView) view.findViewById(R.id.textView));

                // get the clicked item name
                String listItemText = textViewItem.getText().toString();

                // just toast it
                Toast.makeText(context, "Item: " + listItemText , Toast.LENGTH_SHORT).show();

                Log.d("chatListView", "onItemClick: " + i + " " + l);
            }
        });
    }

    public class ChatAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return chatMessage.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Message getItem(int position) {
            return chatMessage.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatRoomActivity.this.getLayoutInflater();
            View result = null;

            if (getItem(position).getMessage_type() == RECEIVE_MESSAGE){
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            }else {
                result = inflater.inflate(R.layout.chat_row_outgoing,null);
            }

            TextView message = (TextView) result.findViewById(R.id.textView);
            message.setText(getItem(position).getMessage());

            return result;
        }
    }

}
