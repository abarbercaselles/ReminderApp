package estudios.com.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {

    public ListView listviewset;
    public int hour,minutes,dayOfMonth,month,year;
    public long calendar;
    public String radiobut,hourminutes,yearmonth,repeat;
    public TextView text;
    public EditText textedit;
    public Button save;

    public FloatingActionButton fabsett;
    public ArrayList<String> valuesset;
    public ArrayAdapter<String> adapterset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        save = (Button) findViewById(R.id.save);
        text = (TextView) findViewById(R.id.text);
        textedit = (EditText) findViewById(R.id.textedit);
        listviewset = (ListView) findViewById(R.id.listview1);

        valuesset = new ArrayList<String>();

        valuesset.add("TIME");
        valuesset.add("DATE");
        valuesset.add("REPEAT");
/*
        String[] valuesset = new String[] { "DATE",
                "TIME",
                "REPEAT",

        };
*/
        //adapterset = new ArrayAdapter<String>(this,
               // android.R.layout.simple_list_item_2, android.R.id.text2, valuesset);


        adapterset = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2, android.R.id.text2, valuesset) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text2.setTextColor(Color.WHITE);
                return view;
            };
        };

        listviewset.setAdapter(adapterset);
        adapterset.notifyDataSetChanged();



        listviewset.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition     = position;

                String  itemValue    = (String) listviewset.getItemAtPosition(position);
                //Toast.makeText(getApplicationContext(), "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                       // .show();

                if (itemPosition == 0){

                Intent myIntent = new Intent(SettingsActivity.this, HourActivity.class);
                startActivityForResult(myIntent, 1);

                }

                if (itemPosition == 1){

                    Intent myIntent = new Intent(SettingsActivity.this, CalendarActivity.class);
                    startActivityForResult(myIntent, 2);
                    /*
                    Dialog dialog = new Dialog(SettingsActivity.this);
                    dialog.setContentView(R.layout.hour);
                    dialog.setTitle("Card Payment");
                    dialog.setCancelable(true);
                    dialog.show();
                    */

                }

                if (itemPosition == 2) {

                    Intent myIntent = new Intent(SettingsActivity.this, RadiobuttonActivity.class);
                    startActivityForResult(myIntent, 3);
                }
                    /*
                    final Dialog dialog1 = new Dialog(SettingsActivity.this);
                    dialog1.setContentView(R.layout.radiobutton);

                    Button buttonapply = (Button) dialog1.findViewById(R.id.button_apply);
                    // if button is clicked, close the custom dialog
                    buttonapply.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {



                            //accept the changes or modifications

                            dialog1.dismiss();
                        }
                    });

                    dialog1.show();
                }
*/
            }


        });





/*
                AlertDialog.Builder a_builder = new AlertDialog.Builder(SettingsActivity.this);

                a_builder.setMessage("Are you sure you want to delete the data? ")
                        .setCancelable(false)

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent resultIntent = new Intent();
                                resultIntent.putExtra("delete", "delete");
                                resultIntent.putExtra("position", position);

                                setResult(RESULT_OK, resultIntent);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Delete Info");
                alert.show();

*/




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (textedit.getText().toString().equals("")  ||  (hour == 0) ||  (year == 0)  || (radiobut == null)  ){


                    if (textedit.getText().toString().equals("")){

                        Toast.makeText(SettingsActivity.this,"Write some reminder",Toast.LENGTH_SHORT).show();
                    }

                    //else ((hour == 0) ||(year == 0) || (radiobut == null) ){
                       else{
                        Toast.makeText(SettingsActivity.this,"Complete your reminder setting up a date, time and repeat",Toast.LENGTH_SHORT).show();
                    }

                }

                else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("hour", hour);
                    resultIntent.putExtra("minutes", minutes);
                    resultIntent.putExtra("year", year);
                    resultIntent.putExtra("month", month);
                    resultIntent.putExtra("dayOfMonth", dayOfMonth);
                    resultIntent.putExtra("radiobut", radiobut);
                    resultIntent.putExtra("reminder", textedit.getText().toString());
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                 hour = data.getIntExtra("hour",0);
                minutes = data.getIntExtra("minutes",0);


                if (minutes < 10){

                    hourminutes = "TIME :   " + String.valueOf(hour) + ":0" + String.valueOf(minutes);

                }
                else {
                    hourminutes = "TIME :   " + String.valueOf(hour) + ":" + String.valueOf(minutes);
                }
                valuesset.set(0,hourminutes);

                adapterset.notifyDataSetChanged();

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }


        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {


                year = data.getIntExtra("year",0);
                month = data.getIntExtra("month",0);
                dayOfMonth = data.getIntExtra("dayOfMonth",0);

              yearmonth = "DATE :    " +String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year);

                valuesset.set(1,yearmonth);

                adapterset.notifyDataSetChanged();

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {

                radiobut = data.getStringExtra("radiobut");

                yearmonth = "REPEAT :   " + radiobut;

                valuesset.set(2,yearmonth);

                adapterset.notifyDataSetChanged();

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }



    }


}
