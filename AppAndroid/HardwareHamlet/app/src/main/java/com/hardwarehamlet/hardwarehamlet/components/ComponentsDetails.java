package com.hardwarehamlet.hardwarehamlet.components;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hardwarehamlet.hardwarehamlet.R;
import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.model.Components;
import com.hardwarehamlet.hardwarehamlet.model.Titles;
import com.hardwarehamlet.hardwarehamlet.model.Users;
import com.hardwarehamlet.hardwarehamlet.repositories.ComponentsRepository;
import com.hardwarehamlet.hardwarehamlet.repositories.UsersRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.hardwarehamlet.hardwarehamlet.components.ComponentsList.COMPONENT_ID;

public class ComponentsDetails extends AppCompatActivity {
    private static long component_id;
    private TextView textViewComponentName;
    private TextView textViewUsername;
    private TextView textViewData;
    private TextView textViewDescription;
    private TextView textViewPrice;
    private ImageView imageView;
    private TextView textViewUserTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components_details);

        this.textViewComponentName = findViewById(R.id.textViewComponentName);
        this.textViewUsername = findViewById(R.id.textViewUsername);
        this.textViewData = findViewById(R.id.textViewData);
        this.textViewDescription = findViewById(R.id.textViewDescription);
        this.textViewPrice = findViewById(R.id.textViewComponentQuantity);
        this.textViewUserTitle = findViewById(R.id.textViewUserTitleCompDet);

        this.imageView = findViewById(R.id.imageViewDetails);
        Bundle bundle = getIntent().getExtras();
        component_id = bundle.getLong(COMPONENT_ID);
    }

    @Override
    public void onResume() {
        super.onResume();


        ComponentsRepository.getComponentById(this, new ComponentsRepository.ComponentsListCallback() {
            @Override
            public void onResult(List<Components> componentsList) {

            }

            @Override
            public void onResult(final Components component) {
              runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Components componets = component;
                        String name = component.getBrand() + " " +  component.getName();
                        textViewComponentName.setText(name);
                        //getting username the inserted the component
                        UsersRepository.getUserById(ComponentsDetails.this, new UsersRepository.UsersCallback() {
                            @Override
                            public void onResult(final Users user) {
                                textViewUsername.setText(user.getUsername());
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        AppDatabase appDatabase = AppDatabase.getInstance(ComponentsDetails.this);
                                        final Titles title = appDatabase.getTitlesDAO().getTitleById(user.getTitle_id());
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                textViewUserTitle.setTextColor(Color.parseColor(title.getColor()));
                                                textViewUserTitle.setText(title.getName());
                                            }
                                        });

                                    }
                                }).start();
                            }

                            @Override
                            public void onError(String error) {

                            }
                        },component.getUser_id());

                        Glide.with(ComponentsDetails.this).load(component.getIcon_url()).into(imageView);
                        textViewDescription.setText(component.getDescription());
                        textViewData.setText(getFormatedDate(component.getRegist_date()));
                        String price = String.valueOf(component.getPrice()) + "€";
                        textViewPrice.setText(price);
                    }
                });
            }

            @Override
            public void onError(String error) {

            }
        }, component_id);
    }

    //private static final SimpleDateFormat todaySimpleDateFormat = new SimpleDateFormat("hh:mm:ss");
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    public String getFormatedDate(long time){

        return simpleDateFormat.format(new Date(time));
    }

    public void closeDetails(View view) {
        finish();
    }
}
