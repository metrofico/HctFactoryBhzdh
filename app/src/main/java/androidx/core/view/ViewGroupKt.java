package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010)\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\u0086\n\u001a0\u0010\r\u001a\u00020\u000e*\u00020\u00032!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u000e0\u0010H\u0086\b\u001aE\u0010\u0013\u001a\u00020\u000e*\u00020\u000326\u0010\u000f\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u000e0\u0014H\u0086\b\u001a\u0015\u0010\u0016\u001a\u00020\u0002*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0007H\u0086\u0002\u001a\r\u0010\u0017\u001a\u00020\u000b*\u00020\u0003H\u0086\b\u001a\r\u0010\u0018\u001a\u00020\u000b*\u00020\u0003H\u0086\b\u001a\u0013\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u001a*\u00020\u0003H\u0086\u0002\u001a\u0015\u0010\u001b\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\u0086\n\u001a\u0015\u0010\u001c\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\u0086\n\u001a\u0017\u0010\u001d\u001a\u00020\u000e*\u00020\u001e2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0086\b\u001a5\u0010\u001f\u001a\u00020\u000e*\u00020\u001e2\b\b\u0003\u0010 \u001a\u00020\u00072\b\b\u0003\u0010!\u001a\u00020\u00072\b\b\u0003\u0010\"\u001a\u00020\u00072\b\b\u0003\u0010#\u001a\u00020\u0007H\u0086\b\u001a5\u0010$\u001a\u00020\u000e*\u00020\u001e2\b\b\u0003\u0010%\u001a\u00020\u00072\b\b\u0003\u0010!\u001a\u00020\u00072\b\b\u0003\u0010&\u001a\u00020\u00072\b\b\u0003\u0010#\u001a\u00020\u0007H\u0087\b\"\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0016\u0010\u0006\u001a\u00020\u0007*\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006'"},
   d2 = {"children", "Lkotlin/sequences/Sequence;", "Landroid/view/View;", "Landroid/view/ViewGroup;", "getChildren", "(Landroid/view/ViewGroup;)Lkotlin/sequences/Sequence;", "size", "", "getSize", "(Landroid/view/ViewGroup;)I", "contains", "", "view", "forEach", "", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "forEachIndexed", "Lkotlin/Function2;", "index", "get", "isEmpty", "isNotEmpty", "iterator", "", "minusAssign", "plusAssign", "setMargins", "Landroid/view/ViewGroup$MarginLayoutParams;", "updateMargins", "left", "top", "right", "bottom", "updateMarginsRelative", "start", "end", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class ViewGroupKt {
   public static final boolean contains(ViewGroup var0, View var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$contains");
      Intrinsics.checkParameterIsNotNull(var1, "view");
      boolean var2;
      if (var0.indexOfChild(var1) != -1) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static final void forEach(ViewGroup var0, Function1 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$forEach");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      int var3 = var0.getChildCount();

      for(int var2 = 0; var2 < var3; ++var2) {
         View var4 = var0.getChildAt(var2);
         Intrinsics.checkExpressionValueIsNotNull(var4, "getChildAt(index)");
         var1.invoke(var4);
      }

   }

   public static final void forEachIndexed(ViewGroup var0, Function2 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$forEachIndexed");
      Intrinsics.checkParameterIsNotNull(var1, "action");
      int var3 = var0.getChildCount();

      for(int var2 = 0; var2 < var3; ++var2) {
         View var4 = var0.getChildAt(var2);
         Intrinsics.checkExpressionValueIsNotNull(var4, "getChildAt(index)");
         var1.invoke(var2, var4);
      }

   }

   public static final View get(ViewGroup var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$get");
      View var2 = var0.getChildAt(var1);
      if (var2 != null) {
         return var2;
      } else {
         throw (Throwable)(new IndexOutOfBoundsException("Index: " + var1 + ", Size: " + var0.getChildCount()));
      }
   }

   public static final Sequence getChildren(ViewGroup var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$children");
      return (Sequence)(new Sequence(var0) {
         final ViewGroup $this_children;

         {
            this.$this_children = var1;
         }

         public Iterator iterator() {
            return ViewGroupKt.iterator(this.$this_children);
         }
      });
   }

   public static final int getSize(ViewGroup var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$size");
      return var0.getChildCount();
   }

   public static final boolean isEmpty(ViewGroup var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isEmpty");
      boolean var1;
      if (var0.getChildCount() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final boolean isNotEmpty(ViewGroup var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$isNotEmpty");
      boolean var1;
      if (var0.getChildCount() != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final Iterator iterator(ViewGroup var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$iterator");
      return (Iterator)(new Iterator(var0) {
         final ViewGroup $this_iterator;
         private int index;

         {
            this.$this_iterator = var1;
         }

         public boolean hasNext() {
            boolean var1;
            if (this.index < this.$this_iterator.getChildCount()) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         public View next() {
            ViewGroup var2 = this.$this_iterator;
            int var1 = this.index++;
            View var3 = var2.getChildAt(var1);
            if (var3 != null) {
               return var3;
            } else {
               throw (Throwable)(new IndexOutOfBoundsException());
            }
         }

         public void remove() {
            ViewGroup var2 = this.$this_iterator;
            int var1 = this.index - 1;
            this.index = var1;
            var2.removeViewAt(var1);
         }
      });
   }

   public static final void minusAssign(ViewGroup var0, View var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$minusAssign");
      Intrinsics.checkParameterIsNotNull(var1, "view");
      var0.removeView(var1);
   }

   public static final void plusAssign(ViewGroup var0, View var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$plusAssign");
      Intrinsics.checkParameterIsNotNull(var1, "view");
      var0.addView(var1);
   }

   public static final void setMargins(ViewGroup.MarginLayoutParams var0, int var1) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$setMargins");
      var0.setMargins(var1, var1, var1, var1);
   }

   public static final void updateMargins(ViewGroup.MarginLayoutParams var0, int var1, int var2, int var3, int var4) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$updateMargins");
      var0.setMargins(var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static void updateMargins$default(ViewGroup.MarginLayoutParams var0, int var1, int var2, int var3, int var4, int var5, Object var6) {
      if ((var5 & 1) != 0) {
         var1 = var0.leftMargin;
      }

      if ((var5 & 2) != 0) {
         var2 = var0.topMargin;
      }

      if ((var5 & 4) != 0) {
         var3 = var0.rightMargin;
      }

      if ((var5 & 8) != 0) {
         var4 = var0.bottomMargin;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$updateMargins");
      var0.setMargins(var1, var2, var3, var4);
   }

   public static final void updateMarginsRelative(ViewGroup.MarginLayoutParams var0, int var1, int var2, int var3, int var4) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$updateMarginsRelative");
      var0.setMarginStart(var1);
      var0.topMargin = var2;
      var0.setMarginEnd(var3);
      var0.bottomMargin = var4;
   }

   // $FF: synthetic method
   public static void updateMarginsRelative$default(ViewGroup.MarginLayoutParams var0, int var1, int var2, int var3, int var4, int var5, Object var6) {
      if ((var5 & 1) != 0) {
         var1 = var0.getMarginStart();
      }

      if ((var5 & 2) != 0) {
         var2 = var0.topMargin;
      }

      if ((var5 & 4) != 0) {
         var3 = var0.getMarginEnd();
      }

      if ((var5 & 8) != 0) {
         var4 = var0.bottomMargin;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$updateMarginsRelative");
      var0.setMarginStart(var1);
      var0.topMargin = var2;
      var0.setMarginEnd(var3);
      var0.bottomMargin = var4;
   }
}
