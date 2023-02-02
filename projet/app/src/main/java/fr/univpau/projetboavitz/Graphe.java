package fr.univpau.projetboavitz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;

public class Graphe extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        String pif_reçu  = getIntent().getStringExtra("2");

        Button Bouton_Retour = (Button)findViewById(R.id.boutonRetour);
        Bouton_Retour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String pif = "aled2";
                Intent intent = new Intent(Graphe.this, MainActivity.class);
                intent.putExtra("id_value", pif);
                startActivity(intent);
            }
        });
    }
    BarChart chartGlobalWarning;
    BarChart chartPrimaryEnergy;
    BarChart chartRessExhausted;
    ArrayList<BarChart> barChartList = new ArrayList<>();
    private void setupCharts(){



        //Initialisation des graphiques
        chartGlobalWarning = findViewById(R.id.graphe_global);
        chartPrimaryEnergy = findViewById(R.id.graphe_primary);
        chartRessExhausted = findViewById(R.id.graphe_abiotic);

        //Ajout des graphiques à la liste
        barChartList.add(chartGlobalWarning);
        barChartList.add(chartPrimaryEnergy);
        barChartList.add(chartRessExhausted);

    }



}
