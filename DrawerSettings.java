package barber.studios.reminderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DrawerSettings extends AppCompatActivity {

    public ImageView image;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawersettings);

        image = (ImageView)findViewById(R.id.drawersettings);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent comeback = new Intent(DrawerSettings.this, MainActivity.class);
                startActivity(comeback);
                finish();


            }
        });
    }
}
