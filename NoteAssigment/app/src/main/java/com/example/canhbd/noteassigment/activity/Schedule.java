package com.example.canhbd.noteassigment.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.canhbd.noteassigment.R;
import com.example.canhbd.noteassigment.utils.Realheper;
import com.example.canhbd.noteassigment.realm.Information;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class Schedule extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener, TimePickerDialog.OnTimeSetListener {
    Realm realm;
    public AlertDialog dialog;
    EditText Title, Note;
    String color;
    RealmChangeListener realmChangeListener;
    Spinner dateSpinner, timeSpinner;
    ArrayAdapter adapterDate;
    ArrayAdapter adapterTime;
    String title, note, date, time;
    String[] Datespn = new String[]{"Today", "Tomorrow", "Next Week", "Other Day"};
    String[] timespn = new String[]{"9:00", "10:00", "11:00", "Other Time"};
    final ArrayList<String> DateList = new ArrayList<>(Arrays.asList(Datespn));
    final ArrayList<String> TimeList = new ArrayList<>(Arrays.asList(timespn));
    private int idinfor;
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

        dateSpinner.setOnItemSelectedListener(this);
        timeSpinner.setOnItemSelectedListener(this);


    }

    public void InsertData(View click) {
        RealmResults<Information> resultUser = realm.where(Information.class).findAll();
        title = Title.getText().toString();
        note = Note.getText().toString();
        Information information = new Information();
        //check if any user already registered
        Realheper helper=new Realheper(realm);

        if(resultUser.size()>0){
            Information exist = resultUser.get(resultUser.size()-1);
            idinfor = exist.getId()+1;
            Log.d("userID",String.valueOf(idinfor));
            if (title.isEmpty() || note.isEmpty()) {
                information.setId(idinfor);
                information.setTitle("UnTitle");
                information.setNote("UnNote");
                information.setDate("");
                information.setTime("");
                information.setColor(color);
                helper.save(information);
            } else {

                date = this.dateSpinner.getSelectedItem().toString();
                time = this.timeSpinner.getSelectedItem().toString();
                information.setId(idinfor);
                information.setTitle(title);
                information.setNote(note);
                information.setDate(date);
                information.setTime(time);
                information.setColor(color);
                realm.commitTransaction();
                helper.save(information);
            }
        }else{
            //empty users
            idinfor = 1;
            Log.d("userID",String.valueOf(idinfor));
            if (title.isEmpty() || note.isEmpty()) {
                information.setId(idinfor);
                information.setTitle("UnTitle");
                information.setNote("UnNote");
                information.setDate("");
                information.setTime("");
                information.setColor(color);
                helper.save(information);
            } else {
                date = this.dateSpinner.getSelectedItem().toString();
                time = this.timeSpinner.getSelectedItem().toString();
                information.setId(idinfor);
                information.setTitle(title);
                information.setNote(note);
                information.setDate(date);
                information.setTime(time);
                information.setColor(color);
                realm.commitTransaction();
                helper.save(information);
            }
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


    public void DateTime(View click) {

        dateSpinner.setVisibility(View.VISIBLE);
        timeSpinner.setVisibility(View.VISIBLE);

        adapterDate = new ArrayAdapter(this, android.R.layout.simple_spinner_item, DateList);
        adapterDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterTime = new ArrayAdapter(this, android.R.layout.simple_spinner_item, TimeList);
        adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        dateSpinner.setAdapter(adapterDate);
        timeSpinner.setAdapter(adapterTime);


    }

    public void licstcolorclick(View Click) {
//        mShowDialogcolor = (ImageButton) findViewById(R.id.imglist);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialogcolor, null);
        mBuilder.setView(mView);
        dialog = mBuilder.create();
        mView.setMinimumWidth(250);
        mView.setMinimumHeight(400);
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
        LinearLayout white = (LinearLayout) findViewById(R.id.linewarcolor);
        white.setBackgroundColor(Color.WHITE);
        color = "WHITE";
        dialog.dismiss();
    }

    public void Bluecolor(View click) {
        LinearLayout white = (LinearLayout) findViewById(R.id.linewarcolor);
        white.setBackgroundColor(Color.GREEN);
        color = "GREEN";
        dialog.dismiss();
    }

    public void Redcolor(View click) {
        LinearLayout white = (LinearLayout) findViewById(R.id.linewarcolor);
        white.setBackgroundColor(Color.RED);
        color = "RED";
        dialog.dismiss();
    }

    public void Oriangecolor(View click) {
        LinearLayout white = (LinearLayout) findViewById(R.id.linewarcolor);
        white.setBackgroundColor(Color.BLUE);
        color = "BLUE";
        dialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.removeChangeListener(realmChangeListener);
        realm.close();
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
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.show(getSupportFragmentManager(), "date");

        } else if (select.equals("Other Time")) {
            DialogFragment newFragment = new TimePickerFragment();
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
}
