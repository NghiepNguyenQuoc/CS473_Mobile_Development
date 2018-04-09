package cubex.listviewcustom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.File;

/**
 * Created by maheshthippala on 01/10/16.
 */
public class MyAdapter extends BaseAdapter {

    String path="/storage/sdcard0/sample_images/";
    File f=new File(path);
    String[] files=f.list();

    MainActivity activity;

    public MyAdapter(MainActivity activity){
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return files.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(activity);

        View v=inflater.inflate(R.layout.indi_view,null);

        return v;
    }
}
