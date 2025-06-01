package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._283.adapter.MyAdapter;
import com.hzbhd.canbus.car_cus._283.bean.TextViewBean;
import java.util.ArrayList;
import java.util.List;

public class MyTextView extends LinearLayout {
   private List lists;
   private Context mContext;
   private MyAdapter mMyAdapter;
   private View mView;
   private int textSize;
   private int textSizeInterval;

   public MyTextView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public MyTextView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public MyTextView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.lists = new ArrayList();
      this.textSize = 34;
      this.textSizeInterval = 0;
      this.mContext = var1;
      View var6 = LayoutInflater.from(var1).inflate(2131558472, this, true);
      this.mView = var6;
      RecyclerView var7 = (RecyclerView)var6.findViewById(2131363072);
      LinearLayoutManager var4 = new LinearLayoutManager(var1);
      var4.setOrientation(0);
      var7.setLayoutManager(var4);
      MyAdapter var5 = new MyAdapter(var1, this.lists);
      this.mMyAdapter = var5;
      var7.setAdapter(var5);
   }

   public String getText() {
      StringBuilder var2 = new StringBuilder();

      for(int var1 = 0; var1 < this.lists.size(); ++var1) {
         var2.append(((TextViewBean)this.lists.get(var1)).getText());
      }

      return var2.toString();
   }

   public void setText(String var1) {
      this.lists.clear();

      for(int var2 = 0; var2 < var1.length(); ++var2) {
         this.lists.add(new TextViewBean(String.valueOf(var1.charAt(var2)), this.textSize - this.textSizeInterval * var2));
      }

      this.mMyAdapter.notifyDataSetChanged();
   }
}
