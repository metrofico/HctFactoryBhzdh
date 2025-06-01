package kotlin.internal;

import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(
   d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0001\u001a\"\u0010\b\u001a\u0002H\t\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0083\b¢\u0006\u0002\u0010\f\u001a\b\u0010\r\u001a\u00020\u0005H\u0002\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"},
   d2 = {"IMPLEMENTATIONS", "Lkotlin/internal/PlatformImplementations;", "apiVersionIsAtLeast", "", "major", "", "minor", "patch", "castToBaseType", "T", "", "instance", "(Ljava/lang/Object;)Ljava/lang/Object;", "getJavaVersion", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class PlatformImplementationsKt {
   public static final PlatformImplementations IMPLEMENTATIONS;

   static {
      // $FF: Couldn't be decompiled
   }

   public static final boolean apiVersionIsAtLeast(int var0, int var1, int var2) {
      return KotlinVersion.CURRENT.isAtLeast(var0, var1, var2);
   }

   // $FF: synthetic method
   private static final Object castToBaseType(Object var0) {
      try {
         Intrinsics.reifiedOperationMarker(1, "T");
         Object var1 = (Object)var0;
         return var0;
      } catch (ClassCastException var3) {
         ClassLoader var4 = var0.getClass().getClassLoader();
         Intrinsics.reifiedOperationMarker(4, "T");
         Class var2 = (Class)Object.class;
         ClassLoader var5 = Object.class.getClassLoader();
         if (!Intrinsics.areEqual((Object)var4, (Object)var5)) {
            throw new ClassNotFoundException("Instance class was loaded from a different classloader: " + var4 + ", base type classloader: " + var5, (Throwable)var3);
         } else {
            throw var3;
         }
      }
   }

   private static final int getJavaVersion() {
      String var6 = System.getProperty("java.specification.version");
      int var0 = 65542;
      if (var6 == null) {
         return 65542;
      } else {
         CharSequence var5 = (CharSequence)var6;
         int var4 = StringsKt.indexOf$default(var5, '.', 0, false, 6, (Object)null);
         int var1;
         if (var4 < 0) {
            try {
               var1 = Integer.parseInt(var6);
            } catch (NumberFormatException var7) {
               return var0;
            }

            var0 = var1 * 65536;
            return var0;
         } else {
            int var3 = var4 + 1;
            int var2 = StringsKt.indexOf$default(var5, '.', var3, false, 4, (Object)null);
            var1 = var2;
            if (var2 < 0) {
               var1 = var6.length();
            }

            String var9 = var6.substring(0, var4);
            Intrinsics.checkNotNullExpressionValue(var9, "this as java.lang.String…ing(startIndex, endIndex)");
            var6 = var6.substring(var3, var1);
            Intrinsics.checkNotNullExpressionValue(var6, "this as java.lang.String…ing(startIndex, endIndex)");

            try {
               var1 = Integer.parseInt(var9);
               var2 = Integer.parseInt(var6);
            } catch (NumberFormatException var8) {
               return var0;
            }

            var0 = var1 * 65536 + var2;
            return var0;
         }
      }
   }
}
