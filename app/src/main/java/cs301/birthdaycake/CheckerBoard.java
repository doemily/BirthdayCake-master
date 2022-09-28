package cs301.birthdaycake;

import android.graphics.Canvas;
import android.graphics.Paint;

public class CheckerBoard {
    private float left;
    private float right;
    private Paint red;
    private Paint green;
    private float top;
    private float bottom;
    private float translateX;
    private float translateY;
    public boolean touched;

    public CheckerBoard() {
        left = -50;
        top = -50;
        right = left + 50;
        bottom = top + 50;
        translateX = 0;
        translateY = 0;
        touched = false;
        red = new Paint();
        green = new Paint();
        red.setARGB(255,255,0,0);
        green.setARGB(255,0,255,0);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(left + translateX,top + translateY,right+translateX,bottom+translateY,red);
        canvas.drawRect(left+100+translateX,top+translateY,right+translateX,bottom+translateY,green);
        canvas.drawRect(left+translateX,top+100+translateY,right+translateX,bottom+translateY,green);
        canvas.drawRect(left+100+translateX,top+100+translateY,right+translateX,bottom+translateY,red);
    }

    public void set(float _cx, float _cy){
        translateX = _cx;
        translateY = _cy;
    }

}
