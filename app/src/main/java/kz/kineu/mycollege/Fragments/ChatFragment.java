package kz.kineu.mycollege.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketHandler;
import kz.kineu.mycollege.Entities.ChatMessage;
import kz.kineu.mycollege.R;


public class ChatFragment extends Fragment {

    private final String LOG_TAG = "MyCollegeApp";
    private final String url = "ws://78.46.123.237:7777/chat";
    private final WebSocketConnection wsConnection;
    private ImageButton sendButton;
    private LinearLayout linearLayout;
    private EditText messageText;
    private ObjectMapper objectMapper;
    private String name;

    {
        name = "";
        wsConnection = new WebSocketConnection();
        objectMapper = new ObjectMapper();
    }

    public ChatFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        linearLayout = (LinearLayout) view.findViewById(R.id.llChat);
        sendButton = (ImageButton) view.findViewById(R.id.ibSend);
        messageText = (EditText) view.findViewById(R.id.etMessage);
        if(name.isEmpty()) {
            getNameDialog();
        }
        if(!wsConnection.isConnected()) {
            establishConnection();
        }
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.isEmpty()) {
                    getNameDialog();
                } else {
                    String text = messageText.getText().toString();
                    ChatMessage chatMessage = new ChatMessage(name, text);
                    if (!text.isEmpty()) {
                        try {
                            wsConnection.sendTextMessage(objectMapper.writeValueAsString(chatMessage));
                            messageText.setText("");
                        } catch (Exception e) {
                            Toast.makeText(getContext(), "Возникла ошибка при отправке сообщения", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Я не могу отправить пустое сообщение", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        sendButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getNameDialog();
                return true;
            }
        });
        return view;
    }

    private void establishConnection() {
        try {

            wsConnection.connect(url,new WebSocketHandler() {
                @Override
                public void onOpen() {
                    Log.d(LOG_TAG,"Connection established");
                }

                @Override
                public void onClose(int code, String reason) {
                    Log.d(LOG_TAG,"Connection lost");
                }

                @Override
                public void onTextMessage(String payload) {
                    ArrayList<ChatMessage> messages = null;
                    try {
                        messages = new ObjectMapper().readValue(payload, new TypeReference<ArrayList<ChatMessage>>(){});
                        for (ChatMessage tmp:messages) {
                            View view = getActivity().getLayoutInflater().inflate(R.layout.chat_item, linearLayout, false);
                            TextView tvName = (TextView) view.findViewById(R.id.tvChatName);
                            TextView tvText = (TextView) view.findViewById(R.id.tvChatMessge);
                            tvName.setText(tmp.getName()+":");
                            tvText.setText(tmp.getMessage());
                            linearLayout.addView(view);
                        }
                    } catch (Exception e) {
                        Log.e(LOG_TAG,"Exception catched "+e.getMessage());
                    }
                }


            });

        } catch (Exception e) {

        }
    }

    private void getNameDialog() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Чат");
        alertDialog.setMessage("Введи свое имя:");
        final EditText input = new EditText(getActivity());
        alertDialog.setView(input);

        alertDialog.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    if (name.isEmpty()) {
                        name = input.getText().toString();

                    } else {

                    }
            }
        });

        alertDialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        alertDialog.show();
    }
}
