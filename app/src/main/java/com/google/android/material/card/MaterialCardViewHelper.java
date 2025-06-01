package com.google.android.material.card;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import com.google.android.material.R;

class MaterialCardViewHelper {
   private static final int DEFAULT_STROKE_VALUE = -1;
   private final MaterialCardView materialCardView;
   private int strokeColor;
   private int strokeWidth;

   public MaterialCardViewHelper(MaterialCardView var1) {
      this.materialCardView = var1;
   }

   private void adjustContentPadding() {
      int var7 = this.materialCardView.getContentPaddingLeft();
      int var3 = this.strokeWidth;
      int var1 = this.materialCardView.getContentPaddingTop();
      int var5 = this.strokeWidth;
      int var2 = this.materialCardView.getContentPaddingRight();
      int var6 = this.strokeWidth;
      int var8 = this.materialCardView.getContentPaddingBottom();
      int var4 = this.strokeWidth;
      this.materialCardView.setContentPadding(var7 + var3, var1 + var5, var2 + var6, var8 + var4);
   }

   private Drawable createForegroundDrawable() {
      GradientDrawable var2 = new GradientDrawable();
      var2.setCornerRadius(this.materialCardView.getRadius());
      int var1 = this.strokeColor;
      if (var1 != -1) {
         var2.setStroke(this.strokeWidth, var1);
      }

      return var2;
   }

   int getStrokeColor() {
      return this.strokeColor;
   }

   int getStrokeWidth() {
      return this.strokeWidth;
   }

   public void loadFromAttributes(TypedArray var1) {
      this.strokeColor = var1.getColor(R.styleable.MaterialCardView_strokeColor, -1);
      this.strokeWidth = var1.getDimensionPixelSize(R.styleable.MaterialCardView_strokeWidth, 0);
      this.updateForeground();
      this.adjustContentPadding();
   }

   void setStrokeColor(int var1) {
      this.strokeColor = var1;
      this.updateForeground();
   }

   void setStrokeWidth(int var1) {
      this.strokeWidth = var1;
      this.updateForeground();
      this.adjustContentPadding();
   }

   void updateForeground() {
      this.materialCardView.setForeground(this.createForegroundDrawable());
   }
}
