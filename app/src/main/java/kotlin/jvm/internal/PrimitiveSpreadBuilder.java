package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.ranges.IntRange;

@Metadata(
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\t\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00028\u0000¢\u0006\u0002\u0010\u0012J\b\u0010\u0003\u001a\u00020\u0004H\u0004J\u001d\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010\u0016J\u0011\u0010\u0017\u001a\u00020\u0004*\u00028\u0000H$¢\u0006\u0002\u0010\u0018R\u001a\u0010\u0006\u001a\u00020\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0005R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000bX\u0082\u0004¢\u0006\n\n\u0002\u0010\u000e\u0012\u0004\b\f\u0010\r¨\u0006\u0019"},
   d2 = {"Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "T", "", "size", "", "(I)V", "position", "getPosition", "()I", "setPosition", "spreads", "", "getSpreads$annotations", "()V", "[Ljava/lang/Object;", "addSpread", "", "spreadArgument", "(Ljava/lang/Object;)V", "toArray", "values", "result", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getSize", "(Ljava/lang/Object;)I", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public abstract class PrimitiveSpreadBuilder {
   private int position;
   private final int size;
   private final Object[] spreads;

   public PrimitiveSpreadBuilder(int var1) {
      this.size = var1;
      this.spreads = new Object[var1];
   }

   // $FF: synthetic method
   private static void getSpreads$annotations() {
   }

   public final void addSpread(Object var1) {
      Intrinsics.checkNotNullParameter(var1, "spreadArgument");
      Object[] var3 = this.spreads;
      int var2 = this.position++;
      var3[var2] = var1;
   }

   protected final int getPosition() {
      return this.position;
   }

   protected abstract int getSize(Object var1);

   protected final void setPosition(int var1) {
      this.position = var1;
   }

   protected final int size() {
      int var2 = this.size;
      int var1 = 0;

      for(IntIterator var3 = (new IntRange(0, var2 - 1)).iterator(); var3.hasNext(); var1 += var2) {
         var2 = var3.nextInt();
         Object var4 = this.spreads[var2];
         if (var4 != null) {
            var2 = this.getSize(var4);
         } else {
            var2 = 1;
         }
      }

      return var1;
   }

   protected final Object toArray(Object var1, Object var2) {
      Intrinsics.checkNotNullParameter(var1, "values");
      Intrinsics.checkNotNullParameter(var2, "result");
      IntIterator var8 = (new IntRange(0, this.size - 1)).iterator();
      int var4 = 0;
      int var3 = 0;

      int var5;
      while(var8.hasNext()) {
         int var6 = var8.nextInt();
         Object var7 = this.spreads[var6];
         if (var7 != null) {
            var5 = var3;
            if (var4 < var6) {
               var5 = var6 - var4;
               System.arraycopy(var1, var4, var2, var3, var5);
               var5 += var3;
            }

            var3 = this.getSize(var7);
            System.arraycopy(var7, 0, var2, var5, var3);
            var3 += var5;
            var4 = var6 + 1;
         }
      }

      var5 = this.size;
      if (var4 < var5) {
         System.arraycopy(var1, var4, var2, var3, var5 - var4);
      }

      return var2;
   }
}
