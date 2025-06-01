package androidx.core.util;

import android.util.LruCache;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u001aø\u0001\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000628\b\u0006\u0010\u0007\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00060\b2%\b\u0006\u0010\r\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0006\u0012\u0004\u0018\u0001H\u00030\u000e2d\b\u0006\u0010\u000f\u001a^\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u0001H\u0003¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u0010H\u0086\b¨\u0006\u0016"},
   d2 = {"lruCache", "Landroid/util/LruCache;", "K", "V", "", "maxSize", "", "sizeOf", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "key", "value", "create", "Lkotlin/Function1;", "onEntryRemoved", "Lkotlin/Function4;", "", "evicted", "oldValue", "newValue", "", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class LruCacheKt {
   public static final LruCache lruCache(int var0, Function2 var1, Function1 var2, Function4 var3) {
      Intrinsics.checkParameterIsNotNull(var1, "sizeOf");
      Intrinsics.checkParameterIsNotNull(var2, "create");
      Intrinsics.checkParameterIsNotNull(var3, "onEntryRemoved");
      return (LruCache)(new LruCache(var1, var2, var3, var0, var0) {
         final Function1 $create;
         final int $maxSize;
         final Function4 $onEntryRemoved;
         final Function2 $sizeOf;

         public {
            this.$sizeOf = var1;
            this.$create = var2;
            this.$onEntryRemoved = var3;
            this.$maxSize = var4;
         }

         protected Object create(Object var1) {
            Intrinsics.checkParameterIsNotNull(var1, "key");
            return this.$create.invoke(var1);
         }

         protected void entryRemoved(boolean var1, Object var2, Object var3, Object var4) {
            Intrinsics.checkParameterIsNotNull(var2, "key");
            Intrinsics.checkParameterIsNotNull(var3, "oldValue");
            this.$onEntryRemoved.invoke(var1, var2, var3, var4);
         }

         protected int sizeOf(Object var1, Object var2) {
            Intrinsics.checkParameterIsNotNull(var1, "key");
            Intrinsics.checkParameterIsNotNull(var2, "value");
            return ((Number)this.$sizeOf.invoke(var1, var2)).intValue();
         }
      });
   }

   // $FF: synthetic method
   public static LruCache lruCache$default(int var0, Function2 var1, Function1 var2, Function4 var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var1 = (Function2)null.INSTANCE;
      }

      if ((var4 & 4) != 0) {
         var2 = (Function1)null.INSTANCE;
      }

      if ((var4 & 8) != 0) {
         var3 = (Function4)null.INSTANCE;
      }

      Intrinsics.checkParameterIsNotNull(var1, "sizeOf");
      Intrinsics.checkParameterIsNotNull(var2, "create");
      Intrinsics.checkParameterIsNotNull(var3, "onEntryRemoved");
      return (LruCache)(new LruCache(var1, var2, var3, var0, var0) {
         final Function1 $create;
         final int $maxSize;
         final Function4 $onEntryRemoved;
         final Function2 $sizeOf;

         public {
            this.$sizeOf = var1;
            this.$create = var2;
            this.$onEntryRemoved = var3;
            this.$maxSize = var4;
         }

         protected Object create(Object var1) {
            Intrinsics.checkParameterIsNotNull(var1, "key");
            return this.$create.invoke(var1);
         }

         protected void entryRemoved(boolean var1, Object var2, Object var3, Object var4) {
            Intrinsics.checkParameterIsNotNull(var2, "key");
            Intrinsics.checkParameterIsNotNull(var3, "oldValue");
            this.$onEntryRemoved.invoke(var1, var2, var3, var4);
         }

         protected int sizeOf(Object var1, Object var2) {
            Intrinsics.checkParameterIsNotNull(var1, "key");
            Intrinsics.checkParameterIsNotNull(var2, "value");
            return ((Number)this.$sizeOf.invoke(var1, var2)).intValue();
         }
      });
   }
}
