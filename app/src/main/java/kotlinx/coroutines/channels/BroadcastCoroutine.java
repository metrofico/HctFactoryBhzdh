package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobCancellationException;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.selects.SelectClause2;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0012\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0005B#\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0012\u0010\u001a\u001a\u00020\n2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0007J\u0016\u0010\u001a\u001a\u00020\u00032\u000e\u0010\u001b\u001a\n\u0018\u00010\u001dj\u0004\u0018\u0001`\u001eJ\u0010\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0012\u0010 \u001a\u00020\n2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J.\u0010!\u001a\u00020\u00032#\u0010\"\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u001c¢\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00030#H\u0097\u0001J\u0016\u0010&\u001a\u00020\n2\u0006\u0010'\u001a\u00028\u0000H\u0096\u0001¢\u0006\u0002\u0010(J\u0018\u0010)\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020\nH\u0014J\u0015\u0010+\u001a\u00020\u00032\u0006\u0010,\u001a\u00020\u0003H\u0014¢\u0006\u0002\u0010-J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00000/H\u0096\u0001J\u0019\u00100\u001a\u00020\u00032\u0006\u0010'\u001a\u00028\u0000H\u0096Aø\u0001\u0000¢\u0006\u0002\u00101R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\n8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\n8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R$\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f0\u0017X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019\u0082\u0002\u0004\n\u0002\b\u0019¨\u00062"},
   d2 = {"Lkotlinx/coroutines/channels/BroadcastCoroutine;", "E", "Lkotlinx/coroutines/AbstractCoroutine;", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlinx/coroutines/channels/BroadcastChannel;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "_channel", "active", "", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/channels/BroadcastChannel;Z)V", "get_channel", "()Lkotlinx/coroutines/channels/BroadcastChannel;", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "getChannel", "()Lkotlinx/coroutines/channels/SendChannel;", "isActive", "()Z", "isClosedForSend", "isFull", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "cancel", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancelInternal", "close", "invokeOnClose", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "offer", "element", "(Ljava/lang/Object;)Z", "onCancelled", "handled", "onCompleted", "value", "(Lkotlin/Unit;)V", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
class BroadcastCoroutine extends AbstractCoroutine implements ProducerScope, BroadcastChannel {
   private final BroadcastChannel _channel;

   public BroadcastCoroutine(CoroutineContext var1, BroadcastChannel var2, boolean var3) {
      super(var1, var3);
      this._channel = var2;
   }

   // $FF: synthetic method
   static Object send$suspendImpl(BroadcastCoroutine var0, Object var1, Continuation var2) {
      return var0._channel.send(var1, var2);
   }

   public final void cancel(CancellationException var1) {
      if (var1 == null) {
         JobSupport var3 = (JobSupport)this;
         String var2 = (String)null;
         Throwable var4 = (Throwable)null;
         var1 = (CancellationException)(new JobCancellationException(JobSupport.access$cancellationExceptionMessage(var3), (Throwable)null, (Job)var3));
      }

      this.cancelInternal((Throwable)var1);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public final boolean cancel(Throwable var1) {
      if (var1 == null) {
         JobSupport var3 = (JobSupport)this;
         String var2 = (String)null;
         Throwable var4 = (Throwable)null;
         var1 = (Throwable)(new JobCancellationException(JobSupport.access$cancellationExceptionMessage(var3), (Throwable)null, (Job)var3));
      }

      this.cancelInternal(var1);
      return true;
   }

   public void cancelInternal(Throwable var1) {
      CancellationException var2 = JobSupport.toCancellationException$default(this, var1, (String)null, 1, (Object)null);
      this._channel.cancel(var2);
      this.cancelCoroutine((Throwable)var2);
   }

   public boolean close(Throwable var1) {
      boolean var2 = this._channel.close(var1);
      this.start();
      return var2;
   }

   public SendChannel getChannel() {
      return (SendChannel)this;
   }

   public SelectClause2 getOnSend() {
      return this._channel.getOnSend();
   }

   protected final BroadcastChannel get_channel() {
      return this._channel;
   }

   public void invokeOnClose(Function1 var1) {
      this._channel.invokeOnClose(var1);
   }

   public boolean isActive() {
      return super.isActive();
   }

   public boolean isClosedForSend() {
      return this._channel.isClosedForSend();
   }

   public boolean isFull() {
      return this._channel.isFull();
   }

   public boolean offer(Object var1) {
      return this._channel.offer(var1);
   }

   protected void onCancelled(Throwable var1, boolean var2) {
      if (!this._channel.close(var1) && !var2) {
         CoroutineExceptionHandlerKt.handleCoroutineException(this.getContext(), var1);
      }

   }

   protected void onCompleted(Unit var1) {
      SendChannel.DefaultImpls.close$default((SendChannel)this._channel, (Throwable)null, 1, (Object)null);
   }

   public ReceiveChannel openSubscription() {
      return this._channel.openSubscription();
   }

   public Object send(Object var1, Continuation var2) {
      return send$suspendImpl(this, var1, var2);
   }
}
