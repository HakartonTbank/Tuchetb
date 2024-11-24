package com.example.tuchet;

import android.util.Log;

import java.util.ArrayList;

public class UserData {
    public static ArrayList<DataOperation> dataList = new ArrayList<>();
    public static long income = 0;
    public  static long spend = 0;

    public static Integer cnt = 0;
    public static void increaseCnt(){
        Log.d("my_logs", "new el in data list");
        cnt++;
    }

}
