package barber.studios.reminderapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarview;
    public Button buttondate;
    int yearfinal,monthfinal,dayfinal;
    TextView mydate;
    int dayOfMonth,month,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        //fab = (FloatingActionButton) findViewById(R.id.fab);
        calendarview = (CalendarView)findViewById(R.id.calendar);
        mydate = (TextView)findViewById(R.id.mydate);
        buttondate = (Button)findViewById(R.id.buttonDate);

        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String date = (dayOfMonth)+  "/" + (month+1) + "/"  + year;
                mydate.setText(date);

                yearfinal = year;
                monthfinal = month;
                dayfinal = dayOfMonth;



            }
        });

        buttondate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (yearfinal == 0) {

                    Toast.makeText(getApplicationContext(), "Pick up a date", Toast.LENGTH_SHORT)
                    .show();

                }
                    else{

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("year", yearfinal);
                    resultIntent.putExtra("month", monthfinal);
                    resultIntent.putExtra("dayOfMonth", dayfinal);
                    setResult(RESULT_OK, resultIntent);
                    finish();

                }

            }
        });


    }
}
