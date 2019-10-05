package com.example.work;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class userFragment extends Fragment {

    private TextView email;
    private android.widget.ImageView ImageView;
    private android.widget.Button Button;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1 ;

    private TextView name;
    private TextView emails;
    private  TextView skill;
    private  TextView cvs;

    public userFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle queryBundle = getArguments();
        String email = queryBundle.getString("Useremail");
        String names = queryBundle.getString("names");
        String skills =  queryBundle.getString("Userskill");
        String cv = queryBundle.getString("Usercv");



        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_user, container, false);

        name = (TextView) rootview.findViewById(R.id.fullname);
        emails = (TextView) rootview.findViewById(R.id.adress);
        skill = (TextView) rootview.findViewById(R.id.skills);
        cvs = (TextView) rootview.findViewById(R.id.cv);



        ImageView = (ImageView) rootview.findViewById(R.id.profile);
        Button = (Button) rootview.findViewById(R.id.pic);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capture();
            }
        });
//        FloatingActionButton fab = rootview.findViewById(R.id.post);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity().getApplicationContext(), review.class);
//                startActivity(intent);
//            }
//        });
//        email = (TextView) rootview.findViewById(R.id.adress);
//        Intent intent = getIntent();
//        String email = intent.getStringExtra("mail");
//        email.getText();
        return rootview;
    }

    public void startCameraPicker() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

    public void capture() {
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
//             In this code the user is requesting for permissions
            ActivityCompat.requestPermissions( getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        } else {
//                In this case the permissions have been offered already
            startCameraPicker();
        }

    }





    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == 1)
            {

                if (resultCode == RESULT_OK) {

                    final Uri imageUri = data.getData();
                    Log.d("image selected path", imageUri.getPath());
                    System.out.println("image selected path"
                            + imageUri.getPath());
                    ImageView.setImageURI(imageUri);
                    String toStoreString = imageUri.toString();
                    SharedPreferences profileImage = getActivity().getSharedPreferences("prefs",0);
                    SharedPreferences.Editor editor= profileImage.edit();
                    editor.putString("profileImage",toStoreString);
                    editor.commit();
                }
            }

        }

        catch (Exception e)
        {
            Log.d("Exception","Error found " + e.getMessage());
        }
    }
    @Override
    public void onResume()
    {
        super.onResume();

        SharedPreferences settings = getActivity().getSharedPreferences("prefs", 0);
        String image  = settings.getString("profileImage", " ");


        if (!ImageView.equals(" ")) {
            Uri imageUri = Uri.parse(image);
            ImageView.setImageURI(imageUri);
            Log.d("setImage","Image will be set on resuming" );

        }
    }


}
