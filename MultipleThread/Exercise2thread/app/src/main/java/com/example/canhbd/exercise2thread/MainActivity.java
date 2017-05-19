package com.example.canhbd.exercise2thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements Runnable{
    private final static int LOOP_LIMIT = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Btnclick(View clickedButton) {
        ExecutorService taskList =
                Executors.newFixedThreadPool(1000);
        for(int i=0; i<5; i++) {
            taskList.execute(this);
        }
    }
    @Override
    public void run() {
        for (int i = 0; i < LOOP_LIMIT; i++) {
            String threadName = Thread.currentThread().getName();
            System.out.printf("%s: %s%n", threadName, i);
            ThreadUtils.pause(Math.random());
        }
    }
}
