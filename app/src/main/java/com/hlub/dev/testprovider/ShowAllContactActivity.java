package com.hlub.dev.testprovider;

import android.Manifest;
import android.content.CursorLoader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowAllContactActivity extends AppCompatActivity {

    private ListView lvContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_contact);
        lvContact = findViewById(R.id.lvAllContact);


        if (ContextCompat.checkSelfPermission(ShowAllContactActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ShowAllContactActivity.this, new String[]{Manifest.permission.READ_CONTACTS},
                    999);
        } else {
            showAllContact();
        }

    }

    public void showAllContact() {
        Uri uri = Uri.parse("content://contacts/people");
        ArrayList<String> list = new ArrayList<>();
        //từ android 8. dùng ContactsContract.Contacts.CONTENT_URI thay uri
        CursorLoader loader = new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        Cursor cursor = loader.loadInBackground();
        if (cursor != null && cursor.getCount() > 0)

        {
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                String s = "";
                //id contact
                String id = ContactsContract.Contacts._ID;
                int idIndex = cursor.getColumnIndex(id);
                //name contact
                String name = ContactsContract.Contacts.DISPLAY_NAME;
                int nameIndex = cursor.getColumnIndex(name);
                s = cursor.getString(idIndex) + " - " + cursor.getString(nameIndex);
                cursor.moveToNext();
                list.add(s);
            }
            cursor.close();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
            lvContact.setAdapter(adapter);
        }
    }


}

