package gdg.androidtitlan.androidchatmaterialdesign.login;

import com.firebase.client.AuthData;
import com.firebase.client.FirebaseError;

/**
 * 14/05/16.
 */
public interface LoginContract {

  interface View {

    void showProgress(boolean state);

    void firebaseCreateUser(UserCredential credential);

    void firebaseAuthWithPassword(UserCredential credential);

    void firebaseAuthStateListener();

    void launchChatActivity(String userName);
  }

  interface UserActionListener {

    void login(UserCredential credential);

    void auth(UserCredential credential);

    void authError(UserCredential credential,FirebaseError firebaseError);

    void authStateListener();

    void authStateChanged(AuthData authData);
  }
}
