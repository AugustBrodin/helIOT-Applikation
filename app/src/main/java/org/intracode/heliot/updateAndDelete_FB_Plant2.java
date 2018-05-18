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

public class updateAndDelete_FB_Plant2 extends AppCompatActivity {

    private EditText plant, waterAmount, timeHour, timeMin;
    TextView ordernumber, accountComperer;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete__fb__plant2);

        plant = (EditText)findViewById(R.id.updateAndDelete_editText_newPlant2);
        waterAmount = (EditText)findViewById(R.id.updateAndDelete_editText_waterAmount2);
        timeHour = (EditText)findViewById(R.id.updateAndDelete_editText_timeHour2);
        timeMin = (EditText)findViewById(R.id.updateAndDelete_editText_timeMin2);

        String ordernummer = getIntent().getExtras().get("order_number_plant2").toString();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Bevattningar").child("Plant2").child(ordernummer);

        accountComperer = (TextView)findViewById(R.id.updateAndDelete_textView_accountComperer2);
        ordernumber = (TextView)findViewById(R.id.updateAndDelete_textView_ordernumber2);
        accountComperer.setText("ID verifikation: " + getIntent().getStringExtra("account_comperer_plant2"));
        ordernumber.setText("ID: " + ordernummer);
        plant.setText(getIntent().getStringExtra("plant2"));
        waterAmount.setText(getIntent().getStringExtra("water_amount_plant2"));
        timeHour.setText(getIntent().getStringExtra("time_hour_plant2"));
        timeMin.setText(getIntent().getStringExtra("time_min_plant2"));
    }

    public void updateValues_FB_Plant2(View view){

        databaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("plant2").setValue(plant.getText().toString());
                        dataSnapshot.getRef().child("water_amount_plant2").setValue(waterAmount.getText().toString());
                        dataSnapshot.getRef().child("time_hour_plant2").setValue(timeHour.getText().toString());
                        dataSnapshot.getRef().child("time_min_plant2").setValue(timeMin.getText().toString());
                        Toast.makeText(updateAndDelete_FB_Plant2.this, "Bevattning uppdaterad!", Toast.LENGTH_SHORT).show();
                        updateAndDelete_FB_Plant2.this.finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

        // databaseReference.child("plant").setValue(plant);

    }

    public void eraseValues_FB_Plant2(View view){

        databaseReference.removeValue().addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(updateAndDelete_FB_Plant2.this, "Bevattning borttagen...", Toast.LENGTH_SHORT).show();
                            updateAndDelete_FB_Plant2.this.finish();
                        }
                        else{
                            Toast.makeText(updateAndDelete_FB_Plant2.this, "Fel vid radering av bevattning", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }
}
