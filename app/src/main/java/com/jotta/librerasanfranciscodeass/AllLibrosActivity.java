package com.jotta.librerasanfranciscodeass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class AllLibrosActivity extends AppCompatActivity {
    private int id_grado;
    private String nombre_grado;
    private ProductRecViewAdapter adapter;
    private RecyclerView librosRecyclerView;
private TextView txtNoConexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_libros);
        Data.flag = false;
        txtNoConexion = findViewById(R.id.txtNoConexion);
        Bundle extras = getIntent().getExtras();

        //Si se pasaron extras en el intent, almacenar esos datos en la variable
        if(extras != null){
            id_grado = Integer.parseInt(extras.getString("id_grado"));
            nombre_grado = extras.getString("nombre_grado");

        }

        //Colocar título en blanco y botón de volver
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Libros de " + nombre_grado + "</font>"));
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button



        String urlWebService = "https://libreriacsfa.000webhostapp.com/recuperar_libros" + id_grado + ".php";
        Data.getJSON(urlWebService);

        //delay para hacer el recyclerview
        Thread thread = new Thread(){
            public void run(){
                try {
                    while(Data.flag == false){
                        Thread.sleep(100);
                        System.out.println("sleeping");
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("casi haciendo el recview");
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
        librosRecyclerView = findViewById(R.id.librosRecyclerView);
        librosRecyclerView.setAdapter(adapter);
        librosRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        if (Data.getData().length!=0){
            System.out.println("no es diferente a 0");
            txtNoConexion.setVisibility(View.INVISIBLE);
            adapter.setProducts(Data.getData());
        }else{
            System.out.println("el papu");
            txtNoConexion.setVisibility(View.VISIBLE);
            txtNoConexion.setText("No se encontraron productos disponibles");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}