package com.hct.factory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
   private Context mContext;
   private LayoutInflater mInflater;
   private ArrayList mList;
   private int selectItem = -1;

   public ListAdapter(Context var1, ArrayList var2) {
      this.mList = var2;
      this.mContext = var1;
      this.mInflater = LayoutInflater.from(this.mContext);
   }

   public int getCount() {
      return this.mList.size();
   }

   public Object getItem(int var1) {
      return this.mList.get(var1);
   }

   public long getItemId(int var1) {
      return (long)var1;
   }

   public int getSelectedItem() {
      return this.selectItem;
   }

   public View getView(int var1, View var2, ViewGroup var3) {
      View var5 = var2;
      if (var2 == null) {
         var5 = this.mInflater.inflate(2131230734, (ViewGroup)null);
      }

      LinearLayout var4 = (LinearLayout)var5.findViewById(2131099843);
      ((ImageView)var5.findViewById(2131099788)).setBackgroundResource((Integer)this.mList.get(var1));
      if (this.selectItem == var1) {
         var4.setBackgroundResource(2131034240);
      } else {
         var4.setBackgroundResource(0);
      }

      return var5;
   }

   public void setSelectedItem(int var1) {
      this.selectItem = var1;
   }
}
