package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

public class EverScrollTextView extends AppCompatTextView {
   public EverScrollTextView(Context var1) {
      super(var1);
   }

   public EverScrollTextView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   public EverScrollTextView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
   }

   public boolean isFocused() {
      return true;
   }
}
