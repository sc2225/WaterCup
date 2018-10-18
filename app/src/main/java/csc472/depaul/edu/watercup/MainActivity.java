package csc472.depaul.edu.watercup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView sugarCup;
    private ImageView waterCup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize cups
        sugarCup = (ImageView) findViewById(R.id.sugarCupId);
        waterCup = (ImageView) findViewById(R.id.waterCupId);

        sugarCup.setOnClickListener(this);
        waterCup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.sugarCupId:
                Toast.makeText(this, "Sugar Cup", Toast.LENGTH_LONG).show();
                break;
            case R.id.waterCupId:
                Toast.makeText(this, "WaterCup", Toast.LENGTH_LONG).show();
                break;

        }
    }
}
