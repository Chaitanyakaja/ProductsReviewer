package volleyapp2.example.com.project258_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katto on 5/17/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "saveProducts";
    private static final String TABLE_SAVE = "saved";
    private static final String KEY_ID = "id";
    private static final String KEY_PROD_NAME = "product_name";
    private static final String KEY_DESC = "description";
    private static final String KEY_PROD_ID = "prod_ID";
    private static final String KEY_PROD_UPC = "prod_UPC";
    private static final String KEY_PRICE = "price";
    private static final String KEY_PROD_URL = "Wallmart_URL";
    private static final String KEY_IMG_SRC = "image_SRC";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SAVE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_PROD_ID + " TEXT,"
                + KEY_PROD_NAME + " TEXT,"
                + KEY_PROD_UPC + " TEXT,"
                + KEY_PROD_URL + " TEXT,"
                + KEY_PRICE + " TEXT,"
                + KEY_DESC + " TEXT,"
                + KEY_IMG_SRC + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVE);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    void addContact(ModelListOfProducts modelListOfProducts) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROD_ID, modelListOfProducts.getProductID()); // Contact Name
        values.put(KEY_PROD_NAME, modelListOfProducts.getProductName()); // Contact Phone
        values.put(KEY_PROD_UPC, modelListOfProducts.getProductUPC());
        values.put(KEY_PROD_URL, modelListOfProducts.getProductURL());
        values.put(KEY_PRICE, modelListOfProducts.getPrice());
        values.put(KEY_DESC, modelListOfProducts.getDescription());
        values.put(KEY_IMG_SRC, modelListOfProducts.getImageSRC());

        // Inserting Row
        db.insert(TABLE_SAVE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    ModelListOfProducts getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SAVE, new String[] { KEY_ID,
                        KEY_PROD_ID, KEY_PROD_NAME,KEY_PROD_UPC,KEY_PROD_URL,KEY_PRICE,KEY_DESC,KEY_IMG_SRC }, KEY_PROD_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ModelListOfProducts modelListOfProducts = new ModelListOfProducts(cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7)


        );
        // return contact
        return modelListOfProducts;
    }

    // code to get all contacts in a list view
    public List<ModelListOfProducts> getAllProducts() {
        List<ModelListOfProducts> contactList = new ArrayList<ModelListOfProducts>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SAVE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ModelListOfProducts modelListOfProducts = new ModelListOfProducts();
                //contact.setID(Integer.parseInt(cursor.getString(0)));
                modelListOfProducts.setProductID(cursor.getString(1));
                modelListOfProducts.setProductName(cursor.getString(2));
                modelListOfProducts.setProductUPC(cursor.getString(3));
                modelListOfProducts.setProductURL(cursor.getString(4));
                modelListOfProducts.setPrice(cursor.getString(5));
                modelListOfProducts.setDescription(cursor.getString(6));
                modelListOfProducts.setImageSRC(cursor.getString(7));
                // Adding contact to list
                contactList.add(modelListOfProducts);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // code to update the single contact
    public int updateContact(ModelListOfProducts modelListOfProducts) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROD_ID, modelListOfProducts.getProductID()); // Contact Name
        values.put(KEY_PROD_NAME, modelListOfProducts.getProductName()); // Contact Phone
        values.put(KEY_PROD_UPC, modelListOfProducts.getProductUPC());
        values.put(KEY_PROD_URL, modelListOfProducts.getProductURL());
        values.put(KEY_PRICE, modelListOfProducts.getPrice());
        values.put(KEY_DESC, modelListOfProducts.getDescription());
        values.put(KEY_IMG_SRC, modelListOfProducts.getImageSRC());



        // updating row
        return db.update(TABLE_SAVE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(modelListOfProducts.getProductID()) });
    }

    // Deleting single contact
    public void deleteContact(ModelListOfProducts modelListOfProducts) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SAVE, KEY_PROD_ID + " = ?",
                new String[] { String.valueOf(modelListOfProducts.getProductID()) });
        db.close();
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SAVE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}