package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(
   d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0010\u0007\n\u0002\b\u0005\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u0002H\u0086\u0002¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0002H&¨\u0006\u0007"},
   d2 = {"Lkotlin/collections/FloatIterator;", "", "", "()V", "next", "()Ljava/lang/Float;", "nextFloat", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public abstract class FloatIterator implements Iterator, KMappedMarker {
   public final Float next() {
      return this.nextFloat();
   }

   public abstract float nextFloat();

   public void remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
