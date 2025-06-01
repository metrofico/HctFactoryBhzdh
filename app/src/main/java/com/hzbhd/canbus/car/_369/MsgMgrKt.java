package com.hzbhd.canbus.car._369;

import android.content.Context;
import com.hzbhd.canbus.util.InitUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n\u001a\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e\u001a\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e\u001a\u000e\u0010\u0010\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e\"\u001a\u0010\u0000\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005¨\u0006\u0011"},
   d2 = {"lastKnobValue", "", "getLastKnobValue", "()B", "setLastKnobValue", "(B)V", "accurateTo", "", "double", "digits", "", "getOpenOrClose", "", "boolean", "", "getValidOrInvalid", "getValueOfBoolean", "CanBusInfo_release"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgrKt {
   private static byte lastKnobValue;

   public static final double accurateTo(double var0, int var2) {
      double var3 = (double)var2;
      return Math.rint(var0 * var3) / var3;
   }

   public static final byte getLastKnobValue() {
      return lastKnobValue;
   }

   public static final String getOpenOrClose(boolean var0) {
      Context var2 = InitUtilsKt.getMContext();
      int var1;
      if (var0) {
         var1 = 2131769504;
      } else {
         var1 = 2131768042;
      }

      String var3 = var2.getString(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "mContext.run {\n    if (b…tString(R.string.close)\n}");
      return var3;
   }

   public static final String getValidOrInvalid(boolean var0) {
      Context var2 = InitUtilsKt.getMContext();
      int var1;
      if (var0) {
         var1 = 2131770263;
      } else {
         var1 = 2131768909;
      }

      String var3 = var2.getString(var1);
      Intrinsics.checkNotNullExpressionValue(var3, "mContext.run {\n    if (b…tring(R.string.invalid)\n}");
      return var3;
   }

   public static final int getValueOfBoolean(boolean var0) {
      return var0;
   }

   public static final void setLastKnobValue(byte var0) {
      lastKnobValue = var0;
   }
}
