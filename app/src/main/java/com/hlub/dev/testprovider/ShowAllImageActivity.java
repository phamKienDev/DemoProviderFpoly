package com.hlub.dev.testprovider;

import android.Manifest;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Config;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShowAllImageActivity extends AppCompatActivity {

    private GridView gridView;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_image);
        gridView = findViewById(R.id.gridView);

        if (ContextCompat.checkSelfPermission(ShowAllImageActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ShowAllImageActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    999);
        } else {
            showAllImage();
        }

    }

    public void showAllImage() {
        List<Bitmap> listImage = new ArrayList<>();
        List<String> list = new ArrayList<>();
        //từ android 8. dùng ContactsContract.Contacts.CONTENT_URI thay uri
        CursorLoader loader = new CursorLoader(this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        Cursor cursor = loader.loadInBackground();

        if (cursor != null && cursor.getCount() > 0)

        {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                String s = "";
                //id image
                String id = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media._ID));
                //name image
                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

                //s = id + " - " + name;
                cursor.moveToNext();
                list.add(name);
                Log.e("size 0 ", list.size() + "");


            }
            cursor.close();

            ImageAdapter imageAdapter = new ImageAdapter(list, this);
            gridView.setAdapter(imageAdapter);
        }

    }


}
