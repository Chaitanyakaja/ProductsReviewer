package volleyapp2.example.com.project258_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import static volleyapp2.example.com.project258_1.R.id.listViewReviews;

public class SavedProducts extends AppCompatActivity {

    public static ListView listViewSave;
    public static SaveAdapter saveAdapter;
    ArrayList<ModelListOfProducts> modelListOfProducts;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_products);
        listViewSave = (ListView) findViewById(R.id.listViewSavedProducts);
        db = new DatabaseHandler(this);
        modelListOfProducts = (ArrayList)db.getAllProducts();

        saveAdapter = new SaveAdapter(this,R.layout.activity_list_view_items_saved_products,modelListOfProducts);

        listViewSave.setAdapter(saveAdapter);








    }
}
