package org.intracode.heliot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Plots_Plant2_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plots__plant2__menu);
    }

    public void goToPlant2WaterPlots(View view){

        Intent startNewActivity = new Intent(this, Plots_FB_Plant2_Water.class);
        startActivity(startNewActivity);
    }
}
