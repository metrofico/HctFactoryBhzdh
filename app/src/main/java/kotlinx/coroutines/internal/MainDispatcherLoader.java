package kotlinx.coroutines.internal;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.Metadata;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\b"},
   d2 = {"Lkotlinx/coroutines/internal/MainDispatcherLoader;", "", "()V", "FAST_SERVICE_LOADER_ENABLED", "", "dispatcher", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "loadMainDispatcher", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class MainDispatcherLoader {
   private static final boolean FAST_SERVICE_LOADER_ENABLED;
   public static final MainDispatcherLoader INSTANCE;
   public static final MainCoroutineDispatcher dispatcher;

   static {
      MainDispatcherLoader var0 = new MainDispatcherLoader();
      INSTANCE = var0;
      FAST_SERVICE_LOADER_ENABLED = SystemPropsKt.systemProp("kotlinx.coroutines.fast.service.loader", true);
      dispatcher = var0.loadMainDispatcher();
   }

   private MainDispatcherLoader() {
   }

   private final MainCoroutineDispatcher loadMainDispatcher() {
      Throwable var10000;
      MainCoroutineDispatcher var120;
      label995: {
         List var5;
         boolean var10001;
         label987: {
            try {
               if (FAST_SERVICE_LOADER_ENABLED) {
                  var5 = FastServiceLoader.INSTANCE.loadMainDispatcherFactory$kotlinx_coroutines_core();
                  break label987;
               }
            } catch (Throwable var118) {
               var10000 = var118;
               var10001 = false;
               break label995;
            }

            try {
               var5 = SequencesKt.toList(SequencesKt.asSequence(ServiceLoader.load(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader()).iterator()));
            } catch (Throwable var117) {
               var10000 = var117;
               var10001 = false;
               break label995;
            }
         }

         Object var4;
         label977: {
            label996: {
               Iterator var8;
               try {
                  var8 = ((Iterable)var5).iterator();
                  if (!var8.hasNext()) {
                     break label996;
                  }
               } catch (Throwable var116) {
                  var10000 = var116;
                  var10001 = false;
                  break label995;
               }

               label969:
               try {
                  var4 = var8.next();
                  if (var8.hasNext()) {
                     break label969;
                  }
                  break label977;
               } catch (Throwable var115) {
                  var10000 = var115;
                  var10001 = false;
                  break label995;
               }

               int var2;
               try {
                  var2 = ((MainDispatcherFactory)var4).getLoadPriority();
               } catch (Throwable var113) {
                  var10000 = var113;
                  var10001 = false;
                  break label995;
               }

               Object var6 = var4;

               while(true) {
                  int var3;
                  Object var7;
                  try {
                     var7 = var8.next();
                     var3 = ((MainDispatcherFactory)var7).getLoadPriority();
                  } catch (Throwable var112) {
                     var10000 = var112;
                     var10001 = false;
                     break label995;
                  }

                  var4 = var6;
                  int var1 = var2;
                  if (var2 < var3) {
                     var4 = var7;
                     var1 = var3;
                  }

                  var6 = var4;
                  var2 = var1;

                  try {
                     if (!var8.hasNext()) {
                        break label977;
                     }
                  } catch (Throwable var114) {
                     var10000 = var114;
                     var10001 = false;
                     break label995;
                  }
               }
            }

            var4 = null;
         }

         MainDispatcherFactory var119;
         try {
            var119 = (MainDispatcherFactory)var4;
         } catch (Throwable var111) {
            var10000 = var111;
            var10001 = false;
            break label995;
         }

         if (var119 != null) {
            try {
               var120 = MainDispatchersKt.tryCreateDispatcher(var119, var5);
            } catch (Throwable var110) {
               var10000 = var110;
               var10001 = false;
               break label995;
            }

            if (var120 != null) {
               return var120;
            }
         }

         label944:
         try {
            var120 = (MainCoroutineDispatcher)MainDispatchersKt.createMissingDispatcher$default((Throwable)null, (String)null, 3, (Object)null);
            return var120;
         } catch (Throwable var109) {
            var10000 = var109;
            var10001 = false;
            break label944;
         }
      }

      Throwable var121 = var10000;
      var120 = (MainCoroutineDispatcher)MainDispatchersKt.createMissingDispatcher$default(var121, (String)null, 2, (Object)null);
      return var120;
   }
}
