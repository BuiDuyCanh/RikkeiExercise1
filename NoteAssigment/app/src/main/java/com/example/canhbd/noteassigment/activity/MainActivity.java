package com.example.canhbd.noteassigment.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.canhbd.noteassigment.customAdapter.CustomAdapter;
import com.example.canhbd.noteassigment.R;
import com.example.canhbd.noteassigment.realm.Information;
import com.example.canhbd.noteassigment.utils.Realheper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class MainActivity extends AppCompatActivity {
    Realm realm;

    RealmChangeListener realmChangeListener;
    GridView gvInfor;
    CustomAdapter adapter;
    Information inforlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        gvInfor = (GridView) findViewById(R.id.gdvinformation);
        //SETUP REALM
        realm = Realm.getDefaultInstance();
        //RETRIEVE
        final Realheper helper = new Realheper(realm);
        helper.retrieveFromDB();
        //SETUP ADAPTER
        adapter = new CustomAdapter(this, helper.justRefresh());
        gvInfor.setAdapter(adapter);
        //DETECT DATA CHANGES
        inforlist = new Information();
        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange() {
                adapter = new CustomAdapter(MainActivity.this, helper.justRefresh());
                gvInfor.setAdapter(adapter);
            }
        };
        //ADD IT TO REALM
        realm.addChangeListener(realmChangeListener);
        gvInfor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Information inforItem = (Information) parent.getItemAtPosition(position);
                Intent intentUpdate = new Intent(getApplicationContext(), updatedata.class);
                intentUpdate.putExtra("id",inforItem.getId());
                intentUpdate.putExtra("title",inforItem.getTitle());
                intentUpdate.putExtra("note",inforItem.getNote());
                intentUpdate.putExtra("date",inforItem.getDate());
                intentUpdate.putExtra("time",inforItem.getTime());
                intentUpdate.putExtra("color",inforItem.getColor());
                startActivity(intentUpdate);
            }
        });
    }

    public void Add(View click) {
        Intent intent = new Intent(this, Schedule.class);
        startActivity(intent);
    }
}
