package com.example.tuchet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentHome extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Button incomeButton = v.findViewById(R.id.income_button);
        Button spendButton = v.findViewById(R.id.spend_button);
        TextView textIncome = v.findViewById(R.id.text_income);
        TextView textSpend = v.findViewById(R.id.text_spend);
        TextView currentBalance = v.findViewById(R.id.current_balance);


        if (UserData.getCnt() != 0){
            textIncome.setText("Доходы: " + Long.toString(UserData.income));
            textSpend.setText("Расходы: " + Long.toString(UserData.spend));
            currentBalance.setText(Long.toString(UserData.income - UserData.spend));
        }else{
            textIncome.setText("Доходы: 0");
            textSpend.setText("Расходы: 0");
            currentBalance.setText("0");
        }



        incomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EnterIncomeData.class);
                startActivity(intent);
            }
        });

        spendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EnterSpendData.class);
                startActivity(intent);
            }
        });

        return v;
    }

}
