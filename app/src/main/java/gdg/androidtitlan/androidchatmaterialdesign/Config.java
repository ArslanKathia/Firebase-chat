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

package gdg.androidtitlan.androidchatmaterialdesign;

import android.content.Context;
import android.content.SharedPreferences;

import com.firebase.client.Firebase;

public class Config {

    // TODO: change this to your own Firebase URL
    private static final String FIREBASE_URL = "https://android-chat-gds.firebaseio.com/";
    private static final String FIREBASE_CHILD = "chat";
    private static final String FIREBASE_MAIL = "email";
    public static final String USER_MAIL = "user_mail";

    private static final String PREFERENCE_MAIL = "pref_email";
    private static final String PREFERENCE_USER_MAIL = "pref_user_email";

    public static String getFirebaseMail() {
        return FIREBASE_MAIL;
    }

    public static void getFirebaseInitialize(Context context) {
        Firebase.setAndroidContext(context);
    }

    public static Firebase getFirebaseReference(){
       return new Firebase(FIREBASE_URL).child(FIREBASE_CHILD);
    }

    public static void setMail(Context context ,String mail){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_MAIL, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(PREFERENCE_USER_MAIL, mail).apply();
    }

    public static String getMail(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_MAIL, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PREFERENCE_USER_MAIL, "soy_un_mail");
    }

}
