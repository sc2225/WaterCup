package csc472.depaul.edu.watercup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements View.OnClickListener {

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


        ImageView alarmIc = findViewById(R.id.alarmIcon);
        ImageView settingsIc = findViewById(R.id.settingsIcon);
        ImageView statsIc = findViewById(R.id.statsIcon);
        ImageView homeIc = findViewById(R.id.homeIcon);
        Button submit = findViewById(R.id.submit);
        weightView = findViewById(R.id.textWeight) ;
        weightInput = findViewById(R.id.editText);
        ageInput = findViewById(R.id.editAge);
        waterView = findViewById(R.id.waterView);
        TextView logo = findViewById(R.id.logo);


        alarmIc.setOnClickListener(this);
        settingsIc.setOnClickListener(this);
        statsIc.setOnClickListener(this);
        submit.setOnClickListener(this);
        homeIc.setOnClickListener(this);

        //set the main logo
        Typeface weatherFont = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");
        logo.setText(getString(R.string.waterdrop));
        logo.setTypeface(weatherFont);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //if not sharedPreferences for weight not null, populate the text view.
        if (pref.contains("weight") && pref.contains("age")) {

            String dialog = "weight and age set as " + Integer.toString(pref.getInt("weight", 0)) + " lbs., " + Integer.toString(pref.getInt("age", 0)) + " years old";
            weightView.setText(dialog);


            String drinkDialog = "You need to drink " + pref.getInt("baseWater", 0)+ " fl. oz.";
            waterView.setText(drinkDialog);
        } else {
            weightView.setText(R.string.water_dialog_notset);
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
            case R.id.homeIcon:
                startActivity(new Intent(Settings.this, MainActivity.class));
                break;
            case R.id.submit:

                if (weightInput.getText().toString().isEmpty() || ageInput.getText().toString().isEmpty() || Integer.parseInt(weightInput.getText().toString()) == 0 ||  Integer.parseInt(ageInput.getText().toString()) == 0) {
                    Toast.makeText(this, "Please enter a valid value for age and weight", Toast.LENGTH_SHORT).show();
                } else {

                    editor.putInt("weight", Integer.parseInt(weightInput.getText().toString()) );
                    editor.apply();

                    editor.putInt("age", Integer.parseInt(ageInput.getText().toString()) );
                    editor.apply();

                    int weight = pref.getInt("weight", 0);
                    int age = pref.getInt("age", 0);
                    String weightSet ="You have set your weight to " +Integer.toString(weight) + " and age to " + age;
                    weightView.setText(weightSet);
                    editor.putInt("baseWater", SharedData.getInstance().getDailyWater(weight, age) );
                    editor.apply();
                    String waterReq ="Daily Requirement: "+ pref.getInt("baseWater", 0)+ " fl. oz.";
                    waterView.setText(waterReq);

                    break;
                }

        }
    }
}
