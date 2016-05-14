/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gdg.androidtitlan.androidchatmaterialdesign.view;

import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import gdg.androidtitlan.androidchatmaterialdesign.Config;
import gdg.androidtitlan.androidchatmaterialdesign.R;
import gdg.androidtitlan.androidchatmaterialdesign.model.Chat;
import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements View.OnClickListener {

  public MainFragment() {
  }

  private Firebase firebase;
  private List<Chat> chatList;
  private String idDevice;
  private ChatAdapter chatAdapter;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Config.getFirebaseInitialize(getActivity());
    firebase = Config.getFirebaseReference();
    chatList = new ArrayList<>();
    idDevice =
        Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
  }

  EditText editTxtMessage;
  RecyclerView recyclerViewChat;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_main, container, false);

    initializeView(rootView);
    return rootView;
  }

  private void setUpRecyclerView(RecyclerView recyclerView, ChatAdapter chatAdapter) {
    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    recyclerView.setAdapter(chatAdapter);
  }

  private void initializeView(View rootView) {

    Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

    if (actionBar != null) {
      actionBar.setTitle(getString(R.string.app_name) + " - " + Config.getMail(getActivity()));
    }

    editTxtMessage = (EditText) rootView.findViewById(R.id.edit_txt_message);
    recyclerViewChat = (RecyclerView) rootView.findViewById(R.id.recycler_view_chat);
    ((FloatingActionButton) rootView.findViewById(R.id.button_sent)).setOnClickListener(this);

    chatAdapter = new ChatAdapter(chatList, idDevice);
    setUpRecyclerView(recyclerViewChat, chatAdapter);
    getChatMessages();
  }

  private void getChatMessages() {

    firebase.addChildEventListener(new ChildEventListener() {
      @Override public void onChildAdded(DataSnapshot dataSnapshot, String s) {

        if (dataSnapshot != null && dataSnapshot.getValue() != null) {
          Chat model = dataSnapshot.getValue(Chat.class);
          chatList.add(model);
          recyclerViewChat.scrollToPosition(chatList.size() - 1);
          chatAdapter.notifyItemInserted(chatList.size() - 1);
        }
      }

      @Override public void onChildChanged(DataSnapshot dataSnapshot, String s) {

      }

      @Override public void onChildRemoved(DataSnapshot dataSnapshot) {

      }

      @Override public void onChildMoved(DataSnapshot dataSnapshot, String s) {

      }

      @Override public void onCancelled(FirebaseError firebaseError) {
        firebaseError.getMessage();
      }
    });
  }

  private void getMessageToSent() {

    String message = editTxtMessage.getText().toString();
    if (!message.isEmpty()) firebase.push().setValue(new Chat(message, idDevice));

    editTxtMessage.setText("");
  }

  @Override public void onClick(View view) {
    getMessageToSent();
  }
}
