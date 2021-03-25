package com.example.homework2.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.homework2.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewModel extends ViewModel {
    public static int counter = 100;

    private final MutableLiveData<List<Contact>> contacts = new MutableLiveData<>();
    private final ArrayList<Contact> contactList = new ArrayList<>(); // lokalna baza

    public RecyclerViewModel() {
        for (int i = 1; i <= 100; i++) {
            Contact contact = new Contact(i, "https://static.wikia.nocookie.net/meme/images/c/ce/24058219.png", "Stefan", "Karaferovic", "063-519-1991", "stefan@gmail.com");
            contactList.add(contact);
        }
        ArrayList<Contact> listToSubmit = new ArrayList<>(contactList); // da se izbegne bag
        contacts.setValue(listToSubmit);
    }

    public MutableLiveData<List<Contact>> getContacts() {
        return contacts;
    }
}
