package com.hardwarehamlet.hardwarehamlet.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hardwarehamlet.hardwarehamlet.R;
import com.hardwarehamlet.hardwarehamlet.model.Components;

import java.util.ArrayList;
import java.util.List;

public class ComponentsAdapter extends BaseAdapter {
    private Context context;
    private List<Components> componentsList;

    public ComponentsAdapter(Context context) {
        this.context = context;
        this.componentsList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.componentsList.size();
    }

    @Override
    public Components getItem(int position) {
        return this.componentsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.componentsList.get(position).getComponent_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.component_row, parent, false);
        }

        Components components = getItem(position);

        TextView textViewName = convertView.findViewById(R.id.textViewComponentName);
        String name = components.getBrand() + " " + components.getName();
        textViewName.setText(name);
        TextView textViewDescription = convertView.findViewById(R.id.textViewDescription);
        textViewDescription.setText(components.getDescription());
        TextView textViewPrice = convertView.findViewById(R.id.textViewComponentQuantity);
        String price = components.getPrice() + "€";
        textViewPrice.setText(price);
        ImageView imageView = convertView.findViewById(R.id.imageView);
        Glide.with(this.context).load(components.getIcon_url()).into(imageView);


        return convertView;
    }

    public void refreshList(List<Components> componentsList){
        this.componentsList = componentsList;
        notifyDataSetChanged();
    }
}
