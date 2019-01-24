package com.hardwarehamlet.hardwarehamlet.builds;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hardwarehamlet.hardwarehamlet.R;
import com.hardwarehamlet.hardwarehamlet.data.local.AppDatabase;
import com.hardwarehamlet.hardwarehamlet.model.Comments;
import com.hardwarehamlet.hardwarehamlet.model.Titles;
import com.hardwarehamlet.hardwarehamlet.model.Users;
import com.hardwarehamlet.hardwarehamlet.repositories.UsersRepository;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentsListAdapter extends BaseAdapter {
    private static final SimpleDateFormat todaySimpleDateFormat = new SimpleDateFormat("hh:mm:ss");
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    private List<Comments> commentsList;
    private Context context;
    private int numberOfItems;
    private Activity activity;

    public CommentsListAdapter(Context context, Activity activity) {
        this.context = context;
        this.commentsList = new ArrayList<>();
        this.activity = activity;
    }

    @Override
    public int getCount() {
        if(this.numberOfItems > this.commentsList.size()){
            return this.commentsList.size();
        }else{
            return this.numberOfItems;
        }
    }

    @Override
    public Comments getItem(int position) {
        return this.commentsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.commentsList.get(position).getComment_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.comments_row, parent, false);
        }
        final View view = convertView;
        final TextView textViewUser = view.findViewById(R.id.textViewUser);
        final TextView textViewComment = view.findViewById(R.id.textViewComment);
        final TextView textViewTimestamp = view.findViewById(R.id.textViewTimeStamp);
        final TextView textViewTitle = view.findViewById(R.id.textViewUserTitle);

        final Comments comment = getItem(position);
        textViewComment.setText(comment.getContent());
        textViewTimestamp.setText(getFormatedDate(comment.getRegist_date()));
//TODO: Mostrar o título, incluindo a sua cor, onde quer que apareçam users.
        UsersRepository.getUserById(context, new UsersRepository.UsersCallback() {
            @Override
            public void onResult(final Users user) {
                textViewUser.setText(user.getUsername());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase appDatabase = AppDatabase.getInstance(context);
                        final Titles title = appDatabase.getTitlesDAO().getTitleById(user.getTitle_id());
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(!title.getColor().isEmpty()){
                                    textViewTitle.setTextColor(Color.parseColor(title.getColor()));
                                }
                                textViewTitle.setText(title.getName());
                            }
                        });
                    }
                }).start();

            }

            @Override
            public void onError(String error) {

            }
        },comment.getUser_id());



        return convertView;
    }

    public void refreshList(List<Comments> commentsList, int numberItems){
        this.commentsList = commentsList;
        this.numberOfItems = numberItems;
        notifyDataSetChanged();
    }

    public String getFormatedDate(long miliseconds){
        if(DateUtils.isToday(miliseconds)){
            return todaySimpleDateFormat.format(new Date(miliseconds));
        } else {
            return simpleDateFormat.format(new Date(miliseconds));
        }
    }
}
