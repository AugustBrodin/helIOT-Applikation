package org.intracode.heliot;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updateAndDelete_FB_Plant1 extends AppCompatActivity {

    private EditText plant, waterAmount, timeHour, timeMin;
    TextView ordernumber, accountComperer;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete__fb__plant1);

        plant = (EditText)findViewById(R.id.updateAndDelete_editText_newPlant1);
        waterAmount = (EditText)findViewById(R.id.updateAndDelete_editText_waterAmount1);
        timeHour = (EditText)findViewById(R.id.updateAndDelete_editText_timeHour1);
        timeMin = (EditText)findViewById(R.id.updateAndDelete_editText_timeMin1);

        String ordernummer = getIntent().getExtras().get("order_number_plant1").toString();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Bevattningar").child("Plant1").child(ordernummer);

        accountComperer = (TextView)findViewById(R.id.updateAndDelete_textView_accountComperer1);
        ordernumber = (TextView)findViewById(R.id.updateAndDelete_textView_ordernumber1);
        accountComperer.setText("ID verifikation: " + getIntent().getStringExtra("account_comperer_plant1"));
        ordernumber.setText("ID: " + ordernummer);
        plant.setText(getIntent().getStringExtra("plant1"));
        waterAmount.setText(getIntent().getStringExtra("water_amount_plant1"));
        timeHour.setText(getIntent().getStringExtra("time_hour_plant1"));
        timeMin.setText(getIntent().getStringExtra("time_min_plant1"));
    }

    public void updateValues_FB_Plant1(View view){

        databaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("plant1").setValue(plant.getText().toString());
                        dataSnapshot.getRef().child("water_amount_plant1").setValue(waterAmount.getText().toString());
                        dataSnapshot.getRef().child("time_hour_plant1").setValue(timeHour.getText().toString());
                        dataSnapshot.getRef().child("time_min_plant1").setValue(timeMin.getText().toString());
                        Toast.makeText(updateAndDelete_FB_Plant1.this, "Bevattning uppdaterad!", Toast.LENGTH_SHORT).show();
                        updateAndDelete_FB_Plant1.this.finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

        // databaseReference.child("plant").setValue(plant);

    }

    public void eraseValues_FB_Plant1(View view){

        databaseReference.removeValue().addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(updateAndDelete_FB_Plant1.this, "Bevattning borttagen...", Toast.LENGTH_SHORT).show();
                            updateAndDelete_FB_Plant1.this.finish();
                        }
                        else{
                            Toast.makeText(updateAndDelete_FB_Plant1.this, "Fel vid radering av bevattning", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }
}
