package com.example.tuchet;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class EnterData extends AppCompatActivity {

    EditText Money;

    String[] types = {"Food", "HouseAndCS", "Medicine", "OptionalExpenses", "Transport"};

    TextView currentDateTime;
    Calendar dateAndTime=Calendar.getInstance();

    Boolean food = false;
    Boolean houseAndCS = false;
    Boolean medicine = false;
    Boolean optionalExpenses = false;
    Boolean transport = false;
    Date date;

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
                    case (0): food = true;
                            break;
                    case (1): houseAndCS = true;
                        break;
                    case (2): medicine = true;
                        break;
                    case (3): optionalExpenses = true;
                        break;
                    case (4): transport = true;
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){

            }
        });

    }

    public void onEnterClick(View v){
        enterDateData(date, Integer.parseInt(Money.getText().toString()), true, food, houseAndCS, medicine, optionalExpenses, transport);
    }

    public void setDate(View v) {
        new DatePickerDialog(EnterData.this, d,
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

    public void enterDateData(Date date, Integer summ, Boolean replanishment, Boolean food,
                         Boolean houseAndCS, Boolean medicine, Boolean optionalExpenses, Boolean transport) {

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        Long longTime = new Long(date.getTime()/86400000);
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


