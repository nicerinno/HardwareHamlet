package com.hardwarehamlet.hardwarehamlet;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.hardwarehamlet.hardwarehamlet.data.remote.DataService;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataSource;
import com.hardwarehamlet.hardwarehamlet.model.ResponseBody;
import com.hardwarehamlet.hardwarehamlet.model.UserRegistBody;
import com.hardwarehamlet.hardwarehamlet.preferences_manager.PreferencesManager;
import com.hardwarehamlet.hardwarehamlet.repositories.UsersRepository;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextPasswordConf;
    private EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.editTextUsername = findViewById(R.id.editTextUsername);
        this.editTextPassword = findViewById(R.id.editTextPassword);
        this.editTextPasswordConf = findViewById(R.id.editTextPasswordConf);
        this.editTextEmail = findViewById(R.id.editTextEmail);
    }

    public void signUp(View view) {
        final String username = this.editTextUsername.getText().toString();
        String password = this.editTextPassword.getText().toString();
        String passwordConf = this.editTextPasswordConf.getText().toString();
        String email = this.editTextEmail.getText().toString();

        if(password.equals(passwordConf)){
            DataService dataService = DataSource.getDataService();

            UserRegistBody body = new UserRegistBody(username,email,password);

            Call<ResponseBody> call = dataService.userRegistration(body);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()){
                        ResponseBody responseBody = response.body();
                        if((responseBody != null && responseBody.getResult().equals("successful and email sent"))){
                            Snackbar.make(findViewById(android.R.id.content),"Registado com sucesso", Snackbar.LENGTH_LONG).show();

                            UsersRepository.getUserByUsername(getApplicationContext(), new UsersRepository.UserIdCallback(){
                                @Override
                                public void onResult(long id) {
                                        PreferencesManager.startSession(SignUpActivity.this,id);
                                    finish();
                                }
                            },username);
                            startActivity(new Intent(SignUpActivity.this,NavActivity.class));
                        }else if((responseBody != null && responseBody.getResult().equals("successful but email not sent"))){
                            Snackbar.make(findViewById(android.R.id.content),"Email inválido", Snackbar.LENGTH_LONG).show();
                        }else if((responseBody != null && responseBody.getResult().equals("username already exists"))){
                            Snackbar.make(findViewById(android.R.id.content),"Este username já está a ser utilizado", Snackbar.LENGTH_LONG).show();
                        }
                        finish();
                    }else Snackbar.make(findViewById(android.R.id.content),"Algo correu mal. Erro: " + response.code(), Snackbar.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Snackbar.make(findViewById(android.R.id.content),"Algo correu mal com o servidor ", Snackbar.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });

        }else Snackbar.make(findViewById(android.R.id.content),"As passwords não coincidem. Tente novamente", Snackbar.LENGTH_LONG).show();

    }

    public void cancelSignup(View view) {
        finish();
    }
}
