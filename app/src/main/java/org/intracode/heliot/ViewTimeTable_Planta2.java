package org.intracode.heliot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class ViewTimeTable_Planta2 extends AppCompatActivity {

    ListView listView;
    FirebaseListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_time_table__planta2);

        listView = (ListView) findViewById(R.id.viewTimeTable_Planta2);
        Query query = FirebaseDatabase.getInstance().getReference().child("Bevattningar").child("Plant2");

        FirebaseListOptions<UserInformation_FB_Plant2> options = new FirebaseListOptions.Builder<UserInformation_FB_Plant2>()
                .setLayout(R.layout.bevattningar_info_ui_plant2)
                .setQuery(query, UserInformation_FB_Plant2.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {

                TextView Plant = v.findViewById(R.id.bevattningar_info_ui_plant_text2);
                TextView Water_amount = v.findViewById(R.id.bevattningar_info_ui_waterAmount_text2);
                TextView Time_hour = v.findViewById(R.id.bevattningar_info_ui_timeHour_text2);
                TextView Time_min = v.findViewById(R.id.bevattningar_info_ui_timeMin_text2);
                TextView Order_number = v.findViewById(R.id.bevattningar_info_ui_orderNumber_text2);
                TextView Account_comperer = v.findViewById(R.id.bevattningar_info_ui_accountComperer_text2);

                UserInformation_FB_Plant2 bevattning = (UserInformation_FB_Plant2) model;

                Plant.setText("Planta nr: " + bevattning.getPlant2().toString());
                Water_amount.setText("Vattenmängd: " + bevattning.getWater_amount_plant2().toString());
                Time_hour.setText("Timslag: " + bevattning.getTime_hour_plant2().toString());
                Time_min.setText("Minutslag: " + bevattning.getTime_min_plant2().toString());
                Order_number.setText("Ordernummer: " + bevattning.getOrder_number_plant2().toString());
                Account_comperer.setText("ID verifikation: " + bevattning.getAccount_comperer_plant2().toString());
            }
        };
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent updateAndDelete_FB_Plant2 = new Intent(ViewTimeTable_Planta2.this, updateAndDelete_FB_Plant2.class);
                        UserInformation_FB_Plant2 s = (UserInformation_FB_Plant2) adapterView.getItemAtPosition(i);
                        updateAndDelete_FB_Plant2.putExtra("account_comperer_plant2", s.getAccount_comperer_plant2());
                        updateAndDelete_FB_Plant2.putExtra("order_number_plant2", s.getOrder_number_plant2());
                        updateAndDelete_FB_Plant2.putExtra("plant2", s.getPlant2());
                        updateAndDelete_FB_Plant2.putExtra("water_amount_plant2", s.getWater_amount_plant2());
                        updateAndDelete_FB_Plant2.putExtra("time_hour_plant2", s.getTime_hour_plant2());
                        updateAndDelete_FB_Plant2.putExtra("time_min_plant2", s.getTime_min_plant2());
                        startActivity(updateAndDelete_FB_Plant2);

                    }
                }
        );
    }

    @Override
    protected void onStart () {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop () {
        super.onStop();
        adapter.stopListening();
    }
}
