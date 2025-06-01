package kotlin.internal;

import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.MatchResult;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.FallbackThreadLocalRandom;
import kotlin.random.Random;
import kotlin.text.MatchGroup;

@Metadata(
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u00112\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\u0013"},
   d2 = {"Lkotlin/internal/PlatformImplementations;", "", "()V", "addSuppressed", "", "cause", "", "exception", "defaultPlatformRandom", "Lkotlin/random/Random;", "getMatchResultNamedGroup", "Lkotlin/text/MatchGroup;", "matchResult", "Ljava/util/regex/MatchResult;", "name", "", "getSuppressed", "", "ReflectThrowable", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class PlatformImplementations {
   public void addSuppressed(Throwable var1, Throwable var2) {
      Intrinsics.checkNotNullParameter(var1, "cause");
      Intrinsics.checkNotNullParameter(var2, "exception");
      Method var3 = ReflectThrowable.addSuppressed;
      if (var3 != null) {
         var3.invoke(var1, var2);
      }

   }

   public Random defaultPlatformRandom() {
      return (Random)(new FallbackThreadLocalRandom());
   }

   public MatchGroup getMatchResultNamedGroup(MatchResult var1, String var2) {
      Intrinsics.checkNotNullParameter(var1, "matchResult");
      Intrinsics.checkNotNullParameter(var2, "name");
      throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
   }

   public List getSuppressed(Throwable var1) {
      Intrinsics.checkNotNullParameter(var1, "exception");
      Method var2 = ReflectThrowable.getSuppressed;
      List var4;
      if (var2 != null) {
         Object var3 = var2.invoke(var1);
         if (var3 != null) {
            List var5 = ArraysKt.asList((Throwable[])var3);
            var4 = var5;
            if (var5 != null) {
               return var4;
            }
         }
      }

      var4 = CollectionsKt.emptyList();
      return var4;
   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"},
      d2 = {"Lkotlin/internal/PlatformImplementations$ReflectThrowable;", "", "()V", "addSuppressed", "Ljava/lang/reflect/Method;", "getSuppressed", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private static final class ReflectThrowable {
      public static final ReflectThrowable INSTANCE = new ReflectThrowable();
      public static final Method addSuppressed;
      public static final Method getSuppressed;

      static {
         Method[] var6 = Throwable.class.getMethods();
         Intrinsics.checkNotNullExpressionValue(var6, "throwableMethods");
         int var3 = var6.length;
         byte var2 = 0;
         int var0 = 0;

         Method var4;
         Object var5;
         while(true) {
            var5 = null;
            if (var0 >= var3) {
               var4 = null;
               break;
            }

            boolean var1;
            label35: {
               var4 = var6[var0];
               if (Intrinsics.areEqual((Object)var4.getName(), (Object)"addSuppressed")) {
                  Class[] var7 = var4.getParameterTypes();
                  Intrinsics.checkNotNullExpressionValue(var7, "it.parameterTypes");
                  if (Intrinsics.areEqual((Object)ArraysKt.singleOrNull((Object[])var7), (Object)Throwable.class)) {
                     var1 = true;
                     break label35;
                  }
               }

               var1 = false;
            }

            if (var1) {
               break;
            }

            ++var0;
         }

         addSuppressed = var4;
         int var8 = var6.length;
         var0 = var2;

         while(true) {
            var4 = (Method)var5;
            if (var0 >= var8) {
               break;
            }

            var4 = var6[var0];
            if (Intrinsics.areEqual((Object)var4.getName(), (Object)"getSuppressed")) {
               break;
            }

            ++var0;
         }

         getSuppressed = var4;
      }
   }
}
