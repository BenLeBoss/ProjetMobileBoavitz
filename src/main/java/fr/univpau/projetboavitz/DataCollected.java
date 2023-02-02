package fr.univpau.projetboavitz;

import android.app.Activity;
import android.content.Context;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class DataCollected extends Activity {

    public Activity activity;
    public DataCollected(Activity _activity){
        this.activity = _activity;
    }

    void afficher(){
        Log.i("Tag", "Faire fonctionner la fonction");
    }

    String JSON_creation(MainActivity context, String[] Arr_conf_CPU, String[] Arr_conf_RAM, String[] Arr_conf_SSD, String[] Arr_conf_Others, String[] Arr_Usage) throws IOException, JSONException {
        JSONObject JsonModel = new JSONObject();
        JSONObject JsonConfig = new JSONObject();
        JSONObject JsonUsage = new JSONObject();


        JSONObject SousJsonCPU = new JSONObject();
        JSONObject SousJsonRAM = new JSONObject();
        JSONArray SousJsonArrayRAM = new JSONArray();
        JSONArray SousJsonDISK = new JSONArray();
        JSONObject SousJsonPowerSupply = new JSONObject();
        JSONObject SousJsonArray1Disk = new JSONObject();
        JSONObject SousJsonArray2Disk = new JSONObject();

        JSONObject MainJson = new JSONObject();

        JsonModel.put("type", Arr_conf_Others[1]);
        MainJson.put("model", JsonModel);

        SousJsonCPU.put("core_units", Integer.parseInt(Arr_conf_CPU[1]));
        SousJsonCPU.put("units", Integer.parseInt(Arr_conf_CPU[0]));
        SousJsonCPU.put("family", Arr_conf_CPU[3]);

        SousJsonRAM.put("units", Integer.parseInt(Arr_conf_RAM[0]));
        SousJsonRAM.put("capacity", Integer.parseInt(Arr_conf_RAM[1]));
        SousJsonRAM.put("manufacturer", Arr_conf_RAM[2]);
        SousJsonArrayRAM.put(SousJsonRAM);


        SousJsonArray1Disk.put("units", Integer.parseInt(Arr_conf_SSD[0]));
        SousJsonArray1Disk.put("type", "SSD");
        SousJsonArray1Disk.put("capacity", Integer.parseInt(Arr_conf_SSD[1]));
        SousJsonArray1Disk.put("manufacturer", Arr_conf_SSD[2]);
        SousJsonDISK.put(SousJsonArray1Disk);

        SousJsonArray2Disk.put("units", Integer.parseInt(Arr_conf_Others[0]));
        SousJsonArray2Disk.put("type", "HDD");
        SousJsonDISK.put(SousJsonArray2Disk);

        SousJsonPowerSupply.put("units", Arr_conf_Others[2]);
        SousJsonPowerSupply.put("unit_weight", 4);

        JsonConfig.put("cpu", SousJsonCPU);
        JsonConfig.put("ram", SousJsonArrayRAM);
        JsonConfig.put("disk", SousJsonDISK);
        JsonConfig.put("powersupply", SousJsonPowerSupply);

        MainJson.put("configuration", JsonConfig);

        //a compléter pour les pays avec les initiales (origine)
        JsonUsage.put("years_use_time", 1);
        JsonUsage.put("days_use_time", 1);
        JsonUsage.put("hours_use_time", 1);
        JsonUsage.put("hours_electrical_consumption", Integer.parseInt(Arr_Usage[3]));
        JsonUsage.put("usage_location", "FRA");

        MainJson.put("usage", JsonUsage);


        String Rendu = MainJson.toString();

        FileOutputStream fos = context.openFileOutput("jsonfile", Context.MODE_PRIVATE);
        fos.write(Rendu.getBytes());
        fos.close();

        Log.d("JSON" , Rendu);

        return Rendu;
    }


    //SERVER CONFIG
    //CPU
    public String[] get_values_from_ServerConfig_CPU(){
        String[] Array_values = new String[4];

        EditText EditText_CpuQuantity = (EditText) this.activity.findViewById(R.id.editTextcpuquantity);
        if (!EditText_CpuQuantity.getText().toString().isEmpty()){
            int Current_CpuQuantity = Integer.parseInt(EditText_CpuQuantity.getText().toString());
            Array_values[0] = String.valueOf(Current_CpuQuantity);
        }


        EditText EditText_CpuCore = (EditText) this.activity.findViewById(R.id.editTextcpucore);
        if (!EditText_CpuCore.getText().toString().isEmpty()){
            int Current_CpuCore = Integer.parseInt(EditText_CpuCore.getText().toString());
            Array_values[1] = String.valueOf(Current_CpuCore);
        }

        EditText EditText_CpuTdp = (EditText) this.activity.findViewById(R.id.editTextcputdp);
        if (!EditText_CpuTdp.getText().toString().isEmpty()){
            int Current_CpuTdp = Integer.parseInt(EditText_CpuTdp.getText().toString());
            Array_values[2] = String.valueOf(Current_CpuTdp);
        }

        Spinner Spinner_CpuArchitecture = (Spinner) this.activity.findViewById(R.id.spinnercpuarchitecture);
        String Current_Value_Spinner_CpuArchitecture = Spinner_CpuArchitecture.getSelectedItem().toString();
        Array_values[3] = Current_Value_Spinner_CpuArchitecture;

        return Array_values;
    }

    //RAM
    public String[] get_values_from_ServerConfig_RAM(){
        String[] Array_values = new String[3];

        EditText EditText_RamQuantity = (EditText) this.activity.findViewById(R.id.editTextramquantity);
        if (!EditText_RamQuantity.getText().toString().isEmpty()) {
            int Current_RamQuantity = Integer.parseInt(EditText_RamQuantity.getText().toString());
            Array_values[0]=String.valueOf(Current_RamQuantity);
        }

        EditText EditText_RamCapacity = (EditText) this.activity.findViewById(R.id.editTextramcapacity);
        if (!EditText_RamCapacity.getText().toString().isEmpty()) {
            int Current_RamCapacity = Integer.parseInt(EditText_RamCapacity.getText().toString());
            Array_values[1]=String.valueOf(Current_RamCapacity);
        }

        Spinner Spinner_RamManufacturer = (Spinner) this.activity.findViewById(R.id.spinnerrammanufacturer);
        String Current_Value_Spinner_RamManufacturer = Spinner_RamManufacturer.getSelectedItem().toString();
        Array_values[2]= Current_Value_Spinner_RamManufacturer;

        return Array_values;
    }

    //SSD
    public String[] get_values_from_ServerConfig_SSD(){
        String[] Array_values = new String[3];

        EditText EditText_SsdQuantity = (EditText) this.activity.findViewById(R.id.editTextssdquantity);
        if (!EditText_SsdQuantity.getText().toString().isEmpty()) {
            int Current_SsdQuantity = Integer.parseInt(EditText_SsdQuantity.getText().toString());
            Array_values[0]=String.valueOf(Current_SsdQuantity);
        }

        EditText EditText_SsdCapacity = (EditText) this.activity.findViewById(R.id.editTextssdcapacity);
        if (!EditText_SsdCapacity.getText().toString().isEmpty()) {
            int Current_SsdCapacity = Integer.parseInt(EditText_SsdCapacity.getText().toString());
            Array_values[1]=String.valueOf(Current_SsdCapacity);
        }

        Spinner Spinner_SsdManufacturer = (Spinner) this.activity.findViewById(R.id.spinnerssdmanufacturer);
        String Current_Value_Spinner_SsdManufacturer = Spinner_SsdManufacturer.getSelectedItem().toString();
        Array_values[2]= Current_Value_Spinner_SsdManufacturer;

        return Array_values;
    }

    //Others
    public String[] get_values_from_ServerConfig_Others(){
        String[] Array_values = new String[3];

        EditText EditText_OthersHdd = (EditText) this.activity.findViewById(R.id.editTextothershdd);
        if (!EditText_OthersHdd.getText().toString().isEmpty()) {
            int Current_OthersHdd = Integer.parseInt(EditText_OthersHdd.getText().toString());
            Array_values[0]=String.valueOf(Current_OthersHdd);
        }

        Spinner Spinner_OthersServer = (Spinner) this.activity.findViewById(R.id.spinnersothersserver);
        String Current_Value_Spinner_OthersServer = Spinner_OthersServer.getSelectedItem().toString();
        Array_values[1]= Current_Value_Spinner_OthersServer;

        EditText EditText_OthersPsu = (EditText) this.activity.findViewById(R.id.editTextotherspsu);
        if (!EditText_OthersPsu.getText().toString().isEmpty()) {
            int Current_OthersPsu = Integer.parseInt(EditText_OthersPsu.getText().toString());
            Array_values[2]=String.valueOf(Current_OthersPsu);
        }

        return Array_values;
    }

    //USAGE
    public String[] get_values_from_Usage(){
        String[] Array_values = new String[4];

        Spinner Spinner_UsageLocalisation = (Spinner) this.activity.findViewById(R.id.spinnerusagelocalisation);
        String Current_Value_Spinner_UsageLocalisation = Spinner_UsageLocalisation.getSelectedItem().toString();
        Array_values[0]= Current_Value_Spinner_UsageLocalisation;

        EditText EditText_UsageLifespan = (EditText) this.activity.findViewById(R.id.editTextusagelifespan);
        if (!EditText_UsageLifespan.getText().toString().isEmpty()) {
            int Current_UsageLifespan = Integer.parseInt(EditText_UsageLifespan.getText().toString());
            Array_values[1] = String.valueOf(Current_UsageLifespan);
        }

        Spinner Spinner_UsageMethod = (Spinner) this.activity.findViewById(R.id.spinnerusagemethod);
        String Current_Value_Spinner_UsageMethod = Spinner_UsageMethod.getSelectedItem().toString();
        Array_values[2]= Current_Value_Spinner_UsageMethod;

        EditText EditText_UsageAverage = (EditText) this.activity.findViewById(R.id.editTextusageaverage);
        if (!EditText_UsageAverage.getText().toString().isEmpty()) {
            int Current_UsageAverage = Integer.parseInt(EditText_UsageAverage.getText().toString());
            Array_values[3] = String.valueOf(Current_UsageAverage);
        }

        return Array_values;

    }


}
