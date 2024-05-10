package com.zeyneptekin.myhealthassistant;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatFragment extends Fragment {
    private final Handler responseHandler = new Handler(Looper.getMainLooper());
    private ArrayList<ChatMessageClass> messages;
    private ArrayAdapter<ChatMessageClass> adapter;

    public ChatFragment() {
        // Boş yapıcı metot
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        ListView chatListView = view.findViewById(R.id.chatListView);
        EditText messageEditText = view.findViewById(R.id.messageEditText);
        messages = new ArrayList<>();

        adapter = new ArrayAdapter<ChatMessageClass>(requireContext(), android.R.layout.simple_list_item_1, messages) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView textView = view.findViewById(android.R.id.text1);
                ChatMessageClass message = messages.get(position);

                textView.setText(message.getContent());

                // Mesajın kullanıcı tarafından mı asistan tarafından mı gönderildiğini kontrol et
                if (message.getIsUserMessage()) {
                    // Kullanıcı tarafından gönderilmişse
                    textView.setBackgroundResource(R.drawable.user_message_background);
                } else {
                    // Asistan tarafından gönderilmişse
                    textView.setBackgroundResource(R.drawable.assistant_message_background);
                }

                return view;
            }
        };

        chatListView.setAdapter(adapter);

        messageEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendMessage(messageEditText);
                return true;
            }
            return false;
        });

        return view;
    }

    private void sendMessage(EditText messageEditText) {
        String messageText = messageEditText.getText().toString();
        ChatMessageClass userMessage = new ChatMessageClass("Siz\n" + messageText, true); // Kullanıcı tarafından gönderilen mesaj

        if (!userMessage.getContent().isEmpty()) {
            messages.add(userMessage);
            adapter.notifyDataSetChanged();
            messageEditText.getText().clear();

            // Asistan mesajını almak için OpenAI API'sine istek gönder
            new Thread(() -> {
                String assistantResponse = OpenAIChat.sendRequest(messageText);

                responseHandler.post(() -> {
                    if (assistantResponse != null) {
                        ChatMessageClass assistantMessage = new ChatMessageClass("Assistan\n" + assistantResponse, false); // Asistan tarafından gönderilen mesaj
                        messages.add(assistantMessage);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "API isteği başarısız oldu. Lütfen tekrar deneyin.", Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        }
    }
}
