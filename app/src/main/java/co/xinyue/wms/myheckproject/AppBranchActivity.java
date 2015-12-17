package co.xinyue.wms.myheckproject;

import android.app.AlertDialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppBranchActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private PagerTabStrip tabStrip;
    private ArrayList<View> views=new ArrayList<>();
    private String[] titles;
    private LayoutInflater layoutInflater;
    private int[] tabs=new int[]{R.layout.tab1,R.layout.tab2,R.layout.tab3,R.layout.tab4};
    private MyPagerAdapter adapter;
    private Resources resources;
    private static final int FILTER_ALL_APP=0,FILTERSYSTEM_APP=1,FILTER_THIRD_APP=2,FILTER_SDCARD_APP=3;
    private ListView listView=null;
    private PackageManager packageManager;
    private int filter=FILTER_ALL_APP;
    private List<AppInfo> appInfoList;
    private int[] listViewIds=new int[]{R.id.all_app_list,R.id.system_app_list,R.id.third_app_list,R.id.sdCard_app_list,R.id.permissionGroup};
    private ExpandableListView[] listViews;
//    private AppListAdapter appListAdapter;
    private MyExpandListAdapter expandListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_branch);
        resources=getResources();
        titles=resources.getStringArray(R.array.titles);
        layoutInflater=getLayoutInflater();
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        tabStrip= (PagerTabStrip) findViewById(R.id.tabstrip);
        tabStrip.setDrawFullUnderline(false);
        tabStrip.setTextSpacing(40);
        tabStrip.setBackgroundColor(getResources().getColor(android.R.color.black));
        tabStrip.setTabIndicatorColor(getResources().getColor(android.R.color.holo_green_dark));
        tabStrip.setTextSpacing(200);
        packageManager=getPackageManager();
        appInfoList=new ArrayList<>();
        listViews=new ExpandableListView[4];
        View view=null;
//        appListAdapter =new AppListAdapter(AppBranchActivity.this,null);
        expandListAdapter=new MyExpandListAdapter(AppBranchActivity.this,appInfoList);
        for (int i=0;i<4;i++){
            view=layoutInflater.inflate(tabs[i],null);
            listViews[i]= (ExpandableListView) view.findViewById(listViewIds[i]);
//            listViews[i].setAdapter(appListAdapter);
            listViews[i].setAdapter(expandListAdapter);
            views.add(view);
        }
        adapter=new MyPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        tabStrip.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
    }

    @Override
    public void onPageSelected(int position) {
//        new AlertDialog.Builder(AppBranchActivity.this).setMessage("page"+position+"is selected").show();
        tabStrip.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        queryFilterAppInfo(filter);
//        appListAdapter.setAppInfos(appInfoList);
//        appListAdapter.notifyDataSetChanged();
        expandListAdapter.notifyDataSetChanged();
    }
    private void queryPackageInfo(){
        List<PermissionGroupInfo> permissions = packageManager.getAllPermissionGroups(PackageManager.GET_META_DATA);
    }
    private void queryFilterAppInfo(int filter) {
        List<ApplicationInfo> applicationInfos=packageManager.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        Collections.sort(applicationInfos, new ApplicationInfo.DisplayNameComparator(packageManager));
        List<ApplicationInfo> infoList=new ArrayList<>();
        infoList.clear();
        switch (filter){
            case FILTER_ALL_APP:
                for (ApplicationInfo appInfo:applicationInfos){
                    appInfoList.add(getAppInfo(appInfo));
                }
                break;
            case FILTERSYSTEM_APP:
                for (ApplicationInfo appInfo:applicationInfos){
                    if ((appInfo.flags&ApplicationInfo.FLAG_SYSTEM)!=0){
                        appInfoList.add(getAppInfo(appInfo));
                    }

                }
                break;
            case FILTER_THIRD_APP:
                for (ApplicationInfo appInfo:applicationInfos){
                    if ((appInfo.flags&ApplicationInfo.FLAG_SYSTEM)<=0){
                        appInfoList.add(getAppInfo(appInfo));
                    }
                }
                break;
            case FILTER_SDCARD_APP:
                break;
        }
    }

    private AppInfo getAppInfo(ApplicationInfo appInfo) {
        AppInfo info=new AppInfo();
        info.appLabel=appInfo.loadLabel(packageManager).toString();//
        info.appIcon=appInfo.loadIcon(packageManager);//
        info.pkgName =appInfo.packageName;//
        info.dataDir=appInfo.dataDir;
        info.permission=appInfo.permission;
        info.processName=appInfo.processName;
        info.publicSourceDir=appInfo.publicSourceDir;
        info.setInfoList();
        return info;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        tabStrip.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
    }

    class MyPagerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(views.get(position));
//            listViews[position].setAdapter();
            return views.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
