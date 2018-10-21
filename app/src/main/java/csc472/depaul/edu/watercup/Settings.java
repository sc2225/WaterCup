package csc472.depaul.edu.watercup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    private ImageView alarmIc;
    private ImageView settingsIc;
    private ImageView statsIc;
    private Button submit;
    private EditText weightInput;
    private TextView weightView;
    private EditText ageInput;
    SharedPreferences.Editor editor;
    SharedPreferences pref;
    private TextView waterView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //create and initialize a sharedPreference
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();


        alarmIc= (ImageView) findViewById(R.id.alarmIcon);
        settingsIc = (ImageView) findViewById(R.id.settingsIcon);
        statsIc = (ImageView) findViewById(R.id.statsIcon);
        submit = (Button) findViewById(R.id.submit);
        weightView = (TextView) findViewById(R.id.textWeight) ;
        weightInput = (EditText) findViewById(R.id.editText);
        ageInput = (EditText) findViewById(R.id.editAge);
        waterView = (TextView) findViewById(R.id.waterView);

        alarmIc.setOnClickListener(this);
        settingsIc.setOnClickListener(this);
        statsIc.setOnClickListener(this);
        submit.setOnClickListener(this);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //if not sharedPreferences for weight not null, populate the text view.
        if (pref.contains("weight") && pref.contains("age")) {
            weightView.setText("Your weight and age are set as " + Integer.toString(pref.getInt("weight", 0)) + " lbs, " + Integer.toString(pref.getInt("age", 0)) + " years old." );
            waterView.setText( pref.getInt("baseWater", 0)+ " oz.");
        } else {
            weightView.setText("You have not specified a weight and/or age yet.");
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.alarmIcon:
                startActivity(new Intent(Settings.this, Alarms.class));
                break;
            case R.id.settingsIcon:
                break;
            case R.id.statsIcon:
                startActivity(new Intent(Settings.this, Stats.class));
                break;
            case R.id.submit:

                if (weightInput.getText().toString().isEmpty() || ageInput.getText().toString().isEmpty() || Integer.parseInt(weightInput.getText().toString()) == 0 ||  Integer.parseInt(ageInput.getText().toString()) == 0) {
                    Toast.makeText(this, "Please enter a valid value for age and weight", Toast.LENGTH_SHORT).show();
                } else {

                   // SharedData.getInstance().setWeight(Integer.parseInt(weightInput.getText().toString()));
                    editor.putInt("weight", Integer.parseInt(weightInput.getText().toString()) );
                    editor.apply();

                    editor.putInt("age", Integer.parseInt(ageInput.getText().toString()) );
                    editor.apply();

                    int weight = pref.getInt("weight", 0);
                    int age = pref.getInt("age", 0);
                    weightView.setText("You have set your weight to " +Integer.toString(weight) + " and age to " + age);
                    editor.putInt("baseWater", SharedData.getInstance().getDailyWater(weight, age) );
                    editor.apply();
                    waterView.setText( pref.getInt("baseWater", 0)+ " oz.");

                    break;
                }

        }
    }
}
