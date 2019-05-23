package barber.studios.reminderapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class DeleteActivity extends AppCompatActivity {
    public DatabaseHelper myDb;
    public ListView listview1;
    public String text1,date,radiobut,hourfinal,date3,reminder,result,position10,position0,minutesfinal;
    public TextView mydate,mydate2,mydate3,textview;
    public int hour,minutes,dayOfMonth,month,year,monthfinal,id,count;
    public long millis;
    public int position1,position2;
    public Button button,edit;
    public TextView text;
    public String itemvalue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        text = (TextView)findViewById(R.id.text);
        Intent myIntent1 = getIntent();
        position1 = myIntent1.getIntExtra("position1",0);
        itemvalue = myIntent1.getStringExtra("itemvalue");
        position2 = position1 +1;
        text.setText(itemvalue);

        button = (Button) findViewById(R.id.button1);
        edit = (Button) findViewById(R.id.edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent resultIntent = new Intent();
                resultIntent.putExtra("position1",String.valueOf(position2));
                resultIntent.putExtra("creation", "delete");
                setResult(RESULT_OK, resultIntent);
                finish();


            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent1 = new Intent(DeleteActivity.this, SettingsActivity.class);
                startActivityForResult(myIntent1, 1);



            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                hour = data.getIntExtra("hour", 0);
                minutes = data.getIntExtra("minutes", 0);

                year = data.getIntExtra("year", 0);
                month = data.getIntExtra("month", 0);
                dayOfMonth = data.getIntExtra("dayOfMonth", 0);

                radiobut = data.getStringExtra("radiobut");
                reminder = data.getStringExtra("reminder");


                Intent resultIntent = new Intent();
                resultIntent.putExtra("position1",String.valueOf(position2));
                resultIntent.putExtra("creation", "edit");
                resultIntent.putExtra("hour", hour);
                resultIntent.putExtra("minutes", minutes);
                resultIntent.putExtra("year", year);
                resultIntent.putExtra("month", month);
                resultIntent.putExtra("dayOfMonth", dayOfMonth);
                resultIntent.putExtra("radiobut", radiobut);
                resultIntent.putExtra("reminder", reminder);
                setResult(RESULT_OK, resultIntent);
                finish();





            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    @Override
    public void onBackPressed() {

        Intent comeback = new Intent(DeleteActivity.this,MainActivity.class);
        startActivity(comeback);
        finish();

    }


}
