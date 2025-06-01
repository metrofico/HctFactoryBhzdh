package com.google.android.material.internal;

import android.graphics.Outline;

public class CircularBorderDrawableLollipop extends CircularBorderDrawable {
   public void getOutline(Outline var1) {
      this.copyBounds(this.rect);
      var1.setOval(this.rect);
   }
}
