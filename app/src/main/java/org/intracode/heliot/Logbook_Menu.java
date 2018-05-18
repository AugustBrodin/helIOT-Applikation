package org.intracode.heliot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Logbook_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook__menu);
    }
    public void goToLogbookPlant1(View view) {

        Intent startNewActivity = new Intent(this, Logbook_Planta1.class);
        startActivity(startNewActivity);
    }
    public void goToLogbookPlant2(View view) {

        Intent startNewActivity = new Intent(this, Logbook_Planta2.class);
        startActivity(startNewActivity);
    }
    public void goToLogbookPlant3(View view) {

        Intent startNewActivity = new Intent(this, Logbook_Planta3.class);
        startActivity(startNewActivity);
    }
}
