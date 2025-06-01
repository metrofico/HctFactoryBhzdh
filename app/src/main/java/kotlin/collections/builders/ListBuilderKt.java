package kotlin.collections.builders;

import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a!\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¢\u0006\u0002\u0010\u0005\u001a+\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0001\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00012\u0006\u0010\b\u001a\u00020\u0004H\u0000¢\u0006\u0002\u0010\t\u001a%\u0010\n\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\f\u001a\u00020\u0004H\u0000¢\u0006\u0002\u0010\r\u001a-\u0010\u000e\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0000¢\u0006\u0002\u0010\u0011\u001a9\u0010\u0012\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00012\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u0017H\u0002¢\u0006\u0002\u0010\u0018\u001a-\u0010\u0019\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00012\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010\u001a\u001a/\u0010\u001b\u001a\u00020\u001c\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u00012\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010\u001d¨\u0006\u001e"},
   d2 = {"arrayOfUninitializedElements", "", "E", "size", "", "(I)[Ljava/lang/Object;", "copyOfUninitializedElements", "T", "newSize", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "resetAt", "", "index", "([Ljava/lang/Object;I)V", "resetRange", "fromIndex", "toIndex", "([Ljava/lang/Object;II)V", "subarrayContentEquals", "", "offset", "length", "other", "", "([Ljava/lang/Object;IILjava/util/List;)Z", "subarrayContentHashCode", "([Ljava/lang/Object;II)I", "subarrayContentToString", "", "([Ljava/lang/Object;II)Ljava/lang/String;", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class ListBuilderKt {
   // $FF: synthetic method
   public static final boolean access$subarrayContentEquals(Object[] var0, int var1, int var2, List var3) {
      return subarrayContentEquals(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static final int access$subarrayContentHashCode(Object[] var0, int var1, int var2) {
      return subarrayContentHashCode(var0, var1, var2);
   }

   // $FF: synthetic method
   public static final String access$subarrayContentToString(Object[] var0, int var1, int var2) {
      return subarrayContentToString(var0, var1, var2);
   }

   public static final Object[] arrayOfUninitializedElements(int var0) {
      boolean var1;
      if (var0 >= 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      if (var1) {
         return new Object[var0];
      } else {
         throw new IllegalArgumentException("capacity must be non-negative.".toString());
      }
   }

   public static final Object[] copyOfUninitializedElements(Object[] var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0 = Arrays.copyOf(var0, var1);
      Intrinsics.checkNotNullExpressionValue(var0, "copyOf(this, newSize)");
      Intrinsics.checkNotNull(var0, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.builders.ListBuilderKt.copyOfUninitializedElements>");
      return var0;
   }

   public static final void resetAt(Object[] var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      var0[var1] = null;
   }

   public static final void resetRange(Object[] var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");

      while(var1 < var2) {
         resetAt(var0, var1);
         ++var1;
      }

   }

   private static final boolean subarrayContentEquals(Object[] var0, int var1, int var2, List var3) {
      if (var2 != var3.size()) {
         return false;
      } else {
         for(int var4 = 0; var4 < var2; ++var4) {
            if (!Intrinsics.areEqual(var0[var1 + var4], var3.get(var4))) {
               return false;
            }
         }

         return true;
      }
   }

   private static final int subarrayContentHashCode(Object[] var0, int var1, int var2) {
      int var4 = 1;

      for(int var3 = 0; var3 < var2; ++var3) {
         Object var6 = var0[var1 + var3];
         int var5;
         if (var6 != null) {
            var5 = var6.hashCode();
         } else {
            var5 = 0;
         }

         var4 = var4 * 31 + var5;
      }

      return var4;
   }

   private static final String subarrayContentToString(Object[] var0, int var1, int var2) {
      StringBuilder var4 = new StringBuilder(var2 * 3 + 2);
      var4.append("[");

      for(int var3 = 0; var3 < var2; ++var3) {
         if (var3 > 0) {
            var4.append(", ");
         }

         var4.append(var0[var1 + var3]);
      }

      var4.append("]");
      String var5 = var4.toString();
      Intrinsics.checkNotNullExpressionValue(var5, "sb.toString()");
      return var5;
   }
}
