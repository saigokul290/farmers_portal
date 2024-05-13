package com.example.farmerportal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProductActivity extends AppCompatActivity {
    Button submit;
    private Spinner spinner;
    private EditText etPrice;
    private EditText etQuantity;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("products");



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        submit = findViewById(R.id.button5);
        spinner = findViewById(R.id.spinner3);
        etPrice = findViewById(R.id.editText7);
        etQuantity = findViewById(R.id.editText8);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cat, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedcat = spinner.getSelectedItem().toString();
                String price = etPrice.getText().toString();
                String quantity = etQuantity.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference productsRef = database.getReference("products");
                String productId = productsRef.push().getKey();

                // Create a Product object
                Product product = new Product(selectedcat, price, quantity);
                myRef.push().setValue(product);
                productsRef.child(productId).setValue(product);


                String message = "Selected cat"+selectedcat+"\nPrice:"+price+"\nQuantity:"+quantity;
                Toast.makeText(ProductActivity.this,message,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ProductActivity.this,ProductAddedActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}
