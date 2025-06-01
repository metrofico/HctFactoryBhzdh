package com.hzbhd.canbus.car._341;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003\u001a\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006\u001a\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006\u001a\u0015\u0010\u000b\u001a\u00020\f*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\u0004¨\u0006\r"},
   d2 = {"contentCompare", "", "one", "", "anotherOne", "getMsbLsbResult", "", "MSB", "LSB", "reverse", "param", "transTo", "", "CanBusInfo_release"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgrKt {
   public static final boolean contentCompare(int[] var0, int[] var1) {
      Intrinsics.checkNotNullParameter(var0, "one");
      Intrinsics.checkNotNullParameter(var1, "anotherOne");
      return Arrays.equals(var0, var1);
   }

   public static final int getMsbLsbResult(int var0, int var1) {
      return (var0 & 255) << 8 | var1 & 255;
   }

   public static final int reverse(int var0) {
      byte var4 = 0;
      int var1 = 14;
      int var2 = 0;

      int var3;
      while(true) {
         var3 = var4;
         if (-1 >= var1) {
            break;
         }

         if (var2 == var0) {
            var3 = var1;
            break;
         }

         --var1;
         ++var2;
      }

      return var3;
   }

   public static final void transTo(int[] var0, int[] var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "one");
      int var3 = var0.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         var1[var2] = var0[var2];
      }

   }
}
