package com.fiteval.controller;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by nader on 11/11/16.
 */

public class ExperienceService extends Service implements SensorEventListener {

    private int steps = 0;
    private SensorManager mSensorManager;
    private Sensor mStepCtr;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Retrieve the sensor manager and the step counter
        Log.d("Step Service", "Step service started");
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mStepCtr = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        // Register for sensor events
        mSensorManager.registerListener(this, mStepCtr, SensorManager.SENSOR_DELAY_NORMAL);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mSensorManager.unregisterListener(this);

        super.onDestroy();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            steps++;
            Log.d("Step Service", "Steps so far: " + steps);
            //main.getKnight().addExp(calcExp());
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}