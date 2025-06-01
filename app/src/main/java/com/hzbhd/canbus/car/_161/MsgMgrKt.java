package com.hzbhd.canbus.car._161;

import kotlin.Metadata;

@Metadata(
   d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0002¨\u0006\u0003"},
   d2 = {"range", "", "max", "CanBusInfo_release"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgrKt {
   // $FF: synthetic method
   public static final int access$range(int var0, int var1) {
      return range(var0, var1);
   }

   private static final int range(int var0, int var1) {
      int var2 = var0;
      if (var0 > var1) {
         var2 = var1;
      }

      return var2;
   }
}
