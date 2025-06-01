package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J\u0011\u0010\u0003\u001a\u00020\u0004H¦Bø\u0001\u0000¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\u00028\u0000H¦\u0002¢\u0006\u0002\u0010\u0007J\u0013\u0010\b\u001a\u00028\u0000H\u0097@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"},
   d2 = {"Lkotlinx/coroutines/channels/ChannelIterator;", "E", "", "hasNext", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "next", "()Ljava/lang/Object;", "next0", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public interface ChannelIterator {
   Object hasNext(Continuation var1);

   Object next();

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.3.0, binary compatibility with versions <= 1.2.x"
   )
   Object next(Continuation var1);

   @Metadata(
      bv = {1, 0, 3},
      k = 3,
      mv = {1, 4, 0}
   )
   public static final class DefaultImpls {
      // $FF: synthetic method
      @Deprecated(
         level = DeprecationLevel.HIDDEN,
         message = "Since 1.3.0, binary compatibility with versions <= 1.2.x"
      )
      public static Object next(ChannelIterator var0, Continuation var1) {
         Object var5;
         label27: {
            if (var1 instanceof <undefinedtype>) {
               <undefinedtype> var3 = (<undefinedtype>)var1;
               if ((var3.label & Integer.MIN_VALUE) != 0) {
                  var3.label += Integer.MIN_VALUE;
                  var5 = var3;
                  break label27;
               }
            }

            var5 = new ContinuationImpl(var0, var1) {
               Object L$0;
               int label;
               Object result;
               final ChannelIterator this$0;

               {
                  this.this$0 = var1;
               }

               public final Object invokeSuspend(Object var1) {
                  this.result = var1;
                  this.label |= Integer.MIN_VALUE;
                  return DefaultImpls.next((ChannelIterator)null, this);
               }
            };
         }

         Object var6 = ((<undefinedtype>)var5).result;
         Object var4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         int var2 = ((<undefinedtype>)var5).label;
         if (var2 != 0) {
            if (var2 != 1) {
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            var0 = (ChannelIterator)((<undefinedtype>)var5).L$0;
            ResultKt.throwOnFailure(var6);
            var5 = var6;
         } else {
            ResultKt.throwOnFailure(var6);
            ((<undefinedtype>)var5).L$0 = var0;
            ((<undefinedtype>)var5).label = 1;
            var6 = var0.hasNext((Continuation)var5);
            var5 = var6;
            if (var6 == var4) {
               return var4;
            }
         }

         if ((Boolean)var5) {
            return var0.next();
         } else {
            throw (Throwable)(new ClosedReceiveChannelException("Channel was closed"));
         }
      }
   }
}
