package volleyapp2.example.com.project258_1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class product_details extends AppCompatActivity {

    Intent i;
    ModelListOfProducts m;
    TextView textViewProductName, textViewPrice;
    WebView webViewProductImage;
    ListView listViewReviews;
    ImageView imageViewAmazon,imageViewWallmart;
    ReviewAdapter Radapter;
    ArrayList<ModelReview> fetched_datas;
    DatabaseHandler db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


        i = getIntent();
        m = (ModelListOfProducts) i.getSerializableExtra("prodID");

        textViewProductName = (TextView) findViewById(R.id.textViewProductNamepp);
        textViewPrice = (TextView) findViewById(R.id.textViewProductPrice);
        webViewProductImage = (WebView) findViewById(R.id.webViewProductImage);
        listViewReviews = (ListView) findViewById(R.id.listViewReviews);


        textViewProductName.setText(m.getProductName());
        textViewPrice.setText(m.getPrice());
        webViewProductImage.loadUrl(m.getImageSRC());
        db = new DatabaseHandler(this);
        imageViewAmazon = (ImageView) findViewById(R.id.imageViewAmazon);
        imageViewWallmart = (ImageView) findViewById(R.id.imageViewWallmart);
        Button buttonSave = (Button) findViewById(R.id.buttonSave);

        imageViewAmazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(m.getProductURL()); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        imageViewWallmart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(m.getProductURL()); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

      //  if (db.getProduct())

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.addContact(m);
                }catch (Exception e){
                    Log.d("DBError","Error adding to database");
                }

                Toast.makeText(getApplicationContext(),"Product Saved",Toast.LENGTH_SHORT).show();
            }
        });



        fetched_datas = new ArrayList<ModelReview>();

        Radapter = new ReviewAdapter(this,R.layout.activity_list_view_review,fetched_datas);

        listViewReviews.setAdapter(Radapter);



        fetchReviews();






    }

    private void fetchReviews() {


        String url1 = "http://api.walmartlabs.com/v1/reviews/"+ m.getProductID() +"?format=json&apiKey=x8cww2sgbv3ekax7d6n7tanq";

        //   String url1 = "http://api.walmartlabs.com/v1/reviews/21805451?format=json&apiKey=x8cww2sgbv3ekax7d6n7tanq";


        //   final String[] finalReviewerName = reviewerName;
        JsonObjectRequest jsonObjReq1 = new JsonObjectRequest(Request.Method.GET,
                url1, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response", response.toString());

                try {
                    String jsonResponse = null;
                    // Parsing json object response
                    // response will be a json object
                    JSONArray ii = response
                            .getJSONArray("reviews");

                    int i = 0;
                    //       for (int i=0; i< 10; i++){
                    while(ii.length() >= i ){
                        if(i==5) break;

                        JSONObject items = (JSONObject) ii.get(i);
                        // JSONObject rate = items.getJSONObject("overallRating");
                        String rate = items.getJSONObject("overallRating").getString("rating");
                        // JSONObject rate = items.getJSONObject("overallRating");

                        //int stars = rate.getInt("rating");

/*
                        //   JSONObject items = response.getJSONObject("items");
                        String reviewer = items.getString("reviewer");
                        // reviewerName[0] = reviewer;

                        String reviewText = items.getString("reviewText");
                        //  mainReview = reviewText;
                        String title = items.getString("title");
                        String upVotes = items.getString("upVotes");
                        //   thumbsUp = upVotes;
                        String downVotes = items.getString("downVotes");
                        //   thumbsDown[0] = downVotes;


                        jsonResponse = "";
                        jsonResponse += "     Reviewer: " + reviewer;
                        // m.setProductName(name);
                        jsonResponse += "     Review Text: " + reviewText;
                        // m.setUPC(upc);
                        jsonResponse += "     Title: " + title;
                        // m.setImageSrc(image);
                        jsonResponse += "     Up Votes: " + upVotes;
                        jsonResponse += "     Down Votes: " + downVotes;
                        // m.setPrice(price);
                        // jsonResponse = thumbsUp;

*/
//                        textViewProductName.setText(jsonResponse);

                        ModelReview fetched_data = new ModelReview(rate,
                                items.getString("upVotes"),
                                items.getString("downVotes"),
                                items.getString("reviewText"),
                                items.getString("reviewer"),
                                items.getString("title"));

                        fetched_datas.add(fetched_data);

                        // Toast.makeText(getApplicationContext(),fetched_data.getTitle(),Toast.LENGTH_SHORT);




                        // if (ii.get(i+1)==null)
                        //    break;
                        i++;
//                            }


                    }

                    Collections.sort(fetched_datas, new Comparator<ModelReview>() {
                        @Override
                        public int compare(ModelReview o1, ModelReview o2) {
                            return Integer.parseInt(o2.getThumbsUp()) - Integer.parseInt(o1.getThumbsUp());
                        }
                    });

                   // textViewReview.setText(fetched_datas.get(1).getTitle());
                  //  adapter.notifyDataSetChanged();

                    Radapter.notifyDataSetChanged();
                    Log.d("TAG","afterNotify");

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                //hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                // hidepDialog();
            }
        });

        // Adding request to request queue
        //
        // AppController.getInstance().addToRequestQueue(jsonObjReq);
        Volley.newRequestQueue(this).add(jsonObjReq1);







    }
}
