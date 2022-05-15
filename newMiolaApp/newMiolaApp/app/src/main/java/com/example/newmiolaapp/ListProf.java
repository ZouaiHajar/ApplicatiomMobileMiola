package com.example.newmiolaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.example.newmiolaapp.Modal.prof;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListProf extends AppCompatActivity {

    private FirebaseFirestore db;

    RecyclerView mrecyclerview;
    StaggeredGridLayoutManager st;

    private ArrayList<prof>  profArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prof);

        mrecyclerview = findViewById(R.id.MyRecec);

        db = FirebaseFirestore.getInstance();

        profArrayList = new ArrayList<>();

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
                               if(document.getString("statut").equals("Professeur")){
                                   profArrayList.add(new prof(document.getString("nom"),document.getString("prenom"),document.getString("email") ));
                               }
                            }
                        }


                        profAdapter adapter = new profAdapter(profArrayList);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        mrecyclerview.setLayoutManager(layoutManager);
                        mrecyclerview.setItemAnimator(new DefaultItemAnimator());
                        mrecyclerview.setAdapter(adapter);

                    }
                });






    }


}