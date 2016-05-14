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

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{



    private Firebase mFirebaseReference;
    private String mUsername;
    TextView  txtEmail, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Config.getFirebaseInitialize(this);
        mFirebaseReference = Config.getFirebaseReference();
        initializeView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getAuthStateListener();
    }

    /**
     * Inicializamos nuestras vistas
     */

    private void initializeView(){

        ((FloatingActionButton)findViewById(R.id.button_login)).setOnClickListener(this);
        txtEmail = (TextView)findViewById(R.id.edit_txt_mail);
        txtPassword  =(TextView)findViewById(R.id.edit_txt_pass);
    }


    /**
     * Agregamos una listener a nuestra referencia para saber si estamos Autentificados
     */

    private void getAuthStateListener(){

        mFirebaseReference.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    mUsername = ((String) authData.getProviderData().get(Config.getFirebaseMail()));
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra(Config.USER_MAIL,mUsername);
                    startActivity(intent);
                    Config.setMail(LoginActivity.this,mUsername);
                    Toast.makeText(LoginActivity.this,"Bienvenido! "+ mUsername,Toast.LENGTH_SHORT).show();

                } else {
                    mUsername = null;

                }
            }
        });
    }

    /**
     * @param email correo del usuario
     * @param password contraseña del usuario
     *
     * Este metodo hace login con firebase si el usuario no existe lo crea
     *
     */

    private void firebaseLogin (final String email, final String password){

        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, null,getString(R.string.login_progress_dialog), true);
        mFirebaseReference.createUser(email, password, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                mFirebaseReference.authWithPassword(email, password, null);
                progressDialog.dismiss();
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                mFirebaseReference.authWithPassword(email, password, null);
                progressDialog.dismiss();
            }
        });

    }


    /**
     *
     * @param view listener para el FloatingActionButton
     */

    @Override
    public void onClick(View view) {
        firebaseLogin(txtEmail.getText().toString(), txtPassword.getText().toString());
    }
}
