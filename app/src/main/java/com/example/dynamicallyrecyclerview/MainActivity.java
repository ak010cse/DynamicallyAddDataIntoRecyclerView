package com.example.dynamicallyrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RemoveClickListner {
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Button btnAddItem, otherView;
    ArrayList<RecyclerData> myList = new ArrayList<>();
    EditText etTitle, etDescription;
    String title = "", description = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        //*********** reverse order data show********//

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        //*********** reverse order data show********//

        mRecyclerView.setLayoutManager(layoutManager);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        myList = new ArrayList<>();

        btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = etTitle.getText().toString();
                description = etDescription.getText().toString();
                if (title.matches("")) {
                    etTitle.requestFocus();
                    etTitle.setError("You did not enter a Title");
                    return;
                }
                if (description.matches("")) {
                    etDescription.requestFocus();
                    etDescription.setError("You did not enter a description");
                    return;
                }

                RecyclerData mLog = new RecyclerData();
                mLog.setTitle(title);
                mLog.setDescription(description);
                myList.add(mLog);
                mRecyclerAdapter = new RecyclerAdapter(myList, MainActivity.this);
                mRecyclerView.setAdapter(mRecyclerAdapter);
                mRecyclerAdapter.notifyData(myList);
                etTitle.setText("");
                etDescription.setText("");
                etTitle.requestFocus();
            }
        });

        otherView = findViewById(R.id.otherView);

        otherView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DynamicViewActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void OnRemoveClick(int index) {
        myList.remove(index);
        mRecyclerAdapter.notifyData(myList);
    }
}
