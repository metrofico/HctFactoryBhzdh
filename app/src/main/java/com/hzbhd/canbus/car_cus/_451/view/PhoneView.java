package com.hzbhd.canbus.car_cus._451.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class PhoneView extends LinearLayout {
   private View view;

   public PhoneView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public PhoneView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public PhoneView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.view = LayoutInflater.from(var1).inflate(2131558557, this, true);
   }
}
