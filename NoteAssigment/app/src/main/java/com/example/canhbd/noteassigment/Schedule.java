package com.example.canhbd.noteassigment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class Schedule extends AppCompatActivity {
    Realm realm;
    public AlertDialog dialog;
    EditText Title, Note;
    RealmChangeListener realmChangeListener;
    Spinner dateSpinner, timeSpinner;
    ArrayAdapter adapterDate;
    ArrayAdapter adapterTime;
    String[] Datespn = {"Today", "Tomorrow", "Next Week", "Other",};
    String[] timespn = {"9:00", "10:00", "11:00", "12:00",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        dateSpinner = (Spinner) findViewById(R.id.spndate);
        timeSpinner = (Spinner) findViewById(R.id.spntime);
        Title = (EditText) findViewById(R.id.edttitle);
        Note = (EditText) findViewById(R.id.edtnote);

        //SETUP REALM
        realm = Realm.getDefaultInstance();

    }

    public void InsertData(View click) {

        String title = Title.getText().toString();
        String note = Note.getText().toString();
        String date,time;
        Information information = new Information();
        if (title.isEmpty() || note.isEmpty()) {

            information.setTitle("UnTitle");
            information.setNote("UnNote");
            information.setDate("");
            information.setTime("");
            Realheper realheper = new Realheper(realm);
            realheper.save(information);


        } else {
            date = this.dateSpinner.getSelectedItem().toString();
            time = this.timeSpinner.getSelectedItem().toString();
            information.setTitle(title);
            information.setNote(note);
            information.setDate(date);
            information.setTime(time);
            Realheper realheper = new Realheper(realm);
            realheper.save(information);
        }


            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

    }
    public void DateTime(View click) {

        dateSpinner.setVisibility(View.VISIBLE);
        timeSpinner.setVisibility(View.VISIBLE);


        //Creating the ArrayAdapter instance having the country list
        adapterDate = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Datespn);
        adapterDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterTime = new ArrayAdapter(this, android.R.layout.simple_spinner_item, timespn);
        adapterDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        dateSpinner.setAdapter(adapterDate);
        timeSpinner.setAdapter(adapterTime);
    }

    public void licstcolorclick(View Click) {
//        mShowDialogcolor = (ImageButton) findViewById(R.id.imglist);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog, null);
        mBuilder.setView(mView);
        dialog = mBuilder.create();
        mView.setMinimumWidth(250);
        mView.setMinimumHeight(400);
        dialog.setContentView(mView);
        dialog.show();
    }

    public void whitecolor(View click) {
        LinearLayout white = (LinearLayout) findViewById(R.id.linewarcolor);
        white.setBackgroundColor(Color.WHITE);
        dialog.dismiss();

    }

    public void Bluecolor(View click) {
        LinearLayout white = (LinearLayout) findViewById(R.id.linewarcolor);
        white.setBackgroundColor(Color.GREEN);
        dialog.dismiss();

    }

    public void Redcolor(View click) {
        LinearLayout white = (LinearLayout) findViewById(R.id.linewarcolor);
        white.setBackgroundColor(Color.RED);
        dialog.dismiss();

    }

    public void Oriangecolor(View click) {
        LinearLayout white = (LinearLayout) findViewById(R.id.linewarcolor);
        white.setBackgroundColor(Color.BLUE);
        dialog.dismiss();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.removeChangeListener(realmChangeListener);
        realm.close();
    }
}
