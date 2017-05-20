package volleyapp2.example.com.project258_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by katto on 5/14/2017.
 */

public class ReviewAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelReview> DataList;


    public ReviewAdapter(Activity activity, int activity_list_view_review, List<ModelReview> dataitem) {
        this.activity = activity;
        this.DataList = dataitem;
    }


    @Override




    public int getCount() {
        return DataList.size();
    }

    @Override
    public Object getItem(int position) {
        return DataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("ErrorJson","IN GETVIEW");

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.activity_list_view_review, null);

        //     if (imageLoader == null)
        //         imageLoader = Controller.getPermission().getImageLoader();
        //     NetworkImageView thumbNail = (NetworkImageView) convertView
        //             .findViewById(R.id.thumbnail);
        TextView textViewReviewerName = (TextView) convertView.findViewById(R.id.textViewReviewerName);
        TextView textViewThumbsUp = (TextView) convertView.findViewById(R.id.textViewLikes);
        TextView textViewThumbsDown = (TextView) convertView.findViewById(R.id.textViewDislikes);
        TextView textViewStars = (TextView) convertView.findViewById(R.id.textViewStars);
        TextView textViewMainReview = (TextView) convertView.findViewById(R.id.textViewReviews);
        TextView textViewTital = (TextView) convertView.findViewById(R.id.textViewTitle);
        //Fetched_Data m = DataList.get(position);
        //  thumbNail.setImageUrl(m.getImage(), imageLoader);
        ModelReview m = DataList.get(position);



        textViewReviewerName.setText(m.getReviewerName());
        textViewMainReview.setText(m.getReview());
        textViewStars.setText(m.getStars());
        textViewThumbsDown.setText(m.getThumbsUp());
        textViewThumbsUp.setText(m.getThumbsDown());
        textViewTital.setText(m.getReviewTitle());




        return convertView;
    }










}



