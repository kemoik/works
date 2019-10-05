package com.example.work;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class display extends AppCompatActivity {

    private EditText company;
    private EditText prof;
    private EditText loc;
    private EditText dat;
    private Button send;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        mDatabase = FirebaseDatabase.getInstance().getReference();

        company =(EditText)findViewById(R.id.company);
        prof=(EditText)findViewById(R.id.profession);
        loc =(EditText)findViewById(R.id.location);
        dat=(EditText)findViewById(R.id.date);
        send=(Button)findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String companyName = company.getText().toString();
                String profession = prof.getText().toString();
                String date = dat.getText().toString();
                String location = loc.getText().toString();

                Jobs newjob = new Jobs(companyName,profession,date,location);

                Intent myIntent = new Intent(display.this,MyIntentService.class);
                myIntent.setAction(Backgroundactivities.writeToFirebase);
                myIntent.putExtra("Work",newjob);
                startService(myIntent);

            }
        });

    }
}
