package kotlin.collections;

import java.util.Enumeration;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086\u0002¨\u0006\u0004"},
   d2 = {"iterator", "", "T", "Ljava/util/Enumeration;", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/collections/CollectionsKt"
)
class CollectionsKt__IteratorsJVMKt extends CollectionsKt__IterablesKt {
   public CollectionsKt__IteratorsJVMKt() {
   }

   public static final Iterator iterator(Enumeration var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return (Iterator)(new Iterator(var0) {
         final Enumeration $this_iterator;

         {
            this.$this_iterator = var1;
         }

         public boolean hasNext() {
            return this.$this_iterator.hasMoreElements();
         }

         public Object next() {
            return this.$this_iterator.nextElement();
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }
      });
   }
}
