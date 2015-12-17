package co.xinyue.wms.myheckproject;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener{
    private ListView listView;
    private List<MainClickItem> mainClickItems=new ArrayList<>();
    private ArrayAdapter<MainClickItem> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= getListView();
        arrayAdapter=new ArrayAdapter<MainClickItem>(MainActivity.this,android.R.layout.simple_list_item_1,mainClickItems);
        mainClickItems.add(new MainClickItem("应用列表",new Intent(MainActivity.this,AppInfoActivity.class)));
        mainClickItems.add(new MainClickItem("应用列表分类",new Intent(MainActivity.this,AppBranchActivity.class)));

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
        startService(new Intent(this, MyService1.class));
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(mainClickItems.get(position).intent);
    }
}
