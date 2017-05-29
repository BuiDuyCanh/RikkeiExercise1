package com.example.canhbd.noteassigment.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.canhbd.noteassigment.R;
import com.example.canhbd.noteassigment.realm.Information;

public class updatedata extends AppCompatActivity {
    ImageButton imgcolor,imgupdate,imgcamera;
    Spinner updatedate,timeupdate;
    EditText updatetitle,updatenote;
    Information information;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedata);
        imgcolor = (ImageButton) findViewById(R.id.imgcolorupdate);
        imgupdate = (ImageButton) findViewById(R.id.imgupdate);
        imgcamera = (ImageButton) findViewById(R.id.imgcamera);
        updatedate = (Spinner) findViewById(R.id.spndateupdate);
        timeupdate = (Spinner) findViewById(R.id.spntimeupdate);
        updatetitle = (EditText) findViewById(R.id.edttitleupdate);
        updatenote = (EditText) findViewById(R.id.edtnoteupdate);

        updatetitle.setText(information.getTitle());
        updatenote.setText(information.getNote());
        updatedate.setSelected(Boolean.parseBoolean(information.getDate()));
        updatedate.setSelected(Boolean.parseBoolean(information.getTime()));
    }

}
