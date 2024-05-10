package com.zeyneptekin.myhealthassistant;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatFragment extends Fragment {
    private final Handler responseHandler = new Handler(Looper.getMainLooper());
    private ArrayList<ChatMessageClass> messages;
    private ChatAdapter adapter;

    public ChatFragment() {
        // Boş yapıcı metot
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        RecyclerView chatRecyclerView = view.findViewById(R.id.chatRecyclerView);
        EditText messageEditText = view.findViewById(R.id.messageEditText);
        messages = new ArrayList<>();

        adapter = new ChatAdapter(messages);
        chatRecyclerView.setAdapter(adapter);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        messageEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
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
