package androidx.core.view;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import androidx.core.graphics.Insets;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class WindowInsetsCompat {
   public static final WindowInsetsCompat CONSUMED;
   private static final String TAG = "WindowInsetsCompat";
   private final Impl mImpl;

   static {
      if (VERSION.SDK_INT >= 30) {
         CONSUMED = WindowInsetsCompat.Impl30.CONSUMED;
      } else {
         CONSUMED = WindowInsetsCompat.Impl.CONSUMED;
      }

   }

   private WindowInsetsCompat(WindowInsets var1) {
      if (VERSION.SDK_INT >= 30) {
         this.mImpl = new Impl30(this, var1);
      } else if (VERSION.SDK_INT >= 29) {
         this.mImpl = new Impl29(this, var1);
      } else if (VERSION.SDK_INT >= 28) {
         this.mImpl = new Impl28(this, var1);
      } else if (VERSION.SDK_INT >= 21) {
         this.mImpl = new Impl21(this, var1);
      } else if (VERSION.SDK_INT >= 20) {
         this.mImpl = new Impl20(this, var1);
      } else {
         this.mImpl = new Impl(this);
      }

   }

   public WindowInsetsCompat(WindowInsetsCompat var1) {
      if (var1 != null) {
         Impl var2 = var1.mImpl;
         if (VERSION.SDK_INT >= 30 && var2 instanceof Impl30) {
            this.mImpl = new Impl30(this, (Impl30)var2);
         } else if (VERSION.SDK_INT >= 29 && var2 instanceof Impl29) {
            this.mImpl = new Impl29(this, (Impl29)var2);
         } else if (VERSION.SDK_INT >= 28 && var2 instanceof Impl28) {
            this.mImpl = new Impl28(this, (Impl28)var2);
         } else if (VERSION.SDK_INT >= 21 && var2 instanceof Impl21) {
            this.mImpl = new Impl21(this, (Impl21)var2);
         } else if (VERSION.SDK_INT >= 20 && var2 instanceof Impl20) {
            this.mImpl = new Impl20(this, (Impl20)var2);
         } else {
            this.mImpl = new Impl(this);
         }

         var2.copyWindowDataInto(this);
      } else {
         this.mImpl = new Impl(this);
      }

   }

   static Insets insetInsets(Insets var0, int var1, int var2, int var3, int var4) {
      int var7 = Math.max(0, var0.left - var1);
      int var5 = Math.max(0, var0.top - var2);
      int var6 = Math.max(0, var0.right - var3);
      int var8 = Math.max(0, var0.bottom - var4);
      return var7 == var1 && var5 == var2 && var6 == var3 && var8 == var4 ? var0 : Insets.of(var7, var5, var6, var8);
   }

   public static WindowInsetsCompat toWindowInsetsCompat(WindowInsets var0) {
      return toWindowInsetsCompat(var0, (View)null);
   }

   public static WindowInsetsCompat toWindowInsetsCompat(WindowInsets var0, View var1) {
      WindowInsetsCompat var2 = new WindowInsetsCompat((WindowInsets)Preconditions.checkNotNull(var0));
      if (var1 != null && ViewCompat.isAttachedToWindow(var1)) {
         var2.setRootWindowInsets(ViewCompat.getRootWindowInsets(var1));
         var2.copyRootViewBounds(var1.getRootView());
      }

      return var2;
   }

   @Deprecated
   public WindowInsetsCompat consumeDisplayCutout() {
      return this.mImpl.consumeDisplayCutout();
   }

   @Deprecated
   public WindowInsetsCompat consumeStableInsets() {
      return this.mImpl.consumeStableInsets();
   }

   @Deprecated
   public WindowInsetsCompat consumeSystemWindowInsets() {
      return this.mImpl.consumeSystemWindowInsets();
   }

   void copyRootViewBounds(View var1) {
      this.mImpl.copyRootViewBounds(var1);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof WindowInsetsCompat)) {
         return false;
      } else {
         WindowInsetsCompat var2 = (WindowInsetsCompat)var1;
         return ObjectsCompat.equals(this.mImpl, var2.mImpl);
      }
   }

   public DisplayCutoutCompat getDisplayCutout() {
      return this.mImpl.getDisplayCutout();
   }

   public Insets getInsets(int var1) {
      return this.mImpl.getInsets(var1);
   }

   public Insets getInsetsIgnoringVisibility(int var1) {
      return this.mImpl.getInsetsIgnoringVisibility(var1);
   }

   @Deprecated
   public Insets getMandatorySystemGestureInsets() {
      return this.mImpl.getMandatorySystemGestureInsets();
   }

   @Deprecated
   public int getStableInsetBottom() {
      return this.mImpl.getStableInsets().bottom;
   }

   @Deprecated
   public int getStableInsetLeft() {
      return this.mImpl.getStableInsets().left;
   }

   @Deprecated
   public int getStableInsetRight() {
      return this.mImpl.getStableInsets().right;
   }

   @Deprecated
   public int getStableInsetTop() {
      return this.mImpl.getStableInsets().top;
   }

   @Deprecated
   public Insets getStableInsets() {
      return this.mImpl.getStableInsets();
   }

   @Deprecated
   public Insets getSystemGestureInsets() {
      return this.mImpl.getSystemGestureInsets();
   }

   @Deprecated
   public int getSystemWindowInsetBottom() {
      return this.mImpl.getSystemWindowInsets().bottom;
   }

   @Deprecated
   public int getSystemWindowInsetLeft() {
      return this.mImpl.getSystemWindowInsets().left;
   }

   @Deprecated
   public int getSystemWindowInsetRight() {
      return this.mImpl.getSystemWindowInsets().right;
   }

   @Deprecated
   public int getSystemWindowInsetTop() {
      return this.mImpl.getSystemWindowInsets().top;
   }

   @Deprecated
   public Insets getSystemWindowInsets() {
      return this.mImpl.getSystemWindowInsets();
   }

   @Deprecated
   public Insets getTappableElementInsets() {
      return this.mImpl.getTappableElementInsets();
   }

   public boolean hasInsets() {
      boolean var1;
      if (this.getInsets(WindowInsetsCompat.Type.all()).equals(Insets.NONE) && this.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.all() ^ WindowInsetsCompat.Type.ime()).equals(Insets.NONE) && this.getDisplayCutout() == null) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   @Deprecated
   public boolean hasStableInsets() {
      return this.mImpl.getStableInsets().equals(Insets.NONE) ^ true;
   }

   @Deprecated
   public boolean hasSystemWindowInsets() {
      return this.mImpl.getSystemWindowInsets().equals(Insets.NONE) ^ true;
   }

   public int hashCode() {
      Impl var2 = this.mImpl;
      int var1;
      if (var2 == null) {
         var1 = 0;
      } else {
         var1 = var2.hashCode();
      }

      return var1;
   }

   public WindowInsetsCompat inset(int var1, int var2, int var3, int var4) {
      return this.mImpl.inset(var1, var2, var3, var4);
   }

   public WindowInsetsCompat inset(Insets var1) {
      return this.inset(var1.left, var1.top, var1.right, var1.bottom);
   }

   public boolean isConsumed() {
      return this.mImpl.isConsumed();
   }

   public boolean isRound() {
      return this.mImpl.isRound();
   }

   public boolean isVisible(int var1) {
      return this.mImpl.isVisible(var1);
   }

   @Deprecated
   public WindowInsetsCompat replaceSystemWindowInsets(int var1, int var2, int var3, int var4) {
      return (new Builder(this)).setSystemWindowInsets(Insets.of(var1, var2, var3, var4)).build();
   }

   @Deprecated
   public WindowInsetsCompat replaceSystemWindowInsets(Rect var1) {
      return (new Builder(this)).setSystemWindowInsets(Insets.of(var1)).build();
   }

   void setOverriddenInsets(Insets[] var1) {
      this.mImpl.setOverriddenInsets(var1);
   }

   void setRootViewData(Insets var1) {
      this.mImpl.setRootViewData(var1);
   }

   void setRootWindowInsets(WindowInsetsCompat var1) {
      this.mImpl.setRootWindowInsets(var1);
   }

   void setStableInsets(Insets var1) {
      this.mImpl.setStableInsets(var1);
   }

   public WindowInsets toWindowInsets() {
      Impl var1 = this.mImpl;
      WindowInsets var2;
      if (var1 instanceof Impl20) {
         var2 = ((Impl20)var1).mPlatformInsets;
      } else {
         var2 = null;
      }

      return var2;
   }

   static class Api21ReflectionHolder {
      private static Field sContentInsets;
      private static boolean sReflectionSucceeded;
      private static Field sStableInsets;
      private static Field sViewAttachInfoField;

      static {
         try {
            Field var0 = View.class.getDeclaredField("mAttachInfo");
            sViewAttachInfoField = var0;
            var0.setAccessible(true);
            Class var3 = Class.forName("android.view.View$AttachInfo");
            Field var1 = var3.getDeclaredField("mStableInsets");
            sStableInsets = var1;
            var1.setAccessible(true);
            var0 = var3.getDeclaredField("mContentInsets");
            sContentInsets = var0;
            var0.setAccessible(true);
            sReflectionSucceeded = true;
         } catch (ReflectiveOperationException var2) {
            Log.w("WindowInsetsCompat", "Failed to get visible insets from AttachInfo " + var2.getMessage(), var2);
         }

      }

      private Api21ReflectionHolder() {
      }

      public static WindowInsetsCompat getRootWindowInsets(View var0) {
         if (sReflectionSucceeded && var0.isAttachedToWindow()) {
            View var1 = var0.getRootView();

            IllegalAccessException var10000;
            label45: {
               Object var2;
               boolean var10001;
               try {
                  var2 = sViewAttachInfoField.get(var1);
               } catch (IllegalAccessException var6) {
                  var10000 = var6;
                  var10001 = false;
                  break label45;
               }

               if (var2 == null) {
                  return null;
               }

               Rect var8;
               Rect var10;
               try {
                  var8 = (Rect)sStableInsets.get(var2);
                  var10 = (Rect)sContentInsets.get(var2);
               } catch (IllegalAccessException var5) {
                  var10000 = var5;
                  var10001 = false;
                  break label45;
               }

               if (var8 == null || var10 == null) {
                  return null;
               }

               try {
                  Builder var3 = new Builder();
                  WindowInsetsCompat var9 = var3.setStableInsets(Insets.of(var8)).setSystemWindowInsets(Insets.of(var10)).build();
                  var9.setRootWindowInsets(var9);
                  var9.copyRootViewBounds(var0.getRootView());
                  return var9;
               } catch (IllegalAccessException var4) {
                  var10000 = var4;
                  var10001 = false;
               }
            }

            IllegalAccessException var7 = var10000;
            Log.w("WindowInsetsCompat", "Failed to get insets from AttachInfo. " + var7.getMessage(), var7);
         }

         return null;
      }
   }

   public static final class Builder {
      private final BuilderImpl mImpl;

      public Builder() {
         if (VERSION.SDK_INT >= 30) {
            this.mImpl = new BuilderImpl30();
         } else if (VERSION.SDK_INT >= 29) {
            this.mImpl = new BuilderImpl29();
         } else if (VERSION.SDK_INT >= 20) {
            this.mImpl = new BuilderImpl20();
         } else {
            this.mImpl = new BuilderImpl();
         }

      }

      public Builder(WindowInsetsCompat var1) {
         if (VERSION.SDK_INT >= 30) {
            this.mImpl = new BuilderImpl30(var1);
         } else if (VERSION.SDK_INT >= 29) {
            this.mImpl = new BuilderImpl29(var1);
         } else if (VERSION.SDK_INT >= 20) {
            this.mImpl = new BuilderImpl20(var1);
         } else {
            this.mImpl = new BuilderImpl(var1);
         }

      }

      public WindowInsetsCompat build() {
         return this.mImpl.build();
      }

      public Builder setDisplayCutout(DisplayCutoutCompat var1) {
         this.mImpl.setDisplayCutout(var1);
         return this;
      }

      public Builder setInsets(int var1, Insets var2) {
         this.mImpl.setInsets(var1, var2);
         return this;
      }

      public Builder setInsetsIgnoringVisibility(int var1, Insets var2) {
         this.mImpl.setInsetsIgnoringVisibility(var1, var2);
         return this;
      }

      @Deprecated
      public Builder setMandatorySystemGestureInsets(Insets var1) {
         this.mImpl.setMandatorySystemGestureInsets(var1);
         return this;
      }

      @Deprecated
      public Builder setStableInsets(Insets var1) {
         this.mImpl.setStableInsets(var1);
         return this;
      }

      @Deprecated
      public Builder setSystemGestureInsets(Insets var1) {
         this.mImpl.setSystemGestureInsets(var1);
         return this;
      }

      @Deprecated
      public Builder setSystemWindowInsets(Insets var1) {
         this.mImpl.setSystemWindowInsets(var1);
         return this;
      }

      @Deprecated
      public Builder setTappableElementInsets(Insets var1) {
         this.mImpl.setTappableElementInsets(var1);
         return this;
      }

      public Builder setVisible(int var1, boolean var2) {
         this.mImpl.setVisible(var1, var2);
         return this;
      }
   }

   private static class BuilderImpl {
      private final WindowInsetsCompat mInsets;
      Insets[] mInsetsTypeMask;

      BuilderImpl() {
         WindowInsetsCompat var1 = (WindowInsetsCompat)null;
         this(new WindowInsetsCompat((WindowInsetsCompat)null));
      }

      BuilderImpl(WindowInsetsCompat var1) {
         this.mInsets = var1;
      }

      protected final void applyInsetTypes() {
         Insets[] var1 = this.mInsetsTypeMask;
         if (var1 != null) {
            Insets var3 = var1[WindowInsetsCompat.Type.indexOf(1)];
            Insets var2 = this.mInsetsTypeMask[WindowInsetsCompat.Type.indexOf(2)];
            Insets var4 = var2;
            if (var2 == null) {
               var4 = this.mInsets.getInsets(2);
            }

            var2 = var3;
            if (var3 == null) {
               var2 = this.mInsets.getInsets(1);
            }

            this.setSystemWindowInsets(Insets.max(var2, var4));
            var4 = this.mInsetsTypeMask[WindowInsetsCompat.Type.indexOf(16)];
            if (var4 != null) {
               this.setSystemGestureInsets(var4);
            }

            var4 = this.mInsetsTypeMask[WindowInsetsCompat.Type.indexOf(32)];
            if (var4 != null) {
               this.setMandatorySystemGestureInsets(var4);
            }

            var4 = this.mInsetsTypeMask[WindowInsetsCompat.Type.indexOf(64)];
            if (var4 != null) {
               this.setTappableElementInsets(var4);
            }
         }

      }

      WindowInsetsCompat build() {
         this.applyInsetTypes();
         return this.mInsets;
      }

      void setDisplayCutout(DisplayCutoutCompat var1) {
      }

      void setInsets(int var1, Insets var2) {
         if (this.mInsetsTypeMask == null) {
            this.mInsetsTypeMask = new Insets[9];
         }

         for(int var3 = 1; var3 <= 256; var3 <<= 1) {
            if ((var1 & var3) != 0) {
               this.mInsetsTypeMask[WindowInsetsCompat.Type.indexOf(var3)] = var2;
            }
         }

      }

      void setInsetsIgnoringVisibility(int var1, Insets var2) {
         if (var1 == 8) {
            throw new IllegalArgumentException("Ignoring visibility inset not available for IME");
         }
      }

      void setMandatorySystemGestureInsets(Insets var1) {
      }

      void setStableInsets(Insets var1) {
      }

      void setSystemGestureInsets(Insets var1) {
      }

      void setSystemWindowInsets(Insets var1) {
      }

      void setTappableElementInsets(Insets var1) {
      }

      void setVisible(int var1, boolean var2) {
      }
   }

   private static class BuilderImpl20 extends BuilderImpl {
      private static Constructor sConstructor;
      private static boolean sConstructorFetched;
      private static Field sConsumedField;
      private static boolean sConsumedFieldFetched;
      private WindowInsets mPlatformInsets;
      private Insets mStableInsets;

      BuilderImpl20() {
         this.mPlatformInsets = createWindowInsetsInstance();
      }

      BuilderImpl20(WindowInsetsCompat var1) {
         super(var1);
         this.mPlatformInsets = var1.toWindowInsets();
      }

      private static WindowInsets createWindowInsetsInstance() {
         if (!sConsumedFieldFetched) {
            try {
               sConsumedField = WindowInsets.class.getDeclaredField("CONSUMED");
            } catch (ReflectiveOperationException var3) {
               Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", var3);
            }

            sConsumedFieldFetched = true;
         }

         Field var0 = sConsumedField;
         WindowInsets var7;
         if (var0 != null) {
            label54: {
               ReflectiveOperationException var10000;
               label59: {
                  boolean var10001;
                  try {
                     var7 = (WindowInsets)var0.get((Object)null);
                  } catch (ReflectiveOperationException var6) {
                     var10000 = var6;
                     var10001 = false;
                     break label59;
                  }

                  if (var7 == null) {
                     break label54;
                  }

                  try {
                     var7 = new WindowInsets(var7);
                     return var7;
                  } catch (ReflectiveOperationException var5) {
                     var10000 = var5;
                     var10001 = false;
                  }
               }

               ReflectiveOperationException var8 = var10000;
               Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", var8);
            }
         }

         if (!sConstructorFetched) {
            try {
               sConstructor = WindowInsets.class.getConstructor(Rect.class);
            } catch (ReflectiveOperationException var2) {
               Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", var2);
            }

            sConstructorFetched = true;
         }

         Constructor var1 = sConstructor;
         if (var1 != null) {
            try {
               Rect var9 = new Rect();
               var7 = (WindowInsets)var1.newInstance(var9);
               return var7;
            } catch (ReflectiveOperationException var4) {
               Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", var4);
            }
         }

         return null;
      }

      WindowInsetsCompat build() {
         this.applyInsetTypes();
         WindowInsetsCompat var1 = WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets);
         var1.setOverriddenInsets(this.mInsetsTypeMask);
         var1.setStableInsets(this.mStableInsets);
         return var1;
      }

      void setStableInsets(Insets var1) {
         this.mStableInsets = var1;
      }

      void setSystemWindowInsets(Insets var1) {
         WindowInsets var2 = this.mPlatformInsets;
         if (var2 != null) {
            this.mPlatformInsets = var2.replaceSystemWindowInsets(var1.left, var1.top, var1.right, var1.bottom);
         }

      }
   }

   private static class BuilderImpl29 extends BuilderImpl {
      final WindowInsets.Builder mPlatBuilder;

      BuilderImpl29() {
         this.mPlatBuilder = new WindowInsets.Builder();
      }

      BuilderImpl29(WindowInsetsCompat var1) {
         super(var1);
         WindowInsets var2 = var1.toWindowInsets();
         WindowInsets.Builder var3;
         if (var2 != null) {
            var3 = new WindowInsets.Builder(var2);
         } else {
            var3 = new WindowInsets.Builder();
         }

         this.mPlatBuilder = var3;
      }

      WindowInsetsCompat build() {
         this.applyInsetTypes();
         WindowInsetsCompat var1 = WindowInsetsCompat.toWindowInsetsCompat(this.mPlatBuilder.build());
         var1.setOverriddenInsets(this.mInsetsTypeMask);
         return var1;
      }

      void setDisplayCutout(DisplayCutoutCompat var1) {
         WindowInsets.Builder var2 = this.mPlatBuilder;
         DisplayCutout var3;
         if (var1 != null) {
            var3 = var1.unwrap();
         } else {
            var3 = null;
         }

         var2.setDisplayCutout(var3);
      }

      void setMandatorySystemGestureInsets(Insets var1) {
         this.mPlatBuilder.setMandatorySystemGestureInsets(var1.toPlatformInsets());
      }

      void setStableInsets(Insets var1) {
         this.mPlatBuilder.setStableInsets(var1.toPlatformInsets());
      }

      void setSystemGestureInsets(Insets var1) {
         this.mPlatBuilder.setSystemGestureInsets(var1.toPlatformInsets());
      }

      void setSystemWindowInsets(Insets var1) {
         this.mPlatBuilder.setSystemWindowInsets(var1.toPlatformInsets());
      }

      void setTappableElementInsets(Insets var1) {
         this.mPlatBuilder.setTappableElementInsets(var1.toPlatformInsets());
      }
   }

   private static class BuilderImpl30 extends BuilderImpl29 {
      BuilderImpl30() {
      }

      BuilderImpl30(WindowInsetsCompat var1) {
         super(var1);
      }

      void setInsets(int var1, Insets var2) {
         this.mPlatBuilder.setInsets(WindowInsetsCompat.TypeImpl30.toPlatformType(var1), var2.toPlatformInsets());
      }

      void setInsetsIgnoringVisibility(int var1, Insets var2) {
         this.mPlatBuilder.setInsetsIgnoringVisibility(WindowInsetsCompat.TypeImpl30.toPlatformType(var1), var2.toPlatformInsets());
      }

      void setVisible(int var1, boolean var2) {
         this.mPlatBuilder.setVisible(WindowInsetsCompat.TypeImpl30.toPlatformType(var1), var2);
      }
   }

   private static class Impl {
      static final WindowInsetsCompat CONSUMED = (new Builder()).build().consumeDisplayCutout().consumeStableInsets().consumeSystemWindowInsets();
      final WindowInsetsCompat mHost;

      Impl(WindowInsetsCompat var1) {
         this.mHost = var1;
      }

      WindowInsetsCompat consumeDisplayCutout() {
         return this.mHost;
      }

      WindowInsetsCompat consumeStableInsets() {
         return this.mHost;
      }

      WindowInsetsCompat consumeSystemWindowInsets() {
         return this.mHost;
      }

      void copyRootViewBounds(View var1) {
      }

      void copyWindowDataInto(WindowInsetsCompat var1) {
      }

      public boolean equals(Object var1) {
         boolean var2 = true;
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof Impl)) {
            return false;
         } else {
            Impl var3 = (Impl)var1;
            if (this.isRound() != var3.isRound() || this.isConsumed() != var3.isConsumed() || !ObjectsCompat.equals(this.getSystemWindowInsets(), var3.getSystemWindowInsets()) || !ObjectsCompat.equals(this.getStableInsets(), var3.getStableInsets()) || !ObjectsCompat.equals(this.getDisplayCutout(), var3.getDisplayCutout())) {
               var2 = false;
            }

            return var2;
         }
      }

      DisplayCutoutCompat getDisplayCutout() {
         return null;
      }

      Insets getInsets(int var1) {
         return Insets.NONE;
      }

      Insets getInsetsIgnoringVisibility(int var1) {
         if ((var1 & 8) == 0) {
            return Insets.NONE;
         } else {
            throw new IllegalArgumentException("Unable to query the maximum insets for IME");
         }
      }

      Insets getMandatorySystemGestureInsets() {
         return this.getSystemWindowInsets();
      }

      Insets getStableInsets() {
         return Insets.NONE;
      }

      Insets getSystemGestureInsets() {
         return this.getSystemWindowInsets();
      }

      Insets getSystemWindowInsets() {
         return Insets.NONE;
      }

      Insets getTappableElementInsets() {
         return this.getSystemWindowInsets();
      }

      public int hashCode() {
         return ObjectsCompat.hash(this.isRound(), this.isConsumed(), this.getSystemWindowInsets(), this.getStableInsets(), this.getDisplayCutout());
      }

      WindowInsetsCompat inset(int var1, int var2, int var3, int var4) {
         return CONSUMED;
      }

      boolean isConsumed() {
         return false;
      }

      boolean isRound() {
         return false;
      }

      boolean isVisible(int var1) {
         return true;
      }

      public void setOverriddenInsets(Insets[] var1) {
      }

      void setRootViewData(Insets var1) {
      }

      void setRootWindowInsets(WindowInsetsCompat var1) {
      }

      public void setStableInsets(Insets var1) {
      }
   }

   private static class Impl20 extends Impl {
      private static Class sAttachInfoClass;
      private static Field sAttachInfoField;
      private static Method sGetViewRootImplMethod;
      private static Field sVisibleInsetsField;
      private static boolean sVisibleRectReflectionFetched;
      private Insets[] mOverriddenInsets;
      final WindowInsets mPlatformInsets;
      Insets mRootViewVisibleInsets;
      private WindowInsetsCompat mRootWindowInsets;
      private Insets mSystemWindowInsets;

      Impl20(WindowInsetsCompat var1, WindowInsets var2) {
         super(var1);
         this.mSystemWindowInsets = null;
         this.mPlatformInsets = var2;
      }

      Impl20(WindowInsetsCompat var1, Impl20 var2) {
         this(var1, new WindowInsets(var2.mPlatformInsets));
      }

      private Insets getInsets(int var1, boolean var2) {
         Insets var4 = Insets.NONE;

         for(int var3 = 1; var3 <= 256; var3 <<= 1) {
            if ((var1 & var3) != 0) {
               var4 = Insets.max(var4, this.getInsetsForType(var3, var2));
            }
         }

         return var4;
      }

      private Insets getRootStableInsets() {
         WindowInsetsCompat var1 = this.mRootWindowInsets;
         return var1 != null ? var1.getStableInsets() : Insets.NONE;
      }

      private Insets getVisibleInsets(View var1) {
         if (VERSION.SDK_INT < 30) {
            if (!sVisibleRectReflectionFetched) {
               loadReflectionField();
            }

            Method var3 = sGetViewRootImplMethod;
            Object var2 = null;
            if (var3 != null && sAttachInfoClass != null && sVisibleInsetsField != null) {
               ReflectiveOperationException var10000;
               label46: {
                  Object var8;
                  boolean var10001;
                  try {
                     var8 = var3.invoke(var1);
                  } catch (ReflectiveOperationException var7) {
                     var10000 = var7;
                     var10001 = false;
                     break label46;
                  }

                  if (var8 == null) {
                     try {
                        NullPointerException var9 = new NullPointerException();
                        Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", var9);
                        return null;
                     } catch (ReflectiveOperationException var4) {
                        var10000 = var4;
                        var10001 = false;
                     }
                  } else {
                     label56: {
                        Rect var12;
                        try {
                           var8 = sAttachInfoField.get(var8);
                           var12 = (Rect)sVisibleInsetsField.get(var8);
                        } catch (ReflectiveOperationException var6) {
                           var10000 = var6;
                           var10001 = false;
                           break label56;
                        }

                        Insets var10 = (Insets)var2;
                        if (var12 != null) {
                           try {
                              var10 = Insets.of(var12);
                           } catch (ReflectiveOperationException var5) {
                              var10000 = var5;
                              var10001 = false;
                              break label56;
                           }
                        }

                        return var10;
                     }
                  }
               }

               ReflectiveOperationException var11 = var10000;
               Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + var11.getMessage(), var11);
            }

            return null;
         } else {
            throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
         }
      }

      private static void loadReflectionField() {
         try {
            sGetViewRootImplMethod = View.class.getDeclaredMethod("getViewRootImpl");
            Class var0 = Class.forName("android.view.View$AttachInfo");
            sAttachInfoClass = var0;
            sVisibleInsetsField = var0.getDeclaredField("mVisibleInsets");
            sAttachInfoField = Class.forName("android.view.ViewRootImpl").getDeclaredField("mAttachInfo");
            sVisibleInsetsField.setAccessible(true);
            sAttachInfoField.setAccessible(true);
         } catch (ReflectiveOperationException var1) {
            Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + var1.getMessage(), var1);
         }

         sVisibleRectReflectionFetched = true;
      }

      void copyRootViewBounds(View var1) {
         Insets var2 = this.getVisibleInsets(var1);
         Insets var3 = var2;
         if (var2 == null) {
            var3 = Insets.NONE;
         }

         this.setRootViewData(var3);
      }

      void copyWindowDataInto(WindowInsetsCompat var1) {
         var1.setRootWindowInsets(this.mRootWindowInsets);
         var1.setRootViewData(this.mRootViewVisibleInsets);
      }

      public boolean equals(Object var1) {
         if (!super.equals(var1)) {
            return false;
         } else {
            Impl20 var2 = (Impl20)var1;
            return Objects.equals(this.mRootViewVisibleInsets, var2.mRootViewVisibleInsets);
         }
      }

      public Insets getInsets(int var1) {
         return this.getInsets(var1, false);
      }

      protected Insets getInsetsForType(int var1, boolean var2) {
         if (var1 != 1) {
            Insets var4 = null;
            Insets var5 = null;
            if (var1 != 2) {
               if (var1 != 8) {
                  if (var1 != 16) {
                     if (var1 != 32) {
                        if (var1 != 64) {
                           if (var1 != 128) {
                              return Insets.NONE;
                           } else {
                              WindowInsetsCompat var8 = this.mRootWindowInsets;
                              DisplayCutoutCompat var9;
                              if (var8 != null) {
                                 var9 = var8.getDisplayCutout();
                              } else {
                                 var9 = this.getDisplayCutout();
                              }

                              return var9 != null ? Insets.of(var9.getSafeInsetLeft(), var9.getSafeInsetTop(), var9.getSafeInsetRight(), var9.getSafeInsetBottom()) : Insets.NONE;
                           }
                        } else {
                           return this.getTappableElementInsets();
                        }
                     } else {
                        return this.getMandatorySystemGestureInsets();
                     }
                  } else {
                     return this.getSystemGestureInsets();
                  }
               } else {
                  Insets[] var7 = this.mOverriddenInsets;
                  var4 = var5;
                  if (var7 != null) {
                     var4 = var7[WindowInsetsCompat.Type.indexOf(8)];
                  }

                  if (var4 != null) {
                     return var4;
                  } else {
                     var5 = this.getSystemWindowInsets();
                     var4 = this.getRootStableInsets();
                     if (var5.bottom > var4.bottom) {
                        return Insets.of(0, 0, 0, var5.bottom);
                     } else {
                        var5 = this.mRootViewVisibleInsets;
                        return var5 != null && !var5.equals(Insets.NONE) && this.mRootViewVisibleInsets.bottom > var4.bottom ? Insets.of(0, 0, 0, this.mRootViewVisibleInsets.bottom) : Insets.NONE;
                     }
                  }
               }
            } else if (var2) {
               var4 = this.getRootStableInsets();
               var5 = this.getStableInsets();
               return Insets.of(Math.max(var4.left, var5.left), 0, Math.max(var4.right, var5.right), Math.max(var4.bottom, var5.bottom));
            } else {
               var5 = this.getSystemWindowInsets();
               WindowInsetsCompat var6 = this.mRootWindowInsets;
               if (var6 != null) {
                  var4 = var6.getStableInsets();
               }

               int var3 = var5.bottom;
               var1 = var3;
               if (var4 != null) {
                  var1 = Math.min(var3, var4.bottom);
               }

               return Insets.of(var5.left, 0, var5.right, var1);
            }
         } else {
            return var2 ? Insets.of(0, Math.max(this.getRootStableInsets().top, this.getSystemWindowInsets().top), 0, 0) : Insets.of(0, this.getSystemWindowInsets().top, 0, 0);
         }
      }

      public Insets getInsetsIgnoringVisibility(int var1) {
         return this.getInsets(var1, true);
      }

      final Insets getSystemWindowInsets() {
         if (this.mSystemWindowInsets == null) {
            this.mSystemWindowInsets = Insets.of(this.mPlatformInsets.getSystemWindowInsetLeft(), this.mPlatformInsets.getSystemWindowInsetTop(), this.mPlatformInsets.getSystemWindowInsetRight(), this.mPlatformInsets.getSystemWindowInsetBottom());
         }

         return this.mSystemWindowInsets;
      }

      WindowInsetsCompat inset(int var1, int var2, int var3, int var4) {
         Builder var5 = new Builder(WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets));
         var5.setSystemWindowInsets(WindowInsetsCompat.insetInsets(this.getSystemWindowInsets(), var1, var2, var3, var4));
         var5.setStableInsets(WindowInsetsCompat.insetInsets(this.getStableInsets(), var1, var2, var3, var4));
         return var5.build();
      }

      boolean isRound() {
         return this.mPlatformInsets.isRound();
      }

      protected boolean isTypeVisible(int var1) {
         if (var1 != 1 && var1 != 2) {
            if (var1 == 4) {
               return false;
            }

            if (var1 != 8 && var1 != 128) {
               return true;
            }
         }

         return this.getInsetsForType(var1, false).equals(Insets.NONE) ^ true;
      }

      boolean isVisible(int var1) {
         for(int var2 = 1; var2 <= 256; var2 <<= 1) {
            if ((var1 & var2) != 0 && !this.isTypeVisible(var2)) {
               return false;
            }
         }

         return true;
      }

      public void setOverriddenInsets(Insets[] var1) {
         this.mOverriddenInsets = var1;
      }

      void setRootViewData(Insets var1) {
         this.mRootViewVisibleInsets = var1;
      }

      void setRootWindowInsets(WindowInsetsCompat var1) {
         this.mRootWindowInsets = var1;
      }
   }

   private static class Impl21 extends Impl20 {
      private Insets mStableInsets = null;

      Impl21(WindowInsetsCompat var1, WindowInsets var2) {
         super(var1, var2);
      }

      Impl21(WindowInsetsCompat var1, Impl21 var2) {
         super(var1, (Impl20)var2);
         this.mStableInsets = var2.mStableInsets;
      }

      WindowInsetsCompat consumeStableInsets() {
         return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeStableInsets());
      }

      WindowInsetsCompat consumeSystemWindowInsets() {
         return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeSystemWindowInsets());
      }

      final Insets getStableInsets() {
         if (this.mStableInsets == null) {
            this.mStableInsets = Insets.of(this.mPlatformInsets.getStableInsetLeft(), this.mPlatformInsets.getStableInsetTop(), this.mPlatformInsets.getStableInsetRight(), this.mPlatformInsets.getStableInsetBottom());
         }

         return this.mStableInsets;
      }

      boolean isConsumed() {
         return this.mPlatformInsets.isConsumed();
      }

      public void setStableInsets(Insets var1) {
         this.mStableInsets = var1;
      }
   }

   private static class Impl28 extends Impl21 {
      Impl28(WindowInsetsCompat var1, WindowInsets var2) {
         super(var1, var2);
      }

      Impl28(WindowInsetsCompat var1, Impl28 var2) {
         super(var1, (Impl21)var2);
      }

      WindowInsetsCompat consumeDisplayCutout() {
         return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeDisplayCutout());
      }

      public boolean equals(Object var1) {
         boolean var2 = true;
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof Impl28)) {
            return false;
         } else {
            Impl28 var3 = (Impl28)var1;
            if (!Objects.equals(this.mPlatformInsets, var3.mPlatformInsets) || !Objects.equals(this.mRootViewVisibleInsets, var3.mRootViewVisibleInsets)) {
               var2 = false;
            }

            return var2;
         }
      }

      DisplayCutoutCompat getDisplayCutout() {
         return DisplayCutoutCompat.wrap(this.mPlatformInsets.getDisplayCutout());
      }

      public int hashCode() {
         return this.mPlatformInsets.hashCode();
      }
   }

   private static class Impl29 extends Impl28 {
      private Insets mMandatorySystemGestureInsets = null;
      private Insets mSystemGestureInsets = null;
      private Insets mTappableElementInsets = null;

      Impl29(WindowInsetsCompat var1, WindowInsets var2) {
         super(var1, var2);
      }

      Impl29(WindowInsetsCompat var1, Impl29 var2) {
         super(var1, (Impl28)var2);
      }

      Insets getMandatorySystemGestureInsets() {
         if (this.mMandatorySystemGestureInsets == null) {
            this.mMandatorySystemGestureInsets = Insets.toCompatInsets(this.mPlatformInsets.getMandatorySystemGestureInsets());
         }

         return this.mMandatorySystemGestureInsets;
      }

      Insets getSystemGestureInsets() {
         if (this.mSystemGestureInsets == null) {
            this.mSystemGestureInsets = Insets.toCompatInsets(this.mPlatformInsets.getSystemGestureInsets());
         }

         return this.mSystemGestureInsets;
      }

      Insets getTappableElementInsets() {
         if (this.mTappableElementInsets == null) {
            this.mTappableElementInsets = Insets.toCompatInsets(this.mPlatformInsets.getTappableElementInsets());
         }

         return this.mTappableElementInsets;
      }

      WindowInsetsCompat inset(int var1, int var2, int var3, int var4) {
         return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.inset(var1, var2, var3, var4));
      }

      public void setStableInsets(Insets var1) {
      }
   }

   private static class Impl30 extends Impl29 {
      static final WindowInsetsCompat CONSUMED;

      static {
         CONSUMED = WindowInsetsCompat.toWindowInsetsCompat(WindowInsets.CONSUMED);
      }

      Impl30(WindowInsetsCompat var1, WindowInsets var2) {
         super(var1, var2);
      }

      Impl30(WindowInsetsCompat var1, Impl30 var2) {
         super(var1, (Impl29)var2);
      }

      final void copyRootViewBounds(View var1) {
      }

      public Insets getInsets(int var1) {
         return Insets.toCompatInsets(this.mPlatformInsets.getInsets(WindowInsetsCompat.TypeImpl30.toPlatformType(var1)));
      }

      public Insets getInsetsIgnoringVisibility(int var1) {
         return Insets.toCompatInsets(this.mPlatformInsets.getInsetsIgnoringVisibility(WindowInsetsCompat.TypeImpl30.toPlatformType(var1)));
      }

      public boolean isVisible(int var1) {
         return this.mPlatformInsets.isVisible(WindowInsetsCompat.TypeImpl30.toPlatformType(var1));
      }
   }

   public static final class Type {
      static final int CAPTION_BAR = 4;
      static final int DISPLAY_CUTOUT = 128;
      static final int FIRST = 1;
      static final int IME = 8;
      static final int LAST = 256;
      static final int MANDATORY_SYSTEM_GESTURES = 32;
      static final int NAVIGATION_BARS = 2;
      static final int SIZE = 9;
      static final int STATUS_BARS = 1;
      static final int SYSTEM_GESTURES = 16;
      static final int TAPPABLE_ELEMENT = 64;
      static final int WINDOW_DECOR = 256;

      private Type() {
      }

      static int all() {
         return -1;
      }

      public static int captionBar() {
         return 4;
      }

      public static int displayCutout() {
         return 128;
      }

      public static int ime() {
         return 8;
      }

      static int indexOf(int var0) {
         if (var0 != 1) {
            if (var0 != 2) {
               if (var0 != 4) {
                  if (var0 != 8) {
                     if (var0 != 16) {
                        if (var0 != 32) {
                           if (var0 != 64) {
                              if (var0 != 128) {
                                 if (var0 == 256) {
                                    return 8;
                                 } else {
                                    throw new IllegalArgumentException("type needs to be >= FIRST and <= LAST, type=" + var0);
                                 }
                              } else {
                                 return 7;
                              }
                           } else {
                              return 6;
                           }
                        } else {
                           return 5;
                        }
                     } else {
                        return 4;
                     }
                  } else {
                     return 3;
                  }
               } else {
                  return 2;
               }
            } else {
               return 1;
            }
         } else {
            return 0;
         }
      }

      public static int mandatorySystemGestures() {
         return 32;
      }

      public static int navigationBars() {
         return 2;
      }

      public static int statusBars() {
         return 1;
      }

      public static int systemBars() {
         return 7;
      }

      public static int systemGestures() {
         return 16;
      }

      public static int tappableElement() {
         return 64;
      }

      @Retention(RetentionPolicy.SOURCE)
      public @interface InsetsType {
      }
   }

   private static final class TypeImpl30 {
      static int toPlatformType(int var0) {
         int var3 = 0;

         int var1;
         for(int var2 = 1; var2 <= 256; var3 = var1) {
            var1 = var3;
            if ((var0 & var2) != 0) {
               label47: {
                  if (var2 != 1) {
                     if (var2 != 2) {
                        if (var2 != 4) {
                           if (var2 != 8) {
                              if (var2 != 16) {
                                 if (var2 != 32) {
                                    if (var2 != 64) {
                                       if (var2 != 128) {
                                          var1 = var3;
                                          break label47;
                                       }

                                       var1 = android.view.WindowInsets.Type.displayCutout();
                                    } else {
                                       var1 = android.view.WindowInsets.Type.tappableElement();
                                    }
                                 } else {
                                    var1 = android.view.WindowInsets.Type.mandatorySystemGestures();
                                 }
                              } else {
                                 var1 = android.view.WindowInsets.Type.systemGestures();
                              }
                           } else {
                              var1 = android.view.WindowInsets.Type.ime();
                           }
                        } else {
                           var1 = android.view.WindowInsets.Type.captionBar();
                        }
                     } else {
                        var1 = android.view.WindowInsets.Type.navigationBars();
                     }
                  } else {
                     var1 = android.view.WindowInsets.Type.statusBars();
                  }

                  var1 |= var3;
               }
            }

            var2 <<= 1;
         }

         return var3;
      }
   }
}
