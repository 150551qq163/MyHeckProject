package co.xinyue.wms.myheckproject;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * Created by wms on 2015/12/10.
 * UncaughtException 处理类，当程序发生Uncaught异常时，由该类来接管程序，并记录发送错误报告
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";
    //系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    private static CrashHandler INSTANCE = new CrashHandler();
    private Context context;
    private HashMap<String, String> infos = new HashMap<>();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private CrashHandler() {
    }

    public static CrashHandler getINSTANCE() {
        return INSTANCE;
    }

    public void init(Context context) {
        this.context = context;
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && uncaughtExceptionHandler != null) {
            //如果用户没有处理则让系统默认的异常处理来处理
            uncaughtExceptionHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error:", e);
                e.printStackTrace();
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理，收集错误信息，发送错误报告
     *
     * @param ex
     * @return
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "很抱歉,程序出现异常,即将退出", Toast.LENGTH_SHORT).show();
            }
        }.start();
        collectDeviceInfo(context);
        saveCrashInfo2File(ex);
        return true;
    }

    /**
     * 收集设备参数信息
     * @param context
     */
    private void collectDeviceInfo(Context context) {
        PackageManager packageManager=context.getPackageManager();
//        packageManager.
    }

    private void saveCrashInfo2File(Throwable ex) {

    }
}
