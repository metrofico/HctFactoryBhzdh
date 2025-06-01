package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0087@\u0018\u0000 \u001f*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0002\u001e\u001fB\u0016\b\u0000\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0016\u001a\u00020\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u000f\u0010\u001a\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00078F¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\u00028\u00008F¢\u0006\f\u0012\u0004\b\u0011\u0010\t\u001a\u0004\b\u0012\u0010\u0005R\u0019\u0010\u0013\u001a\u0004\u0018\u00018\u00008F¢\u0006\f\u0012\u0004\b\u0014\u0010\t\u001a\u0004\b\u0015\u0010\u0005ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "},
   d2 = {"Lkotlinx/coroutines/channels/ValueOrClosed;", "T", "", "holder", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "closeCause", "", "getCloseCause$annotations", "()V", "getCloseCause-impl", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "isClosed", "", "isClosed-impl", "(Ljava/lang/Object;)Z", "value", "getValue$annotations", "getValue-impl", "valueOrNull", "getValueOrNull$annotations", "getValueOrNull-impl", "equals", "other", "hashCode", "", "toString", "", "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "Closed", "Companion", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class ValueOrClosed {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final Object holder;

   // $FF: synthetic method
   private ValueOrClosed(Object var1) {
      this.holder = var1;
   }

   // $FF: synthetic method
   public static final ValueOrClosed box_impl(Object var0) {
      return new ValueOrClosed(var0);
   }

   public static Object constructor_impl(Object var0) {
      return var0;
   }

   public static boolean equals_impl(Object var0, Object var1) {
      return var1 instanceof ValueOrClosed && Intrinsics.areEqual(var0, ((ValueOrClosed)var1).unbox_impl());
   }

   public static final boolean equals_impl0(Object var0, Object var1) {
      return Intrinsics.areEqual(var0, var1);
   }

   // $FF: synthetic method
   public static void getCloseCause$annotations() {
   }

   public static final Throwable getCloseCause_impl(Object var0) {
      if (var0 instanceof Closed) {
         return ((Closed)var0).cause;
      } else {
         throw (Throwable)(new IllegalStateException("Channel was not closed".toString()));
      }
   }

   // $FF: synthetic method
   public static void getValue$annotations() {
   }

   public static final Object getValue_impl(Object var0) {
      if (!(var0 instanceof Closed)) {
         return var0;
      } else {
         throw (Throwable)(new IllegalStateException("Channel was closed".toString()));
      }
   }

   // $FF: synthetic method
   public static void getValueOrNull$annotations() {
   }

   public static final Object getValueOrNull_impl(Object var0) {
      Object var1 = var0;
      if (var0 instanceof Closed) {
         var1 = null;
      }

      return var1;
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
      return var0 instanceof Closed;
   }

   public static String toString_impl(Object var0) {
      String var1;
      if (var0 instanceof Closed) {
         var1 = var0.toString();
      } else {
         var1 = "Value(" + var0 + ')';
      }

      return var1;
   }

   public boolean equals(Object var1) {
      return equals_impl(this.holder, var1);
   }

   public int hashCode() {
      return hashCode_impl(this.holder);
   }

   public String toString() {
      return toString_impl(this.holder);
   }

   // $FF: synthetic method
   public final Object unbox_impl() {
      return this.holder;
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"},
      d2 = {"Lkotlinx/coroutines/channels/ValueOrClosed$Closed;", "", "cause", "", "(Ljava/lang/Throwable;)V", "equals", "", "other", "hashCode", "", "toString", "", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public static final class Closed {
      public final Throwable cause;

      public Closed(Throwable var1) {
         this.cause = var1;
      }

      public boolean equals(Object var1) {
         boolean var2;
         if (var1 instanceof Closed && Intrinsics.areEqual((Object)this.cause, (Object)((Closed)var1).cause)) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      public int hashCode() {
         Throwable var2 = this.cause;
         int var1;
         if (var2 != null) {
            var1 = var2.hashCode();
         } else {
            var1 = 0;
         }

         return var1;
      }

      public String toString() {
         return "Closed(" + this.cause + ')';
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0080\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\tJ*\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\n\u001a\u0002H\u0005H\u0080\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\f\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\r"},
      d2 = {"Lkotlinx/coroutines/channels/ValueOrClosed$Companion;", "", "()V", "closed", "Lkotlinx/coroutines/channels/ValueOrClosed;", "E", "cause", "", "closed-ZYPwvRU$kotlinx_coroutines_core", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "value", "value-ZYPwvRU$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }

      public final Object closed_ZYPwvRU$kotlinx_coroutines_core(Throwable var1) {
         return ValueOrClosed.constructor_impl(new Closed(var1));
      }

      public final Object value_ZYPwvRU$kotlinx_coroutines_core(Object var1) {
         return ValueOrClosed.constructor_impl(var1);
      }
   }
}
