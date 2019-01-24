package com.hardwarehamlet.hardwarehamlet.components;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.hardwarehamlet.hardwarehamlet.R;
import com.hardwarehamlet.hardwarehamlet.SignUpActivity;
import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.model.Component_Type;
import com.hardwarehamlet.hardwarehamlet.model.Components;
import com.hardwarehamlet.hardwarehamlet.preferences_manager.PreferencesManager;
import com.hardwarehamlet.hardwarehamlet.repositories.ComponentTypeRepository;
import com.hardwarehamlet.hardwarehamlet.repositories.ComponentsRepository;

import java.util.ArrayList;
import java.util.List;

public class ComponentsList extends Fragment{
    public ComponentsAdapter adapter;
    public ListView listView;
    public EditText editTextSearch;
    public static String sort_value = "desc";
    public static String search = "";
    public static int type_id = 0;
    public ImageButton buttonSort;
    private Spinner spinner;
    public static String COMPONENT_ID = "component_id";

    public static void setSort_value(String sort_value) {
        ComponentsList.sort_value = sort_value;
    }

    public static void setSearch(String search) {
        ComponentsList.search = search;
    }

    public static void setType_id(int type_id) {
        ComponentsList.type_id = type_id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_component_list,null);
    }

    @Override
    public void onViewCreated(@NonNull final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner = view.findViewById(R.id.spinner);

        adapter = new ComponentsAdapter(getContext());
        listView = view.findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(PreferencesManager.getSession(getContext())){
                    Intent intent = new Intent(getActivity(), ComponentsDetails.class);
                    intent.putExtra(COMPONENT_ID,id);
                    startActivity(intent);
                }else{
                    Snackbar snackbar = Snackbar.make(getView()
                            ,"Apenas utilizadores podem aceder aos detalhes."
                            , Snackbar.LENGTH_LONG);
                    snackbar.setAction("Criar conta", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getActivity(), SignUpActivity.class));
                        }
                    });
                    snackbar.show();
                }
            }
        });

        editTextSearch = view.findViewById(R.id.editTextSearch);
        listView.setAdapter(adapter);

        this.buttonSort = view.findViewById(R.id.buttonSort);
        buttonSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String search2 = editTextSearch.getText().toString();

                if(search2.equals("")){
                    setSearch("none");
                }else{
                    setSearch(search2);
                }

                if(sort_value.equals("desc")){
                    setSort_value("asc");
                    buttonSort.setImageResource(R.drawable.asc);
                }else{
                    setSort_value("desc");
                    buttonSort.setImageResource(R.drawable.desc);
                }

                ComponentsRepository.getComponents(getContext(), new ComponentsRepository.ComponentsListCallback() {
                    @Override
                    public void onResult(final List<Components> componentsList) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.refreshList(componentsList);
                            }
                        });
                    }

                    @Override
                    public void onResult(Components component) {

                    }

                    @Override
                    public void onError(String error) {
                        Snackbar.make(view,error, Snackbar.LENGTH_LONG).show();
                    }
                },sort_value,type_id, search);
            }
        });

        ImageButton imageButtonSearch = view.findViewById(R.id.imageButtonSearch);
        imageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                String search2 = editTextSearch.getText().toString();
                if(search2.equals("")){
                    setSearch("none");
                }else{
                    setSearch(search2);
                }
                //ComponentsList.setSearch(search);
                ComponentsRepository.getComponents(getContext(), new ComponentsRepository.ComponentsListCallback() {
                    @Override
                    public void onResult(final List<Components> componentsList) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.refreshList(componentsList);
                            }
                        });
                    }

                    @Override
                    public void onResult(Components component) {

                    }

                    @Override
                    public void onError(String error) {
                        Snackbar.make(view,error, Snackbar.LENGTH_LONG).show();
                    }
                },sort_value,type_id, search);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();

        final AppDatabase appDatabase = AppDatabase.getInstance(getContext());
        ComponentTypeRepository.getComponentTypeList(getContext(), new ComponentTypeRepository.ComponentTypeCallback() {
            @Override
            public void onResult(final List<Component_Type> component_typeList) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<String> array = new ArrayList<>();
                        //array.add("Todos os componentes");
                        for (int i = 0; i <= component_typeList.size()-1; i++) {
                            array.add(component_typeList.get(i).getName());
                        }
                        ArrayAdapter<String> adapterArray = new ArrayAdapter<String>(getContext(), R.layout.spinner_comp_list, array);
                        adapterArray.setDropDownViewResource( R.layout.spinner_comp_list);
                        spinner.setAdapter(adapterArray);
                    }
                });

            }

            @Override
            public void onError(String error) {

            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String search2 = editTextSearch.getText().toString();

                if(search2.equals("")){
                    setSearch("none");
                }else{
                    setSearch(search2);
                }

                setType_id(position+1);
                ComponentsRepository.getComponents(getContext(), new ComponentsRepository.ComponentsListCallback() {
                    @Override
                    public void onResult(final List<Components> componentsList) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.refreshList(componentsList);
                            }
                        });
                    }

                    @Override
                    public void onResult(Components component) {

                    }

                    @Override
                    public void onError(String error) {
                        Snackbar.make(getView(),error, Snackbar.LENGTH_LONG).show();
                    }
                },sort_value,type_id, search);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
            }
}
