package cs301.birthdaycake;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, View.OnTouchListener {
    private CakeView cakeView;
    private CakeModel cakeModel;
    private View view;

    public CakeController(CakeView cv) {
        cakeView = cv;
        cakeModel = cakeView.getCakeModel();
    }

    @Override
    public void onClick(View view) {
        Log.d("button", "received clicks");
        this.cakeModel.isLit = !this.cakeModel.isLit;
        cakeView.invalidate();
    }

    @Override
    public void onCheckedChanged(CompoundButton button, boolean hasCandles) {
        this.cakeModel.hasCandles = !this.cakeModel.hasCandles;
        cakeView.invalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int seekNum, boolean b) {
        this.cakeModel.numCandles = seekNum;
        cakeView.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        this.view = view;
        view.performClick();
        cakeModel.touchX = (int)motionEvent.getX();
        cakeModel.touchY = (int)motionEvent.getY();
        cakeView.invalidate();
        if(motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();

            cakeModel.checkerBoard.touched = true;
            cakeModel.checkerBoard.set(x,y);

            cakeView.invalidate();
            return true;
        }
        //set coordinates for the balloon touch event
        cakeView.balloonLeft = motionEvent.getX();
        cakeView.balloonTop = motionEvent.getY();
        //set cakeModel's x and y coord to corresponding touch
        cakeModel.x = (int) motionEvent.getX();
        cakeModel.y = (int) motionEvent.getY();
        cakeView.invalidate();

        return true;
    }
}
