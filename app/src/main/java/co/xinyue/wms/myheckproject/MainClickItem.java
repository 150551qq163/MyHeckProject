package co.xinyue.wms.myheckproject;

import android.content.Intent;

/**
 * Created by wms on 2015/12/14.
 */
public class MainClickItem {
    String name;
    Intent intent;

    public MainClickItem(String name, Intent intent) {
        this.name = name;
        this.intent = intent;
    }

    @Override
    public String toString() {
        return name;
    }
}
