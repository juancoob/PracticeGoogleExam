package com.juancoob.practicegoogleexam.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.juancoob.practicegoogleexam.R;
import com.juancoob.practicegoogleexam.service.ReminderFirebaseIntentService;
import com.juancoob.practicegoogleexam.ui.MainActivity;

/**
 * Created by Juan Antonio Cobos Obrero on 4/10/18.
 */
public class NotificationUtils {

    public static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static void clearAllNotifications(Context context) {
        NotificationManager notificationManager = getNotificationManager(context);
        notificationManager.cancelAll();
    }

    public static void reminderNotification(Context context) {
        NotificationManager notificationManager = getNotificationManager(context);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    Constants.REMINDER_NOTIFICATION_CHANNEL_ID,
                    context.getString(R.string.notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Constants.REMINDER_NOTIFICATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(setLargeIcon(context))
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_message))
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(context.getString(R.string.notification_message)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .addAction(ignoreReminderAction(context))
                .addAction(incrementNumber(context))
                .setAutoCancel(true);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(Constants.REMINDER_NOTIFICATION_ID, builder.build());

    }

    private static Bitmap setLargeIcon(Context context) {
        Resources resources = context.getResources();
        return BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background);
    }

    private static PendingIntent contentIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(context, Constants.REMINDER_PENDING_INTENT_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static NotificationCompat.Action ignoreReminderAction(Context context) {
        Intent ignoreIntent = new Intent(context, ReminderFirebaseIntentService.class);
        ignoreIntent.setAction(Constants.ACTION_DISMISS);
        PendingIntent pendingIntent = PendingIntent.getService(context,
                Constants.REMINDER_PENDING_INTENT_DISMISS_ID, ignoreIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Action(R.drawable.ic_clear_black_24dp, context.getString(R.string.notification_option_dismiss), pendingIntent);
    }

    private static NotificationCompat.Action incrementNumber(Context context) {
        Intent incrementIntent = new Intent(context, ReminderFirebaseIntentService.class);
        incrementIntent.setAction(Constants.ACTION_INCREMENT_COUNT);
        PendingIntent pendingIntent = PendingIntent.getService(context,
                Constants.REMINDER_PENDING_INTENT_INCREMENT_ID, incrementIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Action(R.drawable.ic_launcher_foreground, context.getString(R.string.notification_option_agree), pendingIntent);
    }

}
