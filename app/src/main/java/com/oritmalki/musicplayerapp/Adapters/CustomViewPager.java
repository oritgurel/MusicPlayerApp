package com.oritmalki.musicplayerapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager {

    float mStartDragX;
    OnSwipeOutListener mListener;

    public CustomViewPager(@NonNull Context context) {
        super(context);
    }


    public void setOnSwipeOutListener(OnSwipeOutListener listener) {
        mListener = listener;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartDragX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mStartDragX < x && getCurrentItem() == 0) {
                    mListener.onSwipeOutAtStart();
                } else if (mStartDragX > x && getCurrentItem() == getAdapter().getCount() - 1) {
                    mListener.onSwipeOutAtEnd();
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public interface OnSwipeOutListener {
        public void onSwipeOutAtStart();

        public void onSwipeOutAtEnd();
    }

}

