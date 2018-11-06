package csc472.depaul.edu.watercup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.util.ArrayList;
import java.util.List;

public class Stats extends AppCompatActivity implements View.OnClickListener {
    private ImageView alarmIc;
    private ImageView settingsIc;
    private ImageView statsIc;
    private ImageView homeIc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        alarmIc = (ImageView) findViewById(R.id.alarmIcon);
        settingsIc = (ImageView) findViewById(R.id.settingsIcon);
        statsIc = (ImageView) findViewById(R.id.statsIcon);
        homeIc = findViewById(R.id.homeIcon);

        alarmIc.setOnClickListener(this);
        settingsIc.setOnClickListener(this);
        statsIc.setOnClickListener(this);
        homeIc.setOnClickListener(this);

        Pie pie = AnyChart.pie();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

        int sugarIntake = pref.getInt("currentSugar", 0);
        int waterIntake = pref.getInt("currentWater", 0);




        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Sugar Intake", sugarIntake));
        data.add(new ValueDataEntry("Water Intake", waterIntake));

        pie.data(data);
        pie.title("Sugar vs Water Consumption (fl. oz.)");
        pie.labels().position("outside");
        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        AnyChartView anyChartView =  (AnyChartView)findViewById(R.id.any_chart_view);
        anyChartView.setChart(pie);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.alarmIcon:
                startActivity(new Intent(Stats.this, Alarms.class));
                break;
            case R.id.settingsIcon:
                startActivity(new Intent(Stats.this, Settings.class));
                break;
            case R.id.statsIcon:
                break;
            case R.id.homeIcon:
                startActivity(new Intent(Stats.this, MainActivity.class));
                break;

        }
    }
}
