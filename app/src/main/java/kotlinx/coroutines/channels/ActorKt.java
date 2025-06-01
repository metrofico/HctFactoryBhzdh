package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000R\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u009c\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2-\b\u0002\u0010\n\u001a'\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000bj\u0004\u0018\u0001`\u00112-\u0010\u0012\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0013¢\u0006\u0002\b\u0017H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"},
   d2 = {"actor", "Lkotlinx/coroutines/channels/SendChannel;", "E", "Lkotlinx/coroutines/CoroutineScope;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "start", "Lkotlinx/coroutines/CoroutineStart;", "onCompletion", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ActorScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/SendChannel;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class ActorKt {
   public static final SendChannel actor(CoroutineScope var0, CoroutineContext var1, int var2, CoroutineStart var3, Function1 var4, Function2 var5) {
      CoroutineContext var6 = CoroutineContextKt.newCoroutineContext(var0, var1);
      Channel var8 = ChannelKt.Channel$default(var2, (BufferOverflow)null, (Function1)null, 6, (Object)null);
      ActorCoroutine var7;
      if (var3.isLazy()) {
         var7 = (ActorCoroutine)(new LazyActorCoroutine(var6, var8, var5));
      } else {
         var7 = new ActorCoroutine(var6, var8, true);
      }

      if (var4 != null) {
         var7.invokeOnCompletion(var4);
      }

      var7.start(var3, var7, var5);
      return (SendChannel)var7;
   }

   // $FF: synthetic method
   public static SendChannel actor$default(CoroutineScope var0, CoroutineContext var1, int var2, CoroutineStart var3, Function1 var4, Function2 var5, int var6, Object var7) {
      if ((var6 & 1) != 0) {
         var1 = (CoroutineContext)EmptyCoroutineContext.INSTANCE;
      }

      if ((var6 & 2) != 0) {
         var2 = 0;
      }

      if ((var6 & 4) != 0) {
         var3 = CoroutineStart.DEFAULT;
      }

      if ((var6 & 8) != 0) {
         var4 = null;
         Function1 var8 = (Function1)null;
      }

      return actor(var0, var1, var2, var3, var4, var5);
   }
}
