package com.juancoob.practicegoogleexam.util;

import java.util.concurrent.TimeUnit;

/**
 * Created by Juan Antonio Cobos Obrero on 30/09/18.
 */
public abstract class Constants {

    // JobScheduler time
    public static final String REMINDER_JOB_TAG = "reminderJobTag";
    public static final int REMINDER_INTERVAL_MINUTES = 15;
    public static final int REMINDER_INTERVAL_SECONDS = (int) TimeUnit.MINUTES.toSeconds(REMINDER_INTERVAL_MINUTES);
    public static final int SYNC_FLEXIBLE_SECONDS = REMINDER_INTERVAL_SECONDS;

    // Notification actions
    public static final String ACTION_INCREMENT_COUNT = "incrementCount";
    public static final String ACTION_DISMISS = "dismiss";
    public static final String ACTION_NOTIFICATION_REMINDER = "notificationReminder";
    public static final String REMINDER_NOTIFICATION_CHANNEL_ID = "123";
    public static final int REMINDER_PENDING_INTENT_ID = 456;
    public static final int REMINDER_PENDING_INTENT_DISMISS_ID = 789;
    public static final int REMINDER_PENDING_INTENT_INCREMENT_ID = 12;
    public static final int REMINDER_NOTIFICATION_ID = 34;

    // Sharedpreferences
    public static final String INCREMENT_KEY = "incrementKey";
    public static final int DEFAULT_INCREMENT_VALUE = 0;

}
