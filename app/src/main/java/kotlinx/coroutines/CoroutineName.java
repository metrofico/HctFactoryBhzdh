package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"},
   d2 = {"Lkotlinx/coroutines/CoroutineName;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Key", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class CoroutineName extends AbstractCoroutineContextElement {
   public static final Key Key = new Key((DefaultConstructorMarker)null);
   private final String name;

   public CoroutineName(String var1) {
      super((CoroutineContext.Key)Key);
      this.name = var1;
   }

   // $FF: synthetic method
   public static CoroutineName copy$default(CoroutineName var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.name;
      }

      return var0.copy(var1);
   }

   public final String component1() {
      return this.name;
   }

   public final CoroutineName copy(String var1) {
      return new CoroutineName(var1);
   }

   public boolean equals(Object var1) {
      if (this != var1) {
         if (!(var1 instanceof CoroutineName)) {
            return false;
         }

         CoroutineName var2 = (CoroutineName)var1;
         if (!Intrinsics.areEqual((Object)this.name, (Object)var2.name)) {
            return false;
         }
      }

      return true;
   }

   public final String getName() {
      return this.name;
   }

   public int hashCode() {
      String var2 = this.name;
      int var1;
      if (var2 != null) {
         var1 = var2.hashCode();
      } else {
         var1 = 0;
      }

      return var1;
   }

   public String toString() {
      return "CoroutineName(" + this.name + ')';
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"},
      d2 = {"Lkotlinx/coroutines/CoroutineName$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/CoroutineName;", "()V", "kotlinx-coroutines-core"},
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
