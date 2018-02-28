package com.example.ist_mac_01.labdatabases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);
        db.addContact (new Contact("Pongsakron","099999999"));
        List<Contact> contacts = db.getAllContacts();

        String[] datas = new String[contacts.size()];
        for(int i = 0 ; i<datas.length ; i++) {
            datas[i]=contacts.get(i)._name;
        }

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(),datas);
        ListView listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(adapter);

        Toast.makeText(this,contacts.get(0)._name, Toast.LENGTH_LONG);
    }
}
