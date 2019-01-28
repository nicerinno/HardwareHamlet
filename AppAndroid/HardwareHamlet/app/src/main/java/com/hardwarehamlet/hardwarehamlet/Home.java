package com.hardwarehamlet.hardwarehamlet;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hardwarehamlet.hardwarehamlet.model.Builds;
import com.hardwarehamlet.hardwarehamlet.model.Components;
import com.hardwarehamlet.hardwarehamlet.repositories.BuildsRepository;
import com.hardwarehamlet.hardwarehamlet.repositories.ComponentsRepository;
import com.hardwarehamlet.hardwarehamlet.repositories.MedalsRepository;
import com.hardwarehamlet.hardwarehamlet.repositories.TitlesRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Home extends Fragment {
    private TextView textViewDate;
    private TextView textViewVisitSite;
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.textViewDate = view.findViewById(R.id.textViewTodayDate);
        this.textViewVisitSite = view.findViewById(R.id.textViewVisitSite);

        textViewDate.setText(simpleDateFormat.format(new Date(System.currentTimeMillis())));

        this.textViewVisitSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("http://194.210.139.1/"));
                startActivity(viewIntent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
