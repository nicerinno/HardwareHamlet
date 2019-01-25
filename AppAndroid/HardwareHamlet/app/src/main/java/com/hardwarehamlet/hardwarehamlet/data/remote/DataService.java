package com.hardwarehamlet.hardwarehamlet.data.remote;

import com.hardwarehamlet.hardwarehamlet.builds.BuildRegistBody;
import com.hardwarehamlet.hardwarehamlet.model.Build_Type;
import com.hardwarehamlet.hardwarehamlet.model.Builds;
import com.hardwarehamlet.hardwarehamlet.model.BuildsResponseBody;
import com.hardwarehamlet.hardwarehamlet.model.Comments;
import com.hardwarehamlet.hardwarehamlet.model.Component_Type;
import com.hardwarehamlet.hardwarehamlet.model.Components;
import com.hardwarehamlet.hardwarehamlet.model.Medals;
import com.hardwarehamlet.hardwarehamlet.model.ResponseBody;
import com.hardwarehamlet.hardwarehamlet.model.Titles;
import com.hardwarehamlet.hardwarehamlet.model.UserRegistBody;
import com.hardwarehamlet.hardwarehamlet.model.Users;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface DataService {

    @GET("getLastBuildId.php")
    Call<ResponseBody> getLastBuildId();

    @POST("userValidation.php")
    Call<ResponseBody> userValidation(@Body UserRegistBody body);

    @POST("userRegistration.php")
    Call<ResponseBody> userRegistration(@Body UserRegistBody body);

    @GET("getComponents.php")
    Call<List<Components>> getAllComponents(@QueryMap Map<String,String> query);

    @GET("getComponents.php")
    Call<Components> getComponentById(@Query("component_id") long id);


    @GET("getUserInfo.php")
    Call<Users> getUserById(@Query("user_id") long user_id);

    @GET("getUserInfo.php")
    Call<ResponseBody> getUserByUsername(@Query("username") String username);

    @GET("getBuilds.php")
    Call<List<BuildsResponseBody>> getBuildsList(@QueryMap Map<String,String> query);

    @GET("setLike.php")
    Call<ResponseBody> setLike(@Query("user_id") long user_id,@Query("build_id") long build_id);

    @GET("removeLike.php")
    Call<ResponseBody> removeLike(@Query("user_id") long user_id,@Query("build_id") long build_id);

    @GET("checkIfLiked.php")
    Call<ResponseBody> checkIfLiked(@Query("user_id") long user_id,@Query("build_id") long build_id);

    @POST("buildRegistration.php")
    Call<ResponseBody> insertBuild(@Body BuildRegistBody build);

    @GET("getComments.php")
    Call<List<Comments>> getCommentsByBuild(@Query("build_id") long build_id);

    @GET("insertComment.php")
    Call<ResponseBody> insertComment(@Query("build_id") long build_id,@Query("content") String content
            , @Query("user_id") long user_id,@Query("regist_date") long regist_date);


    @GET("getBuildTypeList.php")
    Call<List<Build_Type>> getBuildTypeList();

    @GET("getComponentTypeList.php")
    Call<List<Component_Type>> getComponentTypeList();

    @GET("getMedalsInfo.php")
    Call<List<Medals>> getMedals();

    @GET("getTitlesInfo.php")
    Call<List<Titles>> getTitles();

    @GET("getRankings.php?ranking=users")
    Call<List<Users>> getRankingUsers();

    @GET("getRankings.php?ranking=components")
    Call<List<Components>> getRankingComponents();

    @GET("getRankings.php?ranking=builds")
    Call<List<BuildsResponseBody>> getRankingBuilds();
}
