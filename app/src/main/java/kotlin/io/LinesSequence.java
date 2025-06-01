package kotlin.io;

import java.io.BufferedReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(
   d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H\u0096\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"},
   d2 = {"Lkotlin/io/LinesSequence;", "Lkotlin/sequences/Sequence;", "", "reader", "Ljava/io/BufferedReader;", "(Ljava/io/BufferedReader;)V", "iterator", "", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
final class LinesSequence implements Sequence {
   private final BufferedReader reader;

   public LinesSequence(BufferedReader var1) {
      Intrinsics.checkNotNullParameter(var1, "reader");
      super();
      this.reader = var1;
   }

   public Iterator iterator() {
      return (Iterator)(new Iterator(this) {
         private boolean done;
         private String nextValue;
         final LinesSequence this$0;

         {
            this.this$0 = var1;
         }

         public boolean hasNext() {
            String var2 = this.nextValue;
            boolean var1 = true;
            if (var2 == null && !this.done) {
               var2 = this.this$0.reader.readLine();
               this.nextValue = var2;
               if (var2 == null) {
                  this.done = true;
               }
            }

            if (this.nextValue == null) {
               var1 = false;
            }

            return var1;
         }

         public String next() {
            if (this.hasNext()) {
               String var1 = this.nextValue;
               this.nextValue = null;
               Intrinsics.checkNotNull(var1);
               return var1;
            } else {
               throw new NoSuchElementException();
            }
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }
      });
   }
}
