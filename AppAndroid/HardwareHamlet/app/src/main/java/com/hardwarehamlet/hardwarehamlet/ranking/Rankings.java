package com.hardwarehamlet.hardwarehamlet.ranking;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hardwarehamlet.hardwarehamlet.R;
import com.hardwarehamlet.hardwarehamlet.Utility;
import com.hardwarehamlet.hardwarehamlet.builds.BuildsListAdapter;
import com.hardwarehamlet.hardwarehamlet.components.ComponentsAdapter;
import com.hardwarehamlet.hardwarehamlet.model.Builds;
import com.hardwarehamlet.hardwarehamlet.model.Components;
import com.hardwarehamlet.hardwarehamlet.model.Users;
import com.hardwarehamlet.hardwarehamlet.ranking.UserAdapter;
import com.hardwarehamlet.hardwarehamlet.repositories.RankingRepository;

import java.util.List;

public class Rankings extends Fragment {

    private UserAdapter usersAdapter;
    private BuildsListAdapter buildsListAdapter;
    private ListView listViewTopBuilds;
    private ListView listViewTopUsers;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_rankings,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        usersAdapter = new UserAdapter(getContext(),getActivity());
        listViewTopUsers = view.findViewById(R.id.listViewTopUsers);
        listViewTopUsers.setAdapter(usersAdapter);
        listViewTopUsers.setEnabled(false);
        listViewTopUsers.setOnItemClickListener(null);
        listViewTopUsers.setClickable(false);

        buildsListAdapter = new BuildsListAdapter(getContext(),getActivity());
        listViewTopBuilds = view.findViewById(R.id.listViewTopBuilds);
        listViewTopBuilds.setAdapter(buildsListAdapter);
        listViewTopBuilds.setEnabled(false);
        listViewTopBuilds.setOnItemClickListener(null);
        listViewTopBuilds.setClickable(false);
    }

    @Override
    public void onResume() {
        super.onResume();

        RankingRepository.getRankingUsers(getContext(), new RankingRepository.RankingsCallback<Users>() {
            @Override
            public void onResult(List<Users> list) {
                usersAdapter.refreshList(list);
                Utility.setListViewHeightBasedOnChildren3(listViewTopUsers);

            }
            @Override
            public void onError(String error) {

            }
        });

        // TODO: 24/01/2019 Arranjar os builds para aparecer na lista
        RankingRepository.getRankingBuilds(getContext(), new RankingRepository.RankingsCallback<Builds>() {
            @Override
            public void onResult(List<Builds> list) {
                buildsListAdapter.refreshList(list);
                Utility.setListViewHeightBasedOnChildren2(listViewTopBuilds);

            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
