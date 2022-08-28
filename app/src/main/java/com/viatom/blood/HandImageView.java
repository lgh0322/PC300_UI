package com.viatom.blood;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.renderscript.Float2;
import android.renderscript.Float4;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/3/21.
 */
public class HandImageView extends ImageView {
    private PointF centerPoint;
    private float firstdistance;
    private Matrix matrix;
    public HandImageView(Context context) {
        super(context);
        initParams();
    }
    private void initParams() {
        matrix = new Matrix();
        setImageMatrix(matrix);
    }
    public HandImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams();
    }
    public HandImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams();
    }

    float last_x=-1f;
    float last_y=-1f;


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        int count = event.getPointerCount();
        if(count == 1){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    System.out.println("actiondown 1----");

                    break;
                case MotionEvent.ACTION_MOVE:
                    if(last_x>0&&last_y>0){
                        float dx=event.getX()-last_x;
                        float dy=event.getY()-last_y;
                        matrix.postTranslate(dx,dy);
                        setImageMatrix(matrix);
                    }
                    last_x=event.getX();
                    last_y=event.getY();


                    break;
                case MotionEvent.ACTION_UP:
                    last_x=-1;
                    last_y=-1;
                    System.out.println("actionup 1----");
                    break;
                default:
                    break;
            }
        }else if(count == 2){
            switch (event.getAction()&MotionEvent.ACTION_MASK){
                case MotionEvent.ACTION_POINTER_DOWN:
                    firstdistance = getDistance(event);
                    centerPoint = getCenterPoint(event);
                    System.out.println("actiondown 2===");
                    break;
                case MotionEvent.ACTION_MOVE:
                    float distance = getDistance(event);
                    float scale = distance / firstdistance;
                    matrix.postScale(scale,scale,centerPoint.x,centerPoint.y);
                    setImageMatrix(matrix);
                    System.out.println(scale + "====");
                    firstdistance = distance;
                    System.out.println("actionmove 2===");
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                default:
                    break;
            }
        }
        return true;
    }
    /**
     * 获取两个点的中心点坐标
     * @param event
     */
    private PointF getCenterPoint(MotionEvent event) {
        PointF point = new PointF();
        point.x = (event.getX()+event.getX(1))/2;
        point.y = (event.getY()+event.getY(1))/2;
        return point;
    }
    /**
     * 两根手指时手指间的距离
     * @param event
     */
    private float getDistance(MotionEvent event) {
        float x1 = event.getX();
        float y1 = event.getY();
        float x2 = event.getX(1);
        float y2 = event.getY(1);
        float distance = (float) Math.sqrt((x1 - x2)*(x1 - x2)+(y1 - y2)*(y1- y2));
        return distance;
    }
}