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


public class ViewTimeTable_Planta3 extends AppCompatActivity {

    ListView listView;
    FirebaseListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_time_table__planta3);

        listView = (ListView) findViewById(R.id.viewTimeTable_Planta3);
        Query query = FirebaseDatabase.getInstance().getReference().child("Bevattningar").child("Plant3");

        FirebaseListOptions<UserInformation_FB_Plant3> options = new FirebaseListOptions.Builder<UserInformation_FB_Plant3>()
                .setLayout(R.layout.bevattningar_info_ui_plant3)
                .setQuery(query, UserInformation_FB_Plant3.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {

                TextView Plant = v.findViewById(R.id.bevattningar_info_ui_plant_text3);
                TextView Water_amount = v.findViewById(R.id.bevattningar_info_ui_waterAmount_text3);
                TextView Time_hour = v.findViewById(R.id.bevattningar_info_ui_timeHour_text3);
                TextView Time_min = v.findViewById(R.id.bevattningar_info_ui_timeMin_text3);
                TextView Order_number = v.findViewById(R.id.bevattningar_info_ui_orderNumber_text3);
                TextView Account_comperer = v.findViewById(R.id.bevattningar_info_ui_accountComperer_text3);

                UserInformation_FB_Plant3 bevattning = (UserInformation_FB_Plant3) model;

                Plant.setText("Planta nr: " + bevattning.getPlant3().toString());
                Water_amount.setText("Vattenmängd: " + bevattning.getWater_amount_plant3().toString());
                Time_hour.setText("Timslag: " + bevattning.getTime_hour_plant3().toString());
                Time_min.setText("Minutslag: " + bevattning.getTime_min_plant3().toString());
                Order_number.setText("Ordernummer: " + bevattning.getOrder_number_plant3().toString());
                Account_comperer.setText("ID verifikation: " + bevattning.getAccount_comperer_plant3().toString());
            }
        };
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent updateAndDelete_FB_Plant3 = new Intent(ViewTimeTable_Planta3.this, updateAndDelete_FB_Plant3.class);
                        UserInformation_FB_Plant3 s = (UserInformation_FB_Plant3) adapterView.getItemAtPosition(i);
                        updateAndDelete_FB_Plant3.putExtra("account_comperer_plant3", s.getAccount_comperer_plant3());
                        updateAndDelete_FB_Plant3.putExtra("order_number_plant3", s.getOrder_number_plant3());
                        updateAndDelete_FB_Plant3.putExtra("plant3", s.getPlant3());
                        updateAndDelete_FB_Plant3.putExtra("water_amount_plant3", s.getWater_amount_plant3());
                        updateAndDelete_FB_Plant3.putExtra("time_hour_plant3", s.getTime_hour_plant3());
                        updateAndDelete_FB_Plant3.putExtra("time_min_plant3", s.getTime_min_plant3());
                        startActivity(updateAndDelete_FB_Plant3);

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
