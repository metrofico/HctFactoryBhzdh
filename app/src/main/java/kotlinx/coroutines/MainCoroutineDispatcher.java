package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\n\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0005R\u0012\u0010\u0003\u001a\u00020\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\t"},
   d2 = {"Lkotlinx/coroutines/MainCoroutineDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "()V", "immediate", "getImmediate", "()Lkotlinx/coroutines/MainCoroutineDispatcher;", "toString", "", "toStringInternalImpl", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public abstract class MainCoroutineDispatcher extends CoroutineDispatcher {
   public abstract MainCoroutineDispatcher getImmediate();

   public String toString() {
      String var1 = this.toStringInternalImpl();
      if (var1 == null) {
         var1 = DebugStringsKt.getClassSimpleName(this) + '@' + DebugStringsKt.getHexAddress(this);
      }

      return var1;
   }

   protected final String toStringInternalImpl() {
      MainCoroutineDispatcher var1 = Dispatchers.getMain();
      MainCoroutineDispatcher var2 = (MainCoroutineDispatcher)this;
      if (this == var1) {
         return "Dispatchers.Main";
      } else {
         try {
            var1 = var1.getImmediate();
         } catch (UnsupportedOperationException var3) {
            var1 = null;
         }

         return this == var1 ? "Dispatchers.Main.immediate" : null;
      }
   }
}
