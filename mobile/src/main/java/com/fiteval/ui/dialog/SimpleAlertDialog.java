package com.fiteval.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fiteval.R;

/**
 * Created by Henry Moon on 11/4/2016.
 */

public class SimpleAlertDialog {
    private static final int SDK = android.os.Build.VERSION.SDK_INT;
    private static final int SDK_JELLY_BEAN = Build.VERSION_CODES.JELLY_BEAN;

    private Context mContext;
    private AlertDialog dialog;
    private TextView textView;
    private LinearLayout button;
    private boolean isButtonRed;

    public SimpleAlertDialog(Context context) {
        mContext = context;
        View dialogView = LayoutInflater.from(context).inflate(R.layout.custom_dialog_confirm, null, false);
        textView = (TextView) dialogView.findViewById(R.id.custom_dialog_confirm_msg);
        button = (LinearLayout) dialogView.findViewById(R.id.custom_dialog_confirm_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                if (mCallback != null) {
                    mCallback.onConfirm();
                }
            }
        });
        dialog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .setCancelable(true)
                .create();
    }

    /**
     * 보일 메시지 설정 + dialog 띄우기
     *
     * @param message 메시지
     */
    @SuppressWarnings("deprecation")
    public void show(String message) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        textView.setText(message);
        if (isButtonRed) {
            button.setBackgroundDrawable(ContextCompat.getDrawable(mContext,
                    R.drawable.dialog_btn_selector));
            isButtonRed = false;
        }
        dialog.show();
    }

    @SuppressWarnings("deprecation")
    public void showRed(String message) {
        if (!dialog.isShowing()) {
            dialog.dismiss();
        }

        textView.setText(message);
        if (!isButtonRed) {
            button.setBackgroundDrawable(ContextCompat.getDrawable(mContext,
                    R.drawable.custom_dialog_confirm_btn_red_selector));
            isButtonRed = true;
        }

        dialog.show();
    }

    SimpleAlertDialogListener mCallback;

    public void setOnConfirmListener(SimpleAlertDialogListener listener) {
        this.mCallback = listener;
    }

    public interface SimpleAlertDialogListener {
        void onConfirm();
    }
}