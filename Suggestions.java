package barber.studios.reminderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Suggestions extends AppCompatActivity {

    public ImageView image;
    public Button button;
    public TextView thankyou,textview;
    public EditText textedit;
   // public barber.studios.reminderapp.DatabaseHelper2 myDb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        image = (ImageView)findViewById(R.id.suggestions);
        button = (Button)findViewById(R.id.buttonsend);

        thankyou = (TextView) findViewById(R.id.thankyou);
        textedit = (EditText) findViewById(R.id.textedit);
        textview = (TextView) findViewById(R.id.textview);

        //myDb2 = new barber.studios.reminderapp.DatabaseHelper2(this);


        thankyou.setVisibility(View.GONE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //AddData2();
                //myDb2.updatesuggestions("YES");

                thankyou.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);
                textedit.setVisibility(View.GONE);
                textview.setVisibility(View.GONE);


            }
        });
    }
/*
    public void AddData2() {

        boolean isInserted = myDb2.insertData(1,"No","Default","No","No","Default");

        if (isInserted == true)
            Toast.makeText(Suggestions.this, "Data Inserted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(Suggestions.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
    }
    */
}
