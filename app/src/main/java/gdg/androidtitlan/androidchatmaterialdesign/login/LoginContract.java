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

package gdg.androidtitlan.androidchatmaterialdesign.login;

import com.firebase.client.AuthData;
import com.firebase.client.FirebaseError;

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
