package com.hardwarehamlet.hardwarehamlet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.hardwarehamlet.hardwarehamlet.builds.AddBuild;
import com.hardwarehamlet.hardwarehamlet.builds.Build;
import com.hardwarehamlet.hardwarehamlet.components.ComponentsList;
import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataService;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataSource;
import com.hardwarehamlet.hardwarehamlet.model.ResponseBody;
import com.hardwarehamlet.hardwarehamlet.preferences_manager.PreferencesManager;
import com.hardwarehamlet.hardwarehamlet.ranking.Rankings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hardwarehamlet.hardwarehamlet.builds.Build.REQUEST_BUILD_CREATOR;


public class NavActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private AppDatabase appDatabase;
    private long lastComponentId;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            int id = item.getItemId();
            if(id== R.id.navigation_home){
                setTitle("Home");
                fragment = new Home();
            } else if(id==R.id.navigation_components){
                setTitle("Components");
                fragment = new ComponentsList();
            }else if(id==R.id.navigation_builds){
                setTitle("Builds");
                fragment = new Build();
            } else if(id==R.id.navigation_rankings){
                setTitle("Rankings");
                fragment = new Rankings();
            }

            if(fragment != null){
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();

                //Change the screen_area content for the fragment we chose
                ft.replace(R.id.screen_area, fragment);
                ft.addToBackStack("fragment_stack");
                ft.commit();
            }

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Fragment fragment = new Home();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        setTitle("Home");
        //Change the screen_area content for the fragment we chose
        ft.replace(R.id.screen_area, fragment);
        ft.addToBackStack("fragment_stack");
        ft.commit();
    }


    //handling the onClick of the Search Button
    public void search(final View view) {
        //closing virtual keyboard after button pressed to clear the view
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DataService dataService = DataSource.getDataService();
        if(item.getItemId() == R.id.logout){
            PreferencesManager.endSession(this);
            finish();
            startActivity(new Intent(this,Login.class));
            return true;
        }else if(item.getItemId() == R.id.buttonAddBuild){
            if(PreferencesManager.getSavedUserId(this) != 0){
                Call<ResponseBody> call = dataService.checkIfActive(PreferencesManager.getSavedUserId(this));
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            ResponseBody responseBody = response.body();
                            if(responseBody.getResult().equals("active")){
                                Intent intent = new Intent(NavActivity.this, AddBuild.class);
                                startActivityForResult(intent,REQUEST_BUILD_CREATOR);
                            }else{
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
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }else{
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content)
                        ,"Apenas utilizadores podem aceder a esta fucionalidade."
                        , Snackbar.LENGTH_LONG);
                snackbar.setAction("Criar conta", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(NavActivity.this, SignUpActivity.class);
                        startActivity(intent);
                    }
                });
                snackbar.show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_BUILD_CREATOR){
            if(resultCode == RESULT_OK){
                    Snackbar.make(findViewById(android.R.id.content),"Build registada com sucesso"
                            , Snackbar.LENGTH_SHORT).show();
            }else if(resultCode == RESULT_CANCELED){
                if(AddBuild.getBuildHolder() != null){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            AppDatabase appDatabase = AppDatabase.getInstance(NavActivity.this);
                            appDatabase.getBuildsDAO().deleteBuild(AddBuild.getBuildHolder().getBuild_id());
                            appDatabase.getBuildComponentsDAO().deleteBuildComponents(AddBuild.getBuildHolder().getBuild_id());
                        }
                    }).start();
                }
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
