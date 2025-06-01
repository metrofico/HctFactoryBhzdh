package com.hzbhd.canbus.car._403;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005¨\u0006\b"},
   d2 = {"radioAscii", "", "index", "", "band", "", "size", "freq", "CanBusInfo_release"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgrKt {
   public static final byte[] radioAscii(int var0, String var1, int var2, String var3) {
      byte[] var9;
      label62: {
         byte var4;
         byte var6;
         byte[] var10;
         label63: {
            Intrinsics.checkNotNullParameter(var1, "band");
            Intrinsics.checkNotNullParameter(var3, "freq");
            int var7 = var1.hashCode();
            byte var5 = 0;
            var6 = 0;
            switch (var7) {
               case 64901:
                  if (!var1.equals("AM1")) {
                     break label62;
                  }
                  break;
               case 64902:
                  if (!var1.equals("AM2")) {
                     break label62;
                  }
                  break;
               case 69706:
                  if (!var1.equals("FM1")) {
                     break label62;
                  }
                  break label63;
               case 69707:
                  if (!var1.equals("FM2")) {
                     break label62;
                  }
                  break label63;
               case 69708:
                  if (!var1.equals("FM3")) {
                     break label62;
                  }
                  break label63;
               default:
                  break label62;
            }

            var9 = var3.getBytes(Charsets.US_ASCII);
            Intrinsics.checkNotNullExpressionValue(var9, "this as java.lang.String).getBytes(charset)");
            var4 = (byte)var0;
            var2 = var2 - 2 - 5;
            var10 = new byte[var2];

            for(var0 = var5; var0 < var2; ++var0) {
               var10[var0] = 32;
            }

            var9 = ArraysKt.plus(ArraysKt.plus(new byte[]{0, var4}, var10), com.hzbhd.canbus.car._361.MsgMgrKt.restrict(var9, 5, 32));
            return var9;
         }

         Double var8 = StringsKt.toDoubleOrNull(var3);
         Intrinsics.checkNotNull(var8);
         var9 = String.valueOf(com.hzbhd.canbus.car._369.MsgMgrKt.accurateTo(var8, 10)).getBytes(Charsets.US_ASCII);
         Intrinsics.checkNotNullExpressionValue(var9, "this as java.lang.String).getBytes(charset)");
         var4 = (byte)var0;
         var2 = var2 - 2 - var9.length;
         var10 = new byte[var2];

         for(var0 = var6; var0 < var2; ++var0) {
            var10[var0] = 32;
         }

         var9 = ArraysKt.plus(ArraysKt.plus(new byte[]{0, var4}, var10), var9);
         return var9;
      }

      var9 = new byte[0];
      return var9;
   }
}
