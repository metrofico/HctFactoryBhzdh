package kotlin.internal.jdk8;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.internal.jdk7.JDK7PlatformImplementations;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.jdk8.PlatformThreadLocalRandom;
import kotlin.ranges.IntRange;
import kotlin.text.MatchGroup;

@Metadata(
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002¨\u0006\u0010"},
   d2 = {"Lkotlin/internal/jdk8/JDK8PlatformImplementations;", "Lkotlin/internal/jdk7/JDK7PlatformImplementations;", "()V", "defaultPlatformRandom", "Lkotlin/random/Random;", "getMatchResultNamedGroup", "Lkotlin/text/MatchGroup;", "matchResult", "Ljava/util/regex/MatchResult;", "name", "", "sdkIsNullOrAtLeast", "", "version", "", "ReflectSdkVersion", "kotlin-stdlib-jdk8"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class JDK8PlatformImplementations extends JDK7PlatformImplementations {
   private final boolean sdkIsNullOrAtLeast(int var1) {
      boolean var2;
      if (ReflectSdkVersion.sdkVersion != null && ReflectSdkVersion.sdkVersion < var1) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   public Random defaultPlatformRandom() {
      Random var1;
      if (this.sdkIsNullOrAtLeast(24)) {
         var1 = (Random)(new PlatformThreadLocalRandom());
      } else {
         var1 = super.defaultPlatformRandom();
      }

      return var1;
   }

   public MatchGroup getMatchResultNamedGroup(MatchResult var1, String var2) {
      Intrinsics.checkNotNullParameter(var1, "matchResult");
      Intrinsics.checkNotNullParameter(var2, "name");
      boolean var3 = var1 instanceof Matcher;
      MatchGroup var4 = null;
      Matcher var6;
      if (var3) {
         var6 = (Matcher)var1;
      } else {
         var6 = null;
      }

      if (var6 != null) {
         IntRange var5 = new IntRange(var6.start(var2), var6.end(var2) - 1);
         if (var5.getStart() >= 0) {
            String var7 = var6.group(var2);
            Intrinsics.checkNotNullExpressionValue(var7, "matcher.group(name)");
            var4 = new MatchGroup(var7, var5);
         }

         return var4;
      } else {
         throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
      }
   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u0006"},
      d2 = {"Lkotlin/internal/jdk8/JDK8PlatformImplementations$ReflectSdkVersion;", "", "()V", "sdkVersion", "", "Ljava/lang/Integer;", "kotlin-stdlib-jdk8"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   private static final class ReflectSdkVersion {
      public static final ReflectSdkVersion INSTANCE = new ReflectSdkVersion();
      public static final Integer sdkVersion;

      static {
         Object var3 = null;
         boolean var5 = false;

         Object var1;
         label58: {
            Integer var7;
            label57:
            try {
               var5 = true;
               var1 = Class.forName("android.os.Build$VERSION").getField("SDK_INT").get((Object)null);
               if (var1 instanceof Integer) {
                  var7 = (Integer)var1;
                  var5 = false;
                  break label58;
               }

               var5 = false;
            } finally {
               if (var5) {
                  var7 = (Integer)null;
                  break label57;
               }
            }

            var1 = null;
         }

         Object var2 = var3;
         if (var1 != null) {
            boolean var0;
            if (((Number)var1).intValue() > 0) {
               var0 = true;
            } else {
               var0 = false;
            }

            var2 = var3;
            if (var0) {
               var2 = var1;
            }
         }

         sdkVersion = (Integer)var2;
      }
   }
}
