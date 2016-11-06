package com.fiteval.controller;

/*
 * Interface for a step detector.
 *
 * @author Andy Georges
 */
public interface StepDetection {

    public void addData(long timestamp, double xAccell, double yAccell, double zAccell);
    public int getSteps();

}