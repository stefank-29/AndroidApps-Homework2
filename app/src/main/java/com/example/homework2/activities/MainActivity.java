package com.example.homework2.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.homework2.R;
import com.example.homework2.recycler.adapter.ContactAdapter;
import com.example.homework2.recycler.differ.ContactDiffItemCallback;
import com.example.homework2.viewmodels.RecyclerViewModel;

public class MainActivity extends AppCompatActivity {

    // View comps
    private RecyclerView recyclerView;
    private EditText search;
    private EditText addEditText;
    private Button addBtn;

    private RecyclerViewModel recyclerViewModel;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // instanciranje view modela
        recyclerViewModel = new ViewModelProvider(this).get(RecyclerViewModel.class);
        init();
    }

    public void init() {
        initView();
        initListeners();
        initObservers();
        initRecycler();
    }

    public void initView() {
        recyclerView = findViewById(R.id.contactsRv);
        search = findViewById(R.id.searchEt);
        addEditText = findViewById(R.id.addEt);
        addBtn = findViewById(R.id.addBtn);
    }

    public void initListeners() {

    }

    // punjenje podataka u recyclerView
    public void initObservers() {
        recyclerViewModel.getContacts().observe(this, contacts -> { // kad dodju contacti prosledim to adapteru
            contactAdapter.submitList(contacts);
        });
    }

    // recyvler view pravljenje
    public void initRecycler() {
        contactAdapter = new ContactAdapter(new ContactDiffItemCallback());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(contactAdapter);
    }
}