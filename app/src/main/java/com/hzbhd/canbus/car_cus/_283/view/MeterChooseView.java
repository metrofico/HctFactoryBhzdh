package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.List;

public class MeterChooseView extends ConstraintLayout implements View.OnClickListener {
   private static final int idCounts = 10;
   private List lists = new ArrayList();
   private OnItemClickListener mOnItemClickListener;

   public MeterChooseView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public void onClick(View var1) {
      OnItemClickListener var2 = this.mOnItemClickListener;
      if (var2 != null) {
         var2.itemClick(var1, this.lists.indexOf(var1));
      }

   }

   protected void onFinishInflate() {
      super.onFinishInflate();

      int var2;
      for(int var1 = 0; var1 < 10; var1 = var2) {
         List var4 = this.lists;
         Resources var3 = this.getContext().getResources();
         StringBuilder var5 = (new StringBuilder()).append("meterChooseTv");
         var2 = var1 + 1;
         var4.add((TextView)this.findViewById(var3.getIdentifier(var5.append(var2).toString(), "id", this.getContext().getPackageName())));
         ((TextView)this.lists.get(var1)).setOnClickListener(this);
      }

   }

   public void setOnItemClickListener(OnItemClickListener var1) {
      this.mOnItemClickListener = var1;
   }

   public void setSelectItem(int var1) {
      for(int var2 = 0; var2 < this.lists.size(); ++var2) {
         ((TextView)this.lists.get(var2)).setSelected(false);
      }

      ((TextView)this.lists.get(var1)).setSelected(true);
   }

   public interface OnItemClickListener {
      void itemClick(View var1, int var2);
   }
}
