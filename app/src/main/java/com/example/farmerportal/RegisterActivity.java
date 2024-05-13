package com.example.farmerportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

     FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final String TAG = RegisterActivity.class.getSimpleName();


    EditText name,mobile,password,address;
    Spinner category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (EditText)findViewById(R.id.editText3);
        mobile = (EditText)findViewById(R.id.editText4);
        password = (EditText)findViewById(R.id.editText5);
        address = (EditText)findViewById(R.id.editText6);
        category = (Spinner)findViewById(R.id.spinner);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    public void log(View view){

        String name1 = name.getText().toString();
        String mobile1 = mobile.getText().toString();

        String password1 = password.getText().toString();
        String address1 = address.getText().toString();
        String cat = category.getSelectedItem().toString();


        if(name1.isEmpty() || mobile1.isEmpty() || password1.isEmpty() || address1.isEmpty()){
            AlertDialog.Builder b = new AlertDialog.Builder(RegisterActivity.this);
            b.setMessage("SOME FIELDS ARE EMPTY FILL THEM").setPositiveButton("OK",null).setCancelable(false);

            AlertDialog alert = b.create();
            alert.show();
        }
        else {

            Map<String, Object> user = new HashMap<>();
            user.put("phone", mobile1);
            user.put("name", name1);
            user.put("password", password1);
            user.put("address", address1);
            user.put("category", cat);

            db.collection("DS").document(mobile1)
                    .set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });



            Toast.makeText(this, "You have been succesfully resgistered", Toast.LENGTH_SHORT).show();
            boolean registered = true;
            if (registered) {
                Intent intent = new Intent(this, RegisteredActivity.class);
                startActivity(intent);
            }
        }
    }
}
