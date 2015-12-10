package co.xinyue.wms.myheckproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

/**
 * Created by wms on 2015/12/10.
 */
public class Utils {
    public static void showNotification(Context context,String title,String contenttext,int number) {
        NotificationManager notificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification=new Notification.Builder(context).setContentTitle(title).setContentText(contenttext).setSmallIcon(R.mipmap.ic_launcher).build();
        notificationManager.notify(number,notification);
    }
}
