package com.example.homework2.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.homework2.models.Contact;

public class ContactDiffItemCallback extends DiffUtil.ItemCallback<Contact> {

    // ako vrati false poziva se areContentsTheSame
    @Override
    public boolean areItemsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Contact oldItem, @NonNull Contact newItem) {
        // ako se bar jedna inforacija ne poklapa desava se animacija za izmenu
        return oldItem.getImage().equals(newItem.getImage())
                && oldItem.getName().equals(newItem.getName())
                && oldItem.getSurname().equals(newItem.getSurname())
                && oldItem.getNumber().equals(newItem.getNumber())
                && oldItem.getEmail().equals(newItem.getEmail());
    }
}
