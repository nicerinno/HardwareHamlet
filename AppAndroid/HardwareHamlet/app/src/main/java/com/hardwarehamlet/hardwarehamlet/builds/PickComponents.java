package com.hardwarehamlet.hardwarehamlet.builds;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hardwarehamlet.hardwarehamlet.R;
import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.model.Build_Components;
import com.hardwarehamlet.hardwarehamlet.model.Components;

import java.util.List;

import static com.hardwarehamlet.hardwarehamlet.builds.BuildsDetails.BUILD_ID;
import static com.hardwarehamlet.hardwarehamlet.builds.ComponentListToPick.COMPONENT_TYPE_ID;

public class PickComponents extends AppCompatActivity {
    private int RESULT_CODE_CPU = 1;
    private int RESULT_CODE_GPU = 2;
    private int RESULT_CODE_MOTHERBOARD = 3;
    private int RESULT_CODE_FANS = 4;
    private int RESULT_CODE_CPU_COOLER = 5;
    private int RESULT_CODE_PSU = 6;
    private int RESULT_CODE_SSD = 7;
    private int RESULT_CODE_HDD = 8;
    private int RESULT_CODE_SSHD = 9;
    private int RESULT_CODE_RAM = 10;
    private int RESULT_CODE_CASE = 11;

    private int REQUEST_CODE_PICK_COMPONENTS = 300;

    private AppDatabase appDatabase;
    private Button buttonPickCpu;
    private Button buttonPickGpu;
    private Button buttonPickMotherboard;
    private Button buttonPickFans;
    private Button buttonPickCooler;
    private Button buttonPickSsd;
    private Button buttonPickHdd;
    private Button buttonPickPsu;
    private Button buttonPickSshd;
    private Button buttonPickRam;
    private Button buttonPickCase;

    private long build_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_components);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            this.build_id = bundle.getLong(BUILD_ID);
        }

        setTitle("Escolher tipo de componente");

        this.appDatabase = AppDatabase.getInstance(this);
        //cpu
        this.buttonPickCpu = findViewById(R.id.buttonPickCPU);
        //gpu
        this.buttonPickGpu = findViewById(R.id.buttonPickGPU);

        //motherboard
        this.buttonPickMotherboard = findViewById(R.id.buttonPickMotherboard);

        //fans
        this.buttonPickFans = findViewById(R.id.buttonPickFans);

        //cpu cooler
        this.buttonPickCooler = findViewById(R.id.buttonPickCPUCooler);

        //PSU
        this.buttonPickPsu = findViewById(R.id.buttonPickPSU);

        //ssd
        this.buttonPickSsd = findViewById(R.id.buttonPickSSD);

        //hdd
        this.buttonPickHdd = findViewById(R.id.buttonPickHDD);

        //sshd
        this.buttonPickSshd = findViewById(R.id.pickSSHD);

        //ram
        this.buttonPickRam = findViewById(R.id.buttonPickRam);

        //case
        this.buttonPickCase = findViewById(R.id.buttonPickCase);
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Build_Components> build_componentsList = appDatabase.getBuildComponentsDAO()
                        .getBuildComponentesByBuild(build_id);
                if(build_componentsList.size() > 0){
                    Build_Components cpu = appDatabase.getBuildComponentsDAO().getBuildComponentByType(build_id,1);
                    Build_Components gpu = appDatabase.getBuildComponentsDAO().getBuildComponentByType(build_id,2);
                    Build_Components motherboard = appDatabase.getBuildComponentsDAO().getBuildComponentByType(build_id,3);
                    Build_Components fan = appDatabase.getBuildComponentsDAO().getBuildComponentByType(build_id,4);
                    Build_Components coolerCpu = appDatabase.getBuildComponentsDAO().getBuildComponentByType(build_id,5);
                    Build_Components psu = appDatabase.getBuildComponentsDAO().getBuildComponentByType(build_id,6);
                    Build_Components ssd = appDatabase.getBuildComponentsDAO().getBuildComponentByType(build_id,7);
                    Build_Components hdd = appDatabase.getBuildComponentsDAO().getBuildComponentByType(build_id,8);
                    Build_Components sshd = appDatabase.getBuildComponentsDAO().getBuildComponentByType(build_id,9);
                    Build_Components ram = appDatabase.getBuildComponentsDAO().getBuildComponentByType(build_id,10);
                    Build_Components caixa = appDatabase.getBuildComponentsDAO().getBuildComponentByType(build_id,11);
                    if(cpu != null){
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,1);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickCpu.setVisibility(View.GONE);
                                final LinearLayout linearLayoutCPU = findViewById(R.id.cpuPicked);
                                linearLayoutCPU.setVisibility(View.VISIBLE);

                                TextView textViewEdit = findViewById(R.id.textViewEditCPU);
                                textViewEdit.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewCPUName);
                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);


                                TextView textViewQuantity = findViewById(R.id.textViewCPUQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewCPUPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewCPU);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                    if(gpu!= null){
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,2);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickGpu.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.gpuPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewEdit = findViewById(R.id.textViewEditGPU);
                                textViewEdit.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewGPUName);
                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);


                                TextView textViewQuantity = findViewById(R.id.textViewGPUQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewGPUPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewGPU);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                    if(motherboard != null){
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,3);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickMotherboard.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.motherboardPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewMotherboardName);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);

                                TextView textViewEdit = findViewById(R.id.textViewEditMotherboard);
                                textViewEdit.setVisibility(View.VISIBLE);

                                TextView textViewQuantity = findViewById(R.id.textViewMotherboardQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewMotherboardPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewMotherboard);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });

                    }
                    if(fan != null){
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,4);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickFans.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.fansPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewFanName);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);

                                TextView textViewEdit = findViewById(R.id.textViewEditFans);
                                textViewEdit.setVisibility(View.VISIBLE);

                                TextView textViewQuantity = findViewById(R.id.textViewFanQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewFanPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewFans);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                    if(coolerCpu != null){
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,5);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickCooler.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.cpuCoolerPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewCPUCoolerName);

                                TextView textViewEdit = findViewById(R.id.textViewEditCPUCooler);
                                textViewEdit.setVisibility(View.VISIBLE);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);


                                TextView textViewQuantity = findViewById(R.id.textViewCPUCoolerQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewCPUCoolerPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewCPUCooler);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                    if(psu != null){
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,6);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickPsu.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.psuPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewPSUName);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);

                                TextView textViewEdit = findViewById(R.id.textViewEditPSU);
                                textViewEdit.setVisibility(View.VISIBLE);


                                TextView textViewPrice = findViewById(R.id.textViewPSUPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewPSU);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                    if(ssd != null){
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,7);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickSsd.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.ssdPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewSSDName);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);

                                TextView textViewEdit = findViewById(R.id.textViewEditSSD);
                                textViewEdit.setVisibility(View.VISIBLE);


                                TextView textViewQuantity = findViewById(R.id.textViewSSDQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewSSDPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewSSD);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                    if(hdd != null){
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,8);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickHdd.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.hddPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewHDDName);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);

                                TextView textViewEdit = findViewById(R.id.textViewEditHDD);
                                textViewEdit.setVisibility(View.VISIBLE);

                                TextView textViewQuantity = findViewById(R.id.textViewHDDQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewHDDPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewHDD);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                    if(sshd != null){
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,9);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickSshd.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.sshdPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewSSHDName);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);

                                TextView textViewEdit = findViewById(R.id.textViewEditSSHD);
                                textViewEdit.setVisibility(View.VISIBLE);

                                TextView textViewQuantity = findViewById(R.id.textViewSSHDQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewSSHDPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewSSHD);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                    if(ram != null){
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,10);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickRam.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.ramPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewRAMName);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);

                                TextView textViewEdit = findViewById(R.id.textViewEditRam);
                                textViewEdit.setVisibility(View.VISIBLE);

                                TextView textViewQuantity = findViewById(R.id.textViewRAMQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewRAMPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewRam);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                    if(caixa != null){
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,11);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickCase.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.casePicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewCaseName);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);

                                TextView textViewEdit = findViewById(R.id.textViewEditCase);
                                textViewEdit.setVisibility(View.VISIBLE);

                                TextView textViewQuantity = findViewById(R.id.textViewCaseQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewCasePrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewCase);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                }
            }
        }).start();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //int request = requestCode;
        if(requestCode == REQUEST_CODE_PICK_COMPONENTS){
            if (resultCode == 1) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,1);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickCpu.setVisibility(View.GONE);
                                final LinearLayout linearLayoutCPU = findViewById(R.id.cpuPicked);
                                linearLayoutCPU.setVisibility(View.VISIBLE);

                                TextView textViewEdit = findViewById(R.id.textViewEditCPU);
                                textViewEdit.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewCPUName);
                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);


                                TextView textViewQuantity = findViewById(R.id.textViewCPUQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewCPUPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewCPU);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                }).start();

            }else if(resultCode == 2) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,2);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickGpu.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.gpuPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewEdit = findViewById(R.id.textViewEditGPU);
                                textViewEdit.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewGPUName);
                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);


                                TextView textViewQuantity = findViewById(R.id.textViewGPUQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewGPUPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewGPU);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                }).start();
            }else if(resultCode == 5) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,5);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickCooler.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.cpuCoolerPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewCPUCoolerName);

                                TextView textViewEdit = findViewById(R.id.textViewEditCPUCooler);
                                textViewEdit.setVisibility(View.VISIBLE);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);


                                TextView textViewQuantity = findViewById(R.id.textViewCPUCoolerQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewCPUCoolerPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewCPUCooler);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                }).start();
            }else if(resultCode == 4) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,4);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickFans.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.fansPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewFanName);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);

                                TextView textViewEdit = findViewById(R.id.textViewEditFans);
                                textViewEdit.setVisibility(View.VISIBLE);

                                TextView textViewQuantity = findViewById(R.id.textViewFanQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewFanPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewFans);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                }).start();
            }else if(resultCode == 3) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,3);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickMotherboard.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.motherboardPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewMotherboardName);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);

                                TextView textViewEdit = findViewById(R.id.textViewEditMotherboard);
                                textViewEdit.setVisibility(View.VISIBLE);

                                TextView textViewQuantity = findViewById(R.id.textViewMotherboardQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewMotherboardPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewMotherboard);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });

                    }
                }).start();
            }else if(resultCode == 6) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,6);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickPsu.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.psuPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewPSUName);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);

                                TextView textViewEdit = findViewById(R.id.textViewEditPSU);
                                textViewEdit.setVisibility(View.VISIBLE);


                                TextView textViewPrice = findViewById(R.id.textViewPSUPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewPSU);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                }).start();
            }else if(resultCode == 7) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,7);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickSsd.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.ssdPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewSSDName);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);

                                TextView textViewEdit = findViewById(R.id.textViewEditSSD);
                                textViewEdit.setVisibility(View.VISIBLE);


                                TextView textViewQuantity = findViewById(R.id.textViewSSDQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewSSDPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewSSD);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                }).start();

            }else if(resultCode == 8) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,8);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickHdd.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.hddPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewHDDName);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);

                                TextView textViewEdit = findViewById(R.id.textViewEditHDD);
                                textViewEdit.setVisibility(View.VISIBLE);

                                TextView textViewQuantity = findViewById(R.id.textViewHDDQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewHDDPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewHDD);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                }).start();

            }else if(resultCode == 9) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,9);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickSshd.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.sshdPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewSSHDName);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);

                                TextView textViewEdit = findViewById(R.id.textViewEditSSHD);
                                textViewEdit.setVisibility(View.VISIBLE);

                                TextView textViewQuantity = findViewById(R.id.textViewSSHDQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewSSHDPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewSSHD);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                }).start();
            }else if(resultCode == 11) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,11);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickCase.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.casePicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewCaseName);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);

                                TextView textViewEdit = findViewById(R.id.textViewEditCase);
                                textViewEdit.setVisibility(View.VISIBLE);

                                TextView textViewQuantity = findViewById(R.id.textViewCaseQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewCasePrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewCase);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                }).start();
            }else if(resultCode == 10) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Components component = appDatabase.getBuildComponentsDAO().buildComponents(build_id,10);
                        final Build_Components build_components = appDatabase.getBuildComponentsDAO().getBuildComponentById(component.getComponent_id(),build_id);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonPickRam.setVisibility(View.GONE);
                                LinearLayout linearLayout = findViewById(R.id.ramPicked);
                                linearLayout.setVisibility(View.VISIBLE);

                                TextView textViewName = findViewById(R.id.textViewRAMName);

                                String name = component.getBrand() + " " + component.getName();
                                textViewName.setText(name);

                                TextView textViewEdit = findViewById(R.id.textViewEditRam);
                                textViewEdit.setVisibility(View.VISIBLE);

                                TextView textViewQuantity = findViewById(R.id.textViewRAMQuantity);
                                String quantity = "x" + build_components.getQuantity();
                                textViewQuantity.setText(quantity);

                                TextView textViewPrice = findViewById(R.id.textViewRAMPrice);
                                double price = component.getPrice() * build_components.getQuantity();
                                String stringPrice = price + "€";
                                textViewPrice.setText(stringPrice);

                                ImageView imageView = findViewById(R.id.imageViewRam);
                                Glide.with(getApplication()).load(component.getIcon_url()).into(imageView);
                            }
                        });
                    }
                }).start();
            }

        }else if(resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
        }
    }
    public void pickCPU(View view) {
        Intent intent = new Intent(PickComponents.this,ComponentListToPick.class);
        intent.putExtra(COMPONENT_TYPE_ID,1);
        intent.putExtra(BUILD_ID,this.build_id);
        startActivityForResult(intent, REQUEST_CODE_PICK_COMPONENTS);
    }


    public void pickGpu(View view) {
        Intent intent = new Intent(PickComponents.this,ComponentListToPick.class);
        intent.putExtra(COMPONENT_TYPE_ID,2);
        intent.putExtra(BUILD_ID,this.build_id);
        startActivityForResult(intent, REQUEST_CODE_PICK_COMPONENTS);
    }

    public void pickMotherboard(View view) {
        Intent intent = new Intent(PickComponents.this,ComponentListToPick.class);
        intent.putExtra(COMPONENT_TYPE_ID,3);
        intent.putExtra(BUILD_ID,this.build_id);
        startActivityForResult(intent, REQUEST_CODE_PICK_COMPONENTS);
    }

    public void pickFans(View view) {
        Intent intent = new Intent(PickComponents.this,ComponentListToPick.class);
        intent.putExtra(COMPONENT_TYPE_ID,4);
        intent.putExtra(BUILD_ID,this.build_id);
        startActivityForResult(intent, REQUEST_CODE_PICK_COMPONENTS);
    }

    public void pickCooler(View view) {
        Intent intent = new Intent(PickComponents.this,ComponentListToPick.class);
        intent.putExtra(COMPONENT_TYPE_ID,5);
        intent.putExtra(BUILD_ID,this.build_id);
        startActivityForResult(intent, REQUEST_CODE_PICK_COMPONENTS);
    }

    public void pickPsu(View view) {
        Intent intent = new Intent(PickComponents.this,ComponentListToPick.class);
        intent.putExtra(COMPONENT_TYPE_ID,6);
        intent.putExtra(BUILD_ID,this.build_id);
        startActivityForResult(intent, REQUEST_CODE_PICK_COMPONENTS);
    }

    public void pickSsd(View view) {
        Intent intent = new Intent(PickComponents.this,ComponentListToPick.class);
        intent.putExtra(COMPONENT_TYPE_ID,7);
        intent.putExtra(BUILD_ID,this.build_id);
        startActivityForResult(intent, REQUEST_CODE_PICK_COMPONENTS);
    }

    public void pickHdd(View view) {
        Intent intent = new Intent(PickComponents.this,ComponentListToPick.class);
        intent.putExtra(COMPONENT_TYPE_ID,8);
        intent.putExtra(BUILD_ID,this.build_id);
        startActivityForResult(intent, REQUEST_CODE_PICK_COMPONENTS);
    }

    public void pickSshd(View view) {
        Intent intent = new Intent(PickComponents.this,ComponentListToPick.class);
        intent.putExtra(COMPONENT_TYPE_ID,9);
        intent.putExtra(BUILD_ID,this.build_id);
        startActivityForResult(intent, REQUEST_CODE_PICK_COMPONENTS);
    }

    public void pickRam(View view) {
        Intent intent = new Intent(PickComponents.this,ComponentListToPick.class);
        intent.putExtra(COMPONENT_TYPE_ID,10);
        intent.putExtra(BUILD_ID,this.build_id);
        startActivityForResult(intent, REQUEST_CODE_PICK_COMPONENTS);
    }

    public void pickCase(View view) {
        Intent intent = new Intent(PickComponents.this,ComponentListToPick.class);
        intent.putExtra(COMPONENT_TYPE_ID,11);
        intent.putExtra(BUILD_ID,this.build_id);
        startActivityForResult(intent, REQUEST_CODE_PICK_COMPONENTS);
    }

    public void editCpu(final View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.getBuildComponentsDAO().deleteBuildComponent(build_id,1);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.findViewById(R.id.textViewEditCPU).setVisibility(View.GONE);
                        buttonPickCpu.setVisibility(View.VISIBLE);
                        LinearLayout linearLayout = findViewById(R.id.cpuPicked);
                        linearLayout.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    public void editGPU(final View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.getBuildComponentsDAO().deleteBuildComponent(build_id,2);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.findViewById(R.id.textViewEditGPU).setVisibility(View.GONE);
                        buttonPickGpu.setVisibility(View.VISIBLE);
                        LinearLayout linearLayout = findViewById(R.id.gpuPicked);
                        linearLayout.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    public void editMotherboard(final View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.getBuildComponentsDAO().deleteBuildComponent(build_id,3);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.findViewById(R.id.textViewEditMotherboard).setVisibility(View.GONE);
                        buttonPickMotherboard.setVisibility(View.VISIBLE);
                        LinearLayout linearLayout = findViewById(R.id.motherboardPicked);
                        linearLayout.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    public void editFans(final View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.getBuildComponentsDAO().deleteBuildComponent(build_id,4);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.findViewById(R.id.textViewEditFans).setVisibility(View.GONE);
                        buttonPickFans.setVisibility(View.VISIBLE);
                        LinearLayout linearLayout = findViewById(R.id.fansPicked);
                        linearLayout.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    public void editCPUCooler(final View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.getBuildComponentsDAO().deleteBuildComponent(build_id,5);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.findViewById(R.id.textViewEditCPUCooler).setVisibility(View.GONE);
                        buttonPickCooler.setVisibility(View.VISIBLE);
                        LinearLayout linearLayout = findViewById(R.id.cpuCoolerPicked);
                        linearLayout.setVisibility(View.GONE);
                    }
                });
            }
        }).start();

    }

    public void editPSU(final View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.getBuildComponentsDAO().deleteBuildComponent(build_id,6);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.findViewById(R.id.textViewEditPSU).setVisibility(View.GONE);
                        buttonPickPsu.setVisibility(View.VISIBLE);
                        LinearLayout linearLayout = findViewById(R.id.psuPicked);
                        linearLayout.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    public void editSSD(final View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.getBuildComponentsDAO().deleteBuildComponent(build_id,7);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.findViewById(R.id.textViewEditSSD).setVisibility(View.GONE);
                        buttonPickSsd.setVisibility(View.VISIBLE);
                        LinearLayout linearLayout = findViewById(R.id.ssdPicked);
                        linearLayout.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    public void editHDD(final View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.getBuildComponentsDAO().deleteBuildComponent(build_id,8);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.findViewById(R.id.textViewEditHDD).setVisibility(View.GONE);
                        buttonPickSshd.setVisibility(View.VISIBLE);
                        LinearLayout linearLayout = findViewById(R.id.hddPicked);
                        linearLayout.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    public void editSSHD(final View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.getBuildComponentsDAO().deleteBuildComponent(build_id,9);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.findViewById(R.id.textViewEditSSHD).setVisibility(View.GONE);
                        buttonPickSshd.setVisibility(View.VISIBLE);
                        LinearLayout linearLayout = findViewById(R.id.sshdPicked);
                        linearLayout.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    public void editRam(final View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.getBuildComponentsDAO().deleteBuildComponent(build_id,10);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.findViewById(R.id.textViewEditRam).setVisibility(View.GONE);
                        buttonPickRam.setVisibility(View.VISIBLE);
                        LinearLayout linearLayout = findViewById(R.id.ramPicked);
                        linearLayout.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    public void editCase(final View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.getBuildComponentsDAO().deleteBuildComponent(build_id,11);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.findViewById(R.id.textViewEditCase).setVisibility(View.GONE);
                        buttonPickCase.setVisibility(View.VISIBLE);
                        LinearLayout linearLayout = findViewById(R.id.casePicked);
                        linearLayout.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }


    public void continueButton(View view) {
        setResult(RESULT_OK);
        finish();
    }

}
