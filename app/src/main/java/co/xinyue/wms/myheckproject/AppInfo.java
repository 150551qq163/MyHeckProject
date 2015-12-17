package co.xinyue.wms.myheckproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wms on 2015/12/14.
 */
public class AppInfo {
    String appLabel;
    Drawable appIcon;
    Intent intent;
    String pkgName;
    String processName;
    String dataDir;
    String permission, publicSourceDir;
    List<String> infoList;

    public AppInfo() {
    }

    public void setInfoList() {
        infoList = new ArrayList<>();
        infoList.add("appLabel:" + appLabel);
        infoList.add("pkgName:" + pkgName);
        infoList.add("processName:" + processName);
        infoList.add("dataDir:" + dataDir);
        infoList.add("permission:" + permission);
        infoList.add("publicSourceDir:" + publicSourceDir);
    }

    public AppInfo(String appLabel, Drawable appIcon, Intent intent, String pagName, String processName, String dataDir, String permission,String publicSourceDir) {
        this.appLabel = appLabel;
        this.appIcon = appIcon;
        this.intent = intent;
        this.pkgName = pagName;
        this.processName = processName;
        this.dataDir = dataDir;
        this.permission = permission;
        this.publicSourceDir=publicSourceDir;
        setInfoList();

    }

    @Override
    public String toString() {
        return new StringBuffer().append("appLabel:").append(appLabel).append("\npkgName:").append(pkgName).append("\nprocessName:").append(processName)
                .append("\ndataDir:").append(dataDir).append("\npermission:").append(permission).toString();
    }
}
