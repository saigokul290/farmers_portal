package com.example.farmerportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {



    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText phoneId;
    EditText passId;
    String phoneid;
    String pass_1;



    private static final String TAG = MainActivity.class.getSimpleName();


    String root = "1";
    String root_pass = "1";

    String root1 = "2";
    String root1_pass = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    public void log(View view){
        phoneId = findViewById(R.id.editText);
        passId = findViewById(R.id.editText2);
        phoneid = phoneId.getText().toString();
        pass_1 = passId.getText().toString();




        DocumentReference docRef = db.collection("DS").document(phoneid );
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        String pass= document.get("password").toString();
                        String cat= document.get("category").toString();

                        login(pass,cat);


                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });






        // Toast.makeText(this,phone_pass,Toast.LENGTH_SHORT).show();

    }

    public void login(String pass,String cat_) {
        if(cat_.equalsIgnoreCase("seller") && pass_1.equals(pass) || cat_.equalsIgnoreCase("buyer") && pass_1.equals(pass)){
            String cat = "seller";
            Class a;
            if(cat_.equalsIgnoreCase("buyer")){
                a = BuyerActivity.class;
            }
            else{
                a = ComActivity.class;
            }
            Intent intent = new Intent(this, a);
            intent.putExtra("phoneid","1");
            startActivity(intent);


        }
        else{
            AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
            b.setMessage("WRONG ID AND PASSWORD").setPositiveButton("OK",null).setCancelable(false);

            AlertDialog alert = b.create();
            alert.show();
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
