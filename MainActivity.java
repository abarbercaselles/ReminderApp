package estudios.com.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final String TASKS_FILE = "estudios.com.myapplication.TasksFile";
    public static final String NUM_TASKS = "NumOfTasks";
    public static final String VALUES = "value";

    public DatabaseHelper myDb;
    public ListView listview1;
    public String text1,date,radiobut,hourfinal,date3,reminder,result,position1,position0,minutesfinal;
    public TextView mydate,mydate2,mydate3,textview;
    public int hour,minutes,dayOfMonth,month,year,monthfinal,id,count;
    public long millis;

    public FloatingActionButton fab;

    public ArrayList<String> values;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        //mydate = (TextView) findViewById(R.id.mydate);
        //mydate2 = (TextView) findViewById(R.id.mydate2);
        listview1 = (ListView) findViewById(R.id.listview1);

        values = new ArrayList<String>();


        //adapter = new ArrayAdapter<String>(this,
                //android.R.layout.simple_list_item_1, android.R.id.text1, values);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                text1.setTextColor(Color.WHITE);
                return view;
            };
        };



        listview1.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Values();

        id = values.size();

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;

                String itemValue = (String) listview1.getItemAtPosition(position);

                Intent myIntent1 = new Intent(MainActivity.this, DeleteActivity.class);

                myIntent1.putExtra("position1",itemPosition);
                myIntent1.putExtra("itemvalue",itemValue);

                startActivityForResult(myIntent1, 3);



               // Toast.makeText(getApplicationContext(), "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        //.show();
            }
        });


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent myIntent1 = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(myIntent1, 2);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {


                hour = data.getIntExtra("hour", 0);
                minutes = data.getIntExtra("minutes", 0);

                year = data.getIntExtra("year", 0);
                month = data.getIntExtra("month", 0);
                dayOfMonth = data.getIntExtra("dayOfMonth", 0);

                radiobut = data.getStringExtra("radiobut");
                reminder = data.getStringExtra("reminder");

                monthfinal = month + 1;
                if (minutes < 10){

                    minutesfinal = "0" + String.valueOf(minutes);

                }
                else {

                    minutesfinal = String.valueOf(minutes);

                }




                Calendar calendar = Calendar.getInstance();

                hourfinal = hour + ":" + minutesfinal;

                date = dayOfMonth + "/" + monthfinal + "/" + year;
                //mydate.setText(hourfinal);


                calendar.set(year, month, dayOfMonth,
                        hour, minutes, 1);


                result = date + "   " + hourfinal + "   " + reminder;

                values.add(date + "   " + hourfinal + "   " + reminder + "     " + radiobut);
                adapter.notifyDataSetChanged();
                // calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                //  hour, minutes, 0);


                id = values.size();
                //mydate.setText(String.valueOf(id));
                AddData();

                AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                Intent i = new Intent(MainActivity.this, MyAlarm.class);
                i.putExtra("id",id);

                PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, id, i, 0);


                //Cursor res = myDb.getAllData(id);

                // Calendar calendar = Calendar.getInstance();

                // calendar.set(res.getInt(1), res.getInt(2),res.getInt(3),
                //res.getInt(4), res.getInt(5), 1);


                //setting the repeating alarm that will be fired every day

                if (radiobut.equals("Once")) {
                    am.set(AlarmManager.RTC, calendar.getTimeInMillis(), pi);

                }

                if (radiobut.equals("Hourly")) {
                    am.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pi);

                }
                if (radiobut.equals("Daily")) {
                    am.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);

                }
                if (radiobut.equals("Weekly")) {
                    am.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pi);

                }
                if (radiobut.equals("Monthly")) {
                    am.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 365, pi);

                }

                //values.add(res.getString(8));


                // setAlarm(calendar.getTimeInMillis());

                // date3 = String.valueOf(calendar.getTimeInMillis());
                // mydate.setText(date3);

            }

            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result


            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {


                position0 = data.getStringExtra("position1");
                Integer deletedRows = myDb.deleteData(position0);

                values.remove(Integer.valueOf(position0)-1);
                adapter.notifyDataSetChanged();

                /*
                AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, id, i, 0);
*/

                if(deletedRows > 0)
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();

                

            }

            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result


            }
        }



    }




    public  void AddData() {

                        boolean isInserted = myDb.insertData(id,dayOfMonth,
                                month,
                                year,
                                minutes,
                                hour,
                                reminder,
                                radiobut,
                                result);

                        if(isInserted == true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }



     public void Values(){

         Cursor res = myDb.getAllData();
         if(res.getCount() == 0) {
             // show message
             return;
         }

         while (res.moveToNext()) {
             values.add(res.getString(8));

         }

     }

     public void Counting(){

         Cursor c = myDb.Count();
         System.out.println(c.getCount());


     }




     }





