package com.example.work;

import android.content.Intent;

public class MyIntentService
{
    public MyIntentService()
    {
        super("MyIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {

        Backgroundactivities backgroundactivities = new Backgroundactivities();
        String action = intent.getAction();

        if (Backgroundactivities.writeToFirebase.equals(action))
        {
            Jobs job = intent.getParcelableExtra("Work");
            backgroundactivities.writeToFirebase(job);
        }
        if(Backgroundactivities.sendUserDetails.equals(action))
        {
            User user = intent.getParcelableExtra("Client");
            backgroundactivities.SendUserDetails(user);
        }
    }
}
