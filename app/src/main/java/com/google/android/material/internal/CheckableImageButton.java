package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;
import androidx.appcompat.R;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

public class CheckableImageButton extends AppCompatImageButton implements Checkable {
   private static final int[] DRAWABLE_STATE_CHECKED = new int[]{16842912};
   private boolean checked;

   public CheckableImageButton(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CheckableImageButton(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.imageButtonStyle);
   }

   public CheckableImageButton(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat(this) {
         final CheckableImageButton this$0;

         {
            this.this$0 = var1;
         }

         public void onInitializeAccessibilityEvent(View var1, AccessibilityEvent var2) {
            super.onInitializeAccessibilityEvent(var1, var2);
            var2.setChecked(this.this$0.isChecked());
         }

         public void onInitializeAccessibilityNodeInfo(View var1, AccessibilityNodeInfoCompat var2) {
            super.onInitializeAccessibilityNodeInfo(var1, var2);
            var2.setCheckable(true);
            var2.setChecked(this.this$0.isChecked());
         }
      });
   }

   public boolean isChecked() {
      return this.checked;
   }

   public int[] onCreateDrawableState(int var1) {
      if (this.checked) {
         int[] var2 = DRAWABLE_STATE_CHECKED;
         return mergeDrawableStates(super.onCreateDrawableState(var1 + var2.length), var2);
      } else {
         return super.onCreateDrawableState(var1);
      }
   }

   public void setChecked(boolean var1) {
      if (this.checked != var1) {
         this.checked = var1;
         this.refreshDrawableState();
         this.sendAccessibilityEvent(2048);
      }

   }

   public void toggle() {
      this.setChecked(this.checked ^ true);
   }
}
