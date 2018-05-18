package org.intracode.heliot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Logbook_Planta2 extends AppCompatActivity {

    ListView listView;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    private UserInformation_FB_Plant2 userInformation_FB_Plant2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook__planta2);

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference().child("Logbook").child("Plant2");

        userInformation_FB_Plant2 = new UserInformation_FB_Plant2();

        listView = (ListView) findViewById(R.id.logBook_lisView_Plant2);

        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.logbook_planta2_info, R.id.logbook_planta2_info_text, list);

        databaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int i = 1;
                        int account_Counter = 0;
                        int databaseCounter = 0;
                        int plant1Counter = 0;
                        int arrayMaxSize = 100;
                        int plant1SizeMax = 9;
                        int plant1Comperer = 0;
                        int countedValues = 0;
                        int overflowCounter = 1;
                        int[] databaseValues;
                        String databaseValuesMessage[];
                        String databaseValuesTimeStamp[];
                        int[] plant1Values;
                        databaseValues = new int[arrayMaxSize];
                        databaseValuesMessage = new String[arrayMaxSize];
                        databaseValuesTimeStamp = new String[arrayMaxSize];
                        plant1Values = new int[plant1SizeMax];
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {

                            userInformation_FB_Plant2 = ds.getValue(UserInformation_FB_Plant2.class);
                            account_Counter = Integer.parseInt(userInformation_FB_Plant2.getAccount_comperer_plant2());
                            String getMessage = userInformation_FB_Plant2.getMessage_plant2().toString();
                            String getTimeStamp = userInformation_FB_Plant2.getTimestamp_plant2().toString();
                            //
                            databaseValues[databaseCounter++] = Integer.parseInt(userInformation_FB_Plant2.getAccount_comperer_plant2().toString());
                            databaseValuesMessage[databaseCounter] = getMessage;
                            databaseValuesTimeStamp[databaseCounter] = getTimeStamp;
                            countedValues = databaseValues.length;

                            if(databaseCounter < 9) {
                                //  Toast.makeText(Logbook_Planta1.this, "Bevattning tillagd.", Toast.LENGTH_SHORT).show();
                                for (int m = 2; m < countedValues; m++) {
                                    //  Toast.makeText(Plant2Watering.this, "" + databaseValues[m-2], Toast.LENGTH_SHORT).show();
                                    if ((databaseValues[m - 1] - databaseValues[m - 2]) > 1 && databaseValues[m - 2] == 1) {
                                        // calculateDifference = databaseValues[m] - databaseValues[m - 1];
                                        Toast.makeText(Logbook_Planta2.this, "THIS SUCKS", Toast.LENGTH_SHORT).show();
                                        list.add("*" + databaseValuesMessage[databaseCounter] + "\n" + databaseValuesTimeStamp[databaseCounter]);
                                    } else if ((databaseValues[m - 1] - databaseValues[m - 2]) > 1 && databaseValues[m - 2] != 1) {
                                        Toast.makeText(Logbook_Planta2.this, "AWESOME", Toast.LENGTH_SHORT).show();
                                        list.add("*" + databaseValuesMessage[databaseCounter] + "\n" + databaseValuesTimeStamp[databaseCounter]);
                                    } else if ((databaseValues[m - 1] - databaseValues[m - 2]) == 1 && databaseValues[m] == 0 && databaseValues[m - 2] != 1 && databaseValues[0] != 1) {
                                        Toast.makeText(Logbook_Planta2.this, "AWESOME111", Toast.LENGTH_SHORT).show();
                                        list.add("*" + databaseValuesMessage[databaseCounter] + "\n" + databaseValuesTimeStamp[databaseCounter]);
                                    } else if ((databaseValues[m - 1] - databaseValues[m - 2]) == 1 && databaseValues[m] == 0 && databaseValues[m - 2] == 1 && databaseValues[0] == 1) {
                                        Toast.makeText(Logbook_Planta2.this, "AWESOME112", Toast.LENGTH_SHORT).show();
                                        list.add("*" + databaseValuesMessage[databaseCounter] + "\n" + databaseValuesTimeStamp[databaseCounter]);
                                    } else if ((databaseValues[m - 1] - databaseValues[m - 2]) == 1 && databaseValues[m] == 0 && databaseValues[m-2] != 1 && databaseValues[0] == 1) {
                                        Toast.makeText(Logbook_Planta2.this, "AWESOME113", Toast.LENGTH_SHORT).show();
                                        list.add("*" + databaseValuesMessage[databaseCounter] + "\n" + databaseValuesTimeStamp[databaseCounter]);
                                        //VERIFIED WORKS
                                    }else if ((databaseValues[m - 1] - databaseValues[m - 2]) == 1 && (databaseValues[m] - databaseValues[m - 1]) > 1 && databaseValues[0] != 1) {
                                        Toast.makeText(Logbook_Planta2.this, "AWESOME22", Toast.LENGTH_SHORT).show();
                                        list.add("*" + databaseValuesMessage[databaseCounter] + "\n" + databaseValuesTimeStamp[databaseCounter]);
                                    }
                                    //
                                    // VERIFIED WORKS
                                    else if ((databaseValues[m - 1] - databaseValues[m - 2]) == 1 && (databaseValues[m] - databaseValues[m - 1]) > 1 && databaseValues[0] == 1) {
                                        Toast.makeText(Logbook_Planta2.this, "AWESOME33", Toast.LENGTH_SHORT).show();
                                        list.add("*" + databaseValuesMessage[databaseCounter] + "\n" + databaseValuesTimeStamp[databaseCounter]);
                                    }
                                    //
                                }
                                //for(int m = 0; m < countedValues; m++) {
                                if (databaseValues[0] == 1 && databaseValues[1] == 0) {
                                    Toast.makeText(Logbook_Planta2.this, "DUDESTAR", Toast.LENGTH_SHORT).show();
                                    list.add("*" + databaseValuesMessage[databaseCounter] + "\n" + databaseValuesTimeStamp[databaseCounter]);
                                } else if (databaseValues[0] != 0 && databaseValues[1] == 0) {
                                    Toast.makeText(Logbook_Planta2.this, "DUDESTAR", Toast.LENGTH_SHORT).show();
                                    list.add("*" + databaseValuesMessage[databaseCounter] + "\n" + databaseValuesTimeStamp[databaseCounter]);
                                } else if (databaseValues[0] == 0 && databaseValues[1] == 0) {
                                    Toast.makeText(Logbook_Planta2.this, "DUDEFAIL", Toast.LENGTH_SHORT).show();
                                    list.add("*" + databaseValuesMessage[databaseCounter] + "\n" + databaseValuesTimeStamp[databaseCounter]);
                                }
                            }
                            else{
                                Toast.makeText(Logbook_Planta2.this, "Loggbok är vid tidpunkten full.", Toast.LENGTH_LONG).show();
                            }
                        }
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );
/**
 listView.setOnItemClickListener(
 new AdapterView.OnItemClickListener() {
@Override
public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
Intent updateAndDelete_FB = new Intent(Logbook_Planta1.this, updateAndDelete_FB.class);
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
 */
    }
}
