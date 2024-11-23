package com.example.tuchet;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class EnterDateData {

    public EnterDateData(Date date, Integer summ, Boolean replanishment, Boolean food,
                         Boolean houseAndCS, Boolean medicine, Boolean optionalExpenses, Boolean transport) {

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        Long longTime = new Long(date.getTime()/86400);
        HashMap<String, String> dateInfo = new HashMap<>();
        dateInfo.put("summ", Integer.toString(summ));
        dateInfo.put("replanishment", Boolean.toString(replanishment));
        dateInfo.put("food", Boolean.toString(food));
        dateInfo.put("houseAndCS", Boolean.toString(houseAndCS));
        dateInfo.put("medicine", Boolean.toString(medicine));
        dateInfo.put("optionalExpenses", Boolean.toString(optionalExpenses));
        dateInfo.put("transport", Boolean.toString(transport));

        //dateInfo.put("date", Long.toString(longTime));
        FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("data").child(Long.toString(longTime))
                .setValue(dateInfo);

    }
}
