package com.example.tuchet;

import java.util.ArrayList;

public class UserData {
    static ArrayList<DataOperation> dataList;
    static Integer cnt = 0;
    public void increaseCnt(){
        cnt++;
    }
    public void addData(DataOperation d){
        dataList.add(d);
    }

}
