package com.fiteval.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.fiteval.R;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by Henry Moon on 11/4/2016.
 */

public class MiscUtil {
    private Context mContext;
    private LayoutInflater inflater;
    private Vibrator mVibrator;

    public MiscUtil(Context mContext) {
        this.mContext = mContext;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mVibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void toast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public void toastTop(String msg) {
        Toast toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    public void toastCenter(String msg) {
        Toast toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void toastLong(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    public void toastLongTop(String msg) {
        Toast toast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    public void toastLongCenter(String msg) {
        Toast toast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void toastCheckMark() {
        View toastLayout = inflater.inflate(R.layout.custom_toast_check_mark, null, false);
        Toast checkToast = new Toast(mContext);
        checkToast.setView(toastLayout);
        checkToast.setDuration(Toast.LENGTH_SHORT);
        checkToast.setGravity(Gravity.CENTER, 0, 0);
        checkToast.show();
        mVibrator.vibrate(500);
    }

    public void toastXMark() {
        View toastLayout = inflater.inflate(R.layout.custom_toast_x_mark, null, false);
        Toast checkToast = new Toast(mContext);
        checkToast.setView(toastLayout);
        checkToast.setDuration(Toast.LENGTH_SHORT);
        checkToast.setGravity(Gravity.CENTER, 0, 0);
        checkToast.show();
        mVibrator.vibrate(500);
    }

    public boolean fileExist(String filePath) {
        File f = new File(filePath);
        return (f.exists() && !f.isDirectory());
    }

    public boolean hasPermission(String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermission(String permission, int requestCode, String displayMsg) {

        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) mContext, permission)) {
            toastLong(displayMsg);
        }

        // No explanation needed, we can request the permission.
        ActivityCompat.requestPermissions((Activity) mContext, new String[]{permission}, requestCode);
    }

    public void hideKeyboardFrom(Context context, View view) {
        if (view == null) {
            Log.e("hideKeyboardFrom", "empty view param");
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) context
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showKeyboardFrom(Context context, View view) {
        if (view == null) {
            Log.e("hideKeyboardFrom", "empty view param");
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) context
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * full screen width and height in pixel
     *
     * @return width height
     */
    @SuppressWarnings("deprecation")
    public int[] getFullScreenSize() {
        int[] metrics = new int[2];

        Display display = ((Activity) mContext).getWindowManager().getDefaultDisplay();
        int realWidth;
        int realHeight;

        if (Build.VERSION.SDK_INT >= 17) {
            //new pleasant way to get real metrics
            DisplayMetrics realMetrics = new DisplayMetrics();
            display.getRealMetrics(realMetrics);
            realWidth = realMetrics.widthPixels;
            realHeight = realMetrics.heightPixels;

        } else if (Build.VERSION.SDK_INT >= 14) {
            //reflection for this weird in-between time
            try {
                Method mGetRawH = Display.class.getMethod("getRawHeight");
                Method mGetRawW = Display.class.getMethod("getRawWidth");
                realWidth = (Integer) mGetRawW.invoke(display);
                realHeight = (Integer) mGetRawH.invoke(display);
            } catch (Exception e) {
                //this may not be 100% accurate, but it's all we've got
                realWidth = display.getWidth();
                realHeight = display.getHeight();
                Log.e("Display Info", "Couldn't use reflection to get the real display metrics.");
            }

        } else {
            //This should be close, as lower API devices should not have window navigation bars
            realWidth = display.getWidth();
            realHeight = display.getHeight();
        }
        metrics[0] = realWidth;
        metrics[1] = realHeight;

        return metrics;
    }
}
