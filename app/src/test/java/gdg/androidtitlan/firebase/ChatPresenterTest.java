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

/**
 * 16/05/16.
 */

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
