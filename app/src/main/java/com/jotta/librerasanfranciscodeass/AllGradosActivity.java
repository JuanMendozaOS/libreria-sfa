package com.jotta.librerasanfranciscodeass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class AllGradosActivity extends AppCompatActivity {
    private GradoRecViewAdapter adapter;
    private RecyclerView gradosRecyclerView;
    private ImageView imgBackArrow;
    private String[][] grados;
    private GradoRecViewAdapter.RecyclerViewClickListener listener;
    private TextView txtNoConexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_grados);
        Data.flag = false;
        txtNoConexion = findViewById(R.id.txtNoConexion);


        imgBackArrow = findViewById(R.id.imgBackArrow);
        imgBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //obtener datos y realizar recyclerview 192.168.237.140  192.168.237.140
        Data.getJSON("https://libreriacsfa.000webhostapp.com/recuperar_grados.php");
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
        if(( grados = Data.getData())!=null){
            txtNoConexion.setVisibility(View.INVISIBLE);
            setOnClickListner();
            adapter = new GradoRecViewAdapter(this, listener);
            gradosRecyclerView = findViewById(R.id.gradosRecyclerView);
            gradosRecyclerView.setAdapter(adapter);
            gradosRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            adapter.setGrados(grados);
        }else{
            txtNoConexion.setVisibility(View.VISIBLE);
        }

    }

    private void setOnClickListner() {
        listener = new GradoRecViewAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), AllLibrosActivity.class);
                intent.putExtra("id_grado", grados[position][0]);
                intent.putExtra("nombre_grado", grados[position][1]);
                startActivity(intent);
            }
        };
    }
}