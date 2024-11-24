package com.example.tuchet;

import java.util.ArrayList;

public class UserData {
    public static ArrayList<DataOperation> dataList;
    public static long income = 0;
    public  static long spend = 0;

    static Integer cnt = 0;
    public static void increaseCnt(){
        cnt++;
    }
    public void addData(DataOperation d){
        dataList.add(d);
    }

    public static Integer getCnt() {
        return cnt;
    }

}
