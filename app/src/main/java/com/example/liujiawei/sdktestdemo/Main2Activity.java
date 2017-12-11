package com.example.liujiawei.sdktestdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        lv = (ListView) findViewById(R.id.lv);
//        List<String> list = new ArrayList<>();
//        for (int i=0;i<30;i++){
//            list.add("ceshi" +i);
//        }
//        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
//        lv.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
