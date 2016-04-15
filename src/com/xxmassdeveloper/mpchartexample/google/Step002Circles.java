/* Copyright (C) 2012 The Android Open Source Project

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package com.xxmassdeveloper.mpchartexample.google;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Custom view that shows a pie chart and, optionally, a label.
 */
public class Step002Circles extends ViewGroup {

    Paint circlePaint;
    private Paint mTextPaint;
    /**
     * main paint object used for rendering
     */
    protected Paint mRenderPaint;
    private Path surface = new Path();

    float diameterOfCircle;
    float radiusOfCircle;

    private RectF mPieBounds = new RectF();


    public Step002Circles(Context context) {
        super(context);
        init();
    }

    public Step002Circles(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Step002Circles(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        // Do nothing. Do not call the superclass method--that would start a layout pass
        // on this view's children. PieChart lays out its children in onSizeChanged().
    }

    private void init() {
        circlePaint = new Paint();
        circlePaint.setColor(Color.parseColor("#F4511E"));
        circlePaint.setStrokeWidth(3);
        circlePaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.parseColor("#F4511E"));
        mTextPaint.setTextSize(20);


        mRenderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRenderPaint.setStyle(Paint.Style.FILL);
        mRenderPaint.setColor(Color.parseColor("#F4511E"));

        surface.moveTo(20, 20);
        surface.lineTo(40, 40);

        surface.lineTo(60, 60);
        surface.lineTo(20, 20);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*draw circle*/
        diameterOfCircle = mPieBounds.width();
        radiusOfCircle = diameterOfCircle / 2;
//        canvas.drawCircle(mPieBounds.centerX(), mPieBounds.centerY(), diameterOfCircle / 2, circlePaint);
        canvas.drawCircle(mPieBounds.centerX(), mPieBounds.centerY(), diameterOfCircle / (2 * 5), circlePaint);
        canvas.drawCircle(mPieBounds.centerX(), mPieBounds.centerY(), (diameterOfCircle * 2) / (2 * 5), circlePaint);
        canvas.drawCircle(mPieBounds.centerX(), mPieBounds.centerY(), (diameterOfCircle * 3) / (2 * 5), circlePaint);
        canvas.drawCircle(mPieBounds.centerX(), mPieBounds.centerY(), (diameterOfCircle * 4) / (2 * 5), circlePaint);

        mRenderPaint.setStyle(Paint.Style.FILL);
        mRenderPaint.setAlpha(85);
        canvas.drawPath(surface, mRenderPaint);

        mRenderPaint.setStyle(Paint.Style.STROKE);
        mRenderPaint.setAlpha(255);
        canvas.drawPath(surface, mRenderPaint);


//        canvas.drawLine(mPieBounds.centerX(), mPieBounds.centerY(),
//                radiusOfCircle * (float) Math.sin(Math.toRadians(72)),
//                radiusOfCircle * (float) Math.cos(Math.toRadians(72)),
//                circlePaint);
        circlePaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(mPieBounds, 0, -72, true, circlePaint);
        canvas.drawArc(mPieBounds, -72, -72 * 2, true, circlePaint);
        canvas.drawArc(mPieBounds, -72 * 2, -72 * 3, true, circlePaint);
        canvas.drawArc(mPieBounds, -72 * 3, -72 * 4, true, circlePaint);
        canvas.drawArc(mPieBounds, 0, 72, true, circlePaint);

        canvas.drawText("random", mPieBounds.centerX(), mPieBounds.centerY(), mTextPaint);

//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(300, 300, 200, paint);
        //drawCircle(cx, cy, radius, paint)

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // Account for padding
        float xpad = (float) (getPaddingLeft() + getPaddingRight());
        float ypad = (float) (getPaddingTop() + getPaddingBottom());

        float ww = (float) w - xpad;
        float hh = (float) h - ypad;

        float diameter = Math.min(ww, hh);

        mPieBounds = new RectF(
                0.0f,
                0.0f,
                diameter,
                diameter);
        mPieBounds.offsetTo(getPaddingLeft(), getPaddingTop());


    }
}