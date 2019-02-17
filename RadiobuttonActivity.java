package estudios.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RadiobuttonActivity extends AppCompatActivity {

    public RadioGroup radioGroup;
    public RadioButton radioButton;
    public TextView textView;
    public Button buttonApply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiobutton);


        radioGroup = findViewById(R.id.radioGroup);

        buttonApply = findViewById(R.id.button_apply);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if (radioButton != null) {
                   // int radioId = radioGroup.getCheckedRadioButtonId();
                    //radioButton = findViewById(radioId);
                   // textView.setText("Your choice: " + radioButton.getText());

                    if (radioButton != null) {

                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("radiobut", radioButton.getText());
                        setResult(RESULT_OK, resultIntent);
                        finish();


                    }

                    else {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("radiobut", "Once");
                        setResult(RESULT_OK, resultIntent);
                        finish();

                    }
                    }

        });

    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }
}
