package com.hardwarehamlet.hardwarehamlet.builds;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hardwarehamlet.hardwarehamlet.NavActivity;
import com.hardwarehamlet.hardwarehamlet.R;
import com.hardwarehamlet.hardwarehamlet.SignUpActivity;
import com.hardwarehamlet.hardwarehamlet.components.ComponentsDetails;
import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataService;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataSource;
import com.hardwarehamlet.hardwarehamlet.model.Builds;
import com.hardwarehamlet.hardwarehamlet.model.ResponseBody;
import com.hardwarehamlet.hardwarehamlet.preferences_manager.PreferencesManager;
import com.hardwarehamlet.hardwarehamlet.repositories.BuildsRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hardwarehamlet.hardwarehamlet.builds.Build.REQUEST_BUILD_CREATOR;
import static com.hardwarehamlet.hardwarehamlet.builds.BuildsDetails.BUILD_ID;

public class BuildsList extends Fragment {
    private static int build_type;
    private ListView listViewBuildsList;
    private BuildsListAdapter adapter;

    public static int getBuild_type() {
        return build_type;
    }

    public static void setBuild_type(int build_type) {
        BuildsList.build_type = build_type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_builds_list,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listViewBuildsList = view.findViewById(R.id.listViewBuildsList);
        adapter = new BuildsListAdapter(getContext(),getActivity());
        listViewBuildsList.setAdapter(adapter);

        final AppDatabase appDatabase = AppDatabase.getInstance(getContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                final String name = "Builds de " + appDatabase.getBuild_TypeDAO().getBuildTypeName(BuildsList.getBuild_type());;

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().setTitle(name);
                    }
                });
            }
        }).start();


    }

    @Override
    public void onResume() {
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                BuildsRepository.getBuilds(getContext(), new BuildsRepository.AllBuildsCallback() {
                    @Override
                    public void onResult(final List<Builds> buildsList) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.refreshList(buildsList);
                            }
                        });
                    }

                    @Override
                    public void onError(String error) {

                    }
                },"desc",BuildsList.getBuild_type(),"none");
            }
        }).start();
        final DataService dataService = DataSource.getDataService();
        listViewBuildsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                if( PreferencesManager.getSavedUserId(getContext()) != 0){
                    Call<ResponseBody> call = dataService.checkIfActive(PreferencesManager.getSavedUserId(getContext()));
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){
                                ResponseBody responseBody = response.body();
                                if(responseBody.getResult().equals("active")){
                                    Intent intent = new Intent(getActivity(), BuildsDetails.class);
                                    intent.putExtra(BUILD_ID, id);
                                    startActivity(intent);
                                }else{
                                    Snackbar snackbar = Snackbar.make(getView()
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
                    Snackbar snackbar = Snackbar.make(view.findViewById(android.R.id.content)
                            ,"Apenas utilizadores podem aceder a esta fucionalidade."
                            , Snackbar.LENGTH_LONG);
                    snackbar.setAction("Criar conta", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), SignUpActivity.class);
                            startActivity(intent);
                        }
                    });
                    snackbar.show();
                }
            }
        });
    }
}
