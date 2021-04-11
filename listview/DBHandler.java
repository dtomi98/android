package com.example.listview;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "szervezes";
    private static final String TABLE_Events = "esemenyek";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "nev";
    private static final String KEY_TIM = "idopont";
    private static final String KEY_TYP = "tipus";
    private static final String KEY_LOC = "helyszin";
    private static final String KEY_ORG = "szervezo";
    private String nev;

    public DBHandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Events + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_TIM + " TEXT,"
                + KEY_TYP + " TEXT,"
                + KEY_LOC + " TEXT,"
                + KEY_ORG + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Events);
        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details
    void insertEventDetails(String nev,String idopont,String tipus,String helyszin,String szervezo){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, nev);
        cValues.put(KEY_TIM, idopont);
        cValues.put(KEY_TYP, tipus);
        cValues.put(KEY_LOC, helyszin);
        cValues.put(KEY_ORG, szervezo);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Events,null, cValues);
        db.close();
    }
    // Get User Details
    public ArrayList<HashMap<String, String>> GetEvents(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> eventList = new ArrayList<>();
        String query = "SELECT nev,idopont,tipus,helyszin,szervezo FROM "+ TABLE_Events;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> event = new HashMap<>();
            event.put("nev",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            event.put("idopont",cursor.getString(cursor.getColumnIndex(KEY_TIM)));
            event.put("tipus",cursor.getString(cursor.getColumnIndex(KEY_TYP)));
            event.put("helyszin",cursor.getString(cursor.getColumnIndex(KEY_LOC)));
            event.put("szervezo",cursor.getString(cursor.getColumnIndex(KEY_ORG)));
            eventList.add(event);
        } 
        return  eventList;
    }
    public Cursor getItemID(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " SELECT " + KEY_ID + " FROM " + TABLE_Events + " WHERE " + KEY_TIM + " = '" + id + "'" ;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    // Get User Details based on userid
    public ArrayList<HashMap<String, String>> GetEventById(String eventid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> eventList = new ArrayList<>();
        String query = "SELECT nev,idopont,tipus,helyszin,szervezo FROM "+ TABLE_Events;
        Cursor cursor = db.query(TABLE_Events, new String[]{KEY_NAME,KEY_TIM,KEY_TYP,KEY_LOC,KEY_ORG},KEY_ID+ "=?",new String[]{String.valueOf(eventid)},null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> event = new HashMap<>();
            event.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            event.put("idopont",cursor.getString(cursor.getColumnIndex(KEY_TIM)));
            event.put("tipus",cursor.getString(cursor.getColumnIndex(KEY_TYP)));
            event.put("helyszin",cursor.getString(cursor.getColumnIndex(KEY_LOC)));
            event.put("szervezo",cursor.getString(cursor.getColumnIndex(KEY_ORG)));
            eventList.add(event);
        }
        return  eventList;
    }
    // Delete User Details
    public void DeleteEvent(int eventid){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Events, KEY_ID+" = ?",new String[]{String.valueOf(eventid)});
        db.close();
    }
    // Update User Details
    public int UpdateEventDetails(String nev,String idopont,String tipus,String helyszin,String szervezo,long id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_NAME, nev);
        cVals.put(KEY_TIM, idopont);
        cVals.put(KEY_TYP, tipus);
        cVals.put(KEY_LOC, helyszin);
        cVals.put(KEY_ORG, szervezo);
        int count = db.update(TABLE_Events, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }
}