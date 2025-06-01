package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080@\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\u0014\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\u0010\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\u00028\u00008F¢\u0006\f\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"},
   d2 = {"Lkotlinx/coroutines/internal/SegmentOrClosed;", "S", "Lkotlinx/coroutines/internal/Segment;", "", "value", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "isClosed", "", "isClosed-impl", "(Ljava/lang/Object;)Z", "segment", "getSegment$annotations", "()V", "getSegment-impl", "(Ljava/lang/Object;)Lkotlinx/coroutines/internal/Segment;", "equals", "other", "hashCode", "", "toString", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class SegmentOrClosed {
   private final Object value;

   // $FF: synthetic method
   private SegmentOrClosed(Object var1) {
      this.value = var1;
   }

   // $FF: synthetic method
   public static final SegmentOrClosed box_impl(Object var0) {
      return new SegmentOrClosed(var0);
   }

   public static Object constructor_impl(Object var0) {
      return var0;
   }

   public static boolean equals_impl(Object var0, Object var1) {
      return var1 instanceof SegmentOrClosed && Intrinsics.areEqual(var0, ((SegmentOrClosed)var1).unbox_impl());
   }

   public static final boolean equals_impl0(Object var0, Object var1) {
      return Intrinsics.areEqual(var0, var1);
   }

   // $FF: synthetic method
   public static void getSegment$annotations() {
   }

   public static final Segment getSegment_impl(Object var0) {
      if (var0 != ConcurrentLinkedListKt.access$getCLOSED$p()) {
         if (var0 != null) {
            return (Segment)var0;
         } else {
            throw new NullPointerException("null cannot be cast to non-null type S");
         }
      } else {
         throw (Throwable)(new IllegalStateException("Does not contain segment".toString()));
      }
   }

   public static int hashCode_impl(Object var0) {
      int var1;
      if (var0 != null) {
         var1 = var0.hashCode();
      } else {
         var1 = 0;
      }

      return var1;
   }

   public static final boolean isClosed_impl(Object var0) {
      boolean var1;
      if (var0 == ConcurrentLinkedListKt.access$getCLOSED$p()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static String toString_impl(Object var0) {
      return "SegmentOrClosed(value=" + var0 + ")";
   }

   public boolean equals(Object var1) {
      return equals_impl(this.value, var1);
   }

   public int hashCode() {
      return hashCode_impl(this.value);
   }

   public String toString() {
      return toString_impl(this.value);
   }

   // $FF: synthetic method
   public final Object unbox_impl() {
      return this.value;
   }
}
