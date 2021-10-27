package com.example.pruebafalsamapitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner origen, destino;
    Button res;
    EditText DATE;
    //EditText fechasa;
    ArrayList<String> valores= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        origen = (Spinner) findViewById(R.id.sp_origen);
        destino = (Spinner) findViewById(R.id.sp_destino);
        DATE = (EditText) findViewById(R.id.date);
        //fechasa = (EditText) findViewById(R.id.txt_fechas);
        res = (Button) findViewById(R.id.btn_reservar);

        valores.add(" ");
        valores.add("Seba");
        valores.add("Talca");
        valores.add("Santiago");
        valores.add("Valparaiso");
        valores.add("Rancagua");
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,valores);
        origen.setAdapter(adaptador);
        destino.setAdapter(adaptador);

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(origen.getSelectedItem()=="..."){
                    Toast.makeText(getApplicationContext(), "Debe seleccionar un origen",Toast.LENGTH_LONG).show();
                }
                if(destino.getSelectedItem()=="..."){
                    Toast.makeText(getApplicationContext(), "Debe seleccionar destino",Toast.LENGTH_LONG).show();
                }
                String or = origen.getSelectedItem().toString();
                String des = destino.getSelectedItem().toString();
                String da = DATE.getText().toString();
                Intent I = new Intent(getApplicationContext(),Indique.class);
                I.putExtra("SOrigen", or);
                I.putExtra("SDestino", des);
                I.putExtra("SDate",da);
                startActivity(I);
            }
        });
    }
}