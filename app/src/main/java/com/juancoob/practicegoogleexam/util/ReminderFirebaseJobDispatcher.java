package com.juancoob.practicegoogleexam.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;
import com.juancoob.practicegoogleexam.service.ReminderFirebaseJobService;

/**
 * Created by Juan Antonio Cobos Obrero on 3/10/18.
 */
public class ReminderFirebaseJobDispatcher {

    private static boolean sInitialized;

    synchronized public static void scheduleChargingReminder(@NonNull final Context context) {

        if (sInitialized) return;

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job constraintReminderJob = dispatcher.newJobBuilder()
                .setService(ReminderFirebaseJobService.class)
                .setTag(Constants.REMINDER_JOB_TAG)
                //.setConstraints(Constraint.DEVICE_IDLE)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(
                        Constants.REMINDER_INTERVAL_SECONDS,
                        Constants.REMINDER_INTERVAL_SECONDS + Constants.SYNC_FLEXIBLE_SECONDS))
                .setReplaceCurrent(true)
                .build();

        dispatcher.schedule(constraintReminderJob);

        sInitialized = true;
    }

}
