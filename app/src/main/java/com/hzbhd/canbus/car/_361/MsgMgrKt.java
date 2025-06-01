package com.hzbhd.canbus.car._361;

import com.hzbhd.canbus.CanbusMsgSender;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001\u001a \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\u0001\u001a \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00012\b\b\u0002\u0010\u000e\u001a\u00020\u0006¨\u0006\u000f"},
   d2 = {"getAnotherMsbLsbResult", "", "MSB_16", "MSB_8", "LSB", "restrict", "", "param", "max", "default", "sendMediaInfo", "", "data0", "data1", "data2_7", "CanBusInfo_release"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgrKt {
   public static final int getAnotherMsbLsbResult(int var0, int var1, int var2) {
      return var0 << 16 | var1 << 8 | var2;
   }

   public static final byte[] restrict(byte[] var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "param");
      int var4 = var0.length;
      byte var3 = 0;
      if (var4 > var1) {
         var0 = ArraysKt.copyOfRange(var0, 0, var1);
      } else {
         var4 = var1 - var0.length;
         byte[] var5 = new byte[var4];

         for(var1 = var3; var1 < var4; ++var1) {
            var5[var1] = (byte)var2;
         }

         var0 = ArraysKt.plus(var0, var5);
      }

      return var0;
   }

   // $FF: synthetic method
   public static byte[] restrict$default(byte[] var0, int var1, int var2, int var3, Object var4) {
      if ((var3 & 4) != 0) {
         var2 = 0;
      }

      return restrict(var0, var1, var2);
   }

   public static final void sendMediaInfo(int var0, int var1, byte[] var2) {
      Intrinsics.checkNotNullParameter(var2, "data2_7");
      CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -126, (byte)var0, (byte)var1}, var2));
   }

   // $FF: synthetic method
   public static void sendMediaInfo$default(int var0, int var1, byte[] var2, int var3, Object var4) {
      if ((var3 & 4) != 0) {
         var2 = new byte[6];

         for(var3 = 0; var3 < 6; ++var3) {
            var2[var3] = 0;
         }
      }

      sendMediaInfo(var0, var1, var2);
   }
}
