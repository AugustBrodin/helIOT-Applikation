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

public class updateAndDelete_FB extends AppCompatActivity {

    private EditText plant, waterAmount, timeHour, timeMin;
    TextView ordernumber, accountComperer;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete__fb);

        plant = (EditText)findViewById(R.id.updateAndDelete_editText_newPlant);
        waterAmount = (EditText)findViewById(R.id.updateAndDelete_editText_waterAmount);
        timeHour = (EditText)findViewById(R.id.updateAndDelete_editText_timeHour);
        timeMin = (EditText)findViewById(R.id.updateAndDelete_editText_timeMin);

        String ordernummer = getIntent().getExtras().get("order_number").toString();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Bevattningar").child(ordernummer);

        accountComperer = (TextView)findViewById(R.id.updateAndDelete_textView_accountComperer);
        ordernumber = (TextView)findViewById(R.id.updateAndDelete_textView_ordernumber);
        accountComperer.setText("ID verifikation: " + getIntent().getStringExtra("account_comperer"));
        ordernumber.setText("ID: " + ordernummer);
        plant.setText(getIntent().getStringExtra("plant"));
        waterAmount.setText(getIntent().getStringExtra("water_amount"));
        timeHour.setText(getIntent().getStringExtra("time_hour"));
        timeMin.setText(getIntent().getStringExtra("time_min"));
    }

    public void updateValues_FB(View view){

       databaseReference.addListenerForSingleValueEvent(
               new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       dataSnapshot.getRef().child("plant").setValue(plant.getText().toString());
                       dataSnapshot.getRef().child("water_amount").setValue(waterAmount.getText().toString());
                       dataSnapshot.getRef().child("time_hour").setValue(timeHour.getText().toString());
                       dataSnapshot.getRef().child("time_min").setValue(timeMin.getText().toString());
                       Toast.makeText(updateAndDelete_FB.this, "Bevattning uppdaterad!", Toast.LENGTH_SHORT).show();
                       updateAndDelete_FB.this.finish();
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               }
       );

       // databaseReference.child("plant").setValue(plant);

    }

    public void eraseValues_FB(View view){

        databaseReference.removeValue().addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(updateAndDelete_FB.this, "Bevattning borttagen...", Toast.LENGTH_SHORT).show();
                            updateAndDelete_FB.this.finish();
                        }
                        else{
                            Toast.makeText(updateAndDelete_FB.this, "Fel vid radering av bevattning", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }
}
