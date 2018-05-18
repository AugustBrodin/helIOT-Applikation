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


public class View_timeTableUI extends AppCompatActivity {

    ListView listView;
    FirebaseListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_time_table_ui);

        listView = (ListView)findViewById(R.id.viewTimeTable_UI);
        Query query = FirebaseDatabase.getInstance().getReference().child("Bevattningar");

        FirebaseListOptions<UserInformation_FB> options = new FirebaseListOptions.Builder<UserInformation_FB>()
                .setLayout(R.layout.bevattningar_info_ui)
                .setQuery(query, UserInformation_FB.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {

                TextView Plant = v.findViewById(R.id.bevattningar_info_ui_plant_text);
                TextView Water_amount = v.findViewById(R.id.bevattningar_info_ui_waterAmount_text);
                TextView Time_hour = v.findViewById(R.id.bevattningar_info_ui_timeHour_text);
                TextView Time_min = v.findViewById(R.id.bevattningar_info_ui_timeMin_text);
                TextView Order_number = v.findViewById(R.id.bevattningar_info_ui_orderNumber_text);
                TextView Account_comperer = v.findViewById(R.id.bevattningar_info_ui_accountComperer_text);

                UserInformation_FB bevattning = (UserInformation_FB) model;

                Plant.setText("Planta nr: " + bevattning.getPlant().toString());
                Water_amount.setText("Vattenmängd: " + bevattning.getWater_amount().toString());
                Time_hour.setText("Timslag: " + bevattning.getTime_hour().toString());
                Time_min.setText("Minutslag: " + bevattning.getTime_min().toString());
                Order_number.setText("Ordernummer: " + bevattning.getOrder_number().toString());
                Account_comperer.setText("ID verifikation: " + bevattning.getAccount_comperer().toString());
            }
        };
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent updateAndDelete_FB = new Intent(View_timeTableUI.this, updateAndDelete_FB.class);
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
