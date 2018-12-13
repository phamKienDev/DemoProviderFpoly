package com.hlub.dev.testprovider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showAllContact(View view) {
        Intent intent = new Intent(MainActivity.this, ShowAllContactActivity.class);
        startActivity(intent);
    }


    public void acessPicture(View view) {
        Intent intent = new Intent(MainActivity.this, ShowAllImageActivity.class);
        startActivity(intent);
    }
}
