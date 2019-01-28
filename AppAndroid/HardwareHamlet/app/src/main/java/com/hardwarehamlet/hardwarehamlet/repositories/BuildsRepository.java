package com.hardwarehamlet.hardwarehamlet.repositories;

import android.content.Context;

import com.hardwarehamlet.hardwarehamlet.builds.BuildRegistBody;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataService;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataSource;
import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.model.Build_Components;
import com.hardwarehamlet.hardwarehamlet.model.Builds;
import com.hardwarehamlet.hardwarehamlet.model.BuildsResponseBody;
import com.hardwarehamlet.hardwarehamlet.model.Comments;
import com.hardwarehamlet.hardwarehamlet.model.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuildsRepository {
    private static BuildsRepository INSTANCE;
    private final DataService dataService;

    private BuildsRepository(Context context) {
        this.dataService = DataSource.getDataService();
    }

    public static BuildsRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new BuildsRepository(context);
        }
        return INSTANCE;
    }

    public static void getBuildById(Context context, final BuildCallback callback, final long build_id){
        final DataService dataService = DataSource.getDataService();
        final AppDatabase appDatabase = AppDatabase.getInstance(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Builds build = appDatabase.getBuildsDAO().getBuildById(build_id);
                List<Comments> commentsList = appDatabase.getCommentsDAO().getCommentsListByBuild(build_id);
                List<Build_Components> build_componentsList = appDatabase.getBuildComponentsDAO().getBuildComponentesByBuild(build_id);
                final BuildsResponseBody buildsResponseBody = new BuildsResponseBody(build,build_componentsList,commentsList);
                if(buildsResponseBody.getBuild() != null && buildsResponseBody.getComments().size() > 0 || buildsResponseBody.getComponents() != null){
                    callback.onResult(buildsResponseBody);
                }
                Map<String,String> data = new HashMap<>();
                data.put("build_id", Long.toString(build_id));

                Call<List<BuildsResponseBody>> call = dataService.getBuildsList(data);

                call.enqueue(new Callback<List<BuildsResponseBody>>() {
                    @Override
                    public void onResponse(Call<List<BuildsResponseBody>> call, final Response<List<BuildsResponseBody>> response) {
                        if(response.isSuccessful()){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    List<BuildsResponseBody> buildResponse = response.body();
                                    assert buildResponse != null;
                                    Builds newBuild = buildResponse.get(0).getBuild();
                                    List<Comments> newCommentsList = buildResponse.get(0).getComments();
                                    List<Build_Components> newBuildComponents = buildResponse.get(0).getComponents();
                                    if(build != newBuild && newBuild != null){
                                        appDatabase.getBuildsDAO().deleteBuild(build_id);
                                        appDatabase.getCommentsDAO().deleteCommentsFromBuild(build_id);
                                        appDatabase.getBuildComponentsDAO().deleteBuildComponents(build_id);

                                        appDatabase.getBuildsDAO().insertBuild(newBuild);
                                        appDatabase.getBuildComponentsDAO().insertBuildComponents(newBuildComponents);
                                        appDatabase.getCommentsDAO().insertComments(newCommentsList);

                                        callback.onResult(buildResponse.get(0));
                                    }else{
                                        callback.onResult(buildsResponseBody);
                                    }
                                }
                            }).start();
                        }else{
                            callback.onError(response.errorBody().toString());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<BuildsResponseBody>> call, Throwable t) {

                    }
                });
            }
        }).start();

    }

    public static void getBuilds(final Context context, final AllBuildsCallback callback, final String sort, final int type_id, final String search){
        final DataService dataService = DataSource.getDataService();
        final AppDatabase appDatabase = AppDatabase.getInstance(context);
        new Thread(new Runnable() {
            @Override
            public void run() {

                List<Builds> buildsList = new ArrayList<>();
                if(search.equals("none")){
                    if(sort.equals("asc")){
                        buildsList = appDatabase.getBuildsDAO().getBuildsWithoutSearchASC(type_id);

                    }else{

                        buildsList = appDatabase.getBuildsDAO().getBuildsWithoutSearchDESC(type_id);

                    }
                }else{
                    String newSearch = "%" + search + "%";
                    if(sort.equals("asc")){

                        buildsList = appDatabase.getBuildsDAO().getAllBuildsWithTypeASC(newSearch,type_id);

                    }else{

                        buildsList = appDatabase.getBuildsDAO().getAllBuildsWithTypeDESC(newSearch,type_id);

                    }
                }
                callback.onResult(buildsList);
                final List<Builds> lastBuildList = buildsList;
                Map<String,String> data = new HashMap<>();
                data.put("recent",sort);
                data.put("build_type_id",String.valueOf(type_id));
                data.put("search",search);
                Call<List<BuildsResponseBody>> call = dataService.getBuildsList(data);

                call.enqueue(new Callback<List<BuildsResponseBody>>() {
                    @Override
                    public void onResponse(Call<List<BuildsResponseBody>> call, final Response<List<BuildsResponseBody>> response) {
                        if(response.isSuccessful()){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    List<BuildsResponseBody> buildsResponseList = response.body();
                                    List<Builds> buildsList = new ArrayList<>();
                                    assert buildsResponseList != null;
                                    for (int i = 0; i < buildsResponseList.size(); i++) {
                                        long build_id = buildsResponseList.get(i).getBuild().getBuild_id();
                                        List<Comments> lastCommentsList = appDatabase.getCommentsDAO()
                                                .getCommentsListByBuild(build_id);
                                        List<Build_Components> lastBuildComponentsList = appDatabase.getBuildComponentsDAO()
                                                .getBuildComponentesByBuild(build_id);

                                        buildsList.add(buildsResponseList.get(i).getBuild());
                                        List<Build_Components> build_componentsList = new ArrayList<>(buildsResponseList.get(i).getComponents());
                                        List<Comments> commentsList = new ArrayList<>(buildsResponseList.get(i).getComments());

                                        if(lastCommentsList.size() != commentsList.size() && commentsList.size() != 0){
                                            appDatabase.getCommentsDAO().deleteCommentsFromBuild(build_id);
                                            appDatabase.getCommentsDAO().insertComments(commentsList);
                                        }

                                        if(lastBuildComponentsList.size() != build_componentsList.size() && build_componentsList.size() != 0){
                                            appDatabase.getBuildComponentsDAO().deleteBuildComponents(build_id);
                                            appDatabase.getBuildComponentsDAO().insertBuildComponents(build_componentsList);
                                        }
                                    }

                                    if(lastBuildList.size() != buildsList.size() && buildsList.size() != 0){
                                        appDatabase.getBuildsDAO().deleAllBuilds();
                                        appDatabase.getBuildsDAO().inserBuildsList(buildsList);
                                        callback.onResult(buildsList);
                                    }else{
                                        callback.onResult(lastBuildList);
                                    }
                                }
                            }).start();
                        }else{
                            callback.onError("Couldn't fecth. Erro: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<BuildsResponseBody>> call, Throwable t) {
                        callback.onError("Couldn't fetch. Failure");
                        t.printStackTrace();
                    }
                });
            }
        }).start();


    }


    public static void insertBuild(final BuildRegistBody build, final InsertBuildCallback callback){
        final DataService dataService = DataSource.getDataService();

        Call<ResponseBody> call = dataService.insertBuild(build);
        //final AppDatabase appDatabase = AppDatabase.getInstance(context);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    ResponseBody responseBody = response.body();
                    if(responseBody.getResult().equals("success")){
                        callback.onResult(responseBody.getResult());
                    }
                }else{
                    callback.onResult("failure");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public static void getLastBuildId(final Context context, final LastBuildId callback){
        DataService dataService = DataSource.getDataService();

        Call<ResponseBody> call = dataService.getLastBuildId();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    ResponseBody responseBody = response.body();
                    long id = Long.parseLong(responseBody.getResult());
                    callback.onResult(id+1);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public static interface AllBuildsCallback{
        void onResult(List<Builds> buildsList);
        void onError(String error);
    }

    public static interface BuildCallback{
        void onResult(BuildsResponseBody builds);
        void onError(String error);
    }

    public static interface LastBuildId{
        void onResult(long id);
    }

    public static interface InsertBuildCallback{
        void onResult(String result);
        void onError(String error);
    }
}
