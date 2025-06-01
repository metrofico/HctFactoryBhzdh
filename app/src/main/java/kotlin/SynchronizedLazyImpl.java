package kotlin;

import java.io.Serializable;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004B\u001f\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\bH\u0002R\u0010\u0010\n\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006X\u0088\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0013"},
   d2 = {"Lkotlin/SynchronizedLazyImpl;", "T", "Lkotlin/Lazy;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "initializer", "Lkotlin/Function0;", "lock", "", "(Lkotlin/jvm/functions/Function0;Ljava/lang/Object;)V", "_value", "value", "getValue", "()Ljava/lang/Object;", "isInitialized", "", "toString", "", "writeReplace", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
final class SynchronizedLazyImpl implements Lazy, Serializable {
   private volatile Object _value;
   private Function0 initializer;
   private final Object lock;

   public SynchronizedLazyImpl(Function0 var1, Object var2) {
      Intrinsics.checkNotNullParameter(var1, "initializer");
      super();
      this.initializer = var1;
      this._value = UNINITIALIZED_VALUE.INSTANCE;
      Object var3 = var2;
      if (var2 == null) {
         var3 = this;
      }

      this.lock = var3;
   }

   // $FF: synthetic method
   public SynchronizedLazyImpl(Function0 var1, Object var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
      }

      this(var1, var2);
   }

   private final Object writeReplace() {
      return new InitializedLazyImpl(this.getValue());
   }

   public Object getValue() {
      Object var1 = this._value;
      if (var1 != UNINITIALIZED_VALUE.INSTANCE) {
         return var1;
      } else {
         Object var2 = this.lock;
         synchronized(var2){}

         try {
            var1 = this._value;
            if (var1 == UNINITIALIZED_VALUE.INSTANCE) {
               Function0 var5 = this.initializer;
               Intrinsics.checkNotNull(var5);
               var1 = var5.invoke();
               this._value = var1;
               this.initializer = null;
            }
         } finally {
            ;
         }

         return var1;
      }
   }

   public boolean isInitialized() {
      boolean var1;
      if (this._value != UNINITIALIZED_VALUE.INSTANCE) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public String toString() {
      String var1;
      if (this.isInitialized()) {
         var1 = String.valueOf(this.getValue());
      } else {
         var1 = "Lazy value not initialized yet.";
      }

      return var1;
   }
}
