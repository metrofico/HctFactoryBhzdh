package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobCancellationException;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause2;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0004B#\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\"\u001a\u00020\u0003H\u0016J\u0012\u0010\"\u001a\u00020\t2\b\u0010#\u001a\u0004\u0018\u00010$H\u0007J\u0016\u0010\"\u001a\u00020\u00032\u000e\u0010#\u001a\n\u0018\u00010%j\u0004\u0018\u0001`&J\u0010\u0010'\u001a\u00020\u00032\u0006\u0010#\u001a\u00020$H\u0016J\u0013\u0010(\u001a\u00020\t2\b\u0010#\u001a\u0004\u0018\u00010$H\u0096\u0001J.\u0010)\u001a\u00020\u00032#\u0010*\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010$¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u00030+H\u0097\u0001J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00000/H\u0096\u0003J\u0016\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00028\u0000H\u0096\u0001¢\u0006\u0002\u00102J\u0010\u00103\u001a\u0004\u0018\u00018\u0000H\u0096\u0001¢\u0006\u0002\u00104J\u0011\u00105\u001a\u00028\u0000H\u0096Aø\u0001\u0000¢\u0006\u0002\u00106J\u001f\u00107\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0097Aø\u0001\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b8\u00106J\u0013\u00109\u001a\u0004\u0018\u00018\u0000H\u0097Aø\u0001\u0000¢\u0006\u0002\u00106J\u0019\u0010:\u001a\u00020\u00032\u0006\u00101\u001a\u00028\u0000H\u0096Aø\u0001\u0000¢\u0006\u0002\u0010;R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u000f\u001a\u00020\t8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\t8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\t8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0014\u0010\u0013\u001a\u00020\t8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0010R\u0018\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R#\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00190\u00158\u0016X\u0097\u0005ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0017R\u001c\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00158\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0017R$\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001f0\u001eX\u0096\u0005¢\u0006\u0006\u001a\u0004\b \u0010!\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006<"},
   d2 = {"Lkotlinx/coroutines/channels/ChannelCoroutine;", "E", "Lkotlinx/coroutines/AbstractCoroutine;", "", "Lkotlinx/coroutines/channels/Channel;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "_channel", "active", "", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/channels/Channel;Z)V", "get_channel", "()Lkotlinx/coroutines/channels/Channel;", "channel", "getChannel", "isClosedForReceive", "()Z", "isClosedForSend", "isEmpty", "isFull", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveOrClosed", "Lkotlinx/coroutines/channels/ValueOrClosed;", "getOnReceiveOrClosed", "onReceiveOrNull", "getOnReceiveOrNull", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "Lkotlinx/coroutines/channels/SendChannel;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "cancel", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancelInternal", "close", "invokeOnClose", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "offer", "element", "(Ljava/lang/Object;)Z", "poll", "()Ljava/lang/Object;", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOrClosed", "receiveOrClosed-ZYPwvRU", "receiveOrNull", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public class ChannelCoroutine extends AbstractCoroutine implements Channel {
   private final Channel _channel;

   public ChannelCoroutine(CoroutineContext var1, Channel var2, boolean var3) {
      super(var1, var3);
      this._channel = var2;
   }

   // $FF: synthetic method
   static Object receive$suspendImpl(ChannelCoroutine var0, Continuation var1) {
      return var0._channel.receive(var1);
   }

   // $FF: synthetic method
   static Object receiveOrClosed_ZYPwvRU$suspendImpl(ChannelCoroutine var0, Continuation var1) {
      return var0._channel.receiveOrClosed_ZYPwvRU(var1);
   }

   // $FF: synthetic method
   static Object receiveOrNull$suspendImpl(ChannelCoroutine var0, Continuation var1) {
      return var0._channel.receiveOrNull(var1);
   }

   // $FF: synthetic method
   static Object send$suspendImpl(ChannelCoroutine var0, Object var1, Continuation var2) {
      return var0._channel.send(var1, var2);
   }

   // $FF: synthetic method
   public void cancel() {
      JobSupport var1 = (JobSupport)this;
      String var2 = (String)null;
      Throwable var3 = (Throwable)null;
      this.cancelInternal((Throwable)(new JobCancellationException(JobSupport.access$cancellationExceptionMessage(var1), (Throwable)null, (Job)var1)));
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
      JobSupport var3 = (JobSupport)this;
      String var2 = (String)null;
      Throwable var4 = (Throwable)null;
      this.cancelInternal((Throwable)(new JobCancellationException(JobSupport.access$cancellationExceptionMessage(var3), (Throwable)null, (Job)var3)));
      return true;
   }

   public void cancelInternal(Throwable var1) {
      CancellationException var2 = JobSupport.toCancellationException$default(this, var1, (String)null, 1, (Object)null);
      this._channel.cancel(var2);
      this.cancelCoroutine((Throwable)var2);
   }

   public boolean close(Throwable var1) {
      return this._channel.close(var1);
   }

   public final Channel getChannel() {
      return (Channel)this;
   }

   public SelectClause1 getOnReceive() {
      return this._channel.getOnReceive();
   }

   public SelectClause1 getOnReceiveOrClosed() {
      return this._channel.getOnReceiveOrClosed();
   }

   public SelectClause1 getOnReceiveOrNull() {
      return this._channel.getOnReceiveOrNull();
   }

   public SelectClause2 getOnSend() {
      return this._channel.getOnSend();
   }

   protected final Channel get_channel() {
      return this._channel;
   }

   public void invokeOnClose(Function1 var1) {
      this._channel.invokeOnClose(var1);
   }

   public boolean isClosedForReceive() {
      return this._channel.isClosedForReceive();
   }

   public boolean isClosedForSend() {
      return this._channel.isClosedForSend();
   }

   public boolean isEmpty() {
      return this._channel.isEmpty();
   }

   public boolean isFull() {
      return this._channel.isFull();
   }

   public ChannelIterator iterator() {
      return this._channel.iterator();
   }

   public boolean offer(Object var1) {
      return this._channel.offer(var1);
   }

   public Object poll() {
      return this._channel.poll();
   }

   public Object receive(Continuation var1) {
      return receive$suspendImpl(this, var1);
   }

   public Object receiveOrClosed_ZYPwvRU(Continuation var1) {
      return receiveOrClosed_ZYPwvRU$suspendImpl(this, var1);
   }

   @Deprecated(
      level = DeprecationLevel.WARNING,
      message = "Deprecated in favor of receiveOrClosed and receiveOrNull extension",
      replaceWith = @ReplaceWith(
   expression = "receiveOrNull",
   imports = {"kotlinx.coroutines.channels.receiveOrNull"}
)
   )
   public Object receiveOrNull(Continuation var1) {
      return receiveOrNull$suspendImpl(this, var1);
   }

   public Object send(Object var1, Continuation var2) {
      return send$suspendImpl(this, var1, var2);
   }
}
