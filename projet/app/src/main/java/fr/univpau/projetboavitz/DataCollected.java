package fr.univpau.projetboavitz;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DataCollected extends Activity {

    void afficher(){
        Log.i("Tag", "Faire fonctionner la fonction");
    }

    void JSON_creation(MainActivity context) throws IOException, JSONException {
        JSONObject JsonModel = new JSONObject();
        JSONObject JsonConfig = new JSONObject();
        JSONObject JsonUsage = new JSONObject();

        JSONObject SousJsonCPU = new JSONObject();
        JSONObject SousJsonRAM = new JSONObject();
        JSONArray SousJsonDISK = new JSONArray();

        JSONObject MainJson = new JSONObject();

        JsonModel.put("type", "rack");
        MainJson.put("model", JsonModel);

        SousJsonCPU.put("units", 2);
        SousJsonCPU.put("name", "intel xeon gold 6134");

        SousJsonRAM.put("units", 12);
        SousJsonRAM.put("capacity", 32);

        SousJsonDISK.put(new JSONObject().put("units", 1));
        SousJsonDISK.put(new JSONObject().put("type", "ssd"));
        SousJsonDISK.put(new JSONObject().put("capacity", 400));
        SousJsonDISK.put(new JSONObject().put("density", 50.6));

        JsonConfig.put("cpu", SousJsonCPU);
        JsonConfig.put("ram", SousJsonRAM);
        JsonConfig.put("disk", SousJsonDISK);

        MainJson.put("configuration", JsonConfig);

        JsonUsage.put("years_use_time", 1);
        JsonUsage.put("days_use_time", 1);
        JsonUsage.put("hours_use_time", 1);
        JsonUsage.put("hours_electrical_consumption", 300);
        JsonUsage.put("usage_location", "FRA");

        MainJson.put("usage", JsonUsage);


        String Rendu = MainJson.toString();

        FileOutputStream fos = context.openFileOutput("jsonfile", Context.MODE_PRIVATE);
        fos.write(Rendu.getBytes());
        fos.close();

        Log.d("JSON" , Rendu);
    }

    //SERVER CONFIG
    //CPU
    void get_values_from_ServerConfig_CPU(){
        EditText EditText_CpuQuantity = (EditText)findViewById(R.id.editTextcpuquantity);
        double Current_CpuQuantity = Double.parseDouble(EditText_CpuQuantity.getText().toString());

        EditText EditText_CpuCore = (EditText)findViewById(R.id.editTextcpucore);
        double Current_CpuCore = Double.parseDouble(EditText_CpuCore.getText().toString());

        EditText EditText_CpuTdp = (EditText)findViewById(R.id.editTextcputdp);
        double Current_CpuTdp = Double.parseDouble(EditText_CpuTdp.getText().toString());

        Spinner Spinner_CpuArchitecture = (Spinner)findViewById(R.id.spinnercpuarchitecture);
        String Current_Value_Spinner_CpuArchitecture = Spinner_CpuArchitecture.getSelectedItem().toString();
    }

    //RAM
    void get_values_from_ServerConfig_RAM(){
        EditText EditText_RamQuantity = (EditText)findViewById(R.id.editTextramquantity);
        double Current_RamQuantity = Double.parseDouble(EditText_RamQuantity.getText().toString());

        EditText EditText_RamCapacity = (EditText)findViewById(R.id.editTextramcapacity);
        double Current_RamCapacity = Double.parseDouble(EditText_RamCapacity.getText().toString());

        Spinner Spinner_RamManufacturer = (Spinner)findViewById(R.id.spinnerrammanufacturer);
        String Current_Value_Spinner_RamManufacturer = Spinner_RamManufacturer.getSelectedItem().toString();
    }

    //SSD
    void get_values_from_ServerConfig_SSD(){
        EditText EditText_SsdQuantity = (EditText)findViewById(R.id.editTextssdquantity);
        double Current_SsdQuantity = Double.parseDouble(EditText_SsdQuantity.getText().toString());

        EditText EditText_SsdCapacity = (EditText)findViewById(R.id.editTextssdcapacity);
        double Current_SsdCapacity = Double.parseDouble(EditText_SsdCapacity.getText().toString());

        Spinner Spinner_SsdManufacturer = (Spinner)findViewById(R.id.spinnerssdmanufacturer);
        String Current_Value_Spinner_SsdManufacturer = Spinner_SsdManufacturer.getSelectedItem().toString();
    }

    //Others
    void get_values_from_ServerConfig_Others(){
        EditText EditText_OthersHdd = (EditText)findViewById(R.id.editTextothershdd);
        double Current_OthersHdd = Double.parseDouble(EditText_OthersHdd.getText().toString());

        EditText EditText_OthersServer = (EditText)findViewById(R.id.editTextothersserver);
        double Current_OthersServer = Double.parseDouble(EditText_OthersServer.getText().toString());

        EditText EditText_OthersPsu = (EditText)findViewById(R.id.editTextotherspsu);
        double Current_OthersPsu = Double.parseDouble(EditText_OthersPsu.getText().toString());
    }

    //USAGE
    void get_values_from_Usage(){
        Spinner Spinner_UsageLocalisation = (Spinner)findViewById(R.id.spinnerusagelocalisation);
        String Current_Value_Spinner_UsageLocalisation = Spinner_UsageLocalisation.getSelectedItem().toString();

        EditText EditText_UsageLifespan = (EditText)findViewById(R.id.editTextusagelifespan);
        double Current_UsageLifespan = Double.parseDouble(EditText_UsageLifespan.getText().toString());

        Spinner Spinner_UsageMethod = (Spinner)findViewById(R.id.spinnerusagemethod);
        String Current_Value_Spinner_UsageMethod = Spinner_UsageMethod.getSelectedItem().toString();

        EditText EditText_UsageAverage = (EditText)findViewById(R.id.editTextusageaverage);
        double Current_UsageAverage= Double.parseDouble(EditText_UsageAverage.getText().toString());

    }


}
