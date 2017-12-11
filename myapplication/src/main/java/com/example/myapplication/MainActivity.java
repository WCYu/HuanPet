package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         new Outer.method().show();
    }
    interface Inter { void show(); }
    class Outer { //补齐代码 }
            public  void method(){
                new Inter() {
                    @Override
                    public void show() {
                        Log.i("tag","helloWorld")
                    }
                };
            }
        }
}
