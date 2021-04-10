package com.example.recyclearview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements ContactAdapter.ItemEventListener {

    ContactAdapter adapter;
    EditText et_fullName;
    ImageView btn_addContact;
    private int editingItemPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new ContactAdapter(this);
        recyclerView.setAdapter(adapter);

        et_fullName = findViewById(R.id.editText_main);
        btn_addContact = findViewById(R.id.img_add);

        btn_addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_fullName.length() > 0) {

                    if (editingItemPosition > -1){
                        adapter.updateContact(et_fullName.getText().toString(),editingItemPosition);
                        editingItemPosition = -1;
                        btn_addContact.setImageResource(R.drawable.ic_add);
                    }
                    adapter.addNewContacts(et_fullName.getText().toString());
                    recyclerView.scrollToPosition(0);

                }
                et_fullName.setText("");
            }
        });



    }

    @Override
    public void onItemSelected(String fullName, int position) {
        editingItemPosition = position;
        et_fullName.setText(fullName);
        btn_addContact.setImageResource(R.drawable.ic_done);
    }
}