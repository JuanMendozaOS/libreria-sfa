package com.jotta.librerasanfranciscodeass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductRecViewAdapter extends RecyclerView.Adapter<ProductRecViewAdapter.ViewHolder> {

    private String[][] products;
    private Context context;

    public ProductRecViewAdapter(Context context) {
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_producto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecViewAdapter.ViewHolder holder, int position) {
            holder.txtProductName.setText(products[position][0]);
            holder.txtProductPrice.setText(products[position][3] + " Gs.");
            holder.txtProductStock.setText("Stock: " + products[position][2]);
            Glide.with(context)
                    .asBitmap()
                    .load(products[position][4])
                    .into(holder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return products.length;
    }

    public void setProducts(String[][] products){
        this.products = products;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgProduct;
        private TextView txtProductName, txtProductPrice, txtProductStock;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtProductPrice = itemView.findViewById(R.id.txtProductPrice);
            txtProductStock = itemView.findViewById(R.id.txtProductStock);
        }
    }
}
