package com.mak.ryan.reddit.ui;


import android.content.Context;
import android.util.AttributeSet;

public class NoScrollTextView extends android.support.v7.widget.AppCompatTextView {
    public NoScrollTextView(Context context) {
        super(context);
    }

    public NoScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void scrollTo(int x, int y) {
        //do nothing
    }
}