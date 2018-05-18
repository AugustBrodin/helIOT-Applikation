package org.intracode.heliot;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.Random;

public class SensorPlotTest extends AppCompatActivity {
    private static final String TAG = "SensorPlotTest";

    PointsGraphSeries<DataPoint> xySeries;

    GraphView mScatterPlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_plot_test);

        mScatterPlot = (GraphView)findViewById(R.id.sensorPlotTest_scatterPlot);

        createScatterPlot();
    }

    private void createScatterPlot(){

        Log.d(TAG, "createScatterPlot: Creating scatter plot");

        xySeries = new PointsGraphSeries();

        ArrayList<XyValuesTest> xyValuesArrayTest = new ArrayList<>();

        double start = -100;
        double end = 100;
        for(int i = 0; i<40; i++) {
            double randomX = new Random().nextDouble();
            double randomY = new Random().nextDouble();

            double x = start + (randomX * (end - start));
            double y = start + (randomY * (end - start));

            xyValuesArrayTest.add(new XyValuesTest(x, y));
/**
            xySeries.appendData(new DataPoint(1.0, 2.0), true, 1000);
            xySeries.appendData(new DataPoint(9.0, -55.0), true, 1000);
            xySeries.appendData(new DataPoint(7.0, -22.0), true, 1000);
 */
        }
        xyValuesArrayTest = sortArray(xyValuesArrayTest);

        for(int i = 0; i < xyValuesArrayTest.size(); i++){

            double x = xyValuesArrayTest.get(i).getX();
            double y = xyValuesArrayTest.get(i).getY();
            xySeries.appendData(new DataPoint(x,y), true, 1000);
        }

        xySeries.setShape(PointsGraphSeries.Shape.POINT);
        xySeries.setColor(Color.BLUE);
        xySeries.setSize(22f);

        mScatterPlot.getViewport().setScalable(true);
        mScatterPlot.getViewport().setScalableY(true);
        mScatterPlot.getViewport().setScrollable(true);
        mScatterPlot.getViewport().setScrollableY(true);

        mScatterPlot.getViewport().setYAxisBoundsManual(true);
        mScatterPlot.getViewport().setMaxY(100);
        mScatterPlot.getViewport().setMinY(-100);

        mScatterPlot.getViewport().setXAxisBoundsManual(true);
        mScatterPlot.getViewport().setMaxX(100);
        mScatterPlot.getViewport().setMinX(-100);

        mScatterPlot.addSeries(xySeries);

    }

    private ArrayList<XyValuesTest> sortArray(ArrayList<XyValuesTest> arrayTest){

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
              double tempY = arrayTest.get(m - 1).getY();
              double tempX = arrayTest.get(m - 1).getX();
              if(tempX > arrayTest.get(m).getX()){
              arrayTest.get(m - 1).setY(arrayTest.get(m).getY());
              arrayTest.get(m).setY(tempY);
              arrayTest.get(m - 1).setX(arrayTest.get(m).getX());
              arrayTest.get(m).setX(tempX);
          }
          else if(tempY == arrayTest.get(m).getY()){

               count++;
               Log.d(TAG, "sortArray: count = " + count);
              }
              else if(arrayTest.get(m).getX() > arrayTest.get(m-1).getX()){

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
