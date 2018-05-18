package org.intracode.heliot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class SensorPlots extends AppCompatActivity {

    LineGraphSeries<DataPoint> humidity_series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_plots);

        double x,y;
        x = -200.0;

        GraphView graph = (GraphView)findViewById(R.id.sensorplots_graph);
        humidity_series = new LineGraphSeries<DataPoint>();
        for(int i=0; i<100; i++){
            x = x + 0.1;
            y = x+1;
            humidity_series.appendData(new DataPoint(x,y), true, 100);
        }
        graph.addSeries(humidity_series);
    }
}
