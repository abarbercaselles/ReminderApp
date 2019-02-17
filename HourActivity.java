package estudios.com.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class HourActivity extends AppCompatActivity {

    //the timepicker object
    TimePicker timePicker;
    public int hour,minutes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hour);

        //getting the timepicker object
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        //attaching clicklistener on button
        findViewById(R.id.buttonAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //We need a calendar object to get the specified time in millis
                //as the alarm manager method takes time in millis to setup the alarm

                    hour = timePicker.getHour();
                    minutes = timePicker.getMinute();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("hour", hour);
                resultIntent.putExtra("minutes", minutes);
                setResult(RESULT_OK, resultIntent);
                finish();


            }
        });
    }

}

