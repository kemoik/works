package com.example.work;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.TextView;

public class homepage extends AppCompatActivity {
    private String email;
    private String name;
    private String skill;
    private String cv;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            switch (item.getItemId()) {

                case R.id.home:
                    homeFragment home = new homeFragment();

                    ft.replace(R.id.container_fragment, home);
                    ft.commit();
                    return true;
                case R.id.Jobs:

                    jobFragment job = new jobFragment();
                    ft.replace(R.id.container_fragment, job);
                    ft.commit();
                    return true;
                case R.id.trends:

                    searchFragment search = new searchFragment();
                    ft.replace(R.id.container_fragment, search);
                    ft.commit();
                    return true;
                case R.id.user:

                    Bundle bundle = new Bundle();
                    bundle.putString("Useremail",email);
                    bundle.putString("Username", name);
                    bundle.putString("Userskill",skill );
                    bundle.putString("Usercv" , cv);
                    userFragment user = new userFragment();
                    user.setArguments(bundle);
                    ft.replace(R.id.container_fragment, user);
                    ft.commit();
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        homeFragment home = new homeFragment();
        ft.replace(R.id.container_fragment, home);
        ft.commit();


//        name = (TextView) findViewById(R.id.fullname);
//        email = (TextView) findViewById(R.id.adress);
//        skill = (TextView) findViewById(R.id.skills);
//        cv = (TextView) findViewById(R.id.cv);

        Intent intent = getIntent();
        name = intent.getStringExtra("names");
        email = intent.getStringExtra("email");
        skill = intent.getStringExtra("skills");
        cv = intent.getStringExtra("cv");



    }

}