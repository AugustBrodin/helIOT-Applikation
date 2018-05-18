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


public class ViewTimeTable_Planta1 extends AppCompatActivity {

    ListView listView;
    FirebaseListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_time_table__planta1);

        listView = (ListView) findViewById(R.id.viewTimeTable_Planta1);
        Query query = FirebaseDatabase.getInstance().getReference().child("Bevattningar").child("Plant1");

        FirebaseListOptions<UserInformation_FB_Plant1> options = new FirebaseListOptions.Builder<UserInformation_FB_Plant1>()
                .setLayout(R.layout.bevattningar_info_ui_plant1)
                .setQuery(query, UserInformation_FB_Plant1.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {

                TextView Plant = v.findViewById(R.id.bevattningar_info_ui_plant_text1);
                TextView Water_amount = v.findViewById(R.id.bevattningar_info_ui_waterAmount_text1);
                TextView Time_hour = v.findViewById(R.id.bevattningar_info_ui_timeHour_text1);
                TextView Time_min = v.findViewById(R.id.bevattningar_info_ui_timeMin_text1);
                TextView Order_number = v.findViewById(R.id.bevattningar_info_ui_orderNumber_text1);
                TextView Account_comperer = v.findViewById(R.id.bevattningar_info_ui_accountComperer_text1);

                UserInformation_FB_Plant1 bevattning = (UserInformation_FB_Plant1) model;

                    Plant.setText("Planta nr: " + bevattning.getPlant1().toString());
                    Water_amount.setText("Vattenmängd: " + bevattning.getWater_amount_plant1().toString());
                    Time_hour.setText("Timslag: " + bevattning.getTime_hour_plant1().toString());
                    Time_min.setText("Minutslag: " + bevattning.getTime_min_plant1().toString());
                    Order_number.setText("Ordernummer: " + bevattning.getOrder_number_plant1().toString());
                    Account_comperer.setText("ID verifikation: " + bevattning.getAccount_comperer_plant1().toString());
                }
        };
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent updateAndDelete_FB_Plant1 = new Intent(ViewTimeTable_Planta1.this, updateAndDelete_FB_Plant1.class);
                            UserInformation_FB_Plant1 s = (UserInformation_FB_Plant1) adapterView.getItemAtPosition(i);
                            updateAndDelete_FB_Plant1.putExtra("account_comperer_plant1", s.getAccount_comperer_plant1());
                            updateAndDelete_FB_Plant1.putExtra("order_number_plant1", s.getOrder_number_plant1());
                            updateAndDelete_FB_Plant1.putExtra("plant1", s.getPlant1());
                            updateAndDelete_FB_Plant1.putExtra("water_amount_plant1", s.getWater_amount_plant1());
                            updateAndDelete_FB_Plant1.putExtra("time_hour_plant1", s.getTime_hour_plant1());
                            updateAndDelete_FB_Plant1.putExtra("time_min_plant1", s.getTime_min_plant1());
                            startActivity(updateAndDelete_FB_Plant1);

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
