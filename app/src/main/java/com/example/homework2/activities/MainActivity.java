package com.example.homework2.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

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
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                recyclerViewModel.filterContacts(s.toString());
                // animacija zbog DiffUtil item callback
                if (s.toString().equals("")) {
                    addBtn.setEnabled(true);
                } else {
                    addBtn.setEnabled(false);
                }
            }
        });

        addBtn.setOnClickListener(v -> {
            String name = addEditText.getText().toString();
            if (name.equals("")) { // ako je prazan editText nista
                return;
            }
            recyclerViewModel.addContact("https://images-na.ssl-images-amazon.com/images/I/41cOeBbaV7L._AC_.jpg", name, name + "vic", "063-286-1389", name + "@gmail.com");
            addEditText.clearFocus();
            addEditText.setText("");
        });

    }

    // punjenje podataka u recyclerView
    public void initObservers() {
        recyclerViewModel.getContacts().observe(this, contacts -> { // kad dodju contacti prosledim to adapteru
            contactAdapter.submitList(contacts);                           // (na pocetku kad instanciram u konstruktoru ili dodam novi)
        });
    }

    // recyvler view pravljenje
    public void initRecycler() {
        contactAdapter = new ContactAdapter(new ContactDiffItemCallback(), contact -> {
            recyclerViewModel.deleteContact(contact.getId());
            return null;
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(contactAdapter);
    }
}