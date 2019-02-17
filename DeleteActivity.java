package estudios.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DeleteActivity extends AppCompatActivity {

    public int position1,position2;
    public Button button;
    public TextView text;
    public String itemvalue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        text = (TextView)findViewById(R.id.text);
        Intent myIntent1 = getIntent();
        position1 = myIntent1.getIntExtra("position1",0);
        itemvalue = myIntent1.getStringExtra("itemvalue");
        position2 = position1 +1;
        text.setText(itemvalue);

        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent resultIntent = new Intent();
                resultIntent.putExtra("position1",String.valueOf(position2));
                setResult(RESULT_OK, resultIntent);
                finish();


            }
        });
    }

}
