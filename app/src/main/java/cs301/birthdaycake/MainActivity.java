package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        // lab3
        CakeView cv = findViewById(R.id.cakeview);
        CakeController cc = new CakeController(cv);
        // int x = 5;

        cv.setOnTouchListener(cc);


        Button button = findViewById(R.id.button);
        button.setOnClickListener(cc);
        Switch candles = findViewById(R.id.candles);
        candles.setOnCheckedChangeListener(cc);
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(cc);
        cv.setOnTouchListener(cc);

    }

    public void goodBye(View button) {
        Log.i("button", "Goodbye");
    }

}
