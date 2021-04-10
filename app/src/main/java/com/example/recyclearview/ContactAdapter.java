package com.example.recyclearview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private static final String TAG = "ContactAdapter";
    private ArrayList<String> contacts = new ArrayList<>();

    private ItemEventListener itemEventListener;

    public ContactAdapter(ItemEventListener itemEventListener) {
        this.itemEventListener = itemEventListener;
        contacts.add("Mikel Jakson");
        contacts.add("Leo Dikaprio");
        contacts.add("Tom Hanks");
        contacts.add("Matew Mackanehy");
        contacts.add("Scarlet Johanson");
        contacts.add("Brad Pitt");
        contacts.add("Johney  Depp");
        contacts.add("Amy Adams");
        contacts.add("Tom Hardi");
        contacts.add("Pishawa Jakson");
        contacts.add("james Cordan");
        contacts.add("Lili Rose");
        contacts.add("Edward Norton");
        contacts.add("Billy Jakson");
        contacts.add("Heath Ledger");
    }

    public void addNewContacts(String fullName) {
        contacts.add(0, fullName);
        notifyItemInserted(0);
    }

    public void updateContact(String fullName, int position) {
        contacts.set(position, fullName);
        notifyItemChanged(position);

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contact, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: position =>" + position);
        holder.bindContact(contacts.get(position));

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_firstCharacter, txt_fullName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_firstCharacter = itemView.findViewById(R.id.txt_first_character);
            txt_fullName = itemView.findViewById(R.id.txt_fullname);

        }

        public void bindContact(String fullName) {
            txt_fullName.setText(fullName);
            txt_firstCharacter.setText(fullName.substring(0, 1));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemEventListener.onItemSelected(fullName, getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    contacts.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());

                    return false;
                }
            });
        }


    }

    public interface ItemEventListener {
        void onItemSelected(String fullName, int position);
    }

}
