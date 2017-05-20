package volleyapp2.example.com.project258_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

/**
 * Created by katto on 5/1/2017.
 */

public class UsersAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelListOfProducts> DataList;
    //  ImageLoader imageLoader = Controller.getPermission().getImageLoader();

    public UsersAdapter(Activity activity, List<ModelListOfProducts> dataitem) {
        this.activity = activity;
        this.DataList = dataitem;
    }

    @Override
    public int getCount() {
        return DataList.size();
    }

    @Override
    public Object getItem(int location) {
        return DataList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_view_list_of_products, null);

   //     if (imageLoader == null)
   //         imageLoader = Controller.getPermission().getImageLoader();
   //     NetworkImageView thumbNail = (NetworkImageView) convertView
   //             .findViewById(R.id.thumbnail);
        TextView textViewProductName = (TextView) convertView.findViewById(R.id.textViewProductName);
        TextView textViewDescription = (TextView) convertView.findViewById(R.id.textViewDescription);
        TextView textViewPrice = (TextView) convertView.findViewById(R.id.textViewPrice);
        Button go = (Button) convertView.findViewById(R.id.buttonGo);

        WebView webViewProductImage = (WebView) convertView.findViewById(R.id.webViewProductImageListOfProducts);
        final ModelListOfProducts m = DataList.get(position);
      //  thumbNail.setImageUrl(m.getImage(), imageLoader);

        textViewProductName.setText(m.getProductName());
        textViewDescription.setText(m.getDescription());
        textViewPrice.setText(m.getPrice());

        final View finalConvertView = convertView;
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();


                Intent i = new Intent(finalConvertView.getContext(),product_details.class);
                i.putExtra("prodID", (Serializable) m);

                activity.startActivity(i);
            }
        });


        /*final View finalConvertView = convertView;
        textViewProductName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(finalConvertView.getContext(),"yellow",Toast.LENGTH_SHORT);
            }
        });
*/

      //  textViewStars.setText(m.getStars());
        webViewProductImage.getSettings().setLoadWithOverviewMode(true);
        webViewProductImage.getSettings().setUseWideViewPort(true);
        webViewProductImage.loadUrl(m.getImageSRC());
       // worth.setText(String.valueOf(m.getWorth()));
      //  year.setText(String.valueOf(m.getYear()));

        return convertView;
    }

}


        /*

        extends ArrayAdapter<Fetched_Data> {
    public UsersAdapter(Context context, ArrayList<Fetched_Data> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent) {
        // Get the data item for this position
        Fetched_Data user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (rowView == null) {
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_view_products_reviews, parent, false);
        }
        // Lookup view for data population
        TextView textViewReviewerName = (TextView) rowView.findViewById(R.id.textViewReviewername);
        TextView textViewThumbsUp = (TextView) rowView.findViewById(R.id.textViewThumbsUp);
        TextView textViewThumbsDown = (TextView) rowView.findViewById(R.id.textViewThumbsDown);
        TextView textViewStars = (TextView) rowView.findViewById(R.id.textViewStars);
        TextView textViewMainReview = (TextView) rowView.findViewById(R.id.textViewMainReview);
         //   Populate the data into the template view using the data object
        textViewReviewerName.setText(user.getName());
        textViewThumbsDown.setText(user.getDownVotes());
        // Return the completed view to render on screen
        return rowView;
    }
}








        */