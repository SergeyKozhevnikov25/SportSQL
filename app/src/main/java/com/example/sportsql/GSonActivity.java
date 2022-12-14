package com.example.sportsql;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GSonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gson_activity);
        TextView tvGson = findViewById(R.id.tv_gson);
        tvGson.setText(getIntent().getStringExtra("json"));
    }
}
