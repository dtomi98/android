package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Record extends AppCompatActivity {
    EditText nev, idopont, tipus,helyszin,szervezo;
    Button recordBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
        nev = (EditText) findViewById(R.id.txtNev);
        idopont = (EditText) findViewById(R.id.txtIdopont);
        tipus = (EditText) findViewById(R.id.txtTipus);
        helyszin = (EditText) findViewById(R.id.txtHelyszin);
        szervezo = (EditText) findViewById(R.id.txtSzervezo);
        recordBtn = (Button) findViewById(R.id.btnSave);
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nev = nev.getText().toString();
                String Idopont = idopont.getText().toString();
                String Tipus = tipus.getText().toString();
                String Helyszin = helyszin.getText().toString();
                String Szervezo = szervezo.getText().toString();
                DBHandler dbHandler = new DBHandler(Record.this);
                dbHandler.insertEventDetails(Nev, Idopont, Tipus, Helyszin, Szervezo);
                intent = new Intent(Record.this, Events.class);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Sikeres felvitel", Toast.LENGTH_SHORT).show();
            }
        });
    }
}