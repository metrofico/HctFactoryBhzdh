package kotlin.internal.jdk7;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.internal.PlatformImplementations;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002¨\u0006\u000f"},
   d2 = {"Lkotlin/internal/jdk7/JDK7PlatformImplementations;", "Lkotlin/internal/PlatformImplementations;", "()V", "addSuppressed", "", "cause", "", "exception", "getSuppressed", "", "sdkIsNullOrAtLeast", "", "version", "", "ReflectSdkVersion", "kotlin-stdlib-jdk7"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public class JDK7PlatformImplementations extends PlatformImplementations {
   private final boolean sdkIsNullOrAtLeast(int var1) {
      boolean var2;
      if (ReflectSdkVersion.sdkVersion != null && ReflectSdkVersion.sdkVersion < var1) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   public void addSuppressed(Throwable var1, Throwable var2) {
      Intrinsics.checkNotNullParameter(var1, "cause");
      Intrinsics.checkNotNullParameter(var2, "exception");
      if (this.sdkIsNullOrAtLeast(19)) {
         var1.addSuppressed(var2);
      } else {
         super.addSuppressed(var1, var2);
      }

   }

   public List getSuppressed(Throwable var1) {
      Intrinsics.checkNotNullParameter(var1, "exception");
      List var3;
      if (this.sdkIsNullOrAtLeast(19)) {
         Throwable[] var2 = var1.getSuppressed();
         Intrinsics.checkNotNullExpressionValue(var2, "exception.suppressed");
         var3 = ArraysKt.asList((Object[])var2);
      } else {
         var3 = super.getSuppressed(var1);
      }

      return var3;
   }

   @Metadata(
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u0006"},
      d2 = {"Lkotlin/internal/jdk7/JDK7PlatformImplementations$ReflectSdkVersion;", "", "()V", "sdkVersion", "", "Ljava/lang/Integer;", "kotlin-stdlib-jdk7"},
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
