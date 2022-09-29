package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class CakeView extends SurfaceView {

    // lab3
    private CakeModel cakeModel;

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();
    Paint balloonPaint = new Paint();
    Paint stringPaint = new Paint();

    Paint redText = new Paint();


    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 40.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;
    public static final float candleHeight2 = 300.0f;
    public static final float candleWidth2 = 40.0f;
    public static final float wickHeight2 = 30.0f;
    public static final float wickWidth2 = 6.0f;
    public static final float outerFlameRadius2 = 30.0f;
    public static final float innerFlameRadius2 = 15.0f;
    public static final float balloonHeight = 100.0f;
    public static final float balloonWidth = 75.0f;
    public static float balloonLeft = 0.0f;
    public static float balloonTop = 0.0f;



    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        cakePaint.setColor(0xFFFF00FF);  //violet-red
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);
        balloonPaint.setColor(Color.BLUE);
        balloonPaint.setStyle(Paint.Style.FILL);
        stringPaint.setColor(Color.BLACK);
        stringPaint.setStrokeWidth(5.0f);
        redText.setColor(Color.RED);
        redText.setTextSize(50);

        setBackgroundColor(Color.WHITE);  //better than black default

        // lab3
        cakeModel = new CakeModel();

        checkerBoard = cakeModel.checkerBoard;
    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {
        if(this.cakeModel.hasCandles) {
            canvas.drawRect(left, bottom - candleHeight, left+ candleWidth +70, bottom, candlePaint);
            if (this.cakeModel.isLit) {
                //draw the outer flame
                float flameCenterX = left + (candleWidth+70) / 2;
                float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

                //draw the inner flame
                flameCenterY += outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
            }

            //draw the wick
            float wickLeft = left + (candleWidth+70) / 2 - wickWidth / 2;
            float wickTop = bottom - wickHeight - candleHeight;
            canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);

        }
    }

    public void drawBalloon(Canvas canvas) {
        canvas.drawOval(balloonLeft - (balloonWidth / 2), balloonTop - (balloonHeight / 2), balloonLeft + balloonWidth, balloonTop + balloonHeight, balloonPaint);
        canvas.drawLine(balloonLeft + (balloonWidth / 3) - 5.0f, balloonTop + balloonHeight, balloonLeft + (balloonWidth / 3) - 5.0f, balloonTop + balloonHeight + 100, stringPaint);
    }
    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        //Now a candle in the center
        // drawCandle(canvas, cakeLeft + cakeWidth-350 - candleWidth, cakeTop);
        // drawCandle(canvas, cakeLeft + cakeWidth/3 - candleWidth, cakeTop);

        for (int i = 1; i < cakeModel.numCandles + 1; i++ ) {
            drawCandle(canvas, cakeLeft + (cakeWidth * i) / (cakeModel.numCandles + 1) - (candleWidth * i) / 2, cakeTop);
        }

        // draws balloon in touch locatin
        if (balloonLeft != 0.0f && balloonTop != 0.0f) {
            drawBalloon(canvas);
        }

        canvas.drawText("("+cakeModel.touchX+","+cakeModel.touchY+")", getWidth()-300, getHeight()-100, redText);

        if(checkerBoard.touched) {
            checkerBoard.draw(canvas);
        }
    }//onDraw

    //lab3
    public CakeModel getCakeModel() {
        return cakeModel;
    }

//    @Override
//    public boolean onTouch(View view, MotionEvent e) {
//        if(e.getActionMasked() == MotionEvent.ACTION_DOWN) {
//            float x = e.getX();
//            float y = e.getY();
//            checkerBoard.touched = true;
//            checkerBoard.set(x,y);
//            invalidate();
//            return true;
//        }
//
//        return false;//In this case we didn't do anything
//    }
    }
//class CakeView

