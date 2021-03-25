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

public class ContactAdapter extends ListAdapter<Contact, ContactAdapter.ViewHolder> {

    // Diff item callback za animacije
    protected ContactAdapter(@NonNull DiffUtil.ItemCallback<Contact> diffCallback) {
        super(diffCallback);
    }

    // kreira VH
    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create new viewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false); // kreiranje view-a, gde ga zakacim, da li odmah da ga zakacim na root parenta
        return new ViewHolder(view, parent.getContext()); // saljem context zbog Glide-a
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

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
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
