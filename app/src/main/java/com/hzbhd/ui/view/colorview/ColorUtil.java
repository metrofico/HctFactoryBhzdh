package com.hzbhd.ui.view.colorview;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.System;
import android.util.Log;
import android.widget.TextView;
import com.hzbhd.config.use.UIDefault;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u0000 *2\u00020\u0001:\u0002)*B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0007J\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0005J\u000e\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\fJ\u000e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\fJ\u000e\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0005J.\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0007J&\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0007J\u0016\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"J\u0018\u0010#\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0007H\u0002J(\u0010#\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0007H\u0002J0\u0010#\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0007H\u0002J\u0016\u0010(\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\"R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"},
   d2 = {"Lcom/hzbhd/ui/view/colorview/ColorUtil;", "", "()V", "colorViewInterfaces", "Ljava/util/ArrayList;", "Lcom/hzbhd/ui/view/colorview/ColorUtil$ColorViewInterface;", "currColor_n", "", "currColor_p", "currTextColor_n", "currTextColor_p", "mContext", "Landroid/content/Context;", "BackGroundChangeColor", "", "bg", "Landroid/graphics/drawable/Drawable;", "color", "addColorInterface", "colorViewInterface", "getsaveColor", "context", "init", "removeColorInterface", "saveColor", "color_n", "color_p", "textColor_n", "textColor_p", "setCurrColor", "textviewStateCahnge", "textView", "Landroid/widget/TextView;", "isPress", "", "viewBackGroundChange", "r", "g", "b", "a", "viewStateChange", "ColorViewInterface", "Companion", "commonview-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ColorUtil {
   private static final int COLOR_A2 = -14072952;
   public static final String COLOR_TEXT_VIEW_COLOR_N = "color_text_view_color_n";
   public static final String COLOR_TEXT_VIEW_COLOR_P = "color_text_view_color_p";
   public static final String COLOR_VIEW_COLOR_N = "color_view_color_n";
   public static final String COLOR_VIEW_COLOR_P = "color_view_color_p";
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private static final int DEFAULT_COLOR;
   private static final int brightness = 63;
   public static final ColorUtil instance = new ColorUtil();
   private static final float saturation = 0.0F;
   private static final float saturation2 = 0.0F;
   private final ArrayList colorViewInterfaces = new ArrayList();
   private int currColor_n;
   private int currColor_p;
   private int currTextColor_n;
   private int currTextColor_p;
   private Context mContext;

   static {
      DEFAULT_COLOR = UIDefault.INSTANCE.getColorViewColor();
   }

   public ColorUtil() {
      int var1 = DEFAULT_COLOR;
      this.currColor_n = var1;
      this.currColor_p = var1;
      this.currTextColor_n = -1;
      this.currTextColor_p = -16777216;
   }

   private final void viewBackGroundChange(Drawable var1, int var2) {
      this.viewBackGroundChange(var1, (16711680 & var2) >> 16, ('\uff00' & var2) >> 8, var2 & 255);
   }

   private final void viewBackGroundChange(Drawable var1, int var2, int var3, int var4) {
      ColorMatrix var5 = new ColorMatrix();
      var5.set(new float[]{1.0F, 0.0F, 0.0F, 0.0F, (float)(var2 - 63), 0.0F, 1.0F, 0.0F, 0.0F, (float)(var3 - 63), 0.0F, 0.0F, 1.0F, 0.0F, (float)(var4 - 63), 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F});
      var1.setColorFilter((ColorFilter)(new ColorMatrixColorFilter(var5)));
   }

   private final void viewBackGroundChange(Drawable var1, int var2, int var3, int var4, int var5) {
      Log.d("sswang", "viewBackGroundChange: " + var2 + " , " + var3 + " , " + var4 + " , " + var5);
      ColorMatrix var6 = new ColorMatrix();
      var6.set(new float[]{1.0F, 0.0F, 0.0F, 0.0F, (float)(var3 - 63), 0.0F, 1.0F, 0.0F, 0.0F, (float)(var4 - 63), 0.0F, 0.0F, 1.0F, 0.0F, (float)(var5 - 63), 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, (float)var2});
      var1.setColorFilter((ColorFilter)(new ColorMatrixColorFilter(var6)));
   }

   public final void BackGroundChangeColor(Drawable var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "bg");
      this.viewBackGroundChange(var1, (-16777216 & var2) >> 24, (16711680 & var2) >> 16, ('\uff00' & var2) >> 8, var2 & 255);
   }

   public final void addColorInterface(ColorViewInterface var1) {
      Intrinsics.checkNotNullParameter(var1, "colorViewInterface");
      if (!this.colorViewInterfaces.contains(var1)) {
         this.colorViewInterfaces.add(var1);
      }

   }

   public final int getsaveColor(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      return System.getInt(var1.getContentResolver(), "color_view_color_n", DEFAULT_COLOR);
   }

   public final void init(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      this.mContext = var1;
      Intrinsics.checkNotNull(var1);
      ContentResolver var6 = var1.getContentResolver();
      int var2 = DEFAULT_COLOR;
      int var3 = System.getInt(var6, "color_view_color_n", var2);
      Context var7 = this.mContext;
      Intrinsics.checkNotNull(var7);
      int var4 = System.getInt(var7.getContentResolver(), "color_view_color_p", var2);
      var7 = this.mContext;
      Intrinsics.checkNotNull(var7);
      int var5 = System.getInt(var7.getContentResolver(), "color_text_view_color_n", var2);
      var7 = this.mContext;
      Intrinsics.checkNotNull(var7);
      this.setCurrColor(var3, var4, var5, System.getInt(var7.getContentResolver(), "color_text_view_color_p", var2));
      var1.getContentResolver().registerContentObserver(System.getUriFor("color_view_color_n"), false, (ContentObserver)(new ContentObserver(this, new Handler(Looper.getMainLooper())) {
         final ColorUtil this$0;

         {
            this.this$0 = var1;
         }

         public void onChange(boolean var1) {
            Context var6 = this.this$0.mContext;
            Intrinsics.checkNotNull(var6);
            int var2 = System.getInt(var6.getContentResolver(), "color_view_color_n", ColorUtil.DEFAULT_COLOR);
            var6 = this.this$0.mContext;
            Intrinsics.checkNotNull(var6);
            int var3 = System.getInt(var6.getContentResolver(), "color_view_color_p", ColorUtil.DEFAULT_COLOR);
            var6 = this.this$0.mContext;
            Intrinsics.checkNotNull(var6);
            int var4 = System.getInt(var6.getContentResolver(), "color_text_view_color_n", ColorUtil.DEFAULT_COLOR);
            var6 = this.this$0.mContext;
            Intrinsics.checkNotNull(var6);
            int var5 = System.getInt(var6.getContentResolver(), "color_text_view_color_p", ColorUtil.DEFAULT_COLOR);
            this.this$0.setCurrColor(var2, var3, var4, var5);
         }
      }));
   }

   public final void removeColorInterface(ColorViewInterface var1) {
      Intrinsics.checkNotNullParameter(var1, "colorViewInterface");
      if (!this.colorViewInterfaces.contains(var1)) {
         this.colorViewInterfaces.remove(var1);
      }

   }

   public final void saveColor(Context var1, int var2, int var3, int var4, int var5) {
      Intrinsics.checkNotNullParameter(var1, "context");
      System.putInt(var1.getContentResolver(), "color_view_color_n", var2);
      System.putInt(var1.getContentResolver(), "color_view_color_p", var3);
      System.putInt(var1.getContentResolver(), "color_text_view_color_n", var4);
      System.putInt(var1.getContentResolver(), "color_text_view_color_p", var5);
   }

   public final void setCurrColor(int var1, int var2, int var3, int var4) {
      this.currColor_n = var1;
      this.currColor_p = var2;
      this.currTextColor_n = var3;
      this.currTextColor_p = var4;
      var2 = this.colorViewInterfaces.size();

      for(var1 = 0; var1 < var2; ++var1) {
         ((ColorViewInterface)this.colorViewInterfaces.get(var1)).onColorChange();
      }

   }

   public final void textviewStateCahnge(TextView var1, boolean var2) {
      Intrinsics.checkNotNullParameter(var1, "textView");
      if (var2) {
         var1.setTextColor(this.currTextColor_p);
      } else {
         var1.setTextColor(this.currTextColor_n);
      }

   }

   public final void viewStateChange(Drawable var1, boolean var2) {
      Intrinsics.checkNotNullParameter(var1, "bg");
      if (var2) {
         this.viewBackGroundChange(var1, instance.currColor_p);
      } else {
         this.viewBackGroundChange(var1, instance.currColor_n);
      }

   }

   @Metadata(
      d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"},
      d2 = {"Lcom/hzbhd/ui/view/colorview/ColorUtil$ColorViewInterface;", "", "onColorChange", "", "commonview-base_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public interface ColorViewInterface {
      void onColorChange();
   }

   @Metadata(
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0004J\u000e\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"},
      d2 = {"Lcom/hzbhd/ui/view/colorview/ColorUtil$Companion;", "", "()V", "COLOR_A2", "", "COLOR_TEXT_VIEW_COLOR_N", "", "COLOR_TEXT_VIEW_COLOR_P", "COLOR_VIEW_COLOR_N", "COLOR_VIEW_COLOR_P", "DEFAULT_COLOR", "brightness", "instance", "Lcom/hzbhd/ui/view/colorview/ColorUtil;", "saturation", "", "saturation2", "convertToARGB", "color", "convertToRGB", "commonview-base_release"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }

      public final String convertToARGB(int var1) {
         String var3 = Integer.toHexString(Color.alpha(var1));
         String var4 = Integer.toHexString(Color.red(var1));
         String var5 = Integer.toHexString(Color.green(var1));
         String var6 = Integer.toHexString(Color.blue(var1));
         String var2 = var3;
         if (var3.length() == 1) {
            var2 = '0' + var3;
         }

         var3 = var4;
         if (var4.length() == 1) {
            var3 = '0' + var4;
         }

         var4 = var5;
         if (var5.length() == 1) {
            var4 = '0' + var5;
         }

         var5 = var6;
         if (var6.length() == 1) {
            var5 = '0' + var6;
         }

         return '#' + var2 + var3 + var4 + var5;
      }

      public final String convertToRGB(int var1) {
         String var3 = Integer.toHexString(Color.red(var1));
         String var4 = Integer.toHexString(Color.green(var1));
         String var5 = Integer.toHexString(Color.blue(var1));
         String var2 = var3;
         if (var3.length() == 1) {
            var2 = '0' + var3;
         }

         var3 = var4;
         if (var4.length() == 1) {
            var3 = '0' + var4;
         }

         var4 = var5;
         if (var5.length() == 1) {
            var4 = '0' + var5;
         }

         return '#' + var2 + var3 + var4;
      }
   }
}
