package org.intracode.heliot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Bevattning extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bevattning);
    }

    public void goToPlant1Watering(View view) {

        Intent startNewActivity = new Intent(this, Plant1Watering.class);
        startActivity(startNewActivity);
    }

    public void goToPlant2Watering(View view) {

        Intent startNewActivity = new Intent(this, Plant2Watering.class);
        startActivity(startNewActivity);

    }

    public void goToPlant3Watering(View view) {

        Intent startNewActivity = new Intent(this, Plant3Watering.class);
        startActivity(startNewActivity);

    }
}