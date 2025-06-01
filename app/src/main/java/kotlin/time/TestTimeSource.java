package kotlin.time;

import kotlin.Metadata;

@Metadata(
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\nJ\b\u0010\r\u001a\u00020\u0004H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"},
   d2 = {"Lkotlin/time/TestTimeSource;", "Lkotlin/time/AbstractLongTimeSource;", "()V", "reading", "", "overflow", "", "duration", "Lkotlin/time/Duration;", "overflow-LRDsOJo", "(J)V", "plusAssign", "plusAssign-LRDsOJo", "read", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class TestTimeSource extends AbstractLongTimeSource {
   private long reading;

   public TestTimeSource() {
      super(DurationUnit.NANOSECONDS);
   }

   private final void overflow_LRDsOJo(long var1) {
      throw new IllegalStateException("TestTimeSource will overflow if its reading " + this.reading + "ns is advanced by " + Duration.toString_impl(var1) + '.');
   }

   public final void plusAssign_LRDsOJo(long var1) {
      long var9 = Duration.toLong_impl(var1, this.getUnit());
      long var5;
      if (var9 != Long.MIN_VALUE && var9 != Long.MAX_VALUE) {
         long var11 = this.reading;
         long var7 = var11 + var9;
         var5 = var7;
         if ((var9 ^ var11) >= 0L) {
            var5 = var7;
            if ((var11 ^ var7) < 0L) {
               this.overflow_LRDsOJo(var1);
               var5 = var7;
            }
         }
      } else {
         double var3 = Duration.toDouble_impl(var1, this.getUnit());
         var3 += (double)this.reading;
         if (var3 > 9.223372036854776E18 || var3 < -9.223372036854776E18) {
            this.overflow_LRDsOJo(var1);
         }

         var5 = (long)var3;
      }

      this.reading = var5;
   }

   protected long read() {
      return this.reading;
   }
}
