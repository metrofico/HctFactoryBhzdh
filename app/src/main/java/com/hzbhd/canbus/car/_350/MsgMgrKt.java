package com.hzbhd.canbus.car._350;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000&\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0010\u0006\n\u0002\b\u0002\u001a(\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001a\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a\n\u0010\n\u001a\u00020\u000b*\u00020\f\u001a\n\u0010\r\u001a\u00020\u000b*\u00020\f¨\u0006\u000e"},
   d2 = {"isEqual", "", "T", "first", "", "second", "reverse", "", "param", "max", "transToC", "", "", "transToF", "CanBusInfo_release"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class MsgMgrKt {
   public static final boolean isEqual(List var0, List var1) {
      Intrinsics.checkNotNullParameter(var0, "first");
      Intrinsics.checkNotNullParameter(var1, "second");
      int var3 = var0.size();
      int var2 = var1.size();
      boolean var4 = false;
      if (var3 != var2) {
         return false;
      } else {
         Iterable var5 = (Iterable)CollectionsKt.zip((Iterable)var0, (Iterable)var1);
         if (!(var5 instanceof Collection) || !((Collection)var5).isEmpty()) {
            Iterator var6 = var5.iterator();

            while(var6.hasNext()) {
               Pair var7 = (Pair)var6.next();
               if (!Intrinsics.areEqual(var7.component1(), var7.component2())) {
                  return var4;
               }
            }
         }

         var4 = true;
         return var4;
      }
   }

   public static final int reverse(int var0, int var1) {
      byte var4 = 0;
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

   // $FF: synthetic method
   public static int reverse$default(int var0, int var1, int var2, Object var3) {
      if ((var2 & 2) != 0) {
         var1 = 14;
      }

      return reverse(var0, var1);
   }

   public static final String transToC(double var0) {
      StringBuilder var4 = new StringBuilder();
      double var2 = (double)10;
      return var4.append(Math.rint(var0 * var2) / var2).append(" °C").toString();
   }

   public static final String transToF(double var0) {
      StringBuilder var6 = new StringBuilder();
      double var2 = (double)32;
      double var4 = (double)10;
      return var6.append(Math.rint((var0 * 1.8 + var2) * var4) / var4).append(" °F").toString();
   }
}
