package com.hnucm.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MessageFragment messageFragment = new MessageFragment();
    ContactFragment contactFragment = new ContactFragment();
    DontaiFragment dontaiFragment = new DontaiFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout2);
        ConstraintLayout constraintLayout1 = findViewById(R.id.constraintLayout);
        ConstraintLayout constraintLayout2 = findViewById(R.id.constraintLayout3);
        constraintLayout.setSelected(true);
        constraintLayout1.setSelected(false);
        constraintLayout2.setSelected(false);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,messageFragment).commit();
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,messageFragment).commit();
                constraintLayout.setSelected(true);
                constraintLayout1.setSelected(false);
                constraintLayout2.setSelected(false);
            }
        });


        constraintLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,contactFragment).commit();
                constraintLayout.setSelected(false);
                constraintLayout1.setSelected(true);
                constraintLayout2.setSelected(false);
            }
        });


        constraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setSelected(false);
                constraintLayout1.setSelected(false);
                constraintLayout2.setSelected(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,dontaiFragment).commit();
            }
        });

    }
}