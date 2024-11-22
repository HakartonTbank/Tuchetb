package com.example.tuchet;

import static android.app.PendingIntent.getActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    //йоу

    ImageView homeIV;
    ImageView profileIV;
    ImageView assistentIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("my_logs", "2");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        replaceFragment(new FragmentHome());
        homeIV = findViewById(R.id.home_button);
        profileIV = findViewById(R.id.profile_button);
        assistentIV = findViewById(R.id.assistent_button);




    }


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = this.getSupportFragmentManager(); //getActivity()
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.place_holder, fragment);
        fragmentTransaction.commit();
    }


}


