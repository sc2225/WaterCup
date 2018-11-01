package csc472.depaul.edu.watercup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView sugarCup;
    private ImageView waterCup;
    private ImageView alarmIc;
    private ImageView settingsIc;
    private ImageView statsIc;
    private ProgressBar progressBar;
    private TextView dialogString;
    private SugarCounterDialog sugardialog;
    private WaterCounterDialog waterdialog;
    private TextView weather;
    private Typeface weatherFont;


/**    For sharedPref, Keys are:
        currentWater -- current water intake
        baseWater -- total FLUID requirement w/no exercise
        currentSugar -- current sugar
        currentFluidIntake-- current TOTAL intake (sugar + water)
        pastDate
        newDate
 **/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("state:", "OnCreate");

        resetDailyConsumption();

//
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
//        SharedPreferences.Editor editer = pref.edit();
//        editer.clear();
//        editer.apply();

        //initialize cups
        sugarCup = (ImageView) findViewById(R.id.sugarCupId);
        waterCup = (ImageView) findViewById(R.id.waterCupId);
        alarmIc = (ImageView) findViewById(R.id.alarmIcon);
        settingsIc = (ImageView) findViewById(R.id.settingsIcon);
        statsIc = (ImageView) findViewById(R.id.statsIcon);
        progressBar = findViewById(R.id.progressBar);
        dialogString = findViewById(R.id.DialogString);

        sugarCup.setOnClickListener(this);
        waterCup.setOnClickListener(this);
        alarmIc.setOnClickListener(this);
        settingsIc.setOnClickListener(this);
        statsIc.setOnClickListener(this);

        setProgressBar();
        setDialogString();

        //code to set weather icons
        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weather.ttf");
        weather = findViewById(R.id.weather);
        weather.setText(getString(R.string.clear_weather));
        weather.setTypeface(weatherFont);

       // weather.setText(new Fetch().execute().toString());


    }

    private void setProgressBar() {
        //get the values for weight and age from sharedprefs
       SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
       int fluidReq = pref.getInt("baseWater", 0);
       int currFluidIntake = pref.getInt("currentFluidIntake", 0);

       progressBar.setMax(fluidReq);
       progressBar.setProgress(currFluidIntake);

    }

    private void setDialogString () {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        int totalWater = pref.getInt("baseWater", 0);

        //TODO: change to TOTALFLUIDINTAKE later. Right now its only reflecting water intake, no sugar
        int currentFluidIntake = pref.getInt("currentFluidIntake", 0);


        if (currentFluidIntake < totalWater ) {
            dialogString.setText("Keep it up! You've only drank " + currentFluidIntake+ "/" + totalWater + " fl. oz");
        } else {
            dialogString.setText("You've reached your daily fluid intake! Good Job!");
        }
    }

    private void resetDailyConsumption (){
        //store current dat in SharedPreference. if "olddate" not in SharedPref, make this olddate AND currentDate
        //compare old date and new date by if olddate == newdate. if a day has passed, store that passed day data in shared pref
        //When 7 days have passed, restart over again

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        Calendar currentTime = Calendar.getInstance();
        int month = currentTime.get(Calendar.MONTH);
        int day = currentTime.get(Calendar.DAY_OF_MONTH);
        int year = currentTime.get(Calendar.YEAR);

        if (!pref.contains("pastdate")) {

            editor.putString("pastdate", month+"/"+day+"/"+year);
            editor.apply();
            editor.putString("newDate", month+"/"+day+"/"+year );
            editor.apply();
        } else {
            editor.putString("newDate", month+"/"+day+"/"+year );
            editor.apply();
        }

        //compare the old and new dates to see if we need to erase the currentWater key-value in sharedpref
        String olddate = pref.getString("pastDate", "" );
        String newdate = pref.getString("newdate", "");

        //if strings are the same, ok. if not, erase the value.
        if (!olddate.equals(newdate)) {
            editor.putInt("currentWater", 0);
            editor.apply();
            System.out.println("THIS SHOULD BE NOT HERE");
        }

    }



    @Override
    public void onClick(View v) {

        View mainLay = findViewById(R.id.mainLay);


        switch(v.getId()) {
            case R.id.sugarCupId:
                showSugarNumberPicker(mainLay);
                break;
            case R.id.waterCupId:
                showWaterNumberPicker(mainLay);
                break;

            case R.id.alarmIcon:
                startActivity(new Intent(MainActivity.this, Alarms.class));
                break;
            case R.id.settingsIcon:
                startActivity(new Intent(MainActivity.this, Settings.class));
                break;
            case R.id.statsIcon:
                startActivity(new Intent(MainActivity.this, Stats.class));
                break;

        }
    }


    public void showSugarNumberPicker(View view) {
        sugardialog = new SugarCounterDialog();
        sugardialog.setValueChangeListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                int newtotalSugar = picker.getValue() + pref.getInt("currentSugar", 0);
                editor.putInt("currentSugar", newtotalSugar);
                editor.apply();

                //add to currentFluidIntake
                int oldtotal = pref.getInt("currentFluidIntake", 0);
                editor.putInt("currentFluidIntake", oldtotal + picker.getValue());
                editor.apply();

                setDialogString();
                setProgressBar();

            }
        });

        sugardialog.show(getSupportFragmentManager(), "sugarpicker");

    }

    public void showWaterNumberPicker (View view) {
        waterdialog = new WaterCounterDialog();
        waterdialog.setValueChangeListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                    //initialize SharedPreferences
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                int newtotalWater = picker.getValue() + pref.getInt("currentWater", 0);
                editor.putInt("currentWater", newtotalWater);
                editor.apply();

                //add to currentFluidIntake
                int oldtotal = pref.getInt("currentFluidIntake", 0);
                editor.putInt("currentFluidIntake", oldtotal + picker.getValue());
                editor.apply();

                setDialogString();
                setProgressBar();
            }
        });

        waterdialog.show(getSupportFragmentManager(), "numPicker");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("state:", "OnPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("state:", "OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("state:", "OnResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("state:", "OnStop");
    }
}
