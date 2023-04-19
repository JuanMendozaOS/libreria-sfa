package com.jotta.librerasanfranciscodeass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class Articulos extends AppCompatActivity implements View.OnClickListener {
    ImageView imgBotonLibros, imgBotonUniformes, imgBotonUtiles, imgBackArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulos);
        changeStatusBarColor();
        initViews();

    }

    private void changeStatusBarColor() {
        Window window = this.getWindow();

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        window.setStatusBarColor(this.getResources().getColor(R.color.celeste_claro));
    }

    private void initViews() {
        imgBackArrow = findViewById(R.id.imgBackArrow);
        imgBackArrow.setOnClickListener(this);
        imgBotonLibros = findViewById(R.id.imgBotonLibros);
        imgBotonLibros.setOnClickListener(this);
        imgBotonUniformes = findViewById(R.id.imgBotonUniformes);
        imgBotonUniformes.setOnClickListener(this);
        imgBotonUtiles = findViewById(R.id.imgBotonUtiles);
        imgBotonUtiles.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBackArrow:
                finish();
                break;
            case R.id.imgBotonLibros:
                startActivity(new Intent(Articulos.this, AllGradosActivity.class));
                //Toast.makeText(this, "*Inserte RecyclerView de libros*", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgBotonUniformes:
                startActivity(new Intent(Articulos.this, AllUniformesActivity.class));

                //Toast.makeText(this, "*Inserte RecyclerView de uniformes*", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgBotonUtiles:
                startActivity(new Intent(Articulos.this, AllUtilesActivity.class));
                //Toast.makeText(this, "*Inserte RecyclerView de utiles*", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}