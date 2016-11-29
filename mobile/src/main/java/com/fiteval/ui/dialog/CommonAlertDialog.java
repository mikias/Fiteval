package com.fiteval.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fiteval.R;

/**
 * Created by Henry Moon on 11/21/2016.
 */

public class CommonAlertDialog extends AlertDialog implements View.OnClickListener {

    private Button mBtnConfirm;
    private Button mBtnCancel;
    private TextView mTxtMessage;
    private Context mContext;

    public CommonAlertDialog(Context context) {
        super(context, false, null);
        this.mContext = context;
        setView();
    }

    public void setView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.alert_dialog_common, null);
        mBtnCancel = (Button) view.findViewById(R.id.alert_dialog_common_btn_cancel);
        mBtnConfirm = (Button) view.findViewById(R.id.alert_dialog_common_btn_confirm);
        mTxtMessage = (TextView) view.findViewById(R.id.alert_dialog_common_txt_message);
        mBtnCancel.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);
        super.setView(view);
    }

    public void setCustomMessage(String msg) {
        mTxtMessage.setText(msg);
    }

    public void setPositiveBtnText(String msg) {
        mBtnConfirm.setText(msg);
    }

    public void setNegativeBtnText(String msg) {
        mBtnCancel.setText(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alert_dialog_common_btn_confirm:
                dismiss();
                if (mCallback != null) {
                    mCallback.onPositive();
                }
                break;

            case R.id.alert_dialog_common_btn_cancel:
                dismiss();
                if (mCallback != null) {
                    mCallback.onNegative();
                }
                break;
        }
    }

    private onButtonClickedListener mCallback;

    public void setOnButtonClickedListener(onButtonClickedListener listener) {
        mCallback = listener;
    }

    public interface onButtonClickedListener {
        void onPositive();

        void onNegative();
    }
}
