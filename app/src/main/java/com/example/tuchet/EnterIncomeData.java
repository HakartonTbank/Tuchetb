package com.example.tuchet;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.app.DatePickerDialog;
import android.text.format.DateUtils;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class EnterIncomeData extends AppCompatActivity {

    EditText Money;

    String[] types = {"Наличные", "Безналичные", "Стипендия", "Заработная плата", "Другое"};

    TextView currentDateTime;
    Calendar dateAndTime=Calendar.getInstance();

    Boolean cash = false;
    Boolean nonCash = false;
    Boolean scholarship = false;
    Boolean wage = false;
    Boolean other = false;
    Date date;
    UserData userData = new UserData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.enter_data_activity);
        Money = findViewById(R.id.enterMoney);
        currentDateTime = findViewById(R.id.currentDateTime);
        setInitialDateTime();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setSelection(2);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //Toast.makeText(getBaseContext(), "Position =" + position, Toast.LENGTH_SHORT).show();

                switch(position){
                    case (0): cash = true;
                        nonCash = false;
                        scholarship = false;
                        wage = false;
                        other = false;
                            break;
                    case (1): nonCash = true;
                        cash = false;
                        scholarship = false;
                        wage = false;
                        other = false;
                        break;
                    case (2): scholarship = true;
                        nonCash = false;
                        cash = false;
                        wage = false;
                        other = false;
                        break;
                    case (3): wage = true;
                        nonCash = false;
                        scholarship = false;
                        wage = false;
                        other = false;
                        break;
                    case (4): other = true;
                        nonCash = false;
                        scholarship = false;
                        wage = false;
                        cash = false;
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){

            }
        });

    }

    public void onEnterClick(View v){
        enterDateData(date, Integer.parseInt(Money.getText().toString()), true, cash, nonCash, scholarship, wage, other);
    }

    public void setDate(View v) {
        new DatePickerDialog(EnterIncomeData.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }


    // установка начальных даты и времени
    private void setInitialDateTime() {

        currentDateTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
        date = dateAndTime.getTime();
    }

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

    public void enterDateData(Date date, Integer summ, Boolean replanishment, Boolean cash,
                         Boolean nonCash, Boolean scholarship, Boolean wage, Boolean other) {

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        Date currDate = new Date();
        Long longTime = new Long((date.getTime()/100000)*100000 + currDate.getTime()%100000);
        HashMap<String, String> dateInfo = new HashMap<>();
        dateInfo.put("summ", Integer.toString(summ));
        dateInfo.put("replanishment", Boolean.toString(replanishment));
        dateInfo.put("cash", Boolean.toString(cash));
        dateInfo.put("nonCash", Boolean.toString(nonCash));
        dateInfo.put("scholarship", Boolean.toString(scholarship));
        dateInfo.put("wage", Boolean.toString(wage));
        dateInfo.put("other", Boolean.toString(other));

        DataOperation dat = new DataOperation();
        dat.unixDate = longTime;
        if(cash){
            dat.cash += summ;
        }else if(nonCash){
            dat.nonCash += summ;
        }else if(scholarship){
            dat.scholarship += summ;
        }else if(wage){
            dat.wage += summ;
        }else if(other){
            dat.other += summ;
        }

        userData.addData(dat);
        userData.increaseCnt();

        FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("data").child(Long.toString(longTime))
                .setValue(dateInfo);

    }
}


