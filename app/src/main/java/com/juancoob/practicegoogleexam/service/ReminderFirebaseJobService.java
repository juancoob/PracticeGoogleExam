package com.juancoob.practicegoogleexam.service;


import android.content.Context;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.juancoob.practicegoogleexam.util.AppExecutors;
import com.juancoob.practicegoogleexam.util.Constants;
import com.juancoob.practicegoogleexam.util.ReminderTasks;

/**
 * Created by Juan Antonio Cobos Obrero on 3/10/18.
 */
public class ReminderFirebaseJobService extends JobService {
    @Override
    public boolean onStartJob(final JobParameters job) {

        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Context context = ReminderFirebaseJobService.this;
                ReminderTasks.executeTask(context, Constants.ACTION_NOTIFICATION_REMINDER);
                jobFinished(job, false);
            }
        });

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return true;
    }
}
