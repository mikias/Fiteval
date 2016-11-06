package com.fiteval.controller;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

/*
 * @author Andy Georges
 *
 */

public class AccellService extends Service implements SensorEventListener {

    /*
     * Communication with the Activity that is using this Service.
     */
    private LocalBinder accellBinder = new LocalBinder();
    /*
     * Sensor objects
     */
    private SensorManager mSensorManager;
    private Sensor mAccellSensor;

    /*
     * Global data that is used to make sure the service is actually up and
     * running before using it.
     */
    private boolean started = false;
    private boolean registered = false;

    /*
     * The following object will be used to assign a
     * step detector that is used  */
    private StepDetection detector = null;


    @Override
    public void onCreate() {

        super.onCreate();

		/*
         * Opgave 1. Zorg ervoor dat de volgende variabelen een
		 * correcte waarde krijgen. Je kunt gebruik maken van de
		 * functie getSystemService uit de Context klasse. De
		 * mSensorManager laat je dan toe om de correct sensor op
		 * te vragen a.d.h.v. een waarde in een veld uit de Sensor
		 * klasse.
		 */
        mSensorManager = null;
        mAccellSensor = null;
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccellSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        synchronized (this) {
            if (started) {
                return START_STICKY;
            }

            registered = mSensorManager.registerListener(this, mAccellSensor, SensorManager.SENSOR_DELAY_FASTEST);
            started = true;
        }
        return START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return accellBinder;
    }

    @Override
    public void onDestroy() {
        mSensorManager.unregisterListener(this, mAccellSensor);
        Toast.makeText(this, "AccellMeterService destroyed", Toast.LENGTH_LONG).show();

        Util.get().closeFile();

        super.onDestroy();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER) {
            return;
        }

    	/*
    	 * Opgave 2: Zorg ervoor dat de gemeten data die je in
    	 * het event-object terugvindt ook gelogd wordt door de
    	 * trace methode van InteractionModelSingleton op te roepen
    	 * met de gepaste argumenten. Je kunt hierbij gebruik maken
    	 * van de velden in het event-object alsook van de tijd die
    	 * je terugkrijgt van een Calendar-object dat je in de Java
    	 * API terugvindt (java.util.Calendar).
    	 */
    }

    public float getResolution() {
        return mAccellSensor.getResolution();
    }

    public void setAccuracy(int accuracy) {
    	/* unregister first, otherwise the listener keeps receiving data at the highest set rate */
        synchronized (this) {
            if (registered) {
                mSensorManager.unregisterListener(this);
                registered = false;
            }
            if (mSensorManager.registerListener(this, mAccellSensor, accuracy)) {
                registered = true;
            }
        }
    }

    public void setFilter(StepDetection filter) {
        detector = filter;
    }

    public class LocalBinder extends Binder {
        AccellService getService() {
            return AccellService.this;
        }
    }

}