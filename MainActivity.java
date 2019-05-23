package barber.studios.reminderapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TASKS_FILE = "estudios.com.myapplication.TasksFile";
    public static final String NUM_TASKS = "NumOfTasks";
    public static final String VALUES = "value";

    public NavigationView navigationView;
    public DrawerLayout drawerLayout;
    public MediaPlayer SoundMP;
    public barber.studios.reminderapp.DatabaseHelper myDb;
    public ListView listview1;
    public String text1, date, radiobut, hourfinal, date3, reminder, result, position1, position0, minutesfinal, songfinal, songs, creation,comeback;
    public TextView mydate, mydate2, mydate3, textview, titlesong;
    public int hour, minutes, dayOfMonth, month, year, monthfinal, id, count;
    public long millis;
    public Toolbar toolbar;
    public ActionBar actionbar;

    public FloatingActionButton fab;

    public ArrayList<String> values;

    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);














        SoundMP = MediaPlayer.create(MainActivity.this, R.raw.genius);

        myDb = new barber.studios.reminderapp.DatabaseHelper(this);

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
            }

            ;
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

                Intent myIntent1 = new Intent(MainActivity.this, barber.studios.reminderapp.DeleteActivity.class);

                myIntent1.putExtra("position1", itemPosition);
                myIntent1.putExtra("itemvalue", itemValue);

                startActivityForResult(myIntent1, 3);


                // Toast.makeText(getApplicationContext(), "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                //.show();
            }
        });


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent1 = new Intent(MainActivity.this, barber.studios.reminderapp.SettingsActivity.class);
                startActivityForResult(myIntent1, 2);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {

                comeback = data.getStringExtra("comeback");

                if (comeback.equals("yes")){

                }

                if (comeback.equals("no"))
                {

                    hour = data.getIntExtra("hour", 0);
                    minutes = data.getIntExtra("minutes", 0);

                    year = data.getIntExtra("year", 0);
                    month = data.getIntExtra("month", 0);
                    dayOfMonth = data.getIntExtra("dayOfMonth", 0);


                    songfinal = data.getStringExtra("song");
                    radiobut = data.getStringExtra("radiobut");
                    reminder = data.getStringExtra("reminder");

                    monthfinal = month + 1;
                    if (minutes < 10) {

                        minutesfinal = "0" + String.valueOf(minutes);

                    } else {

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
                    // calendar.set(calendar.get(Calendar.YEAR), calendar.get((Calendar.MONTH)+1), calendar.get(Calendar.DAY_OF_MONTH),
                    //  hour, minutes, 0);


                    id = values.size();
                    //mydate.setText(String.valueOf(id));
                    AddData();

                    AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                    Intent i = new Intent(MainActivity.this, barber.studios.reminderapp.MyAlarm.class);
                    i.putExtra("id", id);
                    i.putExtra("songfinal", songfinal);
                    i.putExtra("open", 1);

                    PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, id, i, PendingIntent.FLAG_CANCEL_CURRENT);


                    //Cursor res = myDb.getAllData(id);

                    // Calendar calendar = Calendar.getInstance();

                    // calendar.set(res.getInt(1), res.getInt(2),res.getInt(3),
                    //res.getInt(4), res.getInt(5), 1);


                    //setting the repeating alarm that will be fired every day

                    if (radiobut.equals("Once")) {
                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);

                    }

                    if (radiobut.equals("Hourly")) {
                        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pi);

                    }
                    if (radiobut.equals("Daily")) {
                        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);

                    }
                    if (radiobut.equals("Weekly")) {
                        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pi);

                    }
                    if (radiobut.equals("Monthly")) {
                        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 365, pi);

                    }

                    //values.add(res.getString(8));


                    // setAlarm(calendar.getTimeInMillis());

                    // date3 = String.valueOf(calendar.getTimeInMillis());
                    // mydate.setText(date3);
                }

            }

            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result


            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {

                creation = data.getStringExtra("creation");
            }

            if (creation.equals("delete")) {
                position0 = data.getStringExtra("position1");
                Integer deletedRows = myDb.deleteData(position0);

                values.remove(Integer.valueOf(position0) - 1);
                adapter.notifyDataSetChanged();

                if (deletedRows > 0)
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
            } else {

                position0 = data.getStringExtra("position1");
                hour = data.getIntExtra("hour", 0);
                minutes = data.getIntExtra("minutes", 0);

                year = data.getIntExtra("year", 0);
                month = data.getIntExtra("month", 0);
                dayOfMonth = data.getIntExtra("dayOfMonth", 0);

                radiobut = data.getStringExtra("radiobut");
                reminder = data.getStringExtra("reminder");

                monthfinal = month + 1;
                if (minutes < 10) {

                    minutesfinal = "0" + String.valueOf(minutes);

                } else {

                    minutesfinal = String.valueOf(minutes);

                }


                Calendar calendar = Calendar.getInstance();

                hourfinal = hour + ":" + minutesfinal;

                date = dayOfMonth + "/" + monthfinal + "/" + year;


                calendar.set(year, month, dayOfMonth,
                        hour, minutes, 1);


                result = date + "   " + hourfinal + "   " + reminder;

                //values.add(date + "   " + hourfinal + "   " + reminder + "     " + radiobut);
                //adapter.notifyDataSetChanged();


                myDb.updateData(String.valueOf(id), String.valueOf(dayOfMonth), String.valueOf(month), String.valueOf(year), String.valueOf(minutes), String.valueOf(hour), reminder, radiobut, result);

                AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                Intent i = new Intent(MainActivity.this, MyAlarm.class);
                i.putExtra("id", id);

                PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, id, i, PendingIntent.FLAG_CANCEL_CURRENT);


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


                values.set(Integer.valueOf(position0) - 1, result);
                adapter.notifyDataSetChanged();


            }

            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result


            }
        }


    }


    public void AddData() {

        boolean isInserted = myDb.insertData(id, dayOfMonth,
                month,
                year,
                minutes,
                hour,
                reminder,
                radiobut,
                result);

        if (isInserted == true)
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
    }


    public void Values() {

        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            // show message
            return;
        }

        while (res.moveToNext()) {
            values.add(res.getString(8));

        }

    }

    public void Counting() {

        Cursor c = myDb.Count();
        System.out.println(c.getCount());


    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_settings) {

            if (SoundMP.isPlaying()) {
                pausePlayer();
            } else {
                startPlayer();
            }
        }



        return super.onOptionsItemSelected(item);

    }

    */

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (id == R.id.nav_manage) {

            Intent myIntent10 = new Intent(MainActivity.this, barber.studios.reminderapp.DrawerSettings.class);
            startActivity(myIntent10);
           // drawer.closeDrawers();


        } else if (id == R.id.nav_camera) {

            Intent myIntent10 = new Intent(MainActivity.this, barber.studios.reminderapp.SongsPack.class);
            startActivity(myIntent10);
           // drawer.closeDrawers();

        }  else if (id == R.id.nav_gallery) {

            Intent myIntent10 = new Intent(MainActivity.this, barber.studios.reminderapp.Quotes.class);
            startActivity(myIntent10);
           // drawer.closeDrawers();

        } else if (id == R.id.nav_suggestions) {

            Intent myIntent10 = new Intent(MainActivity.this, barber.studios.reminderapp.Suggestions.class);
            startActivity(myIntent10);
           // drawer.closeDrawers();

        } else if (id == R.id.nav_share) {

            Intent myIntent10 = new Intent(MainActivity.this, barber.studios.reminderapp.Feedback.class);
            startActivity(myIntent10);
            //drawer.closeDrawers();

        } else if (id == R.id.nav_send) {

            Intent myIntent10 = new Intent(MainActivity.this, barber.studios.reminderapp.Donate.class);
            startActivity(myIntent10);
            //drawer.closeDrawers();

        }


        return true;
    }

    @Override
    public void onBackPressed() {

    }



    @Override
    protected void onPause() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawers();
        super.onPause();
    }



}



/*
    @Override
    protected void onStop() {

        if (SoundMP.isPlaying()) {
            pausePlayer();
        }
        super.onStop();
    }


    private void startPlayer() {
        SoundMP.start();

    }
    private void pausePlayer() {
        SoundMP.pause();


    }
*/







