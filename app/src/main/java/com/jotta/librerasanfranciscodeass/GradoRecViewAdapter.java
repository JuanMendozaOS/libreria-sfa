package com.jotta.librerasanfranciscodeass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class GradoRecViewAdapter extends RecyclerView.Adapter<GradoRecViewAdapter.ViewHolder> {
    private String[][] grados;
    private Context context;
    private String nombre_grado;
    private RecyclerViewClickListener listener;

    public GradoRecViewAdapter(Context context, RecyclerViewClickListener listener) {
        this.context = context;
        this.listener = listener;
        notifyDataSetChanged();
    }

    public void setGrados(String[][] grados){
        this.grados = grados;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_grado, parent, false);
        return new GradoRecViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtNombreGrado.setText(grados[position][1]);


    }

    @Override
    public int getItemCount() {
        return grados.length;
    }

    //interface para el clicklistener del recyclerview
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtNombreGrado;
        private final Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreGrado = itemView.findViewById(R.id.txtNombreGrado);
            context = itemView.getContext();
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

}
