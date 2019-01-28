package com.hardwarehamlet.hardwarehamlet.ranking;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hardwarehamlet.hardwarehamlet.R;
import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.model.Titles;
import com.hardwarehamlet.hardwarehamlet.model.Users;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private List<Users> usersList;
    private Activity activity;

    public UserAdapter(Context context, Activity activity) {
        this.context = context;
        this.usersList = new ArrayList<>();
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.usersList.size();
    }

    @Override
    public Users getItem(int position) {
        return this.usersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.usersList.get(position).getUser_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.user_list_row, parent, false);
        }
        final Users user = getItem(position);

        TextView textViewName = convertView.findViewById(R.id.textViewUname);
        textViewName.setText(user.getUsername());


        final TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase appDatabase = AppDatabase.getInstance(context);
                final Titles title = appDatabase.getTitlesDAO().getTitleById(user.getTitle_id());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!title.getColor().isEmpty()){
                            textViewTitle.setTextColor(Color.parseColor(title.getColor()));
                        }
                        textViewTitle.setText(title.getName());
                    }
                });
            }
        }).start();

        TextView textViewReputation = convertView.findViewById(R.id.textViewReputation);
        String rep = "Rep: " + String.valueOf(user.getReputation());
        textViewReputation.setText(rep);

        //ImageView imageView = convertView.findViewById(R.id.imageViewUser);


        return convertView;
    }

    public void refreshList(List<Users> usersList){
        this.usersList = usersList;
        notifyDataSetChanged();
    }
}
