package com.hzbhd.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.WindowManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J&\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u00182\u0006\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006&"},
   d2 = {"Lcom/hzbhd/util/ContextUtil;", "", "()V", "PX_X_MAX", "", "PX_Y_MAX", "maxSize", "Landroid/graphics/PointF;", "getMaxSize", "()Landroid/graphics/PointF;", "maxSize$delegate", "Lkotlin/Lazy;", "screenSize", "Landroid/graphics/Point;", "getScreenSize", "()Landroid/graphics/Point;", "screenSize$delegate", "textColor", "", "getTextColor", "()J", "setTextColor", "(J)V", "textSize", "", "getTextSize", "()I", "setTextSize", "(I)V", "init", "", "context", "Landroid/content/Context;", "setSize", "x", "y", "xMax", "yMax", "thread_util_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class ContextUtil {
   public static final ContextUtil INSTANCE = new ContextUtil();
   public static final float PX_X_MAX = 1920.0F;
   public static final float PX_Y_MAX = 1080.0F;
   private static final Lazy maxSize$delegate;
   private static final Lazy screenSize$delegate;
   private static long textColor;
   private static int textSize;

   static {
      screenSize$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      maxSize$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      textColor = 4294967295L;
      textSize = 18;
   }

   private ContextUtil() {
   }

   public final PointF getMaxSize() {
      return (PointF)maxSize$delegate.getValue();
   }

   public final Point getScreenSize() {
      return (Point)screenSize$delegate.getValue();
   }

   public final long getTextColor() {
      return textColor;
   }

   public final int getTextSize() {
      return textSize;
   }

   public final void init(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      SharedPreferences var2 = var1.getSharedPreferences("share", 0);
      Intrinsics.checkNotNullExpressionValue(var2, "context.getSharedPrefere…e\", Context.MODE_PRIVATE)");
      ContextUtilKt.setBaseShare(var2);
      ContextUtilKt.setBaseContext(var1);
      ((WindowManager)var1.getSystemService(WindowManager.class)).getDefaultDisplay().getRealSize(this.getScreenSize());
   }

   public final void setSize(int var1, int var2, int var3, int var4) {
      this.getScreenSize().x = var1;
      this.getScreenSize().y = var2;
      this.getMaxSize().x = (float)var3;
      this.getMaxSize().y = (float)var4;
   }

   public final void setTextColor(long var1) {
      textColor = var1;
   }

   public final void setTextSize(int var1) {
      textSize = var1;
   }
}
