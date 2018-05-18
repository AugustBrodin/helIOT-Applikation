package org.intracode.heliot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ViewTimeTable_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_time_table__menu);
    }

    public void goToViewTimeTablePlanta1(View view) {

        Intent startNewActivity = new Intent(this, ViewTimeTable_Planta1.class);
        startActivity(startNewActivity);
    }

    public void goToViewTimeTablePlanta2(View view) {

        Intent startNewActivity = new Intent(this, ViewTimeTable_Planta2.class);
        startActivity(startNewActivity);
    }

    public void goToViewTimeTablePlanta3(View view) {

        Intent startNewActivity = new Intent(this, ViewTimeTable_Planta3.class);
        startActivity(startNewActivity);
    }
}
