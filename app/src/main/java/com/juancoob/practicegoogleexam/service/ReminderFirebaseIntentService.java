package com.juancoob.practicegoogleexam.service;

import android.app.IntentService;
import android.content.Intent;

import com.juancoob.practicegoogleexam.util.ReminderTasks;

/**
 * Created by Juan Antonio Cobos Obrero on 3/10/18.
 */
public class ReminderFirebaseIntentService extends IntentService {

    public ReminderFirebaseIntentService() {
        super("ReminderFirebaseIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        ReminderTasks.executeTask(this, action);
    }
}
