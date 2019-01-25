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
import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.model.Builds;
import com.hardwarehamlet.hardwarehamlet.preferences_manager.PreferencesManager;
import com.hardwarehamlet.hardwarehamlet.repositories.BuildsRepository;

import java.util.List;

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

        listViewBuildsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if( PreferencesManager.getSavedUserId(getContext()) != 0){
                    Intent intent = new Intent(getActivity(), AddBuild.class);
                    startActivityForResult(intent,REQUEST_BUILD_CREATOR);
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
