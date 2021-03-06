package fiteval.com.fiteval;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import org.w3c.dom.Text;

/**
 * Created by nader on 11/11/16.
 */

public class HbService extends Service implements SensorEventListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private SensorManager mSensorManager;
    private int mHeartbeat;
    private GoogleApiClient mGoogleApiClient;
    private TextView mTVHeartbeat;

    private boolean attached = false;
    private hbListener mListener;

    public HbService() {
        super();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        if(!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);

        Log.d("HB Service", "Hb service started");
        Log.e("HB Service", "Sensor: " + mHeartRateSensor);
        Log.e("HB Service", "This: " + this);

        for (Sensor sensor : mSensorManager.getSensorList(Sensor.TYPE_ALL)) {
            Log.e("HB Service", sensor.getName() + ": " + sensor.getType());
        }



        // Register for sensor events
        mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_UI);

        Log.d("HB Service", "Hb service rehgistered");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        mSensorManager.unregisterListener(this);
        Log.d("HB service", " sensor unregistered");
        super.onDestroy();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Log.d("HB Service", Sensor.TYPE_HEART_RATE+" : "+sensorEvent.sensor.getType());

        if (sensorEvent.sensor.getType() == Sensor.TYPE_HEART_RATE && sensorEvent.values.length > 0) {
//            Log.d("HB Service", "Heartbeat Read: " + Math.round(sensorEvent.values[0]));
            mHeartbeat = Math.round(sensorEvent.values[0]);
            double multiplier = 1;

            Log.d("HB service", "heartbeat: " + mHeartbeat);
//            sendBeetsBySchrute();
//            mListener.callback(mHeartbeat);
            MainActivity.mTextView.setText(Integer.toString(mHeartbeat));
            if (mHeartbeat < 85) {
                multiplier = 1;
            } else if (mHeartbeat >= 85 && mHeartbeat < 125) {
                multiplier = 1.5;
            } else if (mHeartbeat >= 125 && mHeartbeat < 150) {
                multiplier = 2;
            } else if (mHeartbeat >= 150) {
                multiplier = 2.5;
            }
            MainActivity.mMultiplier.setText(multiplier + "x EXP");

            //if (onChangeListener != null) {
                //Log.d("HB service", "sending new value to listener: " + newValue);
                //onChangeListener.onValueChanged(newValue);
                //sendMessageToHandheld(Integer.toString(newValue));
            //}
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void registerListener(hbListener listener) {
        mListener = listener;
    }

    public void sendBeetsBySchrute() {
        new WatchSenderAsync().execute(mHeartbeat);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("hbservice", "connectionfailed");
    }

    public interface hbListener {
        void callback(int heartbeat);
    }

    private class WatchSenderAsync extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            PutDataMapRequest putDataMapRequest = PutDataMapRequest.create("/heartbeat");
            putDataMapRequest.getDataMap().putInt("heartbeat", integers[0]);
            PutDataRequest putDataRequest = putDataMapRequest.asPutDataRequest();
            PendingResult<DataApi.DataItemResult> pendingResult = Wearable.DataApi.putDataItem(mGoogleApiClient, putDataRequest);
            return null;
        }
    }
}
