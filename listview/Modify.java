package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Modify extends AppCompatActivity {
    EditText nev, idopont, tipus, helyszin, szervezo;
    Button delete, update;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        Bundle b=getIntent().getExtras();
        b.getLong("id",id);
         String name= b.getString("nev");
        String date=b.getString("idopont");
        String type=b.getString("tipus");
        String location=b.getString("helyszin");
        String organiser= b.getString("szervezo");
        nev = (EditText)findViewById(R.id.txtNev);
        idopont = (EditText)findViewById(R.id.txtIdopont);
        tipus = (EditText)findViewById(R.id.txtTipus);
        helyszin=(EditText)findViewById(R.id.txtHelyszin);
        szervezo=(EditText)findViewById(R.id.txtSzervezo);
        update = (Button)findViewById(R.id.btnSave);
        delete=(Button)findViewById(R.id.btnDelete);
        nev.setText(name);
        idopont.setText(date);
        tipus.setText(type);
        helyszin.setText(location);
        szervezo.setText(organiser);
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
                dbHandler.UpdateEventDetails(Nev,Idopont,Tipus,Helyszin,Szervezo,(int) id);
                Intent intent = new Intent(Modify.this,Events.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Sikeres módosítás",Toast.LENGTH_SHORT).show();
            }
        });
       // delete.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //   public void onClick(View view) {
        //      DBHandler dbHandler = new DBHandler(Modify.this);
        //     dbHandler.DeleteEvent((int) id);
        //     Intent intent =new Intent(Modify.this,Events.class);
        //    Toast.makeText(getApplicationContext(), "Törölve",Toast.LENGTH_SHORT).show();
        //  }
        //});
    }
}