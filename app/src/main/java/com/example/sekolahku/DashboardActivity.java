package com.example.sekolahku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity  {

    private Button LihatDataButton;
    private Button InformasiButton;
    private Button InputDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        LihatDataButton = findViewById(R.id.lihat_data_button);
        InformasiButton = findViewById(R.id.informasi_button);
        InputDataButton = findViewById(R.id.input_data_button);

        LihatDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, LihatDataActivity.class);
                startActivity(intent);
            }
        });

        InformasiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, InformasiActivity.class);
                startActivity(intent);
            }
        });

        InputDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, InputDataActivity.class);
                startActivity(intent);
            }
        });
    }
}
