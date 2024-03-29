package com.example.freestylemeeting.AdaptersList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freestylemeeting.NavegationDrawerActivity;
import com.example.freestylemeeting.PistaActivity;
import com.example.freestylemeeting.R;

import java.util.List;

import Modelo.Estacion;
import Modelo.Pista;

public class PistaAdapter extends RecyclerView.Adapter<PistaAdapter.ViewHolder>  {

    public List<Pista> pistasList;

    public Context context;

    public PistaAdapter(Context context, List<Pista> pistasList) {
        this.context = context;
        this.pistasList = pistasList;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pistas_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pista pista = pistasList.get(position);
        holder.nametext.setText(pista.getNombre());

        switch (pista.getDificultad()){
            case "Verde":
                holder.dificultadtext.setTextColor(Color.rgb(76,175,80));
                break;
            case "Azul":
                holder.dificultadtext.setTextColor(Color.rgb(23,137,255));
                break;
            case "Rojo":
                holder.dificultadtext.setTextColor(Color.rgb(223,0,0));
                break;
            case "Negro":
                holder.dificultadtext.setTextColor(Color.rgb(0,0,0));
                break;
        }
        holder.dificultadtext.setText(pista.getDificultad());

        if(pista.getDisponible()){
            holder.disponibleText.setText("Disponible");
            holder.disponibleText.setTextColor(Color.rgb(76,175,80));
        } else {
            holder.disponibleText.setText("Cerrada");
            holder.disponibleText.setTextColor(Color.RED);
        }

        holder.mView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PistaActivity.class);
                intent.putExtra("pista", pista);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pistasList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public TextView nametext;
        public TextView dificultadtext;
        public TextView disponibleText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            nametext = (TextView) mView.findViewById(R.id.nombrePistaList);
            dificultadtext = (TextView) mView.findViewById(R.id.dificultadPistaList);
            disponibleText = (TextView) mView.findViewById(R.id.disponiblePistaList);
        }
    }
}
