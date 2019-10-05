package com.example.work;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {
    //defining views
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText client;
    private EditText skill;
    private EditText vitae;

    //firebase auth object
    private FirebaseAuth firebaseAuth;


    //progress dialog
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //getting firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the objects getcurrentuser method is not null
        //means user is already logged in
        if(firebaseAuth.getCurrentUser() != null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), homepage.class));
        }

        //initializing views
        client = (EditText)findViewById(R.id.person);
        skill = (EditText) findViewById(R.id.prof);
        vitae = (EditText)findViewById(R.id.cv);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.register);


        progressDialog = new ProgressDialog(this);

//        attaching click listener
        buttonSignIn.setOnClickListener(this);
    }
    //method for user login
    private void userLogin(){
        final String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();
        String names = client.getText().toString().trim();
        String cv = vitae.getText().toString().trim();
        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(names)){
            Toast.makeText(this,"Please enter your Name",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(cv)){
            Toast.makeText(this,"Please enter a link to your cv",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Loading...");
        progressDialog.show();

        //logging in the user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            String names = client.getText().toString();
                            String email = editTextEmail.getText().toString();
                            String cv = vitae.getText().toString();
                            String skills = skill.getText().toString();

                            User newuser = new User(names,email,cv,skills);

                            Intent myIntent = new Intent(signup.this,MyIntentService.class);
                            myIntent.setAction(Backgroundactivities.sendUserDetails);
                            myIntent.putExtra("Client",newuser);
                            startService(myIntent);

                            Intent intent = new Intent( getApplicationContext(), homepage.class);
                            intent.putExtra("names", names);
                            intent.putExtra("email", email);
                            intent.putExtra("cv", cv);
                            intent.putExtra("skills", skills );
                            startActivity(intent);

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            progressDialog.hide();
                            startActivity(new Intent(getApplicationContext(), homepage.class));


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("Authentication","Auth failed"+task.getException());
                            Toast.makeText(signup.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view){
        if (view == buttonSignIn)
        {
            userLogin();
        }



    }

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }





}
