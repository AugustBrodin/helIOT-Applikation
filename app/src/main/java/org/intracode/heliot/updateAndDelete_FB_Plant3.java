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

public class updateAndDelete_FB_Plant3 extends AppCompatActivity {

    private EditText plant, waterAmount, timeHour, timeMin;
    TextView ordernumber, accountComperer;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete__fb__plant3);

        plant = (EditText)findViewById(R.id.updateAndDelete_editText_newPlant3);
        waterAmount = (EditText)findViewById(R.id.updateAndDelete_editText_waterAmount3);
        timeHour = (EditText)findViewById(R.id.updateAndDelete_editText_timeHour3);
        timeMin = (EditText)findViewById(R.id.updateAndDelete_editText_timeMin3);

        String ordernummer = getIntent().getExtras().get("order_number_plant3").toString();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Bevattningar").child("Plant3").child(ordernummer);

        accountComperer = (TextView)findViewById(R.id.updateAndDelete_textView_accountComperer3);
        ordernumber = (TextView)findViewById(R.id.updateAndDelete_textView_ordernumber3);
        accountComperer.setText("ID verifikation: " + getIntent().getStringExtra("account_comperer_plant3"));
        ordernumber.setText("ID: " + ordernummer);
        plant.setText(getIntent().getStringExtra("plant3"));
        waterAmount.setText(getIntent().getStringExtra("water_amount_plant3"));
        timeHour.setText(getIntent().getStringExtra("time_hour_plant3"));
        timeMin.setText(getIntent().getStringExtra("time_min_plant3"));
    }

    public void updateValues_FB_Plant3(View view){

        databaseReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("plant3").setValue(plant.getText().toString());
                        dataSnapshot.getRef().child("water_amount_plant3").setValue(waterAmount.getText().toString());
                        dataSnapshot.getRef().child("time_hour_plant3").setValue(timeHour.getText().toString());
                        dataSnapshot.getRef().child("time_min_plant3").setValue(timeMin.getText().toString());
                        Toast.makeText(updateAndDelete_FB_Plant3.this, "Bevattning uppdaterad!", Toast.LENGTH_SHORT).show();
                        updateAndDelete_FB_Plant3.this.finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

        // databaseReference.child("plant").setValue(plant);

    }

    public void eraseValues_FB_Plant3(View view){

        databaseReference.removeValue().addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(updateAndDelete_FB_Plant3.this, "Bevattning borttagen...", Toast.LENGTH_SHORT).show();
                            updateAndDelete_FB_Plant3.this.finish();
                        }
                        else{
                            Toast.makeText(updateAndDelete_FB_Plant3.this, "Fel vid radering av bevattning", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }
}
