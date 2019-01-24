package com.hardwarehamlet.hardwarehamlet.data.local;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.hardwarehamlet.hardwarehamlet.model.Build_Components;
import com.hardwarehamlet.hardwarehamlet.model.Build_Type;
import com.hardwarehamlet.hardwarehamlet.model.Builds;
import com.hardwarehamlet.hardwarehamlet.model.Comments;
import com.hardwarehamlet.hardwarehamlet.model.Component_Type;
import com.hardwarehamlet.hardwarehamlet.model.Components;
import com.hardwarehamlet.hardwarehamlet.model.Likes;
import com.hardwarehamlet.hardwarehamlet.model.Medals;
import com.hardwarehamlet.hardwarehamlet.model.Titles;
import com.hardwarehamlet.hardwarehamlet.model.Users;

@Database(entities = {Components.class,Builds.class,Titles.class,Comments.class,Build_Components.class,
        Medals.class,Build_Type.class, Component_Type.class, Users.class, Likes.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract Build_TypeDAO getBuild_TypeDAO();
    public abstract ComponentsDAO getComponentsDAO();
    public abstract BuildsDAO getBuildsDAO();
    public abstract Build_ComponentsDAO getBuildComponentsDAO();
    public abstract CommentsDAO getCommentsDAO();
    public abstract MedalsDAO getMedalsDAO();
    public abstract TitlesDAO getTitlesDAO();
    private static AppDatabase INSTANCE;

    public synchronized static AppDatabase getInstance(Context context) {
        if (null == INSTANCE) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "HardwareHamletDB")
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }
}
