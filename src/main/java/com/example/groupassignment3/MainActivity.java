package com.example.groupassignment3;
import android.Manifest;
import android.net.Uri;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.example.groupassignment3.ui.main.*;


public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_WRITE = 0;
    private static final int READ_REQUEST_CODE = 42;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private boolean permissionGranted;
    private Bundle placeholderBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissionGranted = true;

        setContentView(R.layout.activity_main);
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            permissionGranted = false;
            System.out.println("Permissions were not granted in app settings");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_WRITE);
        } else { System.out.println("Permissions were granted in app settings");}



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {//Switch statement for additional permission requests
            case PERMISSION_REQUEST_WRITE: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionGranted = true;
                    System.out.println("Permissions were granted in pop-up");

                } else {
                    permissionGranted = false;
                    System.out.println("Permissions were not granted in pop-up");
                }

            }


        }
    }



    public void performSearch() {
        if(permissionGranted) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");

            startActivityForResult(intent, READ_REQUEST_CODE);

        } else {System.out.println("Permissions not available for that command");}

    }


    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                Uri uri;
                if (resultData != null) {
                    uri = resultData.getData();
                    sendStringToReadFragment(uri.getPath());
                }
        }

    }

    private void sendStringToReadFragment(String fileLocation) {
        placeholderBundle = new Bundle();
        placeholderBundle.putString("fileLocation", fileLocation);
        sectionsPagerAdapter.getReadFragment().setArguments(placeholderBundle);

    }




}