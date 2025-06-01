package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u001a#\u0010\u0002\u001a\u00028\u0000\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0000*\u00028\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001ao\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r\"\u000e\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u00028\u00000\u0004*\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u000628\u0010\f\u001a4\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0007\u0012\u0015\u0012\u0013\u0018\u00018\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00028\u00000\bH\u0082\bø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\"\u001c\u0010\u0011\u001a\u00020\u00108\u0002@\u0003X\u0083\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u0012\u0004\b\u0013\u0010\u0014\"\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"},
   d2 = {"Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "N", "close", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "Lkotlinx/coroutines/internal/Segment;", "S", "", "id", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "prev", "createNewSegment", "Lkotlinx/coroutines/internal/SegmentOrClosed;", "findSegmentInternal", "(Lkotlinx/coroutines/internal/Segment;JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/Symbol;", "CLOSED", "Lkotlinx/coroutines/internal/Symbol;", "getCLOSED$annotations", "()V", "", "POINTERS_SHIFT", "I", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class ConcurrentLinkedListKt {
   private static final Symbol CLOSED = new Symbol("CLOSED");
   private static final int POINTERS_SHIFT = 16;

   // $FF: synthetic method
   public static final Object access$findSegmentInternal(Segment var0, long var1, Function2 var3) {
      return findSegmentInternal(var0, var1, var3);
   }

   // $FF: synthetic method
   public static final Symbol access$getCLOSED$p() {
      return CLOSED;
   }

   public static final ConcurrentLinkedListNode close(ConcurrentLinkedListNode var0) {
      while(true) {
         Object var1 = ConcurrentLinkedListNode.access$getNextOrClosed$p(var0);
         if (var1 == access$getCLOSED$p()) {
            return var0;
         }

         ConcurrentLinkedListNode var2 = (ConcurrentLinkedListNode)var1;
         if (var2 == null) {
            if (var0.markAsClosed()) {
               return var0;
            }
         } else {
            var0 = var2;
         }
      }
   }

   private static final Object findSegmentInternal(Segment var0, long var1, Function2 var3) {
      Segment var4 = var0;

      while(true) {
         while(true) {
            if (var4.getId() >= var1 && !var4.getRemoved()) {
               return SegmentOrClosed.constructor_impl(var4);
            }

            Object var6 = ConcurrentLinkedListNode.access$getNextOrClosed$p((ConcurrentLinkedListNode)var4);
            if (var6 == access$getCLOSED$p()) {
               return SegmentOrClosed.constructor_impl(access$getCLOSED$p());
            }

            var0 = (Segment)((ConcurrentLinkedListNode)var6);
            if (var0 != null) {
               break;
            }

            Segment var5 = (Segment)var3.invoke(var4.getId() + 1L, var4);
            if (var4.trySetNext((ConcurrentLinkedListNode)var5)) {
               var0 = var5;
               if (var4.getRemoved()) {
                  var4.remove();
                  var0 = var5;
               }
               break;
            }
         }

         var4 = var0;
      }
   }

   // $FF: synthetic method
   private static void getCLOSED$annotations() {
   }
}
