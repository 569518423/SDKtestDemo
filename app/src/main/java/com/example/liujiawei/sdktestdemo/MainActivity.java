package com.example.liujiawei.sdktestdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tencent.bugly.crashreport.CrashReport;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button  button,button2,button3;
    private MyApplication application;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        application = (MyApplication) getApplication();
        application.setLabel("zou ni");
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        EventBus.getDefault().unregister(this);
//    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBus_demo messageEvent){
        Log.e("aaa","onEvent ======"+messageEvent.getName()+messageEvent.getAge());

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                CrashReport.testJavaCrash();
                break;
            case R.id.button2:
//                CrashReport.testANRCrash();
                startActivity(new Intent(this,Main3Activity.class));
                break;
            case R.id.button3:
//                CrashReport.testNativeCrash();
                EventBus.getDefault().postSticky(new EventBus_demo(11,"kimi"));
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
