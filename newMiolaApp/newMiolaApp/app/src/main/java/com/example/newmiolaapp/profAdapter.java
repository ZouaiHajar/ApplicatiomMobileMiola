package com.example.newmiolaapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newmiolaapp.Modal.prof;

import java.util.ArrayList;

public class profAdapter extends RecyclerView.Adapter<profAdapter.MyViewHolder>{

    private ArrayList<prof> profList;

    public profAdapter(ArrayList<prof> profList){
        this.profList = profList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nom;
        private TextView prenom;
        private TextView Email;

        public MyViewHolder(final View view){
            super(view);
            nom = view.findViewById(R.id.N);
            prenom = view.findViewById(R.id.P);
            Email = view.findViewById(R.id.EM);

        }
    }

    @NonNull
    @Override
    public profAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list , parent , false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull profAdapter.MyViewHolder holder, int position) {
        String n = profList.get(position).getNom();
        String P = profList.get(position).getPrenom();
        String E = profList.get(position).getEmail();

        holder.nom.setText(n);
        holder.prenom.setText(P);
        holder.Email.setText(E);
    }

    @Override
    public int getItemCount() {
        return profList.size();
    }
}
