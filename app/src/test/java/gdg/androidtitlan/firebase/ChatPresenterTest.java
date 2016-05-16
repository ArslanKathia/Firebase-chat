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

import com.firebase.client.DataSnapshot;
import gdg.androidtitlan.firebase.chat.ChatContract;
import gdg.androidtitlan.firebase.chat.ChatPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class) public class ChatPresenterTest {

  @Mock ChatContract.View view;
  @Mock DataSnapshot dataSnapshot;

  private ChatPresenter presenter;

  @Before public void setUp() {
    presenter = new ChatPresenter(view);
  }

  @Test public void testSendMessage() {
    presenter.send();
    verify(view).sendMessage();
  }

  @Test public void testChildAdded() {
    final String s = "test";
    presenter.childAdded(dataSnapshot, s);
    verify(view).fireBaseOnChildAdded(dataSnapshot, s);
  }
}
