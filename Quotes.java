package barber.studios.reminderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class Quotes extends AppCompatActivity {

    public ImageView image;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        image = (ImageView)findViewById(R.id.quotes);
        button = (Button)findViewById(R.id.buttonsend);
    }
}
