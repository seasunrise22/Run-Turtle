package com.teamturtle.gnu.exercisescheduler.settingTap;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.content.Context;

//IconTextItem
public class IconText {

    private Drawable mIcon;
    private String [] mDate;
 //   private boolean mSelectable = true;

    public IconText(Drawable icon, String[] obj){
        mIcon = icon;
        mDate = obj;
    }

    public IconText(Drawable icon, String obj1){
        mIcon = icon;

        mDate = new String[1];
        mDate[0] = obj1;
    }

    public String[] getData() {
        return mDate;
    }

    public String getData(int index) {
        if (mDate == null || index >= mDate.length) {
            return null;
        }

        return mDate[index];
    }

    public void setData(String[] obj){
        mDate = obj;
    }

    public Drawable getIcon() {
        return mIcon;
    }
}
