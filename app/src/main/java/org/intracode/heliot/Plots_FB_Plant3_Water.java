package org.intracode.heliot;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;

public class Plots_FB_Plant3_Water extends AppCompatActivity {
    private static final String TAG = "Plots_FB_Plant3_Water";

    PointsGraphSeries<DataPoint> xySeries;

    //New LineGraphSeries
    LineGraphSeries<DataPoint> twodimensionalLineSeries;
    //

    GraphView SensorPlot;

    FirebaseDatabase databaseFB;
    DatabaseReference databaseReference;
    private UserInformation_FB_Plant3 user;

    ArrayList<XyValuesPlant3> xyValuesArrayPlant3;

    int displayWater;
    int displayHour;
    int displayMin;
    int displayDay;
    int displayPlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plots__fb__plant3__water);

        user = new UserInformation_FB_Plant3();

        databaseFB = FirebaseDatabase.getInstance();
        databaseReference = databaseFB.getReference().child("Bevattningar").child("Plant3");

        SensorPlot = (GraphView)findViewById(R.id.plots_fb_plant3_water_graphView);

        //Axis titles
        SensorPlot.getGridLabelRenderer().setVerticalAxisTitle("Vattenmängd (ml)");
        SensorPlot.getGridLabelRenderer().setHorizontalAxisTitle("Nuvarande dag");

        //Background color theme
        SensorPlot.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        //End

        Log.d(TAG, "createScatterPlot: Creating scatter plot");

        xySeries = new PointsGraphSeries();

        //New LineGraphSeries
        twodimensionalLineSeries = new LineGraphSeries();
        //

        xyValuesArrayPlant3 = new ArrayList<>();
/**
 double start = -10000;
 double end = 10000;
 for(int i = 0; i<40; i++) {
 double randomX = new Random().nextDouble();
 double randomY = new Random().nextDouble();

 double x = start + (randomX * (end - start));
 double y = start + (randomY * (end - start));

 xyValuesArrayTest.add(new XyValuesTest(x, y));
 }
 */
/**
 xySeries.appendData(new DataPoint(1.0, 2.0), true, 1000);
 xySeries.appendData(new DataPoint(9.0, -55.0), true, 1000);
 xySeries.appendData(new DataPoint(7.0, -22.0), true, 1000);
 */


     //   xyValuesArrayPlant3 = sortArray(xyValuesArrayPlant3);
/**
 for(int i = 0; i < xyValuesArrayTest.size(); i++){

 double x = xyValuesArrayTest.get(i).getX();
 double y = xyValuesArrayTest.get(i).getY();
 xySeries.appendData(new DataPoint(x,y), true, 1000);
 }
 */
        // DUDE STYLE FOR US
/**
 databaseReference.addValueEventListener(
 new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
for(DataSnapshot ds: dataSnapshot.getChildren()){

user = ds.getValue(UserInformation_FB.class);
//list.add("Planta nr: " + user.getPlant().toString() + "  Vattenmängd: " + user.getWater_amount().toString() + "    Tid: " + user.getTime_hour().toString() + ":" + user.getTime_min().toString());
displayWater = Integer.parseInt(user.getWater_amount().toString());
displayMin = Integer.parseInt(user.getTime_min().toString());
//displayMin = xyValuesArrayTest.get(Integer.parseInt(user.getTime_min().toString())).getX();
xyValuesArrayTest.add(new XyValuesTest(displayWater, displayMin));
}
xyValuesArrayTest = sortArray(xyValuesArrayTest);
xySeries.appendData(new DataPoint(displayWater, displayMin), true, 1000);
}

@Override
public void onCancelled(DatabaseError databaseError) {

}
}
 );
 */

        databaseReference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int i = 1;
                        StringBuilder displayDayStr;
                        for(DataSnapshot ds: dataSnapshot.getChildren()){

                            user = ds.getValue(UserInformation_FB_Plant3.class);
                            //list.add("Planta nr: " + user.getPlant().toString() + "  Vattenmängd: " + user.getWater_amount().toString() + "    Tid: " + user.getTime_hour().toString() + ":" + user.getTime_min().toString());
                            displayWater = Integer.parseInt(user.getWater_amount_plant3().toString());
                            displayHour = Integer.parseInt(user.getTime_hour_plant3().toString());
                            displayMin = Integer.parseInt(user.getTime_min_plant3().toString());
                            displayPlant = Integer.parseInt(user.getPlant3().toString());
                            String displayHourStr = Integer.toString(displayHour);
                            String displayMinStr = Integer.toString(displayMin);
                            displayDayStr = new StringBuilder();
                            displayDayStr.append(displayHour).append(displayMin);
                            displayDayStr.toString();
                            displayDay = Integer.parseInt(displayDayStr.toString());
                            Toast.makeText(Plots_FB_Plant3_Water.this, "STR " + displayDayStr, Toast.LENGTH_SHORT).show();
                            Toast.makeText(Plots_FB_Plant3_Water.this, "INT " + displayDay, Toast.LENGTH_SHORT).show();
                            //displayMin = xyValuesArrayTest.get(Integer.parseInt(user.getTime_min().toString())).getX();
                            if(displayPlant == 3) {
                                appendGraphValues(displayDay, displayWater, i);
                                i++;
                            }
                            //xyValuesArrayTest.add(new XyValuesTest(displayMin, displayWater));
                            //xyValuesArrayTest = sortArray(xyValuesArrayTest);
                            //displayWater = xyValuesArrayTest.get(ds).getX();
                            //displayMin = xyValuesArrayTest.get(displayMin).getY();
                            //xySeries.appendData(new DataPoint(displayMin, displayWater), true, 1000);

                        }
                        appendGraphValues(0, 0 ,i);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

        //int arraySize = xyValuesArrayTest.size();
        //String array = Integer.toString(arraySize);
        // Toast.makeText(this,"" + array, Toast.LENGTH_SHORT).show();
        // Toast.makeText(this, "" + displayWater, Toast.LENGTH_SHORT).show();


        // FUN ENDS
        /**
         xySeries.setShape(PointsGraphSeries.Shape.RECTANGLE);
         xySeries.setColor(Color.BLUE);
         xySeries.setSize(22f);

         SensorPlot.getViewport().setScalable(true);
         SensorPlot.getViewport().setScalableY(true);
         SensorPlot.getViewport().setScrollable(true);
         SensorPlot.getViewport().setScrollableY(true);

         SensorPlot.getViewport().setYAxisBoundsManual(true);
         SensorPlot.getViewport().setMaxY(10000);
         SensorPlot.getViewport().setMinY(-10000);

         SensorPlot.getViewport().setXAxisBoundsManual(true);
         SensorPlot.getViewport().setMaxX(100);
         SensorPlot.getViewport().setMinX(-100);

         SensorPlot.addSeries(xySeries);
         */
    }

    int appendGraphValues(int x, int y, int i){

        //Toast.makeText(this, "" + x + "    " + y + "      " + i, Toast.LENGTH_SHORT).show();
        int comparer = 0;
        if(x != 0 || y != 0){
            for (int j = i; j <= i; j++) {
                xyValuesArrayPlant3.add(new XyValuesPlant3(x, y));
                comparer = i;
            }
            xyValuesArrayPlant3 = sortArray(xyValuesArrayPlant3);

            int arraySize = xyValuesArrayPlant3.size();
            Toast.makeText(this, "" + arraySize, Toast.LENGTH_SHORT).show();
            //  Toast.makeText(this, "WHAT THE FUCK IS i = " + i, Toast.LENGTH_SHORT).show();
            //  Toast.makeText(this, "WHAT THE FUCK IS compare = " + comparer, Toast.LENGTH_SHORT).show();
        }
        if(x == 0 && y == 0){
            drawGraph(xyValuesArrayPlant3);

            /**
             for(int l = 0; l < arraySize; l++) {

             Toast.makeText(this, "" + l, Toast.LENGTH_SHORT).show();
             double x = xyValuesArrayTest.get(l).getX();
             double y = xyValuesArrayTest.get(l).getY();
             xySeries.appendData(new DataPoint(x,y), true, 1000);

             }
             */
            //DONT TRY TO ESCAPE
/**

 xySeries.setShape(PointsGraphSeries.Shape.RECTANGLE);
 xySeries.setColor(Color.BLUE);
 xySeries.setSize(22f);

 SensorPlot.getViewport().setScalable(true);
 SensorPlot.getViewport().setScalableY(true);
 SensorPlot.getViewport().setScrollable(true);
 SensorPlot.getViewport().setScrollableY(true);

 SensorPlot.getViewport().setYAxisBoundsManual(true);
 SensorPlot.getViewport().setMaxY(10000);
 SensorPlot.getViewport().setMinY(-10000);

 SensorPlot.getViewport().setXAxisBoundsManual(true);
 SensorPlot.getViewport().setMaxX(100);
 SensorPlot.getViewport().setMinX(-100);

 SensorPlot.addSeries(xySeries);
 */

            //IF NOTHING IS YOUR FAITH THERE IS NO SCENARIO

        }
        return i;
    }

    // private ArrayList<XyValuesTest>
    private void drawGraph(ArrayList<XyValuesPlant3> ArrayPlant3){

        int arraySize = xyValuesArrayPlant3.size();

        for(int l = 0; l < arraySize; l++) {

            Toast.makeText(this, "" + l, Toast.LENGTH_SHORT).show();
            double x = ArrayPlant3.get(l).getX_plant3();
            double y = ArrayPlant3.get(l).getY_plant3();
            xySeries.appendData(new DataPoint(x, y), true, 1000);

            //New LineGraphSeries
            twodimensionalLineSeries.appendData(new DataPoint(x, y), true, 1000);
            //
        }
        xySeries.setShape(PointsGraphSeries.Shape.POINT);
        xySeries.setColor(Color.DKGRAY);
        xySeries.setSize(22f);

        //New LineGraph
        twodimensionalLineSeries.setColor(Color.DKGRAY);
        twodimensionalLineSeries.setThickness(14);
        //

        SensorPlot.getViewport().setScalable(true);
        SensorPlot.getViewport().setScalableY(true);
        SensorPlot.getViewport().setScrollable(true);
        SensorPlot.getViewport().setScrollableY(true);

        SensorPlot.getViewport().setYAxisBoundsManual(true);
        SensorPlot.getViewport().setMaxY(10000);
        SensorPlot.getViewport().setMinY(0);

        SensorPlot.getViewport().setXAxisBoundsManual(true);
        SensorPlot.getViewport().setMaxX(2400);
        SensorPlot.getViewport().setMinX(0);

        SensorPlot.addSeries(xySeries);

        //New LineGraphSeries
        SensorPlot.addSeries(twodimensionalLineSeries);
        //

    }

    private ArrayList<XyValuesPlant3> sortArray(ArrayList<XyValuesPlant3> arrayTest){

        int factor = Integer.parseInt(String.valueOf(Math.round(Math.pow(arrayTest.size(),2))));
        int m = arrayTest.size()-1;
        int count = 0;
        Log.d(TAG, "sortArray: Sorting the XYArray.");

        while(true){
            m--;
            if(m <= 0){
                m = arrayTest.size()-1;
            }
            Log.d(TAG, "sortArray: m z " + m);
            try {
                /**
                 Log.d(TAG, "sortArray: Order: ");
                 for(int n = 0; n < arrayTest.size(); n++){
                 Log.d(TAG, "sortArray: "  +arrayTest.get(n).getY());
                 }
                 */
                double tempY = arrayTest.get(m - 1).getY_plant3();
                double tempX = arrayTest.get(m - 1).getX_plant3();
                if(tempX > arrayTest.get(m).getX_plant3()){
                    arrayTest.get(m - 1).setY_plant3(arrayTest.get(m).getY_plant3());
                    arrayTest.get(m).setY_plant3(tempY);
                    arrayTest.get(m - 1).setX_plant3(arrayTest.get(m).getX_plant3());
                    arrayTest.get(m).setX_plant3(tempX);
                }
                else if(tempY == arrayTest.get(m).getY_plant3()){

                    count++;
                    Log.d(TAG, "sortArray: count = " + count);
                }
                else if(arrayTest.get(m).getX_plant3() > arrayTest.get(m-1).getX_plant3()){

                    count++;
                    Log.d(TAG, "sortArray: count = " + count);
                }
                if(count == factor){
                    break;
                }
            }
            catch(ArrayIndexOutOfBoundsException e){
                Log.d(TAG, "sortArray: ArrayIndexOutOfBoundsException. Need more than 1 data point to create Plot." +
                        e.getMessage());
                break;

            }
        }
        return arrayTest;
    }

}
