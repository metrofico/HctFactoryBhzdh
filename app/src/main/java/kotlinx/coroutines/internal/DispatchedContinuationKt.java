package kotlinx.coroutines.internal;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.CompletionStateKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadLocalEventLoop;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a;\u0010\u0006\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0082\b\u001aU\u0010\u0011\u001a\u00020\u0010\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00152%\b\u0002\u0010\u0016\u001a\u001f\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0017H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a\u0012\u0010\u001d\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00100\bH\u0000\"\u0016\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"},
   d2 = {"REUSABLE_CLAIMED", "Lkotlinx/coroutines/internal/Symbol;", "getREUSABLE_CLAIMED$annotations", "()V", "UNDEFINED", "getUNDEFINED$annotations", "executeUnconfined", "", "Lkotlinx/coroutines/internal/DispatchedContinuation;", "contState", "", "mode", "", "doYield", "block", "Lkotlin/Function0;", "", "resumeCancellableWith", "T", "Lkotlin/coroutines/Continuation;", "result", "Lkotlin/Result;", "onCancellation", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "yieldUndispatched", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class DispatchedContinuationKt {
   public static final Symbol REUSABLE_CLAIMED = new Symbol("REUSABLE_CLAIMED");
   private static final Symbol UNDEFINED = new Symbol("UNDEFINED");

   // $FF: synthetic method
   public static final Symbol access$getUNDEFINED$p() {
      return UNDEFINED;
   }

   private static final boolean executeUnconfined(DispatchedContinuation var0, Object var1, int var2, boolean var3, Function0 var4) {
      boolean var7 = DebugKt.getASSERTIONS_ENABLED();
      boolean var6 = false;
      if (var7) {
         boolean var5;
         if (var2 != -1) {
            var5 = true;
         } else {
            var5 = false;
         }

         if (!var5) {
            throw (Throwable)(new AssertionError());
         }
      }

      EventLoop var8 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
      if (var3 && var8.isUnconfinedQueueEmpty()) {
         return false;
      } else {
         if (var8.isUnconfinedLoopActive()) {
            var0._state = var1;
            var0.resumeMode = var2;
            var8.dispatchUnconfined((DispatchedTask)var0);
            var3 = true;
         } else {
            DispatchedTask var22 = (DispatchedTask)var0;
            var8.incrementUseCount(true);

            label225: {
               Throwable var10000;
               label214: {
                  boolean var10001;
                  try {
                     var4.invoke();
                  } catch (Throwable var21) {
                     var10000 = var21;
                     var10001 = false;
                     break label214;
                  }

                  while(true) {
                     try {
                        var3 = var8.processUnconfinedEvent();
                     } catch (Throwable var20) {
                        var10000 = var20;
                        var10001 = false;
                        break;
                     }

                     if (!var3) {
                        InlineMarker.finallyStart(1);
                        break label225;
                     }
                  }
               }

               Throwable var23 = var10000;
               boolean var12 = false;

               try {
                  var12 = true;
                  var22.handleFatalException$kotlinx_coroutines_core(var23, (Throwable)null);
                  var12 = false;
               } finally {
                  if (var12) {
                     InlineMarker.finallyStart(1);
                     var8.decrementUseCount(true);
                     InlineMarker.finallyEnd(1);
                  }
               }

               InlineMarker.finallyStart(1);
            }

            var8.decrementUseCount(true);
            InlineMarker.finallyEnd(1);
            var3 = var6;
         }

         return var3;
      }
   }

   // $FF: synthetic method
   static boolean executeUnconfined$default(DispatchedContinuation var0, Object var1, int var2, boolean var3, Function0 var4, int var5, Object var6) {
      boolean var7 = false;
      if ((var5 & 4) != 0) {
         var3 = false;
      }

      if (DebugKt.getASSERTIONS_ENABLED()) {
         boolean var23;
         if (var2 != -1) {
            var23 = true;
         } else {
            var23 = false;
         }

         if (!var23) {
            throw (Throwable)(new AssertionError());
         }
      }

      EventLoop var24 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
      if (var3 && var24.isUnconfinedQueueEmpty()) {
         return false;
      } else {
         if (var24.isUnconfinedLoopActive()) {
            var0._state = var1;
            var0.resumeMode = var2;
            var24.dispatchUnconfined((DispatchedTask)var0);
            var3 = true;
         } else {
            DispatchedTask var21 = (DispatchedTask)var0;
            var24.incrementUseCount(true);

            label241: {
               Throwable var10000;
               label229: {
                  boolean var10001;
                  try {
                     var4.invoke();
                  } catch (Throwable var20) {
                     var10000 = var20;
                     var10001 = false;
                     break label229;
                  }

                  while(true) {
                     try {
                        var3 = var24.processUnconfinedEvent();
                     } catch (Throwable var19) {
                        var10000 = var19;
                        var10001 = false;
                        break;
                     }

                     if (!var3) {
                        InlineMarker.finallyStart(1);
                        break label241;
                     }
                  }
               }

               Throwable var22 = var10000;
               boolean var11 = false;

               try {
                  var11 = true;
                  var21.handleFatalException$kotlinx_coroutines_core(var22, (Throwable)null);
                  var11 = false;
               } finally {
                  if (var11) {
                     InlineMarker.finallyStart(1);
                     var24.decrementUseCount(true);
                     InlineMarker.finallyEnd(1);
                  }
               }

               InlineMarker.finallyStart(1);
            }

            var24.decrementUseCount(true);
            InlineMarker.finallyEnd(1);
            var3 = var7;
         }

         return var3;
      }
   }

   // $FF: synthetic method
   public static void getREUSABLE_CLAIMED$annotations() {
   }

   // $FF: synthetic method
   private static void getUNDEFINED$annotations() {
   }

   public static final void resumeCancellableWith(Continuation var0, Object var1, Function1 var2) {
      if (var0 instanceof DispatchedContinuation) {
         DispatchedContinuation var5 = (DispatchedContinuation)var0;
         Object var7 = CompletionStateKt.toState(var1, var2);
         if (var5.dispatcher.isDispatchNeeded(var5.getContext())) {
            var5._state = var7;
            var5.resumeMode = 1;
            var5.dispatcher.dispatch(var5.getContext(), (Runnable)var5);
         } else {
            DebugKt.getASSERTIONS_ENABLED();
            EventLoop var76 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
            if (var76.isUnconfinedLoopActive()) {
               var5._state = var7;
               var5.resumeMode = 1;
               var76.dispatchUnconfined((DispatchedTask)var5);
            } else {
               DispatchedTask var79 = (DispatchedTask)var5;
               var76.incrementUseCount(true);

               Throwable var10000;
               label698: {
                  Job var6;
                  boolean var10001;
                  try {
                     var6 = (Job)var5.getContext().get((CoroutineContext.Key)Job.Key);
                  } catch (Throwable var75) {
                     var10000 = var75;
                     var10001 = false;
                     break label698;
                  }

                  boolean var3;
                  label684: {
                     label683: {
                        if (var6 != null) {
                           try {
                              if (!var6.isActive()) {
                                 CancellationException var80 = var6.getCancellationException();
                                 var5.cancelCompletedResult$kotlinx_coroutines_core(var7, (Throwable)var80);
                                 Result.Companion var82 = Result.Companion;
                                 var5.resumeWith(Result.constructor_impl(ResultKt.createFailure((Throwable)var80)));
                                 break label683;
                              }
                           } catch (Throwable var74) {
                              var10000 = var74;
                              var10001 = false;
                              break label698;
                           }
                        }

                        var3 = false;
                        break label684;
                     }

                     var3 = true;
                  }

                  if (!var3) {
                     Object var81;
                     CoroutineContext var83;
                     try {
                        var83 = var5.getContext();
                        var81 = ThreadContextKt.updateThreadContext(var83, var5.countOrElement);
                     } catch (Throwable var73) {
                        var10000 = var73;
                        var10001 = false;
                        break label698;
                     }

                     try {
                        var5.continuation.resumeWith(var1);
                        Unit var77 = Unit.INSTANCE;
                     } finally {
                        try {
                           ThreadContextKt.restoreThreadContext(var83, var81);
                        } catch (Throwable var70) {
                           var10000 = var70;
                           var10001 = false;
                           break label698;
                        }
                     }
                  }

                  while(true) {
                     boolean var4;
                     try {
                        var4 = var76.processUnconfinedEvent();
                     } catch (Throwable var71) {
                        var10000 = var71;
                        var10001 = false;
                        break;
                     }

                     if (!var4) {
                        return;
                     }
                  }
               }

               Throwable var78 = var10000;

               try {
                  var79.handleFatalException$kotlinx_coroutines_core(var78, (Throwable)null);
               } finally {
                  var76.decrementUseCount(true);
               }
            }
         }
      } else {
         var0.resumeWith(var1);
      }

   }

   // $FF: synthetic method
   public static void resumeCancellableWith$default(Continuation var0, Object var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
         Function1 var5 = (Function1)null;
      }

      resumeCancellableWith(var0, var1, var2);
   }

   public static final boolean yieldUndispatched(DispatchedContinuation var0) {
      Unit var4 = Unit.INSTANCE;
      DebugKt.getASSERTIONS_ENABLED();
      EventLoop var3 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
      boolean var2 = var3.isUnconfinedQueueEmpty();
      boolean var1 = false;
      if (!var2) {
         if (var3.isUnconfinedLoopActive()) {
            var0._state = var4;
            var0.resumeMode = 1;
            var3.dispatchUnconfined((DispatchedTask)var0);
            var1 = true;
         } else {
            DispatchedTask var18 = (DispatchedTask)var0;
            var3.incrementUseCount(true);

            Throwable var10000;
            label135: {
               boolean var10001;
               try {
                  var0.run();
               } catch (Throwable var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label135;
               }

               while(true) {
                  try {
                     var2 = var3.processUnconfinedEvent();
                  } catch (Throwable var15) {
                     var10000 = var15;
                     var10001 = false;
                     break;
                  }

                  if (!var2) {
                     return var1;
                  }
               }
            }

            Throwable var17 = var10000;

            try {
               var18.handleFatalException$kotlinx_coroutines_core(var17, (Throwable)null);
            } finally {
               var3.decrementUseCount(true);
            }
         }
      }

      return var1;
   }
}
