package com.example.tuchet;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        textSpend.setVisibility(View.INVISIBLE);
        textIncome.setVisibility(View.INVISIBLE);

        return v;
    }


    private void initialDateTime() {
        date1 = dateAndTime1.getTime();
        date2 = dateAndTime2.getTime();
    }

    @SuppressLint("SetTextI18n")
    public void onEnterDatesClick(View v){
        /*
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

            long totalSpend = sum.food + sum.houseAndCS + sum.medicine + sum.optionalExpenses + sum.transport;
            long totalIncome = sum.cash + sum.nonCash + sum.wage + sum.other + sum.scholarship;

            textSpend.setText("Расходы:\nПитание - " + Long.toString((sum.food / totalSpend) * 100) + "\nТранспорт - " + Long.toString(
                    (sum.transport / totalSpend) * 100) + "\nЗдоровье - " + Long.toString((sum.medicine / totalSpend) * 100) + "\nОплата налогов и счетов - " + Long.toString(
                    (sum.houseAndCS / totalSpend) * 100) + "\nОпциональные траты - " + Long.toString((sum.optionalExpenses / totalSpend) * 100));

            textIncome.setText("Доходы:\nНаличные - " + Long.toString((sum.cash / totalIncome) * 100) + "\nБезналичные - " + Long.toString(
                    (sum.nonCash / totalIncome) * 100) + "\nСтипендия - " + Long.toString((sum.scholarship / totalIncome) * 100) + "\nЗароботная плата - " + Long.toString(
                    (sum.wage / totalIncome) * 100) + "\nДругое - " + Long.toString((sum.other / totalIncome) * 100));

            textSpend.setVisibility(View.VISIBLE);
            textIncome.setVisibility(View.VISIBLE);

        }

         */
    }


    public void setDate1(View v) {
        /*
        new DatePickerDialog(getContext(), d1,
                dateAndTime1.get(Calendar.YEAR),
                dateAndTime1.get(Calendar.MONTH),
                dateAndTime1.get(Calendar.DAY_OF_MONTH))
                .show();

         */
    }

    public void setDate2(View v) {
        /*
        new DatePickerDialog(getContext(), d2,
                dateAndTime2.get(Calendar.YEAR),
                dateAndTime2.get(Calendar.MONTH),
                dateAndTime2.get(Calendar.DAY_OF_MONTH))
                .show();

         */
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
