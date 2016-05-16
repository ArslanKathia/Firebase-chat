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
