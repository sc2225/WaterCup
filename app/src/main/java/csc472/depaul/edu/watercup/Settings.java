package csc472.depaul.edu.watercup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    private ImageView alarmIc;
    private ImageView settingsIc;
    private ImageView statsIc;
    private Button submit;
    private EditText weightInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        alarmIc= (ImageView) findViewById(R.id.alarmIcon);
        settingsIc = (ImageView) findViewById(R.id.settingsIcon);
        statsIc = (ImageView) findViewById(R.id.statsIcon);
        submit = (Button) findViewById(R.id.submit);

        alarmIc.setOnClickListener(this);
        settingsIc.setOnClickListener(this);
        statsIc.setOnClickListener(this);
        submit.setOnClickListener(this);
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
                Toast.makeText(this, weightInput.getText().toString(), Toast.LENGTH_SHORT).show();
                break;


        }
    }
}
