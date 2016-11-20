package com.fiteval.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Henry Moon on 11/18/2016.
 */

public class NonScrollableGridView extends GridView {

    public NonScrollableGridView(Context context) {
        super(context);
    }

    public NonScrollableGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NonScrollableGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        getLayoutParams().height = getMeasuredHeight();
    }
}
