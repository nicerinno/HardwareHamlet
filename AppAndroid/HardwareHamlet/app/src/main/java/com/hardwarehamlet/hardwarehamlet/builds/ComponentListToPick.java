package com.hardwarehamlet.hardwarehamlet.builds;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.hardwarehamlet.hardwarehamlet.R;
import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.model.Build_Components;
import com.hardwarehamlet.hardwarehamlet.model.Components;
import com.hardwarehamlet.hardwarehamlet.repositories.ComponentsRepository;

import java.util.List;

import static com.hardwarehamlet.hardwarehamlet.builds.BuildsDetails.BUILD_ID;


public class ComponentListToPick extends AppCompatActivity {
    private ComponentsListToPickAdapter adapter;
    private int component_type;
    public static final String COMPONENT_TYPE_ID = "component_type_id";
    private ListView listView;
    private EditText searchBar;
    private EditText editTextQuantity;
    public static String sort_value = "desc";
    public static String search = "";
    public ImageButton buttonSort;
    private AppDatabase appDatabase;
    private long build_id;

    public static void setSort_value(String sort_value) {
        ComponentListToPick.sort_value = sort_value;
    }

    public static void setSearch(String search) {
        ComponentListToPick.search = search;
    }

    public static String getSort_value() {
        return sort_value;
    }

    public static String getSearch() {
        return search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component_list_to_pick);

        this.adapter = new ComponentsListToPickAdapter(this);
        this.listView = findViewById(R.id.listView);
        this.searchBar = findViewById(R.id.editTextSearchToPick);
        this.editTextQuantity = findViewById(R.id.editTextQuantity);
        this.buttonSort = findViewById(R.id.buttonSortByPrice);
        this.listView.setAdapter(this.adapter);
        setTitle("Escolher componente");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (null == bundle) {finish();}
        else{
            this.component_type = bundle.getInt(COMPONENT_TYPE_ID,-1);
            this.build_id = bundle.getLong(BUILD_ID);
        }

        this.appDatabase = AppDatabase.getInstance(this);
        ComponentsRepository.getComponents(this, new ComponentsRepository.ComponentsListCallback() {
            @Override
            public void onResult(final List<Components> componentsList) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        List<Components> list = componentsList;
//                        adapter.refreshList(list);
                        adapter.refreshList(componentsList);
                    }
                });

            }

            @Override
            public void onResult(Components component) {

            }

            @Override
            public void onError(String error) {

            }
        },sort_value,component_type,"none");
        if(component_type == 6){
            editTextQuantity.setEnabled(false);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, final long id) {
                if(editTextQuantity.getText().toString().matches("^[0-9]+$")){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Build_Components build_components = new Build_Components(build_id,id,Integer.parseInt(editTextQuantity.getText().toString()));
                            appDatabase.getBuildComponentsDAO().insertBuildComponent(build_components);
                            setResult(component_type);
                            finish();
                        }
                    }).start();
                }else{
                    Snackbar.make(view,"Apenas numeros são aceites no campo Quantidade",Snackbar.LENGTH_SHORT);
                }

            }
        });
    }

    public void sortPrice(final View view) {
        if(this.searchBar.getText().toString().equals("")){
            setSearch("none");
        }else{
            setSearch(this.searchBar.getText().toString());
        }

        if(sort_value.equals("desc")){
            setSort_value("asc");
            buttonSort.setImageResource(R.drawable.asc);
        }else{
            setSort_value("desc");
            buttonSort.setImageResource(R.drawable.desc);
        }

        ComponentsRepository.getComponents(ComponentListToPick.this, new ComponentsRepository.ComponentsListCallback() {
            @Override
            public void onResult(final List<Components> componentsList) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.refreshList(componentsList);
                    }
                });
            }

            @Override
            public void onResult(Components component) {

            }

            @Override
            public void onError(String error) {
                Snackbar.make(view,error, Snackbar.LENGTH_LONG).show();
            }
        },sort_value,this.component_type, search);
    }

    public void searchComponent(final View view) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        String search2 = this.searchBar.getText().toString();
        if(search2.equals("")){
            setSearch("none");
        }else{
            setSearch(search2);
        }
        //ComponentsList.setSearch(search);
        ComponentsRepository.getComponents(this, new ComponentsRepository.ComponentsListCallback() {
            @Override
            public void onResult(final List<Components> componentsList) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.refreshList(componentsList);
                    }
                });
            }

            @Override
            public void onResult(Components component) {

            }

            @Override
            public void onError(String error) {
                Snackbar.make(view,error, Snackbar.LENGTH_LONG).show();
            }
        },sort_value,this.component_type, search);
    }

    public void cancelComponentSelection(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
        finish();
    }
}
