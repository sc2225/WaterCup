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
    SharedPreferences.Editor editor;
    SharedPreferences pref;



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



        alarmIc.setOnClickListener(this);
        settingsIc.setOnClickListener(this);
        statsIc.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //if not sharedPreferences for weight not null, populate the text view.
        if (pref.contains("weight")) {
            weightView.setText(Integer.toString(pref.getInt("weight", 0)));
        } else {
            weightView.setText("Need to set weight");
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
                weightInput = (EditText) findViewById(R.id.editText);

                //if null do not set corresponding singleton attribute
                if (weightInput.getText().toString().isEmpty()) {
                    System.out.println("Null");
                    Toast.makeText(this, "Not a valid weight", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(this, weightInput.getText().toString(), Toast.LENGTH_SHORT).show();
                   // SharedData.getInstance().setWeight(Integer.parseInt(weightInput.getText().toString()));
                    editor.putInt("weight", Integer.parseInt(weightInput.getText().toString()) );
                    editor.apply();

                    int pageNumber = pref.getInt("weight", 0);
                    weightView.setText(Integer.toString(pageNumber));
                    break;
                }

        }
    }
}
