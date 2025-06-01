package kotlin.collections;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\u001a/\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¢\u0006\u0002\u0010\u0006\u001a\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001a#\u0010\n\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0001H\u0001¢\u0006\u0004\b\u000b\u0010\f\u001a,\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0001H\u0086\b¢\u0006\u0002\u0010\u000e\u001a\u0015\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0087\b\u001a&\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u0015H\u0086\b¢\u0006\u0002\u0010\u0016¨\u0006\u0017"},
   d2 = {"arrayOfNulls", "", "T", "reference", "size", "", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "copyOfRangeToIndexCheck", "", "toIndex", "contentDeepHashCodeImpl", "contentDeepHashCode", "([Ljava/lang/Object;)I", "orEmpty", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toString", "", "", "charset", "Ljava/nio/charset/Charset;", "toTypedArray", "", "(Ljava/util/Collection;)[Ljava/lang/Object;", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/collections/ArraysKt"
)
class ArraysKt__ArraysJVMKt {
   public ArraysKt__ArraysJVMKt() {
   }

   public static final Object[] arrayOfNulls(Object[] var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "reference");
      Object var2 = Array.newInstance(var0.getClass().getComponentType(), var1);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
      return (Object[])var2;
   }

   public static final int contentDeepHashCode(Object[] var0) {
      return Arrays.deepHashCode(var0);
   }

   public static final void copyOfRangeToIndexCheck(int var0, int var1) {
      if (var0 > var1) {
         throw new IndexOutOfBoundsException("toIndex (" + var0 + ") is greater than size (" + var1 + ").");
      }
   }

   // $FF: synthetic method
   public static final Object[] orEmpty(Object[] var0) {
      Object[] var1 = var0;
      if (var0 == null) {
         Intrinsics.reifiedOperationMarker(0, "T?");
         var1 = (Object[])(new Object[0]);
      }

      return var1;
   }

   private static final String toString(byte[] var0, Charset var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "charset");
      return new String(var0, var1);
   }

   // $FF: synthetic method
   public static final Object[] toTypedArray(Collection var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Collection var1 = (Collection)var0;
      Intrinsics.reifiedOperationMarker(0, "T?");
      Object[] var2 = var0.toArray(new Object[0]);
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
      return (Object[])var2;
   }
}
