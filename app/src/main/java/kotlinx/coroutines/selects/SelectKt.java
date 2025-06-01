package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.internal.Symbol;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00004\n\u0000\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aE\u0010\u0010\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u00112\u001f\b\u0004\u0010\u0012\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0014\u0012\u0004\u0012\u00020\u00150\u0013¢\u0006\u0002\b\u0016H\u0086Hø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0017\u001aK\u0010\u0018\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u001c\u0010\u001b\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0013H\u0007ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\"\u001c\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001c\u0010\u0006\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\b\u0010\u0005\"\u0016\u0010\t\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003\"\u0016\u0010\u000b\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u0003\"\u0016\u0010\r\u001a\u00020\u000e8\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"},
   d2 = {"ALREADY_SELECTED", "", "getALREADY_SELECTED$annotations", "()V", "getALREADY_SELECTED", "()Ljava/lang/Object;", "NOT_SELECTED", "getNOT_SELECTED$annotations", "getNOT_SELECTED", "RESUMED", "getRESUMED$annotations", "UNDECIDED", "getUNDECIDED$annotations", "selectOpSequenceNumber", "Lkotlinx/coroutines/selects/SeqNumber;", "getSelectOpSequenceNumber$annotations", "select", "R", "builder", "Lkotlin/Function1;", "Lkotlinx/coroutines/selects/SelectBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onTimeout", "timeout", "Lkotlin/time/Duration;", "block", "Lkotlin/coroutines/Continuation;", "onTimeout-0lHKgQg", "(Lkotlinx/coroutines/selects/SelectBuilder;DLkotlin/jvm/functions/Function1;)V", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class SelectKt {
   private static final Object ALREADY_SELECTED = new Symbol("ALREADY_SELECTED");
   private static final Object NOT_SELECTED = new Symbol("NOT_SELECTED");
   private static final Object RESUMED = new Symbol("RESUMED");
   private static final Object UNDECIDED = new Symbol("UNDECIDED");
   private static final SeqNumber selectOpSequenceNumber = new SeqNumber();

   // $FF: synthetic method
   public static final Object access$getRESUMED$p() {
      return RESUMED;
   }

   // $FF: synthetic method
   public static final SeqNumber access$getSelectOpSequenceNumber$p() {
      return selectOpSequenceNumber;
   }

   // $FF: synthetic method
   public static final Object access$getUNDECIDED$p() {
      return UNDECIDED;
   }

   public static final Object getALREADY_SELECTED() {
      return ALREADY_SELECTED;
   }

   // $FF: synthetic method
   public static void getALREADY_SELECTED$annotations() {
   }

   public static final Object getNOT_SELECTED() {
      return NOT_SELECTED;
   }

   // $FF: synthetic method
   public static void getNOT_SELECTED$annotations() {
   }

   // $FF: synthetic method
   private static void getRESUMED$annotations() {
   }

   // $FF: synthetic method
   private static void getSelectOpSequenceNumber$annotations() {
   }

   // $FF: synthetic method
   private static void getUNDECIDED$annotations() {
   }

   public static final void onTimeout_0lHKgQg(SelectBuilder var0, double var1, Function1 var3) {
      var0.onTimeout(DelayKt.toDelayMillis_LRDsOJo(var1), var3);
   }

   public static final Object select(Function1 var0, Continuation var1) {
      SelectBuilderImpl var2 = new SelectBuilderImpl(var1);

      label32:
      try {
         var0.invoke(var2);
      } catch (Throwable var4) {
         var2.handleBuilderException(var4);
         break label32;
      }

      Object var5 = var2.getResult();
      if (var5 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var1);
      }

      return var5;
   }

   private static final Object select$$forInline(Function1 var0, Continuation var1) {
      InlineMarker.mark(0);
      SelectBuilderImpl var2 = new SelectBuilderImpl(var1);

      label32:
      try {
         var0.invoke(var2);
      } catch (Throwable var4) {
         var2.handleBuilderException(var4);
         break label32;
      }

      Object var5 = var2.getResult();
      if (var5 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var1);
      }

      InlineMarker.mark(1);
      return var5;
   }
}
