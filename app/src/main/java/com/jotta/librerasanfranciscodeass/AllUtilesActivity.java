package com.jotta.librerasanfranciscodeass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;

import java.util.Scanner;

public class AllUtilesActivity extends AppCompatActivity {
    ProductRecViewAdapter adapter;
    RecyclerView utilesRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utiles);
        Data.flag = false;
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Útiles</font>"));
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        Data.getJSON("https://libreriacsfa.000webhostapp.com/recuperar_utiles.php");

        thread.start();

    }
    Thread thread = new Thread(){
        public void run(){
            try {
                while(Data.flag == false){
                    Thread.sleep(100);
                    System.out.println("Bandera = " + Data.flag);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Bandera = " + Data.flag);
                        makeRecyclerView();
                    }
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private void makeRecyclerView() {
        adapter = new ProductRecViewAdapter(this);
        utilesRecyclerView = findViewById(R.id.utilesRecyclerView);
        utilesRecyclerView.setAdapter(adapter);
        utilesRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter.setProducts(Data.getData());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
