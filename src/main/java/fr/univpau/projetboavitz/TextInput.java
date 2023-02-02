package fr.univpau.projetboavitz;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;



public class TextInput {
    public MainActivity MainActivity;

    public void setClearOnClickListeners() {
        setClearInputOnClick(R.id.editTextcpuquantity, R.string.Cpu_Quantity);
        setClearInputOnClick(R.id.editTextcpucore, R.string.Cpu_CoreUnits);
        setClearInputOnClick(R.id.editTextcputdp, R.string.Cpu_TDP);

        setClearInputOnClick(R.id.editTextramcapacity, R.string.RAM_Capacity);
        setClearInputOnClick(R.id.editTextssdquantity, R.string.SSD_Quantity);

        setClearInputOnClick(R.id.editTextssdcapacity, R.string.SSD_Capacity);
        setClearInputOnClick(R.id.editTextothershdd, R.string.Other_Quantity);

        setClearInputOnClick(R.id.editTextotherspsu, R.string.PSU_Quantity);
        setClearInputOnClick(R.id.editTextusagelifespan, R.string.Text_Lifespan);

        setClearInputOnClick(R.id.editTextusageaverage, R.string.Avg_consumption);
    }

    private void setClearInputOnClick(int inputId, int defaultValue) {
        TextInputEditText inputView = MainActivity.findViewById(inputId);
        inputView.setOnFocusChangeListener((view, b) -> {
            if (b && Objects.requireNonNull(inputView.getText()).length() > 0)
                inputView.getText().clear();
            else if (Objects.requireNonNull(inputView.getText()).length() == 0) {
                inputView.setText(defaultValue);
            }
        });
    }

}
