package gdg.androidtitlan.firebase;

import com.firebase.client.AuthData;
import com.firebase.client.FirebaseError;
import gdg.androidtitlan.firebase.login.LoginContract;
import gdg.androidtitlan.firebase.login.LoginPresenter;
import gdg.androidtitlan.firebase.login.UserCredential;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * 16/05/16.
 */

@RunWith(MockitoJUnitRunner.class) public class LoginPresenterTest {

  @Mock LoginContract.View view;

  @Mock FirebaseError firebaseError;

  @Mock AuthData authData;

  private LoginPresenter presenter;

  @Before public void setUp() {
    presenter = new LoginPresenter(view);
  }

  @Test public void testLoginCreatingUserOnFirebase() {
    UserCredential userCredential = getFakeUserCredential();
    presenter.login(userCredential);
    verify(view).showProgress(true);
    verify(view).fireBaseCreateUser(userCredential);
  }

  @Test public void testAuthFirebase() {
    UserCredential userCredential = getFakeUserCredential();
    presenter.auth(userCredential);
    verify(view).fireBaseAuthWithPassword(userCredential);
    verify(view).showProgress(false);
  }

  @Test public void testAuthErrorFirebase() {
    UserCredential userCredential = getFakeUserCredential();
    presenter.authError(userCredential, firebaseError);
    verify(view).fireBaseAuthWithPassword(userCredential);
    verify(view).showProgress(false);
  }

  @Test public void testAuthStateListener() {
    presenter.authStateListener();
    verify(view).fireBaseAuthStateListener();
  }

  @Test public void testAuthStateChanged() {
    //authData is null
    presenter.authStateChanged(authData);
    verify(view).launchChatActivity(null);
  }

  private UserCredential getFakeUserCredential() {
    return new UserCredential("TEST_MAIL@MAIL.COM", "TEST_PASS");
  }
}
