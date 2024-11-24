package com.example.tuchet;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.Date;

public class FragmentAssistent extends Fragment {

    Boolean flag1 = false;
    Boolean flag2 = false;
    Date date1, date2;
    Calendar dateAndTime1 = Calendar.getInstance();
    Calendar dateAndTime2 = Calendar.getInstance();
    TextView textDates, textSpend, textIncome;
    Button datePicker1, datePicker2, enterDates;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_assist , container, false);
        initialDateTime();
        textDates = v.findViewById(R.id.text_dates);
        textSpend = v.findViewById(R.id.textSpend);
        textIncome = v.findViewById(R.id.textIncome);
        datePicker1 = v.findViewById(R.id.first_date_picker);
        datePicker2 = v.findViewById(R.id.second_date_picker);
        enterDates = v.findViewById(R.id.enter_date_button);
        textSpend.setVisibility(View.INVISIBLE);
        textIncome.setVisibility(View.INVISIBLE);


        datePicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("my_logs", "setDate");
                new DatePickerDialog(requireContext(), d1,
                        dateAndTime1.get(Calendar.YEAR),
                        dateAndTime1.get(Calendar.MONTH),
                        dateAndTime1.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });

        datePicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(requireContext(), d2,
                        dateAndTime2.get(Calendar.YEAR),
                        dateAndTime2.get(Calendar.MONTH),
                        dateAndTime2.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });

        enterDates.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onClick(View v) {
                if (flag1 && flag2) {
                    textDates.setText("Расходы c " + DateUtils.formatDateTime(requireActivity(),
                            dateAndTime1.getTimeInMillis(),
                            DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR) + "\nпо " + DateUtils.formatDateTime(requireActivity(),
                            dateAndTime2.getTimeInMillis(),
                            DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR) + ".");
                    long unix1 = (date1.getTime() / 86400000);
                    long unix2 = (date2.getTime() / 86400000);

                    DataOperation sum = new DataOperation();

                    for (int i = 0; i < UserData.dataList.size(); i++) {
                        DataOperation d = UserData.dataList.get(i);
                        if ((d.unixDate / 86400000 >= unix1) && (d.unixDate / 86400000 <= unix2)) {
                            sum.food += d.food;
                            sum.cash += d.cash;
                            sum.nonCash += d.nonCash;
                            sum.wage += d.wage;
                            sum.other += d.other;
                            sum.scholarship += d.scholarship;
                            sum.houseAndCS += d.houseAndCS;
                            sum.medicine += d.medicine;
                            sum.optionalExpenses += d.optionalExpenses;
                            sum.transport += d.transport;
                        }
                    }

                    Double q = (double) sum.cash;
                    long totalSpend = sum.food + sum.houseAndCS + sum.medicine + sum.optionalExpenses + sum.transport;
                    long totalIncome = sum.cash + sum.nonCash + sum.wage + sum.other + sum.scholarship;



                    textSpend.setText("Расходы:\nПитание - " + String.format("%.3f",((double) sum.food / totalSpend) * 100) + "%\nТранспорт - " + String.format("%.3f",
                            ((double) sum.transport / totalSpend) * 100) + "%\nЗдоровье - " + String.format("%.3f",((double) sum.medicine / totalSpend) * 100) + "%\nОплата налогов и счетов - " + String.format("%.3f",
                            ((double) sum.houseAndCS / totalSpend) * 100) + "%\nОпциональные траты - " + String.format("%.3f",((double) sum.optionalExpenses / totalSpend) * 100) +"%");

                    textIncome.setText("Доходы:\nНаличные - " + String.format("%.3f",((double) sum.cash / totalSpend) * 100) + "%\nБезналичные - " + String.format("%.3f",
                            ((double) sum.nonCash / totalSpend) * 100) + "%\nСтипендия - " + String.format("%.3f",((double) sum.scholarship / totalSpend) * 100) + "%\nЗароботная плата - " + String.format("%.3f",
                            ((double) sum.wage / totalSpend) * 100) + "%\nДругое - " + String.format("%.3f",((double) sum.other / totalSpend) * 100) + "%");

                    textSpend.setVisibility(View.VISIBLE);
                    textIncome.setVisibility(View.VISIBLE);

                }
            }
        });


        return v;
    }


    private void initialDateTime() {
        date1 = dateAndTime1.getTime();
        date2 = dateAndTime2.getTime();
    }



    DatePickerDialog.OnDateSetListener d1=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Log.d("my_logs", "date picker1");
            dateAndTime1.set(Calendar.YEAR, year);
            dateAndTime1.set(Calendar.MONTH, monthOfYear);
            dateAndTime1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            date1 = dateAndTime1.getTime();
            flag1 = true;
            Log.d("my_logs", "date picker1");
        }

    };

    DatePickerDialog.OnDateSetListener d2=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Log.d("my_logs", "date picker2");
            dateAndTime2.set(Calendar.YEAR, year);
            dateAndTime2.set(Calendar.MONTH, monthOfYear);
            dateAndTime2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            date2 = dateAndTime2.getTime();
            flag2 = true;
            Log.d("my_logs", "date picker2");
        }
    };

}
