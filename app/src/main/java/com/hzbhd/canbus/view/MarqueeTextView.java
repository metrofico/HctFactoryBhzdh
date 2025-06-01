package com.hzbhd.canbus.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewDebug.ExportedProperty;
import androidx.appcompat.widget.AppCompatTextView;

public class MarqueeTextView extends AppCompatTextView {
   public MarqueeTextView(Context var1, AttributeSet var2) {
      super(var1, var2);
   }

   @ExportedProperty(
      category = "focus"
   )
   public boolean isFocused() {
      return true;
   }

   protected void onFocusChanged(boolean var1, int var2, Rect var3) {
      super.onFocusChanged(var1, var2, var3);
   }

   public void onWindowFocusChanged(boolean var1) {
      if (var1) {
         super.onWindowFocusChanged(var1);
      }

   }
}
