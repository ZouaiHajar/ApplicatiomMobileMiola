package com.example.newmiolaapp.Activity;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newmiolaapp.Modal.Emploi;
import com.example.newmiolaapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class DownloadTimetable extends AppCompatActivity {
    private static final String TAG = "Main3Activity" ;

    Spinner mspinner1 ;
    Spinner mspinner2;

    Spinner mspinner4 ;
    TextView text ;
    Button down ;

    public String Path ;
    public Emploi emploi;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference ;
    StorageReference ref ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_timetable);

        System.out.println("__________________DownloadTimetable___________________________");

        mspinner1 = findViewById(R.id.spinner1);
        mspinner2= findViewById(R.id.spinner2);
        mspinner4= findViewById(R.id.spinner4);

        emploi = new Emploi();

        down = findViewById(R.id.down);

        mspinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String an  =  parent.getItemAtPosition(position).toString();
                if(an.equals("Première année")){
                    emploi.setAnnee("A1");
                }else if(an.equals("Deuxième année")){
                    emploi.setAnnee("A1");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mspinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sem  =  parent.getItemAtPosition(position).toString();

                if(sem.equals("Premier semestre")){
                    emploi.setSemestre("S1");
                }else if(sem.equals("Deuxième semestre")){
                    emploi.setSemestre("S2");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

                emploi.setPeriode("P1");

        mspinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                emploi.setSemaine(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Path = generateFileName(emploi);
                download();
            }
        });

    }


    public String generateFileName(Emploi emp){
        String name="";

        name+=emp.getAnnee()+emp.getSemestre()+emp.getPeriode()+"Semaine"+emp.getSemaine();
        System.out.println("*************************** file name "+name);
        return name;
    }


    public void  download(){
        storageReference = firebaseStorage.getInstance().getReference();
        //ref = storageReference.child("image.PNG");
        //ref = storageReference.child(Path +".PNG");
        ref = storageReference.child("/Emplois/" + Path );

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                Log.d(TAG, "onSuccess: ");
                Toast.makeText(DownloadTimetable.this,"L'emplois du temps est en cours de telechargement",Toast.LENGTH_SHORT).show();
                //Downoald manager
                DownloadFile(DownloadTimetable.this,"/Emplois/"+Path,".pdf", DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG,"Oups !) " + e.getMessage());
                //message d'erreur le document n'existe pas !
                Toast.makeText(DownloadTimetable.this,"L'emplois que vous cherchez n'existe pas !",Toast.LENGTH_SHORT).show();

            }
        });

    }

    //set the android manager
    public void DownloadFile(Context context , String fileName , String fileExtention , String destinationDirectory , String url){
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context,destinationDirectory,fileName + fileExtention);
        downloadManager.enqueue(request);
    }

}
