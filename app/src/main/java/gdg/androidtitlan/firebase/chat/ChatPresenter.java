package gdg.androidtitlan.firebase.chat;

import android.support.annotation.NonNull;
import com.firebase.client.DataSnapshot;

/**
 * 15/05/16.
 */
public class ChatPresenter implements ChatContract.UserActionListener {

  private ChatContract.View view;

  public ChatPresenter(@NonNull ChatContract.View view) {
    this.view = view;
  }

  @Override public void send() {
    view.sendMessage();
  }

  @Override public void childAdded(DataSnapshot dataSnapshot, String s) {
    view.fireBaseOnChildAdded(dataSnapshot, s);
  }
}
