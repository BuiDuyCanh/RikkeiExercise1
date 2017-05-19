package com.example.canhbd.exercisethread1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Btnclick(View clickedButton) {
        ExecutorService taskList =
                Executors.newFixedThreadPool(1000);
        for(int i=0; i<5; i++) {
            taskList.execute(new Flipper(i));
        }
    }
}
