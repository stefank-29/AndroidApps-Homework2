package com.example.homework2.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.homework2.R;

public class MainActivity extends AppCompatActivity {

    // View comps
    private RecyclerView recyclerView;
    private EditText search;
    private EditText addNew;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        initView();
        initListeners();
        initObservers();
        initRecycler();
    }

    public void initView() {

    }

    public void initListeners(){

    }
    public void initObservers(){

    }
    public void initRecycler() {

    }
}