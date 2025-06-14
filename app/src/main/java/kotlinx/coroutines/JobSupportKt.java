package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.Symbol;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\u0004\u0018\u00010\u0016H\u0000\u001a\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0016*\u0004\u0018\u00010\u0016H\u0000\"\u0016\u0010\u0000\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003\"\u0016\u0010\u0006\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0003\"\u0016\u0010\b\u001a\u00020\t8\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003\"\u0016\u0010\u000b\u001a\u00020\t8\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u0003\"\u000e\u0010\r\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0010\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0011\u0010\u0003\"\u0016\u0010\u0012\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0013\u0010\u0003\"\u000e\u0010\u0014\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"},
   d2 = {"COMPLETING_ALREADY", "Lkotlinx/coroutines/internal/Symbol;", "getCOMPLETING_ALREADY$annotations", "()V", "COMPLETING_RETRY", "getCOMPLETING_RETRY$annotations", "COMPLETING_WAITING_CHILDREN", "getCOMPLETING_WAITING_CHILDREN$annotations", "EMPTY_ACTIVE", "Lkotlinx/coroutines/Empty;", "getEMPTY_ACTIVE$annotations", "EMPTY_NEW", "getEMPTY_NEW$annotations", "FALSE", "", "RETRY", "SEALED", "getSEALED$annotations", "TOO_LATE_TO_CANCEL", "getTOO_LATE_TO_CANCEL$annotations", "TRUE", "boxIncomplete", "", "unboxState", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class JobSupportKt {
   private static final Symbol COMPLETING_ALREADY = new Symbol("COMPLETING_ALREADY");
   private static final Symbol COMPLETING_RETRY = new Symbol("COMPLETING_RETRY");
   public static final Symbol COMPLETING_WAITING_CHILDREN = new Symbol("COMPLETING_WAITING_CHILDREN");
   private static final Empty EMPTY_ACTIVE = new Empty(true);
   private static final Empty EMPTY_NEW = new Empty(false);
   private static final int FALSE = 0;
   private static final int RETRY = -1;
   private static final Symbol SEALED = new Symbol("SEALED");
   private static final Symbol TOO_LATE_TO_CANCEL = new Symbol("TOO_LATE_TO_CANCEL");
   private static final int TRUE = 1;

   // $FF: synthetic method
   public static final Symbol access$getCOMPLETING_ALREADY$p() {
      return COMPLETING_ALREADY;
   }

   // $FF: synthetic method
   public static final Symbol access$getCOMPLETING_RETRY$p() {
      return COMPLETING_RETRY;
   }

   // $FF: synthetic method
   public static final Empty access$getEMPTY_ACTIVE$p() {
      return EMPTY_ACTIVE;
   }

   // $FF: synthetic method
   public static final Empty access$getEMPTY_NEW$p() {
      return EMPTY_NEW;
   }

   // $FF: synthetic method
   public static final Symbol access$getSEALED$p() {
      return SEALED;
   }

   // $FF: synthetic method
   public static final Symbol access$getTOO_LATE_TO_CANCEL$p() {
      return TOO_LATE_TO_CANCEL;
   }

   public static final Object boxIncomplete(Object var0) {
      Object var1 = var0;
      if (var0 instanceof Incomplete) {
         var1 = new IncompleteStateBox((Incomplete)var0);
      }

      return var1;
   }

   // $FF: synthetic method
   private static void getCOMPLETING_ALREADY$annotations() {
   }

   // $FF: synthetic method
   private static void getCOMPLETING_RETRY$annotations() {
   }

   // $FF: synthetic method
   public static void getCOMPLETING_WAITING_CHILDREN$annotations() {
   }

   // $FF: synthetic method
   private static void getEMPTY_ACTIVE$annotations() {
   }

   // $FF: synthetic method
   private static void getEMPTY_NEW$annotations() {
   }

   // $FF: synthetic method
   private static void getSEALED$annotations() {
   }

   // $FF: synthetic method
   private static void getTOO_LATE_TO_CANCEL$annotations() {
   }

   public static final Object unboxState(Object var0) {
      Object var1;
      if (!(var0 instanceof IncompleteStateBox)) {
         var1 = null;
      } else {
         var1 = var0;
      }

      IncompleteStateBox var2 = (IncompleteStateBox)var1;
      var1 = var0;
      if (var2 != null) {
         Incomplete var3 = var2.state;
         var1 = var0;
         if (var3 != null) {
            var1 = var3;
         }
      }

      return var1;
   }
}
