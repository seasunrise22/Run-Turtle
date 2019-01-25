package com.teamturtle.gnu.exercisescheduler.settingTap;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teamturtle.gnu.exercisescheduler.R;

public class ListViewItem extends LinearLayout{

    private ImageView mIcon;
    private TextView mText;

    public ListViewItem(Context context, IconText aItem) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.activity_list_view_item, this, true);

        mIcon = (ImageView) findViewById(R.id.imageView);
        mIcon.setImageDrawable(aItem.getIcon());

        mText = (TextView) findViewById(R.id.textView);
        mText.setText(aItem.getData(0));
    }

    public void setText(int index, String data){
        if (index == 0){
            mText.setText(data);
        }
        else{
            throw new IllegalArgumentException();
        }

    }

    public void setIcon(Drawable icon){
        mIcon.setImageDrawable(icon);
    }

    public ListViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewItem(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

}
