package com.hardwarehamlet.hardwarehamlet.builds;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hardwarehamlet.hardwarehamlet.R;
import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.model.Builds;

import java.util.ArrayList;
import java.util.List;



public class BuildsListAdapter extends BaseAdapter {
    private Context context;
    private Activity activity;
    private List<Builds> buildsList;

    public BuildsListAdapter(Context context, Activity activity) {
        this.activity = activity;
        this.context = context;
        this.buildsList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.buildsList.size();
    }

    @Override
    public Builds getItem(int i) {
        return this.buildsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.buildsList.get(i).getBuild_id();
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.builds_list_row, viewGroup, false);
        }
        final View view = convertView;
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Builds build = getItem(i);
                AppDatabase appDatabase = AppDatabase.getInstance(context);
                final String build_type_name = appDatabase.getBuild_TypeDAO().getBuildTypeName(build.getBuild_type_id());
                final String image = appDatabase.getBuildComponentsDAO().getCaseIcon(build.getBuild_id());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView textViewName = view.findViewById(R.id.textViewBuildName);
                        textViewName.setText(build.getBuild_name());

                        TextView textViewBuildType = view.findViewById(R.id.textViewBuildType);
                        textViewBuildType.setText(build_type_name);

                        TextView textViewPrice = view.findViewById(R.id.textViewBuildPrice);
                        String price = String.valueOf(build.getPrice());
                        if(price.substring(price.indexOf("."),price.length()-1).length() != 2){
                            String finalS = price + "0€";
                            textViewPrice.setText(finalS);
                        }else{
                            String finalS = price + "€";
                            textViewPrice.setText(finalS);
                        }
                        
                        TextView textViewLikes = view.findViewById(R.id.textViewLikes);
                        String likes = "Likes: " + build.getLikes();
                        textViewLikes.setText(likes);

                        ImageView imageView = view.findViewById(R.id.imageViewFromBuild);
                        if(image != null){
                            Glide.with(context).load(image).into(imageView);
                        }else{
                            Glide.with(context).load(R.mipmap.unavailable_foreground).into(imageView);
                        }

                    }
                });
            }
        }).start();
        return view;
    }

    public void refreshList(List<Builds> buildsList){
        this.buildsList = buildsList;
        notifyDataSetChanged();
    }
}
