package com.hzbhd.canbus.car._314;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0015\n\u0002\u0010\u0012\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"},
   d2 = {"toIntArray", "", "", "CanBusInfo_release"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgrKt {
   public static final int[] toIntArray(byte[] var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      int var2 = var0.length;
      int[] var3 = new int[var2];

      for(int var1 = 0; var1 < var2; ++var1) {
         var3[var1] = var0[var1];
      }

      return var3;
   }
}
