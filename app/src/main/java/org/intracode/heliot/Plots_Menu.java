package org.intracode.heliot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Plots_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plots__menu);
    }

    public void goToPlant1Plots(View view){

        Intent startNewActivity = new Intent(this, Plots_Plant1_Menu.class);
        startActivity(startNewActivity);
    }

    public void goToPlant2Plots(View view) {

        Intent startNewActivity = new Intent(this, Plots_Plant2_Menu.class);
        startActivity(startNewActivity);
    }

    public void goToPlant3Plots(View view) {

        Intent startNewActivity = new Intent(this, Plots_Plant3_Menu.class);
        startActivity(startNewActivity);
    }
}
