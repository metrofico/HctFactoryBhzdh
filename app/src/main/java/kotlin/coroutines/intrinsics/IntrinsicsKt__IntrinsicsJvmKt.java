package kotlin.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

@Metadata(
   d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aF\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0004\b\u0000\u0010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00012\u001c\b\u0004\u0010\u0005\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0083\b¢\u0006\u0002\b\b\u001aD\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0004\b\u0000\u0010\u0003*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a]\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0003*#\b\u0001\u0012\u0004\u0012\u0002H\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\f¢\u0006\u0002\b\r2\u0006\u0010\u000e\u001a\u0002H\u000b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0007\u001aA\u0010\u0011\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0003*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001aZ\u0010\u0011\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0003*#\b\u0001\u0012\u0004\u0012\u0002H\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\f¢\u0006\u0002\b\r2\u0006\u0010\u000e\u001a\u0002H\u000b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001an\u0010\u0011\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0014\"\u0004\b\u0002\u0010\u0003*)\b\u0001\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0015¢\u0006\u0002\b\r2\u0006\u0010\u000e\u001a\u0002H\u000b2\u0006\u0010\u0016\u001a\u0002H\u00142\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0001H\u0081\bø\u0001\u0000¢\u0006\u0002\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"},
   d2 = {"createCoroutineFromSuspendFunction", "Lkotlin/coroutines/Continuation;", "", "T", "completion", "block", "Lkotlin/Function1;", "", "createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt", "createCoroutineUnintercepted", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "intercepted", "startCoroutineUninterceptedOrReturn", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "P", "Lkotlin/Function3;", "param", "(Lkotlin/jvm/functions/Function3;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/coroutines/intrinsics/IntrinsicsKt"
)
class IntrinsicsKt__IntrinsicsJvmKt {
   public IntrinsicsKt__IntrinsicsJvmKt() {
   }

   private static final Continuation createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt(Continuation var0, Function1 var1) {
      CoroutineContext var2 = var0.getContext();
      if (var2 == EmptyCoroutineContext.INSTANCE) {
         var0 = (Continuation)(new RestrictedContinuationImpl(var0, var1) {
            final Function1 $block;
            private int label;

            public {
               this.$block = var2;
               Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }

            protected Object invokeSuspend(Object var1) {
               int var2 = this.label;
               if (var2 != 0) {
                  if (var2 != 1) {
                     throw new IllegalStateException("This coroutine had already completed".toString());
                  }

                  this.label = 2;
                  ResultKt.throwOnFailure(var1);
               } else {
                  this.label = 1;
                  ResultKt.throwOnFailure(var1);
                  var1 = this.$block.invoke(this);
               }

               return var1;
            }
         });
      } else {
         var0 = (Continuation)(new ContinuationImpl(var0, var2, var1) {
            final Function1 $block;
            private int label;

            public {
               this.$block = var3;
               Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }

            protected Object invokeSuspend(Object var1) {
               int var2 = this.label;
               if (var2 != 0) {
                  if (var2 != 1) {
                     throw new IllegalStateException("This coroutine had already completed".toString());
                  }

                  this.label = 2;
                  ResultKt.throwOnFailure(var1);
               } else {
                  this.label = 1;
                  ResultKt.throwOnFailure(var1);
                  var1 = this.$block.invoke(this);
               }

               return var1;
            }
         });
      }

      return var0;
   }

   public static final Continuation createCoroutineUnintercepted(Function1 var0, Continuation var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "completion");
      Continuation var2 = DebugProbesKt.probeCoroutineCreated(var1);
      Continuation var3;
      if (var0 instanceof BaseContinuationImpl) {
         var3 = ((BaseContinuationImpl)var0).create(var2);
      } else {
         CoroutineContext var4 = var2.getContext();
         if (var4 == EmptyCoroutineContext.INSTANCE) {
            var3 = (Continuation)(new RestrictedContinuationImpl(var2, var0) {
               final Function1 $this_createCoroutineUnintercepted$inlined;
               private int label;

               public {
                  this.$this_createCoroutineUnintercepted$inlined = var2;
                  Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
               }

               protected Object invokeSuspend(Object var1) {
                  int var2 = this.label;
                  if (var2 != 0) {
                     if (var2 != 1) {
                        throw new IllegalStateException("This coroutine had already completed".toString());
                     }

                     this.label = 2;
                     ResultKt.throwOnFailure(var1);
                  } else {
                     this.label = 1;
                     ResultKt.throwOnFailure(var1);
                     Continuation var3 = (Continuation)this;
                     Intrinsics.checkNotNull(this.$this_createCoroutineUnintercepted$inlined, "null cannot be cast to non-null type kotlin.Function1<kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda-0>, kotlin.Any?>");
                     var1 = ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(this.$this_createCoroutineUnintercepted$inlined, 1)).invoke(var3);
                  }

                  return var1;
               }
            });
         } else {
            var3 = (Continuation)(new ContinuationImpl(var2, var4, var0) {
               final Function1 $this_createCoroutineUnintercepted$inlined;
               private int label;

               public {
                  this.$this_createCoroutineUnintercepted$inlined = var3;
                  Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
               }

               protected Object invokeSuspend(Object var1) {
                  int var2 = this.label;
                  if (var2 != 0) {
                     if (var2 != 1) {
                        throw new IllegalStateException("This coroutine had already completed".toString());
                     }

                     this.label = 2;
                     ResultKt.throwOnFailure(var1);
                  } else {
                     this.label = 1;
                     ResultKt.throwOnFailure(var1);
                     Continuation var3 = (Continuation)this;
                     Intrinsics.checkNotNull(this.$this_createCoroutineUnintercepted$inlined, "null cannot be cast to non-null type kotlin.Function1<kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda-0>, kotlin.Any?>");
                     var1 = ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(this.$this_createCoroutineUnintercepted$inlined, 1)).invoke(var3);
                  }

                  return var1;
               }
            });
         }
      }

      return var3;
   }

   public static final Continuation createCoroutineUnintercepted(Function2 var0, Object var1, Continuation var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "completion");
      var2 = DebugProbesKt.probeCoroutineCreated(var2);
      Continuation var4;
      if (var0 instanceof BaseContinuationImpl) {
         var4 = ((BaseContinuationImpl)var0).create(var1, var2);
      } else {
         CoroutineContext var3 = var2.getContext();
         if (var3 == EmptyCoroutineContext.INSTANCE) {
            var4 = (Continuation)(new RestrictedContinuationImpl(var2, var0, var1) {
               final Object $receiver$inlined;
               final Function2 $this_createCoroutineUnintercepted$inlined;
               private int label;

               public {
                  this.$this_createCoroutineUnintercepted$inlined = var2;
                  this.$receiver$inlined = var3;
                  Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
               }

               protected Object invokeSuspend(Object var1) {
                  int var2 = this.label;
                  if (var2 != 0) {
                     if (var2 != 1) {
                        throw new IllegalStateException("This coroutine had already completed".toString());
                     }

                     this.label = 2;
                     ResultKt.throwOnFailure(var1);
                  } else {
                     this.label = 1;
                     ResultKt.throwOnFailure(var1);
                     Continuation var3 = (Continuation)this;
                     Intrinsics.checkNotNull(this.$this_createCoroutineUnintercepted$inlined, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda-1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda-1>, kotlin.Any?>");
                     var1 = ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(this.$this_createCoroutineUnintercepted$inlined, 2)).invoke(this.$receiver$inlined, var3);
                  }

                  return var1;
               }
            });
         } else {
            var4 = (Continuation)(new ContinuationImpl(var2, var3, var0, var1) {
               final Object $receiver$inlined;
               final Function2 $this_createCoroutineUnintercepted$inlined;
               private int label;

               public {
                  this.$this_createCoroutineUnintercepted$inlined = var3;
                  this.$receiver$inlined = var4;
                  Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
               }

               protected Object invokeSuspend(Object var1) {
                  int var2 = this.label;
                  if (var2 != 0) {
                     if (var2 != 1) {
                        throw new IllegalStateException("This coroutine had already completed".toString());
                     }

                     this.label = 2;
                     ResultKt.throwOnFailure(var1);
                  } else {
                     this.label = 1;
                     ResultKt.throwOnFailure(var1);
                     Continuation var3 = (Continuation)this;
                     Intrinsics.checkNotNull(this.$this_createCoroutineUnintercepted$inlined, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda-1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda-1>, kotlin.Any?>");
                     var1 = ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(this.$this_createCoroutineUnintercepted$inlined, 2)).invoke(this.$receiver$inlined, var3);
                  }

                  return var1;
               }
            });
         }
      }

      return var4;
   }

   public static final Continuation intercepted(Continuation var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      ContinuationImpl var1;
      if (var0 instanceof ContinuationImpl) {
         var1 = (ContinuationImpl)var0;
      } else {
         var1 = null;
      }

      Continuation var2 = var0;
      if (var1 != null) {
         var2 = var1.intercepted();
         if (var2 == null) {
            var2 = var0;
         }
      }

      return var2;
   }

   private static final Object startCoroutineUninterceptedOrReturn(Function1 var0, Continuation var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "completion");
      return ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(var0, 1)).invoke(var1);
   }

   private static final Object startCoroutineUninterceptedOrReturn(Function2 var0, Object var1, Continuation var2) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var2, "completion");
      return ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(var0, 2)).invoke(var1, var2);
   }

   private static final Object startCoroutineUninterceptedOrReturn(Function3 var0, Object var1, Object var2, Continuation var3) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var3, "completion");
      return ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(var0, 3)).invoke(var1, var2, var3);
   }
}
