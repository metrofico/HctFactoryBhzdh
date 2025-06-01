package kotlin.collections;

import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\u001a3\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001aQ\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032!\u0010\b\u001a\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u0002H\u00010\t\u001aX\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\f2!\u0010\b\u001a\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u0002H\u00010\tH\u0007¢\u0006\u0002\b\r¨\u0006\u000e"},
   d2 = {"getOrImplicitDefault", "V", "K", "", "key", "getOrImplicitDefaultNullable", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;", "withDefault", "defaultValue", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", "withDefaultMutable", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/collections/MapsKt"
)
class MapsKt__MapWithDefaultKt {
   public MapsKt__MapWithDefaultKt() {
   }

   public static final Object getOrImplicitDefaultNullable(Map var0, Object var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (var0 instanceof MapWithDefault) {
         return ((MapWithDefault)var0).getOrImplicitDefault(var1);
      } else {
         Object var2 = var0.get(var1);
         if (var2 == null && !var0.containsKey(var1)) {
            throw new NoSuchElementException("Key " + var1 + " is missing in the map.");
         } else {
            return var2;
         }
      }
   }

   public static final Map withDefault(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "defaultValue");
      if (var0 instanceof MapWithDefault) {
         var0 = MapsKt.withDefault(((MapWithDefault)var0).getMap(), var1);
      } else {
         var0 = (Map)(new MapWithDefaultImpl(var0, var1));
      }

      return var0;
   }

   public static final Map withDefaultMutable(Map var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "defaultValue");
      if (var0 instanceof MutableMapWithDefault) {
         var0 = MapsKt.withDefaultMutable(((MutableMapWithDefault)var0).getMap(), var1);
      } else {
         var0 = (Map)(new MutableMapWithDefaultImpl(var0, var1));
      }

      return var0;
   }
}
