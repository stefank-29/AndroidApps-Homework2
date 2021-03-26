package com.example.homework2.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homework2.R;
import com.example.homework2.models.Contact;

import java.util.function.Function;

public class ContactAdapter extends ListAdapter<Contact, ContactAdapter.ViewHolder> {

    private Function<Contact, Void> onContactClicked;

    // Diff item callback za animacije
    public ContactAdapter(@NonNull DiffUtil.ItemCallback<Contact> diffCallback, Function<Contact, Void> onContactClicked) {
        super(diffCallback);
        this.onContactClicked = onContactClicked;
    }

    // kreira VH
    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create new viewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false); // kreiranje view-a, gde ga zakacim, da li odmah da ga zakacim na root parenta
        return new ViewHolder(view, parent.getContext(), position -> { // saljem context zbog Glide-a
            Contact contact = getItem(position);
            onContactClicked.apply(contact);
            return null; // vracam contact activitiju
        });
    }

    // VH se binduje
    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) { // position pozicija u listi
        Contact contact = getItem(position);
        holder.bind(contact); // bindujem na VH
    }

    // ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Context context;

        public ViewHolder(@NonNull View itemView, Context context, Function<Integer, Void> onItemClicked) {
            super(itemView);
            this.context = context;
            itemView.findViewById(R.id.deleteBtn).setOnClickListener(v -> {
                onItemClicked.apply(getAdapterPosition()); // vracam poziciju u listi adapteru
            });
        }

        // bind - setovanje podataka iz modela na car_list_item
        public void bind(Contact contact) {
            ImageView imageView = itemView.findViewById(R.id.contactImage); // mora itemView jer nisam u activitiju
            Glide.with(context).load(contact.getImage()).circleCrop().into(imageView);
            ((TextView) itemView.findViewById(R.id.nameTv)).setText(contact.getName());
            ((TextView) itemView.findViewById(R.id.surnameTv)).setText(contact.getSurname());
            ((TextView) itemView.findViewById(R.id.numberTv)).setText(contact.getNumber());
            ((TextView) itemView.findViewById(R.id.emailTv)).setText(contact.getEmail());

        }
    }


}
