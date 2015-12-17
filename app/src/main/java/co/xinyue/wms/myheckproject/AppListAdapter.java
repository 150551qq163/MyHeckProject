package co.xinyue.wms.myheckproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wms on 2015/12/14.
 */
public class AppListAdapter extends BaseAdapter {
    private List<AppInfo> appInfos=null;
    LayoutInflater layoutInflater=null;

    public AppListAdapter(Context context,List<AppInfo> appInfos) {
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.appInfos = appInfos;
    }

    public void setAppInfos(List<AppInfo> appInfos) {
        this.appInfos = appInfos;
    }

    @Override
    public int getCount() {
        return appInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return appInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
        AppInfo appInfo=appInfos.get(position);
        viewHolder.appIcon.setImageDrawable(appInfo.appIcon);
        viewHolder.label.setText(appInfo.appLabel);
        viewHolder.pagName.setText(appInfo.pkgName);
        return view;
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
}
