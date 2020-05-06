package com.example.app.StatisticStuff;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Simple single android view component that can be used to showing a round progress bar.
 * It can be customized with size, stroke size, colors and text etc.
 * Progress change will be animated.
 * Created by Kristoffer, http://kmdev.se
 */
public class CircularProgressBar extends View {

    private int mViewWidth;
    private int mViewHeight;

    private float mStartAngle = -90;            // Start from top (default is: "3 o'clock on a watch.")
    private float mBeginningAngle = 0;          //what the progress % starts off of
    private float mMaxSweepAngle = 360;         // Max degrees for the progress ring = full circle
    private int mStrokeWidth = 100;              // Width of outline
    private int mAnimationDuration = 600;       // Animation duration for progress change
    private int mMaxProgress = 100;             // Max progress to use
    private boolean mDrawText = true;           // Set to true if progress text should be drawn
    private boolean mRoundedCorners = true;     // Set to true if rounded corners should be applied to outline ends
    private int mProgressColor = Color.BLACK;   // Outline color
    private int mBackgroundColor = Color.parseColor("#DBDBDB"); // Background color
    private int mTextColor = Color.BLACK;       // Progress text color
    private boolean mClockWise = true;
    private boolean hasBackground = true;

    private final Paint mPaint;                 // Allocate paint outside onDraw to avoid unnecessary object creation


    public CircularProgressBar(Context context) {
        this(context, null);
    }

    public CircularProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initMeasurements();
        drawOutlineArc(canvas);

        if (mDrawText) {
            drawText(canvas);
        }
    }

    private void initMeasurements() {
        mViewWidth = getWidth();
        mViewHeight = getHeight();
    }

    private void drawOutlineArc(Canvas canvas) {

        final int diameter = Math.min(mViewWidth, mViewHeight);
        final float pad = (float) (mStrokeWidth / 2.0);
        final RectF outerOval = new RectF(pad, pad, diameter - pad, diameter - pad);

        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        if(hasBackground) {
            mPaint.setColor(mBackgroundColor);
            mPaint.setStrokeCap(Paint.Cap.BUTT);
            canvas.drawArc(outerOval, 0, mMaxSweepAngle, false, mPaint);
        }
        mPaint.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        mPaint.setColor(mProgressColor);
        canvas.drawArc(outerOval, mStartAngle, mClockWise ? mBeginningAngle : -mBeginningAngle, false, mPaint);
    }

    private void drawText(Canvas canvas) {
        mPaint.setTextSize(Math.min(mViewWidth, mViewHeight) / 5f);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setColor(mTextColor);
        mPaint.setStyle(Paint.Style.FILL);
        // Center text
        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / 2) - ((mPaint.descent() + mPaint.ascent()) / 2));

        canvas.drawText(calcProgressFromSweepAngle(mBeginningAngle) + "%", xPos, yPos, mPaint);
    }

    private float calcSweepAngleFromProgress(int progress) {
        return (mMaxSweepAngle / mMaxProgress) * progress;
    }

    private int calcProgressFromSweepAngle(float sweepAngle) {
        return (int) ((sweepAngle * mMaxProgress) / mMaxSweepAngle);
    }

    /**
     * Set progress of the circular progress bar.
     *
     * @param progress progress between 0 and 100.
     */
    public void setProgress(int progress) {
        ValueAnimator animator = ValueAnimator.ofFloat(mBeginningAngle, calcSweepAngleFromProgress(progress));
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(mAnimationDuration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mBeginningAngle = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    public void setProgressColor(int color) {
        mProgressColor = color;
        invalidate();
    }

    public void setStrokeWidth(int width) {
        mStrokeWidth = width;
        invalidate();
    }

    public void setTextColor(int color) {
        mTextColor = color;
        invalidate();
    }

    public void showProgressText(boolean show) {
        mDrawText = show;
        invalidate();
    }

    /**
     * Toggle this if you don't want rounded corners on progress bar.
     * Default is true.
     *
     * @param roundedCorners true if you want rounded corners of false otherwise.
     */
    public void useRoundedCorners(boolean roundedCorners) {
        mRoundedCorners = roundedCorners;
        invalidate();
    }

    public float getStartAngle() {
        return mStartAngle;
    }

    public void setStartAngle(float angle) {
        mStartAngle = angle;
    }

    public boolean isClockWise() {
        return mClockWise;
    }

    public void setClockWise(boolean mClockWise) {
        this.mClockWise = mClockWise;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public void setBackgroundColor(int mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
    }

    public boolean isHasBackground() {
        return hasBackground;
    }

    public void setHasBackground(boolean hasBackground) {
        this.hasBackground = hasBackground;
    }
}

