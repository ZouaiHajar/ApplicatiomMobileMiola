package com.example.newmiolaapp.Modal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newmiolaapp.R;
import com.example.newmiolaapp.profAdapter;

import java.util.ArrayList;

public class EtudiantAdapter extends RecyclerView.Adapter<EtudiantAdapter.MyViewHolder>{


    private ArrayList<Etudiant> etudList;

    public EtudiantAdapter(ArrayList<Etudiant> etudList){
        this.etudList = etudList;
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
    public EtudiantAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list , parent , false);
        return new EtudiantAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EtudiantAdapter.MyViewHolder holder, int position) {

        String n = etudList.get(position).getNom();
        String P = etudList.get(position).getPrenom();
        String E = etudList.get(position).getEmail();

        holder.nom.setText(n);
        holder.prenom.setText(P);
        holder.Email.setText(E);

    }

    @Override
    public int getItemCount() {
        return etudList.size();
    }
}
