package androidx.core.view;

import android.app.UiModeManager;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.Display;
import androidx.core.util.Preconditions;

public final class DisplayCompat {
   private static final int DISPLAY_SIZE_4K_HEIGHT = 2160;
   private static final int DISPLAY_SIZE_4K_WIDTH = 3840;

   private DisplayCompat() {
   }

   static Point getCurrentDisplaySizeFromWorkarounds(Context var0, Display var1) {
      Point var3;
      if (VERSION.SDK_INT < 28) {
         var3 = parsePhysicalDisplaySizeFromSystemProperties("sys.display-size", var1);
      } else {
         var3 = parsePhysicalDisplaySizeFromSystemProperties("vendor.display-size", var1);
      }

      if (var3 != null) {
         return var3;
      } else {
         boolean var2 = isSonyBravia4kTv(var0);
         var3 = null;
         Point var4 = var3;
         if (var2) {
            var4 = var3;
            if (isCurrentModeTheLargestMode(var1)) {
               var4 = new Point(3840, 2160);
            }
         }

         return var4;
      }
   }

   private static Point getDisplaySize(Context var0, Display var1) {
      Point var2 = getCurrentDisplaySizeFromWorkarounds(var0, var1);
      if (var2 != null) {
         return var2;
      } else {
         var2 = new Point();
         if (VERSION.SDK_INT >= 17) {
            var1.getRealSize(var2);
         } else {
            var1.getSize(var2);
         }

         return var2;
      }
   }

   public static ModeCompat getMode(Context var0, Display var1) {
      return VERSION.SDK_INT >= 23 ? DisplayCompat.Api23Impl.getMode(var0, var1) : new ModeCompat(getDisplaySize(var0, var1));
   }

   public static ModeCompat[] getSupportedModes(Context var0, Display var1) {
      return VERSION.SDK_INT >= 23 ? DisplayCompat.Api23Impl.getSupportedModes(var0, var1) : new ModeCompat[]{getMode(var0, var1)};
   }

   private static String getSystemProperty(String var0) {
      try {
         Class var1 = Class.forName("android.os.SystemProperties");
         var0 = (String)var1.getMethod("get", String.class).invoke(var1, var0);
         return var0;
      } catch (Exception var2) {
         return null;
      }
   }

   static boolean isCurrentModeTheLargestMode(Display var0) {
      return VERSION.SDK_INT >= 23 ? DisplayCompat.Api23Impl.isCurrentModeTheLargestMode(var0) : true;
   }

   private static boolean isSonyBravia4kTv(Context var0) {
      boolean var1;
      if (isTv(var0) && "Sony".equals(Build.MANUFACTURER) && Build.MODEL.startsWith("BRAVIA") && var0.getPackageManager().hasSystemFeature("com.sony.dtv.hardware.panel.qfhd")) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private static boolean isTv(Context var0) {
      UiModeManager var2 = (UiModeManager)var0.getSystemService("uimode");
      boolean var1;
      if (var2 != null && var2.getCurrentModeType() == 4) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private static Point parseDisplaySize(String var0) throws NumberFormatException {
      String[] var3 = var0.trim().split("x", -1);
      if (var3.length == 2) {
         int var1 = Integer.parseInt(var3[0]);
         int var2 = Integer.parseInt(var3[1]);
         if (var1 > 0 && var2 > 0) {
            return new Point(var1, var2);
         }
      }

      throw new NumberFormatException();
   }

   private static Point parsePhysicalDisplaySizeFromSystemProperties(String var0, Display var1) {
      if (var1.getDisplayId() != 0) {
         return null;
      } else {
         var0 = getSystemProperty(var0);
         if (TextUtils.isEmpty(var0)) {
            return null;
         } else {
            try {
               Point var3 = parseDisplaySize(var0);
               return var3;
            } catch (NumberFormatException var2) {
               return null;
            }
         }
      }
   }

   static class Api17Impl {
      private Api17Impl() {
      }

      static void getRealSize(Display var0, Point var1) {
         var0.getRealSize(var1);
      }
   }

   static class Api23Impl {
      private Api23Impl() {
      }

      static ModeCompat getMode(Context var0, Display var1) {
         Display.Mode var2 = var1.getMode();
         Point var3 = DisplayCompat.getCurrentDisplaySizeFromWorkarounds(var0, var1);
         ModeCompat var4;
         if (var3 != null && !physicalSizeEquals(var2, var3)) {
            var4 = new ModeCompat(var2, var3);
         } else {
            var4 = new ModeCompat(var2, true);
         }

         return var4;
      }

      public static ModeCompat[] getSupportedModes(Context var0, Display var1) {
         Display.Mode[] var7 = var1.getSupportedModes();
         ModeCompat[] var5 = new ModeCompat[var7.length];
         Display.Mode var6 = var1.getMode();
         Point var9 = DisplayCompat.getCurrentDisplaySizeFromWorkarounds(var0, var1);
         byte var3 = 0;
         int var2 = var3;
         if (var9 != null) {
            if (!physicalSizeEquals(var6, var9)) {
               for(var2 = 0; var2 < var7.length; ++var2) {
                  ModeCompat var8;
                  if (physicalSizeEquals(var7[var2], var6)) {
                     var8 = new ModeCompat(var7[var2], var9);
                  } else {
                     var8 = new ModeCompat(var7[var2], false);
                  }

                  var5[var2] = var8;
               }

               return var5;
            }

            var2 = var3;
         }

         while(var2 < var7.length) {
            boolean var4 = physicalSizeEquals(var7[var2], var6);
            var5[var2] = new ModeCompat(var7[var2], var4);
            ++var2;
         }

         return var5;
      }

      static boolean isCurrentModeTheLargestMode(Display var0) {
         Display.Mode var2 = var0.getMode();
         Display.Mode[] var3 = var0.getSupportedModes();

         for(int var1 = 0; var1 < var3.length; ++var1) {
            if (var2.getPhysicalHeight() < var3[var1].getPhysicalHeight() || var2.getPhysicalWidth() < var3[var1].getPhysicalWidth()) {
               return false;
            }
         }

         return true;
      }

      static boolean physicalSizeEquals(Display.Mode var0, Point var1) {
         boolean var2;
         if ((var0.getPhysicalWidth() != var1.x || var0.getPhysicalHeight() != var1.y) && (var0.getPhysicalWidth() != var1.y || var0.getPhysicalHeight() != var1.x)) {
            var2 = false;
         } else {
            var2 = true;
         }

         return var2;
      }

      static boolean physicalSizeEquals(Display.Mode var0, Display.Mode var1) {
         boolean var2;
         if (var0.getPhysicalWidth() == var1.getPhysicalWidth() && var0.getPhysicalHeight() == var1.getPhysicalHeight()) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }
   }

   public static final class ModeCompat {
      private final boolean mIsNative;
      private final Display.Mode mMode;
      private final Point mPhysicalSize;

      ModeCompat(Point var1) {
         Preconditions.checkNotNull(var1, "physicalSize == null");
         this.mPhysicalSize = var1;
         this.mMode = null;
         this.mIsNative = true;
      }

      ModeCompat(Display.Mode var1, Point var2) {
         Preconditions.checkNotNull(var1, "mode == null, can't wrap a null reference");
         Preconditions.checkNotNull(var2, "physicalSize == null");
         this.mPhysicalSize = var2;
         this.mMode = var1;
         this.mIsNative = true;
      }

      ModeCompat(Display.Mode var1, boolean var2) {
         Preconditions.checkNotNull(var1, "mode == null, can't wrap a null reference");
         this.mPhysicalSize = new Point(var1.getPhysicalWidth(), var1.getPhysicalHeight());
         this.mMode = var1;
         this.mIsNative = var2;
      }

      public int getPhysicalHeight() {
         return this.mPhysicalSize.y;
      }

      public int getPhysicalWidth() {
         return this.mPhysicalSize.x;
      }

      @Deprecated
      public boolean isNative() {
         return this.mIsNative;
      }

      public Display.Mode toMode() {
         return this.mMode;
      }
   }
}
