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

public class Logbook_Add_Test extends AppCompatActivity {

    ListView listView;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    private UserInformation_FB_Plant2 userInformation_FB_Plant2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook__add__test);

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference().child("Logbook").child("Plant2");

        userInformation_FB_Plant2 = new UserInformation_FB_Plant2();

        listView = (ListView) findViewById(R.id.logBookTest_lisView_Plants);

        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.logbook_test_plants, R.id.logbook_plants_info_text, list);

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
                            list.add("*" + userInformation_FB_Plant2.getMessage_plant2() + "\n" + userInformation_FB_Plant2.getTimestamp_plant2());
                        }
                        //     listView.setAdapter(adapter);

                        if(databaseCounter < 9) {
                            Toast.makeText(Logbook_Add_Test.this, "Bevattning tillagd.", Toast.LENGTH_SHORT).show();
                            for (int m = 2; m < countedValues; m++) {
                                //  Toast.makeText(Plant2Watering.this, "" + databaseValues[m-2], Toast.LENGTH_SHORT).show();
                                if ((databaseValues[m - 1] - databaseValues[m - 2]) > 1 && overflowCounter == 1 && databaseValues[m - 2] == 1) {
                                    // calculateDifference = databaseValues[m] - databaseValues[m - 1];
                                    overflowCounter++;
                                    Toast.makeText(Logbook_Add_Test.this, "THIS SUCKS", Toast.LENGTH_SHORT).show();
                                    userInformation_FB_Plant2.setLogbook_id_plant2("Logga-" + + (databaseValues[m - 2] + 1));
                                    userInformation_FB_Plant2.setMessage_plant2("BEVATTNING PÅBÖRJAD");
                                    userInformation_FB_Plant2.setTimestamp_plant2("2018-05-11    11:52");
                                    userInformation_FB_Plant2.setAccount_comperer_plant2("" + (databaseValues[m - 2] + 1));
                                    databaseReference.child(userInformation_FB_Plant2.getLogbook_id_plant2()).setValue(userInformation_FB_Plant2);
                                } else if ((databaseValues[m - 1] - databaseValues[m - 2]) > 1 && overflowCounter == 1 && databaseValues[m - 2] != 1) {
                                    overflowCounter++;
                                    Toast.makeText(Logbook_Add_Test.this, "AWESOME", Toast.LENGTH_SHORT).show();
                                    userInformation_FB_Plant2.setLogbook_id_plant2("Logga-" + (databaseValues[m - 1] - databaseValues[m - 1] + 1));
                                    userInformation_FB_Plant2.setMessage_plant2("BEVATTNING AVSLUTAD");
                                    userInformation_FB_Plant2.setTimestamp_plant2("2018-05-12    11:52");
                                    userInformation_FB_Plant2.setAccount_comperer_plant2("" + (databaseValues[m - 1] - databaseValues[m - 1] + 1));
                                    databaseReference.child(userInformation_FB_Plant2.getLogbook_id_plant2()).setValue(userInformation_FB_Plant2);
                                } else if ((databaseValues[m - 1] - databaseValues[m - 2]) == 1 && overflowCounter == 1 && databaseValues[m] == 0 && databaseValues[m - 2] != 1 && databaseValues[0] != 1) {
                                    overflowCounter++;
                                    Toast.makeText(Logbook_Add_Test.this, "AWESOME111", Toast.LENGTH_SHORT).show();
                                    userInformation_FB_Plant2.setLogbook_id_plant2("Logga-" + (databaseValues[m-1] - databaseValues[m-1] + 1));
                                    userInformation_FB_Plant2.setMessage_plant2("BEVATTNING PÅBÖRJAD");
                                    userInformation_FB_Plant2.setTimestamp_plant2("2018-05-13    11:52");
                                    userInformation_FB_Plant2.setAccount_comperer_plant2("" + (databaseValues[m-1] - databaseValues[m-1] + 1));
                                    databaseReference.child(userInformation_FB_Plant2.getLogbook_id_plant2()).setValue(userInformation_FB_Plant2);
                                } else if ((databaseValues[m - 1] - databaseValues[m - 2]) == 1 && overflowCounter == 1 && databaseValues[m] == 0 && databaseValues[m - 2] == 1 && databaseValues[0] == 1) {
                                    overflowCounter++;
                                    Toast.makeText(Logbook_Add_Test.this, "AWESOME112", Toast.LENGTH_SHORT).show();
                                    userInformation_FB_Plant2.setLogbook_id_plant2("Logga-" + (databaseValues[m-1] + 1));
                                    userInformation_FB_Plant2.setMessage_plant2("BEVATTNING AVSLUTAD");
                                    userInformation_FB_Plant2.setTimestamp_plant2("2018-05-14    11:52");
                                    userInformation_FB_Plant2.setAccount_comperer_plant2("" + (databaseValues[m-1] + 1));
                                    databaseReference.child(userInformation_FB_Plant2.getLogbook_id_plant2()).setValue(userInformation_FB_Plant2);
                                } else if ((databaseValues[m - 1] - databaseValues[m - 2]) == 1 && overflowCounter == 1 && databaseValues[m] == 0 && databaseValues[m-2] != 1 && databaseValues[0] == 1) {
                                    overflowCounter++;
                                    Toast.makeText(Logbook_Add_Test.this, "AWESOME113", Toast.LENGTH_SHORT).show();
                                    userInformation_FB_Plant2.setLogbook_id_plant2("Logga-" + (databaseValues[m-1] + 1));
                                    userInformation_FB_Plant2.setMessage_plant2("BEVATTNING PÅBÖRJAD");
                                    userInformation_FB_Plant2.setTimestamp_plant2("2018-05-15    11:52");
                                    userInformation_FB_Plant2.setAccount_comperer_plant2("" + (databaseValues[m-1] + 1));
                                    databaseReference.child(userInformation_FB_Plant2.getLogbook_id_plant2()).setValue(userInformation_FB_Plant2);
                                    //VERIFIED WORKS
                                }else if ((databaseValues[m - 1] - databaseValues[m - 2]) == 1 && (databaseValues[m] - databaseValues[m - 1]) > 1 && overflowCounter == 1 && databaseValues[0] != 1) {
                                    overflowCounter++;
                                    Toast.makeText(Logbook_Add_Test.this, "AWESOME22", Toast.LENGTH_SHORT).show();
                                    userInformation_FB_Plant2.setLogbook_id_plant2("Logga-" + (databaseValues[m - 1] - databaseValues[m - 1] + 1));
                                    userInformation_FB_Plant2.setMessage_plant2("BEVATTNING AVSLUTAD");
                                    userInformation_FB_Plant2.setTimestamp_plant2("2018-05-16    11:52");
                                    userInformation_FB_Plant2.setAccount_comperer_plant2("" + (databaseValues[m - 1] - databaseValues[m - 1] + 1));
                                    databaseReference.child(userInformation_FB_Plant2.getLogbook_id_plant2()).setValue(userInformation_FB_Plant2);
                                }
                                //
                                // VERIFIED WORKS
                                else if ((databaseValues[m - 1] - databaseValues[m - 2]) == 1 && (databaseValues[m] - databaseValues[m - 1]) > 1 && overflowCounter == 1 && databaseValues[0] == 1) {
                                    overflowCounter++;
                                    Toast.makeText(Logbook_Add_Test.this, "AWESOME33", Toast.LENGTH_SHORT).show();
                                    userInformation_FB_Plant2.setLogbook_id_plant2("Logga-" + (databaseValues[m - 1] + 1));
                                    userInformation_FB_Plant2.setMessage_plant2("BEVATTNING PÅBÖRJAD");
                                    userInformation_FB_Plant2.setTimestamp_plant2("2018-05-17   11:52");
                                    userInformation_FB_Plant2.setAccount_comperer_plant2("" + (databaseValues[m - 1] + 1));
                                    databaseReference.child(userInformation_FB_Plant2.getLogbook_id_plant2()).setValue(userInformation_FB_Plant2);
                                }
                                //
                            }
                            //for(int m = 0; m < countedValues; m++) {
                            if (databaseValues[0] == 1 && databaseValues[1] == 0 && overflowCounter == 1) {
                                overflowCounter++;
                                Toast.makeText(Logbook_Add_Test.this, "DUDESTAR", Toast.LENGTH_SHORT).show();
                                userInformation_FB_Plant2.setLogbook_id_plant2("Logga-" + (databaseValues[0] - databaseValues[0] + 2));
                                userInformation_FB_Plant2.setMessage_plant2("BEVATTNING AVSLUTAD");
                                userInformation_FB_Plant2.setTimestamp_plant2("2018-05-18    11:52");
                                userInformation_FB_Plant2.setAccount_comperer_plant2("" + (databaseValues[0] - databaseValues[0] + 2));
                                databaseReference.child(userInformation_FB_Plant2.getLogbook_id_plant2()).setValue(userInformation_FB_Plant2);
                            } else if (databaseValues[0] != 0 && databaseValues[1] == 0 && overflowCounter == 1) {
                                overflowCounter++;
                                Toast.makeText(Logbook_Add_Test.this, "DUDESTAR", Toast.LENGTH_SHORT).show();
                                userInformation_FB_Plant2.setLogbook_id_plant2("Logga-" + (databaseValues[0] - databaseValues[0] + 1));
                                userInformation_FB_Plant2.setMessage_plant2("BEVATTNING PÅBÖRJAD");
                                userInformation_FB_Plant2.setTimestamp_plant2("2018-05-19    11:52");
                                userInformation_FB_Plant2.setAccount_comperer_plant2("" + (databaseValues[0] - databaseValues[0] + 1));
                                databaseReference.child(userInformation_FB_Plant2.getLogbook_id_plant2()).setValue(userInformation_FB_Plant2);
                            } else if (databaseValues[0] == 0 && databaseValues[1] == 0 && overflowCounter == 1) {
                                overflowCounter++;
                                Toast.makeText(Logbook_Add_Test.this, "DUDEFAIL", Toast.LENGTH_SHORT).show();
                                userInformation_FB_Plant2.setLogbook_id_plant2("Logga-" + (databaseValues[0] - databaseValues[0] + 1));
                                userInformation_FB_Plant2.setMessage_plant2("BEVATTNING AVSLUTAD");
                                userInformation_FB_Plant2.setTimestamp_plant2("2018-05-20    11:52");
                                userInformation_FB_Plant2.setAccount_comperer_plant2("" + (databaseValues[0] - databaseValues[0] + 1));
                                databaseReference.child(userInformation_FB_Plant2.getLogbook_id_plant2()).setValue(userInformation_FB_Plant2);
                            }
                        }
                        else{
                            Toast.makeText(Logbook_Add_Test.this, "Maximalt antal bevattningar i systemet. Går inte att lägga till fler innan en annan bevattning har påbörjats.", Toast.LENGTH_LONG).show();
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
