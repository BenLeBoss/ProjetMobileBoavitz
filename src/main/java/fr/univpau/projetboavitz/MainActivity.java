package fr.univpau.projetboavitz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public TextInput TextInput;
    String JsonRep;
    String JsonDataCollected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);



        Button Bouton_Lancer = (Button) findViewById(R.id.boutonLancer);
        Bouton_Lancer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DataCollected dataColl = new DataCollected(MainActivity.this);


                Toast.makeText(getApplicationContext(), "Bouton fontionnel", Toast.LENGTH_SHORT).show();
                String[] Arr1 = dataColl.get_values_from_ServerConfig_CPU();
                String[] Arr2 = dataColl.get_values_from_ServerConfig_RAM();
                String[] Arr3 = dataColl.get_values_from_ServerConfig_SSD();
                String[] Arr4 = dataColl.get_values_from_ServerConfig_Others();
                String[] Arr5 = dataColl.get_values_from_Usage();

                for (int i = 0; i < Arr1.length; i++) {
                    Log.i("tab1", Arr1[i]);
                }
                for (int i = 0; i < Arr2.length; i++) {
                    Log.i("tab2", Arr2[i]);
                }
                for (int i = 0; i < Arr3.length; i++) {
                    Log.i("tab3", Arr3[i]);
                }
                for (int i = 0; i < Arr4.length; i++) {
                    Log.i("tab4", Arr4[i]);
                }
                for (int i = 0; i < Arr5.length; i++) {
                    Log.i("tab5", Arr5[i]);
                }


                try {
                    JsonDataCollected = dataColl.JSON_creation(MainActivity.this, Arr1, Arr2, Arr3, Arr4, Arr5);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                requetePost req = new requetePost();
                req.execute(JsonDataCollected);
                System.out.println("Sortie de l'api : " + JsonRep);

            }
        });

        Button Bouton_Graphe = (Button) findViewById(R.id.buttonGraphes);
        Bouton_Graphe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String pif = "aled";
                Intent intent = new Intent(MainActivity.this, Graphe.class);
                intent.putExtra("id_value", pif);
                startActivity(intent);
            }
        });

        String pif_reçu = getIntent().getStringExtra("2");

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


    public class requetePost extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) throws RuntimeException {
            //lien
            URL url = null;
            try {
                url = new URL("https://uppa.api.boavizta.org/v1/server/?verbose=true&allocation=TOTAL");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

            //ouvre la connexion
            HttpURLConnection UrlCon = null;
            try {
                UrlCon = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //requete post
            try {
                UrlCon.setRequestMethod("POST");
            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            }

            //de type json
            UrlCon.setRequestProperty("Content-Type", "application/json");

            //accepte les réponses
            UrlCon.setRequestProperty("Accept", "application/json");

            //pour écrire du contenu de le fil
            UrlCon.setDoOutput(true);



            //avec notre json
            try (OutputStream os = UrlCon.getOutputStream()) {
                byte[] input = JsonDataCollected.getBytes("utf-8");
                try {
                    os.write(input, 0, input.length);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            try (BufferedReader br = new BufferedReader(new InputStreamReader(UrlCon.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while (true) {
                    try {
                        if (!((responseLine = br.readLine()) != null)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    response.append(responseLine.trim());
                }
                JsonRep = response.toString();
                return response.toString();
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }


}