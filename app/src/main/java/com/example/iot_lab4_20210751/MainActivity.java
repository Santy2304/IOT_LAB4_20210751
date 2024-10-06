package com.example.iot_lab4_20210751;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button ingresoButton = findViewById(R.id.ingresarButton);
        ingresoButton.setEnabled(false);

        if(internetConnection()){
            ingresoButton.setEnabled(true);
        }else{
            mostrarDiolog();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void irApp(View view){
        Intent intent = new Intent(MainActivity.this, AppActivity.class);
        startActivity(intent);
    }
    public void mostrarDiolog(){
        new MaterialAlertDialogBuilder(this)
                .setTitle("¡Error de conexión!")
                .setMessage("Dirígete a ajustes para establecer conexión.")
                .setPositiveButton("Ir a configuración", (dialog, which) -> {
                    Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                    startActivity(intent);
                })
                .show();
    }

    //Código tomado de material de clase para probar conexión a internet
    public boolean internetConnection(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        return tieneInternet;
    }
}