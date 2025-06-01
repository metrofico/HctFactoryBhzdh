package kotlinx.coroutines;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000*\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\u001a=\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u001e\u0010\u0003\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004\"\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a%\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u0004\"\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\u001b\u0010\u0007\u001a\u00020\b*\b\u0012\u0004\u0012\u00020\n0\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"},
   d2 = {"awaitAll", "", "T", "deferreds", "", "Lkotlinx/coroutines/Deferred;", "([Lkotlinx/coroutines/Deferred;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "joinAll", "", "jobs", "Lkotlinx/coroutines/Job;", "([Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class AwaitKt {
   public static final Object awaitAll(Collection var0, Continuation var1) {
      Object var6;
      label34: {
         if (var1 instanceof <undefinedtype>) {
            <undefinedtype> var3 = (<undefinedtype>)var1;
            if ((var3.label & Integer.MIN_VALUE) != 0) {
               var3.label += Integer.MIN_VALUE;
               var6 = var3;
               break label34;
            }
         }

         var6 = new ContinuationImpl(var1) {
            Object L$0;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return AwaitKt.awaitAll((Collection)null, this);
            }
         };
      }

      Object var8 = ((<undefinedtype>)var6).result;
      Object var4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var2 = ((<undefinedtype>)var6).label;
      Object var5;
      List var7;
      if (var2 != 0) {
         if (var2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var0 = (Collection)((<undefinedtype>)var6).L$0;
         ResultKt.throwOnFailure(var8);
         var5 = var8;
      } else {
         ResultKt.throwOnFailure(var8);
         if (var0.isEmpty()) {
            var7 = CollectionsKt.emptyList();
            return var7;
         }

         Object[] var9 = var0.toArray(new Deferred[0]);
         if (var9 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
         }

         AwaitAll var10 = new AwaitAll((Deferred[])var9);
         ((<undefinedtype>)var6).L$0 = var0;
         ((<undefinedtype>)var6).label = 1;
         var6 = var10.await((Continuation)var6);
         var5 = var6;
         if (var6 == var4) {
            return var4;
         }
      }

      var7 = (List)var5;
      return var7;
   }

   public static final Object awaitAll(Deferred[] var0, Continuation var1) {
      Object var6;
      label35: {
         if (var1 instanceof <undefinedtype>) {
            <undefinedtype> var3 = (<undefinedtype>)var1;
            if ((var3.label & Integer.MIN_VALUE) != 0) {
               var3.label += Integer.MIN_VALUE;
               var6 = var3;
               break label35;
            }
         }

         var6 = new ContinuationImpl(var1) {
            Object L$0;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return AwaitKt.awaitAll((Deferred[])null, this);
            }
         };
      }

      Object var9 = ((<undefinedtype>)var6).result;
      Object var4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var2 = ((<undefinedtype>)var6).label;
      Object var5;
      List var7;
      if (var2 != 0) {
         if (var2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var0 = (Deferred[])((<undefinedtype>)var6).L$0;
         ResultKt.throwOnFailure(var9);
         var5 = var9;
      } else {
         ResultKt.throwOnFailure(var9);
         boolean var8;
         if (var0.length == 0) {
            var8 = true;
         } else {
            var8 = false;
         }

         if (var8) {
            var7 = CollectionsKt.emptyList();
            return var7;
         }

         AwaitAll var10 = new AwaitAll(var0);
         ((<undefinedtype>)var6).L$0 = var0;
         ((<undefinedtype>)var6).label = 1;
         var6 = var10.await((Continuation)var6);
         var5 = var6;
         if (var6 == var4) {
            return var4;
         }
      }

      var7 = (List)var5;
      return var7;
   }

   public static final Object joinAll(Collection var0, Continuation var1) {
      Object var11;
      label28: {
         if (var1 instanceof <undefinedtype>) {
            <undefinedtype> var3 = (<undefinedtype>)var1;
            if ((var3.label & Integer.MIN_VALUE) != 0) {
               var3.label += Integer.MIN_VALUE;
               var11 = var3;
               break label28;
            }
         }

         var11 = new ContinuationImpl(var1) {
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return AwaitKt.joinAll((Collection)null, this);
            }
         };
      }

      Object var5 = ((<undefinedtype>)var11).result;
      Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var2 = ((<undefinedtype>)var11).label;
      Collection var4;
      Iterator var10;
      Iterable var12;
      if (var2 != 0) {
         if (var2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         Job var8 = (Job)((<undefinedtype>)var11).L$4;
         Object var9 = ((<undefinedtype>)var11).L$3;
         var10 = (Iterator)((<undefinedtype>)var11).L$2;
         var12 = (Iterable)((<undefinedtype>)var11).L$1;
         var4 = (Collection)((<undefinedtype>)var11).L$0;
         ResultKt.throwOnFailure(var5);
      } else {
         ResultKt.throwOnFailure(var5);
         var12 = (Iterable)var0;
         Iterator var13 = var12.iterator();
         var4 = var0;
         var10 = var13;
      }

      Job var14;
      do {
         if (!var10.hasNext()) {
            return Unit.INSTANCE;
         }

         Object var7 = var10.next();
         var14 = (Job)var7;
         ((<undefinedtype>)var11).L$0 = var4;
         ((<undefinedtype>)var11).L$1 = var12;
         ((<undefinedtype>)var11).L$2 = var10;
         ((<undefinedtype>)var11).L$3 = var7;
         ((<undefinedtype>)var11).L$4 = var14;
         ((<undefinedtype>)var11).label = 1;
      } while(var14.join((Continuation)var11) != var6);

      return var6;
   }

   public static final Object joinAll(Job[] var0, Continuation var1) {
      Object var11;
      label26: {
         if (var1 instanceof <undefinedtype>) {
            <undefinedtype> var4 = (<undefinedtype>)var1;
            if ((var4.label & Integer.MIN_VALUE) != 0) {
               var4.label += Integer.MIN_VALUE;
               var11 = var4;
               break label26;
            }
         }

         var11 = new ContinuationImpl(var1) {
            int I$0;
            int I$1;
            Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return AwaitKt.joinAll((Job[])null, this);
            }
         };
      }

      Object var6 = ((<undefinedtype>)var11).result;
      Object var8 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var2 = ((<undefinedtype>)var11).label;
      int var3;
      Job[] var5;
      Job[] var12;
      if (var2 != 0) {
         if (var2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         Job var10 = (Job)((<undefinedtype>)var11).L$4;
         var10 = (Job)((<undefinedtype>)var11).L$3;
         var2 = ((<undefinedtype>)var11).I$1;
         var3 = ((<undefinedtype>)var11).I$0;
         var12 = (Job[])((<undefinedtype>)var11).L$2;
         var5 = (Job[])((<undefinedtype>)var11).L$1;
         var0 = (Job[])((<undefinedtype>)var11).L$0;
         ResultKt.throwOnFailure(var6);
         ++var2;
         var6 = var11;
         var11 = var8;
      } else {
         ResultKt.throwOnFailure(var6);
         var3 = var0.length;
         var12 = var0;
         var2 = 0;
         var6 = var11;
         var11 = var8;
         var5 = var0;
         var0 = var0;
      }

      while(var2 < var3) {
         Job var9 = var12[var2];
         ((<undefinedtype>)var6).L$0 = var0;
         ((<undefinedtype>)var6).L$1 = var5;
         ((<undefinedtype>)var6).L$2 = var12;
         ((<undefinedtype>)var6).I$0 = var3;
         ((<undefinedtype>)var6).I$1 = var2;
         ((<undefinedtype>)var6).L$3 = var9;
         ((<undefinedtype>)var6).L$4 = var9;
         ((<undefinedtype>)var6).label = 1;
         if (var9.join((Continuation)var6) == var11) {
            return var11;
         }

         ++var2;
         var6 = var6;
         var11 = var11;
      }

      return Unit.INSTANCE;
   }
}
