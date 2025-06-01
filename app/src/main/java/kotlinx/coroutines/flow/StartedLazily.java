package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"},
   d2 = {"Lkotlinx/coroutines/flow/StartedLazily;", "Lkotlinx/coroutines/flow/SharingStarted;", "()V", "command", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/SharingCommand;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "", "toString", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
final class StartedLazily implements SharingStarted {
   public StartedLazily() {
   }

   public Flow command(StateFlow var1) {
      return FlowKt.flow((Function2)(new Function2(var1, (Continuation)null) {
         final StateFlow $subscriptionCount;
         Object L$0;
         Object L$1;
         Object L$2;
         int label;
         private FlowCollector p$;

         {
            this.$subscriptionCount = var1;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$subscriptionCount, var2);
            var3.p$ = (FlowCollector)var1;
            return var3;
         }

         public final Object invoke(Object var1, Object var2) {
            return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               Flow var8 = (Flow)this.L$2;
               Ref.BooleanRef var9 = (Ref.BooleanRef)this.L$1;
               FlowCollector var10 = (FlowCollector)this.L$0;
               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               FlowCollector var6 = this.p$;
               Ref.BooleanRef var7 = new Ref.BooleanRef();
               var7.element = false;
               Flow var4 = (Flow)this.$subscriptionCount;
               FlowCollector var5 = (FlowCollector)(new FlowCollector(var6, var7) {
                  final Ref.BooleanRef $started$inlined;
                  final FlowCollector $this_flow$inlined;

                  public {
                     this.$this_flow$inlined = var1;
                     this.$started$inlined = var2;
                  }

                  public Object emit(Object var1, Continuation var2) {
                     Object var10;
                     label27: {
                        if (var2 instanceof <undefinedtype>) {
                           <undefinedtype> var4 = (<undefinedtype>)var2;
                           if ((var4.label & Integer.MIN_VALUE) != 0) {
                              var4.label += Integer.MIN_VALUE;
                              var10 = var4;
                              break label27;
                           }
                        }

                        var10 = new ContinuationImpl(this, var2) {
                           int I$0;
                           Object L$0;
                           Object L$1;
                           Object L$2;
                           int label;
                           Object result;
                           final <undefinedtype> this$0;

                           public {
                              this.this$0 = var1;
                           }

                           public final Object invokeSuspend(Object var1) {
                              this.result = var1;
                              this.label |= Integer.MIN_VALUE;
                              return this.this$0.emit((Object)null, this);
                           }
                        };
                     }

                     Object var11 = ((<undefinedtype>)var10).result;
                     Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                     int var3 = ((<undefinedtype>)var10).label;
                     if (var3 != 0) {
                        if (var3 != 1) {
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        var3 = ((<undefinedtype>)var10).I$0;
                        Continuation var8 = (Continuation)((<undefinedtype>)var10).L$2;
                        var1 = ((<undefinedtype>)var10).L$1;
                        <undefinedtype> var9 = (<undefinedtype>)((<undefinedtype>)var10).L$0;
                        ResultKt.throwOnFailure(var11);
                     } else {
                        ResultKt.throwOnFailure(var11);
                        Continuation var6 = (Continuation)var10;
                        var3 = ((Number)var1).intValue();
                        if (var3 > 0 && !this.$started$inlined.element) {
                           this.$started$inlined.element = true;
                           FlowCollector var7 = this.$this_flow$inlined;
                           SharingCommand var12 = SharingCommand.START;
                           ((<undefinedtype>)var10).L$0 = this;
                           ((<undefinedtype>)var10).L$1 = var1;
                           ((<undefinedtype>)var10).L$2 = var6;
                           ((<undefinedtype>)var10).I$0 = var3;
                           ((<undefinedtype>)var10).label = 1;
                           if (var7.emit(var12, (Continuation)var10) == var5) {
                              return var5;
                           }
                        }
                     }

                     return Unit.INSTANCE;
                  }
               });
               this.L$0 = var6;
               this.L$1 = var7;
               this.L$2 = var4;
               this.label = 1;
               if (var4.collect(var5, this) == var3) {
                  return var3;
               }
            }

            return Unit.INSTANCE;
         }
      }));
   }

   public String toString() {
      return "SharingStarted.Lazily";
   }
}
