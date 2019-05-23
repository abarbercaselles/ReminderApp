package barber.studios.reminderapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class CloseActivity extends AppCompatActivity {

    public String songfinal;
    private MediaPlayer SoundMP;
    public Button closebutton;
    private PowerManager.WakeLock wl;

    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_close);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        Intent close = getIntent();
        songfinal = close.getStringExtra("songfinal");
        closebutton = (Button) findViewById(R.id.button1);

        switch(songfinal){


            case "Wisdom":

                SoundMP = MediaPlayer.create(CloseActivity.this, R.raw.horseproject);
                SoundMP.start();
                break;
            case "Rythym":

                SoundMP = MediaPlayer.create(CloseActivity.this, R.raw.rumberaproject);
                SoundMP.start();
                break;
            case "Classic":

                SoundMP = MediaPlayer.create(CloseActivity.this, R.raw.johannesproject);
                SoundMP.start();
                break;
            case "Mexico":

                SoundMP = MediaPlayer.create(CloseActivity.this, R.raw.julietaproject);
                SoundMP.start();
                break;
            case "Happy":

                SoundMP = MediaPlayer.create(CloseActivity.this, R.raw.vampirepunkproject);
                SoundMP.start();
                break;

            default:
                SoundMP = MediaPlayer.create(CloseActivity.this, R.raw.horseproject);
                SoundMP.start();
                break;


        }

        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent main = new Intent(CloseActivity.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        });



    }

    @Override
    protected void onDestroy() {

        SoundMP.stop();

        super.onDestroy();
    }

    @Override
    protected void onStop() {

        SoundMP.start();


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        super.onStop();
    }

    protected void onPause() {

        SoundMP.start();


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        super.onPause();
    }

    @Override
    public void onBackPressed() {

    }

}
