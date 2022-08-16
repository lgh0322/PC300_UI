package com.vaca.pc300.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.vaca.pc300.R;


public class BeBpResultView extends View {

    private final int TEXT_SIZE = getPixel(R.dimen.bp_text_size);
    private final int RECT_LEFT = getPixel(R.dimen.rect_left);
    private final int RECT_RIGHT = getPixel(R.dimen.rect_right);
    private final int RECT_TOP = getPixel(R.dimen.rect_top);
    private final int RECT_BTM = getPixel(R.dimen.rect_btm);
    private final int BLUE_RECT_W = getPixel(R.dimen.blue_rect_w);
    private final int BLUE_RECT_H = getPixel(R.dimen.blue_rect_h);
    private final int GREEN_RECT_W = getPixel(R.dimen.green_rect_w);
    private final int GREEN_RECT_H = getPixel(R.dimen.green_rect_h);
    private final int YELLOW_RECT_W = getPixel(R.dimen.yellow_rect_w);
    private final int YELLOW_RECT_H = getPixel(R.dimen.yellow_rect_h);
    private final int ORANGE_RECT_W = getPixel(R.dimen.orange_rect_w);
    private final int ORANGE_RECT_H = getPixel(R.dimen.orange_rect_h);
    private final int RED_RECT_W = getPixel(R.dimen.red_rect_w);
    private final int RED_RECT_H = getPixel(R.dimen.red_rect_h);
    private final int DARK_RED_RECT_W = getPixel(R.dimen.dark_red_rect_w);
    private final int DARK_RED_RECT_H = getPixel(R.dimen.dark_red_rect_h);
    private final int CANVAS_W = getPixel(R.dimen.canvas_w);
    private final int CANVAS_H = getPixel(R.dimen.canvas_h);
    private final int MARGIN = getPixel(R.dimen.margin);
    private final int MARGIN_BTM = getPixel(R.dimen.margin_btm);
    private final int CIRCLE_RADIO = getPixel(R.dimen.circle_radio);
    Paint mBluePaint;
    Paint mGreenPaint;
    Paint mYellowPaint;
    Paint mOrangePaint;
    Paint mRedPaint;
    Paint mDarkRedPaint;
    Paint mBlackPaint1;
    Paint mBlackPaint2;
    Paint mMainPaint;
    Paint mWhitePaint;
    Rect mBlueRect;
    Rect mGreenRect;
    Rect mYellowRect;
    Rect mOrangeRect;
    Rect mRedRect;
    Rect mDarkRedRect;
    int mWidth, mHeight;
    int sys, dia;

    public BeBpResultView(Context context) {
        super(context);
        setPaint();
    }

    public BeBpResultView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPaint();


    }

    public void setPaint() {
        mBluePaint = new Paint();
        mBluePaint.setColor(getColor(R.color.r_blue));
        mGreenPaint = new Paint();
        mGreenPaint.setColor(getColor(R.color.r_green));
        mYellowPaint = new Paint();
        mYellowPaint.setColor(getColor(R.color.r_yellow));
        mOrangePaint = new Paint();
        mOrangePaint.setColor(getColor(R.color.r_orange));
        mRedPaint = new Paint();
        mRedPaint.setColor(getColor(R.color.r_red));
        mDarkRedPaint = new Paint();
        mDarkRedPaint.setColor(getColor(R.color.r_dark_red));

        mBlackPaint1 = new Paint();
        mBlackPaint1.setColor(getColor(R.color.gray_vaca));
        mBlackPaint1.setTextSize(TEXT_SIZE);
        mBlackPaint1.setTextAlign(Paint.Align.RIGHT);
        mBlackPaint1.setAntiAlias(true);

        mBlackPaint2 = new Paint();
        mBlackPaint2.setColor(getColor(R.color.gray_vaca));
        mBlackPaint2.setTextSize(TEXT_SIZE);
        mBlackPaint2.setAntiAlias(true);
        mBlackPaint2.setTextAlign(Paint.Align.CENTER);

        mMainPaint = new Paint();
        mMainPaint.setColor(getColor(R.color.beColorPrimary));

        mWhitePaint = new Paint();
        mWhitePaint.setColor(getColor(R.color.white));
        mWhitePaint.setTextSize(TEXT_SIZE);

        mBlueRect = new Rect(RECT_LEFT, RECT_BTM - BLUE_RECT_H, RECT_LEFT + BLUE_RECT_W, RECT_BTM);
        mGreenRect = new Rect(RECT_LEFT, RECT_BTM - GREEN_RECT_H, RECT_LEFT + GREEN_RECT_W, RECT_BTM);
        mYellowRect = new Rect(RECT_LEFT, RECT_BTM - YELLOW_RECT_H, RECT_LEFT + YELLOW_RECT_W, RECT_BTM);
        mOrangeRect = new Rect(RECT_LEFT, RECT_BTM - ORANGE_RECT_H, RECT_LEFT + ORANGE_RECT_W, RECT_BTM);
        mRedRect = new Rect(RECT_LEFT, RECT_BTM - RED_RECT_H, RECT_LEFT + RED_RECT_W, RECT_BTM);
        mDarkRedRect = new Rect(RECT_LEFT, RECT_BTM - DARK_RED_RECT_H, RECT_LEFT + DARK_RED_RECT_W, RECT_BTM);
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);


        c.drawColor(getResources().getColor(android.R.color.transparent));
        c.drawRect(mDarkRedRect, mDarkRedPaint);
        c.drawRect(mRedRect, mRedPaint);
        c.drawRect(mOrangeRect, mOrangePaint);
        c.drawRect(mYellowRect, mYellowPaint);
        c.drawRect(mGreenRect, mGreenPaint);
        c.drawRect(mBlueRect, mBluePaint);

        c.drawText("SYS", RECT_LEFT - MARGIN, RECT_TOP +MARGIN, mBlackPaint1);
        c.drawText("180", RECT_LEFT - MARGIN, RECT_BTM - RED_RECT_H, mBlackPaint1);
        c.drawText("160", RECT_LEFT - MARGIN, RECT_BTM - ORANGE_RECT_H, mBlackPaint1);
        c.drawText("140", RECT_LEFT - MARGIN, RECT_BTM - YELLOW_RECT_H, mBlackPaint1);
        c.drawText("120", RECT_LEFT - MARGIN, RECT_BTM - GREEN_RECT_H, mBlackPaint1);
        c.drawText("90", RECT_LEFT - MARGIN, RECT_BTM - BLUE_RECT_H, mBlackPaint1);
//        c.drawText("70", RECT_LEFT-MARGIN, RECT_BTM, mBlackPaint1);
//
//        c.drawText("40", RECT_LEFT, RECT_BTM+MARGIN_BTM, mBlackPaint2);
        c.drawText("60", RECT_LEFT + BLUE_RECT_W, RECT_BTM + MARGIN_BTM, mBlackPaint2);
        c.drawText("80", RECT_LEFT + GREEN_RECT_W, RECT_BTM + MARGIN_BTM, mBlackPaint2);
        c.drawText("90", RECT_LEFT + YELLOW_RECT_W, RECT_BTM + MARGIN_BTM, mBlackPaint2);
        c.drawText("100", RECT_LEFT + ORANGE_RECT_W, RECT_BTM + MARGIN_BTM, mBlackPaint2);
        c.drawText("110", RECT_LEFT + RED_RECT_W, RECT_BTM + MARGIN_BTM, mBlackPaint2);
        c.drawText("DIA", RECT_LEFT + DARK_RED_RECT_W , RECT_BTM + MARGIN_BTM, mBlackPaint2);

//        c.drawText("Normal", 120, 365, mWhitePaint);

        if (sys != 0 && sys <= 200 && sys >= 70 && dia != 0 && dia <= 120 && dia >= 40) {
            int x=0;
            int y=0;

            if(sys<90){
                y = (90 - sys) * BLUE_RECT_H / 20 + RECT_TOP+(DARK_RED_RECT_H-BLUE_RECT_H);
            }else if(sys<120){
                y = (120- sys) *(GREEN_RECT_H- BLUE_RECT_H) / 30 + RECT_TOP+(DARK_RED_RECT_H-GREEN_RECT_H);
            }else {
                y = (200- sys) *(DARK_RED_RECT_H- GREEN_RECT_H) / 80 + RECT_TOP;
            }

            if(dia<60){
               x = (dia - 40) * BLUE_RECT_W / 20 + RECT_LEFT;
            }else if( dia<80){
                x = (dia - 60) *(GREEN_RECT_W-BLUE_RECT_W) / 20 + RECT_LEFT+BLUE_RECT_W;
            }else{
               x = (dia - 80) * (DARK_RED_RECT_W-GREEN_RECT_W) / 40 + RECT_LEFT+GREEN_RECT_W;
            }

            c.drawCircle(x, y, CIRCLE_RADIO, mMainPaint);
        }
    }

    public void setBPValue(int sys, int dia) {
        if (this.sys != sys && this.dia != dia) {
            this.sys = sys;
            this.dia = dia;
            invalidate();
        }
    }

    protected void onMeasure(int width, int height) {
//        setMeasuredDimension(690, 530);
        setMeasuredDimension(CANVAS_W, CANVAS_H);
    }


    private int getPixel(int resource_id) {
        return getResources().getDimensionPixelSize(resource_id);
    }

    private int getColor(int resource_id) {
        return getResources().getColor(resource_id);
    }
}
