package com.hardwarehamlet.hardwarehamlet.repositories;

import android.content.Context;

import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataService;
import com.hardwarehamlet.hardwarehamlet.data.remote.DataSource;
import com.hardwarehamlet.hardwarehamlet.model.Comments;
import com.hardwarehamlet.hardwarehamlet.model.ResponseBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsRepository {
    private static CommentsRepository INSTANCE;
    private final DataService dataService;

    private CommentsRepository(Context context) {
        this.dataService = DataSource.getDataService();
    }

    public static CommentsRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CommentsRepository(context);
        }
        return INSTANCE;
    }

    public static void getCommentsListFromBuild(Context context,final long build_id, final CommentsCallBack callBack){
        final AppDatabase appDatabase = AppDatabase.getInstance(context);
        final DataService dataService = DataSource.getDataService();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Comments> lastCommentsList = appDatabase.getCommentsDAO().getCommentsListByBuild(build_id);
                if(lastCommentsList.size() != 0){
                    callBack.onResult(lastCommentsList);
                }

                Call<List<Comments>> call = dataService.getCommentsByBuild(build_id);
                call.enqueue(new Callback<List<Comments>>() {
                    @Override
                    public void onResponse(Call<List<Comments>> call, final Response<List<Comments>> response) {
                        if(response.isSuccessful()){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    List<Comments> newCommentsList = response.body();
                                    if(lastCommentsList.size() != newCommentsList.size()){
                                        appDatabase.getCommentsDAO().deleteCommentsFromBuild(build_id);
                                        appDatabase.getCommentsDAO().insertComments(newCommentsList);
                                        callBack.onResult(newCommentsList);
                                    }
                                }
                            }).start();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Comments>> call, Throwable t) {

                    }
                });
            }
        }).start();
    }

    public static void sendComment(Context context,final long build_id, final String content, final long user_id, final long regist_date, final InsertCommentCallBack callBack){
        //final AppDatabase appDatabase = AppDatabase.getInstance(context);
        final DataService dataService = DataSource.getDataService();

        Call<ResponseBody> call = dataService.insertComment(build_id,content,user_id, regist_date);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    ResponseBody responseBody = response.body();
                    if(responseBody.getResult().equals("successful")){
                        callBack.onResult(responseBody.getResult());
                    }else{
                        callBack.onResult(responseBody.getResult());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static interface CommentsCallBack{
        void onResult(List<Comments> commentsList);
    }

    public static interface InsertCommentCallBack{
        void onResult(String result);
    }
}
