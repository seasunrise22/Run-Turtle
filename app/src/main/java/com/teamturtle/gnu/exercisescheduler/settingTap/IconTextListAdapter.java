package com.teamturtle.gnu.exercisescheduler.settingTap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

//listViewAdapter
public class IconTextListAdapter extends BaseAdapter {
    private Context mContext;
    private List<IconText> mItems = new ArrayList<IconText>();

    public IconTextListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addItem(IconText it) {
        mItems.add(it);
    }


    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewItem listViewItem;
        if (convertView == null){
            listViewItem = new ListViewItem(mContext, mItems.get(position));
        } else {
            listViewItem = (ListViewItem) convertView;
            listViewItem.setIcon(mItems.get(position).getIcon());
            listViewItem.setText(0, mItems.get(position).getData(0));
        }
        return listViewItem;
    }
}
