package com.hardwarehamlet.hardwarehamlet.builds;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hardwarehamlet.hardwarehamlet.NavActivity;
import com.hardwarehamlet.hardwarehamlet.R;
import com.hardwarehamlet.hardwarehamlet.SignUpActivity;
import com.hardwarehamlet.hardwarehamlet.Utility;
import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataService;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataSource;
import com.hardwarehamlet.hardwarehamlet.model.Build_Components;
import com.hardwarehamlet.hardwarehamlet.model.Builds;
import com.hardwarehamlet.hardwarehamlet.model.BuildsResponseBody;
import com.hardwarehamlet.hardwarehamlet.model.Comments;
import com.hardwarehamlet.hardwarehamlet.model.Components;
import com.hardwarehamlet.hardwarehamlet.model.ResponseBody;
import com.hardwarehamlet.hardwarehamlet.model.Titles;
import com.hardwarehamlet.hardwarehamlet.model.Users;
import com.hardwarehamlet.hardwarehamlet.preferences_manager.PreferencesManager;
import com.hardwarehamlet.hardwarehamlet.repositories.BuildsRepository;
import com.hardwarehamlet.hardwarehamlet.repositories.CommentsRepository;
import com.hardwarehamlet.hardwarehamlet.repositories.ComponentsRepository;
import com.hardwarehamlet.hardwarehamlet.repositories.UsersRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuildsDetails extends AppCompatActivity {
    public static String BUILD_ID = "build_id";

    private TextView textViewBuildName;
    private TextView textViewDescription;
    private TextView textViewPrice;
    private TextView textViewLikes;
    private TextView textViewShowComments;
    private TextView textViewUserTitle;
    private TextView textViewBuildUsername;
    private TextView textViewBuildDate;

    private EditText editTextComments;

    private ImageView imageViewCase;

    private ListView listViewComments;
    private ListView listViewBuildComponents;

    private LinearLayout layoutComponents;
    private LinearLayout layoutComments;

    private long build_id;

    private Button buttonShowComponents;

    private CommentsListAdapter adapterComments;
    private BuildComponentsListAdapter adapterComponents;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builds_details);

        this.textViewBuildName = findViewById(R.id.textViewBuildDetailsName);
        this.textViewDescription = findViewById(R.id.textViewBuildDescription);
        this.textViewPrice = findViewById(R.id.textViewBuildDetailsPrice);
        this.textViewLikes = findViewById(R.id.textViewBuildLikes);
        this.textViewShowComments = findViewById(R.id.textViewShowComments);
        this.textViewUserTitle = findViewById(R.id.textViewBuildUserTitle);
        this.textViewBuildUsername = findViewById(R.id.textViewBuildUsername);
        this.textViewBuildDate = findViewById(R.id.textViewBuildDate);

        this.buttonShowComponents = findViewById(R.id.buttonShowComponents);

        this.appDatabase = AppDatabase.getInstance(this);

        this.editTextComments = findViewById(R.id.editTextComment);

        this.imageViewCase = findViewById(R.id.imageViewDetailsCase);

        this.listViewComments = findViewById(R.id.listViewComments);
        this.listViewBuildComponents = findViewById(R.id.listViewBuildComponentsDetails);
        this.adapterComments = new CommentsListAdapter(this,BuildsDetails.this);
        this.adapterComponents = new BuildComponentsListAdapter(this,BuildsDetails.this);

        this.listViewComments.setAdapter(this.adapterComments);
        this.listViewComments.setEnabled(false);
        this.listViewComments.setOnItemClickListener(null);
        this.listViewComments.setClickable(false);

        this.listViewBuildComponents.setAdapter(this.adapterComponents);
        this.listViewBuildComponents.setEnabled(false);
        this.listViewBuildComponents.setOnItemClickListener(null);
        this.listViewBuildComponents.setClickable(false);

        this.layoutComments = findViewById(R.id.commentsLayout);
        this.layoutComponents = findViewById(R.id.layoutShowComponents);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.build_id = bundle.getLong(BUILD_ID);
        }
    }

    private String check;
    private DataService dataService;
    private ImageButton imageButtonLike;
    @Override
    protected void onResume() {
        super.onResume();

        imageButtonLike = findViewById(R.id.imageButtonLike);
        this.dataService = DataSource.getDataService();
        Call<ResponseBody> call = dataService.checkIfLiked(PreferencesManager.getSavedUserId(this),this.build_id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    ResponseBody responseBody = response.body();
                    check = responseBody.getResult();
                    if(check.equals("true")){
                        imageButtonLike.setImageResource(R.drawable.dislike);
                    }else{
                        imageButtonLike.setImageResource(R.drawable.like);
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        BuildsRepository.getBuildById(this, new BuildsRepository.BuildCallback() {
            @Override
            public void onResult(final BuildsResponseBody builds) {
                UsersRepository.getUserById(BuildsDetails.this, new UsersRepository.UsersCallback() {
                    @Override
                    public void onResult(final Users userList) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                final Titles title = appDatabase.getTitlesDAO().getTitleById(userList.getTitle_id());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(!title.getColor().isEmpty()){
                                            textViewUserTitle.setTextColor(Color.parseColor(title.getColor()));
                                        }
                                        textViewUserTitle.setText(title.getName());
                                        textViewBuildUsername.setText(userList.getUsername());
                                    }
                                });
                            }
                        }).start();

                    }

                    @Override
                    public void onError(String error) {

                    }
                }, builds.getBuild().getUser_id());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewBuildDate.setText(getFormatedDate(builds.getBuild().getRegist_date()));
                        textViewBuildName.setText(builds.getBuild().getBuild_name());
                        textViewDescription.setText(builds.getBuild().getDescription());
                        String price = builds.getBuild().getPrice() + "€";
                        textViewPrice.setText(price);
                        String likes = String.valueOf(builds.getBuild().getLikes()) + " pessoas gostaram disto";
                        textViewLikes.setText(likes);
                    }

                });
                ComponentsRepository.getComponents(BuildsDetails.this, new ComponentsRepository.ComponentsListCallback() {
                    @Override
                    public void onResult(List<Components> componentsList) {
                        if(componentsList.size() >0){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    AppDatabase appDatabase = AppDatabase.getInstance(BuildsDetails.this);
                                    final String iconCase = appDatabase.getBuildComponentsDAO().getCaseIcon(build_id);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Glide.with(getApplicationContext()).load(iconCase).into(imageViewCase);
                                        }
                                    });
                                }
                            }).start();
                        }
                    }

                    @Override
                    public void onResult(Components component) {

                    }

                    @Override
                    public void onError(String error) {

                    }
                },"desc",0,"none");
            }

            @Override
            public void onError(String error) {

            }
        },this.build_id);


    }

    public void sendComment(View view) {
        if(PreferencesManager.getSession(this)){
            String comment = editTextComments.getText().toString();
            if(comment.isEmpty()){
                Toast.makeText(this, "Não pode enviar um comentário vazio", Toast.LENGTH_SHORT).show();
            }else{
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                CommentsRepository.sendComment(this, build_id, comment, PreferencesManager.getSavedUserId(this)
                        ,System.currentTimeMillis(), new CommentsRepository.InsertCommentCallBack() {
                            @Override
                            public void onResult(String result) {
                                if(result.equals("successful")){
                                    Toast.makeText(BuildsDetails.this, "Comentário enviado com sucesso", Toast.LENGTH_SHORT).show();
                                    if(layoutComments.getVisibility() == View.VISIBLE){
                                        CommentsRepository.getCommentsListFromBuild(BuildsDetails.this, build_id, new CommentsRepository.CommentsCallBack() {
                                            @Override
                                            public void onResult(final List<Comments> commentsList) {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        adapterComments.refreshList(commentsList,5);
                                                        Utility.setListViewHeightBasedOnChildren(listViewComments);
                                                        editTextComments.setText("");
                                                    }
                                                });
                                            }
                                        });
                                    }

                                }else{
                                    Toast.makeText(BuildsDetails.this, "Comentário não enviado", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        }else{
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content)
                    ,"Apenas utilizadores podem aceder a esta fucionalidade."
                    , Snackbar.LENGTH_LONG);
            snackbar.setAction("Criar conta", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BuildsDetails.this, SignUpActivity.class);
                    startActivity(intent);
                }
            });
            snackbar.show();
        }

    }

    private int counter = 0;

    public void showComments(View view) {
        switch(counter){
            case 0: this.layoutComments.setVisibility(View.VISIBLE);
                CommentsRepository.getCommentsListFromBuild(this, this.build_id, new CommentsRepository.CommentsCallBack() {
                    @Override
                    public void onResult(final List<Comments> commentsList) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapterComments.refreshList(commentsList,5);
                                Utility.setListViewHeightBasedOnChildren(listViewComments);
                                editTextComments.setText("");
                                textViewShowComments.setText("MOSTRAR TODOS OS COMENTÁRIOS");
                            }
                        });
                    }
                });
                counter++;
                break;
            case 1:
                CommentsRepository.getCommentsListFromBuild(this, this.build_id, new CommentsRepository.CommentsCallBack() {
                    @Override
                    public void onResult(final List<Comments> commentsList) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapterComments.refreshList(commentsList,commentsList.size());
                                Utility.setListViewHeightBasedOnChildren(listViewComments);
                                editTextComments.setText("");
                                textViewShowComments.setText("ESCONDER TODOS OS COMENTÁRIOS");
                            }
                        });
                    }
                });
                counter++;
                break;

            case 2: this.layoutComments.setVisibility(View.GONE);
                textViewShowComments.setText("MOSTRAR ÚLTIMOS 5 COMENTÁRIOS");
                counter=0;
                break;
        }
    }

    private int counter2 = 0;

    public void showComponents(View view) {
        switch(counter2){
            case 0: this.layoutComponents.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final List<Build_Components> build_componentsList = appDatabase.getBuildComponentsDAO()
                                .getBuildComponentesByBuild(build_id);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(build_componentsList != null){
                                    adapterComponents.refreshList(build_componentsList);
                                    Utility.setListViewHeightBasedOnChildren(listViewBuildComponents);
                                    buttonShowComponents.setText("ESCONDER COMPONENTES");
                                }
                            }
                        });
                    }
                }).start();

                counter2++;
                break;
            case 1: this.layoutComponents.setVisibility(View.GONE);
                buttonShowComponents.setText("MOSTRAR COMPONENTES");
                counter2 = 0;
                break;
        }
    }

    public void like(View view) {
        if(PreferencesManager.getSession(this)){
            if(check.equals("true")){
                Call<ResponseBody> call = dataService.removeLike(PreferencesManager.getSavedUserId(this),build_id);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            ResponseBody responseBody = response.body();
                            if(responseBody.getResult().equals("successfull")){
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Builds build = appDatabase.getBuildsDAO().getBuildById(build_id);
                                        final long likes = build.getLikes() -1;
                                        appDatabase.getBuildsDAO().updateLikes(likes,build_id);
                                        check = "false";
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                String stringLikes = likes + " pessoas gostaram disto";
                                                textViewLikes.setText(stringLikes);
                                                imageButtonLike.setImageResource(R.drawable.like);
                                            }
                                        });
                                    }
                                }).start();
                            }else{
                                Toast.makeText(BuildsDetails.this, "Erro no like", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }else{
                Call<ResponseBody> call = dataService.setLike(PreferencesManager.getSavedUserId(this),build_id);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            ResponseBody responseBody = response.body();
                            if(responseBody.getResult().equals("successfull")){
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Builds build = appDatabase.getBuildsDAO().getBuildById(build_id);
                                        final long likes = build.getLikes() + 1;
                                        appDatabase.getBuildsDAO().updateLikes(likes,build_id);
                                        check = "true";
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                String stringLikes = likes + " pessoas gostaram disto";
                                                textViewLikes.setText(stringLikes);
                                                imageButtonLike.setImageResource(R.drawable.dislike);
                                            }
                                        });
                                    }
                                }).start();
                            }else{
                                Toast.makeText(BuildsDetails.this, "Erro no like", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }


        }else{
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content)
                    ,"Apenas utilizadores podem aceder a esta fucionalidade."
                    , Snackbar.LENGTH_LONG);
            snackbar.setAction("Criar conta", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BuildsDetails.this, SignUpActivity.class);
                    startActivity(intent);
                }
            });
            snackbar.show();
        }
    }

    public void closeBuildsDetails(View view) {
        finish();
    }
    private static final SimpleDateFormat todaySimpleDateFormat = new SimpleDateFormat("hh:mm:ss");
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    public String getFormatedDate(long miliseconds){
        if(DateUtils.isToday(miliseconds)){
            return todaySimpleDateFormat.format(new Date(miliseconds));
        } else {
            return simpleDateFormat.format(new Date(miliseconds));
        }
    }

}
