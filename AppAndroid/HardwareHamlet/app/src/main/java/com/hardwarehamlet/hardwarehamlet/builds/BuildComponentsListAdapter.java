package com.hardwarehamlet.hardwarehamlet.builds;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hardwarehamlet.hardwarehamlet.R;
import com.hardwarehamlet.hardwarehamlet.model.Build_Components;
import com.hardwarehamlet.hardwarehamlet.model.Components;
import com.hardwarehamlet.hardwarehamlet.repositories.ComponentsRepository;

import java.util.ArrayList;
import java.util.List;

public class BuildComponentsListAdapter extends BaseAdapter {
    private Context context;
    private List<Build_Components> build_componentsList;
    private ImageView imageView;
    private Activity activity;


    public BuildComponentsListAdapter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        this.build_componentsList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.build_componentsList.size();
    }

    @Override
    public Build_Components getItem(int position) {
        return this.build_componentsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.build_component_row, parent, false);
        }
        final View view = convertView;

        //AppDatabase appDatabase = AppDatabase.getInstance(context);
        final Build_Components build_component = getItem(position);
        ComponentsRepository.getComponentById(context, new ComponentsRepository.ComponentsListCallback() {
            @Override
            public void onResult(List<Components> componentsList) {

            }

            @Override
            public void onResult(final Components component) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView textViewName = view.findViewById(R.id.textViewComponentName);
                        String name = component.getBrand() + " " + component.getName();
                        textViewName.setText(name);

                        TextView textViewQuantity = view.findViewById(R.id.textViewComponentQuantity);
                        String quantity = "x"+build_component.getQuantity();
                        textViewQuantity.setText(quantity);

                        TextView textViewPrice = view.findViewById(R.id.textViewLikes);
                        double n = component.getPrice() * build_component.getQuantity();
                        String price = n +"€";
                        textViewPrice.setText(price);

                        imageView = view.findViewById(R.id.imageView2);
                        Glide.with(context).load(component.getIcon_url()).into(imageView);
                    }
                });
            }

            @Override
            public void onError(String error) {

            }
        },build_component.getComponent_id());
        return convertView;
    }

    public void refreshList(List<Build_Components> build_componentsList){
        this.build_componentsList = build_componentsList;
        notifyDataSetChanged();
    }
}
