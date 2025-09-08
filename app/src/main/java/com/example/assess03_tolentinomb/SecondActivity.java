package com.example.assess03_tolentinomb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class SecondActivity extends AppCompatActivity {

    // declaring UI elements
    TextView tvName, tvAge;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        // connecting to xml views
        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        btnBack = findViewById(R.id.btnBack);

        // getting data from intent, receiving data from MainActivity
        String name = getIntent().getStringExtra("name");
        String birthdate = getIntent().getStringExtra("birthdate");
        String sex = getIntent().getStringExtra("sex");

        // adding Ms. or Mr. based on sex
        String prefix = "";
        if (sex != null) {
            if (sex.equals("Female")) {
                prefix = "Ms. ";
            } else if (sex.equals("Male")) {
                prefix = "Mr. ";
            }
        }
        tvName.setText ("Hi, " + prefix + name); // displaying greeting with prefix

        // calculating age from birthdate
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
            Date date = sdf.parse(birthdate); // converting string to date

            // calendar object for date of birth
            Calendar dob = Calendar.getInstance();
            dob.setTime(date);

            // Calendar object for date today
            Calendar today = Calendar.getInstance();

            // compute age difference in years
            int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

            // if birthday hasn't passed yet this year, subtract 1
            if(today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                age--;
            }
            tvAge.setText("Your Age is: " + age); // display calculated age
        }catch (Exception e){
            tvAge.setText("Invalid birtdate format."); // if format is wrong
        }

        // back button action
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go back to MainActivity
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}