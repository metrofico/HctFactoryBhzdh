package kotlinx.coroutines.internal;

import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u001a \u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0002\u001a\b\u0010\u000b\u001a\u00020\fH\u0000\u001a\f\u0010\r\u001a\u00020\u0003*\u00020\u000eH\u0007\u001a\u001a\u0010\u000f\u001a\u00020\u000e*\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012H\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\u00020\u0003X\u0082D¢\u0006\b\n\u0000\u0012\u0004\b\u0004\u0010\u0005¨\u0006\u0013"},
   d2 = {"FAST_SERVICE_LOADER_PROPERTY_NAME", "", "SUPPORT_MISSING", "", "getSUPPORT_MISSING$annotations", "()V", "createMissingDispatcher", "Lkotlinx/coroutines/internal/MissingMainCoroutineDispatcher;", "cause", "", "errorHint", "throwMissingMainDispatcherException", "", "isMissing", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "tryCreateDispatcher", "Lkotlinx/coroutines/internal/MainDispatcherFactory;", "factories", "", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class MainDispatchersKt {
   private static final String FAST_SERVICE_LOADER_PROPERTY_NAME = "kotlinx.coroutines.fast.service.loader";
   private static final boolean SUPPORT_MISSING = true;

   private static final MissingMainCoroutineDispatcher createMissingDispatcher(Throwable var0, String var1) {
      if (SUPPORT_MISSING) {
         return new MissingMainCoroutineDispatcher(var0, var1);
      } else if (var0 != null) {
         throw var0;
      } else {
         throwMissingMainDispatcherException();
         throw new KotlinNothingValueException();
      }
   }

   // $FF: synthetic method
   static MissingMainCoroutineDispatcher createMissingDispatcher$default(Throwable var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var0 = (Throwable)null;
         var0 = null;
      }

      if ((var2 & 2) != 0) {
         var1 = (String)null;
         var1 = null;
      }

      return createMissingDispatcher(var0, var1);
   }

   // $FF: synthetic method
   private static void getSUPPORT_MISSING$annotations() {
   }

   public static final boolean isMissing(MainCoroutineDispatcher var0) {
      return var0 instanceof MissingMainCoroutineDispatcher;
   }

   public static final Void throwMissingMainDispatcherException() {
      throw (Throwable)(new IllegalStateException("Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher, e.g. 'kotlinx-coroutines-android' and ensure it has the same version as 'kotlinx-coroutines-core'"));
   }

   public static final MainCoroutineDispatcher tryCreateDispatcher(MainDispatcherFactory var0, List var1) {
      MainCoroutineDispatcher var4;
      MainCoroutineDispatcher var5;
      try {
         var5 = var0.createDispatcher(var1);
      } catch (Throwable var3) {
         var4 = (MainCoroutineDispatcher)createMissingDispatcher(var3, var0.hintOnError());
         return var4;
      }

      var4 = var5;
      return var4;
   }
}
