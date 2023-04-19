package com.jotta.librerasanfranciscodeass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnProductos, btnSobreNosotros, btnSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

    }

    private void initializeViews() {
        btnProductos = findViewById(R.id.btnProductos);
        btnProductos.setOnClickListener(this);
        btnSobreNosotros = findViewById(R.id.btnSobreNosotros);
        btnSobreNosotros.setOnClickListener(this);
        btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnProductos:
                startActivity(new Intent(MainActivity.this, Articulos.class));
                break;
            case R.id.btnSobreNosotros:
                startActivity(new Intent(MainActivity.this, SobreNosotrosActivity.class));
                //Toast.makeText(this, "*Inserte misión y visión*", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnSalir:
                finish();
                System.exit(0);
                break;
        }
    }
}