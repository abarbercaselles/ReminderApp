package barber.studios.reminderapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {

    private MediaPlayer SoundMP;

    public ListView listviewset;
    public int hour=100, minutes, dayOfMonth, month, year;
    public long calendar;
    public String radiobut = "Once",radiobut1 = "Wisdom", hourminutes, yearmonth, repeat, creation,song ,songs;
    public TextView text;
    public EditText textedit;
    public Button save,back;
    public TimePicker timePicker;

    public int yearfinal=100, monthfinal, dayfinal,monthcomparar,daycomparar,hourcomparar,mincomparar;

    public FloatingActionButton fabsett;
    public ArrayList<String> valuesset;
    public ArrayAdapter<String> adapterset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        save = (Button) findViewById(R.id.save);
        back = (Button) findViewById(R.id.back);

        text = (TextView) findViewById(R.id.text);
        textedit = (EditText) findViewById(R.id.textedit);
        listviewset = (ListView) findViewById(R.id.listview1);

        valuesset = new ArrayList<String>();

        valuesset.add("TIME");
        valuesset.add("DATE");
        valuesset.add("REPEAT");
        valuesset.add("RINGTONE");






        adapterset = new ArrayAdapter<String>(SettingsActivity.this,
                android.R.layout.simple_list_item_2, android.R.id.text2, valuesset) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text2.setTextColor(Color.WHITE);
                return view;
            }
        };

        listviewset.setAdapter(adapterset);
        adapterset.notifyDataSetChanged();

        valuesset.set(2, "REPEAT:   Once");
        adapterset.notifyDataSetChanged();
        valuesset.set(3, "RINGTONE:   Wisdom");
        adapterset.notifyDataSetChanged();


        listviewset.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                                               @Override
                                               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                                                   String itemValue = (String) listviewset.getItemAtPosition(position);

                                                   if (position == 0) {


                                                       final Dialog dialog1 = new Dialog(SettingsActivity.this);
                                                       dialog1.setContentView(R.layout.hour);

                                                       Button buttonapply = (Button) dialog1.findViewById(R.id.buttonAlarm);

                                                       buttonapply.setOnClickListener(new View.OnClickListener() {
                                                           @Override
                                                           public void onClick(View v) {


                                                               timePicker = (TimePicker) dialog1.findViewById(R.id.timePicker);

                                                               hour = timePicker.getHour();
                                                               minutes = timePicker.getMinute();

                                                               if (minutes < 10) {

                                                                   hourminutes = "TIME :   " + String.valueOf(hour) + ":0" + String.valueOf(minutes);

                                                               } else {
                                                                   hourminutes = "TIME :   " + String.valueOf(hour) + ":" + String.valueOf(minutes);
                                                               }
                                                               valuesset.set(0, hourminutes);

                                                               adapterset.notifyDataSetChanged();

                                                               dialog1.dismiss();
                                                           }

                                                       });

                                                       dialog1.show();
                                                   }

                                                   if (position == 1) {

                                                       final Dialog dialog1 = new Dialog(SettingsActivity.this);
                                                       dialog1.setContentView(R.layout.calendar);
                                                       CalendarView calendarview;
                                                       Button buttonapply = (Button) dialog1.findViewById(R.id.buttonDate);
                                                       calendarview = (CalendarView) dialog1.findViewById(R.id.calendar);
                                                       calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                                                           @Override
                                                           public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                                                               String date = (dayOfMonth) + "/" + (month + 1) + "/" + year;
                                                               TextView mydate;
                                                               mydate = (TextView) dialog1.findViewById(R.id.mydate);

                                                               mydate.setText(date);
                                                               yearfinal = year;
                                                               monthfinal = month;
                                                               dayfinal = dayOfMonth;


                                                           }
                                                       });
                                                       buttonapply.setOnClickListener(new View.OnClickListener() {
                                                           @Override
                                                           public void onClick(View v) {

                                                               yearmonth = "DATE :    " + String.valueOf(dayfinal) + "/" + String.valueOf(monthfinal + 1) + "/" + String.valueOf(yearfinal);

                                                               valuesset.set(1, yearmonth);

                                                               adapterset.notifyDataSetChanged();


                                                               dialog1.dismiss();
                                                           }
                                                       });

                                                       dialog1.show();
                                                   }


                                                   if (position == 2) {


                                                       final Dialog dialog1 = new Dialog(SettingsActivity.this);
                                                       dialog1.setContentView(R.layout.radiobutton);

                                                       Button buttonapply = (Button) dialog1.findViewById(R.id.button_apply);
                                                       // if button is clicked, close the custom dialog
                                                       buttonapply.setOnClickListener(new View.OnClickListener() {
                                                           @Override
                                                           public void onClick(View v) {

                                                               RadioButton radioButton;
                                                               RadioGroup radioGroup;

                                                               radioGroup = (RadioGroup) dialog1.findViewById(R.id.radioGroup);

                                                               int radioId = radioGroup.getCheckedRadioButtonId();

                                                               radioButton = (RadioButton)dialog1.findViewById(radioId);

                                                               radiobut = radioButton.getText().toString();

                                                               yearmonth = "REPEAT :   " + radiobut;

                                                               valuesset.set(2, yearmonth);

                                                               adapterset.notifyDataSetChanged();


                                                               dialog1.dismiss();
                                                           }
                                                       });

                                                       dialog1.show();
                                                   }

                                                   if (position == 3) {


                                                       final Dialog dialog1 = new Dialog(SettingsActivity.this);
                                                       dialog1.setContentView(R.layout.songs);



                                                       Button buttonsave = (Button) dialog1.findViewById(R.id.button_save);
                                                       // if button is clicked, close the custom dialog
                                                       buttonsave.setOnClickListener(new View.OnClickListener() {
                                                           @Override
                                                           public void onClick(View v) {

                                                               RadioButton radioButton;
                                                               RadioGroup radioGroup;

                                                               radioGroup = (RadioGroup) dialog1.findViewById(R.id.radioGroup);

                                                               int radioId = radioGroup.getCheckedRadioButtonId();

                                                               radioButton = (RadioButton)dialog1.findViewById(radioId);

                                                               radiobut1 = radioButton.getText().toString();

                                                               if (radiobut1==null){



                                                               }

                                                               song = "RINGTONE :   " + radiobut1;
                                                               songs = radiobut1;

                                                               valuesset.set(3, song);

                                                               adapterset.notifyDataSetChanged();


                                                               dialog1.dismiss();


                                                               if (SoundMP == null){

                                                               }
                                                               else if (SoundMP.isPlaying()){

                                                                   SoundMP.stop();

                                                               }



                                                           }
                                                       });



                                                       Button buttonplay = (Button) dialog1.findViewById(R.id.button_play);
                                                       // if button is clicked, close the custom dialog
                                                       buttonplay.setOnClickListener(new View.OnClickListener() {
                                                           @Override
                                                           public void onClick(View v) {

                                                               if (SoundMP == null){

                                                               }

                                                               else if(SoundMP.isPlaying()){

                                                                   SoundMP.stop();
                                                               }


                                                               RadioButton radioButton;
                                                               RadioGroup radioGroup;

                                                               radioGroup = (RadioGroup) dialog1.findViewById(R.id.radioGroup);

                                                               int radioId = radioGroup.getCheckedRadioButtonId();

                                                               radioButton = (RadioButton)dialog1.findViewById(radioId);

                                                               radiobut1 = radioButton.getText().toString();


                                                               switch(radiobut1){


                                                                   case "Wisdom":

                                                                       SoundMP = MediaPlayer.create(SettingsActivity.this, R.raw.horseproject);
                                                                      // SoundMP.start();
                                                                       break;
                                                                   case "Rythym":

                                                                       SoundMP = MediaPlayer.create(SettingsActivity.this, R.raw.rumberaproject);
                                                                       //SoundMP.start();
                                                                       break;
                                                                   case "Classic":

                                                                       SoundMP = MediaPlayer.create(SettingsActivity.this, R.raw.johannesproject);
                                                                       //SoundMP.start();
                                                                       break;
                                                                   case "Mexico":

                                                                       SoundMP = MediaPlayer.create(SettingsActivity.this, R.raw.julietaproject);
                                                                       //SoundMP.start();
                                                                       break;
                                                                   case "Happy":

                                                                       SoundMP = MediaPlayer.create(SettingsActivity.this, R.raw.vampirepunkproject);
                                                                       //SoundMP.start();
                                                                       break;

                                                                   default:
                                                                       SoundMP = MediaPlayer.create(SettingsActivity.this, R.raw.horseproject);
                                                                       //SoundMP.start();
                                                                       break;


                                                               }

                                                                 SoundMP.start();

                                                               }





                                                       });




                                                       dialog1.show();
                                                   }


                                                   save.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {

                                                           Calendar calendar = Calendar.getInstance();


                                                           monthcomparar = calendar.get(Calendar.MONTH);
                                                           daycomparar = calendar.get(Calendar.DAY_OF_MONTH);
                                                           hourcomparar = calendar.get(Calendar.HOUR_OF_DAY);
                                                           mincomparar = calendar.get(Calendar.MINUTE);



                                                           if (textedit==null || (hour == 100) || (yearfinal == 100)) {

                                                               Toast.makeText(SettingsActivity.this, "Complete all the fields", Toast.LENGTH_SHORT).show();


                                                               if (textedit.getText().toString().equals("")) {

                                                                   Toast.makeText(SettingsActivity.this, "Write some reminder", Toast.LENGTH_SHORT).show();
                                                               }



                                                           }


                                                           else if (monthfinal < monthcomparar){

                                                               Toast.makeText(SettingsActivity.this, "Set up a date for the future", Toast.LENGTH_SHORT).show();


                                                           }

                                                           else if (monthfinal== monthcomparar && dayfinal<daycomparar){

                                                               Toast.makeText(SettingsActivity.this, "Set up a date for the future", Toast.LENGTH_SHORT).show();


                                                           }

                                                           else if (monthfinal== monthcomparar && dayfinal==daycomparar && hour<hourcomparar){

                                                               Toast.makeText(SettingsActivity.this, "Set up a date for the future", Toast.LENGTH_SHORT).show();


                                                           }

                                                           else if (monthfinal== monthcomparar && dayfinal==daycomparar && hour==hourcomparar && minutes<mincomparar){

                                                               Toast.makeText(SettingsActivity.this, "Set up a date for the future", Toast.LENGTH_SHORT).show();


                                                           }



                                                            else {

                                                               Intent resultIntent = new Intent();
                                                               resultIntent.putExtra("hour", hour);
                                                               resultIntent.putExtra("minutes", minutes);
                                                               resultIntent.putExtra("year", yearfinal);
                                                               resultIntent.putExtra("month", monthfinal);
                                                               resultIntent.putExtra("dayOfMonth", dayfinal);
                                                               resultIntent.putExtra("radiobut", radiobut);
                                                               resultIntent.putExtra("reminder", textedit.getText().toString());
                                                               resultIntent.putExtra("song", radiobut1);
                                                               resultIntent.putExtra("comeback", "no");
                                                               setResult(RESULT_OK, resultIntent);
                                                               finish();
                                                           }


                                                       }
                                                   });





                                               }

                                           }
                     );

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultIntent = new Intent();
                resultIntent.putExtra("comeback", "yes");

                setResult(RESULT_OK, resultIntent);
                finish();


            }
        });


             }

    @Override
    protected void onStop() {

        if (SoundMP==null){

        }
        else{

            SoundMP.stop();
        }

        super.onStop();
    }

    protected void onPause() {

        if (SoundMP==null){


        }
else{

            SoundMP.stop();
        }


        super.onPause();
    }

    @Override
    public void onBackPressed() {


        Intent resultIntent = new Intent();
        resultIntent.putExtra("comeback", "yes");

        setResult(RESULT_OK, resultIntent);
        finish();

    }


          }
