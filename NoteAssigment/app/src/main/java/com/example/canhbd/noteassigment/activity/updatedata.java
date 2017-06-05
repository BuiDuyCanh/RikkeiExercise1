package com.example.canhbd.noteassigment.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;

import io.realm.RealmResults;

import com.example.canhbd.noteassigment.R;
import com.example.canhbd.noteassigment.realm.Information;
import com.example.canhbd.noteassigment.utils.Realheper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class updatedata extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener, TimePickerDialog.OnTimeSetListener {
    ImageButton imgcolor, imgupdate, imgcamera;
    public AlertDialog dialog;
    EditText updatetitle, updatenote;
    Realm realm;
    Spinner dateSpinner, timeSpinner;
    ArrayAdapter adapterDate;
    ArrayAdapter adapterTime;
    String date, time;
    String color;
    String[] Datespn = new String[]{"Today", "Tomorrow", "Next Week", "Other Day"};
    String[] timespn = new String[]{"9:00", "10:00", "11:00", "Other Time"};
    final ArrayList<String> DateList = new ArrayList<>(Arrays.asList(Datespn));
    final ArrayList<String> TimeList = new ArrayList<>(Arrays.asList(timespn));
    LinearLayout lncolor;
    private ArrayList<Information> inforList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedata);
        imgcolor = (ImageButton) findViewById(R.id.imgcolorupdate);
        imgupdate = (ImageButton) findViewById(R.id.imgupdate);
        imgcamera = (ImageButton) findViewById(R.id.imgcamera);
        dateSpinner = (Spinner) findViewById(R.id.spndateupdate);
        timeSpinner = (Spinner) findViewById(R.id.spntimeupdate);
        realm = Realm.getDefaultInstance();
        updatetitle = (EditText) findViewById(R.id.edttitleupdate);
        updatenote = (EditText) findViewById(R.id.edtnoteupdate);
        String title = getIntent().getStringExtra("title");
        String note = getIntent().getStringExtra("note");
        String dateupdate = getIntent().getStringExtra("date");
        String timeupdate = getIntent().getStringExtra("time");
        String colorupdate = getIntent().getStringExtra("color");
        updatetitle.setText(title);
        updatenote.setText(note);
        adapterDate = new ArrayAdapter(this, android.R.layout.simple_spinner_item, DateList);
        adapterDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterTime = new ArrayAdapter(this, android.R.layout.simple_spinner_item, TimeList);
        adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        dateSpinner.setAdapter(adapterDate);
        timeSpinner.setAdapter(adapterTime);
        DateList.add(0, dateupdate);
        DateList.remove(1);
        adapterDate.notifyDataSetChanged();
        date = this.dateSpinner.getSelectedItem().toString();
        TimeList.add(0, timeupdate);
        TimeList.remove(1);
        adapterTime.notifyDataSetChanged();
        time = this.dateSpinner.getSelectedItem().toString();
        dateSpinner.setOnItemSelectedListener(this);
        timeSpinner.setOnItemSelectedListener(this);
        lncolor = (LinearLayout) findViewById(R.id.lnupdatecolor);
        if (colorupdate.equals("WHITE")) {
            lncolor.setBackgroundColor(Color.WHITE);
            color = "WHITE";
        } else if (colorupdate.equals("GREEN")) {
            lncolor.setBackgroundColor(Color.GREEN);
            color = "GREEN";
        } else if (colorupdate.equals("RED")) {
            lncolor.setBackgroundColor(Color.RED);
            color = "RED";
        } else if (colorupdate.equals("BLUE")) {
            lncolor.setBackgroundColor(Color.BLUE);
            color = "BLUE";
        } else {
            lncolor.setBackgroundColor(Color.WHITE);
            color = "WHITE";
        }

    }

    public void update(View click) {
        Information information = new Information();
        int id = getIntent().getIntExtra("id", 0);
        String upDatetitle = updatetitle.getText().toString();
        String upDatenote = updatenote.getText().toString();
        String upDatedate = this.dateSpinner.getSelectedItem().toString();
        String upDatetime = this.timeSpinner.getSelectedItem().toString();
        information.setColor(color);
        Realheper helper = new Realheper(realm);
        helper.updateData(id, upDatetitle, upDatenote, upDatedate, upDatetime, color);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void deleteData(View click) {
        final int id = getIntent().getIntExtra("id", 0);
        final RealmResults<Information> list = realm.where(Information.class).findAll();
        final int id1 = id - 1;
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Delete Data");
        builder.setMessage("Are You Sure Want to delete this data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                realm.beginTransaction();
                list.remove(id1);
                realm.commitTransaction();
                Intent intent = new Intent(updatedata.this, MainActivity.class);
                startActivity(intent);

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(true);
        android.app.AlertDialog alertDialog = builder.show();

    }

    public void licstcolorclick(View Click) {
//        mShowDialogcolor = (ImageButton) findViewById(R.id.imglist);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialogcolor, null);
        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.setContentView(mView);
        dialog.show();
    }
   public void takePhoto(View Click){
       AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
       View mView = getLayoutInflater().inflate(R.layout.dialogpicture, null);
       mBuilder.setView(mView);
       dialog = mBuilder.create();
       dialog.setContentView(mView);
       dialog.show();
   }
    public void While(View click) {

        lncolor.setBackgroundColor(Color.WHITE);
        color = "WHITE";
        dialog.dismiss();
    }

    public void Bluecolor(View click) {

        lncolor.setBackgroundColor(Color.GREEN);
        color = "GREEN";
        dialog.dismiss();
    }

    public void Redcolor(View click) {

        lncolor.setBackgroundColor(Color.RED);
        color = "RED";
        dialog.dismiss();
    }

    public void Oriangecolor(View click) {

        lncolor.setBackgroundColor(Color.BLUE);
        color = "BLUE";
        dialog.dismiss();
    }

    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        DateList.remove(3);
        DateList.add(dateFormat.format(calendar.getTime()));
        adapterDate.notifyDataSetChanged();
        date = this.dateSpinner.getSelectedItem().toString();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = new GregorianCalendar(year, month, dayOfMonth);
        setDate(cal);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String select = parent.getItemAtPosition(position).toString();

        if (select.equals("Other Day")) {
            Schedule.DatePickerFragment fragment = new Schedule.DatePickerFragment();
            fragment.show(getSupportFragmentManager(), "date");

        } else if (select.equals("Other Time")) {
            DialogFragment newFragment = new Schedule.TimePickerFragment();
            newFragment.show(getSupportFragmentManager(), "timePicker");
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        if (minute >= 0 && minute < 10) {
            TimeList.add(hourOfDay + ":0" + minute);

        } else {
            TimeList.add(hourOfDay + ":" + minute);
        }
        TimeList.remove(3);
        adapterTime.notifyDataSetChanged();
        time = this.timeSpinner.getSelectedItem().toString();
    }

    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);


            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), year, month, day);
        }
    }

    public static class TimePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create and return a new instance of TimePickerDialog
            return new TimePickerDialog(getActivity(),
                    (TimePickerDialog.OnTimeSetListener)
                            getActivity(), hour, minute, true);
        }
    }

    public void linkFaceBook(View click) {
        WebView broswer = (WebView) findViewById(R.id.webviewfacebook);
        String url = "https://www.facebook.com";
        broswer.getSettings().setLoadsImagesAutomatically(true);
        broswer.getSettings().setJavaScriptEnabled(true);
        broswer.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        broswer.loadUrl(url);
    }

    public void nextItem(View click) {
        ImageButton nextItem = (ImageButton) findViewById(R.id.imgnext);
        final int id = getIntent().getIntExtra("id", 0);
        inforList = new ArrayList<>();
        final RealmResults<Information> list = realm.where(Information.class).findAll();
        Information information = new Information();
        int idnext = id + 1;
        realm.beginTransaction();

        realm.commitTransaction();
    }
}

