package co.xinyue.wms.myheckproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals("android.intent.action.BOOT_COMPLETED")){
            Utils.showNotification(context, action, "成功检测到开机广播",1);
            context.startService(new Intent(context,MyService1.class));
        }else if(action.equals("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE")){
            String[] pkgList=intent.getStringArrayExtra(Intent.EXTRA_CHANGED_PACKAGE_LIST);
            StringBuilder stringBuilder=new StringBuilder();
            if (pkgList==null||pkgList.length==0){
                stringBuilder.append("no app");
            }else{
                for (String pkgname :
                        pkgList) {
                    stringBuilder.append(pkgname).append("\n");
                }
            }
            Utils.showNotification(context,action,stringBuilder.toString(),2);
        }else if(action.equals("android.intent.action.SCREEN_ON")){
            Utils.showNotification(context,action,"SCREEN_ON",3);
//            context.startService(new Intent(context,MyService1.class));
        }else if(action.equals("android.intent.action.NEW_OUTGOING_CALL")){
            Utils.showNotification(context,action,"NEW_OUTGOING_CALL",4);
//            context.startService(new Intent(context,MyService1.class));
        }else if(action.equals("android.intent.action.CONFIGURATION_CHANGED")){
            Utils.showNotification(context,action,"CONFIGURATION_CHANGED",5);
//            context.startService(new Intent(context,MyService1.class));
        }else if(action.equals("android.intent.action.PACKAGE_ADDED")){
            Uri data=intent.getData();
            String pkgName=data.getEncodedSchemeSpecificPart();
            Utils.showNotification(context,action,"pkgName",6);
        }
    }
}
