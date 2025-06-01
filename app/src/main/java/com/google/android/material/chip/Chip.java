package com.google.android.material.chip;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.text.BidiFormatter;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.android.material.R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.ripple.RippleUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Chip extends AppCompatCheckBox implements ChipDrawable.Delegate {
   private static final int CLOSE_ICON_VIRTUAL_ID = 0;
   private static final Rect EMPTY_BOUNDS = new Rect();
   private static final String NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android";
   private static final int[] SELECTED_STATE = new int[]{16842913};
   private static final String TAG = "Chip";
   private ChipDrawable chipDrawable;
   private boolean closeIconFocused;
   private boolean closeIconHovered;
   private boolean closeIconPressed;
   private boolean deferredCheckedValue;
   private int focusedVirtualView;
   private final ResourcesCompat.FontCallback fontCallback;
   private CompoundButton.OnCheckedChangeListener onCheckedChangeListenerInternal;
   private View.OnClickListener onCloseIconClickListener;
   private final Rect rect;
   private final RectF rectF;
   private RippleDrawable ripple;
   private final ChipTouchHelper touchHelper;

   public Chip(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public Chip(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.chipStyle);
   }

   public Chip(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.focusedVirtualView = Integer.MIN_VALUE;
      this.rect = new Rect();
      this.rectF = new RectF();
      this.fontCallback = new ResourcesCompat.FontCallback(this) {
         final Chip this$0;

         {
            this.this$0 = var1;
         }

         public void onFontRetrievalFailed(int var1) {
         }

         public void onFontRetrieved(Typeface var1) {
            Chip var2 = this.this$0;
            var2.setText(var2.getText());
            this.this$0.requestLayout();
            this.this$0.invalidate();
         }
      };
      this.validateAttributes(var2);
      ChipDrawable var5 = ChipDrawable.createFromAttributes(var1, var2, var3, R.style.Widget_MaterialComponents_Chip_Action);
      this.setChipDrawable(var5);
      ChipTouchHelper var4 = new ChipTouchHelper(this, this);
      this.touchHelper = var4;
      ViewCompat.setAccessibilityDelegate(this, var4);
      this.initOutlineProvider();
      this.setChecked(this.deferredCheckedValue);
      var5.setShouldDrawText(false);
      this.setText(var5.getText());
      this.setEllipsize(var5.getEllipsize());
      this.setIncludeFontPadding(false);
      if (this.getTextAppearance() != null) {
         this.updateTextPaintDrawState(this.getTextAppearance());
      }

      this.setSingleLine();
      this.setGravity(8388627);
      this.updatePaddingInternal();
   }

   private void applyChipDrawable(ChipDrawable var1) {
      var1.setDelegate(this);
   }

   private float calculateTextOffsetFromStart(ChipDrawable var1) {
      float var2 = this.getChipStartPadding() + var1.calculateChipIconWidth() + this.getTextStartPadding();
      return ViewCompat.getLayoutDirection(this) == 0 ? var2 : -var2;
   }

   private int[] createCloseIconDrawableState() {
      int var2 = this.isEnabled();
      int var1 = var2;
      if (this.closeIconFocused) {
         var1 = var2 + 1;
      }

      var2 = var1;
      if (this.closeIconHovered) {
         var2 = var1 + 1;
      }

      var1 = var2;
      if (this.closeIconPressed) {
         var1 = var2 + 1;
      }

      var2 = var1;
      if (this.isChecked()) {
         var2 = var1 + 1;
      }

      int[] var3 = new int[var2];
      byte var4 = 0;
      if (this.isEnabled()) {
         var3[0] = 16842910;
         var4 = 1;
      }

      var1 = var4;
      if (this.closeIconFocused) {
         var3[var4] = 16842908;
         var1 = var4 + 1;
      }

      var2 = var1;
      if (this.closeIconHovered) {
         var3[var1] = 16843623;
         var2 = var1 + 1;
      }

      var1 = var2;
      if (this.closeIconPressed) {
         var3[var2] = 16842919;
         var1 = var2 + 1;
      }

      if (this.isChecked()) {
         var3[var1] = 16842913;
      }

      return var3;
   }

   private void ensureFocus() {
      if (this.focusedVirtualView == Integer.MIN_VALUE) {
         this.setFocusedVirtualView(-1);
      }

   }

   private RectF getCloseIconTouchBounds() {
      this.rectF.setEmpty();
      if (this.hasCloseIcon()) {
         this.chipDrawable.getCloseIconTouchBounds(this.rectF);
      }

      return this.rectF;
   }

   private Rect getCloseIconTouchBoundsInt() {
      RectF var1 = this.getCloseIconTouchBounds();
      this.rect.set((int)var1.left, (int)var1.top, (int)var1.right, (int)var1.bottom);
      return this.rect;
   }

   private TextAppearance getTextAppearance() {
      ChipDrawable var1 = this.chipDrawable;
      TextAppearance var2;
      if (var1 != null) {
         var2 = var1.getTextAppearance();
      } else {
         var2 = null;
      }

      return var2;
   }

   private boolean handleAccessibilityExit(MotionEvent var1) {
      if (var1.getAction() == 10) {
         try {
            Field var6 = ExploreByTouchHelper.class.getDeclaredField("mHoveredVirtualViewId");
            var6.setAccessible(true);
            if ((Integer)var6.get(this.touchHelper) != Integer.MIN_VALUE) {
               Method var7 = ExploreByTouchHelper.class.getDeclaredMethod("updateHoveredVirtualView", Integer.TYPE);
               var7.setAccessible(true);
               var7.invoke(this.touchHelper, Integer.MIN_VALUE);
               return true;
            }
         } catch (NoSuchMethodException var2) {
            Log.e("Chip", "Unable to send Accessibility Exit event", var2);
         } catch (IllegalAccessException var3) {
            Log.e("Chip", "Unable to send Accessibility Exit event", var3);
         } catch (InvocationTargetException var4) {
            Log.e("Chip", "Unable to send Accessibility Exit event", var4);
         } catch (NoSuchFieldException var5) {
            Log.e("Chip", "Unable to send Accessibility Exit event", var5);
         }
      }

      return false;
   }

   private boolean hasCloseIcon() {
      ChipDrawable var2 = this.chipDrawable;
      boolean var1;
      if (var2 != null && var2.getCloseIcon() != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private void initOutlineProvider() {
      if (VERSION.SDK_INT >= 21) {
         this.setOutlineProvider(new ViewOutlineProvider(this) {
            final Chip this$0;

            {
               this.this$0 = var1;
            }

            public void getOutline(View var1, Outline var2) {
               if (this.this$0.chipDrawable != null) {
                  this.this$0.chipDrawable.getOutline(var2);
               } else {
                  var2.setAlpha(0.0F);
               }

            }
         });
      }

   }

   private boolean moveFocus(boolean var1) {
      this.ensureFocus();
      boolean var2 = true;
      if (var1) {
         if (this.focusedVirtualView == -1) {
            this.setFocusedVirtualView(0);
            var1 = var2;
            return var1;
         }
      } else if (this.focusedVirtualView == 0) {
         this.setFocusedVirtualView(-1);
         var1 = var2;
         return var1;
      }

      var1 = false;
      return var1;
   }

   private void setCloseIconFocused(boolean var1) {
      if (this.closeIconFocused != var1) {
         this.closeIconFocused = var1;
         this.refreshDrawableState();
      }

   }

   private void setCloseIconHovered(boolean var1) {
      if (this.closeIconHovered != var1) {
         this.closeIconHovered = var1;
         this.refreshDrawableState();
      }

   }

   private void setCloseIconPressed(boolean var1) {
      if (this.closeIconPressed != var1) {
         this.closeIconPressed = var1;
         this.refreshDrawableState();
      }

   }

   private void setFocusedVirtualView(int var1) {
      int var2 = this.focusedVirtualView;
      if (var2 != var1) {
         if (var2 == 0) {
            this.setCloseIconFocused(false);
         }

         this.focusedVirtualView = var1;
         if (var1 == 0) {
            this.setCloseIconFocused(true);
         }
      }

   }

   private void unapplyChipDrawable(ChipDrawable var1) {
      if (var1 != null) {
         var1.setDelegate((ChipDrawable.Delegate)null);
      }

   }

   private void updatePaddingInternal() {
      if (!TextUtils.isEmpty(this.getText())) {
         ChipDrawable var3 = this.chipDrawable;
         if (var3 != null) {
            float var1;
            float var2;
            label41: {
               var2 = var3.getChipStartPadding() + this.chipDrawable.getChipEndPadding() + this.chipDrawable.getTextStartPadding() + this.chipDrawable.getTextEndPadding();
               if (!this.chipDrawable.isChipIconVisible() || this.chipDrawable.getChipIcon() == null) {
                  var1 = var2;
                  if (this.chipDrawable.getCheckedIcon() == null) {
                     break label41;
                  }

                  var1 = var2;
                  if (!this.chipDrawable.isCheckedIconVisible()) {
                     break label41;
                  }

                  var1 = var2;
                  if (!this.isChecked()) {
                     break label41;
                  }
               }

               var1 = var2 + this.chipDrawable.getIconStartPadding() + this.chipDrawable.getIconEndPadding() + this.chipDrawable.getChipIconSize();
            }

            var2 = var1;
            if (this.chipDrawable.isCloseIconVisible()) {
               var2 = var1;
               if (this.chipDrawable.getCloseIcon() != null) {
                  var2 = var1 + this.chipDrawable.getCloseIconStartPadding() + this.chipDrawable.getCloseIconEndPadding() + this.chipDrawable.getCloseIconSize();
               }
            }

            if ((float)ViewCompat.getPaddingEnd(this) != var2) {
               ViewCompat.setPaddingRelative(this, ViewCompat.getPaddingStart(this), this.getPaddingTop(), (int)var2, this.getPaddingBottom());
            }
         }
      }

   }

   private void updateTextPaintDrawState(TextAppearance var1) {
      TextPaint var2 = this.getPaint();
      var2.drawableState = this.chipDrawable.getState();
      var1.updateDrawState(this.getContext(), var2, this.fontCallback);
   }

   private void validateAttributes(AttributeSet var1) {
      if (var1 != null) {
         if (var1.getAttributeValue("http://schemas.android.com/apk/res/android", "background") == null) {
            if (var1.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableLeft") == null) {
               if (var1.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableStart") == null) {
                  if (var1.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableEnd") == null) {
                     if (var1.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableRight") == null) {
                        if (var1.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "singleLine", true) && var1.getAttributeIntValue("http://schemas.android.com/apk/res/android", "lines", 1) == 1 && var1.getAttributeIntValue("http://schemas.android.com/apk/res/android", "minLines", 1) == 1 && var1.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLines", 1) == 1) {
                           if (var1.getAttributeIntValue("http://schemas.android.com/apk/res/android", "gravity", 8388627) != 8388627) {
                              Log.w("Chip", "Chip text must be vertically center and start aligned");
                           }

                        } else {
                           throw new UnsupportedOperationException("Chip does not support multi-line text");
                        }
                     } else {
                        throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
                     }
                  } else {
                     throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
                  }
               } else {
                  throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
               }
            } else {
               throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
            }
         } else {
            throw new UnsupportedOperationException("Do not set the background; Chip manages its own background drawable.");
         }
      }
   }

   protected boolean dispatchHoverEvent(MotionEvent var1) {
      boolean var2;
      if (!this.handleAccessibilityExit(var1) && !this.touchHelper.dispatchHoverEvent(var1) && !super.dispatchHoverEvent(var1)) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   public boolean dispatchKeyEvent(KeyEvent var1) {
      boolean var2;
      if (!this.touchHelper.dispatchKeyEvent(var1) && !super.dispatchKeyEvent(var1)) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   protected void drawableStateChanged() {
      super.drawableStateChanged();
      ChipDrawable var2 = this.chipDrawable;
      boolean var1;
      if (var2 != null && var2.isCloseIconStateful()) {
         var1 = this.chipDrawable.setCloseIconState(this.createCloseIconDrawableState());
      } else {
         var1 = false;
      }

      if (var1) {
         this.invalidate();
      }

   }

   public Drawable getCheckedIcon() {
      ChipDrawable var1 = this.chipDrawable;
      Drawable var2;
      if (var1 != null) {
         var2 = var1.getCheckedIcon();
      } else {
         var2 = null;
      }

      return var2;
   }

   public ColorStateList getChipBackgroundColor() {
      ChipDrawable var1 = this.chipDrawable;
      ColorStateList var2;
      if (var1 != null) {
         var2 = var1.getChipBackgroundColor();
      } else {
         var2 = null;
      }

      return var2;
   }

   public float getChipCornerRadius() {
      ChipDrawable var2 = this.chipDrawable;
      float var1;
      if (var2 != null) {
         var1 = var2.getChipCornerRadius();
      } else {
         var1 = 0.0F;
      }

      return var1;
   }

   public Drawable getChipDrawable() {
      return this.chipDrawable;
   }

   public float getChipEndPadding() {
      ChipDrawable var2 = this.chipDrawable;
      float var1;
      if (var2 != null) {
         var1 = var2.getChipEndPadding();
      } else {
         var1 = 0.0F;
      }

      return var1;
   }

   public Drawable getChipIcon() {
      ChipDrawable var1 = this.chipDrawable;
      Drawable var2;
      if (var1 != null) {
         var2 = var1.getChipIcon();
      } else {
         var2 = null;
      }

      return var2;
   }

   public float getChipIconSize() {
      ChipDrawable var2 = this.chipDrawable;
      float var1;
      if (var2 != null) {
         var1 = var2.getChipIconSize();
      } else {
         var1 = 0.0F;
      }

      return var1;
   }

   public ColorStateList getChipIconTint() {
      ChipDrawable var1 = this.chipDrawable;
      ColorStateList var2;
      if (var1 != null) {
         var2 = var1.getChipIconTint();
      } else {
         var2 = null;
      }

      return var2;
   }

   public float getChipMinHeight() {
      ChipDrawable var2 = this.chipDrawable;
      float var1;
      if (var2 != null) {
         var1 = var2.getChipMinHeight();
      } else {
         var1 = 0.0F;
      }

      return var1;
   }

   public float getChipStartPadding() {
      ChipDrawable var2 = this.chipDrawable;
      float var1;
      if (var2 != null) {
         var1 = var2.getChipStartPadding();
      } else {
         var1 = 0.0F;
      }

      return var1;
   }

   public ColorStateList getChipStrokeColor() {
      ChipDrawable var1 = this.chipDrawable;
      ColorStateList var2;
      if (var1 != null) {
         var2 = var1.getChipStrokeColor();
      } else {
         var2 = null;
      }

      return var2;
   }

   public float getChipStrokeWidth() {
      ChipDrawable var2 = this.chipDrawable;
      float var1;
      if (var2 != null) {
         var1 = var2.getChipStrokeWidth();
      } else {
         var1 = 0.0F;
      }

      return var1;
   }

   @Deprecated
   public CharSequence getChipText() {
      return this.getText();
   }

   public Drawable getCloseIcon() {
      ChipDrawable var1 = this.chipDrawable;
      Drawable var2;
      if (var1 != null) {
         var2 = var1.getCloseIcon();
      } else {
         var2 = null;
      }

      return var2;
   }

   public CharSequence getCloseIconContentDescription() {
      ChipDrawable var1 = this.chipDrawable;
      CharSequence var2;
      if (var1 != null) {
         var2 = var1.getCloseIconContentDescription();
      } else {
         var2 = null;
      }

      return var2;
   }

   public float getCloseIconEndPadding() {
      ChipDrawable var2 = this.chipDrawable;
      float var1;
      if (var2 != null) {
         var1 = var2.getCloseIconEndPadding();
      } else {
         var1 = 0.0F;
      }

      return var1;
   }

   public float getCloseIconSize() {
      ChipDrawable var2 = this.chipDrawable;
      float var1;
      if (var2 != null) {
         var1 = var2.getCloseIconSize();
      } else {
         var1 = 0.0F;
      }

      return var1;
   }

   public float getCloseIconStartPadding() {
      ChipDrawable var2 = this.chipDrawable;
      float var1;
      if (var2 != null) {
         var1 = var2.getCloseIconStartPadding();
      } else {
         var1 = 0.0F;
      }

      return var1;
   }

   public ColorStateList getCloseIconTint() {
      ChipDrawable var1 = this.chipDrawable;
      ColorStateList var2;
      if (var1 != null) {
         var2 = var1.getCloseIconTint();
      } else {
         var2 = null;
      }

      return var2;
   }

   public TextUtils.TruncateAt getEllipsize() {
      ChipDrawable var1 = this.chipDrawable;
      TextUtils.TruncateAt var2;
      if (var1 != null) {
         var2 = var1.getEllipsize();
      } else {
         var2 = null;
      }

      return var2;
   }

   public void getFocusedRect(Rect var1) {
      if (this.focusedVirtualView == 0) {
         var1.set(this.getCloseIconTouchBoundsInt());
      } else {
         super.getFocusedRect(var1);
      }

   }

   public MotionSpec getHideMotionSpec() {
      ChipDrawable var1 = this.chipDrawable;
      MotionSpec var2;
      if (var1 != null) {
         var2 = var1.getHideMotionSpec();
      } else {
         var2 = null;
      }

      return var2;
   }

   public float getIconEndPadding() {
      ChipDrawable var2 = this.chipDrawable;
      float var1;
      if (var2 != null) {
         var1 = var2.getIconEndPadding();
      } else {
         var1 = 0.0F;
      }

      return var1;
   }

   public float getIconStartPadding() {
      ChipDrawable var2 = this.chipDrawable;
      float var1;
      if (var2 != null) {
         var1 = var2.getIconStartPadding();
      } else {
         var1 = 0.0F;
      }

      return var1;
   }

   public ColorStateList getRippleColor() {
      ChipDrawable var1 = this.chipDrawable;
      ColorStateList var2;
      if (var1 != null) {
         var2 = var1.getRippleColor();
      } else {
         var2 = null;
      }

      return var2;
   }

   public MotionSpec getShowMotionSpec() {
      ChipDrawable var1 = this.chipDrawable;
      MotionSpec var2;
      if (var1 != null) {
         var2 = var1.getShowMotionSpec();
      } else {
         var2 = null;
      }

      return var2;
   }

   public CharSequence getText() {
      ChipDrawable var1 = this.chipDrawable;
      Object var2;
      if (var1 != null) {
         var2 = var1.getText();
      } else {
         var2 = "";
      }

      return (CharSequence)var2;
   }

   public float getTextEndPadding() {
      ChipDrawable var2 = this.chipDrawable;
      float var1;
      if (var2 != null) {
         var1 = var2.getTextEndPadding();
      } else {
         var1 = 0.0F;
      }

      return var1;
   }

   public float getTextStartPadding() {
      ChipDrawable var2 = this.chipDrawable;
      float var1;
      if (var2 != null) {
         var1 = var2.getTextStartPadding();
      } else {
         var1 = 0.0F;
      }

      return var1;
   }

   public boolean isCheckable() {
      ChipDrawable var2 = this.chipDrawable;
      boolean var1;
      if (var2 != null && var2.isCheckable()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   @Deprecated
   public boolean isCheckedIconEnabled() {
      return this.isCheckedIconVisible();
   }

   public boolean isCheckedIconVisible() {
      ChipDrawable var2 = this.chipDrawable;
      boolean var1;
      if (var2 != null && var2.isCheckedIconVisible()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   @Deprecated
   public boolean isChipIconEnabled() {
      return this.isChipIconVisible();
   }

   public boolean isChipIconVisible() {
      ChipDrawable var2 = this.chipDrawable;
      boolean var1;
      if (var2 != null && var2.isChipIconVisible()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   @Deprecated
   public boolean isCloseIconEnabled() {
      return this.isCloseIconVisible();
   }

   public boolean isCloseIconVisible() {
      ChipDrawable var2 = this.chipDrawable;
      boolean var1;
      if (var2 != null && var2.isCloseIconVisible()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void onChipDrawableSizeChange() {
      this.updatePaddingInternal();
      this.requestLayout();
      if (VERSION.SDK_INT >= 21) {
         this.invalidateOutline();
      }

   }

   protected int[] onCreateDrawableState(int var1) {
      int[] var2 = super.onCreateDrawableState(var1 + 1);
      if (this.isChecked()) {
         mergeDrawableStates(var2, SELECTED_STATE);
      }

      return var2;
   }

   protected void onDraw(Canvas var1) {
      if (!TextUtils.isEmpty(this.getText())) {
         ChipDrawable var3 = this.chipDrawable;
         if (var3 != null && !var3.shouldDrawText()) {
            int var2 = var1.save();
            var1.translate(this.calculateTextOffsetFromStart(this.chipDrawable), 0.0F);
            super.onDraw(var1);
            var1.restoreToCount(var2);
            return;
         }
      }

      super.onDraw(var1);
   }

   protected void onFocusChanged(boolean var1, int var2, Rect var3) {
      if (var1) {
         this.setFocusedVirtualView(-1);
      } else {
         this.setFocusedVirtualView(Integer.MIN_VALUE);
      }

      this.invalidate();
      super.onFocusChanged(var1, var2, var3);
      this.touchHelper.onFocusChanged(var1, var2, var3);
   }

   public boolean onHoverEvent(MotionEvent var1) {
      int var2 = var1.getActionMasked();
      if (var2 != 7) {
         if (var2 == 10) {
            this.setCloseIconHovered(false);
         }
      } else {
         this.setCloseIconHovered(this.getCloseIconTouchBounds().contains(var1.getX(), var1.getY()));
      }

      return super.onHoverEvent(var1);
   }

   public boolean onKeyDown(int var1, KeyEvent var2) {
      int var3 = var2.getKeyCode();
      boolean var5 = false;
      boolean var4;
      if (var3 != 61) {
         label63: {
            if (var3 != 66) {
               switch (var3) {
                  case 21:
                     var4 = var5;
                     if (var2.hasNoModifiers()) {
                        var4 = this.moveFocus(ViewUtils.isLayoutRtl(this));
                     }
                     break label63;
                  case 22:
                     var4 = var5;
                     if (var2.hasNoModifiers()) {
                        var4 = this.moveFocus(ViewUtils.isLayoutRtl(this) ^ true);
                     }
                     break label63;
                  case 23:
                     break;
                  default:
                     var4 = var5;
                     break label63;
               }
            }

            var3 = this.focusedVirtualView;
            if (var3 == -1) {
               this.performClick();
               return true;
            }

            if (var3 == 0) {
               this.performCloseIconClick();
               return true;
            }

            var4 = var5;
         }
      } else {
         byte var9;
         if (var2.hasNoModifiers()) {
            var9 = 2;
         } else if (var2.hasModifiers(1)) {
            var9 = 1;
         } else {
            var9 = 0;
         }

         var4 = var5;
         if (var9 != 0) {
            ViewParent var8 = this.getParent();
            Object var6 = this;

            View var7;
            do {
               var7 = ((View)var6).focusSearch(var9);
               if (var7 == null || var7 == this) {
                  break;
               }

               var6 = var7;
            } while(var7.getParent() == var8);

            var4 = var5;
            if (var7 != null) {
               var7.requestFocus();
               return true;
            }
         }
      }

      if (var4) {
         this.invalidate();
         return true;
      } else {
         return super.onKeyDown(var1, var2);
      }
   }

   public PointerIcon onResolvePointerIcon(MotionEvent var1, int var2) {
      return this.getCloseIconTouchBounds().contains(var1.getX(), var1.getY()) && this.isEnabled() ? PointerIcon.getSystemIcon(this.getContext(), 1002) : null;
   }

   public boolean onTouchEvent(MotionEvent var1) {
      boolean var3;
      boolean var5;
      label48: {
         label47: {
            label53: {
               int var2 = var1.getActionMasked();
               boolean var4 = this.getCloseIconTouchBounds().contains(var1.getX(), var1.getY());
               var3 = false;
               if (var2 != 0) {
                  label43: {
                     if (var2 != 1) {
                        if (var2 == 2) {
                           if (!this.closeIconPressed) {
                              break label47;
                           }

                           if (!var4) {
                              this.setCloseIconPressed(false);
                           }
                           break label43;
                        }

                        if (var2 != 3) {
                           break label47;
                        }
                     } else if (this.closeIconPressed) {
                        this.performCloseIconClick();
                        var5 = true;
                        break label53;
                     }

                     var5 = false;
                     break label53;
                  }
               } else {
                  if (!var4) {
                     break label47;
                  }

                  this.setCloseIconPressed(true);
               }

               var5 = true;
               break label48;
            }

            this.setCloseIconPressed(false);
            break label48;
         }

         var5 = false;
      }

      if (var5 || super.onTouchEvent(var1)) {
         var3 = true;
      }

      return var3;
   }

   public boolean performCloseIconClick() {
      this.playSoundEffect(0);
      View.OnClickListener var2 = this.onCloseIconClickListener;
      boolean var1;
      if (var2 != null) {
         var2.onClick(this);
         var1 = true;
      } else {
         var1 = false;
      }

      this.touchHelper.sendEventForVirtualView(0, 1);
      return var1;
   }

   public void setBackground(Drawable var1) {
      if (var1 != this.chipDrawable && var1 != this.ripple) {
         throw new UnsupportedOperationException("Do not set the background; Chip manages its own background drawable.");
      } else {
         super.setBackground(var1);
      }
   }

   public void setBackgroundColor(int var1) {
      throw new UnsupportedOperationException("Do not set the background color; Chip manages its own background drawable.");
   }

   public void setBackgroundDrawable(Drawable var1) {
      if (var1 != this.chipDrawable && var1 != this.ripple) {
         throw new UnsupportedOperationException("Do not set the background drawable; Chip manages its own background drawable.");
      } else {
         super.setBackgroundDrawable(var1);
      }
   }

   public void setBackgroundResource(int var1) {
      throw new UnsupportedOperationException("Do not set the background resource; Chip manages its own background drawable.");
   }

   public void setBackgroundTintList(ColorStateList var1) {
      throw new UnsupportedOperationException("Do not set the background tint list; Chip manages its own background drawable.");
   }

   public void setBackgroundTintMode(PorterDuff.Mode var1) {
      throw new UnsupportedOperationException("Do not set the background tint mode; Chip manages its own background drawable.");
   }

   public void setCheckable(boolean var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCheckable(var1);
      }

   }

   public void setCheckableResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCheckableResource(var1);
      }

   }

   public void setChecked(boolean var1) {
      ChipDrawable var3 = this.chipDrawable;
      if (var3 == null) {
         this.deferredCheckedValue = var1;
      } else if (var3.isCheckable()) {
         boolean var2 = this.isChecked();
         super.setChecked(var1);
         if (var2 != var1) {
            CompoundButton.OnCheckedChangeListener var4 = this.onCheckedChangeListenerInternal;
            if (var4 != null) {
               var4.onCheckedChanged(this, var1);
            }
         }
      }

   }

   public void setCheckedIcon(Drawable var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCheckedIcon(var1);
      }

   }

   @Deprecated
   public void setCheckedIconEnabled(boolean var1) {
      this.setCheckedIconVisible(var1);
   }

   @Deprecated
   public void setCheckedIconEnabledResource(int var1) {
      this.setCheckedIconVisible(var1);
   }

   public void setCheckedIconResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCheckedIconResource(var1);
      }

   }

   public void setCheckedIconVisible(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCheckedIconVisible(var1);
      }

   }

   public void setCheckedIconVisible(boolean var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCheckedIconVisible(var1);
      }

   }

   public void setChipBackgroundColor(ColorStateList var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipBackgroundColor(var1);
      }

   }

   public void setChipBackgroundColorResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipBackgroundColorResource(var1);
      }

   }

   public void setChipCornerRadius(float var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipCornerRadius(var1);
      }

   }

   public void setChipCornerRadiusResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipCornerRadiusResource(var1);
      }

   }

   public void setChipDrawable(ChipDrawable var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != var1) {
         this.unapplyChipDrawable(var2);
         this.chipDrawable = var1;
         this.applyChipDrawable(var1);
         if (RippleUtils.USE_FRAMEWORK_RIPPLE) {
            this.ripple = new RippleDrawable(RippleUtils.convertToRippleDrawableColor(this.chipDrawable.getRippleColor()), this.chipDrawable, (Drawable)null);
            this.chipDrawable.setUseCompatRipple(false);
            ViewCompat.setBackground(this, this.ripple);
         } else {
            this.chipDrawable.setUseCompatRipple(true);
            ViewCompat.setBackground(this, this.chipDrawable);
         }
      }

   }

   public void setChipEndPadding(float var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipEndPadding(var1);
      }

   }

   public void setChipEndPaddingResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipEndPaddingResource(var1);
      }

   }

   public void setChipIcon(Drawable var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipIcon(var1);
      }

   }

   @Deprecated
   public void setChipIconEnabled(boolean var1) {
      this.setChipIconVisible(var1);
   }

   @Deprecated
   public void setChipIconEnabledResource(int var1) {
      this.setChipIconVisible(var1);
   }

   public void setChipIconResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipIconResource(var1);
      }

   }

   public void setChipIconSize(float var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipIconSize(var1);
      }

   }

   public void setChipIconSizeResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipIconSizeResource(var1);
      }

   }

   public void setChipIconTint(ColorStateList var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipIconTint(var1);
      }

   }

   public void setChipIconTintResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipIconTintResource(var1);
      }

   }

   public void setChipIconVisible(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipIconVisible(var1);
      }

   }

   public void setChipIconVisible(boolean var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipIconVisible(var1);
      }

   }

   public void setChipMinHeight(float var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipMinHeight(var1);
      }

   }

   public void setChipMinHeightResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipMinHeightResource(var1);
      }

   }

   public void setChipStartPadding(float var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipStartPadding(var1);
      }

   }

   public void setChipStartPaddingResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipStartPaddingResource(var1);
      }

   }

   public void setChipStrokeColor(ColorStateList var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipStrokeColor(var1);
      }

   }

   public void setChipStrokeColorResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipStrokeColorResource(var1);
      }

   }

   public void setChipStrokeWidth(float var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipStrokeWidth(var1);
      }

   }

   public void setChipStrokeWidthResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setChipStrokeWidthResource(var1);
      }

   }

   @Deprecated
   public void setChipText(CharSequence var1) {
      this.setText(var1);
   }

   @Deprecated
   public void setChipTextResource(int var1) {
      this.setText(this.getResources().getString(var1));
   }

   public void setCloseIcon(Drawable var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCloseIcon(var1);
      }

   }

   public void setCloseIconContentDescription(CharSequence var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCloseIconContentDescription(var1);
      }

   }

   @Deprecated
   public void setCloseIconEnabled(boolean var1) {
      this.setCloseIconVisible(var1);
   }

   @Deprecated
   public void setCloseIconEnabledResource(int var1) {
      this.setCloseIconVisible(var1);
   }

   public void setCloseIconEndPadding(float var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCloseIconEndPadding(var1);
      }

   }

   public void setCloseIconEndPaddingResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCloseIconEndPaddingResource(var1);
      }

   }

   public void setCloseIconResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCloseIconResource(var1);
      }

   }

   public void setCloseIconSize(float var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCloseIconSize(var1);
      }

   }

   public void setCloseIconSizeResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCloseIconSizeResource(var1);
      }

   }

   public void setCloseIconStartPadding(float var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCloseIconStartPadding(var1);
      }

   }

   public void setCloseIconStartPaddingResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCloseIconStartPaddingResource(var1);
      }

   }

   public void setCloseIconTint(ColorStateList var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCloseIconTint(var1);
      }

   }

   public void setCloseIconTintResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCloseIconTintResource(var1);
      }

   }

   public void setCloseIconVisible(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCloseIconVisible(var1);
      }

   }

   public void setCloseIconVisible(boolean var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setCloseIconVisible(var1);
      }

   }

   public void setCompoundDrawables(Drawable var1, Drawable var2, Drawable var3, Drawable var4) {
      if (var1 == null) {
         if (var3 == null) {
            super.setCompoundDrawables(var1, var2, var3, var4);
         } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
         }
      } else {
         throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
      }
   }

   public void setCompoundDrawablesRelative(Drawable var1, Drawable var2, Drawable var3, Drawable var4) {
      if (var1 == null) {
         if (var3 == null) {
            super.setCompoundDrawablesRelative(var1, var2, var3, var4);
         } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
         }
      } else {
         throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
      }
   }

   public void setCompoundDrawablesRelativeWithIntrinsicBounds(int var1, int var2, int var3, int var4) {
      if (var1 == 0) {
         if (var3 == 0) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(var1, var2, var3, var4);
         } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
         }
      } else {
         throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
      }
   }

   public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable var1, Drawable var2, Drawable var3, Drawable var4) {
      if (var1 == null) {
         if (var3 == null) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(var1, var2, var3, var4);
         } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
         }
      } else {
         throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
      }
   }

   public void setCompoundDrawablesWithIntrinsicBounds(int var1, int var2, int var3, int var4) {
      if (var1 == 0) {
         if (var3 == 0) {
            super.setCompoundDrawablesWithIntrinsicBounds(var1, var2, var3, var4);
         } else {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
         }
      } else {
         throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
      }
   }

   public void setCompoundDrawablesWithIntrinsicBounds(Drawable var1, Drawable var2, Drawable var3, Drawable var4) {
      if (var1 == null) {
         if (var3 == null) {
            super.setCompoundDrawablesWithIntrinsicBounds(var1, var2, var3, var4);
         } else {
            throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
         }
      } else {
         throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
      }
   }

   public void setEllipsize(TextUtils.TruncateAt var1) {
      if (this.chipDrawable != null) {
         if (var1 != TruncateAt.MARQUEE) {
            super.setEllipsize(var1);
            ChipDrawable var2 = this.chipDrawable;
            if (var2 != null) {
               var2.setEllipsize(var1);
            }

         } else {
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
         }
      }
   }

   public void setGravity(int var1) {
      if (var1 != 8388627) {
         Log.w("Chip", "Chip text must be vertically center and start aligned");
      } else {
         super.setGravity(var1);
      }

   }

   public void setHideMotionSpec(MotionSpec var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setHideMotionSpec(var1);
      }

   }

   public void setHideMotionSpecResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setHideMotionSpecResource(var1);
      }

   }

   public void setIconEndPadding(float var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setIconEndPadding(var1);
      }

   }

   public void setIconEndPaddingResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setIconEndPaddingResource(var1);
      }

   }

   public void setIconStartPadding(float var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setIconStartPadding(var1);
      }

   }

   public void setIconStartPaddingResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setIconStartPaddingResource(var1);
      }

   }

   public void setLines(int var1) {
      if (var1 <= 1) {
         super.setLines(var1);
      } else {
         throw new UnsupportedOperationException("Chip does not support multi-line text");
      }
   }

   public void setMaxLines(int var1) {
      if (var1 <= 1) {
         super.setMaxLines(var1);
      } else {
         throw new UnsupportedOperationException("Chip does not support multi-line text");
      }
   }

   public void setMaxWidth(int var1) {
      super.setMaxWidth(var1);
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setMaxWidth(var1);
      }

   }

   public void setMinLines(int var1) {
      if (var1 <= 1) {
         super.setMinLines(var1);
      } else {
         throw new UnsupportedOperationException("Chip does not support multi-line text");
      }
   }

   void setOnCheckedChangeListenerInternal(CompoundButton.OnCheckedChangeListener var1) {
      this.onCheckedChangeListenerInternal = var1;
   }

   public void setOnCloseIconClickListener(View.OnClickListener var1) {
      this.onCloseIconClickListener = var1;
   }

   public void setRippleColor(ColorStateList var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setRippleColor(var1);
      }

   }

   public void setRippleColorResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setRippleColorResource(var1);
      }

   }

   public void setShowMotionSpec(MotionSpec var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setShowMotionSpec(var1);
      }

   }

   public void setShowMotionSpecResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setShowMotionSpecResource(var1);
      }

   }

   public void setSingleLine(boolean var1) {
      if (var1) {
         super.setSingleLine(var1);
      } else {
         throw new UnsupportedOperationException("Chip does not support multi-line text");
      }
   }

   public void setText(CharSequence var1, TextView.BufferType var2) {
      if (this.chipDrawable != null) {
         Object var3 = var1;
         if (var1 == null) {
            var3 = "";
         }

         var1 = BidiFormatter.getInstance().unicodeWrap((CharSequence)var3);
         if (this.chipDrawable.shouldDrawText()) {
            var1 = null;
         }

         super.setText(var1, var2);
         ChipDrawable var4 = this.chipDrawable;
         if (var4 != null) {
            var4.setText((CharSequence)var3);
         }

      }
   }

   public void setTextAppearance(int var1) {
      super.setTextAppearance(var1);
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setTextAppearanceResource(var1);
      }

      if (this.getTextAppearance() != null) {
         this.getTextAppearance().updateMeasureState(this.getContext(), this.getPaint(), this.fontCallback);
         this.updateTextPaintDrawState(this.getTextAppearance());
      }

   }

   public void setTextAppearance(Context var1, int var2) {
      super.setTextAppearance(var1, var2);
      ChipDrawable var3 = this.chipDrawable;
      if (var3 != null) {
         var3.setTextAppearanceResource(var2);
      }

      if (this.getTextAppearance() != null) {
         this.getTextAppearance().updateMeasureState(var1, this.getPaint(), this.fontCallback);
         this.updateTextPaintDrawState(this.getTextAppearance());
      }

   }

   public void setTextAppearance(TextAppearance var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setTextAppearance(var1);
      }

      if (this.getTextAppearance() != null) {
         this.getTextAppearance().updateMeasureState(this.getContext(), this.getPaint(), this.fontCallback);
         this.updateTextPaintDrawState(var1);
      }

   }

   public void setTextAppearanceResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setTextAppearanceResource(var1);
      }

      this.setTextAppearance(this.getContext(), var1);
   }

   public void setTextEndPadding(float var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setTextEndPadding(var1);
      }

   }

   public void setTextEndPaddingResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setTextEndPaddingResource(var1);
      }

   }

   public void setTextStartPadding(float var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setTextStartPadding(var1);
      }

   }

   public void setTextStartPaddingResource(int var1) {
      ChipDrawable var2 = this.chipDrawable;
      if (var2 != null) {
         var2.setTextStartPaddingResource(var1);
      }

   }

   private class ChipTouchHelper extends ExploreByTouchHelper {
      final Chip this$0;

      ChipTouchHelper(Chip var1, Chip var2) {
         super(var2);
         this.this$0 = var1;
      }

      protected int getVirtualViewAt(float var1, float var2) {
         byte var3;
         if (this.this$0.hasCloseIcon() && this.this$0.getCloseIconTouchBounds().contains(var1, var2)) {
            var3 = 0;
         } else {
            var3 = -1;
         }

         return var3;
      }

      protected void getVisibleVirtualViews(List var1) {
         if (this.this$0.hasCloseIcon()) {
            var1.add(0);
         }

      }

      protected boolean onPerformActionForVirtualView(int var1, int var2, Bundle var3) {
         return var2 == 16 && var1 == 0 ? this.this$0.performCloseIconClick() : false;
      }

      protected void onPopulateNodeForHost(AccessibilityNodeInfoCompat var1) {
         boolean var2;
         if (this.this$0.chipDrawable != null && this.this$0.chipDrawable.isCheckable()) {
            var2 = true;
         } else {
            var2 = false;
         }

         var1.setCheckable(var2);
         var1.setClassName(Chip.class.getName());
         CharSequence var3 = this.this$0.getText();
         if (VERSION.SDK_INT >= 23) {
            var1.setText(var3);
         } else {
            var1.setContentDescription(var3);
         }

      }

      protected void onPopulateNodeForVirtualView(int var1, AccessibilityNodeInfoCompat var2) {
         boolean var3 = this.this$0.hasCloseIcon();
         Object var4 = "";
         if (var3) {
            CharSequence var5 = this.this$0.getCloseIconContentDescription();
            if (var5 != null) {
               var2.setContentDescription(var5);
            } else {
               var5 = this.this$0.getText();
               Context var6 = this.this$0.getContext();
               var1 = R.string.mtrl_chip_close_icon_content_description;
               if (!TextUtils.isEmpty(var5)) {
                  var4 = var5;
               }

               var2.setContentDescription(var6.getString(var1, new Object[]{var4}).trim());
            }

            var2.setBoundsInParent(this.this$0.getCloseIconTouchBoundsInt());
            var2.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            var2.setEnabled(this.this$0.isEnabled());
         } else {
            var2.setContentDescription("");
            var2.setBoundsInParent(Chip.EMPTY_BOUNDS);
         }

      }
   }
}
