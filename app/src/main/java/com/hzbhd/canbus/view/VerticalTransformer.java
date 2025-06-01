package com.hzbhd.canbus.view;

import android.view.View;
import androidx.viewpager.widget.ViewPager;

public class VerticalTransformer implements ViewPager.PageTransformer {
   public void transformPage(View var1, float var2) {
      if (var2 < -1.0F) {
         var1.setAlpha(0.0F);
      } else if (var2 <= 1.0F) {
         var1.setAlpha(1.0F);
         var1.setTranslationX((float)var1.getWidth() * -var2);
         var1.setTranslationY(var2 * (float)var1.getHeight());
      } else {
         var1.setAlpha(0.0F);
      }

   }
}
