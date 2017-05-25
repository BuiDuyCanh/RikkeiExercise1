package com.example.canhbd.noteassigment.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.example.canhbd.noteassigment.customAdapter.CustomAdapter;
import com.example.canhbd.noteassigment.R;
import com.example.canhbd.noteassigment.utils.Realheper;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class MainActivity extends AppCompatActivity {
    Realm realm;
    RealmChangeListener realmChangeListener;
    GridView gvInfor;
    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        gvInfor= (GridView) findViewById(R.id.gdvinformation);
        //SETUP REALM
        realm=Realm.getDefaultInstance();
        //RETRIEVE
        final Realheper helper=new Realheper(realm);

        helper.retrieveFromDB();
        //SETUP ADAPTER



        adapter=new CustomAdapter(this,helper.justRefresh());
        gvInfor.setAdapter(adapter);


        //DETECT DATA CHANGES
        realmChangeListener=new RealmChangeListener() {
            @Override
            public void onChange() {
                adapter=new CustomAdapter(MainActivity.this,helper.justRefresh());
                gvInfor.setAdapter(adapter);
            }
        };


        //ADD IT TO REALM
        realm.addChangeListener(realmChangeListener);
    }
    public void  Add(View click){
        Intent intent = new Intent(this,Schedule.class);
        startActivity(intent);
    }
}