package gdg.androidtitlan.androidchatmaterialdesign;

import android.content.Context;
import android.support.annotation.NonNull;
import com.firebase.client.Firebase;

/**
 * 14/05/16.
 */
public class FireBase {

  private static final String FIRE_BASE_URL = "https://android-chat-gds.firebaseio.com/";
  private static final String FIRE_BASE_CHILD = "chat";

  private static Firebase firebase;

  private FireBase() {
  }

  public static Firebase getInstance(@NonNull Context context) {
    Firebase.setAndroidContext(context);
    if (firebase == null) firebase = new Firebase(FIRE_BASE_URL).child(FIRE_BASE_CHILD);

    return firebase;
  }
}
