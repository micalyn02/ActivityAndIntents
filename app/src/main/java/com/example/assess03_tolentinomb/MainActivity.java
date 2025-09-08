package com.example.assess03_tolentinomb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // declaring UI elements
    EditText etName, etBirthdate;
    Button btnNext;
    Spinner sexField;

    // options that will be shown in sex field
    String [] sexOptions = {"Female", "Male"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // connecting to xml views
        etName = findViewById(R.id.etName);
        etBirthdate = findViewById(R.id.etBirthdate);
        sexField = findViewById(R.id.sexField);
        btnNext= findViewById(R.id.btnNext);

        // setting up spinner
        // creating array adapter to supply spinner with values
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                sexOptions
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // setting dropdown style
        sexField.setAdapter(adapter); // attach adapter to spinner


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting input values
                String name = etName.getText().toString().trim();
                String birthdate = etBirthdate.getText().toString().trim();
                String sex = sexField.getSelectedItem().toString();

                // create intent to move to SecondActivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                // attaching data to send to SecondActivity
                intent.putExtra("name", name);
                intent.putExtra("birthdate", birthdate);
                intent.putExtra("sex", sex);

                // starting SecondActivity
                startActivity(intent);
            }
        });
    }
}