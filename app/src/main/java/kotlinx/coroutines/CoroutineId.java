package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0080\b\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0018B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\t\u001a\u00020\u0005HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0002H\u0016J\b\u0010\u0016\u001a\u00020\u0002H\u0016J\u0010\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"},
   d2 = {"Lkotlinx/coroutines/CoroutineId;", "Lkotlinx/coroutines/ThreadContextElement;", "", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "id", "", "(J)V", "getId", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "restoreThreadContext", "", "context", "Lkotlin/coroutines/CoroutineContext;", "oldState", "toString", "updateThreadContext", "Key", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class CoroutineId extends AbstractCoroutineContextElement implements ThreadContextElement {
   public static final Key Key = new Key((DefaultConstructorMarker)null);
   private final long id;

   public CoroutineId(long var1) {
      super((CoroutineContext.Key)Key);
      this.id = var1;
   }

   // $FF: synthetic method
   public static CoroutineId copy$default(CoroutineId var0, long var1, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.id;
      }

      return var0.copy(var1);
   }

   public final long component1() {
      return this.id;
   }

   public final CoroutineId copy(long var1) {
      return new CoroutineId(var1);
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (!(var1 instanceof CoroutineId)) {
            return false;
         }

         CoroutineId var2 = (CoroutineId)var1;
         if (this.id != var2.id) {
            return false;
         }
      }

      return true;
   }

   public Object fold(Object var1, Function2 var2) {
      return ThreadContextElement.DefaultImpls.fold(this, var1, var2);
   }

   public Element get(CoroutineContext.Key var1) {
      return ThreadContextElement.DefaultImpls.get(this, var1);
   }

   public final long getId() {
      return this.id;
   }

   public int hashCode() {
      long var1 = this.id;
      return (int)(var1 ^ var1 >>> 32);
   }

   public CoroutineContext minusKey(CoroutineContext.Key var1) {
      return ThreadContextElement.DefaultImpls.minusKey(this, var1);
   }

   public CoroutineContext plus(CoroutineContext var1) {
      return ThreadContextElement.DefaultImpls.plus(this, var1);
   }

   public void restoreThreadContext(CoroutineContext var1, String var2) {
      Thread.currentThread().setName(var2);
   }

   public String toString() {
      return "CoroutineId(" + this.id + ')';
   }

   public String updateThreadContext(CoroutineContext var1) {
      String var9;
      label20: {
         CoroutineName var8 = (CoroutineName)var1.get((CoroutineContext.Key)CoroutineName.Key);
         if (var8 != null) {
            var9 = var8.getName();
            if (var9 != null) {
               break label20;
            }
         }

         var9 = "coroutine";
      }

      Thread var4 = Thread.currentThread();
      String var5 = var4.getName();
      int var3 = StringsKt.lastIndexOf$default((CharSequence)var5, " @", 0, false, 6, (Object)null);
      int var2 = var3;
      if (var3 < 0) {
         var2 = var5.length();
      }

      StringBuilder var6 = new StringBuilder(var9.length() + var2 + 10);
      if (var5 != null) {
         String var7 = var5.substring(0, var2);
         Intrinsics.checkNotNullExpressionValue(var7, "(this as java.lang.Strin…ing(startIndex, endIndex)");
         var6.append(var7);
         var6.append(" @");
         var6.append(var9);
         var6.append('#');
         var6.append(this.id);
         Unit var10 = Unit.INSTANCE;
         var9 = var6.toString();
         Intrinsics.checkNotNullExpressionValue(var9, "StringBuilder(capacity).…builderAction).toString()");
         var4.setName(var9);
         return var5;
      } else {
         throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"},
      d2 = {"Lkotlinx/coroutines/CoroutineId$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/CoroutineId;", "()V", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public static final class Key implements CoroutineContext.Key {
      private Key() {
      }

      // $FF: synthetic method
      public Key(DefaultConstructorMarker var1) {
         this();
      }
   }
}
