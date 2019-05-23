package barber.studios.reminderapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SongsPack extends AppCompatActivity {

    public ImageView image;
    public Button button;
    public String packsongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_pack);

        image = (ImageView)findViewById(R.id.songs);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                RadioButton radioButton;

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(radioId);

                if (radioButton != null) {

                    packsongs = radioButton.getText().toString();

                    switch(packsongs) {
                        case "Default":
                            Toast.makeText(SongsPack.this, "Default Pack Song Selected", Toast.LENGTH_SHORT).show();
                            break;
                        case "Cartoons":
                            Toast.makeText(SongsPack.this, "Cartoons Pack Song Selected", Toast.LENGTH_SHORT).show();
                            break;
                        case "TVSeries":
                            Toast.makeText(SongsPack.this, "TVSeries Pack Song Selected", Toast.LENGTH_SHORT).show();
                            break;
                        case "Football Best Goals":

                            Toast.makeText(SongsPack.this, "Football Best Goals Pack Song Selected", Toast.LENGTH_SHORT).show();

                            break;

                        default:

                    }

                }

                Intent comeback = new Intent(SongsPack.this, MainActivity.class);
                startActivity(comeback);
                finish();
            }
        });


    }
}
