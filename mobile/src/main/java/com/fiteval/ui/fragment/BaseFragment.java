package com.fiteval.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.fiteval.config.FitevalApplication;
import com.fiteval.data.UserDto;
import com.fiteval.ui.dialog.CommonAlertDialog;
import com.fiteval.ui.dialog.SimpleAlertDialog;
import com.fiteval.util.MiscUtil;

/**
 * Created by Henry Moon on 11/23/2016.
 */

public class BaseFragment extends Fragment {
    protected static final int SDK = android.os.Build.VERSION.SDK_INT;

    protected Context mContext;
    protected MiscUtil mUtils;
    protected CommonAlertDialog mAlertDialog;
    protected SimpleAlertDialog mDialog;
    protected FitevalApplication mApplication;
    protected UserDto mUserDto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mUtils = new MiscUtil(mContext);
        mAlertDialog = new CommonAlertDialog(mContext);
        mAlertDialog.setCancelable(true);
        mDialog = new SimpleAlertDialog(mContext);
        mAlertDialog.setCancelable(true);
        mApplication = (FitevalApplication) mContext.getApplicationContext();
        mUserDto = mApplication.getUserDto();
        if (mUserDto == null) {
            // ...
        }
    }
}
