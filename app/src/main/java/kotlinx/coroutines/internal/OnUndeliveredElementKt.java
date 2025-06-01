package kotlinx.coroutines.internal;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aI\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001\"\u0004\b\u0000\u0010\u0004*\u0018\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u00030\u0001j\b\u0012\u0004\u0012\u0002H\u0004`\u00052\u0006\u0010\u0006\u001a\u0002H\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\u0010\t\u001a=\u0010\n\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0004*\u0018\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u00030\u0001j\b\u0012\u0004\u0012\u0002H\u0004`\u00052\u0006\u0010\u0006\u001a\u0002H\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\u0010\u000b\u001aC\u0010\f\u001a\u0004\u0018\u00010\r\"\u0004\b\u0000\u0010\u0004*\u0018\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u00030\u0001j\b\u0012\u0004\u0012\u0002H\u0004`\u00052\u0006\u0010\u0006\u001a\u0002H\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0000¢\u0006\u0002\u0010\u000f**\b\u0000\u0010\u0010\u001a\u0004\b\u0000\u0010\u0004\"\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u00030\u00012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u00030\u0001¨\u0006\u0011"},
   d2 = {"bindCancellationFun", "Lkotlin/Function1;", "", "", "E", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "element", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)Lkotlin/jvm/functions/Function1;", "callUndeliveredElement", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)V", "callUndeliveredElementCatchingException", "Lkotlinx/coroutines/internal/UndeliveredElementException;", "undeliveredElementException", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Lkotlinx/coroutines/internal/UndeliveredElementException;)Lkotlinx/coroutines/internal/UndeliveredElementException;", "OnUndeliveredElement", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class OnUndeliveredElementKt {
   public static final Function1 bindCancellationFun(Function1 var0, Object var1, CoroutineContext var2) {
      return (Function1)(new Function1(var0, var1, var2) {
         final CoroutineContext $context;
         final Object $element;
         final Function1 $this_bindCancellationFun;

         {
            this.$this_bindCancellationFun = var1;
            this.$element = var2;
            this.$context = var3;
         }

         public final void invoke(Throwable var1) {
            OnUndeliveredElementKt.callUndeliveredElement(this.$this_bindCancellationFun, this.$element, this.$context);
         }
      });
   }

   public static final void callUndeliveredElement(Function1 var0, Object var1, CoroutineContext var2) {
      UndeliveredElementException var3 = callUndeliveredElementCatchingException(var0, var1, (UndeliveredElementException)null);
      if (var3 != null) {
         CoroutineExceptionHandlerKt.handleCoroutineException(var2, (Throwable)var3);
      }

   }

   public static final UndeliveredElementException callUndeliveredElementCatchingException(Function1 var0, Object var1, UndeliveredElementException var2) {
      try {
         var0.invoke(var1);
      } catch (Throwable var4) {
         if (var2 == null || var2.getCause() == var4) {
            return new UndeliveredElementException("Exception in undelivered element handler for " + var1, var4);
         }

         ExceptionsKt.addSuppressed((Throwable)var2, var4);
         return var2;
      }

      return var2;
   }

   // $FF: synthetic method
   public static UndeliveredElementException callUndeliveredElementCatchingException$default(Function1 var0, Object var1, UndeliveredElementException var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
         UndeliveredElementException var5 = (UndeliveredElementException)null;
      }

      return callUndeliveredElementCatchingException(var0, var1, var2);
   }
}
