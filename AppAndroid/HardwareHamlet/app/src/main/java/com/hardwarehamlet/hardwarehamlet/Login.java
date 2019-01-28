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
import android.widget.CheckBox;
import android.widget.EditText;



import com.hardwarehamlet.hardwarehamlet.data.remote.DataService;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataSource;
import com.hardwarehamlet.hardwarehamlet.model.Builds;
import com.hardwarehamlet.hardwarehamlet.model.Components;
import com.hardwarehamlet.hardwarehamlet.model.ResponseBody;
import com.hardwarehamlet.hardwarehamlet.model.UserRegistBody;
import com.hardwarehamlet.hardwarehamlet.preferences_manager.PreferencesManager;
import com.hardwarehamlet.hardwarehamlet.repositories.BuildsRepository;
import com.hardwarehamlet.hardwarehamlet.repositories.ComponentsRepository;
import com.hardwarehamlet.hardwarehamlet.repositories.MedalsRepository;
import com.hardwarehamlet.hardwarehamlet.repositories.TitlesRepository;
import com.hardwarehamlet.hardwarehamlet.repositories.UsersRepository;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

import retrofit2.Response;


public class Login extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(PreferencesManager.getSession(this)){
            startActivity(new Intent(Login.this,NavActivity.class));
        }

        this.username = findViewById(R.id.editTextUsername);
        this.password = findViewById(R.id.editTextPassword);
        this.checkBox = findViewById(R.id.checkBox);
        getSupportActionBar().hide();


        loadSomeData();
    }

    public void accountValidation(final View view) {
        final String username = this.username.getText().toString();
        String password = this.password.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            Snackbar.make(findViewById(android.R.id.content)
                    ,"Username ou password vazios. Preencha todos os campos", Snackbar.LENGTH_LONG).show();
        }else{
            DataService dataService = DataSource.getDataService();

            UserRegistBody body = new UserRegistBody(username,username,password);

            Call<ResponseBody> call = dataService.userValidation(body);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()){
                        ResponseBody responseBody = response.body();

                        if((responseBody != null && responseBody.getResult().equals("incorrect password"))){
                            Snackbar.make(findViewById(android.R.id.content),"Password incorreta."
                                    , Snackbar.LENGTH_LONG).show();
                        }else if(responseBody != null && responseBody.getResult().equals("username does not exist")) {
                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content)
                                    ,"Este utilizador não existe.", Snackbar.LENGTH_LONG);
                            snackbar.setAction("Criar conta", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(Login.this,SignUpActivity.class));
                                }
                            });
                            snackbar.show();
                        }else if(responseBody != null && responseBody.getResult().equals("user inactive")){
                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content)
                                    ,"Conta inactiva. Por favor, consulte o seu email.", Snackbar.LENGTH_LONG);
                            snackbar.setAction("EMAIL", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Intent.ACTION_MAIN);
                                    intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                                    startActivity(intent);
                                }
                            });
                            snackbar.show();
                        }
                        else{
                            UsersRepository.getUserByUsername(getApplicationContext(), new UsersRepository.UserIdCallback(){
                                @Override
                                public void onResult(long id) {
                                    if(checkBox.isChecked()){
                                        PreferencesManager.startSession(Login.this,id);
                                        startActivity(new Intent(Login.this,NavActivity.class));
                                        finish();
                                    }else{
                                        PreferencesManager.saveUserId(Login.this,id);
                                        startActivity(new Intent(Login.this,NavActivity.class));
                                    }
                                    finish();
                                }
                            },username);
                        }
                    } else Snackbar.make(findViewById(android.R.id.content)
                            ,"Algo correu mal. Erro: " + response.code(), Snackbar.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Snackbar.make(findViewById(android.R.id.content),"Incorrect password. On failure"
                            , Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }

    private void loadSomeData(){
        ComponentsRepository.getComponents(this, new ComponentsRepository.ComponentsListCallback() {
            @Override
            public void onResult(List<Components> componentsList) {

            }

            @Override
            public void onResult(Components component) {

            }

            @Override
            public void onError(String error) {

            }
        },"desc",0,"none");

        BuildsRepository.getBuilds(this, new BuildsRepository.AllBuildsCallback() {
            @Override
            public void onResult(List<Builds> buildsList) {

            }

            @Override
            public void onError(String error) {

            }
        },"desc",0,"none");

        MedalsRepository.getMedalsInfo(this);
        TitlesRepository.getTitlesInfo(this);
    }

    public void accountRegistration(View view) {
        startActivity(new Intent(this,SignUpActivity.class));
    }

    public void enterAsGuest(View view) {
        startActivity(new Intent(Login.this,NavActivity.class));
        finish();
    }
}
