package barber.studios.reminderapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Donate extends AppCompatActivity {

    public ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        image = (ImageView) findViewById(R.id.paypal);

        image.setOnClickListener(new View.OnClickListener() {

                                     public void onClick(View v) {
                                         Intent intent = new Intent();
                                         intent.setAction(Intent.ACTION_VIEW);
                                         intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                         intent.setData(Uri.parse("https://www.paypal.com/us/home"));
                                         startActivity(intent);
                                     }
                                 }
        );
    }
}