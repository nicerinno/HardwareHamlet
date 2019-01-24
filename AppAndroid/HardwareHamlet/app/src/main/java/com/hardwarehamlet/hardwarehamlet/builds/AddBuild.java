package com.hardwarehamlet.hardwarehamlet.builds;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hardwarehamlet.hardwarehamlet.R;
import com.hardwarehamlet.hardwarehamlet.Utility;
import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.model.Build_Components;
import com.hardwarehamlet.hardwarehamlet.model.Build_Type;
import com.hardwarehamlet.hardwarehamlet.model.Builds;
import com.hardwarehamlet.hardwarehamlet.preferences_manager.PreferencesManager;
import com.hardwarehamlet.hardwarehamlet.repositories.BuildTypeRepository;
import com.hardwarehamlet.hardwarehamlet.repositories.BuildsRepository;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

import static com.hardwarehamlet.hardwarehamlet.builds.BuildsDetails.BUILD_ID;
import static com.hardwarehamlet.hardwarehamlet.builds.ComponentListToPick.COMPONENT_TYPE_ID;

public class AddBuild extends AppCompatActivity {
    private EditText editTextBuildName;
    private EditText editTextDescription;
    private ListView listViewComponents;
    private TextView textViewCurrentPrice;
    private Spinner spinner;
    private int build_type;
    private AppDatabase appDatabase;
    private BuildComponentsListAdapter adapter;
    public static int REQUEST_COMPONENT_PICKING = 690;

    public int getBuild_type() {
        return build_type;
    }

    public void setBuild_type(int build_type) {
        this.build_type = build_type;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_build);

        editTextBuildName = findViewById(R.id.editTextBuildName);
        editTextDescription = findViewById(R.id.editTextDescription);
        textViewCurrentPrice = findViewById(R.id.textViewCurrentPrice);
        LinearLayout mainLinear = findViewById(R.id.mainLayout);
        mainLinear.requestFocus();
        setTitle("BUILD CREATOR");
    }

    @Override
    protected void onResume() {
        super.onResume();
        spinner = findViewById(R.id.spinner2);

        BuildTypeRepository.getBuildTypeList(AddBuild.this, new BuildTypeRepository.BuildTypeCallback() {
            @Override
            public void onResult(final List<Build_Type> buildTypeList) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<String> array = new ArrayList<>();
                        for (int i = 0; i < buildTypeList.size(); i++) {
                            array.add(buildTypeList.get(i).getName());
                        }
                        ArrayAdapter<String> adapterArray = new ArrayAdapter<String>(AddBuild.this, R.layout.spinner_style, array);
                        adapterArray.setDropDownViewResource(R.layout.spinner_style);
                        spinner.setAdapter(adapterArray);
                    }
                });
            }

            @Override
            public void onError(String error) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setBuild_type(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        appDatabase = AppDatabase.getInstance(AddBuild.this);
        this.adapter = new BuildComponentsListAdapter(this,AddBuild.this);
        this.listViewComponents = findViewById(R.id.listViewComponentPickedList);
//        listViewComponents.setEnabled(false);
        listViewComponents.setOnItemClickListener(null);
        listViewComponents.setClickable(false);
        listViewComponents.setAdapter(adapter);

    }

    public void cancel(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.getBuildComponentsDAO().deleteBuildComponents(lastSavedBuildId);
            }
        }).start();
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.getBuildComponentsDAO().deleteBuildComponents(lastSavedBuildId);
            }
        }).start();
        setResult(RESULT_CANCELED);
        finish();
    }
    private long lastSavedBuildId;

    public void pickComponents(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                lastSavedBuildId = appDatabase.getBuildsDAO().lastSavedBuildId() + 1;
                Intent intent = new Intent(AddBuild.this,PickComponents.class);
                intent.putExtra(BUILD_ID,lastSavedBuildId);
                startActivityForResult(intent,
                        REQUEST_COMPONENT_PICKING);
            }
        }).start();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_COMPONENT_PICKING){
            if(resultCode == RESULT_OK){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final List<Build_Components> build_components_list = appDatabase.getBuildComponentsDAO()
                                .getBuildComponentesByBuild(lastSavedBuildId);
                        final float price = appDatabase.getBuildComponentsDAO().getBuildPrice(lastSavedBuildId);
                        final String stringPrice = price + "€";
                        if(build_components_list.size() != 0){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    LinearLayout layoutListaComponentes = findViewById(R.id.layoutListaComponentesDetails);
                                    layoutListaComponentes.setVisibility(View.VISIBLE);
                                    textViewCurrentPrice.setText(stringPrice);
                                    adapter.refreshList(build_components_list);
                                    Utility.setListViewHeightBasedOnChildrenTest(listViewComponents);
                                }
                            });
                        }
                    }
                }).start();
            }
        }
    }

    private static Builds buildHolder;

    public static Builds getBuildHolder() {
        return buildHolder;
    }

    public void setBuildHolder(Builds build) {
        buildHolder = build;
    }

    public void buildRegistration(final View view) {
        BuildsRepository.getLastBuildId(AddBuild.this, new BuildsRepository.LastBuildId() {
            @Override
            public void onResult(final long id) {
                final String buildName = editTextBuildName.getText().toString();
                final String description = editTextDescription.getText().toString();
                if (buildName.isEmpty() || description.isEmpty()) {
                    Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_LONG).show();
                } else {
                    if (buildName.length() > 50 || description.length() > 500) {
                        Snackbar.make(view, "Os campos não podem ter mais que 500 caracteres.",
                                Snackbar.LENGTH_LONG).show();
                    } else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                final List<Build_Components> buildComponentsList = appDatabase.getBuildComponentsDAO()
                                        .getBuildComponentesByBuild(lastSavedBuildId);

                                Build_Components checkIfThereIsCase = appDatabase.getBuildComponentsDAO()
                                        .getBuildComponentByType(lastSavedBuildId,11);
                                Build_Components checkIfThereIsCpu = appDatabase.getBuildComponentsDAO()
                                        .getBuildComponentByType(lastSavedBuildId,1);
                                Build_Components checkIfThereIsMotherboard = appDatabase.getBuildComponentsDAO()
                                        .getBuildComponentByType(lastSavedBuildId,3);
                                Build_Components checkIfThereIsRam = appDatabase.getBuildComponentsDAO()
                                        .getBuildComponentByType(lastSavedBuildId,10);
                                Build_Components checkForStorage = appDatabase.getBuildComponentsDAO()
                                        .checkForStorage(lastSavedBuildId);
                                Build_Components checkIfThereIsPSU = appDatabase.getBuildComponentsDAO()
                                        .getBuildComponentByType(lastSavedBuildId,6);

                                final AlertDialog.Builder builder = new AlertDialog.Builder(AddBuild.this);
                                builder.setTitle("Confirmação de criação de build");

                                if(checkIfThereIsCase == null || checkIfThereIsCpu == null
                                        || checkIfThereIsMotherboard == null || checkIfThereIsRam == null
                                         || checkIfThereIsPSU == null || checkForStorage == null){

                                    boolean checkCpu = true;
                                    boolean checkMotherboard = true;
                                    boolean checkRam = true;
                                    boolean checkStorage = true;
                                    boolean checkPsu = true;
                                    boolean checkCase = true;

                                    if(checkIfThereIsCase == null){
                                        checkCase = false;
                                    }
                                    if(checkIfThereIsCpu == null){
                                        checkCpu = false;
                                    }
                                    if(checkIfThereIsMotherboard == null){
                                        checkMotherboard = false;
                                    }
                                    if(checkForStorage == null){
                                        checkStorage = false;
                                    }
                                    if(checkIfThereIsRam == null){
                                        checkRam = false;
                                    }
                                    if(checkIfThereIsPSU == null){
                                        checkPsu = false;
                                    }
                                    builder.setMessage("Notámos que faltam componentes imprescindíveis para que um computador possa funcionar.\n" +
                                            "Componentes que poderão estar em falta:\n" +
                                            "-CPU: " + checkCpu +"\n" +
                                            "-Motherboard: " + checkMotherboard + "\n" +
                                            "-Ram: " + checkRam + "\n" +
                                            "-PSU: " + checkPsu + "\n" +
                                            "-Storage: " + checkStorage + "\n" +
                                            "-Caixa: " + checkCase + "\n" +
                                            "Pretende, ainda assim, continuar? \n Relembramos que uma " +
                                            "build sem uma caixa irá ter menos interação da comunidade.");
                                }else{
                                    builder.setMessage("Deseja confirmar o registo da build? Este é um ponto sem retorno.");
                                }
                                builder.setPositiveButton("Registar mesmo assim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Builds build = new Builds(id, PreferencesManager.getSavedUserId(getApplicationContext())
                                                ,build_type,buildName,description,0,0,System.currentTimeMillis());

                                        setBuildHolder(build);

                                        for (int i = 0; i < buildComponentsList.size()-1 ; i++) {
                                            buildComponentsList.get(i).setBuild_id(id);
                                        }

                                        BuildRegistBody registBody = new BuildRegistBody(build,buildComponentsList);
                                        BuildsRepository.insertBuild(registBody, new BuildsRepository.InsertBuildCallback() {
                                            @Override
                                            public void onResult(String result) {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        setResult(RESULT_OK);
                                                        finish();
                                                    }
                                                });
                                            }
                                            @Override
                                            public void onError(String error) {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Snackbar.make(findViewById(android.R.id.content),"Erro", Snackbar.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        Snackbar.make(findViewById(android.R.id.content),"Sem alterações", Snackbar.LENGTH_SHORT).show();
                                    }
                                });
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                });


                            }
                        }).start();
                    }
                }
            }
        });
    }

}
