package org.intracode.heliot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__menu);
    }

    public void goToBevattning(View view) {

        Intent startNewActivity = new Intent(this, Bevattning.class);
        startActivity(startNewActivity);

    }

    public void goToBevattningsSchema(View view) {

        Intent startNewActivity = new Intent(this, ViewTimeTable_Menu.class);
        startActivity(startNewActivity);

    }

    public void goToTabeller(View view){

        Intent startNewActivity = new Intent(this, Plots_Menu.class);
        startActivity(startNewActivity);
    }

    public void goToPlotTest(View view){

        Intent startNewActivity = new Intent(this, SensorPlotTest.class);
        startActivity(startNewActivity);
    }

    public void goToSensorPlotFB(View view) {

        Intent startNewActivity = new Intent(this, SensorPlots_FB.class);
        startActivity(startNewActivity);
    }

    public void goToSensorPlotFBtest(View view) {

        Intent startNewActivity = new Intent(this, Sensorplots_FB_Test.class);
        startActivity(startNewActivity);
    }

    public void goToPlotsFBPlant11Water(View view) {

        Intent startNewActivity = new Intent(this, Plots_FB_Plant1_Water.class);
        startActivity(startNewActivity);
    }

    public void goToTimeTablePlanta1(View view){

        Intent startNewActivity = new Intent(this, ViewTimeTable_Planta1.class);
        startActivity(startNewActivity);
    }

    public void goToTest(View view) {

        Intent startNewActivity = new Intent(this, Test_list.class);
        startActivity(startNewActivity);
    }

    public void goToViewTable(View view) {

        Intent startNewActivity = new Intent(this, View_timeTable.class);
        startActivity(startNewActivity);
    }

    public void goToViewTableUI(View view) {

        Intent startNewActivity = new Intent(this, View_timeTableUI.class);
        startActivity(startNewActivity);
    }

    public void goToLogbookMenu(View view){

        Intent startNewActivity = new Intent(this, Logbook_Menu.class);
        startActivity(startNewActivity);
    }

    public void goToLogbook_Add_Test(View view){

        Intent startNewActivity = new Intent(this, Logbook_Add_Test.class);
        startActivity(startNewActivity);
    }

}
