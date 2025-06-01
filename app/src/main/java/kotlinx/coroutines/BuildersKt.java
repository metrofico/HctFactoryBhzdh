package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"kotlinx/coroutines/BuildersKt__BuildersKt", "kotlinx/coroutines/BuildersKt__Builders_commonKt"},
   k = 4,
   mv = {1, 4, 0}
)
public final class BuildersKt {
   public static final Deferred async(CoroutineScope var0, CoroutineContext var1, CoroutineStart var2, Function2 var3) {
      return BuildersKt__Builders_commonKt.async(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static Deferred async$default(CoroutineScope var0, CoroutineContext var1, CoroutineStart var2, Function2 var3, int var4, Object var5) {
      return BuildersKt__Builders_commonKt.async$default(var0, var1, var2, var3, var4, var5);
   }

   public static final Object invoke(CoroutineDispatcher var0, Function2 var1, Continuation var2) {
      return BuildersKt__Builders_commonKt.invoke(var0, var1, var2);
   }

   private static final Object invoke$$forInline(CoroutineDispatcher var0, Function2 var1, Continuation var2) {
      return BuildersKt__Builders_commonKt.invoke(var0, var1, var2);
   }

   public static final Job launch(CoroutineScope var0, CoroutineContext var1, CoroutineStart var2, Function2 var3) {
      return BuildersKt__Builders_commonKt.launch(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static Job launch$default(CoroutineScope var0, CoroutineContext var1, CoroutineStart var2, Function2 var3, int var4, Object var5) {
      return BuildersKt__Builders_commonKt.launch$default(var0, var1, var2, var3, var4, var5);
   }

   public static final Object runBlocking(CoroutineContext var0, Function2 var1) throws InterruptedException {
      return BuildersKt__BuildersKt.runBlocking(var0, var1);
   }

   // $FF: synthetic method
   public static Object runBlocking$default(CoroutineContext var0, Function2 var1, int var2, Object var3) throws InterruptedException {
      return BuildersKt__BuildersKt.runBlocking$default(var0, var1, var2, var3);
   }

   public static final Object withContext(CoroutineContext var0, Function2 var1, Continuation var2) {
      return BuildersKt__Builders_commonKt.withContext(var0, var1, var2);
   }
}
