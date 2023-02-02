package fr.univpau.projetboavitz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

public class Graphe extends Activity {

    String JsonRep;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        JsonRep  = getIntent().getStringExtra("id_value");

        System.out.println("Affichage du coté des graphes : " + JsonRep);

        try {
            JsonParsing(JsonRep);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

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

    void JsonParsing(String json_to_parse) throws JSONException {
        JSONObject obj_parsed = new JSONObject(json_to_parse);

        //partie Global warming
        double GwpTotal = obj_parsed.getJSONObject("impacts").getJSONObject("gwp").getDouble("manufacture");
        String GwpUnit = obj_parsed.getJSONObject("impacts").getJSONObject("gwp").getString("unit");
        double Gwpusage = obj_parsed.getJSONObject("impacts").getJSONObject("gwp").getDouble("use");

        double GwpImpAssembly = obj_parsed.getJSONObject("verbose").getJSONObject("ASSEMBLY-1").getJSONObject("manufacture_impacts").getJSONObject("gwp").getDouble("value");
        double GwpAssUnits = obj_parsed.getJSONObject("verbose").getJSONObject("ASSEMBLY-1").getDouble("units");
        double GwpImpCpu = obj_parsed.getJSONObject("verbose").getJSONObject("CPU-1").getJSONObject("manufacture_impacts").getJSONObject("gwp").getDouble("value");
        double GwpCpuUnits = obj_parsed.getJSONObject("verbose").getJSONObject("CPU-1").getDouble("units");
        double GwpImpRam = obj_parsed.getJSONObject("verbose").getJSONObject("RAM-1").getJSONObject("manufacture_impacts").getJSONObject("gwp").getDouble("value");
        double GwpRamUnits = obj_parsed.getJSONObject("verbose").getJSONObject("RAM-1").getDouble("units");
        double GwpImpSSD = obj_parsed.getJSONObject("verbose").getJSONObject("SSD-1").getJSONObject("manufacture_impacts").getJSONObject("gwp").getDouble("value");
        double GwpSsdUnits = obj_parsed.getJSONObject("verbose").getJSONObject("SSD-1").getDouble("units");
        double GwpImpHDD = obj_parsed.getJSONObject("verbose").getJSONObject("HDD-1").getJSONObject("manufacture_impacts").getJSONObject("gwp").getDouble("value");
        double GwpHddUnits = obj_parsed.getJSONObject("verbose").getJSONObject("HDD-1").getDouble("units");
        double GwpImpPower = obj_parsed.getJSONObject("verbose").getJSONObject("POWER_SUPPLY-1").getJSONObject("manufacture_impacts").getJSONObject("gwp").getDouble("value");
        double GwpPowerUnits = obj_parsed.getJSONObject("verbose").getJSONObject("POWER_SUPPLY-1").getDouble("units");
        double GwpImpCase = obj_parsed.getJSONObject("verbose").getJSONObject("CASE-1").getJSONObject("manufacture_impacts").getJSONObject("gwp").getDouble("value");
        double GwpCaseUnits = obj_parsed.getJSONObject("verbose").getJSONObject("CASE-1").getDouble("units");
        double GwpImpMotherB = obj_parsed.getJSONObject("verbose").getJSONObject("MOTHERBOARD-1").getJSONObject("manufacture_impacts").getJSONObject("gwp").getDouble("value");
        double GwpMotherUnits = obj_parsed.getJSONObject("verbose").getJSONObject("MOTHERBOARD-1").getDouble("units");
        double TotalGwp = GwpImpAssembly * GwpAssUnits+ GwpImpCpu * GwpCpuUnits +GwpImpRam * GwpRamUnits + GwpImpSSD * GwpSsdUnits+ GwpImpHDD * GwpHddUnits + GwpImpPower * GwpPowerUnits + GwpImpCase * GwpCaseUnits + GwpImpMotherB * GwpMotherUnits;


        //partie Primary energie
        double PeTotal = obj_parsed.getJSONObject("impacts").getJSONObject("pe").getDouble("manufacture");
        String PeUnit = obj_parsed.getJSONObject("impacts").getJSONObject("pe").getString("unit");
        double Peusage = obj_parsed.getJSONObject("impacts").getJSONObject("pe").getDouble("use");

        double PeImpAssembly = obj_parsed.getJSONObject("verbose").getJSONObject("ASSEMBLY-1").getJSONObject("manufacture_impacts").getJSONObject("pe").getDouble("value");
        double PeImpCpu = obj_parsed.getJSONObject("verbose").getJSONObject("CPU-1").getJSONObject("manufacture_impacts").getJSONObject("pe").getDouble("value");
        double PeImpRam = obj_parsed.getJSONObject("verbose").getJSONObject("RAM-1").getJSONObject("manufacture_impacts").getJSONObject("pe").getDouble("value");
        double PeImpSSD = obj_parsed.getJSONObject("verbose").getJSONObject("SSD-1").getJSONObject("manufacture_impacts").getJSONObject("pe").getDouble("value");
        double PeImpHDD = obj_parsed.getJSONObject("verbose").getJSONObject("HDD-1").getJSONObject("manufacture_impacts").getJSONObject("pe").getDouble("value");
        double PeImpPower = obj_parsed.getJSONObject("verbose").getJSONObject("POWER_SUPPLY-1").getJSONObject("manufacture_impacts").getJSONObject("pe").getDouble("value");
        double PeImpCase = obj_parsed.getJSONObject("verbose").getJSONObject("CASE-1").getJSONObject("manufacture_impacts").getJSONObject("pe").getDouble("value");
        double PeImpMotherB = obj_parsed.getJSONObject("verbose").getJSONObject("MOTHERBOARD-1").getJSONObject("manufacture_impacts").getJSONObject("pe").getDouble("value");
        double TotalPe = PeImpAssembly * GwpAssUnits+ PeImpCpu * GwpCpuUnits +PeImpRam * GwpRamUnits + PeImpSSD * GwpSsdUnits+ PeImpHDD * GwpHddUnits + PeImpPower * GwpPowerUnits + PeImpCase * GwpCaseUnits + PeImpMotherB * GwpMotherUnits;


        //partie Abiotic depletion potential
        double AdpTotal = obj_parsed.getJSONObject("impacts").getJSONObject("adp").getDouble("manufacture");
        String AdpUnit = obj_parsed.getJSONObject("impacts").getJSONObject("adp").getString("unit");
        double Adpusage = obj_parsed.getJSONObject("impacts").getJSONObject("adp").getDouble("use");

        double AdpImpAssembly = obj_parsed.getJSONObject("verbose").getJSONObject("ASSEMBLY-1").getJSONObject("manufacture_impacts").getJSONObject("adp").getDouble("value");
        double AdpImpCpu = obj_parsed.getJSONObject("verbose").getJSONObject("CPU-1").getJSONObject("manufacture_impacts").getJSONObject("adp").getDouble("value");
        double AdpImpRam = obj_parsed.getJSONObject("verbose").getJSONObject("RAM-1").getJSONObject("manufacture_impacts").getJSONObject("adp").getDouble("value");
        double AdpImpSSD = obj_parsed.getJSONObject("verbose").getJSONObject("SSD-1").getJSONObject("manufacture_impacts").getJSONObject("adp").getDouble("value");
        double AdpImpHDD = obj_parsed.getJSONObject("verbose").getJSONObject("HDD-1").getJSONObject("manufacture_impacts").getJSONObject("adp").getDouble("value");
        double AdpImpPower = obj_parsed.getJSONObject("verbose").getJSONObject("POWER_SUPPLY-1").getJSONObject("manufacture_impacts").getJSONObject("adp").getDouble("value");
        double AdpImpCase = obj_parsed.getJSONObject("verbose").getJSONObject("CASE-1").getJSONObject("manufacture_impacts").getJSONObject("adp").getDouble("value");
        double AdpImpMotherB = obj_parsed.getJSONObject("verbose").getJSONObject("MOTHERBOARD-1").getJSONObject("manufacture_impacts").getJSONObject("adp").getDouble("value");
        double TotalAdp = AdpImpAssembly * GwpAssUnits+ AdpImpCpu * GwpCpuUnits +AdpImpRam * GwpRamUnits + AdpImpSSD * GwpSsdUnits+ AdpImpHDD * GwpHddUnits + AdpImpPower * GwpPowerUnits + AdpImpCase * GwpCaseUnits + AdpImpMotherB * GwpMotherUnits;


                System.out.println("AFFICHAGE DES DONNEES DANS LA PARTIE GRAPHE------------------------------------------------------------------------");
        System.out.println("Global Warming Potential (" + GwpUnit + ") Total : " + GwpTotal);
        System.out.println("               Manufacturing : " + TotalGwp);
        System.out.println("               Usage : " + Gwpusage);
        System.out.println("               valeur assembly : " + GwpImpAssembly);
        System.out.println("               valeur cpu : " + GwpImpCpu);
        System.out.println("               valeur ram : " + GwpImpRam);
        System.out.println("               valeur ssd : " + GwpImpSSD);
        System.out.println("               valeur hdd : " + GwpImpHDD);
        System.out.println("               valeur power : " + GwpImpPower);
        System.out.println("               valeur case : " + GwpImpCase);
        System.out.println("               valeur mother : " + GwpImpMotherB);
        System.out.println(" ");



        System.out.println("Primary energy (" + PeUnit + ") Total : " + PeTotal);
        System.out.println("               Manufacturing : " + TotalPe);
        System.out.println("               Usage : " + Peusage);
        System.out.println("               valeur assembly : " + PeImpAssembly);
        System.out.println("               valeur cpu : " + PeImpCpu);
        System.out.println("               valeur ram : " + PeImpRam);
        System.out.println("               valeur ssd : " + PeImpSSD);
        System.out.println("               valeur hdd : " + PeImpHDD);
        System.out.println("               valeur power : " + PeImpPower);
        System.out.println("               valeur case : " + PeImpCase);
        System.out.println("               valeur mother : " + PeImpMotherB);
        System.out.println(" ");



        System.out.println("Abiotic Depletion Potential (" + AdpUnit + ") Total : " + AdpTotal);
        System.out.println("               Manufacturing : " + TotalAdp);
        System.out.println("               Usage : " + Adpusage);
        System.out.println("               valeur assembly : " + AdpImpAssembly);
        System.out.println("               valeur cpu : " + AdpImpCpu);
        System.out.println("               valeur ram : " + AdpImpRam);
        System.out.println("               valeur ssd : " + AdpImpSSD);
        System.out.println("               valeur hdd : " + AdpImpHDD);
        System.out.println("               valeur power : " + AdpImpPower);
        System.out.println("               valeur case : " + AdpImpCase);
        System.out.println("               valeur mother : " + AdpImpMotherB);
        System.out.println(" ");

    }



}
