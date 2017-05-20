package volleyapp2.example.com.project258_1;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

/**
 * Created by katto on 5/9/2017.
 */

public class ListOfProducts extends AppCompatActivity {

    Intent i;
    String productName;
    WebView webViewProductImage;
    ListView listViewProducts;
    UsersAdapter adapter;
    ArrayList<ModelListOfProducts> fetched_datas;
    String TAG = "VolleyError";


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_products);

        i = getIntent();
        productName = (String) i.getSerializableExtra("prodName");


        listViewProducts = (ListView) findViewById(R.id.listViewListOfProducts);
        fetched_datas = new ArrayList<ModelListOfProducts>();
        adapter = new UsersAdapter(this,fetched_datas);

        listViewProducts.setAdapter(adapter);

        listViewProducts.setClickable(true);



        fetchProducts();









    }

    private void fetchProducts() {


        String url = "http://api.walmartlabs.com/v1/search?apiKey=x8cww2sgbv3ekax7d6n7tanq&query="+productName;



        JsonObjectRequest jsonObjReq = (new JsonObjectRequest(Request.Method.GET,
                url, (JSONObject)null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object


                    JSONArray ii = response
                            .getJSONArray("items");

                    int i = 0;


                    while(response.length() != i) {
                        if(i > 10)
                            break;
                        JSONObject items = (JSONObject) ii.get(i);


                    //   JSONObject items = response.getJSONObject("items");
                    String name = items.getString("name");
                    String product_id = items.getString("itemId");
                    String upc = items.getString("upc");
                    String image = items.getString("mediumImage");
                    String price = items.getString("salePrice");
                    String desc = ""; //= items.getString("shortDescription");
                    String UPC = items.getString("upc");
                    String productURL = items.getString("productUrl");

                    //        execute(idsss);

                        ModelListOfProducts m = new ModelListOfProducts(image,name,desc,product_id,UPC,price,productURL);
                        fetched_datas.add(m);

                        i++;
                    }

                    adapter.notifyDataSetChanged();
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
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                // hidepDialog();
            }
        }

    ));

        // Adding request to request queue
        //
        // AppController.getInstance().addToRequestQueue(jsonObjReq);
        Volley.newRequestQueue(ListOfProducts.this).add(jsonObjReq);
        //  Volley.





    }


}
