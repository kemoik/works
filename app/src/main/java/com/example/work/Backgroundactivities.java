package com.example.work;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Backgroundactivities
{

    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    public static final String writeToFirebase ="write to Firebase";
    public static final String sendUserDetails = "send User Details";

    public Backgroundactivities()
    {

    }
    public void writeToFirebase(Jobs jobs)
    {
        firebaseDatabase  = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Job").push().setValue(jobs);

    }
    public void SendUserDetails(User user){
        firebaseDatabase  = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.child("Users").push().setValue(user);
    }
}
