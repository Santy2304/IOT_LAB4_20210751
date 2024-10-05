package com.example.iot_lab4_20210751;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

public class AppActivity extends AppCompatActivity {

    Button ligasButton;
    Button posicionesButton;
    Button resultadosButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app);

        ligasButton = findViewById(R.id.ligasButton);
        posicionesButton = findViewById(R.id.posicionesButton);
        resultadosButton = findViewById(R.id.resultadosButton);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();


            ligasButton.setOnClickListener(view -> {
                navController.navigate(R.id.ligasFragment);
            });

            posicionesButton.setOnClickListener(view -> {
                navController.navigate(R.id.posicionesFragment);

            });

            resultadosButton.setOnClickListener(view -> {
                navController.navigate(R.id.resultadosFragment);

            });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}