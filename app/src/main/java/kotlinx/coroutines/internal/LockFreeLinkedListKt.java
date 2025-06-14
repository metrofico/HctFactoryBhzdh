package kotlinx.coroutines.internal;

import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00008\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012*\u00020\u0001H\u0001\"\u001c\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u0016\u0010\u0006\u001a\u00020\u00078\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003\"\u001c\u0010\t\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0003\u001a\u0004\b\u000b\u0010\u0005\"\u0016\u0010\f\u001a\u00020\u00078\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\r\u0010\u0003\"\u0016\u0010\u000e\u001a\u00020\u00078\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0003*\n\u0010\u0013\"\u00020\u00142\u00020\u0014*\u001c\u0010\u0015\u001a\u0004\b\u0000\u0010\u0016\"\b\u0012\u0004\u0012\u0002H\u00160\u00172\b\u0012\u0004\u0012\u0002H\u00160\u0017*\f\b\u0002\u0010\u0018\"\u00020\u00112\u00020\u0011*\n\u0010\u0019\"\u00020\u001a2\u00020\u001a*\u001c\u0010\u001b\u001a\u0004\b\u0000\u0010\u0016\"\b\u0012\u0004\u0012\u0002H\u00160\u001c2\b\u0012\u0004\u0012\u0002H\u00160\u001c¨\u0006\u001d"},
   d2 = {"CONDITION_FALSE", "", "getCONDITION_FALSE$annotations", "()V", "getCONDITION_FALSE", "()Ljava/lang/Object;", "FAILURE", "", "getFAILURE$annotations", "LIST_EMPTY", "getLIST_EMPTY$annotations", "getLIST_EMPTY", "SUCCESS", "getSUCCESS$annotations", "UNDECIDED", "getUNDECIDED$annotations", "unwrap", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "AbstractAtomicDesc", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "AddLastDesc", "T", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$AddLastDesc;", "Node", "PrepareOp", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$PrepareOp;", "RemoveFirstDesc", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class LockFreeLinkedListKt {
   private static final Object CONDITION_FALSE = new Symbol("CONDITION_FALSE");
   public static final int FAILURE = 2;
   private static final Object LIST_EMPTY = new Symbol("LIST_EMPTY");
   public static final int SUCCESS = 1;
   public static final int UNDECIDED = 0;

   public static final Object getCONDITION_FALSE() {
      return CONDITION_FALSE;
   }

   // $FF: synthetic method
   public static void getCONDITION_FALSE$annotations() {
   }

   // $FF: synthetic method
   public static void getFAILURE$annotations() {
   }

   public static final Object getLIST_EMPTY() {
      return LIST_EMPTY;
   }

   // $FF: synthetic method
   public static void getLIST_EMPTY$annotations() {
   }

   // $FF: synthetic method
   public static void getSUCCESS$annotations() {
   }

   // $FF: synthetic method
   public static void getUNDECIDED$annotations() {
   }

   public static final LockFreeLinkedListNode unwrap(Object var0) {
      Object var1;
      if (!(var0 instanceof Removed)) {
         var1 = null;
      } else {
         var1 = var0;
      }

      Removed var3 = (Removed)var1;
      LockFreeLinkedListNode var2;
      if (var3 != null) {
         LockFreeLinkedListNode var4 = var3.ref;
         if (var4 != null) {
            var2 = var4;
            return var2;
         }
      }

      if (var0 == null) {
         throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
      } else {
         var2 = (LockFreeLinkedListNode)var0;
         return var2;
      }
   }
}
