package com.example.tuchet;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class EnterSpendData extends AppCompatActivity {

    EditText Money;

    String[] types = {"Еда", "Оплата налогов и счетов", "Здоровье", "Опциональные траты", "Транспорт"};

    TextView currentDateTime;
    Calendar dateAndTime=Calendar.getInstance();

    Boolean food = false;
    Boolean houseAndCS = false;
    Boolean medicine = true;
    Boolean optionalExpenses = false;
    Boolean transport = false;
    Date date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.spend_data_activity);
        Money = findViewById(R.id.enterMoney);
        currentDateTime = findViewById(R.id.currentDateTime);
        setInitialDateTime();





        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setSelection(2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //Toast.makeText(getBaseContext(), "Position =" + position, Toast.LENGTH_SHORT).show();

                switch(position){
                    case (0): food = true;
                        houseAndCS = false;
                        medicine = false;
                        optionalExpenses = false;
                        transport = false;
                        break;
                    case (1): houseAndCS = true;
                        food = false;
                        medicine = false;
                        optionalExpenses = false;
                        transport = false;
                        break;
                    case (2): medicine = true;
                        houseAndCS = false;
                        food = false;
                        optionalExpenses = false;
                        transport = false;
                        break;
                    case (3): optionalExpenses = true;
                        houseAndCS = false;
                        medicine = false;
                        food = false;
                        transport = false;
                        break;
                    case (4): transport = true;
                        houseAndCS = false;
                        medicine = false;
                        optionalExpenses = false;
                        food = false;
                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){

            }
        });

    }

    public void onEnterClick(View v){
        enterDateData(date, Integer.parseInt(Money.getText().toString()), false, food, houseAndCS, medicine, optionalExpenses, transport);
    }

    public void setDate(View v) {
        new DatePickerDialog(EnterSpendData.this, d,
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
        Date currDate = new Date();
        Long longTime = new Long((date.getTime()/100000)*100000 + currDate.getTime()%100000);
        HashMap<String, String> dateInfo = new HashMap<>();
        dateInfo.put("summ", Integer.toString(summ));
        dateInfo.put("replanishment", Boolean.toString(replanishment));
        dateInfo.put("food", Boolean.toString(food));
        dateInfo.put("houseAndCS", Boolean.toString(houseAndCS));
        dateInfo.put("medicine", Boolean.toString(medicine));
        dateInfo.put("optionalExpenses", Boolean.toString(optionalExpenses));
        dateInfo.put("transport", Boolean.toString(transport));

        DataOperation dat = new DataOperation();
        dat.unixDate = longTime;
        if(food){
            dat.food += summ;
        }else if(houseAndCS){
            dat.houseAndCS += summ;
        }else if(medicine){
            dat.medicine += summ;
        }else if(optionalExpenses){
            dat.optionalExpenses += summ;
        }else if(transport){
            dat.transport += summ;
        }

        UserData.dataList.add(dat);
        UserData.cnt += 1;
        UserData.spend += summ;

        FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("data").child(Long.toString(longTime))
                .setValue(dateInfo);

        Toast.makeText(this, "Данные загружены.", Toast.LENGTH_LONG).show();

    }

}
