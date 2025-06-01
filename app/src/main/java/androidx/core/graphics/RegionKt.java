package androidx.core.graphics;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.graphics.Region.Op;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0007\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\f\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\f\u001a\u0015\u0010\u0004\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\n\u001a0\u0010\b\u001a\u00020\t*\u00020\u00012!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\t0\u000bH\u0086\b\u001a\u0013\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010*\u00020\u0001H\u0086\u0002\u001a\u0015\u0010\u0011\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\n\u001a\u0015\u0010\u0011\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\n\u001a\r\u0010\u0012\u001a\u00020\u0001*\u00020\u0001H\u0086\n\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\f\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\f\u001a\u0015\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\n\u001a\u0015\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\n\u001a\r\u0010\u0015\u001a\u00020\u0001*\u00020\u0001H\u0086\n\u001a\u0015\u0010\u0016\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\f\u001a\u0015\u0010\u0016\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\f¨\u0006\u0017"},
   d2 = {"and", "Landroid/graphics/Region;", "r", "Landroid/graphics/Rect;", "contains", "", "p", "Landroid/graphics/Point;", "forEach", "", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "rect", "iterator", "", "minus", "not", "or", "plus", "unaryMinus", "xor", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class RegionKt {
   public static final Region and(Region var0, Rect var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$and");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      var0 = new Region(var0);
      var0.op(var1, Op.INTERSECT);
      return var0;
   }

   public static final Region and(Region var0, Region var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$and");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      var0 = new Region(var0);
      var0.op(var1, Op.INTERSECT);
      return var0;
   }

   public static final boolean contains(Region var0, Point var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$contains");
      Intrinsics.checkParameterIsNotNull(var1, "p");
      return var0.contains(var1.x, var1.y);
   }

   public static final void forEach(Region var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$forEach");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      RegionIterator var3 = new RegionIterator(var0);

      while(true) {
         Rect var2 = new Rect();
         if (!var3.next(var2)) {
            return;
         }

         var1.invoke(var2);
      }
   }

   public static final Iterator iterator(Region var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$iterator");
      return (Iterator)(new Iterator(var0) {
         final Region $this_iterator;
         private boolean hasMore;
         private final RegionIterator iterator;
         private final Rect rect;

         {
            this.$this_iterator = var1;
            RegionIterator var3 = new RegionIterator(var1);
            this.iterator = var3;
            Rect var2 = new Rect();
            this.rect = var2;
            this.hasMore = var3.next(var2);
         }

         public boolean hasNext() {
            return this.hasMore;
         }

         public Rect next() {
            if (this.hasMore) {
               Rect var1 = new Rect(this.rect);
               this.hasMore = this.iterator.next(this.rect);
               return var1;
            } else {
               throw (Throwable)(new IndexOutOfBoundsException());
            }
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }
      });
   }

   public static final Region minus(Region var0, Rect var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$minus");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      var0 = new Region(var0);
      var0.op(var1, Op.DIFFERENCE);
      return var0;
   }

   public static final Region minus(Region var0, Region var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$minus");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      var0 = new Region(var0);
      var0.op(var1, Op.DIFFERENCE);
      return var0;
   }

   public static final Region not(Region var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$not");
      Region var1 = new Region(var0.getBounds());
      var1.op(var0, Op.DIFFERENCE);
      return var1;
   }

   public static final Region or(Region var0, Rect var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$or");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      var0 = new Region(var0);
      var0.union(var1);
      return var0;
   }

   public static final Region or(Region var0, Region var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$or");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      var0 = new Region(var0);
      var0.op(var1, Op.UNION);
      return var0;
   }

   public static final Region plus(Region var0, Rect var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      var0 = new Region(var0);
      var0.union(var1);
      return var0;
   }

   public static final Region plus(Region var0, Region var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plus");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      var0 = new Region(var0);
      var0.op(var1, Op.UNION);
      return var0;
   }

   public static final Region unaryMinus(Region var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$unaryMinus");
      Region var1 = new Region(var0.getBounds());
      var1.op(var0, Op.DIFFERENCE);
      return var1;
   }

   public static final Region xor(Region var0, Rect var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$xor");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      var0 = new Region(var0);
      var0.op(var1, Op.XOR);
      return var0;
   }

   public static final Region xor(Region var0, Region var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$xor");
      Intrinsics.checkParameterIsNotNull(var1, "r");
      var0 = new Region(var0);
      var0.op(var1, Op.XOR);
      return var0;
   }
}
