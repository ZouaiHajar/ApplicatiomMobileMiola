package com.example.newmiolaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.example.newmiolaapp.Modal.Etudiant;
import com.example.newmiolaapp.Modal.EtudiantAdapter;
import com.example.newmiolaapp.Modal.prof;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListEtudiant extends AppCompatActivity {


    private FirebaseFirestore db;

    RecyclerView mrecyclerview;
    StaggeredGridLayoutManager st;

    private ArrayList<Etudiant> ListEtud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etudiant);

        mrecyclerview = findViewById(R.id.MyRecec);

        db = FirebaseFirestore.getInstance();

        ListEtud = new ArrayList<>();

        setProfInfo();



    }


    private void setProfInfo() {


        db.collection("users").orderBy("nom")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.getString("statut").equals("Etudiant")){
                                    ListEtud.add(new Etudiant(document.getString("nom"),document.getString("prenom"),document.getString("email") ));
                                }
                            }
                        }


                        EtudiantAdapter adapter = new EtudiantAdapter(ListEtud);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        mrecyclerview.setLayoutManager(layoutManager);
                        mrecyclerview.setItemAnimator(new DefaultItemAnimator());
                        mrecyclerview.setAdapter(adapter);

                    }
                });






    }




}
