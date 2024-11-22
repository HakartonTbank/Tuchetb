package com.example.tuchet;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //йоу


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("my_logs", "2");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    /*
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.place_holder, fragment);
        fragmentTransaction.commit();

     */
}


