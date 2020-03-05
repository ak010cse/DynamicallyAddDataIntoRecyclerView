package com.example.dynamicallyrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;

public class DynamicViewActivity extends AppCompatActivity {
    Button btnDisplay;
    ImageButton btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_view);
        btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        MyLayoutOperation.add(this, btnAdd);
        MyLayoutOperation.display(this, btnDisplay);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
