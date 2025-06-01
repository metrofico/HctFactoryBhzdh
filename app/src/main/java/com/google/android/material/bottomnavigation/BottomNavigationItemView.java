package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R;

public class BottomNavigationItemView extends FrameLayout implements MenuView.ItemView {
   private static final int[] CHECKED_STATE_SET = new int[]{16842912};
   public static final int INVALID_ITEM_POSITION = -1;
   private final int defaultMargin;
   private ImageView icon;
   private ColorStateList iconTint;
   private boolean isShifting;
   private MenuItemImpl itemData;
   private int itemPosition;
   private int labelVisibilityMode;
   private final TextView largeLabel;
   private float scaleDownFactor;
   private float scaleUpFactor;
   private float shiftAmount;
   private final TextView smallLabel;

   public BottomNavigationItemView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public BottomNavigationItemView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public BottomNavigationItemView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.itemPosition = -1;
      Resources var5 = this.getResources();
      LayoutInflater.from(var1).inflate(R.layout.design_bottom_navigation_item, this, true);
      this.setBackgroundResource(R.drawable.design_bottom_navigation_item_background);
      this.defaultMargin = var5.getDimensionPixelSize(R.dimen.design_bottom_navigation_margin);
      this.icon = (ImageView)this.findViewById(R.id.icon);
      TextView var4 = (TextView)this.findViewById(R.id.smallLabel);
      this.smallLabel = var4;
      TextView var6 = (TextView)this.findViewById(R.id.largeLabel);
      this.largeLabel = var6;
      ViewCompat.setImportantForAccessibility(var4, 2);
      ViewCompat.setImportantForAccessibility(var6, 2);
      this.setFocusable(true);
      this.calculateTextScaleFactors(var4.getTextSize(), var6.getTextSize());
   }

   private void calculateTextScaleFactors(float var1, float var2) {
      this.shiftAmount = var1 - var2;
      this.scaleUpFactor = var2 * 1.0F / var1;
      this.scaleDownFactor = var1 * 1.0F / var2;
   }

   private void setViewLayoutParams(View var1, int var2, int var3) {
      FrameLayout.LayoutParams var4 = (FrameLayout.LayoutParams)var1.getLayoutParams();
      var4.topMargin = var2;
      var4.gravity = var3;
      var1.setLayoutParams(var4);
   }

   private void setViewValues(View var1, float var2, float var3, int var4) {
      var1.setScaleX(var2);
      var1.setScaleY(var3);
      var1.setVisibility(var4);
   }

   public MenuItemImpl getItemData() {
      return this.itemData;
   }

   public int getItemPosition() {
      return this.itemPosition;
   }

   public void initialize(MenuItemImpl var1, int var2) {
      this.itemData = var1;
      this.setCheckable(var1.isCheckable());
      this.setChecked(var1.isChecked());
      this.setEnabled(var1.isEnabled());
      this.setIcon(var1.getIcon());
      this.setTitle(var1.getTitle());
      this.setId(var1.getItemId());
      if (!TextUtils.isEmpty(var1.getContentDescription())) {
         this.setContentDescription(var1.getContentDescription());
      }

      TooltipCompat.setTooltipText(this, var1.getTooltipText());
      byte var3;
      if (var1.isVisible()) {
         var3 = 0;
      } else {
         var3 = 8;
      }

      this.setVisibility(var3);
   }

   public int[] onCreateDrawableState(int var1) {
      int[] var2 = super.onCreateDrawableState(var1 + 1);
      MenuItemImpl var3 = this.itemData;
      if (var3 != null && var3.isCheckable() && this.itemData.isChecked()) {
         mergeDrawableStates(var2, CHECKED_STATE_SET);
      }

      return var2;
   }

   public boolean prefersCondensedTitle() {
      return false;
   }

   public void setCheckable(boolean var1) {
      this.refreshDrawableState();
   }

   public void setChecked(boolean var1) {
      TextView var4 = this.largeLabel;
      var4.setPivotX((float)(var4.getWidth() / 2));
      var4 = this.largeLabel;
      var4.setPivotY((float)var4.getBaseline());
      var4 = this.smallLabel;
      var4.setPivotX((float)(var4.getWidth() / 2));
      var4 = this.smallLabel;
      var4.setPivotY((float)var4.getBaseline());
      int var3 = this.labelVisibilityMode;
      float var2;
      if (var3 != -1) {
         if (var3 != 0) {
            if (var3 != 1) {
               if (var3 == 2) {
                  this.setViewLayoutParams(this.icon, this.defaultMargin, 17);
                  this.largeLabel.setVisibility(8);
                  this.smallLabel.setVisibility(8);
               }
            } else if (var1) {
               this.setViewLayoutParams(this.icon, (int)((float)this.defaultMargin + this.shiftAmount), 49);
               this.setViewValues(this.largeLabel, 1.0F, 1.0F, 0);
               var4 = this.smallLabel;
               var2 = this.scaleUpFactor;
               this.setViewValues(var4, var2, var2, 4);
            } else {
               this.setViewLayoutParams(this.icon, this.defaultMargin, 49);
               var4 = this.largeLabel;
               var2 = this.scaleDownFactor;
               this.setViewValues(var4, var2, var2, 4);
               this.setViewValues(this.smallLabel, 1.0F, 1.0F, 0);
            }
         } else {
            if (var1) {
               this.setViewLayoutParams(this.icon, this.defaultMargin, 49);
               this.setViewValues(this.largeLabel, 1.0F, 1.0F, 0);
            } else {
               this.setViewLayoutParams(this.icon, this.defaultMargin, 17);
               this.setViewValues(this.largeLabel, 0.5F, 0.5F, 4);
            }

            this.smallLabel.setVisibility(4);
         }
      } else if (this.isShifting) {
         if (var1) {
            this.setViewLayoutParams(this.icon, this.defaultMargin, 49);
            this.setViewValues(this.largeLabel, 1.0F, 1.0F, 0);
         } else {
            this.setViewLayoutParams(this.icon, this.defaultMargin, 17);
            this.setViewValues(this.largeLabel, 0.5F, 0.5F, 4);
         }

         this.smallLabel.setVisibility(4);
      } else if (var1) {
         this.setViewLayoutParams(this.icon, (int)((float)this.defaultMargin + this.shiftAmount), 49);
         this.setViewValues(this.largeLabel, 1.0F, 1.0F, 0);
         var4 = this.smallLabel;
         var2 = this.scaleUpFactor;
         this.setViewValues(var4, var2, var2, 4);
      } else {
         this.setViewLayoutParams(this.icon, this.defaultMargin, 49);
         var4 = this.largeLabel;
         var2 = this.scaleDownFactor;
         this.setViewValues(var4, var2, var2, 4);
         this.setViewValues(this.smallLabel, 1.0F, 1.0F, 0);
      }

      this.refreshDrawableState();
      this.setSelected(var1);
   }

   public void setEnabled(boolean var1) {
      super.setEnabled(var1);
      this.smallLabel.setEnabled(var1);
      this.largeLabel.setEnabled(var1);
      this.icon.setEnabled(var1);
      if (var1) {
         ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(this.getContext(), 1002));
      } else {
         ViewCompat.setPointerIcon(this, (PointerIconCompat)null);
      }

   }

   public void setIcon(Drawable var1) {
      Drawable var2 = var1;
      if (var1 != null) {
         Drawable.ConstantState var3 = var1.getConstantState();
         if (var3 != null) {
            var1 = var3.newDrawable();
         }

         var2 = DrawableCompat.wrap(var1).mutate();
         DrawableCompat.setTintList(var2, this.iconTint);
      }

      this.icon.setImageDrawable(var2);
   }

   public void setIconSize(int var1) {
      FrameLayout.LayoutParams var2 = (FrameLayout.LayoutParams)this.icon.getLayoutParams();
      var2.width = var1;
      var2.height = var1;
      this.icon.setLayoutParams(var2);
   }

   public void setIconTintList(ColorStateList var1) {
      this.iconTint = var1;
      MenuItemImpl var2 = this.itemData;
      if (var2 != null) {
         this.setIcon(var2.getIcon());
      }

   }

   public void setItemBackground(int var1) {
      Drawable var2;
      if (var1 == 0) {
         var2 = null;
      } else {
         var2 = ContextCompat.getDrawable(this.getContext(), var1);
      }

      this.setItemBackground(var2);
   }

   public void setItemBackground(Drawable var1) {
      ViewCompat.setBackground(this, var1);
   }

   public void setItemPosition(int var1) {
      this.itemPosition = var1;
   }

   public void setLabelVisibilityMode(int var1) {
      if (this.labelVisibilityMode != var1) {
         this.labelVisibilityMode = var1;
         MenuItemImpl var2 = this.itemData;
         boolean var3;
         if (var2 != null) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (var3) {
            this.setChecked(var2.isChecked());
         }
      }

   }

   public void setShifting(boolean var1) {
      if (this.isShifting != var1) {
         this.isShifting = var1;
         MenuItemImpl var3 = this.itemData;
         boolean var2;
         if (var3 != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         if (var2) {
            this.setChecked(var3.isChecked());
         }
      }

   }

   public void setShortcut(boolean var1, char var2) {
   }

   public void setTextAppearanceActive(int var1) {
      TextViewCompat.setTextAppearance(this.largeLabel, var1);
      this.calculateTextScaleFactors(this.smallLabel.getTextSize(), this.largeLabel.getTextSize());
   }

   public void setTextAppearanceInactive(int var1) {
      TextViewCompat.setTextAppearance(this.smallLabel, var1);
      this.calculateTextScaleFactors(this.smallLabel.getTextSize(), this.largeLabel.getTextSize());
   }

   public void setTextColor(ColorStateList var1) {
      if (var1 != null) {
         this.smallLabel.setTextColor(var1);
         this.largeLabel.setTextColor(var1);
      }

   }

   public void setTitle(CharSequence var1) {
      this.smallLabel.setText(var1);
      this.largeLabel.setText(var1);
      MenuItemImpl var2 = this.itemData;
      if (var2 == null || TextUtils.isEmpty(var2.getContentDescription())) {
         this.setContentDescription(var1);
      }

   }

   public boolean showsIcon() {
      return true;
   }
}
