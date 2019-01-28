package com.hardwarehamlet.hardwarehamlet.builds;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hardwarehamlet.hardwarehamlet.Home;
import com.hardwarehamlet.hardwarehamlet.NavActivity;
import com.hardwarehamlet.hardwarehamlet.R;
import com.hardwarehamlet.hardwarehamlet.SignUpActivity;
import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.model.Build_Type;
import com.hardwarehamlet.hardwarehamlet.preferences_manager.PreferencesManager;
import com.hardwarehamlet.hardwarehamlet.repositories.BuildTypeRepository;

import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class Build extends Fragment {
    private TextView textViewName1;
    private TextView textViewName2;
    private TextView textViewName3;
    private TextView textViewName4;
    private TextView textViewName5;
    private TextView textViewName6;

    public static int REQUEST_BUILD_CREATOR = 208;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_build,null);

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewName1 = view.findViewById(R.id.textViewName1);
        textViewName2 = view.findViewById(R.id.textViewName2);
        textViewName3 = view.findViewById(R.id.textViewName3);
        textViewName4 = view.findViewById(R.id.textViewName4);
        textViewName5 = view.findViewById(R.id.textViewName5);
        textViewName6 = view.findViewById(R.id.textViewName6);

        ImageView imageViewBudget = view.findViewById(R.id.imageViewBudget);
        ImageView imageViewGaming = view.findViewById(R.id.imageViewGaming);
        ImageView imageViewGamingE = view.findViewById(R.id.imageViewGamingE);
        ImageView imageViewEdit = view.findViewById(R.id.imageViewEdit);
        ImageView imageViewWork = view.findViewById(R.id.imageViewWork);
        ImageView imageViewMining = view.findViewById(R.id.imageViewMining);
        final Fragment fragment = new BuildsList();
        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction ft = fragmentManager.beginTransaction();


        imageViewBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuildsList.setBuild_type(1);
                ft.replace(R.id.screen_area, fragment);
                ft.addToBackStack("fragment_stack");
                ft.commit();
            }
        });
        imageViewGaming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuildsList.setBuild_type(2);
                ft.replace(R.id.screen_area, fragment);
                ft.addToBackStack("fragment_stack");
                ft.commit();
            }
        });
        imageViewGamingE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuildsList.setBuild_type(3);
                ft.replace(R.id.screen_area, fragment);
                ft.addToBackStack("fragment_stack");
                ft.commit();
            }
        });
        imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuildsList.setBuild_type(4);
                ft.replace(R.id.screen_area, fragment);
                ft.addToBackStack("fragment_stack");
                ft.commit();
            }
        });
        imageViewWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuildsList.setBuild_type(5);
                ft.replace(R.id.screen_area, fragment);
                ft.addToBackStack("fragment_stack");
                ft.commit();
            }
        });
        imageViewMining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuildsList.setBuild_type(6);
                ft.replace(R.id.screen_area, fragment);
                ft.addToBackStack("fragment_stack");
                ft.commit();
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {
                BuildTypeRepository.getBuildTypeList(getContext(), new BuildTypeRepository.BuildTypeCallback() {
                    @Override
                    public void onResult(List<Build_Type> buildTypeList) {
                        textViewName1.setText(buildTypeList.get(0).getName());
                        textViewName2.setText(buildTypeList.get(1).getName());
                        textViewName3.setText(buildTypeList.get(2).getName());
                        textViewName4.setText(buildTypeList.get(3).getName());
                        textViewName5.setText(buildTypeList.get(4).getName());
                        textViewName6.setText(buildTypeList.get(5).getName());
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
            }
        }).start();
    }
}
