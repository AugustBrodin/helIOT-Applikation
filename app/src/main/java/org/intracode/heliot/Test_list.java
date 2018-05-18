package org.intracode.heliot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Test_list extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private Button btnAdd;
    private EditText etText;
    private ListView listView;

    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);

        mDatabase = FirebaseDatabase.getInstance().getReference("doodoo");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        etText = (EditText)findViewById(R.id.etDatabase);
        listView = (ListView)findViewById(R.id.database_list_view);

        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDatabase.push().setValue(etText.getText().toString());
                    }
                }
        );

        mDatabase.addChildEventListener(
                new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        String string = dataSnapshot.getValue(String.class);

                        arrayList.add(string);

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                        String string = dataSnapshot.getValue(String.class);

                        arrayList.remove(string);

                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

    }
}
