package com.example.canhbd.exerciserotations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText message;
    Button btnAdd,btnUpdate;
    ListView lvMessge;
    ArrayList<String> arraylist;
    int vitri = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bind();
        final ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,arraylist);
        lvMessge.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Message = message.getText().toString();
                arraylist.add(Message);
                adapter.notifyDataSetChanged();

            }
        });
        lvMessge.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                message.setText(arraylist.get(position));
                vitri =position;
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arraylist.set(vitri,message.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
        lvMessge.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arraylist.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }


    private void Bind() {
        message = (EditText) findViewById(R.id.edtmessage);
        btnAdd = (Button) findViewById(R.id.btnadd);
        btnUpdate = (Button) findViewById(R.id.btnupdate);
        lvMessge = (ListView) findViewById(R.id.lvmessage);
        arraylist = new  ArrayList<>();

    }

}
