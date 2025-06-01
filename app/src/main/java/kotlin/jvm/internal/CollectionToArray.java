package kotlin.jvm.internal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

@Metadata(
   d1 = {"\u00002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0007¢\u0006\u0004\b\t\u0010\n\u001a5\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\t\u0010\f\u001a~\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0014\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u000f2\u001a\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u00112(\u0010\u0012\u001a$\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u0013H\u0082\b¢\u0006\u0002\u0010\u0014\"\u0018\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"},
   d2 = {"EMPTY", "", "", "[Ljava/lang/Object;", "MAX_SIZE", "", "collectionToArray", "collection", "", "toArray", "(Ljava/util/Collection;)[Ljava/lang/Object;", "a", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "toArrayImpl", "empty", "Lkotlin/Function0;", "alloc", "Lkotlin/Function1;", "trim", "Lkotlin/Function2;", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)[Ljava/lang/Object;", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class CollectionToArray {
   private static final Object[] EMPTY = new Object[0];
   private static final int MAX_SIZE = 2147483645;

   public static final Object[] toArray(Collection var0) {
      Intrinsics.checkNotNullParameter(var0, "collection");
      int var1 = var0.size();
      Object[] var6;
      if (var1 != 0) {
         Iterator var5 = var0.iterator();
         if (var5.hasNext()) {
            var6 = new Object[var1];
            var1 = 0;

            while(true) {
               int var2 = var1 + 1;
               var6[var1] = var5.next();
               Object[] var4;
               if (var2 >= var6.length) {
                  if (!var5.hasNext()) {
                     return var6;
                  }

                  int var3 = var2 * 3 + 1 >>> 1;
                  var1 = var3;
                  if (var3 <= var2) {
                     if (var2 >= 2147483645) {
                        throw new OutOfMemoryError();
                     }

                     var1 = 2147483645;
                  }

                  var4 = Arrays.copyOf(var6, var1);
                  Intrinsics.checkNotNullExpressionValue(var4, "copyOf(result, newSize)");
               } else {
                  var4 = var6;
                  if (!var5.hasNext()) {
                     var6 = Arrays.copyOf(var6, var2);
                     Intrinsics.checkNotNullExpressionValue(var6, "copyOf(result, size)");
                     return var6;
                  }
               }

               var1 = var2;
               var6 = var4;
            }
         }
      }

      var6 = EMPTY;
      return var6;
   }

   public static final Object[] toArray(Collection var0, Object[] var1) {
      Intrinsics.checkNotNullParameter(var0, "collection");
      var1.getClass();
      int var3 = var0.size();
      int var2 = 0;
      Object[] var7;
      if (var3 == 0) {
         var7 = var1;
         if (var1.length > 0) {
            var1[0] = null;
            var7 = var1;
         }
      } else {
         Iterator var6 = var0.iterator();
         if (!var6.hasNext()) {
            var7 = var1;
            if (var1.length > 0) {
               var1[0] = null;
               var7 = var1;
            }
         } else {
            if (var3 <= var1.length) {
               var7 = var1;
            } else {
               Object var8 = Array.newInstance(var1.getClass().getComponentType(), var3);
               Intrinsics.checkNotNull(var8, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
               var7 = (Object[])var8;
            }

            while(true) {
               var3 = var2 + 1;
               var7[var2] = var6.next();
               Object[] var5;
               if (var3 >= var7.length) {
                  if (!var6.hasNext()) {
                     break;
                  }

                  int var4 = var3 * 3 + 1 >>> 1;
                  var2 = var4;
                  if (var4 <= var3) {
                     if (var3 >= 2147483645) {
                        throw new OutOfMemoryError();
                     }

                     var2 = 2147483645;
                  }

                  var5 = Arrays.copyOf(var7, var2);
                  Intrinsics.checkNotNullExpressionValue(var5, "copyOf(result, newSize)");
               } else {
                  var5 = var7;
                  if (!var6.hasNext()) {
                     if (var7 == var1) {
                        var1[var3] = null;
                        var7 = var1;
                     } else {
                        var7 = Arrays.copyOf(var7, var3);
                        Intrinsics.checkNotNullExpressionValue(var7, "copyOf(result, size)");
                     }
                     break;
                  }
               }

               var2 = var3;
               var7 = var5;
            }
         }
      }

      return var7;
   }

   private static final Object[] toArrayImpl(Collection var0, Function0 var1, Function1 var2, Function2 var3) {
      int var4 = var0.size();
      if (var4 == 0) {
         return (Object[])var1.invoke();
      } else {
         Iterator var7 = var0.iterator();
         if (!var7.hasNext()) {
            return (Object[])var1.invoke();
         } else {
            Object[] var9 = (Object[])var2.invoke(var4);
            var4 = 0;

            while(true) {
               int var5 = var4 + 1;
               var9[var4] = var7.next();
               Object[] var8;
               if (var5 >= var9.length) {
                  if (!var7.hasNext()) {
                     return var9;
                  }

                  int var6 = var5 * 3 + 1 >>> 1;
                  var4 = var6;
                  if (var6 <= var5) {
                     if (var5 >= 2147483645) {
                        throw new OutOfMemoryError();
                     }

                     var4 = 2147483645;
                  }

                  var8 = Arrays.copyOf(var9, var4);
                  Intrinsics.checkNotNullExpressionValue(var8, "copyOf(result, newSize)");
               } else {
                  var8 = var9;
                  if (!var7.hasNext()) {
                     return (Object[])var3.invoke(var9, var5);
                  }
               }

               var4 = var5;
               var9 = var8;
            }
         }
      }
   }
}
