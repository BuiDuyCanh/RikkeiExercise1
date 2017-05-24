package com.example.canhbd.noteassigment;

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



import java.util.ArrayList;

/**
 * Created by Oclemy on 6/15/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class CustomAdapter extends BaseAdapter {

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
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.customgridview,parent,false);
        }

        TextView time= (TextView) convertView.findViewById(R.id.txttime);
        TextView title= (TextView) convertView.findViewById(R.id.txttitle);
        TextView note= (TextView) convertView.findViewById(R.id.txtnote);
        TextView date= (TextView) convertView.findViewById(R.id.txtdate);

        final Information infor= (Information) this.getItem(position);

        time.setText(infor.getTime());
        title.setText(infor.getTitle());
        note.setText(infor.getNote());
        date.setText(infor.getDate());


        //ONITECLICK
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,infor.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }
}












