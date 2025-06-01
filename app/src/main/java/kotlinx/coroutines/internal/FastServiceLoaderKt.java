package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"},
   d2 = {"ANDROID_DETECTED", "", "getANDROID_DETECTED", "()Z", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class FastServiceLoaderKt {
   private static final boolean ANDROID_DETECTED;

   static {
      Result.Companion var0;
      Object var4;
      label20:
      try {
         var0 = Result.Companion;
         var4 = Result.constructor_impl(Class.forName("android.os.Build"));
      } catch (Throwable var3) {
         var0 = Result.Companion;
         var4 = Result.constructor_impl(ResultKt.createFailure(var3));
         break label20;
      }

      ANDROID_DETECTED = Result.isSuccess_impl(var4);
   }

   public static final boolean getANDROID_DETECTED() {
      return ANDROID_DETECTED;
   }
}
