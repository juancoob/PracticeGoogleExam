package com.juancoob.practicegoogleexam.service;


import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;


import com.juancoob.practicegoogleexam.util.AppExecutors;
import com.juancoob.practicegoogleexam.util.Constants;
import com.juancoob.practicegoogleexam.util.ReminderTasks;

/**
 * Created by Juan Antonio Cobos Obrero on 3/10/18.
 */
public class ReminderJobService extends JobService {
    @Override
    public boolean onStartJob(final JobParameters job) {

        AppExecutors.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                Context context = ReminderJobService.this;
                ReminderTasks.executeTask(context, Constants.ACTION_NOTIFICATION_REMINDER);
            }
        });

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
}
