package com.example.homework2.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.homework2.models.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecyclerViewModel extends ViewModel {
    public static int counter = 100;

    private final MutableLiveData<List<Contact>> contacts = new MutableLiveData<>(); // lista koju posmatra observer
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

    // activity ne radi sa podacima vec kontaktira view model
    public void filterContacts(String filter) {
        List<Contact> filteredContacts = contactList.stream().filter(contact -> contact.getName().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
        contacts.setValue(filteredContacts); // okine se observer i prosledi adapteru contacte
    }

    public void addContact(String image, String name, String surname, String number, String email) {
        Contact contact = new Contact(counter++, image, name, surname, number, email);
        contactList.add(contact);
        // zbog baga ovako
        ArrayList<Contact> listToSubmit = new ArrayList<>(contactList);
        contacts.setValue(listToSubmit);
    }
}
