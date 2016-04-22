package com.example.zhaoting.myapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.zhaoting.myapplication.R;
import com.example.zhaoting.utils.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.getInstance().init(this);
        Toast.makeText(this,String.valueOf(Utils.getInstance().getScreenWidth()),Toast.LENGTH_SHORT).show();
    }
}
