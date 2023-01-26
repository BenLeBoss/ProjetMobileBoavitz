package fr.univpau.projetboavitz;

import android.app.Activity;
import android.util.Log;
import android.widget.*;

public class DataCollected extends Activity {

    void afficher(){
        Log.i("Tag", "Faire fonctionner la fonction");
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
