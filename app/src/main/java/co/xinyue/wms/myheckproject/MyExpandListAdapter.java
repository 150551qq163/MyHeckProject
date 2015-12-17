package co.xinyue.wms.myheckproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wms on 2015/12/16.
 */
public class MyExpandListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<AppInfo> appInfos;
    private LayoutInflater layoutInflater;

    public MyExpandListAdapter(Context context, List<AppInfo> appInfos) {
        this.context = context;
        this.appInfos = appInfos;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return appInfos.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 4;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return appInfos.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return appInfos.get(groupPosition).infoList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view=null;
        ViewHolder viewHolder=null;
        if(convertView==null){
            view=layoutInflater.inflate(R.layout.main_list_item,null);
            viewHolder=new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        AppInfo appInfo=appInfos.get(groupPosition);
        viewHolder.appIcon.setImageDrawable(appInfo.appIcon);
        viewHolder.label.setText(appInfo.appLabel);
        viewHolder.pagName.setText(appInfo.pkgName);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView textView=null;
//        ChildHolder childHolder=null;
        if(convertView==null){
            textView= (TextView) layoutInflater.inflate(android.R.layout.simple_list_item_1,null);
//            childHolder =new ChildHolder(view);
//            view.setTag(childHolder);
        }else{
            textView= (TextView) convertView;
//            childHolder= (ChildHolder) view.getTag();
        }
        AppInfo appInfo=appInfos.get(groupPosition);
        textView.setText(appInfos.get(groupPosition).infoList.get(childPosition));
//        childHolder.dataDir.setText(appInfo.dataDir);
//        childHolder.permission.setText(appInfo.permission);
//        childHolder.processName.setText(appInfo.processName);
//        childHolder.publicSourceDir.setText(appInfo.publicSourceDir);
        return textView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    class ViewHolder{
        ImageView appIcon;
        TextView label;
        TextView pagName;

        public ViewHolder(View view) {
            this.appIcon = (ImageView) view.findViewById(R.id.imageApp);
            this.label = (TextView) view.findViewById(R.id.tv_label);
            this.pagName = (TextView) view.findViewById(R.id.tv_package);
        }
    }
//    class ChildHolder{
//        TextView dataDir;
//        TextView permission;
//        TextView processName;
//        TextView publicSourceDir;
//
//        public ChildHolder(View view) {
//            this.dataDir = (TextView) view.findViewById(R.id.dataDir);
//            this.permission = (TextView) view.findViewById(R.id.permission);
//            this.processName = (TextView) view.findViewById(R.id.processName);
//            this.publicSourceDir = (TextView) view.findViewById(R.id.publicSourceDir);
//        }
//    }
}
