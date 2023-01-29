package fr.univpau.projetboavitz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        Button Bouton_Lancer = (Button)findViewById(R.id.boutonLancer);
        Bouton_Lancer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DataCollected dataColl = new DataCollected(MainActivity.this);



                Toast.makeText(getApplicationContext(),"Bouton fontionnel",Toast.LENGTH_SHORT).show();
                String[] Arr1 = dataColl.get_values_from_ServerConfig_CPU();
                String[] Arr2 = dataColl.get_values_from_ServerConfig_RAM();
                String[] Arr3 = dataColl.get_values_from_ServerConfig_SSD();
                String[] Arr4 = dataColl.get_values_from_ServerConfig_Others();
                String[] Arr5 = dataColl.get_values_from_Usage();

                for (int i=0;i<Arr1.length;i++){
                    Log.i("tab1", Arr1[i]);
                }
                for (int i=0;i<Arr2.length;i++){
                    Log.i("tab2", Arr2[i]);
                }
                for (int i=0;i<Arr3.length;i++){
                    Log.i("tab3", Arr3[i]);
                }
                for (int i=0;i<Arr4.length;i++){
                    Log.i("tab4", Arr4[i]);
                }
                for (int i=0;i<Arr5.length;i++){
                    Log.i("tab5", Arr5[i]);
                }

                try {
                    dataColl.JSON_creation(MainActivity.this);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Button Bouton_Graphe = (Button)findViewById(R.id.buttonGraphes);
        Bouton_Graphe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String pif = "aled";
                Intent intent = new Intent(MainActivity.this, Graphe.class);
                intent.putExtra("id_value", pif);
                startActivity(intent);
            }
        });

        String pif_reçu  = getIntent().getStringExtra("2");

    }
/*
    @Override
    protected void onResume() {
        super.onResume();
        //setContentView(R.layout.activity_main);


    }*/

  /*  @Override
    protected void onDestroy() {
        super.onDestroy();
    }*/




}