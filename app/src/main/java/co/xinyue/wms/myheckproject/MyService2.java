package co.xinyue.wms.myheckproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService2 extends Service {
    public MyService2() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.showNotification(this,"MyService2","成功启动",0);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        startService(new Intent(this,MyService1.class));
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
