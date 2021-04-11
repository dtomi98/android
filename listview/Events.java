package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Events extends AppCompatActivity {
    Intent intent,intentu;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        ArrayList<HashMap<String, String>> eventList;
        try (DBHandler db = new DBHandler(this)) {
            eventList = db.GetEvents();
        }
        ListView lv = (ListView) findViewById(R.id.event_list);
        ListAdapter adapter = new SimpleAdapter(this, eventList, R.layout.list_row,new String[]{"id","nev","idopont","tipus","helyszin","szervezo"}, new int[]{R.id.nev, R.id.idopont, R.id.tipus,R.id.helyszin,R.id.szervezo});
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap<String, String> event = eventList.get(position);
                intentu = new Intent(Events.this, Modify.class);
                startActivity(intentu);
                finish();


            }
        } );

        Button back = (Button)findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(Events.this,Record.class);
                startActivity(intent);




            }
        });
    }
}