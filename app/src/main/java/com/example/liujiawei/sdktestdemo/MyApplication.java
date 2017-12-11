package com.example.liujiawei.sdktestdemo;

import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by liujiawei on 2017/12/6.
 */

public class MyApplication extends Application {
    private String mylabel;
    @Override
    public void onCreate() {
        super.onCreate();
        initBugly();
    }
    public String getLabel(){
        return mylabel;
    }
    public void setLabel(String S){
        this.mylabel = S;
    }

    private void initBugly() {
        String packageName = getApplicationContext().getPackageName();
        String processName  = getProcessName(Process.myPid());
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
//        CrashReport.initCrashReport(getApplicationContext(),"63915c6627",true);
        Bugly.init(getApplicationContext(), "63915c6627", false);
    }

    private String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/"+ pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)){
                processName = processName.trim();
            }
            return  processName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  null;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
