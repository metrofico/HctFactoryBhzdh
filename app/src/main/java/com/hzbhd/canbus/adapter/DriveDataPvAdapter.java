package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DriveDataPvAdapter extends PagerAdapter {
   private Context mContext;
   private List mDriveDataLvItemAdapterList;
   private List mList;

   public DriveDataPvAdapter(Context var1, List var2) {
      this.mContext = var1;
      this.mList = var2;
      this.mDriveDataLvItemAdapterList = new ArrayList();
   }

   public void destroyItem(ViewGroup var1, int var2, Object var3) {
      var1.removeView((View)var3);
   }

   public int getCount() {
      List var1 = this.mList;
      return var1 != null ? var1.size() : 0;
   }

   public Object instantiateItem(ViewGroup var1, int var2) {
      View var5 = LayoutInflater.from(var1.getContext()).inflate(2131558762, (ViewGroup)null);
      DriveDataLvItemAdapter var6 = new DriveDataLvItemAdapter(this.mContext, ((DriverDataPageUiSet.Page)this.mList.get(var2)).getItemList());
      RecyclerView var4 = (RecyclerView)var5.findViewById(2131363208);
      int var3 = ((DriverDataPageUiSet.Page)this.mList.get(var2)).getSpanCount();
      var2 = var3;
      if (var3 == 0) {
         var2 = 3;
      }

      var4.setLayoutManager(new GridLayoutManager(var1.getContext(), var2, 1, false));
      var4.setAdapter(var6);
      var1.addView(var5, 0);
      this.mDriveDataLvItemAdapterList.add(var6);
      return var5;
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

   public void notifyDataSetChanged() {
      super.notifyDataSetChanged();
      Iterator var2 = this.mDriveDataLvItemAdapterList.iterator();

      while(var2.hasNext()) {
         DriveDataLvItemAdapter var1 = (DriveDataLvItemAdapter)var2.next();
         if (var1 != null) {
            var1.notifyDataSetChanged();
         } else {
            LogUtil.showLog("mDriveDataLvItemAdapter==null");
         }
      }

   }
}
