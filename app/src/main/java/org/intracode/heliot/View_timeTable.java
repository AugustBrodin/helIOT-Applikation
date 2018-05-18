package org.intracode.heliot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class View_timeTable extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    UserInformation_FB user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_time_table);

        user = new UserInformation_FB();

        listView = (ListView) findViewById(R.id.viewTimeTable_listView);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Bevattningar");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.bevattningar_info, R.id.bevattningInfo_bevattningarInfo, list);


        databaseReference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {

                                user = ds.getValue(UserInformation_FB.class);
                                int plant1Comperer = Integer.parseInt(user.getPlant().toString());
                                if(plant1Comperer == 3) {
                                    list.add("Planta nr: " + user.getPlant().toString() + "\n  Vattenmängd: " + user.getWater_amount().toString() + "\n    Tid: " + user.getTime_hour().toString() + ":" + user.getTime_min().toString());
                                }
                                }
                            listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent updateAndDelete_FB = new Intent(View_timeTable.this, updateAndDelete_FB.class);
                        UserInformation_FB s = (UserInformation_FB) adapterView.getItemAtPosition(i);
                        updateAndDelete_FB.putExtra("account_comperer", s.getAccount_comperer());
                        updateAndDelete_FB.putExtra("order_number", s.getOrder_number());
                        updateAndDelete_FB.putExtra("plant", s.getPlant());
                        updateAndDelete_FB.putExtra("water_amount", s.getWater_amount());
                        updateAndDelete_FB.putExtra("time_hour", s.getTime_hour());
                        updateAndDelete_FB.putExtra("time_min", s.getTime_min());
                        startActivity(updateAndDelete_FB);

                    }
                }
        );


        /**

         databaseReference.addChildEventListener(
         new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            for(DataSnapshot ds: dataSnapshot.getChildren()) {

                user = ds.getValue(UserInformation_FB.class);

                list.add("Planta nr: " + user.getPlant().toString() + "  Vattenmängd: " + user.getWater_amount().toString() + "    Tid: " + user.getTime_hour().toString() + ":" + user.getTime_min().toString());

                adapter.notifyDataSetChanged();

            }
            listView.setAdapter(adapter);

        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

            for(DataSnapshot ds: dataSnapshot.getChildren()) {

                user = ds.getValue(UserInformation_FB.class);

                list.remove("Planta nr: " + user.getPlant().toString() + "  Vattenmängd: " + user.getWater_amount().toString() + "    Tid: " + user.getTime_hour().toString() + ":" + user.getTime_min().toString());

                adapter.notifyDataSetChanged();

            }
            listView.setAdapter(adapter);

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

    }

}