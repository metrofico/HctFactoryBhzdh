package kotlin.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH$J\b\u0010\n\u001a\u00020\tH\u0004J\t\u0010\u000b\u001a\u00020\fH\u0096\u0002J\u000e\u0010\r\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001a\u00020\fH\u0002R\u0012\u0010\u0004\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"},
   d2 = {"Lkotlin/collections/AbstractIterator;", "T", "", "()V", "nextValue", "Ljava/lang/Object;", "state", "Lkotlin/collections/State;", "computeNext", "", "done", "hasNext", "", "next", "()Ljava/lang/Object;", "setNext", "value", "(Ljava/lang/Object;)V", "tryToComputeNext", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public abstract class AbstractIterator implements Iterator, KMappedMarker {
   private Object nextValue;
   private State state;

   public AbstractIterator() {
      this.state = State.NotReady;
   }

   private final boolean tryToComputeNext() {
      this.state = State.Failed;
      this.computeNext();
      boolean var1;
      if (this.state == State.Ready) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   protected abstract void computeNext();

   protected final void done() {
      this.state = State.Done;
   }

   public boolean hasNext() {
      State var4 = this.state;
      State var3 = State.Failed;
      boolean var2 = false;
      boolean var1;
      if (var4 != var3) {
         var1 = true;
      } else {
         var1 = false;
      }

      if (var1) {
         var3 = this.state;
         int var5 = WhenMappings.$EnumSwitchMapping$0[var3.ordinal()];
         if (var5 != 1) {
            if (var5 != 2) {
               var2 = this.tryToComputeNext();
            } else {
               var2 = true;
            }
         }

         return var2;
      } else {
         throw new IllegalArgumentException("Failed requirement.".toString());
      }
   }

   public Object next() {
      if (this.hasNext()) {
         this.state = State.NotReady;
         return this.nextValue;
      } else {
         throw new NoSuchElementException();
      }
   }

   public void remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   protected final void setNext(Object var1) {
      this.nextValue = var1;
      this.state = State.Ready;
   }

   @Metadata(
      k = 3,
      mv = {1, 7, 1},
      xi = 48
   )
   public final class WhenMappings {
      public static final int[] $EnumSwitchMapping$0;

      static {
         int[] var0 = new int[State.values().length];
         var0[State.Done.ordinal()] = 1;
         var0[State.Ready.ordinal()] = 2;
         $EnumSwitchMapping$0 = var0;
      }
   }
}
