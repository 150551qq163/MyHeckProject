package co.xinyue.wms.myheckproject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppInfoActivity extends ListActivity implements AdapterView.OnItemClickListener{
    private ListView listView;
    private AppListAdapter appListAdapter;
    private List<AppInfo> appInfos;
    private PackageManager packageManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        listView=getListView();
        appInfos=new ArrayList<>();
        queryAppInfo();
        appListAdapter=new AppListAdapter(AppInfoActivity.this,appInfos);
        listView.setAdapter(appListAdapter);
        listView.setOnItemClickListener(this);
    }

    private void queryAppInfo() {
        packageManager=getPackageManager();
        //Intent Action Category
        Intent mainIntent=new Intent(Intent.ACTION_MAIN,null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        //packageManager
        List<ResolveInfo> resoveInfos = packageManager.queryIntentActivities(mainIntent, PackageManager.MATCH_DEFAULT_ONLY);
        Collections.sort(resoveInfos,new ResolveInfo.DisplayNameComparator(packageManager));
        if(appInfos!=null){
            appInfos.clear();
            ApplicationInfo applicationInfo=null;
            for (ResolveInfo resolveInfo:resoveInfos){
                //ResolveInfo
                String activityName=resolveInfo.activityInfo.name;
                String pkgName=resolveInfo.activityInfo.packageName;
                String appLabel=resolveInfo.loadLabel(packageManager).toString();
                String processName=resolveInfo.activityInfo.processName;

                applicationInfo=resolveInfo.activityInfo.applicationInfo;
                String applicationName=applicationInfo.name;
                String backupAgentName = applicationInfo.backupAgentName;
                String dataDir = applicationInfo.dataDir;
                String nativeLibraryDir = applicationInfo.nativeLibraryDir;
                String permission = applicationInfo.permission;
                String applicationInfoProcessName = applicationInfo.processName;
                Drawable icon=resolveInfo.loadIcon(packageManager);

                Intent lanchIntent=new Intent();
                lanchIntent.setClassName(pkgName,activityName);
                AppInfo appInfo=new AppInfo(appLabel,icon,lanchIntent,pkgName,processName,dataDir,permission,null);
                appInfos.add(appInfo);

            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//
        new AlertDialog.Builder(AppInfoActivity.this).setTitle("应用信息").setIcon(appInfos.get(position).appIcon).setMessage(appInfos.get(position).toString()).setPositiveButton("启动", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(appInfos.get(position).intent);
            }
        }).setNegativeButton("取消",null).show();
    }
}
