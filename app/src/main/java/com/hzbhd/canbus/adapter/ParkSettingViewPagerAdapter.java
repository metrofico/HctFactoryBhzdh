package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.hzbhd.canbus.entity.ParkSettingEntity;
import java.util.List;

public class ParkSettingViewPagerAdapter extends PagerAdapter {
   private Context mContext;
   private List mList;

   public ParkSettingViewPagerAdapter(Context var1, List var2) {
      this.mContext = var1;
      this.mList = var2;
   }

   public void destroyItem(ViewGroup var1, int var2, Object var3) {
      var1.removeView(((ParkSettingEntity)this.mList.get(var2)).getView());
   }

   public int getCount() {
      return this.mList.size();
   }

   public CharSequence getPageTitle(int var1) {
      return ((ParkSettingEntity)this.mList.get(var1)).getTitle();
   }

   public Object instantiateItem(ViewGroup var1, int var2) {
      var1.addView(((ParkSettingEntity)this.mList.get(var2)).getView(), 0);
      return ((ParkSettingEntity)this.mList.get(var2)).getView();
   }

   public boolean isViewFromObject(View var1, Object var2) {
      boolean var3;
      if (var1 == var2) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }
}
