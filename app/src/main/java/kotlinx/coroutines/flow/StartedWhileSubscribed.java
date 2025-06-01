package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.DelayKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u000bH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"},
   d2 = {"Lkotlinx/coroutines/flow/StartedWhileSubscribed;", "Lkotlinx/coroutines/flow/SharingStarted;", "stopTimeout", "", "replayExpiration", "(JJ)V", "command", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/SharingCommand;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "", "equals", "", "other", "", "hashCode", "toString", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
final class StartedWhileSubscribed implements SharingStarted {
   private final long replayExpiration;
   private final long stopTimeout;

   public StartedWhileSubscribed(long var1, long var3) {
      this.stopTimeout = var1;
      this.replayExpiration = var3;
      boolean var6 = true;
      boolean var5;
      if (var1 >= 0L) {
         var5 = true;
      } else {
         var5 = false;
      }

      if (var5) {
         if (var3 >= 0L) {
            var5 = var6;
         } else {
            var5 = false;
         }

         if (!var5) {
            throw (Throwable)(new IllegalArgumentException(("replayExpiration(" + var3 + " ms) cannot be negative").toString()));
         }
      } else {
         throw (Throwable)(new IllegalArgumentException(("stopTimeout(" + var1 + " ms) cannot be negative").toString()));
      }
   }

   public Flow command(StateFlow var1) {
      return FlowKt.distinctUntilChanged(FlowKt.dropWhile(FlowKt.transformLatest((Flow)var1, (Function3)(new Function3(this, (Continuation)null) {
         int I$0;
         Object L$0;
         int label;
         private FlowCollector p$;
         private int p$0;
         final StartedWhileSubscribed this$0;

         {
            this.this$0 = var1;
         }

         public final Continuation create(FlowCollector var1, int var2, Continuation var3) {
            Function3 var4 = new <anonymous constructor>(this.this$0, var3);
            var4.p$ = var1;
            var4.p$0 = var2;
            return var4;
         }

         public final Object invoke(Object var1, Object var2, Object var3) {
            return ((<undefinedtype>)this.create((FlowCollector)var1, ((Number)var2).intValue(), (Continuation)var3)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            FlowCollector var6;
            label69: {
               int var3;
               Object var7;
               label56: {
                  int var2;
                  FlowCollector var8;
                  label55: {
                     long var4;
                     label60: {
                        var7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        var2 = this.label;
                        SharingCommand var10;
                        if (var2 != 0) {
                           if (var2 == 1) {
                              break label69;
                           }

                           if (var2 != 2) {
                              if (var2 != 3) {
                                 if (var2 != 4) {
                                    if (var2 != 5) {
                                       throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    break label69;
                                 }

                                 var2 = this.I$0;
                                 var6 = (FlowCollector)this.L$0;
                                 ResultKt.throwOnFailure(var1);
                                 var8 = var6;
                                 break label55;
                              }

                              var2 = this.I$0;
                              var6 = (FlowCollector)this.L$0;
                              ResultKt.throwOnFailure(var1);
                              var8 = var6;
                              break label60;
                           }

                           var2 = this.I$0;
                           var6 = (FlowCollector)this.L$0;
                           ResultKt.throwOnFailure(var1);
                           var8 = var6;
                        } else {
                           ResultKt.throwOnFailure(var1);
                           var8 = this.p$;
                           var2 = this.p$0;
                           if (var2 > 0) {
                              var10 = SharingCommand.START;
                              this.L$0 = var8;
                              this.I$0 = var2;
                              this.label = 1;
                              if (var8.emit(var10, this) == var7) {
                                 return var7;
                              }

                              return Unit.INSTANCE;
                           }

                           var4 = this.this$0.stopTimeout;
                           this.L$0 = var8;
                           this.I$0 = var2;
                           this.label = 2;
                           if (DelayKt.delay(var4, this) == var7) {
                              return var7;
                           }
                        }

                        var3 = var2;
                        var6 = var8;
                        if (this.this$0.replayExpiration <= 0L) {
                           break label56;
                        }

                        var10 = SharingCommand.STOP;
                        this.L$0 = var8;
                        this.I$0 = var2;
                        this.label = 3;
                        if (var8.emit(var10, this) == var7) {
                           return var7;
                        }
                     }

                     var4 = this.this$0.replayExpiration;
                     this.L$0 = var8;
                     this.I$0 = var2;
                     this.label = 4;
                     if (DelayKt.delay(var4, this) == var7) {
                        return var7;
                     }
                  }

                  var6 = var8;
                  var3 = var2;
               }

               SharingCommand var9 = SharingCommand.STOP_AND_RESET_REPLAY_CACHE;
               this.L$0 = var6;
               this.I$0 = var3;
               this.label = 5;
               if (var6.emit(var9, this) == var7) {
                  return var7;
               }

               return Unit.INSTANCE;
            }

            var6 = (FlowCollector)this.L$0;
            ResultKt.throwOnFailure(var1);
            return Unit.INSTANCE;
         }
      })), (Function2)(new Function2((Continuation)null) {
         int label;
         private SharingCommand p$0;

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(var2);
            var3.p$0 = (SharingCommand)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
               ResultKt.throwOnFailure(var1);
               boolean var2;
               if (this.p$0 != SharingCommand.START) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               return Boxing.boxBoolean(var2);
            } else {
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
         }
      })));
   }

   public boolean equals(Object var1) {
      boolean var4;
      if (var1 instanceof StartedWhileSubscribed) {
         long var2 = this.stopTimeout;
         StartedWhileSubscribed var5 = (StartedWhileSubscribed)var1;
         if (var2 == var5.stopTimeout && this.replayExpiration == var5.replayExpiration) {
            var4 = true;
            return var4;
         }
      }

      var4 = false;
      return var4;
   }

   public int hashCode() {
      return Long.valueOf(this.stopTimeout).hashCode() * 31 + Long.valueOf(this.replayExpiration).hashCode();
   }

   public String toString() {
      List var1 = CollectionsKt.createListBuilder(2);
      if (this.stopTimeout > 0L) {
         var1.add("stopTimeout=" + this.stopTimeout + "ms");
      }

      if (this.replayExpiration < Long.MAX_VALUE) {
         var1.add("replayExpiration=" + this.replayExpiration + "ms");
      }

      var1 = CollectionsKt.build(var1);
      return "SharingStarted.WhileSubscribed(" + CollectionsKt.joinToString$default((Iterable)var1, (CharSequence)null, (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 63, (Object)null) + ')';
   }
}
