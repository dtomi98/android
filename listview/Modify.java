package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Modify extends AppCompatActivity {
    EditText nev, idopont, tipus, helyszin, szervezo;
    Button delete, update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        nev = (EditText)findViewById(R.id.txtNev);
        idopont = (EditText)findViewById(R.id.txtIdopont);
        tipus = (EditText)findViewById(R.id.txtTipus);
        helyszin=(EditText)findViewById(R.id.txtHelyszin);
        szervezo=(EditText)findViewById(R.id.txtSzervezo);
        update = (Button)findViewById(R.id.btnSave);
        delete=(Button)findViewById(R.id.btnDelete);
        Intent intent = getIntent();
        int id=intent.getIntExtra("id",-1);
        DBHandler dbHandler = new DBHandler(Modify.this);
        Cursor cursor=dbHandler.getItem(id);
        String nevertek=cursor.getString(cursor.getColumnIndex("nev"));
        String ido=cursor.getString(cursor.getColumnIndex("idopont"));
        String tip=cursor.getString(cursor.getColumnIndex("tipus"));
        String hely=cursor.getString(cursor.getColumnIndex("helyszin"));
        String szerv=cursor.getString(cursor.getColumnIndex("szervezo"));
        nev.setText(nevertek);
        idopont.setText(ido);
        tipus.setText(tip);
        helyszin.setText(hely);
        szervezo.setText(szerv);


        update.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {


                String Nev = nev.getText().toString();
                String Idopont = idopont.getText().toString();
                String Tipus = tipus.getText().toString();
                String Helyszin = helyszin.getText().toString();
                String Szervezo = szervezo.getText().toString();
                DBHandler dbHandler = new DBHandler(Modify.this);
                dbHandler.UpdateEventDetails(Nev,Idopont,Tipus,Helyszin,Szervezo,id);
                Intent intent = new Intent(Modify.this,Events.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Sikeres módosítás",Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View view) {
              DBHandler dbHandler = new DBHandler(Modify.this);
             dbHandler.DeleteEvent(id);
             Intent intent =new Intent(Modify.this,Events.class);
              startActivity(intent);
            Toast.makeText(getApplicationContext(), "Törölve",Toast.LENGTH_SHORT).show();
          }
        });
    }
}