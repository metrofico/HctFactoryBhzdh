package com.google.android.material.card;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.cardview.widget.CardView;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;

public class MaterialCardView extends CardView {
   private final MaterialCardViewHelper cardViewHelper;

   public MaterialCardView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public MaterialCardView(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.materialCardViewStyle);
   }

   public MaterialCardView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      TypedArray var5 = ThemeEnforcement.obtainStyledAttributes(var1, var2, R.styleable.MaterialCardView, var3, R.style.Widget_MaterialComponents_CardView);
      MaterialCardViewHelper var4 = new MaterialCardViewHelper(this);
      this.cardViewHelper = var4;
      var4.loadFromAttributes(var5);
      var5.recycle();
   }

   public int getStrokeColor() {
      return this.cardViewHelper.getStrokeColor();
   }

   public int getStrokeWidth() {
      return this.cardViewHelper.getStrokeWidth();
   }

   public void setRadius(float var1) {
      super.setRadius(var1);
      this.cardViewHelper.updateForeground();
   }

   public void setStrokeColor(int var1) {
      this.cardViewHelper.setStrokeColor(var1);
   }

   public void setStrokeWidth(int var1) {
      this.cardViewHelper.setStrokeWidth(var1);
   }
}
