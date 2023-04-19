package com.jotta.librerasanfranciscodeass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;

public class AllUniformesActivity extends AppCompatActivity {

    ProductRecViewAdapter adapter;
    RecyclerView uniformesRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_uniformes);
        Data.flag = false;
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Uniformes</font>"));
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        Data.getJSON("https://libreriacsfa.000webhostapp.com/recuperar_uniformes.php");
        Thread thread = new Thread(){
            public void run(){
                try {
                    while(Data.flag == false){
                        Thread.sleep(100);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            makeRecyclerView();
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();

    }

    private void makeRecyclerView() {
        adapter = new ProductRecViewAdapter(this);
        uniformesRecyclerView = findViewById(R.id.uniformesRecyclerView);
        uniformesRecyclerView.setAdapter(adapter);
        uniformesRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter.setProducts(Data.getData());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}