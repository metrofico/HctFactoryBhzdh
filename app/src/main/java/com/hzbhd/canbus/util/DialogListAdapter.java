package com.hzbhd.canbus.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogListAdapter extends BaseAdapter {
   private Context mContext;
   private int mSelectIndex;
   private String[] mStrArray;

   public DialogListAdapter(Context var1, String[] var2) {
      this.mContext = var1;
      this.mStrArray = var2;
   }

   public int getCount() {
      return this.mStrArray.length;
   }

   public Object getItem(int var1) {
      return null;
   }

   public long getItemId(int var1) {
      return (long)var1;
   }

   public View getView(int var1, View var2, ViewGroup var3) {
      ViewHolder var4;
      if (var2 == null) {
         var2 = View.inflate(this.mContext, 2131558757, (ViewGroup)null);
         var4 = new ViewHolder(this);
         var4.textView = (TextView)var2.findViewById(2131363578);
         var4.imageView = (ImageView)var2.findViewById(2131362640);
         var2.setTag(var4);
      } else {
         var4 = (ViewHolder)var2.getTag();
      }

      if (this.mSelectIndex == var1) {
         var4.imageView.setVisibility(0);
      } else {
         var4.imageView.setVisibility(8);
      }

      var4.textView.setText(this.mStrArray[var1]);
      return var2;
   }

   public void setSelectedIndex(int var1) {
      this.mSelectIndex = var1;
   }

   public class ViewHolder {
      private ImageView imageView;
      private TextView textView;
      final DialogListAdapter this$0;

      public ViewHolder(DialogListAdapter var1) {
         this.this$0 = var1;
      }
   }
}
