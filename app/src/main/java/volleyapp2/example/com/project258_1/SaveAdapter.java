package volleyapp2.example.com.project258_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class SaveAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelListOfProducts> DataList;
    WebView webViewSave;
    TextView textViewTitle;
    TextView textViewPrice;
    Button buttonDelete, buttonGoToReview;
    DatabaseHandler db;


    public SaveAdapter(Activity activity, int activity_list_view_review, List<ModelListOfProducts> dataitem) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.d("IN SAVEADAPTER", "YELLOOWWW");

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.activity_list_view_items_saved_products, null);

        final ModelListOfProducts m = DataList.get(position);

        db = new DatabaseHandler(activity);

        webViewSave = (WebView) convertView.findViewById(R.id.webViewSave);
        textViewTitle = (TextView) convertView.findViewById(R.id.textViewSaveTitle);
        textViewPrice = (TextView) convertView.findViewById(R.id.textViewSaveTitle);
        buttonDelete = (Button) convertView.findViewById(R.id.buttonDelete);
       // buttonGoToReview = (Button) convertView.findViewById(R.id.buttonGoReviews);

        webViewSave.loadUrl(m.getImageSRC());
        textViewPrice.setText(m.getPrice());
        textViewTitle.setText(m.getProductName());



        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteContact(m);
                DataList.remove(m);
                //SavedProducts.listViewSave.removeViewAt(position);
               // SavedProducts.modelListOfProducts.remove(m.getProductID());
                SavedProducts.saveAdapter.notifyDataSetChanged();
                //activity.startActivity();
                //Intent intent = new Intent(activity, SavedProducts.class);
               // activity.startActivity(intent);

            }
        });






        return convertView;


    }


}
