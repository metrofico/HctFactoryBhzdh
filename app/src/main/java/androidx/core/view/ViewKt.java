package androidx.core.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.Config;
import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\\\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a2\u0010\u0019\u001a\u00020\u001a*\u00020\u00032#\b\u0004\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u001a0\u001cH\u0086\b\u001a2\u0010 \u001a\u00020\u001a*\u00020\u00032#\b\u0004\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u001a0\u001cH\u0086\b\u001a2\u0010!\u001a\u00020\"*\u00020\u00032#\b\u0004\u0010\u001b\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u001a0\u001cH\u0086\b\u001a\u0014\u0010#\u001a\u00020$*\u00020\u00032\b\b\u0002\u0010%\u001a\u00020&\u001a%\u0010'\u001a\u00020(*\u00020\u00032\u0006\u0010)\u001a\u00020*2\u000e\b\u0004\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0+H\u0086\b\u001a%\u0010,\u001a\u00020(*\u00020\u00032\u0006\u0010)\u001a\u00020*2\u000e\b\u0004\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0+H\u0087\b\u001a\u0017\u0010-\u001a\u00020\u001a*\u00020\u00032\b\b\u0001\u0010.\u001a\u00020\fH\u0086\b\u001a7\u0010/\u001a\u00020\u001a\"\n\b\u0000\u00100\u0018\u0001*\u000201*\u00020\u00032\u0017\u00102\u001a\u0013\u0012\u0004\u0012\u0002H0\u0012\u0004\u0012\u00020\u001a0\u001c¢\u0006\u0002\b3H\u0087\b¢\u0006\u0002\b4\u001a&\u0010/\u001a\u00020\u001a*\u00020\u00032\u0017\u00102\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u001a0\u001c¢\u0006\u0002\b3H\u0086\b\u001a5\u00105\u001a\u00020\u001a*\u00020\u00032\b\b\u0003\u00106\u001a\u00020\f2\b\b\u0003\u00107\u001a\u00020\f2\b\b\u0003\u00108\u001a\u00020\f2\b\b\u0003\u00109\u001a\u00020\fH\u0086\b\u001a5\u0010:\u001a\u00020\u001a*\u00020\u00032\b\b\u0003\u0010;\u001a\u00020\f2\b\b\u0003\u00107\u001a\u00020\f2\b\b\u0003\u0010<\u001a\u00020\f2\b\b\u0003\u00109\u001a\u00020\fH\u0087\b\"*\u0010\u0002\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018Æ\u0002@Æ\u0002X\u0086\u000e¢\u0006\f\u001a\u0004\b\u0002\u0010\u0004\"\u0004\b\u0005\u0010\u0006\"*\u0010\u0007\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018Æ\u0002@Æ\u0002X\u0086\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\u0004\"\u0004\b\b\u0010\u0006\"*\u0010\t\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018Æ\u0002@Æ\u0002X\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0004\"\u0004\b\n\u0010\u0006\"\u0016\u0010\u000b\u001a\u00020\f*\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\"\u0016\u0010\u000f\u001a\u00020\f*\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000e\"\u0016\u0010\u0011\u001a\u00020\f*\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000e\"\u0016\u0010\u0013\u001a\u00020\f*\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000e\"\u0016\u0010\u0015\u001a\u00020\f*\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u000e\"\u0016\u0010\u0017\u001a\u00020\f*\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u000e¨\u0006="},
   d2 = {"value", "", "isGone", "Landroid/view/View;", "(Landroid/view/View;)Z", "setGone", "(Landroid/view/View;Z)V", "isInvisible", "setInvisible", "isVisible", "setVisible", "marginBottom", "", "getMarginBottom", "(Landroid/view/View;)I", "marginEnd", "getMarginEnd", "marginLeft", "getMarginLeft", "marginRight", "getMarginRight", "marginStart", "getMarginStart", "marginTop", "getMarginTop", "doOnLayout", "", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "view", "doOnNextLayout", "doOnPreDraw", "Landroidx/core/view/OneShotPreDrawListener;", "drawToBitmap", "Landroid/graphics/Bitmap;", "config", "Landroid/graphics/Bitmap$Config;", "postDelayed", "Ljava/lang/Runnable;", "delayInMillis", "", "Lkotlin/Function0;", "postOnAnimationDelayed", "setPadding", "size", "updateLayoutParams", "T", "Landroid/view/ViewGroup$LayoutParams;", "block", "Lkotlin/ExtensionFunctionType;", "updateLayoutParamsTyped", "updatePadding", "left", "top", "right", "bottom", "updatePaddingRelative", "start", "end", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class ViewKt {
   public static final void doOnLayout(View var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$doOnLayout");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      if (ViewCompat.isLaidOut(var0) && !var0.isLayoutRequested()) {
         var1.invoke(var0);
      } else {
         var0.addOnLayoutChangeListener((View.OnLayoutChangeListener)(new View.OnLayoutChangeListener(var1) {
            final Function1 $action$inlined;

            public {
               this.$action$inlined = var1;
            }

            public void onLayoutChange(View var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
               Intrinsics.checkParameterIsNotNull(var1, "view");
               var1.removeOnLayoutChangeListener((View.OnLayoutChangeListener)this);
               this.$action$inlined.invoke(var1);
            }
         }));
      }

   }

   public static final void doOnNextLayout(View var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$doOnNextLayout");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      var0.addOnLayoutChangeListener((View.OnLayoutChangeListener)(new View.OnLayoutChangeListener(var1) {
         final Function1 $action;

         public {
            this.$action = var1;
         }

         public void onLayoutChange(View var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9) {
            Intrinsics.checkParameterIsNotNull(var1, "view");
            var1.removeOnLayoutChangeListener((View.OnLayoutChangeListener)this);
            this.$action.invoke(var1);
         }
      }));
   }

   public static final OneShotPreDrawListener doOnPreDraw(View var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$doOnPreDraw");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      OneShotPreDrawListener var2 = OneShotPreDrawListener.add(var0, (Runnable)(new Runnable(var0, var1) {
         final Function1 $action;
         final View $this_doOnPreDraw;

         public {
            this.$this_doOnPreDraw = var1;
            this.$action = var2;
         }

         public final void run() {
            this.$action.invoke(this.$this_doOnPreDraw);
         }
      }));
      Intrinsics.checkExpressionValueIsNotNull(var2, "OneShotPreDrawListener.add(this) { action(this) }");
      return var2;
   }

   public static final Bitmap drawToBitmap(View var0, Bitmap.Config var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$drawToBitmap");
      Intrinsics.checkParameterIsNotNull(var1, "config");
      if (ViewCompat.isLaidOut(var0)) {
         Bitmap var3 = Bitmap.createBitmap(var0.getWidth(), var0.getHeight(), var1);
         Intrinsics.checkExpressionValueIsNotNull(var3, "Bitmap.createBitmap(width, height, config)");
         Canvas var2 = new Canvas(var3);
         var2.translate(-((float)var0.getScrollX()), -((float)var0.getScrollY()));
         var0.draw(var2);
         return var3;
      } else {
         throw (Throwable)(new IllegalStateException("View needs to be laid out before calling drawToBitmap()"));
      }
   }

   // $FF: synthetic method
   public static Bitmap drawToBitmap$default(View var0, Bitmap.Config var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = Config.ARGB_8888;
      }

      return drawToBitmap(var0, var1);
   }

   public static final int getMarginBottom(View var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$marginBottom");
      ViewGroup.LayoutParams var2 = var0.getLayoutParams();
      ViewGroup.LayoutParams var3 = var2;
      if (!(var2 instanceof ViewGroup.MarginLayoutParams)) {
         var3 = null;
      }

      ViewGroup.MarginLayoutParams var4 = (ViewGroup.MarginLayoutParams)var3;
      int var1;
      if (var4 != null) {
         var1 = var4.bottomMargin;
      } else {
         var1 = 0;
      }

      return var1;
   }

   public static final int getMarginEnd(View var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$marginEnd");
      ViewGroup.LayoutParams var2 = var0.getLayoutParams();
      int var1;
      if (var2 instanceof ViewGroup.MarginLayoutParams) {
         var1 = MarginLayoutParamsCompat.getMarginEnd((ViewGroup.MarginLayoutParams)var2);
      } else {
         var1 = 0;
      }

      return var1;
   }

   public static final int getMarginLeft(View var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$marginLeft");
      ViewGroup.LayoutParams var2 = var0.getLayoutParams();
      ViewGroup.LayoutParams var3 = var2;
      if (!(var2 instanceof ViewGroup.MarginLayoutParams)) {
         var3 = null;
      }

      ViewGroup.MarginLayoutParams var4 = (ViewGroup.MarginLayoutParams)var3;
      int var1;
      if (var4 != null) {
         var1 = var4.leftMargin;
      } else {
         var1 = 0;
      }

      return var1;
   }

   public static final int getMarginRight(View var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$marginRight");
      ViewGroup.LayoutParams var2 = var0.getLayoutParams();
      ViewGroup.LayoutParams var3 = var2;
      if (!(var2 instanceof ViewGroup.MarginLayoutParams)) {
         var3 = null;
      }

      ViewGroup.MarginLayoutParams var4 = (ViewGroup.MarginLayoutParams)var3;
      int var1;
      if (var4 != null) {
         var1 = var4.rightMargin;
      } else {
         var1 = 0;
      }

      return var1;
   }

   public static final int getMarginStart(View var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$marginStart");
      ViewGroup.LayoutParams var2 = var0.getLayoutParams();
      int var1;
      if (var2 instanceof ViewGroup.MarginLayoutParams) {
         var1 = MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams)var2);
      } else {
         var1 = 0;
      }

      return var1;
   }

   public static final int getMarginTop(View var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$marginTop");
      ViewGroup.LayoutParams var2 = var0.getLayoutParams();
      ViewGroup.LayoutParams var3 = var2;
      if (!(var2 instanceof ViewGroup.MarginLayoutParams)) {
         var3 = null;
      }

      ViewGroup.MarginLayoutParams var4 = (ViewGroup.MarginLayoutParams)var3;
      int var1;
      if (var4 != null) {
         var1 = var4.topMargin;
      } else {
         var1 = 0;
      }

      return var1;
   }

   public static final boolean isGone(View var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isGone");
      boolean var1;
      if (var0.getVisibility() == 8) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final boolean isInvisible(View var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isInvisible");
      boolean var1;
      if (var0.getVisibility() == 4) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final boolean isVisible(View var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isVisible");
      boolean var1;
      if (var0.getVisibility() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final Runnable postDelayed(View var0, long var1, Function0 var3) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$postDelayed");
      Intrinsics.checkParameterIsNotNull(var3, "action");
      Runnable var4 = (Runnable)(new Runnable(var3) {
         final Function0 $action;

         public {
            this.$action = var1;
         }

         public final void run() {
            this.$action.invoke();
         }
      });
      var0.postDelayed(var4, var1);
      return var4;
   }

   public static final Runnable postOnAnimationDelayed(View var0, long var1, Function0 var3) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$postOnAnimationDelayed");
      Intrinsics.checkParameterIsNotNull(var3, "action");
      Runnable var4 = (Runnable)(new Runnable(var3) {
         final Function0 $action;

         public {
            this.$action = var1;
         }

         public final void run() {
            this.$action.invoke();
         }
      });
      var0.postOnAnimationDelayed(var4, var1);
      return var4;
   }

   public static final void setGone(View var0, boolean var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isGone");
      byte var2;
      if (var1) {
         var2 = 8;
      } else {
         var2 = 0;
      }

      var0.setVisibility(var2);
   }

   public static final void setInvisible(View var0, boolean var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isInvisible");
      byte var2;
      if (var1) {
         var2 = 4;
      } else {
         var2 = 0;
      }

      var0.setVisibility(var2);
   }

   public static final void setPadding(View var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$setPadding");
      var0.setPadding(var1, var1, var1, var1);
   }

   public static final void setVisible(View var0, boolean var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isVisible");
      byte var2;
      if (var1) {
         var2 = 0;
      } else {
         var2 = 8;
      }

      var0.setVisibility(var2);
   }

   public static final void updateLayoutParams(View var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$updateLayoutParams");
      Intrinsics.checkParameterIsNotNull(var1, "block");
      ViewGroup.LayoutParams var2 = var0.getLayoutParams();
      if (var2 != null) {
         var1.invoke(var2);
         var0.setLayoutParams(var2);
      } else {
         throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
      }
   }

   private static final void updateLayoutParamsTyped(View var0, Function1 var1) {
      ViewGroup.LayoutParams var2 = var0.getLayoutParams();
      Intrinsics.reifiedOperationMarker(1, "T");
      ViewGroup.LayoutParams var3 = (ViewGroup.LayoutParams)var2;
      var1.invoke(var2);
      var0.setLayoutParams(var2);
   }

   public static final void updatePadding(View var0, int var1, int var2, int var3, int var4) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$updatePadding");
      var0.setPadding(var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void updatePadding$default(View var0, int var1, int var2, int var3, int var4, int var5, Object var6) {
      if ((var5 & 1) != 0) {
         var1 = var0.getPaddingLeft();
      }

      if ((var5 & 2) != 0) {
         var2 = var0.getPaddingTop();
      }

      if ((var5 & 4) != 0) {
         var3 = var0.getPaddingRight();
      }

      if ((var5 & 8) != 0) {
         var4 = var0.getPaddingBottom();
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$updatePadding");
      var0.setPadding(var1, var2, var3, var4);
   }

   public static final void updatePaddingRelative(View var0, int var1, int var2, int var3, int var4) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$updatePaddingRelative");
      var0.setPaddingRelative(var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void updatePaddingRelative$default(View var0, int var1, int var2, int var3, int var4, int var5, Object var6) {
      if ((var5 & 1) != 0) {
         var1 = var0.getPaddingStart();
      }

      if ((var5 & 2) != 0) {
         var2 = var0.getPaddingTop();
      }

      if ((var5 & 4) != 0) {
         var3 = var0.getPaddingEnd();
      }

      if ((var5 & 8) != 0) {
         var4 = var0.getPaddingBottom();
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$updatePaddingRelative");
      var0.setPaddingRelative(var1, var2, var3, var4);
   }
}
