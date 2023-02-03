package fr.univpau.projetboavitz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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



        //gwp---------------------------------------------
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


        TextView TextViewGWP = (TextView) findViewById(R.id.textViewGwp);
        TextViewGWP.setText("Total : " + TotalGwp);

        PieChart chartGwp = findViewById(R.id.graphe_global);
        chartGwp.setDrawHoleEnabled(true);
        chartGwp.setEntryLabelTextSize(0);
        chartGwp.setEntryLabelColor(Color.BLACK);
        chartGwp.getDescription().setEnabled(false);

        Legend lGwp = chartGwp.getLegend();
        lGwp.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        lGwp.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        lGwp.setOrientation(Legend.LegendOrientation.VERTICAL);
        lGwp.setDrawInside(false);
        lGwp.setEnabled(true);
        ArrayList<Integer> colorsGwp = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colorsGwp.add(color);
        }
        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colorsGwp.add(color);
        }
        ArrayList CompoGwp = new ArrayList<>();

        CompoGwp.add(new PieEntry((float) Gwpusage, "Usage"));
        CompoGwp.add(new PieEntry((float) GwpImpAssembly, "Assembly"));
        CompoGwp.add(new PieEntry((float) GwpImpCpu,"Cpu"));
        CompoGwp.add(new PieEntry((float) GwpImpRam, "Ram"));
        CompoGwp.add(new PieEntry((float) GwpImpSSD, "Ssd"));
        CompoGwp.add(new PieEntry((float) GwpImpHDD, "Hdd"));
        CompoGwp.add(new PieEntry((float) GwpImpPower, "Power"));
        CompoGwp.add(new PieEntry((float) GwpImpCase, "Case"));
        CompoGwp.add(new PieEntry((float) GwpImpMotherB, "Mother"));


        PieDataSet PieDataSetGwp = new PieDataSet(CompoGwp, "");
        PieDataSetGwp.setColors(colorsGwp);
        chartGwp.animateY(5000);
        PieData dataGwp = new PieData(PieDataSetGwp);
        dataGwp.setValueTextSize(12f);
        dataGwp.setValueTextColor(Color.BLACK);
        dataGwp.setDrawValues(true);
        dataGwp.setValueFormatter(new PercentFormatter(chartGwp));

        chartGwp.setData(dataGwp);


        //pe------------------------------------------------------------------------------------
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

        TextView TextViewPE = (TextView) findViewById(R.id.textViewPe);
        TextViewPE.setText("Total : " + TotalPe);

        PieChart chartPe = findViewById(R.id.graphe_primary);
        chartPe.setDrawHoleEnabled(true);
        chartPe.setEntryLabelTextSize(0);
        chartPe.setEntryLabelColor(Color.BLACK);
        chartPe.getDescription().setEnabled(false);

        Legend lPe = chartPe.getLegend();
        lPe.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        lPe.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        lPe.setOrientation(Legend.LegendOrientation.VERTICAL);
        lPe.setDrawInside(false);
        lPe.setEnabled(true);
        ArrayList<Integer> colorsPe = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colorsPe.add(color);
        }
        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colorsPe.add(color);
        }
        ArrayList CompoPe = new ArrayList<>();

        CompoPe.add(new PieEntry((float) Peusage, "Usage"));
        CompoPe.add(new PieEntry((float) PeImpAssembly, "Assembly"));
        CompoPe.add(new PieEntry((float) PeImpCpu,"Cpu"));
        CompoPe.add(new PieEntry((float) PeImpRam, "Ram"));
        CompoPe.add(new PieEntry((float) PeImpSSD, "Ssd"));
        CompoPe.add(new PieEntry((float) PeImpHDD, "Hdd"));
        CompoPe.add(new PieEntry((float) PeImpPower, "Power"));
        CompoPe.add(new PieEntry((float) PeImpCase, "Case"));
        CompoPe.add(new PieEntry((float) PeImpMotherB, "Mother"));


        PieDataSet PieDataSetPe = new PieDataSet(CompoPe, "");
        PieDataSetPe.setColors(colorsPe);
        chartPe.animateY(5000);
        PieData dataPe = new PieData(PieDataSetPe);
        dataPe.setValueTextSize(12f);
        dataPe.setValueTextColor(Color.BLACK);
        dataPe.setDrawValues(true);
        dataPe.setValueFormatter(new PercentFormatter(chartPe));

        chartPe.setData(dataPe);

        //adp --------------------------------------------------------------------------------------------------------------------
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

        TextView TextViewADP = (TextView) findViewById(R.id.textViewAdp);
        TextViewADP.setText("Total : " + TotalAdp);

        PieChart chartAdp = findViewById(R.id.graphe_abiotic);
        chartAdp.setDrawHoleEnabled(true);
        chartAdp.setEntryLabelTextSize(0);
        chartAdp.setEntryLabelColor(Color.BLACK);
        chartAdp.getDescription().setEnabled(false);

        Legend lAdp = chartAdp.getLegend();
        lAdp.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        lAdp.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        lAdp.setOrientation(Legend.LegendOrientation.VERTICAL);
        lAdp.setDrawInside(false);
        lAdp.setEnabled(true);
        ArrayList<Integer> colorsAdp = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colorsAdp.add(color);
        }
        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colorsAdp.add(color);
        }

        ArrayList CompoAdp = new ArrayList<>();

        CompoAdp.add(new PieEntry((float) Adpusage, "Usage"));
        CompoAdp.add(new PieEntry((float) AdpImpAssembly, "Assembly"));
        CompoAdp.add(new PieEntry((float) AdpImpCpu,"Cpu"));
        CompoAdp.add(new PieEntry((float) AdpImpRam, "Ram"));
        CompoAdp.add(new PieEntry((float) AdpImpSSD, "Ssd"));
        CompoAdp.add(new PieEntry((float) AdpImpHDD, "Hdd"));
        CompoAdp.add(new PieEntry((float) AdpImpPower, "Power"));
        CompoAdp.add(new PieEntry((float) AdpImpCase, "Case"));
        CompoAdp.add(new PieEntry((float) AdpImpMotherB, "Mother"));


        PieDataSet PieDataSetAdp = new PieDataSet(CompoAdp, "");
        PieDataSetAdp.setColors(colorsAdp);
        chartAdp.animateY(5000);
        PieData dataAdp = new PieData(PieDataSetAdp);

        dataAdp.setValueTextSize(12f);
        dataAdp.setValueTextColor(Color.BLACK);
        dataAdp.setDrawValues(true);

        chartAdp.setData(dataAdp);


    }



}
