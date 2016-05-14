package gdg.androidtitlan.androidchatmaterialdesign.login;

import android.support.annotation.NonNull;
import com.firebase.client.AuthData;
import com.firebase.client.FirebaseError;

/**
 * 14/05/16.
 */
public class LoginPresenter implements LoginContract.UserActionListener {

  private static final String FIRE_BASE_MAIL = "email";
  private LoginContract.View view;

  public LoginPresenter(@NonNull LoginContract.View view) {
    this.view = view;
  }

  @Override public void login(UserCredential credential) {
    view.showProgress(true);
    view.firebaseCreateUser(credential);
  }

  @Override public void auth(UserCredential credential) {
    view.firebaseAuthWithPassword(credential);
    view.showProgress(false);
  }

  @Override public void authError(UserCredential credential, FirebaseError firebaseError) {
    view.firebaseAuthWithPassword(credential);
    view.showProgress(false);
  }

  @Override public void authStateListener() {
    view.firebaseAuthStateListener();
  }

  @Override public void authStateChanged(AuthData authData) {
    String userName = ((String) authData.getProviderData().get(FIRE_BASE_MAIL));
    view.launchChatActivity(userName);
  }
}
