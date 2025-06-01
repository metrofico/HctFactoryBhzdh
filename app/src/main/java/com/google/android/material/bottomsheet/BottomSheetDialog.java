package com.google.android.material.bottomsheet;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;

public class BottomSheetDialog extends AppCompatDialog {
   private BottomSheetBehavior behavior;
   private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback;
   boolean cancelable;
   private boolean canceledOnTouchOutside;
   private boolean canceledOnTouchOutsideSet;

   public BottomSheetDialog(Context var1) {
      this(var1, 0);
   }

   public BottomSheetDialog(Context var1, int var2) {
      super(var1, getThemeResId(var1, var2));
      this.cancelable = true;
      this.canceledOnTouchOutside = true;
      this.bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback(this) {
         final BottomSheetDialog this$0;

         {
            this.this$0 = var1;
         }

         public void onSlide(View var1, float var2) {
         }

         public void onStateChanged(View var1, int var2) {
            if (var2 == 5) {
               this.this$0.cancel();
            }

         }
      };
      this.supportRequestWindowFeature(1);
   }

   protected BottomSheetDialog(Context var1, boolean var2, DialogInterface.OnCancelListener var3) {
      super(var1, var2, var3);
      this.cancelable = true;
      this.canceledOnTouchOutside = true;
      this.bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback(this) {
         final BottomSheetDialog this$0;

         {
            this.this$0 = var1;
         }

         public void onSlide(View var1, float var2) {
         }

         public void onStateChanged(View var1, int var2) {
            if (var2 == 5) {
               this.this$0.cancel();
            }

         }
      };
      this.supportRequestWindowFeature(1);
      this.cancelable = var2;
   }

   private static int getThemeResId(Context var0, int var1) {
      int var2 = var1;
      if (var1 == 0) {
         TypedValue var3 = new TypedValue();
         if (var0.getTheme().resolveAttribute(R.attr.bottomSheetDialogTheme, var3, true)) {
            var2 = var3.resourceId;
         } else {
            var2 = R.style.Theme_Design_Light_BottomSheetDialog;
         }
      }

      return var2;
   }

   private View wrapInBottomSheet(int var1, View var2, ViewGroup.LayoutParams var3) {
      FrameLayout var5 = (FrameLayout)View.inflate(this.getContext(), R.layout.design_bottom_sheet_dialog, (ViewGroup)null);
      CoordinatorLayout var6 = (CoordinatorLayout)var5.findViewById(R.id.coordinator);
      View var4 = var2;
      if (var1 != 0) {
         var4 = var2;
         if (var2 == null) {
            var4 = this.getLayoutInflater().inflate(var1, var6, false);
         }
      }

      FrameLayout var8 = (FrameLayout)var6.findViewById(R.id.design_bottom_sheet);
      BottomSheetBehavior var7 = BottomSheetBehavior.from(var8);
      this.behavior = var7;
      var7.setBottomSheetCallback(this.bottomSheetCallback);
      this.behavior.setHideable(this.cancelable);
      if (var3 == null) {
         var8.addView(var4);
      } else {
         var8.addView(var4, var3);
      }

      var6.findViewById(R.id.touch_outside).setOnClickListener(new View.OnClickListener(this) {
         final BottomSheetDialog this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            if (this.this$0.cancelable && this.this$0.isShowing() && this.this$0.shouldWindowCloseOnTouchOutside()) {
               this.this$0.cancel();
            }

         }
      });
      ViewCompat.setAccessibilityDelegate(var8, new AccessibilityDelegateCompat(this) {
         final BottomSheetDialog this$0;

         {
            this.this$0 = var1;
         }

         public void onInitializeAccessibilityNodeInfo(View var1, AccessibilityNodeInfoCompat var2) {
            super.onInitializeAccessibilityNodeInfo(var1, var2);
            if (this.this$0.cancelable) {
               var2.addAction(1048576);
               var2.setDismissable(true);
            } else {
               var2.setDismissable(false);
            }

         }

         public boolean performAccessibilityAction(View var1, int var2, Bundle var3) {
            if (var2 == 1048576 && this.this$0.cancelable) {
               this.this$0.cancel();
               return true;
            } else {
               return super.performAccessibilityAction(var1, var2, var3);
            }
         }
      });
      var8.setOnTouchListener(new View.OnTouchListener(this) {
         final BottomSheetDialog this$0;

         {
            this.this$0 = var1;
         }

         public boolean onTouch(View var1, MotionEvent var2) {
            return true;
         }
      });
      return var5;
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      Window var2 = this.getWindow();
      if (var2 != null) {
         if (VERSION.SDK_INT >= 21) {
            var2.clearFlags(67108864);
            var2.addFlags(Integer.MIN_VALUE);
         }

         var2.setLayout(-1, -1);
      }

   }

   protected void onStart() {
      super.onStart();
      BottomSheetBehavior var1 = this.behavior;
      if (var1 != null && var1.getState() == 5) {
         this.behavior.setState(4);
      }

   }

   public void setCancelable(boolean var1) {
      super.setCancelable(var1);
      if (this.cancelable != var1) {
         this.cancelable = var1;
         BottomSheetBehavior var2 = this.behavior;
         if (var2 != null) {
            var2.setHideable(var1);
         }
      }

   }

   public void setCanceledOnTouchOutside(boolean var1) {
      super.setCanceledOnTouchOutside(var1);
      if (var1 && !this.cancelable) {
         this.cancelable = true;
      }

      this.canceledOnTouchOutside = var1;
      this.canceledOnTouchOutsideSet = true;
   }

   public void setContentView(int var1) {
      super.setContentView(this.wrapInBottomSheet(var1, (View)null, (ViewGroup.LayoutParams)null));
   }

   public void setContentView(View var1) {
      super.setContentView(this.wrapInBottomSheet(0, var1, (ViewGroup.LayoutParams)null));
   }

   public void setContentView(View var1, ViewGroup.LayoutParams var2) {
      super.setContentView(this.wrapInBottomSheet(0, var1, var2));
   }

   boolean shouldWindowCloseOnTouchOutside() {
      if (!this.canceledOnTouchOutsideSet) {
         TypedArray var1 = this.getContext().obtainStyledAttributes(new int[]{16843611});
         this.canceledOnTouchOutside = var1.getBoolean(0, true);
         var1.recycle();
         this.canceledOnTouchOutsideSet = true;
      }

      return this.canceledOnTouchOutside;
   }
}
