package com.hzbhd.canbus.car_cus._439.smartPanel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.core.content.ContextCompat;
import com.hzbhd.canbus.R;

public class PanelView extends Button {
   protected int id_n;
   protected int id_p;

   public PanelView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public PanelView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public PanelView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.id_n = 0;
      this.id_p = 0;
      TypedArray var4 = var1.obtainStyledAttributes(var2, R.styleable.img_attr);
      this.id_n = var4.getResourceId(2, 0);
      this.id_p = var4.getResourceId(3, 0);
      this.setSelect(var1, false);
   }

   public void setBackground(Drawable var1) {
      super.setBackground(var1);
   }

   public void setImg(Drawable var1) {
      if (this.id_n != 0) {
         this.setBackground(var1);
      }

   }

   public void setSelect(Context var1, boolean var2) {
      int var3;
      if (var2) {
         var3 = this.id_p;
         if (var3 != 0) {
            this.setBackground(ContextCompat.getDrawable(var1, var3));
         }
      } else {
         var3 = this.id_n;
         if (var3 != 0) {
            this.setBackground(ContextCompat.getDrawable(var1, var3));
         }
      }

   }
}
