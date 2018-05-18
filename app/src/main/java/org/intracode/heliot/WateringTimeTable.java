package org.intracode.heliot;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WateringTimeTable extends AppCompatActivity {
    DataBaseHandler wateringDB;

    EditText editNewPlant2,editNewWaterAmount2,editTimeHour2,editTimeMin2, editOrdernumber2;

    Button viewTable_button;
    Button updateTable_button;
    Button eraseTable_button;

    Button viewTable_button_FB;
    Button updateTable_button_FB;
    Button eraseTable_button_FB;

    ListView listView;

    FirebaseDatabase database;

    DatabaseReference databaseReference;

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    UserInformation_FB user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watering_time_table);
        wateringDB = new DataBaseHandler(this);

        viewTable_button = (Button)findViewById(R.id.WateringTimeTable_viewAll_button);
        updateTable_button = (Button)findViewById(R.id.WateringTimeTable_updateTable_button);
        eraseTable_button = (Button)findViewById(R.id.WateringTimeTable_eraseTable_button);

        editNewPlant2 = (EditText)findViewById(R.id.wateringTable_editText_newPlant);
        editNewWaterAmount2 = (EditText)findViewById(R.id.wateringTable_editText_waterAmount);
        editTimeHour2 = (EditText)findViewById(R.id.wateringTable_editText_timeHour);
        editTimeMin2 = (EditText)findViewById(R.id.wateringTable_editText_timeMin);
        editOrdernumber2 = (EditText)findViewById(R.id.wateringTable_editText_Ordernumber);

        user = new UserInformation_FB();

        listView = (ListView)findViewById(R.id.wateringTable_ListView);

        database = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Shitsauce");

        listView.setAdapter(adapter);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);


        /**
        databaseReference.addChildEventListener(
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

         */


        adapter = new ArrayAdapter<String>(this, R.layout.bevattningar_info, R.id.bevattningar_db_fb_bevattningarInfo, arrayList);
        databaseReference.addValueEventListener(
                new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    user = ds.getValue(UserInformation_FB.class);
                    arrayList.add(user.getPlant().toString() + "  " + user.getWater_amount().toString() + "   " + user.getTime_hour().toString() + ":" + user.getTime_min().toString());

                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }
        );


        viewTable();
        UpdateData();
        DeleteData();
    }

    public void UpdateData(){

        updateTable_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdated = wateringDB.updateData(
                                editOrdernumber2.getText().toString(),
                                editNewPlant2.getText().toString(),
                                editNewWaterAmount2.getText().toString(),
                                editTimeHour2.getText().toString(),
                                editTimeMin2.getText().toString());
                        if(isUpdated == true)
                                Toast.makeText(WateringTimeTable.this, "Förfrågan tillagd", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(WateringTimeTable.this, "Gick inte att lägga till", Toast.LENGTH_LONG).show();
                    }
                }
        );

    }

    public void DeleteData(){
        eraseTable_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRows = wateringDB.deleteData(editOrdernumber2.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(WateringTimeTable.this, "Bevattning borttagen", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(WateringTimeTable.this, "Kunde inte hitta data", Toast.LENGTH_LONG).show();
                    }
                }

        );

    }

    public void viewTable(){
        viewTable_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = wateringDB.getAllData();
                        if(res.getCount() == 0){
                            showMessage("Fel hittat", "Inga bevattning hittade");
                            // show message
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Ordernummer: " + res.getString(0) + "\t");
                            buffer.append("Planta nr: " + res.getString(1) + "\t");
                            buffer.append("Vattenmängd: " + res.getString(2) + "\t");
                            buffer.append("Tid: " + res.getString(3) + ":" + res.getString(4) + "\t\n\n");
                        }
                        // Show all data
                        showMessage("Bevattningar", buffer.toString());
                    }
                }

        );

    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
