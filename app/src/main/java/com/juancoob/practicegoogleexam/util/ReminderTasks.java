package com.juancoob.practicegoogleexam.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Juan Antonio Cobos Obrero on 3/10/18.
 */
public class ReminderTasks {

    public static void executeTask(Context context, String action) {

        if(Constants.ACTION_INCREMENT_COUNT.equals(action)) {
            saveIncrement(context);
        } else if (Constants.ACTION_DISMISS.equals(action)) {
            NotificationUtils.clearAllNotifications(context);
        } else if (Constants.ACTION_NOTIFICATION_REMINDER.equals(action)) {
            NotificationUtils.reminderNotification(context);
        }
    }

    public static void saveIncrement(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        int counter = sharedPreferences.getInt(Constants.INCREMENT_KEY, Constants.DEFAULT_INCREMENT_VALUE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.INCREMENT_KEY, ++counter);
        editor.apply();
    }

}
