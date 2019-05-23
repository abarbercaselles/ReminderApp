package barber.studios.reminderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Feedback extends AppCompatActivity {

    public ImageView image;
    public Button button;
    public TextView thankyou;
    public RadioGroup radiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        image = (ImageView)findViewById(R.id.feedback);
        button = (Button)findViewById(R.id.buttonsend);
        thankyou = (TextView) findViewById(R.id.thankyou);
        radiogroup = (RadioGroup) findViewById(R.id.radioGroup);


        thankyou.setVisibility(View.GONE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thankyou.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);
                radiogroup.setVisibility(View.INVISIBLE);


            }
        });






    }
}
