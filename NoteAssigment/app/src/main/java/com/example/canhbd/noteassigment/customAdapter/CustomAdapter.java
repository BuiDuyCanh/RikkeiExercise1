package com.example.canhbd.noteassigment.customAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.canhbd.noteassigment.R;
import com.example.canhbd.noteassigment.realm.Information;

import java.util.ArrayList;

/**
 * Created by Oclemy on 6/15/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class CustomAdapter extends BaseAdapter {
    int color;
    Context c;
    ArrayList<Information> informations;


    public CustomAdapter(Context c, ArrayList<Information> Informations) {
        this.c = c;
        this.informations = Informations;
    }

    @Override
    public int getCount() {
        return informations.size();
    }

    @Override
    public Object getItem(int position) {
        return informations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(c).inflate(R.layout.customgridview, parent, false);
        }


        TextView title = (TextView) convertView.findViewById(R.id.txttitle);
        TextView note = (TextView) convertView.findViewById(R.id.txtnote);
        TextView date = (TextView) convertView.findViewById(R.id.txtdate);

        final Information infor = (Information) this.getItem(position);
        title.setText(infor.getTitle());
        note.setText(infor.getNote());
        date.setText(infor.getDate());

        if (infor.getColor().equals("WHITE")) {
            convertView.setBackgroundColor(Color.WHITE);
        } else if (infor.getColor().equals("GREEN")) {
            convertView.setBackgroundColor(Color.GREEN);
        } else if (infor.getColor().equals("RED")) {
            convertView.setBackgroundColor(Color.RED);
        } else if
            (infor.getColor().equals("BLUE")) {
            convertView.setBackgroundColor(Color.BLUE);
        }

        return convertView;
    }
}












