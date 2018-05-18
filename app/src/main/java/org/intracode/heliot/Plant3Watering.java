package org.intracode.heliot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Plant3Watering extends AppCompatActivity {

    private FirebaseAuth mAuth;

    DataBaseHandler wateringDB;

    private EditText editNewPlant,editNewWaterAmount,editTimeHour,editTimeMin, editOrdernumber, editAccountComperer;

    private static SeekBar seek_bar1;
    private static TextView stateWater1;
    private static Button acceptWaterAmount_button1;
    private static TextView stateWater2;
    private static TimePicker time_picker;
    private static Button approveTime_button1;
    private static TextView stateTime1;
    private static Button approveFinalParameters_button1;
    private static TextView finalParameters1;
    private static Button changeFinalParameters_button1;
    private static Button deleteFinalParameters_button1;

    private FirebaseDatabase database;

    private DatabaseReference databaseReference;

    private UserInformation_FB_Plant3 userInformation_FB_Plant3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant3_watering);
        wateringDB = new DataBaseHandler(this);

        mAuth = FirebaseAuth.getInstance();

        editNewPlant = (EditText)findViewById(R.id.Plant3Watering_editText_newPlant);
        editNewWaterAmount = (EditText)findViewById(R.id.Plant3Watering_editText_waterAmount);
        editTimeHour = (EditText)findViewById(R.id.Plant3Watering_editText_timeHour);
        editTimeMin = (EditText)findViewById(R.id.Plant3Watering_editText_timeMin);
        editOrdernumber = (EditText)findViewById(R.id.Plant3Watering_editText_Ordernumber);
        editAccountComperer = (EditText)findViewById(R.id.Plant3Watering_editText_accountComperer);

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference().child("Bevattningar").child("Plant3");

        userInformation_FB_Plant3 = new UserInformation_FB_Plant3();

        FirebaseUser user = mAuth.getCurrentUser();

        seekbar_1();
        showTime1();

    }

    public void Clear_lines(){

        editNewPlant.setText("");
        editNewWaterAmount.setText("");
        editTimeHour.setText("");
        editTimeMin.setText("");
        editOrdernumber.setText("");

    }

    int printValue(int compareOrderValue, int shit){
        Toast.makeText(Plant3Watering.this, compareOrderValue, Toast.LENGTH_SHORT).show();
        return compareOrderValue;
    }

    private void getValues(){

        userInformation_FB_Plant3.setAccount_comperer_plant3(editAccountComperer.getText().toString());
        userInformation_FB_Plant3.setOrder_number_plant3(editOrdernumber.getText().toString());
        userInformation_FB_Plant3.setPlant3(editNewPlant.getText().toString());
        userInformation_FB_Plant3.setWater_amount_plant3(editNewWaterAmount.getText().toString());
        userInformation_FB_Plant3.setTime_hour_plant3(editTimeHour.getText().toString());
        userInformation_FB_Plant3.setTime_min_plant3(editTimeMin.getText().toString());

    }

    public void goToHome_Menu_Plant3(View view){

        Intent startNewActivity = new Intent(this, Home_Menu.class);
        startActivity(startNewActivity);

    }

    public void goToBevattning_Plant3(View view){

        Intent startNewActivity = new Intent(this, Bevattning.class);
        startActivity(startNewActivity);

    }

    public void showTime1(){
        time_picker = (TimePicker)findViewById(R.id.Plant3Watering_timePicker);
        approveTime_button1 = (Button)findViewById(R.id.Plant3Watering_approveTime_button);
        stateTime1 = (TextView)findViewById(R.id.Plant3Watering_statedTime_text);

        approveTime_button1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stateTime1.setText("Angiven tid: " + time_picker.getCurrentHour() +  ":" + time_picker.getCurrentMinute());
                    }
                }
        );
    }

    public void seekbar_1() {
        seek_bar1 = (SeekBar) findViewById(R.id.Plant3Watering_seekBar1);
        seek_bar1.setMax(10000);
        stateWater1 = (TextView) findViewById(R.id.Plant3Watering_stateWaterAmount_fill);
        stateWater1.setText("Vattenmängd (ml):" + seek_bar1.getProgress() + " / " + seek_bar1.getMax());

        seek_bar1.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progress_value;

                    @Override
                    public void onProgressChanged(SeekBar Plant1Watering_seekBar1, int progress, boolean fromUser) {
                        progress_value = progress;
                        stateWater1.setText(progress + " / " + seek_bar1.getMax());
                        //     Toast.makeText(Plant1Watering.this, "SeekBar in progress", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar Plant1Watering_seekBar1) {
                        //     Toast.makeText(Plant1Watering.this, "SeekBar in StartTracking", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar Plant1Watering_seekBar1) {
                        stateWater1.setText(progress_value + " / " + seek_bar1.getMax());
                        // Toast.makeText(Plant1Watering.this, "SeekBar in StopTracking", Toast.LENGTH_SHORT).show();
                        acceptWaterAmount_button1 = (Button) findViewById(R.id.Plant3Watering_acceptWaterAmount_button);
                        acceptWaterAmount_button1.setOnClickListener(
                                new View.OnClickListener() {
                                    public void onClick(View v) {
                                        stateWater2 = (TextView) findViewById(R.id.Plant3Watering_acceptedWaterAmount_text);
                                        stateWater2.setText("Angiven vattenmängd: " + progress_value);
                                    }
                                }
                        );
                        approveFinalParameters_button1 = (Button)findViewById(R.id.Plant3Watering_approveWatering_button);
                        time_picker = (TimePicker)findViewById(R.id.Plant3Watering_timePicker);
                        approveFinalParameters_button1.setOnClickListener(
                                new View.OnClickListener(){
                                    public void onClick(View v){
                                        finalParameters1 = (TextView)findViewById(R.id.Plant3Watering_showFinalParameters_text);
                                        finalParameters1.setText("1                   " + progress_value + " ml" + "             " + time_picker.getCurrentHour() +  ":" + time_picker.getCurrentMinute());

                                        int identifier = 0;
                                        int newPlant = 3;
                                        int newWaterAmount = progress_value;
                                        int timeHour = time_picker.getCurrentHour();
                                        int timeMin = time_picker.getCurrentMinute();
                                        int orderAmount = identifier;

                                        // New1

                                        String time_hour = Integer.toString(timeHour);
                                        String time_min = Integer.toString(timeMin);

                                        for(int i=0; i<10; i++){
                                            if(timeHour == 0)
                                                time_hour = "00";
                                            if(timeMin == 0)
                                                time_min = "00";
                                            if(timeHour == 1)
                                                time_hour = "01";
                                            if(timeMin == 1)
                                                time_min = "01";
                                            if(timeHour == 2)
                                                time_hour = "02";
                                            if(timeMin == 2)
                                                time_min = "02";
                                            if(timeHour == 3)
                                                time_hour = "03";
                                            if(timeMin == 3)
                                                time_min = "03";
                                            if(timeHour == 4)
                                                time_hour = "04";
                                            if(timeMin == 4)
                                                time_min = "04";
                                            if(timeHour == 5)
                                                time_hour = "05";
                                            if(timeMin == 5)
                                                time_min = "05";
                                            if(timeHour == 6)
                                                time_hour = "06";
                                            if(timeMin == 6)
                                                time_min = "06";
                                            if(timeHour == 7)
                                                time_hour = "07";
                                            if(timeMin == 7)
                                                time_min = "07";
                                            if(timeHour == 8)
                                                time_hour = "08";
                                            if(timeMin == 8)
                                                time_min = "08";
                                            if(timeHour == 9)
                                                time_hour = "09";
                                            if(timeMin == 9)
                                                time_min = "09";

                                        }

                                        //End New1


                                        editNewPlant.setText(Integer.toString(newPlant));
                                        editNewWaterAmount.setText(Integer.toString(newWaterAmount));

                                        //New 2
                                        editTimeHour.setText(time_hour);
                                        editTimeMin.setText(time_min);
                                        editOrdernumber.setText(Integer.toString(orderAmount));

                                        //End New2

                                        // editTimeHour.setText(Integer.toString(timeHour));
                                        // editTimeMin.setText(Integer.toString(timeMin));

                                        String plant = editNewPlant.getText().toString().trim();
                                        String water_amount = editNewWaterAmount.getText().toString().trim();
                                        time_hour = editTimeHour.getText().toString().trim();
                                        time_min = editTimeMin.getText().toString().trim();
                                        String order_number = editOrdernumber.getText().toString().trim();

                                        int count = 1;
                                        count++;
                                        String n = Integer.toString(count);
/**
 String.format("%02d", time_hour);
 String.format("%02d", time_min);
 */
                                        // new bullfrog

                                        if(TextUtils.isEmpty(plant)) {
                                            Toast.makeText(Plant3Watering.this, "Ingen planta", Toast.LENGTH_SHORT).show();
                                        }
                                        else if(TextUtils.isEmpty(water_amount)){
                                            Toast.makeText(Plant3Watering.this, "Ingen vattenmängd", Toast.LENGTH_SHORT).show();
                                        }
                                        else if(TextUtils.isEmpty(time_hour)){
                                            Toast.makeText(Plant3Watering.this, "Ingen inställd tid", Toast.LENGTH_SHORT).show();

                                        }
                                        else if(TextUtils.isEmpty(time_min)){
                                            Toast.makeText(Plant3Watering.this, "Ingen inställd tid", Toast.LENGTH_SHORT).show();
                                        }
                                        else if(TextUtils.isEmpty(order_number)){
                                            Toast.makeText(Plant3Watering.this, "Inget ordernummer", Toast.LENGTH_SHORT).show();
                                        }
                                        else {

                                            final String id = databaseReference.push().getKey();

                                            //NEW TRY

                                            databaseReference.addListenerForSingleValueEvent(
                                                    new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                            // int compareOrderNumber = 0;
                                                            int compareAccountComperer = 0;
                                                            int databaseCounter = 0;
                                                            int plant3Counter = 0;
                                                            int arrayMaxSize = 100;
                                                            int plant3SizeMax = 9;
                                                            int plant3Comperer = 0;
                                                            int countedValues = 0;
                                                            int calculateDifference = 0;
                                                            int overflowCounter = 1;
                                                            int[] databaseValues;
                                                            int[] plant3Values;
                                                            databaseValues = new int[arrayMaxSize];
                                                            plant3Values = new int[plant3SizeMax];
                                                            for(DataSnapshot ds: dataSnapshot.getChildren()){
                                                                // getValues();
                                                                userInformation_FB_Plant3 = ds.getValue(UserInformation_FB_Plant3.class);
                                                                //compareOrderNumber = Integer.parseInt(userInformation_FB.getOrder_number().toString());
                                                                compareAccountComperer = Integer.parseInt(userInformation_FB_Plant3.getAccount_comperer_plant3().toString());
                                                                databaseValues[databaseCounter++] = Integer.parseInt(userInformation_FB_Plant3.getAccount_comperer_plant3().toString());
                                                                countedValues = databaseValues.length;
                                                                plant3Comperer = Integer.parseInt(userInformation_FB_Plant3.getPlant3().toString());
                                                                if(plant3Comperer == 3){
                                                                    plant3Values[plant3Counter++] = plant3Comperer;
                                                                }
                                                                //Toast.makeText(Plant1Watering.this, "" + compareOrderNumber, Toast.LENGTH_SHORT).show();
                                                                // Toast.makeText(Plant2Watering.this, "" + countedValues, Toast.LENGTH_SHORT).show();

                                                                /**
                                                                 if(compareOrderNumber == 1) {
                                                                 compareOrderNumber++;
                                                                 editOrdernumber.setText(Integer.toString(compareOrderNumber));
                                                                 databaseReference.child(userInformation_FB.getOrder_number()).setValue(userInformation_FB);
                                                                 }else if(compareOrderNumber == 2){
                                                                 compareOrderNumber++;
                                                                 editOrdernumber.setText(Integer.toString(compareOrderNumber));
                                                                 databaseReference.child(userInformation_FB.getOrder_number()).setValue(userInformation_FB);
                                                                 }else if(compareOrderNumber == 3){
                                                                 compareOrderNumber++;
                                                                 editOrdernumber.setText(Integer.toString(compareOrderNumber));
                                                                 databaseReference.child(userInformation_FB.getOrder_number()).setValue(userInformation_FB);
                                                                 }

                                                                 */
                                                            }
                                                            //  Toast.makeText(Plant2Watering.this, "What IN HELL IS: " + databaseCounter, Toast.LENGTH_SHORT).show();
                                                            if(databaseCounter < 9 && plant3Counter < 9) {
                                                                Toast.makeText(Plant3Watering.this, "Bevattning tillagd.", Toast.LENGTH_SHORT).show();
                                                                for (int m = 2; m < countedValues; m++) {
                                                                    //  Toast.makeText(Plant2Watering.this, "" + databaseValues[m-2], Toast.LENGTH_SHORT).show();
                                                                    if ((databaseValues[m - 1] - databaseValues[m - 2]) > 1 && overflowCounter == 1 && databaseValues[m - 2] == 1) {
                                                                        calculateDifference = databaseValues[m] - databaseValues[m - 1];
                                                                        overflowCounter++;
                                                                        Toast.makeText(Plant3Watering.this, "THIS SUCKS", Toast.LENGTH_SHORT).show();
                                                                        editOrdernumber.setText("Schemalaggning-" + (databaseValues[m - 2] + 1));
                                                                        editAccountComperer.setText("" + (databaseValues[m - 2] + 1));
                                                                        getValues();
                                                                        databaseReference.child(userInformation_FB_Plant3.getOrder_number_plant3()).setValue(userInformation_FB_Plant3);
                                                                    } else if ((databaseValues[m - 1] - databaseValues[m - 2]) > 1 && overflowCounter == 1 && databaseValues[m - 2] != 1) {
                                                                        overflowCounter++;
                                                                        Toast.makeText(Plant3Watering.this, "AWESOME", Toast.LENGTH_SHORT).show();
                                                                        editOrdernumber.setText("Schemalaggning-" + (databaseValues[m - 1] - databaseValues[m - 1] + 1));
                                                                        editAccountComperer.setText("" + (databaseValues[m - 1] - databaseValues[m - 1] + 1));
                                                                        getValues();
                                                                        databaseReference.child(userInformation_FB_Plant3.getOrder_number_plant3()).setValue(userInformation_FB_Plant3);
                                                                    } else if ((databaseValues[m - 1] - databaseValues[m - 2]) == 1 && overflowCounter == 1 && databaseValues[m] == 0 && databaseValues[m - 2] != 1 && databaseValues[0] != 1) {
                                                                        overflowCounter++;
                                                                        Toast.makeText(Plant3Watering.this, "AWESOME111", Toast.LENGTH_SHORT).show();
                                                                        editOrdernumber.setText("Schemalaggning-" + (databaseValues[m-1] - databaseValues[m-1] + 1));
                                                                        editAccountComperer.setText("" + (databaseValues[m-1] - databaseValues[m-1] + 1));
                                                                        getValues();
                                                                        databaseReference.child(userInformation_FB_Plant3.getOrder_number_plant3()).setValue(userInformation_FB_Plant3);
                                                                    } else if ((databaseValues[m - 1] - databaseValues[m - 2]) == 1 && overflowCounter == 1 && databaseValues[m] == 0 && databaseValues[m - 2] == 1 && databaseValues[0] == 1) {
                                                                        overflowCounter++;
                                                                        Toast.makeText(Plant3Watering.this, "AWESOME112", Toast.LENGTH_SHORT).show();
                                                                        editOrdernumber.setText("Schemalaggning-" + (databaseValues[m-1] + 1));
                                                                        editAccountComperer.setText("" + (databaseValues[m-1] + 1));
                                                                        getValues();
                                                                        databaseReference.child(userInformation_FB_Plant3.getOrder_number_plant3()).setValue(userInformation_FB_Plant3);
                                                                    } else if ((databaseValues[m - 1] - databaseValues[m - 2]) == 1 && overflowCounter == 1 && databaseValues[m] == 0 && databaseValues[m-2] != 1 && databaseValues[0] == 1) {
                                                                        overflowCounter++;
                                                                        Toast.makeText(Plant3Watering.this, "AWESOME113", Toast.LENGTH_SHORT).show();
                                                                        editOrdernumber.setText("Schemalaggning-" + (databaseValues[m-1] + 1));
                                                                        editAccountComperer.setText("" + (databaseValues[m-1] + 1));
                                                                        getValues();
                                                                        databaseReference.child(userInformation_FB_Plant3.getOrder_number_plant3()).setValue(userInformation_FB_Plant3);
                                                                        //VERIFIED WORKS
                                                                    }else if ((databaseValues[m - 1] - databaseValues[m - 2]) == 1 && (databaseValues[m] - databaseValues[m - 1]) > 1 && overflowCounter == 1 && databaseValues[0] != 1) {
                                                                        overflowCounter++;
                                                                        Toast.makeText(Plant3Watering.this, "AWESOME22", Toast.LENGTH_SHORT).show();
                                                                        editOrdernumber.setText("Schemalaggning-" + (databaseValues[m - 1] - databaseValues[m - 1] + 1));
                                                                        editAccountComperer.setText("" + (databaseValues[m - 1] - databaseValues[m - 1] + 1));
                                                                        getValues();
                                                                        databaseReference.child(userInformation_FB_Plant3.getOrder_number_plant3()).setValue(userInformation_FB_Plant3);
                                                                    }
                                                                    //
                                                                    // VERIFIED WORKS
                                                                    else if ((databaseValues[m - 1] - databaseValues[m - 2]) == 1 && (databaseValues[m] - databaseValues[m - 1]) > 1 && overflowCounter == 1 && databaseValues[0] == 1) {
                                                                        overflowCounter++;
                                                                        Toast.makeText(Plant3Watering.this, "AWESOME33", Toast.LENGTH_SHORT).show();
                                                                        editOrdernumber.setText("Schemalaggning-" + (databaseValues[m - 1] + 1));
                                                                        editAccountComperer.setText("" + (databaseValues[m - 1] + 1));
                                                                        getValues();
                                                                        databaseReference.child(userInformation_FB_Plant3.getOrder_number_plant3()).setValue(userInformation_FB_Plant3);
                                                                    }
                                                                    //
                                                                }
                                                                //for(int m = 0; m < countedValues; m++) {
                                                                if (databaseValues[0] == 1 && databaseValues[1] == 0 && overflowCounter == 1) {
                                                                    overflowCounter++;
                                                                    Toast.makeText(Plant3Watering.this, "DUDESTAR", Toast.LENGTH_SHORT).show();
                                                                    editOrdernumber.setText("Schemalaggning-" + (databaseValues[0] - databaseValues[0] + 2));
                                                                    editAccountComperer.setText("" + (databaseValues[0] - databaseValues[0] + 2));
                                                                    getValues();
                                                                    databaseReference.child(userInformation_FB_Plant3.getOrder_number_plant3()).setValue(userInformation_FB_Plant3);
                                                                } else if (databaseValues[0] != 0 && databaseValues[1] == 0 && overflowCounter == 1) {
                                                                    overflowCounter++;
                                                                    Toast.makeText(Plant3Watering.this, "DUDESTAR", Toast.LENGTH_SHORT).show();
                                                                    editOrdernumber.setText("Schemalaggning-" + (databaseValues[0] - databaseValues[0] + 1));
                                                                    editAccountComperer.setText("" + (databaseValues[0] - databaseValues[0] + 1));
                                                                    getValues();
                                                                    databaseReference.child(userInformation_FB_Plant3.getOrder_number_plant3()).setValue(userInformation_FB_Plant3);
                                                                } else if (databaseValues[0] == 0 && databaseValues[1] == 0 && overflowCounter == 1) {
                                                                    overflowCounter++;
                                                                    Toast.makeText(Plant3Watering.this, "DUDEFAIL", Toast.LENGTH_SHORT).show();
                                                                    editOrdernumber.setText("Schemalaggning-" + (databaseValues[0] - databaseValues[0] + 1));
                                                                    editAccountComperer.setText("" + (databaseValues[0] - databaseValues[0] + 1));
                                                                    getValues();
                                                                    databaseReference.child(userInformation_FB_Plant3.getOrder_number_plant3()).setValue(userInformation_FB_Plant3);
                                                                }
                                                            }
                                                            else{
                                                                Toast.makeText(Plant3Watering.this, "Maximalt antal bevattningar i systemet. Går inte att lägga till fler innan en annan bevattning har påbörjats.", Toast.LENGTH_LONG).show();
                                                            }
                                                            // }

                                                            // Toast.makeText(Plant2Watering.this, "" + countedValues, Toast.LENGTH_SHORT).show();

                                                            /**
                                                             // NEW IMPORTANT

                                                             //compareOrderNumber++;
                                                             compareAccountComperer++;
                                                             if(compareAccountComperer == 0) {
                                                             editOrdernumber.setText("Schemalaggning-" + Integer.toString(compareAccountComperer));
                                                             editAccountComperer.setText(Integer.toString(compareAccountComperer));
                                                             getValues();
                                                             databaseReference.child(userInformation_FB.getOrder_number()).setValue(userInformation_FB);
                                                             //databaseReference.child(userInformation_FB.getAccount_comperer()).setValue(userInformation_FB);
                                                             }
                                                             if(compareAccountComperer != 0) {
                                                             editOrdernumber.setText("Schemalaggning-" + Integer.toString(compareAccountComperer));
                                                             editAccountComperer.setText(Integer.toString(compareAccountComperer));
                                                             // Toast.makeText(Plant2Watering.this, "" + compareAccountComperer, Toast.LENGTH_SHORT).show();
                                                             getValues();
                                                             databaseReference.child(userInformation_FB.getOrder_number()).setValue(userInformation_FB);
                                                             //databaseReference.child(userInformation_FB.getAccount_comperer()).setValue(userInformation_FB);
                                                             }

                                                             */
                                                            //IMPORTANT
                                                        }

                                                        @Override
                                                        public void onCancelled(DatabaseError databaseError) {

                                                        }
                                                    }
                                            );

                                            //END NEW
/**
 databaseReference.addListenerForSingleValueEvent(
 new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
getValues();

//   if(order_number.equals(userInformation.FB));

databaseReference.child(userInformation_FB.getOrder_number()).setValue(userInformation_FB);
Toast.makeText(Plant1Watering.this, "Bevattning tillagd", Toast.LENGTH_SHORT).show();
}

@Override
public void onCancelled(DatabaseError databaseError) {

}
}
 );

 */


                                            /**

                                             String id = databaseReference.push().getKey();
                                             UserInformation_FB userInformation_FB = new UserInformation_FB(plant, water_amount, time_hour, time_min);

                                             databaseReference.child(id).child("plant").setValue(plant.toString());
                                             databaseReference.child(id).child("water_amount").setValue(water_amount.toString());
                                             databaseReference.child(id).child("time_hour").setValue(time_hour.toString());
                                             databaseReference.child(id).child("time_min").setValue(time_min.toString());

                                             Toast.makeText(Plant1Watering.this, "Bevattning tillagd", Toast.LENGTH_SHORT).show();

                                             //Clear_lines();

                                             */
                                        }



                                        //ended bullfrog

                                        boolean isInserted = wateringDB.insertData(
                                                editNewPlant.getText().toString(),
                                                editNewWaterAmount.getText().toString(),
                                                editTimeHour.getText().toString(),
                                                editTimeMin.getText().toString());
                                        if(isInserted == true)
                                            Toast.makeText(Plant3Watering.this,
                                                    ""
                                                    //    "Förfrågan tillagd"
                                                    , Toast.LENGTH_SHORT).show();
                                        else
                                            Toast.makeText(Plant3Watering.this, "Gick inte att lägga till", Toast.LENGTH_SHORT).show();

                                    }
                                }
                        );

                        changeFinalParameters_button1 = (Button)findViewById(R.id.Plant3Watering_approveChange_button);
                        time_picker = (TimePicker)findViewById(R.id.Plant3Watering_timePicker);

                        changeFinalParameters_button1.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        boolean isUpdate = wateringDB.updateData(
                                                editOrdernumber.getText().toString(),
                                                editNewPlant.getText().toString(),
                                                editNewWaterAmount.getText().toString(),
                                                editTimeHour.getText().toString(),
                                                editTimeMin.getText().toString());

                                        if(isUpdate == true)
                                            Toast.makeText(Plant3Watering.this, "Uppdatering utförd", Toast.LENGTH_LONG).show();
                                        else
                                            Toast.makeText(Plant3Watering.this, "Gick inte att uppdatera", Toast.LENGTH_LONG).show();

                                    }
                                }

                        );

                        deleteFinalParameters_button1 = (Button)findViewById(R.id.Plant3Watering_approveDelete_button);
                        time_picker = (TimePicker)findViewById(R.id.Plant3Watering_timePicker);

                        deleteFinalParameters_button1.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Integer deletedRows = wateringDB.deleteData(
                                                editOrdernumber.getText().toString());
                                        if(deletedRows > 0)
                                            Toast.makeText(Plant3Watering.this, "Bevattning borttagen", Toast.LENGTH_LONG).show();
                                        else
                                            Toast.makeText(Plant3Watering.this, "Kunde inte hitta data", Toast.LENGTH_LONG).show();
                                    }
                                }

                        );

                    }
                }
        );

    }

}