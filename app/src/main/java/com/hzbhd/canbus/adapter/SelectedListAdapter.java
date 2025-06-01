package com.hzbhd.canbus.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.hzbhd.canbus.adapter.bean.CanTypeAllEntity;
import java.util.List;
import java.util.Locale;

public class SelectedListAdapter extends BaseAdapter {
   private List mList;
   private int mNormalTextColor;
   private int mSelectPosition;
   private int mSelectTextColor;

   public SelectedListAdapter(Context var1, List var2) {
      this.mList = var2;
      this.mSelectTextColor = var1.getColor(2131099752);
      this.mNormalTextColor = var1.getColor(2131100061);
   }

   public int getCount() {
      return this.mList.size();
   }

   public Object getItem(int var1) {
      return null;
   }

   public long getItemId(int var1) {
      return 0L;
   }

   public View getView(int var1, View var2, ViewGroup var3) {
      ViewHolder var4;
      if (var2 == null) {
         var2 = LayoutInflater.from(var3.getContext()).inflate(2131558757, (ViewGroup)null);
         var4 = new ViewHolder(this);
         var4.tv = (TextView)var2.findViewById(2131363578);
         var2.setTag(var4);
      } else {
         var4 = (ViewHolder)var2.getTag();
      }

      if (Locale.getDefault().toString().equals("en_US")) {
         var4.tv.setText(((CanTypeAllEntity)this.mList.get(var1)).getEnglish_car_model() + " " + ((CanTypeAllEntity)this.mList.get(var1)).getEnglish_years());
      } else {
         var4.tv.setText(((CanTypeAllEntity)this.mList.get(var1)).getCar_model() + " " + ((CanTypeAllEntity)this.mList.get(var1)).getYears());
      }

      if (var1 == this.mSelectPosition) {
         var4.tv.setBackgroundResource(2131234654);
         var4.tv.setTextColor(this.mSelectTextColor);
      } else {
         var4.tv.setBackground((Drawable)null);
         var4.tv.setTextColor(this.mNormalTextColor);
      }

      return var2;
   }

   public void setSelectPosition(int var1) {
      this.mSelectPosition = var1;
   }

   public final class ViewHolder {
      final SelectedListAdapter this$0;
      public TextView tv;

      public ViewHolder(SelectedListAdapter var1) {
         this.this$0 = var1;
      }
   }
}
