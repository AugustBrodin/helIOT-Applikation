package org.intracode.heliot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Plots_Plant3_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plots__plant3__menu);
    }

    public void goToPlant3WaterPlots(View view){

        Intent startNewActivity = new Intent(this, Plots_FB_Plant3_Water.class);
        startActivity(startNewActivity);
    }
}
